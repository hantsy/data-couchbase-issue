package com.example.demo;


import com.example.demo.domain.Assignment;
import com.example.demo.repository.AssignmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Slf4j
@Import(TestCouchbaseConfig.class)
public class AssignmentRepositoryTest {

    @Autowired
    private AssignmentRepository assignmentRepository;

    @BeforeEach
    public void beforeEach() throws InterruptedException {
        log.debug("before each:::");
        this.assignmentRepository.deleteAll();


        Assignment assignment = Assignment.builder().assignee("Tom").roomNumber("101").build();
        Assignment assignment2 = Assignment.builder().assignee("Tom").roomNumber("102").build();

        this.assignmentRepository.saveAll(Arrays.asList(assignment, assignment2));

        log.debug("saved assignments: {}", this.assignmentRepository.findAll());
    }

    @AfterEach
    public void afterEach() {
        log.debug("after each:::");
        this.assignmentRepository.deleteAll();
    }

    @Test
    public void testGetAll() {
        List<Assignment> assignments = this.assignmentRepository.findAll();
        assertEquals(2, assignments.size());
    }

}
