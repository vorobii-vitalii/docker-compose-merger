package org.vitalii.vorobii.docker.compose.parser.deserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.vitalii.vorobii.docker.compose.parser.domain.Volume;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.fasterxml.jackson.dataformat.yaml.JacksonYAMLParseException;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

public class VolumesDeserializer extends JsonDeserializer<List<Volume>> {

	private static final String READ_ONLY = "ro";

	@Override
	public List<Volume> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
		TreeNode treeNode = jsonParser.readValueAsTree();

		YAMLMapper yamlMapper = new YAMLMapper();
		int M = treeNode.size();
		List<Volume> volumes = new ArrayList<>();

		for (int i = 0; i < M; i++) {
			TreeNode node = treeNode.get(i);

			if (node instanceof ObjectNode) {
				Volume volume = node.traverse(yamlMapper).readValueAs(Volume.class);
				volumes.add(volume);
			}
			else {
				String value = ((TextNode) node).asText();

				String[] components = value.split(":");

				int N = components.length;

				if (N == 0 || N > 3) {
					throw new JacksonYAMLParseException(jsonParser, "Cannot parse volumes", null);
				}
				if (N == 1) {
					volumes.add(Volume.builder().target(components[0]).build());
				} else if (N == 2) {
					volumes.add(Volume.builder().source(components[0]).target(components[1]).build());
				} else {
					volumes.add(Volume.builder()
							.source(components[0])
							.target(components[1])
							.read_only(READ_ONLY.equals(components[2]))
							.build());
				}
			}
		}
		return volumes;
	}
}
