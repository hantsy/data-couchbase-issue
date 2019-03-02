package com.example.demo.repository;

import com.example.demo.domain.Assignment;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.CouchbasePagingAndSortingRepository;

import java.util.List;

@ViewIndexed(designDoc = "assignment", viewName = "all")
public interface AssignmentRepository extends CouchbasePagingAndSortingRepository<Assignment, String> {

    List<Assignment> findAll();
}
