package com.sunil.workx.api.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunil.workx.api.domain.WorkerShiftDTO;
import com.sunil.workx.api.entity.Shift;
import com.sunil.workx.api.entity.Worker;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WorkerShiftControllerIT {

	@Autowired
	private MockMvc mockMvc;

	private long shiftId;
	private long workerId;

	@BeforeAll
	public void setup() throws Exception {
		MvcResult result = mockMvc
				.perform(post("/shift").contentType("application/json").content("{\"name\": \"Shift-"
						+ System.currentTimeMillis() + "\", \"startTime\": \"00:00\", \"endTime\": \"08:00\"}"))
				.andReturn();
		Shift shift = new ObjectMapper().readValue(result.getResponse().getContentAsString(), Shift.class);
		shiftId = shift.getId();
		result = mockMvc.perform(post("/worker").contentType("application/json")
				.content("{ \"name\": \"Worker-" + System.currentTimeMillis() + "\" }")).andReturn();
		Worker worker = new ObjectMapper().readValue(result.getResponse().getContentAsString(), Worker.class);
		workerId = worker.getId();
	}

	@Test
	public void testPostWorkerShift() throws Exception {
		mockMvc.perform(post("/workerShift")
				.content("{ \"date\": \"2023-06-02\", \"workerId\": " + workerId + ", \"shiftId\": " + shiftId + " }")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void testPostWorkerShiftDuplicate() throws Exception {
		mockMvc.perform(post("/workerShift")
				.content("{ \"date\": \"2023-06-03\", \"workerId\": " + workerId + ", \"shiftId\": " + shiftId + " }")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		mockMvc.perform(post("/workerShift")
				.content("{ \"date\": \"2023-06-03\", \"workerId\": " + workerId + ", \"shiftId\": " + shiftId + " }")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isConflict());
	}

	@Test
	public void testGetAllWorkerShifts() throws Exception {
		mockMvc.perform(get("/workerShift")).andExpect(status().isOk())
				.andExpect(content().string(containsString("workerId")));
	}

	@Test
	public void testGetWorkerShift() throws Exception {
		MvcResult result = mockMvc.perform(
				get("/workerShift").queryParam("workerId", "" + workerId).contentType(MediaType.APPLICATION_JSON))
				.andReturn();

		WorkerShiftDTO[] workerShift = new ObjectMapper().readValue(result.getResponse().getContentAsString(),
				WorkerShiftDTO[].class);
		mockMvc.perform(get("/workerShift/" + workerShift[0].getId()))
				.andExpect(content().string(containsString("" + workerId)));
	}

}
