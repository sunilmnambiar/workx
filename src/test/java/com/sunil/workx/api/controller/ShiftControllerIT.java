package com.sunil.workx.api.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class ShiftControllerIT {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testGetAllShifts() throws Exception {
		mockMvc.perform(get("/shift"))
	            .andExpect(status().isOk())
	            .andExpect(content().string(containsString("[{\"id\":1,\"name\":\"Shift A\",\"startTime\":\"10:00:00\",\"endTime\":\"12:00:00\"}]")));
	}
	
	@Test
	public void testGetShift() throws Exception {
		mockMvc.perform(post("/shift")
	            .contentType("application/json")
	            .content("{\"name\": \"Shift A\", \"startTime\": \"10:00:00\", \"endTime\": \"12:00:00\"}"))
	            .andExpect(status().isOk())
	            .andExpect(content().json("{}"));
		mockMvc.perform(get("/shift/1"))
	            .andExpect(content().json("{\"id\": 1, \"name\": \"Shift A\", \"startTime\": \"10:00:00\", \"endTime\": \"12:00:00\"}"));
	}
	

}
