package com.sunil.workx.api.entity;

import java.sql.Time;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;


@Getter
@Jacksonized
@Builder
public class Shift {
	
	private Integer id;
	private String name;
	private Time startTime;
	private Time endTime;

}
