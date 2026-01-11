package classes;

import java.util.InputMismatchException;
import java.util.Scanner;

import excecoes.AnoInvalidoException;
import excecoes.AutorNaoPodeSerRemovidoException;
import excecoes.FraseInvalidaException;
import excecoes.LeitorNaoPodeSerRemovidoException;
import excecoes.LivroInvalidoException;
import excecoes.RegistroJaExistenteException;
import excecoes.QuantidadeLivrosDisponiveisInvalidaException;
import excecoes.QuantidadeTotalLivrosInvalidaException;
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
        System.out.print("ID do autor: ");
        try {
            autor = Biblioteca.buscarAutorPorId(scan.nextInt());
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            return;
        } finally {
            scan.close();
        }
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
        }finally{
            scan.close();
        }
    }
    public void removerLivro() {
        Livro livro;
        Scanner scan = new Scanner(System.in);
        int quantidade, id;

        System.out.println("REMOÇÃO DE LIVRO\n----------------");
        System.out.print("ID do livro: ");
        id = scan.nextInt();
        System.out.print("Quantidade a ser removida: ");
        quantidade = scan.nextInt();

        try{
            livro = Biblioteca.buscarLivroPorId(id);
            Biblioteca.removerLivro(livro, quantidade);
        }catch(InputMismatchException e){
            System.out.println("ID inválido. Por favor, insira um número inteiro.");
            System.out.println(e.getMessage());
            return;
        }catch(NullPointerException | QuantidadeLivrosDisponiveisInvalidaException e){
            System.out.println(e.getMessage());
            return;
        }finally {
            scan.close();
        }
    }
    public void registrarEmprestimo() {
        
    }
    public void registrarDevolucao() {
        
    }
    public void cadastrarAutor() {
        Autor autor; 
        Scanner scan = new Scanner(System.in);
        String nome, nacionalidade, dataNascimento;

        System.out.println("CADASTRO DE AUTOR\n----------------");
        System.out.print("Nome: ");
        scan.nextLine();
        nome = scan.nextLine();
        System.out.print("Nacionalidade: ");
        scan.nextLine();
        nacionalidade = scan.nextLine();
        System.out.print("Data de Nascimento: ");
        scan.nextLine();
        dataNascimento = scan.nextLine();

        try{
            autor = new Autor(nome, nacionalidade, dataNascimento);
            Biblioteca.adicionarAutor(autor);
        }catch(FraseInvalidaException e){
            System.out.println(e.getMessage());
            return;
        } finally {
            scan.close();
        }
        
    }
    public void cadastrarLivrosDoAutor(Autor autor) {
        Scanner scan = new Scanner(System.in);
        int id;
        Livro livro;

        System.out.print("CADASTRO DE LIVRO PARA AUTOR\n----------------");
        System.out.println("(Atenção: Cadastre os livros e autores primeiro!)\n");
        System.out.println("ID do livro: ");

        try{
            id = scan.nextInt();
            livro = Biblioteca.buscarLivroPorId(id);
            autor.adicionarLivro(livro);
        }catch(InputMismatchException e){
            System.out.println("ID inválido. Por favor, insira um número inteiro.");
            System.out.println(e.getMessage());
        }catch(LivroInvalidoException e){
            System.out.println(e.getMessage());
        }catch(NullPointerException e){
            System.out.println("Livro não encontrado.");
            System.out.println(e.getMessage());
        }finally{
            scan.close();
        }
    }
    public void removerAutor() {
        Autor autor;
        Scanner scan = new Scanner(System.in);
        int id;

        System.out.println("REMOÇÃO DE AUTOR\n----------------");
        System.out.print("ID do autor: ");
        id = scan.nextInt();

        try{
            autor = Biblioteca.buscarAutorPorId(id);
            Biblioteca.removerAutor(autor);
        }catch(InputMismatchException e){
            System.out.println("ID inválido. Por favor, insira um número inteiro.");
            System.out.println(e.getMessage());
            return;
        }catch(NullPointerException | AutorNaoPodeSerRemovidoException e){
            System.out.println(e.getMessage());
            return;
        }finally {
            scan.close();
        }
    }
    public void cadastrarLeitor() {
        Leitor leitor; 
        Scanner scan = new Scanner(System.in);
        String nome, usuario, senha, email, telefone;

        System.out.println("CADASTRO DE LEITOR\n----------------");
        System.out.print("Nome: ");
        scan.nextLine();
        nome = scan.nextLine();
        System.out.print("Email: ");
        scan.nextLine();
        email = scan.nextLine();
        System.out.print("Telefone: ");
        scan.nextLine();
        telefone = scan.nextLine();
        System.out.println("Usuário: ");
        scan.nextLine();
        usuario = scan.nextLine();
        System.out.println("Senha: ");
        scan.nextLine();
        senha = scan.nextLine();

        try{
            leitor = new Leitor(nome, email, telefone, usuario, senha);
            Biblioteca.adicionarLeitor(leitor);
        }catch(FraseInvalidaException | RegistroJaExistenteException e){
            System.out.println(e.getMessage());
            return;
        } finally {
            scan.close();
        }
    }
    public void removerLeitor() {
        Leitor leitor;
        Scanner scan = new Scanner(System.in);
        int id;

        System.out.println("REMOÇÃO DE LEITOR\n----------------");
        System.out.print("ID do leitor: ");
        id = scan.nextInt();

        try{
            leitor = Biblioteca.buscarLeitorPorId(id);
            Biblioteca.removerLeitor(leitor);
        }catch(InputMismatchException e){
            System.out.println("ID inválido. Por favor, insira um número inteiro.");
            System.out.println(e.getMessage());
            return;
        }catch(NullPointerException | LeitorNaoPodeSerRemovidoException e){
            System.out.println(e.getMessage());
            return;
        }finally {
            scan.close();
        }
    }
    @Override
    public void exibirDados() {
        System.out.println("ID: " + this.id);
        System.out.println("Nome: " + getNome());
        System.out.println("Email: " + getEmail());
        System.out.println("Telefone: " + getTelefone());
        System.out.println("Usuário: " + getUsuario());
    }
}
