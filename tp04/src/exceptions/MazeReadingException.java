package exceptions;

public class MazeReadingException extends Exception {
	
	static final long serialVersionUID = 30122017L;
	
	@SuppressWarnings("unused")
	private final String fileName;
	@SuppressWarnings("unused")
	private final int lineNumber;
	private final String message;

	public MazeReadingException(String fileName, int lineNumber, String message) {
		this.fileName = fileName;
		this.lineNumber = lineNumber;
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
}
