package org.vitalii.vorobii.impl;

import org.vitalii.vorobii.DockerComposeMerger;
import org.vitalii.vorobii.MergeException;
import org.vitalii.vorobii.dto.DockerCompose;
import org.vitalii.vorobii.dto.Pair;
import org.vitalii.vorobii.dto.Service;

import javax.xml.crypto.MarshalException;
import java.util.*;
import java.util.stream.Collectors;

public class DockerComposeMergerImpl implements DockerComposeMerger {

    @Override
    public DockerCompose mergeDockerComposes(List<DockerCompose> dockerComposes) {
        Map<String, Set<Service>> serviceIdToServices = dockerComposes.stream()
                .map(DockerCompose::getServices)
                .flatMap(v -> v.entrySet().stream())
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.mapping(Map.Entry::getValue, Collectors.toSet())));

        Map<String, Service> mergedServices = new HashMap<>();

//        private String restart;
//        private String container_name;
//        private Map<String, String> environment;
//        private List<String> ports;
//        private Map<String, Map<String, String>> networks;
//        private List<String> depends_on;
//        private List<String> links;
//        private String entrypoint;
//        private String command;

        serviceIdToServices.forEach((serviceId, services) -> {
            if (services.size() == 1) {
                mergedServices.put(serviceId, services.stream().findAny().get());
                return;
            }
            Service service = new Service();

            Set<String> builds = services.stream().map(Service::getBuild).collect(Collectors.toSet());

            if (builds.size() == 1) {
                service.setBuild(builds.stream().findAny().get());
            }
            else if (builds.size() > 1) {
                throw new MergeException(
                        "Cannot configure " + serviceId + " because more than 1 unique build was provided " + builds);
            }

            Map<String, Set<String>> imageNameByVersions = services.stream()
                    .map(Service::getImage)
                    .filter(Objects::nonNull)
                    .collect(Collectors.groupingBy(
                            image -> {
                                if (image.contains(":")) {
                                    return image.split(":")[0];
                                }
                                return image;
                            },
                            Collectors.mapping(image -> {
                                if (image.contains(":")) {
                                    return image.split(":")[1];
                                }
                                return "latest";
                            }, Collectors.toSet())));

            if (imageNameByVersions.size() == 1) {
                Map.Entry<String, Set<String>> imageAndVersions =
                        imageNameByVersions.entrySet().stream().findAny().get();

                if (imageAndVersions.getValue().size() > 1) {
                    throw new MergeException(
                            "Cannot configure " + serviceId
                                    + " because more than 1 image version is provided " + imageAndVersions.getKey());
                } else if (imageAndVersions.getValue().size() == 1) {
                    String version = imageAndVersions.getValue().stream().findAny().get();
                    service.setImage(imageAndVersions.getKey() + ":" + version);
                }
            }
            else if (imageNameByVersions.size() > 1) {
                throw new MergeException(
                        "Cannot configure " + serviceId + " because not unique image was set " + imageNameByVersions);
            }



            mergedServices.put(serviceId, service);
        });

        return null;
    }

}
