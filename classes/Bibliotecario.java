package classes;

import java.util.Scanner;

import excecoes.AnoInvalidoException;
import excecoes.FraseInvalidaException;
import excecoes.RegistroJaExistenteException;
import interfaces.gerenciadorBiblioteca;

public class Bibliotecario extends Usuario implements gerenciadorBiblioteca {
    private static int contadorIds = 0;

    public Bibliotecario(String nome, String email, String telefone, String usuario, String senha) throws FraseInvalidaException, RegistroJaExistenteException {
        super(nome, email, telefone, usuario, senha);
        this.id = ++contadorIds;
    }
    
    public void cadastrarLivro() {
        Livro livro;
        Scanner scan = new Scanner(System.in);
        String titulo, isbn, genero, editora;
        Autor autor;
        int anoPublicacao, quantidade;

        System.out.println("CADASTRO DE LIVRO\n----------------");
        System.out.print("Titulo: ");
        scan.nextLine();
        titulo = scan.nextLine();
        System.out.print("Autor: ");
        scan.nextLine();
        autor = Autor.buscarAutorPorNome(scan.nextLine());

        System.out.print("ISBN: ");
        scan.nextLine();
        isbn = scan.nextLine();
        System.out.print("Genero: ");
        scan.nextLine();
        genero = scan.nextLine();
        System.out.print("Editora: ");
        scan.nextLine();
        editora = scan.nextLine();
        System.out.print("Ano de Publicação: ");
        anoPublicacao = scan.nextInt();
        System.out.print("Quantidade: ");
        quantidade = scan.nextInt();

        try{
            livro = new Livro(titulo, autor, isbn, genero, editora, anoPublicacao, quantidade);
            Biblioteca.adicionarLivro(livro);
        }catch(FraseInvalidaException | RegistroJaExistenteException e){
            System.out.println(e.getMessage());
        }catch(AnoInvalidoException e){
            System.out.println(e.getMessage());
        }
        scan.close();
    }
    public void removerLivro() {
        
    }
    public void registrarEmprestimo() {
        
    }
    public void registrarDevolucao() {
        
    }
    public void cadastrarAutor() {
        
    }
    public void removerAutor() {
        
    }
    public void cadastrarLeitor() {
        
    }
    public void removerLeitor() {
        
    }
}
