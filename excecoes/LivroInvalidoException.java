package excecoes;

public class LivroInvalidoException extends Exception {
    public LivroInvalidoException(String message) {
        super(message);
    }
    public String getMessage() {
        return super.getMessage();
    }
}
