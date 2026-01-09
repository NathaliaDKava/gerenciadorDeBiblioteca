package excecoes;

public class LimiteLivrosEmprestadosAtingidoException extends Exception {

    public LimiteLivrosEmprestadosAtingidoException() {
        super("Limite de livros emprestados atingido.");
    }
    public String getMessage() {
        return "Limite de livros emprestados atingido.";
    }
}
