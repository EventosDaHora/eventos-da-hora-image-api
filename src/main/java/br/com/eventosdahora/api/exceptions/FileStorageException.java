package br.com.eventosdahora.api.exceptions;

public class FileStorageException extends RuntimeException {

	private static final long serialVersionUID = -4117776122201193796L;

	public FileStorageException(String message) {
        super(message);
    }

    public FileStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
