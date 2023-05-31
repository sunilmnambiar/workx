package com.sunil.workx.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sunil.workx.api.domain.ShiftDTO;
import com.sunil.workx.api.exception.BadRequestException;
import com.sunil.workx.api.exception.ResourceNotFoundException;
import com.sunil.workx.api.service.ShiftService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ShiftController {
	
	List<String> startTime = List.of("00:00", "08:00", "16:00");
	List<String> endTime = List.of("08:00", "16:00", "24:00");

	private ShiftService shiftService;

	@GetMapping("/shift")
	List<ShiftDTO> getAllShifts() {
		log.info("Get all shifts");
		return shiftService.getAllShifts();
	}

	@GetMapping("/shift/{id}")
	ShiftDTO getShift(@PathVariable Long id) {
		log.info("Get shift: {}", id);
		ShiftDTO shiftDTO = shiftService.getShift(id);
		if(shiftDTO == null) {
			throw new ResourceNotFoundException("Shift with id: " + id);
		} else {
			return shiftDTO;
		}
	}

	@PostMapping("/shift")
	ShiftDTO createShift(@RequestBody ShiftDTO shiftDTO) {
		log.info("Create shift: {}", shiftDTO.getName());
		validateShift(shiftDTO);
		return shiftService.upsertShift(shiftDTO);
	}

	@PutMapping("/shift")
	ShiftDTO updateShift(@RequestBody ShiftDTO shiftDTO) {
		log.info("Update shift: {}", shiftDTO.getId());
		return shiftService.upsertShift(shiftDTO);
	}

	@DeleteMapping("/shift/{id}")
	void deleteShift(@PathVariable Long id) {
		log.info("Delete shift: {}", id);
		shiftService.deleteShift(id);
	}
	
	private void validateShift(ShiftDTO shiftDTO) {
		if(!startTime.contains(shiftDTO.getStartTime()) || !endTime.contains(shiftDTO.getStartTime())) {
			throw new BadRequestException(String.format("Start time or end time is not correct: %s-%s", shiftDTO.getStartTime(), shiftDTO.getEndTime()));
		}
	}

	public ShiftController(ShiftService shiftService) {
		this.shiftService = shiftService;
	}

}
