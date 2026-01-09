package interfaces;
import classes.Livro;
import classes.Emprestimo;
import classes.Autor;
import classes.Leitor;

public interface gerenciadorBiblioteca {
    void cadastrarLivro(Livro livro);
    void removerLivro(Livro livro);
    void registrarEmprestimo(Emprestimo emprestimo);
    void registrarDevolucao(Emprestimo emprestimo);
    void cadastrarAutor(Autor autor);
    void removerAutor(Autor autor);
    void cadastrarLeitor(Leitor leitor);
    void removerLeitor(Leitor leitor);
}
