package org.vitalii.vorobii.docker.compose.parser.domain;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Volume {
	private String type;
	private String source;
	private String target;
	private Boolean read_only;
	private Map<String, String> bind;
	private Map<String, String> volume;
	private Map<String, String> tmpfs;
}
