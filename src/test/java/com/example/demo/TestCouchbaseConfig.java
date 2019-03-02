package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.core.query.Consistency;
import org.springframework.data.couchbase.repository.support.IndexManager;

import java.util.Arrays;
import java.util.List;

@TestConfiguration
@Slf4j
@Order(0)
public class TestCouchbaseConfig extends AbstractCouchbaseConfiguration {

    @Autowired
    private Environment env;


    @Override
    public IndexManager indexManager() {
        return new IndexManager(true, true, true);
    }

    @Override
    protected List<String> getBootstrapHosts() {
        return Arrays.asList(env.getProperty("spring.couchbase.bootstrap-hosts").split(","));
    }

    @Override
    protected String getBucketName() {
        return env.getProperty("spring.couchbase.bucket.name");
    }

    @Override
    protected String getBucketPassword() {
        return env.getProperty("spring.couchbase.bucket.password");
    }

    @Override
    protected Consistency getDefaultConsistency() {
        return Consistency.STRONGLY_CONSISTENT; //READ_YOUR_OWN_WRITES|UPDATE_AFTER ... etc;
    }
}

