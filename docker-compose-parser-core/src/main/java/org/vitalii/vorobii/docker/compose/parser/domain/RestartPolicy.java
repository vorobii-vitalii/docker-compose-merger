package org.vitalii.vorobii.docker.compose.parser.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestartPolicy {
	private String condition;
	private String delay;
	private Integer max_attempts;
	private String window;
}
