package com.sunil.workx.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sunil.workx.api.config.ShiftService;
import com.sunil.workx.api.entity.Shift;
import com.sunil.workx.api.exception.ResourceAlreadyExistsException;
import com.sunil.workx.api.exception.ResourceNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ShiftController {

	private ShiftService shiftService;

	@GetMapping("/shift")
	List<Shift> getAllShifts() {
		log.info("Get all shifts");
		return shiftService.getAllShifts();
	}

	@GetMapping("/shift/{id}")
	Shift getShift(@PathVariable Long id) {
		log.info("Get shift: {}", id);
		return shiftService.getShift(id).orElseThrow(() -> new ResourceNotFoundException("Shift with id: " + id));
	}

	@PostMapping("/shift")
	Shift createShift(@RequestBody Shift shift) {
		log.info("Create shift: {}", shift.getName());
		try {
			return shiftService.upsertShift(shift);
		} catch(Exception e) {
			String errMsg = "Shift with name' " + shift.getName() + "' already exists";
			log.error(errMsg, e);
			throw new ResourceAlreadyExistsException(errMsg);
		}
	}

	@PutMapping("/shift")
	Shift updateShift(@RequestBody Shift shift) {
		log.info("Update shift: {}", shift.getId());
		return shiftService.upsertShift(shift);
	}

	@DeleteMapping("/shift/{id}")
	void deleteShift(@PathVariable Long id) {
		log.info("Delete shift: {}", id);
		shiftService.deleteShift(id);
	}

	public ShiftController(ShiftService shiftService) {
		this.shiftService = shiftService;
	}

}
