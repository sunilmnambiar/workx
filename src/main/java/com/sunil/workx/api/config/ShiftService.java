package com.sunil.workx.api.config;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sunil.workx.api.entity.Shift;
import com.sunil.workx.api.repository.ShiftRepository;

@Service
public class ShiftService {
	
	private ShiftRepository shiftRepository;

	public List<Shift> getAllShifts() {
		return shiftRepository.findAll();
	}

	public Optional<Shift> getShift(Long id) {
		return shiftRepository.findById(id);
	}

	public Shift upsertShift(Shift shift) {
		return shiftRepository.save(shift);
	}

	public void deleteShift(Long id) {
		shiftRepository.deleteById(id);
	}
	
	public ShiftService(ShiftRepository shiftRepository) {
		this.shiftRepository = shiftRepository;
	}

}
