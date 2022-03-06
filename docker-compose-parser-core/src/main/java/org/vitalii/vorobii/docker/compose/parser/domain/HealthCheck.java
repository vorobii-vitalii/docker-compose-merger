package org.vitalii.vorobii.docker.compose.parser.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HealthCheck {
	private String interval;
	private String timeout;
	private String start_period;
	private Integer retries;
	private String test;
	private Boolean disable;
}
