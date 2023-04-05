package com.sunil.workx.api.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.sunil.workx.api.entity.Shift;

public class ShiftControllerTest {
	
	private ShiftController shiftController = new ShiftController();
	
	@Test
	public void testGetAllShifts() throws Exception {
		assertThat(shiftController.getAllShifts()).isNotNull();
	}
	
	@Test
	public void testGetShift() throws Exception {
		assertThat(shiftController.getShift(1)).isNotNull();
	}
	
	@Test
	public void testCreateShift() throws Exception {
		assertThat(shiftController.createShift(Shift.builder().build())).isNotNull();
	}
	
	@Test
	public void testUpdateShift() throws Exception {
		assertThat(shiftController.updateShift(Shift.builder().build())).isNotNull();
	}
	
	@Test
	public void testDeleteShift() throws Exception {
		
	}
	

}
