package org.vitalii.vorobii.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Service {
    private String build;
    private String image;
    private String restart;
    private String container_name;
    private Map<String, String> environment;
    private List<String> ports;
    private Map<String, Map<String, String>> networks;
    private List<String> depends_on;
    private List<String> links;
    private String entrypoint;
    private String command;
}
