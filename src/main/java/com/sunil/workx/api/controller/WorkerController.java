package com.sunil.workx.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sunil.workx.api.domain.WorkerDTO;
import com.sunil.workx.api.exception.ResourceNotFoundException;
import com.sunil.workx.api.service.WorkerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class WorkerController {

	private WorkerService workerService;

	@GetMapping("/worker")
	List<WorkerDTO> getAllWorkers() {
		log.info("Get all workers");
		return workerService.getAllWorkers();
	}

	@GetMapping("/worker/{id}")
	WorkerDTO getWorker(@PathVariable Long id) {
		log.info("Get worker: {}", id);
		WorkerDTO worker = workerService.getWorker(id);
		if(worker == null) {
			throw new ResourceNotFoundException("Worker with id: " + id);
		}else {
			return worker;
		}
	}

	@PostMapping("/worker")
	WorkerDTO createWorker(@RequestBody WorkerDTO worker) {
		log.info("Create worker: {}", worker.getName());
		return workerService.upsertWorker(worker);
	}

	@PutMapping("/worker")
	WorkerDTO updateWorker(@RequestBody WorkerDTO worker) {
		log.info("Update worker: {}", worker.getId());
		return workerService.upsertWorker(worker);
	}

	@DeleteMapping("/worker/{id}")
	void deleteWorker(@PathVariable Long id) {
		log.info("Delete worker: {}", id);
		workerService.deleteWorker(id);
	}

	public WorkerController(WorkerService workerService) {
		this.workerService = workerService;
	}

}
