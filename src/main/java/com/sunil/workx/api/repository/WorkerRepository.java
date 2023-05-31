package com.sunil.workx.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunil.workx.api.entity.Worker;

public interface WorkerRepository extends JpaRepository<Worker, Long> {
	
	

}
