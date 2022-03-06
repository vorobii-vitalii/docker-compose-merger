package org.vitalii.vorobii.docker.compose.parser.domain;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Placement {
	private List<String> constraints;
	private Map<String, String> preferences;
	private Integer max_replicas_per_node;
}
