package com.sunil.workx.api.service;

import java.util.Date;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.sunil.workx.api.domain.WorkerShiftDTO;
import com.sunil.workx.api.entity.WorkerShift;
import com.sunil.workx.api.exception.BadRequestException;
import com.sunil.workx.api.exception.ResourceAlreadyExistsException;
import com.sunil.workx.api.exception.ResourceNotFoundException;
import com.sunil.workx.api.repository.ShiftRepository;
import com.sunil.workx.api.repository.WorkerRepository;
import com.sunil.workx.api.repository.WorkerShiftRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class WorkerShiftService {

	private WorkerShiftRepository workerShiftRepository;
	private WorkerRepository workerRepository;
	private ShiftRepository shiftRepository;

	public List<WorkerShiftDTO> getAllWorkerShifts() {
		return workerShiftRepository.findAll().stream().map(workerShift -> buildWorkerShiftDTO(workerShift)).toList();
	}

	public List<WorkerShiftDTO> getWorkerShifts(Long workerId, Date date) {
		if (workerId == null && date == null) {
			return getAllWorkerShifts();
		} else if (workerId == null) {
			return workerShiftRepository.findByDate(date).stream().map(workerShift -> buildWorkerShiftDTO(workerShift))
					.toList();
		} else if (date == null) {
			return workerShiftRepository.findByWorker(workerId).stream()
					.map(workerShift -> buildWorkerShiftDTO(workerShift)).toList();
		} else {
			return workerShiftRepository.findByWorkerAndDate(workerId, date).stream()
					.map(workerShift -> buildWorkerShiftDTO(workerShift)).toList();
		}
	}

	public WorkerShiftDTO getWorkerShift(Long id) {
		WorkerShift workerShift = workerShiftRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("WorkerShift with id: " + id));
		return buildWorkerShiftDTO(workerShift);
	}

	public WorkerShiftDTO upsertWorkerShift(WorkerShiftDTO workerShiftDTO) {
		try {
			WorkerShift workerShift = buildWorkerShift(workerShiftDTO);
			return buildWorkerShiftDTO(workerShiftRepository.save(workerShift));
		} catch (DataIntegrityViolationException e) {
			String errMsg = "Invalid worker '" + workerShiftDTO.getWorkerId() + "' and/or shift '"
					+ workerShiftDTO.getShiftId() + "'";
			log.error(errMsg, e);
			throw new ResourceAlreadyExistsException(errMsg);
		}
	}

	public void deleteWorkerShift(Long id) {
		workerShiftRepository.findById(id)
				.orElseThrow(() -> new BadRequestException("WorkerShift with id: " + id + " not found"));
		workerShiftRepository.deleteById(id);
	}

	private WorkerShiftDTO buildWorkerShiftDTO(WorkerShift workerShift) {
		if (workerShift != null) {
			return WorkerShiftDTO.builder().id(workerShift.getId()).date(workerShift.getDate())
					.workerId(workerShift.getWorker().getId()).shiftId(workerShift.getShift().getId()).build();
		} else {
			return null;
		}
	}

	private WorkerShift buildWorkerShift(WorkerShiftDTO workerShiftDTO) {
		if (workerShiftDTO != null) {
			return WorkerShift
					.builder().id(workerShiftDTO.getId()).date(workerShiftDTO.getDate()).worker(
							workerRepository.findById(workerShiftDTO.getWorkerId())
									.orElseThrow(() -> new BadRequestException(
											"Worker with id: " + workerShiftDTO.getWorkerId() + " not found")))
					.shift(shiftRepository.findById(workerShiftDTO.getShiftId())
							.orElseThrow(() -> new BadRequestException(
									"Shift with id: " + workerShiftDTO.getShiftId() + " not found")))
					.build();
		} else {
			return null;
		}
	}

	public WorkerShiftService(WorkerShiftRepository workerShiftRepository, WorkerRepository workerRepository,
			ShiftRepository shiftRepository) {
		this.workerShiftRepository = workerShiftRepository;
		this.workerRepository = workerRepository;
		this.shiftRepository = shiftRepository;
	}

}
