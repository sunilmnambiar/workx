package com.sunil.workx.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.sunil.workx.api.domain.WorkerDTO;
import com.sunil.workx.api.entity.Worker;
import com.sunil.workx.api.exception.BadRequestException;
import com.sunil.workx.api.exception.ResourceAlreadyExistsException;
import com.sunil.workx.api.repository.WorkerRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class WorkerService {

	private WorkerRepository workerRepository;

	public List<WorkerDTO> getAllWorkers() {
		return workerRepository.findAll().stream()
				.map(worker -> buildWorkerDTO(worker)).toList();
	}

	public WorkerDTO getWorker(Long id) {
		return buildWorkerDTO(workerRepository.findById(id).orElse(null));
	}

	public WorkerDTO upsertWorker(WorkerDTO workerDTO) {
		try {
			return buildWorkerDTO(workerRepository.save(buildWorker(workerDTO)));
		} catch (DataIntegrityViolationException e) {
			String errMsg = "Worker with name '" + workerDTO.getName() + "' already exists";
			log.error(errMsg, e);
			throw new ResourceAlreadyExistsException(errMsg);
		}
	}

	public void deleteWorker(Long id) {
		Optional<Worker> worker = workerRepository.findById(id);
		if (worker.isPresent()) {
			workerRepository.deleteById(id);
		} else {
			log.warn("Deletion failed, invalid worker id: " + id);
			throw new BadRequestException("Invalid worker id: " + id);
		}
	}
	
	
	private WorkerDTO buildWorkerDTO(Worker worker) {
		if(worker != null) {
			return WorkerDTO.builder()
					.id(worker.getId())
					.name(worker.getName())
					.build();
		} else {
			return null;
		}
	}
	
	private Worker buildWorker(WorkerDTO workerDTO) {
		if(workerDTO != null) {
			return Worker.builder()
					.id(workerDTO.getId())
					.name(workerDTO.getName())
					.build();
		} else {
			return null;
		}
	}

	public WorkerService(WorkerRepository workerRepository) {
		this.workerRepository = workerRepository;
	}

}
