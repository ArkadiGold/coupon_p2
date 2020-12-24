package gold.com.Exception;

public class CanNotChangeExeption extends Exception {

	public CanNotChangeExeption(String message) {
		super(message + " cannot be changed Exception.");
	}
}
