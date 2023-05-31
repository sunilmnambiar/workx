package com.sunil.workx.api.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sunil.workx.api.entity.WorkerShift;

public interface WorkerShiftRepository extends JpaRepository<WorkerShift, Long> {

	@Query("SELECT ws FROM WorkerShift ws WHERE ws.worker.id = ?1 and ws.date = ?2")
	List<WorkerShift> findByWorkerAndDate(long workerId, Date date);
	
	@Query("SELECT ws FROM WorkerShift ws WHERE ws.date = ?1")
	List<WorkerShift> findByDate(Date date);
	
	@Query("SELECT ws FROM WorkerShift ws WHERE ws.worker.id = ?1")
	List<WorkerShift> findByWorker(long workerId);

}
