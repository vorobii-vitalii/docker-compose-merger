package org.vitalii.vorobii.docker.compose.parser.deserializer;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.vitalii.vorobii.docker.compose.parser.domain.NetworkConfig;
import org.vitalii.vorobii.docker.compose.parser.domain.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import lombok.SneakyThrows;

class TestNetworksDeserializer {

	private final ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());

	@SneakyThrows
	@Test
	void testDeserializeJustNetworks() {
		String yaml =
				"networks:\n"
			  + "     - some-network\n"
			  + "     - other-network";

		Map<String, NetworkConfig> actualNetworks = objectMapper.readValue(yaml, Service.class).getNetworks();

		Map<String, NetworkConfig> expectedNetworks = new HashMap<>();
		expectedNetworks.put("some-network", null);
		expectedNetworks.put("other-network", null);

		Assertions.assertEquals(expectedNetworks, actualNetworks);
	}

	@SneakyThrows
	@Test
	void testDeserializeNetworksWithAdvancedOptions() {
		String yaml =
				"networks:\n"
			  + "      app_net:\n"
		  	  + "        ipv4_address: 172.16.238.10\n"
			  + "        ipv6_address: 2001:3984:3989::10\n"
			  + "        aliases:\n"
			  + "          - database";

		Map<String, NetworkConfig> actualNetworks = objectMapper.readValue(yaml, Service.class).getNetworks();

		Map<String, NetworkConfig> expectedNetworks =
				Collections.singletonMap("app_net",
						NetworkConfig.builder()
							.ipv4_address("172.16.238.10")
							.ipv6_address("2001:3984:3989::10")
							.aliases(Collections.singletonList("database"))
							.build());

		Assertions.assertEquals(expectedNetworks, actualNetworks);
	}

}
