package src.exception;

public class AuthenticationException extends Exception {

    private static final long serialVersionUID = 1L;

	public AuthenticationException() {
        super("Não foi possível entrar no sistema. Verifique os dados.");
	}

	public AuthenticationException(String message) {
        super(message);
	}
    
}
