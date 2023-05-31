package com.sunil.workx.api.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Jacksonized
@Builder
public class WorkerShiftDTO {
	
	private Long id;
	
	@JsonSerialize(as = Date.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date date;
	
	private Long workerId;
	private Long shiftId;

}
