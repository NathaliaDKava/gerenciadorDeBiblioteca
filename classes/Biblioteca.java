package classes;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import excecoes.FraseInvalidaException;
import excecoes.RegistroJaExistenteException;

public class Biblioteca {
    private static String nome;
    private static String endereco;
    private static ArrayList<Bibliotecario> bibliotecarios;
    private static ArrayList<Leitor> leitores;
    private static ArrayList<Livro> livros;
    private static ArrayList<Autor> autores;
    
    static{
        bibliotecarios = new ArrayList<Bibliotecario>();
        leitores = new ArrayList<Leitor>();
        livros = new ArrayList<Livro>();
        autores = new ArrayList<Autor>();
    }

    public Biblioteca(String nome, String endereco) throws FraseInvalidaException {
        setNome(nome);
        setEndereco(endereco);
    }

    public static void setNome(String nome) throws FraseInvalidaException {
        if(Validadores.validarString(nome)){
            Biblioteca.nome = nome;
        }else{
            throw new FraseInvalidaException("Nome inválido. Deve conter apenas letras e espaços.");
        }
    }
    public static String getNome(){
        return nome;
    }
    public static void setEndereco(String endereco) {
        Biblioteca.endereco = endereco;
    }
    public static String getEndereco(){
        return endereco;
    }
    public static void cadastrarBibliotecario() {
        Bibliotecario bibliotecario;
        Scanner scan = new Scanner(System.in);
        String nome, email, telefone, usuario, senha;

        System.out.println("CADASTRO DE BIBLIOTECÁRIO\n-------------------------");
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
            bibliotecario = new Bibliotecario(nome, email, telefone, usuario, senha);
            adicionarBibliotecario(bibliotecario);
        }catch(FraseInvalidaException | RegistroJaExistenteException e){
            System.out.println(e.getMessage());
        }
        scan.close();
    }
    public static void adicionarBibliotecario(Bibliotecario bibliotecario) {
        if(!bibliotecarios.contains(bibliotecario) && bibliotecario != null){
            bibliotecarios.add(bibliotecario);
        }
    }
    public static void removerBibliotecario(Bibliotecario bibliotecario) {
        if(bibliotecarios.contains(bibliotecario)) {
            bibliotecarios.remove(bibliotecario);
        }
    }
    public static ArrayList<Bibliotecario> getBibliotecarios() {
        return bibliotecarios;
    }
    public static void adicionarLeitor(Leitor leitor) {
        if(!leitores.contains(leitor) && leitor != null) {
            leitores.add(leitor);
        }
    }
    public static void removerLeitor(Leitor leitor) {
        if(leitores.contains(leitor)) {
            leitores.remove(leitor);
        }
    }
    public static ArrayList<Leitor> getLeitores(){
        return leitores;
    }
    public static void adicionarLivro(Livro livro) {
        if(!livros.contains(livro) && livro != null) {
            livros.add(livro);
        }
    }
    public static void removerLivro(Livro livro) {
        if(livros.contains(livro)) {
            livros.remove(livro);
        }
    }
    public static ArrayList<Livro> getLivros(){
        return livros;
    }
    public static void adicionarAutor(Autor autor) {
        if(!autores.contains(autor) && autor != null) {
            autores.add(autor);
        }
    }
    public static void removerAutor(Autor autor) {
        if(autores.contains(autor)) {
            autores.remove(autor);
        }
    }
    public static ArrayList<Autor> getAutores(){
        return autores;
    }
}
