## Docker-compose-parser

### Library that allows to parse docker-compose YAML files

### Usage example:

```java

String fileName = "docker-compose.yaml";

try (InputStream dockerComposeResource = Main.class.getClassLoader().getResourceAsStream(fileName);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

    if (dockerComposeResource == null) {
         throw new RuntimeException(fileName + " was not found");
    }
    int t = dockerComposeResource.read();
    
    while (t != -1) {
        outputStream.write(t);
        t = dockerComposeResource.read();
    }
    byte[] bytes = outputStream.toByteArray();
    
    DockerComposeParser dockerComposeParser = DockerComposeParser.getInstance();
    
    DockerCompose dockerCompose = dockerComposeParser.readDockerCompose(bytes);
    
    // Work with dockerCompose obj however you want
}
catch (IOException exception) {
    exception.printStackTrace();
}

```

