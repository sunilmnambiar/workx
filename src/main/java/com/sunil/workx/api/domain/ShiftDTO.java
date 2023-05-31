package com.sunil.workx.api.domain;

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
public class ShiftDTO {
	
	private Long id;
	private String name;
	private String startTime;
	private String endTime;

}
