package org.vitalii.vorobii.docker.compose.parser.deserializer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.vitalii.vorobii.docker.compose.parser.domain.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import lombok.SneakyThrows;

class TestOneOrManyStringDeserializer {

	private final ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());

	@SneakyThrows
	@Test
	void testSerializeSingle() {
		String yamlContent = "dns: 8.8.8.8\n";

		List<String> dns = objectMapper.readValue(yamlContent, Service.class).getDns();

		List<String> expectedDns = Collections.singletonList("8.8.8.8");

		Assertions.assertEquals(expectedDns, dns);
	}

	@SneakyThrows
	@Test
	void testSerializeMany() {
		String yamlContent =
				"dns:\n"
			  + "  - 8.8.8.8\n"
		  	  + "  - 9.9.9.9\n";

		List<String> dns = objectMapper.readValue(yamlContent, Service.class).getDns();

		List<String> expectedDns = new ArrayList<>();
		expectedDns.add("8.8.8.8");
		expectedDns.add("9.9.9.9");

		Assertions.assertEquals(expectedDns, dns);
	}

}
