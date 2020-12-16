package src.exception;

public class OperationException extends Exception {

    private static final long serialVersionUID = 1L;

    public OperationException() {
        super("Não foi possível realizar esta operação.");
	}

	public OperationException(String message) {
        super(message);
	}
    
}
