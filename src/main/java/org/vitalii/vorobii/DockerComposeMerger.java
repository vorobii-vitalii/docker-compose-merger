package org.vitalii.vorobii;

import org.vitalii.vorobii.dto.DockerCompose;
import org.vitalii.vorobii.impl.DockerComposeMergerImpl;

import java.util.List;

public interface DockerComposeMerger {
    DockerCompose mergeDockerComposes(List<DockerCompose> dockerComposes);

    static DockerComposeMerger getInstance() {
        return new DockerComposeMergerImpl();
    }
}
