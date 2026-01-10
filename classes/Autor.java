package classes;

import java.util.ArrayList;

import excecoes.FraseInvalidaException;
import excecoes.LivroInvalidoException;

public class Autor {
    private static int contadorIds;
    private int id;
    private String nome;
    private String nacionalidade;
    private String dataNascimento;
    private ArrayList<Livro> livros;

    public Autor(String nome, String nacionalidade, String dataNascimento) throws FraseInvalidaException {
        this.id = ++contadorIds;
        setNome(nome);
        setNacionalidade(nacionalidade);
        setDataNascimento(dataNascimento);
        this.livros = new ArrayList<Livro>();
    }

    static{
        contadorIds = 0;
    }

    public int getId() {
        return id;
    }
    public void setNome(String nome) throws FraseInvalidaException {
        if(Validadores.validarString(nome)) {
            this.nome = nome;
        }else{
            throw new FraseInvalidaException("Nome inválido.");
        }
    }
    public String getNome() {
        return nome;
    }
    public void setNacionalidade(String nacionalidade) throws FraseInvalidaException {
        if(Validadores.validarString(nacionalidade)) {
            this.nacionalidade = nacionalidade;
        }else{
            throw new FraseInvalidaException("Nacionalidade inválida.");
        }
    }
    public String getNacionalidade() {
        return nacionalidade;
    }
    public void setDataNascimento(String dataNascimento) throws FraseInvalidaException {
        if (Validadores.validarData(dataNascimento)) {
            this.dataNascimento = dataNascimento;
        }else{
            throw new FraseInvalidaException("Data de nascimento inválida.");
        }
    }
    public String getDataNascimento() {
        return dataNascimento;
    }
    public void adicionarLivro(Livro livro) throws LivroInvalidoException {
        if(livro != null && !livros.contains(livro)) {
            livros.add(livro);
            return;
        }
        throw new LivroInvalidoException("Livro invalido ou já adicionado.");
    }
    public void removerLivro(Livro livro) throws LivroInvalidoException {
        if(livro != null && livros.contains(livro)) {
            livros.remove(livro);
            return;
        }
        throw new LivroInvalidoException("Livro inválido ou não encontrado.");
    }
    public int getQuantidadeDeLivros() {
        return livros.size();
    }
    public void listarLivros() {
        if(livros.isEmpty()) {
            System.out.println("Nenhum livro associado a este autor.");
            return;
        }
        System.out.println("Livros do autor " + nome + ":");
        for(Livro livro : livros) {
            System.out.println("- " + livro.getTitulo());
        }
    }
    public static Autor buscarAutorPorNome(String nome) {
        for(Autor autor : Biblioteca.getAutores()) {
            if(autor.getNome().equalsIgnoreCase(nome)) {
                return autor;
            }
        }
        return null;
    }
    public void setLivros(ArrayList<Livro> livros) throws LivroInvalidoException {
        if(livros != null){
            this.livros = livros;
        }else{
            throw new LivroInvalidoException("Lista de livros vazia.");
        }
    }
    public ArrayList<Livro> getLivros() {
        return livros;
    }
}
