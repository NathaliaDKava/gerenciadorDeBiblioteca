package classes;

import java.util.ArrayList;

import excecoes.FraseInvalidaException;
import excecoes.RegistroJaExistenteException;
import interfaces.gerenciadorBiblioteca;

public class Bibliotecario extends Usuario implements gerenciadorBiblioteca {
    private static int contadorIds = 0;

    public Bibliotecario(String nome, String email, String telefone, String usuario, String senha, ArrayList<Bibliotecario> bibliotecarios, ArrayList<Leitor> leitores) throws FraseInvalidaException, RegistroJaExistenteException {
        super(nome, email, telefone, usuario, senha, bibliotecarios, leitores);
        this.id = ++contadorIds;
    }
    
    public void cadastrarLivro(Livro livro) {
        System.out.println("CADASTRO DE LIVRO\n----------------");
        System.out.println("");
    }
    public void removerLivro(Livro livro) {
        
    }
    public void registrarEmprestimo(Emprestimo emprestimo) {
        
    }
    public void registrarDevolucao(Emprestimo emprestimo) {
        
    }
    public void cadastrarAutor(Autor autor) {
        
    }
    public void removerAutor(Autor autor) {
        
    }
    public void cadastrarLeitor(Leitor leitor) {
        
    }
    public void removerLeitor(Leitor leitor) {
        
    }
}
