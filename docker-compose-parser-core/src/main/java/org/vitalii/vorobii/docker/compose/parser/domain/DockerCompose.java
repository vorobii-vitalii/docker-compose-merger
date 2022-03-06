package org.vitalii.vorobii.docker.compose.parser.domain;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public final class DockerCompose {
    private String version;
    private Map<String, Service> services;
    private Map<String, NetworkDefinition> networks;
    private Map<String, VolumeDefinition> volumes;
}
