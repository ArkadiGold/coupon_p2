package gold.com.Exception;

public class OperationNotAllowedExeption extends Exception {
	
	public OperationNotAllowedExeption(String message) {
		super(message + " is not allowed Exception.");
	}
}
