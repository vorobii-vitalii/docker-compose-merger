package org.vitalii.vorobii.docker.compose.parser.deserializer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.vitalii.vorobii.docker.compose.parser.domain.Service;
import org.vitalii.vorobii.docker.compose.parser.domain.Volume;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.google.common.collect.Lists;

import lombok.SneakyThrows;

class TestVolumesDeserializer {

	private final ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());

	@SneakyThrows
	@Test
	void testDeserializeVolumesShortForm() {
		String yamlContent = "volumes:\n"
				+ "  # Just specify a path and let the Engine create a volume\n"
				+ "  - /var/lib/mysql\n"
				+ "\n"
				+ "  # Specify an absolute path mapping\n"
				+ "  - /opt/data:/var/lib/mysql\n"
				+ "\n"
				+ "  # Path on the host, relative to the Compose file\n"
				+ "  - ./cache:/tmp/cache\n"
				+ "\n"
				+ "  # User-relative path\n"
				+ "  - ~/configs:/etc/configs/:ro\n"
				+ "\n"
				+ "  # Named volume\n"
				+ "  - datavolume:/var/lib/mysql\n";

		List<Volume> volumes = objectMapper.readValue(yamlContent, Service.class).getVolumes();

		List<Volume> expectedVolumes = Lists.newArrayList(
				Volume.builder().target("/var/lib/mysql").build(),
				Volume.builder().source("/opt/data").target("/var/lib/mysql").build(),
				Volume.builder().source("./cache").target("/tmp/cache").build(),
				Volume.builder().source("~/configs").target("/etc/configs/").read_only(true).build(),
				Volume.builder().source("datavolume").target("/var/lib/mysql").build());

		assertEquals(expectedVolumes, volumes);
	}

	@SneakyThrows
	@Test
	void testDeserializeVolumesLongForm() {
		String yamlContent = "volumes:\n"
				+ "      - type: volume\n"
				+ "        source: mydata\n"
				+ "        target: /data\n"
				+ "        volume:\n"
				+ "          nocopy: true\n"
				+ "      - type: bind\n"
				+ "        source: ./static\n"
				+ "        target: /opt/app/static";

		List<Volume> volumes = objectMapper.readValue(yamlContent, Service.class).getVolumes();

		List<Volume> expectedVolumes = Lists.newArrayList(
				Volume.builder().type("volume").source("mydata").target("/data").volume(Collections.singletonMap("nocopy", "true")).build(),
				Volume.builder().type("bind").source("./static").target("/opt/app/static").build());

		assertEquals(expectedVolumes, volumes);

	}

}
