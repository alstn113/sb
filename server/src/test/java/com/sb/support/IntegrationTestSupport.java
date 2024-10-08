package com.sb.support;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sb.support.cleaner.DatabaseCleaner;
import com.sb.support.cleaner.DatabaseClearExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(classes = {IntegrationTestSupport.TestConfig.class})
@AutoConfigureMockMvc
@ExtendWith(DatabaseClearExtension.class)
public abstract class IntegrationTestSupport {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @TestConfiguration
    public static class TestConfig {

        @Bean
        public DatabaseCleaner databaseCleaner() {
            return new DatabaseCleaner();
        }
    }
}
