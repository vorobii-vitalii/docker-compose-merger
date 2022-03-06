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
public class Logging {
	private String driver;
	private Map<String, String> options;
}
