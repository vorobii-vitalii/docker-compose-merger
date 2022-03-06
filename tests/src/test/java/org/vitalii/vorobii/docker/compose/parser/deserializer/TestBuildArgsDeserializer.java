package org.vitalii.vorobii.docker.compose.parser.deserializer;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

class TestBuildArgsDeserializer {

	private final ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());

	private final Map<String, String> expectedArgs = new HashMap<>();

	@BeforeEach
	void init() {
		expectedArgs.put("buildno", "1");
		expectedArgs.put("gitcommithash", "cdc3b19");
	}

	@SneakyThrows
	@Test
	void testBuildArgsRepresentedAsArray() {

		String buildArgsAsArray =
				"args:\n"
			+   "    - buildno=1\n"
			  + "    - gitcommithash=cdc3b19";

		BuildArgsObj buildArgs = objectMapper.readValue(buildArgsAsArray, BuildArgsObj.class);

		assertEquals(new BuildArgsObj(expectedArgs), buildArgs);
	}

	@SneakyThrows
	@Test
	void testBuildArgsRepresentedAsObject() {

		String buildArgsAsArray =
				"args:\n"
			  + "    buildno: 1\n"
			  + "    gitcommithash: cdc3b19";

		BuildArgsObj buildArgs = objectMapper.readValue(buildArgsAsArray, BuildArgsObj.class);

		assertEquals(new BuildArgsObj(expectedArgs), buildArgs);
	}

	@SneakyThrows
	@Test
	void testBuildArgsWhenValuesAreNotSpecified() {
		String buildArgsAsArray =
				"args:\n"
			  + "  - buildno\n"
			  + "  - gitcommithash\n";

		BuildArgsObj buildArgs = objectMapper.readValue(buildArgsAsArray, BuildArgsObj.class);


		assertTrue(buildArgs.getArgs().containsKey("buildno"));
		assertTrue(buildArgs.getArgs().containsKey("gitcommithash"));

		assertNull(buildArgs.getArgs().get("buildno"));
		assertNull(buildArgs.getArgs().get("gitcommithash"));
	}

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	private static class BuildArgsObj {
		@JsonDeserialize(using = ListRepresentedAsMapOrMapDeserializer.class)
		Map<String, String> args;
	}

}