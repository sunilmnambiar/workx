package com.sunil.workx.api.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import com.sunil.workx.api.domain.WorkerShiftDTO;
import com.sunil.workx.api.service.WorkerShiftService;

public class WorkerShiftControllerTest {

	private WorkerShiftService workerShiftService = mock(WorkerShiftService.class);
	private WorkerShiftController workerShiftController = new WorkerShiftController(workerShiftService);

	@Test
	public void testGetAllWorkerShifts() throws Exception {
		when(workerShiftService.getAllWorkerShifts()).thenReturn(Arrays.asList(WorkerShiftDTO.builder().build()));
		assertThat(workerShiftController.getWorkerShifts(null, null)).isNotNull();
	}
	
	@Test
	public void testGetAllWorkerShiftByWorker() throws Exception {
		when(workerShiftService.getWorkerShifts(10L, null)).thenReturn(Arrays.asList(WorkerShiftDTO.builder().build()));
		assertThat(workerShiftController.getWorkerShifts(10L, null)).isNotNull();
	}

	@Test
	public void testGetWorkerShift() throws Exception {
		when(workerShiftService.getWorkerShift(1L)).thenReturn(WorkerShiftDTO.builder().build());
		assertThat(workerShiftController.getWorkerShift(1L)).isNotNull();
	}

	@Test
	public void testCreateWorkerShift() throws Exception {
		WorkerShiftDTO workerShift = WorkerShiftDTO.builder().build();
		when(workerShiftService.upsertWorkerShift(workerShift)).thenReturn(workerShift);
		assertThat(workerShiftController.createWorkerShift(workerShift)).isNotNull();
	}

	@Test
	public void testUpdateWorkerShift() throws Exception {
		WorkerShiftDTO workerShift = WorkerShiftDTO.builder().build();
		when(workerShiftService.upsertWorkerShift(workerShift)).thenReturn(workerShift);
		assertThat(workerShiftController.updateWorkerShift(workerShift)).isNotNull();
	}

	@Test
	public void testDeleteWorkerShift() throws Exception {
		workerShiftController.deleteWorkerShift(1L);
		verify(workerShiftService, times(1)).deleteWorkerShift(1L);
	}

}
