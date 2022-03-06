package org.vitalii.vorobii.docker.compose.parser;

import java.io.IOException;

import org.vitalii.vorobii.docker.compose.parser.domain.DockerCompose;
import org.vitalii.vorobii.docker.compose.parser.exception.DockerComposeParseException;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DockerComposeParserImpl implements DockerComposeParser {
    private final ObjectMapper objectMapper;

    @Override
    public DockerCompose readDockerCompose(byte[] yamlContent) {
        try {
            return objectMapper.readValue(yamlContent, DockerCompose.class);
        }
        catch (IOException e) {
            throw new DockerComposeParseException(e);
        }
    }
}
