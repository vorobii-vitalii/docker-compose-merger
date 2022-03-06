package org.vitalii.vorobii.docker.compose.parser.deserializer;

import java.io.IOException;

import org.vitalii.vorobii.docker.compose.parser.domain.Build;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class BuildDeserializer extends JsonDeserializer<Build> {
	@Override
	public Build deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
		JsonToken currentToken = jsonParser.getCurrentToken();

		if (currentToken == JsonToken.VALUE_STRING) {
			Build build = new Build();
			build.setContext(jsonParser.getText());
			return build;
		}
		JsonParser parser = deserializationContext.getParser();

		return parser.readValueAs(Build.class);
	}
}
