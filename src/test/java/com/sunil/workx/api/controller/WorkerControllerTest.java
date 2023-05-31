package com.sunil.workx.api.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import com.sunil.workx.api.domain.WorkerDTO;
import com.sunil.workx.api.service.WorkerService;

public class WorkerControllerTest {

	private WorkerService workerService = mock(WorkerService.class);
	private WorkerController workerController = new WorkerController(workerService);

	@Test
	public void testGetAllWorkers() throws Exception {
		when(workerService.getAllWorkers()).thenReturn(Arrays.asList(WorkerDTO.builder().build()));
		assertThat(workerController.getAllWorkers()).isNotNull();
	}

	@Test
	public void testGetWorker() throws Exception {
		when(workerService.getWorker(1L)).thenReturn(WorkerDTO.builder().build());
		assertThat(workerController.getWorker(1L)).isNotNull();
	}

	@Test
	public void testCreateWorker() throws Exception {
		WorkerDTO worker = WorkerDTO.builder().build();
		when(workerService.upsertWorker(worker)).thenReturn(worker);
		assertThat(workerController.createWorker(worker)).isNotNull();
	}

	@Test
	public void testUpdateWorker() throws Exception {
		WorkerDTO worker = WorkerDTO.builder().build();
		when(workerService.upsertWorker(worker)).thenReturn(worker);
		assertThat(workerController.updateWorker(worker)).isNotNull();
	}

	@Test
	public void testDeleteWorker() throws Exception {
		workerController.deleteWorker(1L);
		verify(workerService, times(1)).deleteWorker(1L);
	}

}
