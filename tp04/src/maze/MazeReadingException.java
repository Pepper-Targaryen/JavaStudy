package maze;

public class MazeReadingException extends Exception {
	private final String fileName;
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
