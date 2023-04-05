package com.sunil.workx.api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
		mockMvc.perform(get("/shift")
	            .contentType("application/json"))
	            .andExpect(status().isOk())
	            .andExpect(content().json("[]"));
	}
	
	@Test
	public void testGetShift() throws Exception {
		mockMvc.perform(get("/shift/1")
	            .contentType("application/json"))
	            .andExpect(status().isOk())
	            .andExpect(content().json("{}"));
	}
	

}
