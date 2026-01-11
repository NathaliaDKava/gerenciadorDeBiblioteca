package interfaces;

import classes.Autor;

public interface gerenciadorBiblioteca {
    void cadastrarLivro();
    void removerLivro();
    void registrarEmprestimo();
    void registrarDevolucao();
    void cadastrarAutor();
    void cadastrarLivrosDoAutor(Autor autor);
    void removerAutor();
    void cadastrarLeitor();
    void removerLeitor();
}
