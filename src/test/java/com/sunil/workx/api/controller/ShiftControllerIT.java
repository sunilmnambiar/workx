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
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunil.workx.api.entity.Shift;

@SpringBootTest
@AutoConfigureMockMvc
public class ShiftControllerIT {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testPostShift() throws Exception {
		mockMvc.perform(post("/shift").contentType("application/json")
				.content("{\"name\": \"ShiftPOST-" + System.currentTimeMillis()
						+ "\", \"startTime\": \"00:00\", \"endTime\": \"08:00\"}"))
				.andExpect(status().isOk()).andExpect(content().string(containsString("ShiftPOST")));
	}

	@Test
	public void testPostShiftDuplicate() throws Exception {
		String shiftName = "Shift-" + System.currentTimeMillis();
		mockMvc.perform(post("/shift").contentType("application/json")
				.content("{\"name\": \"" + shiftName + "\", \"startTime\": \"00:00\", \"endTime\": \"08:00\"}"))
				.andExpect(status().isOk());
		// duplicate
		mockMvc.perform(post("/shift").contentType("application/json")
				.content("{\"name\": \"" + shiftName + "\", \"startTime\": \"00:00\", \"endTime\": \"08:00\"}"))
				.andExpect(status().isConflict());
	}

	@Test
	public void testGetAllShifts() throws Exception {
		mockMvc.perform(get("/shift")).andExpect(status().isOk())
				.andExpect(content().string(containsString("ShiftPOST")));
	}

	@Test
	public void testGetShift() throws Exception {
		MvcResult result = mockMvc
				.perform(post("/shift").contentType("application/json").content("{\"name\": \"ShiftGET-"
						+ System.currentTimeMillis() + "\", \"startTime\": \"00:00\", \"endTime\": \"08:00\"}"))
				.andReturn();
		Shift shift = new ObjectMapper().readValue(result.getResponse().getContentAsString(), Shift.class);
		mockMvc.perform(get("/shift/" + shift.getId())).andExpect(content().string(containsString("ShiftGET")));
	}

}
