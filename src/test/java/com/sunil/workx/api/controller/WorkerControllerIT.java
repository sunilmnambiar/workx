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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunil.workx.api.entity.Worker;

@SpringBootTest
@AutoConfigureMockMvc
public class WorkerControllerIT {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testPostWorker() throws Exception {
		mockMvc.perform(post("/worker").content("{ \"name\": \"TESTUSER0001" + System.currentTimeMillis() + "\" }")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().string(containsString("TESTUSER0001")));
	}

	@Test
	public void testGetAllWorkers() throws Exception {
		mockMvc.perform(get("/worker")).andExpect(status().isOk())
				.andExpect(content().string(containsString("TESTUSER0001")));
	}

	@Test
	public void testGetWorker() throws Exception {
		MvcResult result = mockMvc.perform(post("/worker").contentType("application/json")
				.content("{ \"name\": \"TESTUSER0002" + System.currentTimeMillis() + "\" }")).andReturn();
		Worker worker = new ObjectMapper().readValue(result.getResponse().getContentAsString(), Worker.class);
		mockMvc.perform(get("/worker/" + worker.getId())).andExpect(content().string(containsString("TESTUSER0002")));
	}

}
