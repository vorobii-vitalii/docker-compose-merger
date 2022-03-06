package org.vitalii.vorobii.docker.compose.parser.domain;

import java.util.List;
import java.util.Map;

import org.vitalii.vorobii.docker.compose.parser.deserializer.BuildDeserializer;
import org.vitalii.vorobii.docker.compose.parser.deserializer.ListRepresentedAsMapOrMapDeserializer;
import org.vitalii.vorobii.docker.compose.parser.deserializer.NetworksDeserializer;
import org.vitalii.vorobii.docker.compose.parser.deserializer.OneOrManyStringDeserializer;
import org.vitalii.vorobii.docker.compose.parser.deserializer.VolumesDeserializer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Service {
    @JsonDeserialize(using = BuildDeserializer.class)
    private Build build;
    private List<String> cap_add;
    private List<String> cap_drop;
    private String cgroup_parent;
    private String command;
    private List<Object> configs;
    private Map<String, String> credential_spec;
    private List<String> depends_on;
    private List<String> devices;
    @JsonDeserialize(using = OneOrManyStringDeserializer.class)
    private List<String> dns;
    @JsonDeserialize(using = OneOrManyStringDeserializer.class)
    private List<String> dns_search;
    private String entrypoint;
    @JsonDeserialize(using = OneOrManyStringDeserializer.class)
    private List<String> env_file;
    @JsonDeserialize(using = ListRepresentedAsMapOrMapDeserializer.class)
    private Map<String, String> environment;
    private List<String> expose;
    private List<String> external_links;
    private List<String> extra_hosts;
    private HealthCheck healthcheck;
    private String image;
    private Boolean init;
    private String isolation;
    @JsonDeserialize(using = ListRepresentedAsMapOrMapDeserializer.class)
    private Map<String, String> labels;
    private List<String> links;
    private Logging logging;
    private String network_mode;
    @JsonDeserialize(using = NetworksDeserializer.class)
    private Map<String, NetworkConfig> networks;
    private String pid;
    private List<Object> ports;
    private List<String> profiles;
    private String restart;
    private List<Object> secrets;
    private List<String> security_opt;
    private String stop_grace_period;
    private String stop_signal;
    @JsonDeserialize(using = ListRepresentedAsMapOrMapDeserializer.class)
    private Map<String, String> sysctls;
    @JsonDeserialize(using = OneOrManyStringDeserializer.class)
    private List<String> tmpfs;
    @JsonDeserialize(using = VolumesDeserializer.class)
    private List<Volume> volumes;
    private String user;
    private String working_dir;
    private String domainname;
    private String hostname;
    private String ipc;
    private String mac_address;
    private Boolean privileged;
    private Boolean read_only;
    private String shm_size;
    private Boolean stdin_open;
    private Boolean tty;
    private String container_name;
}
