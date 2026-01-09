package excecoes;

public class LivroNaoEncontradoException extends Exception {
    private int livroId;

    public LivroNaoEncontradoException(int livroId) {
        super("Livro com ID " + livroId + " nao encontrado entre os livros emprestados.");
        this.livroId = livroId;
    }
    public LivroNaoEncontradoException() {
        super("Livro nao encontrado entre os livros emprestados.");
    }
    
    public int getLivroId() {
        return livroId;
    }
}
