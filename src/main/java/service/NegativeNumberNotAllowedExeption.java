package service;

@SuppressWarnings("serial")
public class NegativeNumberNotAllowedExeption extends Exception {
	public NegativeNumberNotAllowedExeption(String errorMessage) {
        super(errorMessage);
    }

}
