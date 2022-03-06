package org.vitalii.vorobii.docker.compose.parser.deserializer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.vitalii.vorobii.docker.compose.parser.domain.NetworkConfig;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;
import com.fasterxml.jackson.dataformat.yaml.JacksonYAMLParseException;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

public class NetworksDeserializer extends JsonDeserializer<Map<String, NetworkConfig>> {
	@Override
	public Map<String, NetworkConfig> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
			throws IOException {
		JsonToken currentToken = jsonParser.getCurrentToken();

		TreeNode treeNode = jsonParser.readValueAsTree();

		Map<String, NetworkConfig> networkConfigMap = new HashMap<>();

		if (currentToken == JsonToken.START_ARRAY) {
			int N = treeNode.size();

			for (int i = 0; i < N; i++) {
				TreeNode node = treeNode.get(i);

				if (node.isValueNode()) {
					TextNode textNode = (TextNode) node;

					networkConfigMap.put(textNode.asText(), null);
				}
			}
			return networkConfigMap;
		}
		else if (currentToken == JsonToken.START_OBJECT) {
			YAMLMapper yamlMapper = new YAMLMapper();
			Iterator<String> iterator = treeNode.fieldNames();

			while (iterator.hasNext()) {
				String field = iterator.next();
				TreeNode node = treeNode.get(field);

				NetworkConfig networkConfig = node.traverse(yamlMapper).readValueAs(NetworkConfig.class);
				networkConfigMap.put(field, networkConfig);
			}
			return networkConfigMap;
		}
		throw new JacksonYAMLParseException(jsonParser, "Cannot parse networks", null);
	}
}
