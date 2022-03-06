package org.vitalii.vorobii.docker.compose.parser.domain;

import java.util.List;
import java.util.Map;

import org.vitalii.vorobii.docker.compose.parser.deserializer.ListRepresentedAsMapOrMapDeserializer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Build {
	private String context;
	private String dockerfile;
	@JsonDeserialize(using = ListRepresentedAsMapOrMapDeserializer.class)
	private Map<String, String> args;
	private List<String> cache_from;
	@JsonDeserialize(using = ListRepresentedAsMapOrMapDeserializer.class)
	private Map<String, String> labels;
	private String network;
	private String shm_size;
	private String target;
}
