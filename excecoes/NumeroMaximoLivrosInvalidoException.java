package excecoes;

public class NumeroMaximoLivrosInvalidoException extends Exception {
    public NumeroMaximoLivrosInvalidoException(){
        super("O número máximo de livros emprestados deve ser positivo.");
    }
    public String getMessage(){
        return super.getMessage();
    }
}
