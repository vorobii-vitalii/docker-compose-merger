package org.vitalii.vorobii.docker.compose.parser.impl;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.vitalii.vorobii.docker.compose.parser.DockerComposeParser;
import org.vitalii.vorobii.docker.compose.parser.domain.Build;
import org.vitalii.vorobii.docker.compose.parser.domain.DockerCompose;
import org.vitalii.vorobii.docker.compose.parser.domain.NetworkConfig;
import org.vitalii.vorobii.docker.compose.parser.domain.NetworkDefinition;
import org.vitalii.vorobii.docker.compose.parser.domain.Service;

class TestDockerComposeParserImpl {

	@Test
	void init() {
		DockerComposeParser dockerComposeParser = DockerComposeParser.getInstance();

		String yamlContent =
				"version: \"3.9\"\n"
						+ "\n"
						+ "services:\n"
						+ "  proxy:\n"
						+ "    build: ./proxy\n"
						+ "    networks:\n"
						+ "      - outside\n"
						+ "      - default\n"
						+ "  app:\n"
						+ "    build: ./app\n"
						+ "    networks:\n"
						+ "      - default\n"
						+ "\n"
						+ "networks:\n"
						+ "  outside:\n"
						+ "    external: true\n";

		DockerCompose dockerCompose = dockerComposeParser.readDockerCompose(yamlContent.getBytes(StandardCharsets.UTF_8));

		Map<String, NetworkConfig> proxyNetworkConfig = new HashMap<>();

		proxyNetworkConfig.put("outside", null);
		proxyNetworkConfig.put("default", null);

		Map<String, NetworkConfig> appNetworkConfig = new HashMap<>();

		appNetworkConfig.put("default", null);

		DockerCompose expectedDockerCompose = DockerCompose.builder()
				.version("3.9")
				.services(Map.of(
						"proxy",
						Service.builder()
								.build(Build.builder().context("./proxy").build())
								.networks(proxyNetworkConfig)
								.build(),
						"app",
						Service.builder()
								.build(Build.builder().context("./app").build())
								.networks(appNetworkConfig)
								.build()))
				.networks(Map.of("outside", NetworkDefinition.builder().external(true).build()))
				.build();

		Assertions.assertEquals(expectedDockerCompose, dockerCompose);
	}

}