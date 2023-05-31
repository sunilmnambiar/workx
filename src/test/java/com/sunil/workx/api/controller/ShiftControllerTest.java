package com.sunil.workx.api.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import com.sunil.workx.api.domain.ShiftDTO;
import com.sunil.workx.api.service.ShiftService;

public class ShiftControllerTest {

	private ShiftService shiftService = mock(ShiftService.class);
	private ShiftController shiftController = new ShiftController(shiftService);

	@Test
	public void testGetAllShifts() throws Exception {
		when(shiftService.getAllShifts()).thenReturn(Arrays.asList(ShiftDTO.builder().build()));
		assertThat(shiftController.getAllShifts()).isNotNull();
	}

	@Test
	public void testGetShift() throws Exception {
		when(shiftService.getShift(1L)).thenReturn(ShiftDTO.builder().build());
		assertThat(shiftController.getShift(1L)).isNotNull();
	}

	@Test
	public void testCreateShift() throws Exception {
		ShiftDTO shift = ShiftDTO.builder().build();
		when(shiftService.upsertShift(shift)).thenReturn(shift);
		assertThat(shiftController.createShift(shift)).isNotNull();
	}

	@Test
	public void testUpdateShift() throws Exception {
		ShiftDTO shift = ShiftDTO.builder().build();
		when(shiftService.upsertShift(shift)).thenReturn(shift);
		assertThat(shiftController.updateShift(shift)).isNotNull();
	}

	@Test
	public void testDeleteShift() throws Exception {
		shiftController.deleteShift(1L);
		verify(shiftService, times(1)).deleteShift(1L);
	}

}
