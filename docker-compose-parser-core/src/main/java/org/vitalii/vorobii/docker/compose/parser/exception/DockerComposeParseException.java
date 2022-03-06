package org.vitalii.vorobii.docker.compose.parser.exception;

public class DockerComposeParseException extends RuntimeException {
	public DockerComposeParseException(Exception e) {
		super(e);
	}
}
