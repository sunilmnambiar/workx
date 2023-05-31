package com.sunil.workx.api.controller;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sunil.workx.api.domain.WorkerShiftDTO;
import com.sunil.workx.api.service.WorkerShiftService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class WorkerShiftController {

	private WorkerShiftService workerShiftService;

	@GetMapping("/workerShift")
	List<WorkerShiftDTO> getWorkerShifts(@RequestParam(value = "workerId", required = false) Long workerId, 
			@RequestParam(value = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
		log.info("Get workerShift: workerId={}, date={}", workerId, date);
		return workerShiftService.getWorkerShifts(workerId, date);
	}

	@GetMapping("/workerShift/{id}")
	WorkerShiftDTO getWorkerShift(@PathVariable Long id) {
		log.info("Get workerShift: {}", id);
		return workerShiftService.getWorkerShift(id);
	}
	
	@PostMapping("/workerShift")
	WorkerShiftDTO createWorkerShift(@RequestBody WorkerShiftDTO workerShift) {
		log.info("Create workerShift for worker: {}", workerShift.getWorkerId());
		return workerShiftService.upsertWorkerShift(workerShift);
	}

	@PutMapping("/workerShift")
	WorkerShiftDTO updateWorkerShift(@RequestBody WorkerShiftDTO workerShift) {
		log.info("Update workerShift: {}", workerShift.getId());
		return workerShiftService.upsertWorkerShift(workerShift);
	}

	@DeleteMapping("/workerShift/{id}")
	void deleteWorkerShift(@PathVariable Long id) {
		log.info("Delete workerShift: {}", id);
		workerShiftService.deleteWorkerShift(id);
	}

	public WorkerShiftController(WorkerShiftService workerShiftService) {
		this.workerShiftService = workerShiftService;
	}

}
