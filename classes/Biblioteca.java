package classes;

import java.util.ArrayList;
import java.util.Scanner;

import excecoes.AutorNaoPodeSerRemovidoException;
import excecoes.FraseInvalidaException;
import excecoes.LeitorNaoPodeSerRemovidoException;
import excecoes.QuantidadeLivrosDisponiveisInvalidaException;
import excecoes.QuantidadeTotalLivrosInvalidaException;
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
    public static Bibliotecario buscarBibliotecarioPorId(int id) throws NullPointerException {
        for(Bibliotecario bibliotecario : Biblioteca.getBibliotecarios()) {
            if(bibliotecario.getId() == id) {
                return bibliotecario;
            }
        }
        throw new NullPointerException("Bibliotecário não encontrado.");
    }
    public static ArrayList<Bibliotecario> getBibliotecarios() {
        return bibliotecarios;
    }
    public static void adicionarLeitor(Leitor leitor) {
        if(!leitores.contains(leitor) && leitor != null) {
            leitores.add(leitor);
        }
    }
    public static void removerLeitor(Leitor leitor) throws LeitorNaoPodeSerRemovidoException {
        if(leitores.contains(leitor) && leitor.getLivrosEmprestados() != null && leitor.getTotalMultas() == 0.0) {
            leitores.remove(leitor);
        }else{
            throw new LeitorNaoPodeSerRemovidoException("O leitor em questão não está cadastrado, ou ainda possui livros emprestados, ou possui multas com pagamento pendente.");
        }
    }
    public static Leitor buscarLeitorPorId(int id) throws NullPointerException {
        for(Leitor leitor : Biblioteca.getLeitores()) {
            if(leitor.getId() == id) {
                return leitor;
            }
        }
        throw new NullPointerException("Leitor não encontrado.");
    }
    public static ArrayList<Leitor> getLeitores(){
        return leitores;
    }
    public static void adicionarLivro(Livro livro) {
        if(!livros.contains(livro) && livro != null) {
            livros.add(livro);
        }
    }
    public static void removerLivro(Livro livro, int quantidade) throws QuantidadeLivrosDisponiveisInvalidaException{
        if(livros.contains(livro)) {
            if(quantidade <= livro.getQuantidadeDisponivel() && quantidade > 0){
                if(livro.getQuantidadeDisponivel() == quantidade && livro.getQuantidadeTotal() == quantidade){
                    for(Autor autor : Biblioteca.getAutores()){
                        if(autor.getLivros().contains(livro)){
                            autor.getLivros().remove(livro);
                        }
                    }
                    livros.remove(livro);
                    return;
                }
                try{
                    livro.setQuantidadeDisponivel(livro.getQuantidadeDisponivel() - quantidade);
                    livro.setQuantidadeTotal(livro.getQuantidadeTotal() - quantidade);
                }catch(QuantidadeLivrosDisponiveisInvalidaException | QuantidadeTotalLivrosInvalidaException e){
                    System.out.println(e.getMessage());                }
            }else{
                throw new QuantidadeLivrosDisponiveisInvalidaException("Quantidade disponível de livros inválida. Deve ser maior ou igual a zero e menor ou igual à quantidade total de livros.");
            }
        }
    }
    public static Livro buscarLivroPorId(int id) throws NullPointerException {
        for(Livro livro : Biblioteca.getLivros()) {
            if(livro.getId() == id) {
                return livro;
            }
        }
        throw new NullPointerException("Livro não encontrado.");
    }
    public static ArrayList<Livro> getLivros(){
        return livros;
    }
    public static void adicionarAutor(Autor autor) {
        if(!autores.contains(autor) && autor != null) {
            autores.add(autor);
        }
    }
    public static void removerAutor(Autor autor) throws AutorNaoPodeSerRemovidoException {
        if(autores.contains(autor)) {
            for(Livro livro : Biblioteca.getLivros()){
                if(livro.getAutor().equals(autor)){
                    throw new AutorNaoPodeSerRemovidoException("Há livros cadastrados com este autor. Considere remover os livros desse autor primeiro.");
                }
            }
            autores.remove(autor);
        }
    }
    public static Autor buscarAutorPorId(int id) throws NullPointerException {
        for(Autor autor : Biblioteca.getAutores()) {
            if(autor.getId() == id) {
                return autor;
            }
        }
        throw new NullPointerException("Autor não encontrado.");
    }
    public static ArrayList<Autor> getAutores(){
        return autores;
    }
}
