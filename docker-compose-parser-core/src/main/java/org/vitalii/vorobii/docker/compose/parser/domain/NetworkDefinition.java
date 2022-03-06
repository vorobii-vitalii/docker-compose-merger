package org.vitalii.vorobii.docker.compose.parser.domain;

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
public class NetworkDefinition {
    private String driver;
    private Map<String, String> driver_opts;
    private String name;
    private Object external;
    private Boolean attachable;
    private Boolean enable_ipv6;
    private IPAM ipam;
    @JsonDeserialize(using = ListRepresentedAsMapOrMapDeserializer.class)
    private Map<String, String> labels;
    private Boolean internal;
}
