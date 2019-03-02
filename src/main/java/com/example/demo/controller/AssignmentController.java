package com.example.demo.controller;

import com.example.demo.domain.Assignment;
import com.example.demo.repository.AssignmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/api/assignments")
@RequiredArgsConstructor
@Slf4j
public class AssignmentController {

    private final AssignmentRepository assignmentRepository;

    @GetMapping
    public ResponseEntity<List<Assignment>> all() {
        List<Assignment> assignments = this.assignmentRepository.findAll();
        log.debug("Get all assignments:{}", assignments);
        return ok(assignments);
    }

    @GetMapping("{id}")
    public ResponseEntity<Assignment> get(@PathVariable String id) {
        Optional<Assignment> assignment = this.assignmentRepository.findById(id);
        assignment.ifPresent((a) -> log.debug("Get assignment by id : {}", a));

        return assignment.map(a -> ok(a)).orElse(notFound().build());
    }

    @PostMapping()
    public ResponseEntity save(@RequestBody Assignment assignment, ServletUriComponentsBuilder uriComponentsBuilder) {
        Assignment savedAssignment = this.assignmentRepository.save(assignment);
        log.debug("saved assignment: {}", savedAssignment);
        return created(
            uriComponentsBuilder
                .path("/api/assignments/{id}")
                .buildAndExpand(savedAssignment.getId())
                .toUri()
        )
            .build();
    }
}
