package org.vitalii.vorobii.docker.compose.parser.domain;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Deploy {
	private String endpoint_mode;
	private Map<String, String> labels;
	private String mode;
	private Placement placement;
	private Integer replicas;
	private RestartPolicy restart_policy;
	private RollbackConfig rollback_config;
	private UpdateConfig update_config;
}
