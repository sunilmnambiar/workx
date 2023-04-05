package com.sunil.workx.api.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sunil.workx.api.entity.Shift;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ShiftController {

	@GetMapping("/shift")
	List<Shift> getAllShifts() {
		log.info("Get all shifts");
		return Collections.emptyList();
	}
	
	@GetMapping("/shift/{id}")
	Shift getShift(@PathVariable Integer id) {
		log.info("Get shift: {}", id);
		return Shift.builder().build();
	}
	
	@PostMapping("/shift")
	Shift createShift(@RequestBody Shift shift) {
		log.info("Create shift: {}", shift.getName());
		return shift;
	}
	
	@PutMapping("/shift")
	Shift updateShift(@RequestBody Shift shift) {
		log.info("Update shift: {}", shift.getId());
		return shift;
	}
	
	@DeleteMapping("/shift/{id}")
	void deleteShift(@PathVariable Integer id) {
		log.info("Delete shift: {}", id);
	}

}
