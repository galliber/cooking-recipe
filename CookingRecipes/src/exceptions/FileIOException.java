package exceptions;


public class FileIOException extends RuntimeException {

	public FileIOException() {
		super();
	}

	public FileIOException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileIOException(String message) {
		super(message);
	}

	public FileIOException(Throwable cause) {
		super(cause);
	}
	
}
