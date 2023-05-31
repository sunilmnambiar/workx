package com.sunil.workx.api.entity;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(uniqueConstraints = { @UniqueConstraint(name = "UniqueWorkerShift", columnNames = { "date", "worker_id", "shift_id" }) })
public class WorkerShift {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Temporal(TemporalType.DATE)
	private Date date;
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	private Worker worker;
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	private Shift shift;

}
