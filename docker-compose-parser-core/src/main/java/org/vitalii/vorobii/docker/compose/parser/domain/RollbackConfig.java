package org.vitalii.vorobii.docker.compose.parser.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RollbackConfig {
	private Integer parallelism;
	private String delay;
	private String failure_action;
	private String monitor;
	private String max_failure_ratio;
	private String order;
}
