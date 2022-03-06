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
public class IPAM {
	private String driver;
	private List<Map<String, String>> config;
}
