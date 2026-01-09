package classes;

import java.util.ArrayList;
import java.util.Scanner;
import excecoes.FraseInvalidaException;
import excecoes.RegistroJaExistenteException;

public class Biblioteca {
    private String nome;
    private String endereco;
    private ArrayList<Bibliotecario> bibliotecarios;
    private ArrayList<Leitor> leitores;
    private ArrayList<Livro> livros;
    
    public Biblioteca(String nome, String endereco) throws FraseInvalidaException {
        setNome(nome);
        setEndereco(endereco);
        this.bibliotecarios = new ArrayList<Bibliotecario>();
        this.leitores = new ArrayList<Leitor>();
        this.livros = new ArrayList<Livro>();
    }

    public void setNome(String nome) throws FraseInvalidaException {
        if(Validadores.validarString(nome)){
            this.nome = nome;
        }else{
            throw new FraseInvalidaException("Nome inválido. Deve conter apenas letras e espaços.");
        }
    }
    public String getNome(){
        return this.nome;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public String getEndereco(){
        return this.endereco;
    }
    public boolean cadastrarBibliotecario() {
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
            bibliotecario = new Bibliotecario(nome, email, telefone, usuario, senha, bibliotecarios, leitores);
            adicionarBibliotecario(bibliotecario);
        }catch(FraseInvalidaException | RegistroJaExistenteException e){
            System.out.println(e.getMessage());
        }
        scan.close();

        return true;
    }
    public void adicionarBibliotecario(Bibliotecario bibliotecario) {
        if(!bibliotecarios.contains(bibliotecario) && bibliotecario != null){
            bibliotecarios.add(bibliotecario);
        }
    }
    public void removerBibliotecario(Bibliotecario bibliotecario) {
        if(bibliotecarios.contains(bibliotecario)) {
            bibliotecarios.remove(bibliotecario);
        }
    }
    public ArrayList<Bibliotecario> getBibliotecarios() {
        return bibliotecarios;
    }
    public void adicionarLeitor(Leitor leitor) {
        if(!leitores.contains(leitor) && leitor != null) {
            leitores.add(leitor);
        }
    }
    public void removerLeitor(Leitor leitor) {
        if(leitores.contains(leitor)) {
            leitores.remove(leitor);
        }
    }
    public ArrayList<Leitor> getLeitores(){
        return leitores;
    }
    public void adicionarLivro(Livro livro) {
        if(!livros.contains(livro) && livro != null) {
            livros.add(livro);
        }
    }
    public void removerLivro(Livro livro) {
        if(livros.contains(livro)) {
            livros.remove(livro);
        }
    }
    public ArrayList<Livro> getLivros(){
        return livros;
    }
}
