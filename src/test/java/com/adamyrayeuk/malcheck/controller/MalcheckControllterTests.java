package com.adamyrayeuk.malcheck.controller;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@ContextConfiguration
@AutoConfigureMockMvc
@SpringBootTest
public class MalcheckControllterTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void malwareCheckTest() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "test.txt", "", "nothing here".getBytes());

        mockMvc.perform(MockMvcRequestBuilders.multipart("/v1/api/malware-checking").file(file))
                .andExpect(status().isOk())
                .andExpect(content().json("{'message':'File is safe'}"));
    }
}
