package org.vitalii.vorobii.docker.compose.parser.deserializer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.NumericNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.fasterxml.jackson.dataformat.yaml.JacksonYAMLParseException;

public class ListRepresentedAsMapOrMapDeserializer extends JsonDeserializer<Map<String, String>> {

	@Override
	public Map<String, String> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
		JsonToken currentToken = jsonParser.getCurrentToken();

		JsonParser parser = deserializationContext.getParser();
		TreeNode treeNode = parser.readValueAsTree();

		Map<String, String> map = new HashMap<>();

		if (currentToken == JsonToken.START_ARRAY) {
			int N = treeNode.size();

			for (int i = 0; i < N; i++) {
				TreeNode childNode = treeNode.get(i);

				if (childNode.isValueNode()) {
					String str = ((TextNode) childNode).asText();

					if (str == null) {
						throw new JacksonYAMLParseException(parser, "Wrong build args", null);
					}
					if (!str.contains("=")) {
						map.put(str, null);
					} else {
						String[] components = str.split("=");
						map.put(components[0], components[1]);
					}
				}
			}
			return map;
		}

		treeNode.fieldNames().forEachRemaining(field -> {
			TreeNode node = treeNode.get(field);

			if (node instanceof NumericNode) {
				map.put(field, ((NumericNode) node).asText());
			} else {
				map.put(field, ((TextNode) node).asText());
			}
		});
		return map;
	}

}
