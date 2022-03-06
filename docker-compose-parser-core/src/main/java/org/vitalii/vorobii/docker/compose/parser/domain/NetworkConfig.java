package org.vitalii.vorobii.docker.compose.parser.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NetworkConfig {
	private List<String> aliases;
	private String ipv6_address;
	private String ipv4_address;
}
