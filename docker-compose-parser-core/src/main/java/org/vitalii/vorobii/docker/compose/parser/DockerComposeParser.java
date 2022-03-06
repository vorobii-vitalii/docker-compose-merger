package org.vitalii.vorobii.docker.compose.parser;

import org.vitalii.vorobii.docker.compose.parser.domain.DockerCompose;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public interface DockerComposeParser {
    DockerCompose readDockerCompose(byte[] yamlContent);

    static DockerComposeParser getInstance() {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        return new DockerComposeParserImpl(objectMapper);
    }
}
