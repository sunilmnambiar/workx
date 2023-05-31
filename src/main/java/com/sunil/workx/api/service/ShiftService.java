package com.sunil.workx.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sunil.workx.api.domain.ShiftDTO;
import com.sunil.workx.api.entity.Shift;
import com.sunil.workx.api.exception.BadRequestException;
import com.sunil.workx.api.exception.ResourceAlreadyExistsException;
import com.sunil.workx.api.repository.ShiftRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ShiftService {

	private ShiftRepository shiftRepository;

	public List<ShiftDTO> getAllShifts() {
		return shiftRepository.findAll(Sort.by(Sort.Direction.ASC, "startTime")).stream()
				.map(shift -> buildShiftDTO(shift)).toList();
	}

	public ShiftDTO getShift(Long id) {
		return buildShiftDTO(shiftRepository.findById(id).orElse(null));
	}

	public ShiftDTO upsertShift(ShiftDTO shiftDTO) {
		try {
			return buildShiftDTO(shiftRepository.save(buildShift(shiftDTO)));
		} catch (DataIntegrityViolationException e) {
			String errMsg = "Shift with name '" + shiftDTO.getName() + "' already exists";
			log.error(errMsg, e);
			throw new ResourceAlreadyExistsException(errMsg);
		}
	}

	public void deleteShift(Long id) {
		Optional<Shift> shift = shiftRepository.findById(id);
		if (shift.isPresent()) {
			shiftRepository.deleteById(id);
		} else {
			log.warn("Deletion failed, invalid shift id: " + id);
			throw new BadRequestException("Invalid shift id: " + id);
		}
	}
	
	private ShiftDTO buildShiftDTO(Shift shift) {
		if(shift != null) {
			return ShiftDTO.builder()
					.id(shift.getId())
					.name(shift.getName())
					.startTime(shift.getStartTime())
					.endTime(shift.getEndTime())
					.build();
		} else {
			return null;
		}
	}
	
	private Shift buildShift(ShiftDTO shiftDTO) {
		if(shiftDTO != null) {
			return Shift.builder()
					.id(shiftDTO.getId())
					.name(shiftDTO.getName())
					.startTime(shiftDTO.getStartTime())
					.endTime(shiftDTO.getEndTime())
					.build();
		} else {
			return null;
		}
	}

	public ShiftService(ShiftRepository shiftRepository) {
		this.shiftRepository = shiftRepository;
	}

}
