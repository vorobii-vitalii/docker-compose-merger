package org.vitalii.vorobii.docker.compose.parser.deserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;

public class OneOrManyStringDeserializer extends JsonDeserializer<List<String>> {
	@Override
	public List<String> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
		if (jsonParser.getCurrentToken() == JsonToken.VALUE_STRING) {
			List<String> singletonMutableList = new ArrayList<>();
			singletonMutableList.add(jsonParser.getText());
			return singletonMutableList;
		}
		TreeNode treeNode = jsonParser.readValueAsTree();

		int N = treeNode.size();

		List<String> res = new LinkedList<>();

		for (int i = 0; i < N; i++) {
			TextNode textNode = (TextNode) treeNode.get(i);

			res.add(textNode.asText());
		}
		return res;
	}
}
