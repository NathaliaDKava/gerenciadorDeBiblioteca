package classes;

import java.util.ArrayList;
import excecoes.AnoInvalidoException;
import excecoes.FraseInvalidaException;
import excecoes.QuantidadeLivrosDisponiveisInvalidaException;
import excecoes.QuantidadeTotalLivrosInvalidaException;
import excecoes.RegistroJaExistenteException;

public class Livro {
    private static int contadorIds;
    private static double valorMultaPorDia;
    private int id;
    private String titulo;
    private Autor autor;
    private String isbn;
    private String genero;
    private String editora;
    private int anoPublicacao;
    private int quantidadeTotal;
    private int quantidadeDisponivel;

    public Livro(String titulo, Autor autor, String isbn, int anoPublicacao, int quantidade) throws FraseInvalidaException, AnoInvalidoException, RegistroJaExistenteException {
        this.id = ++contadorIds;
        setTitulo(titulo);
        setAutor(autor);
        setIsbn(isbn);
        setAnoPublicacao(anoPublicacao);
        quantidadeTotal += quantidade;
        quantidadeDisponivel += quantidade;
    }
    public Livro(String titulo, Autor autor, String isbn, String genero, String editora, int anoPublicacao, int quantidade) throws FraseInvalidaException, AnoInvalidoException, RegistroJaExistenteException {
        this(titulo, autor, isbn, anoPublicacao, quantidade);
        setGenero(genero);
        setEditora(editora);
    }

    static{
        contadorIds = 0;
    }
    {
        quantidadeTotal = 0;
        quantidadeDisponivel = 0;
    }

    public int getId() {
        return id;
    }
    public void setTitulo(String titulo) throws FraseInvalidaException {
        if(Validadores.validarString(titulo)) {
            this.titulo = titulo;
        }else{
            throw new FraseInvalidaException("Título inválido. Deve ter apenas letras e espaços");
        }
    }
    public String getTitulo() {
        return titulo;
    }
    public void setAutor(Autor autor) {
        if(autor != null){
            this.autor = autor;
        }
    }
    public Autor getAutor() {
        return autor;
    }
    public void setIsbn(String isbn) throws FraseInvalidaException, RegistroJaExistenteException {
        if(Validadores.validarIsbn(isbn)) {
            if(Validadores.validarIsbnUnico(isbn)){
                this.isbn = isbn;
            }else{
                throw new RegistroJaExistenteException("ISBN já cadastrado. Digite um ISBN único para o livro.");
            }
        }else{
            throw new FraseInvalidaException("ISBN inválido. Deve ter apenas números ou traços.");
        }
    }
    public String getIsbn() {
        return isbn;
    }
    public void setGenero(String genero) throws FraseInvalidaException {
        if(Validadores.validarString(genero)) {
            this.genero = genero;
        }else{
            throw new FraseInvalidaException("Gênero inválido. Deve ter apenas letras e espaços.");
        }
    }
    public String getGenero() {
        return genero;
    }
    public void setEditora(String editora) throws FraseInvalidaException {
        if(Validadores.validarString(editora)) {
            this.editora = editora;
        }else{
            throw new FraseInvalidaException("Editora inválida. Deve ter apenas letras e espaços.");
        }
    }
    public String getEditora() {
        return editora;
    }
    public void setAnoPublicacao(int anoPublicacao) throws AnoInvalidoException {
        if(anoPublicacao > 0) {
            this.anoPublicacao = anoPublicacao;
        }else{
            throw new AnoInvalidoException("Ano inválido. Deve ser maior do que 0.");
        }
    }
    public int getAnoPublicacao() {
        return anoPublicacao;
    }
    public void setQuantidadeTotal(int quantidadeTotal) throws QuantidadeTotalLivrosInvalidaException {
        if(quantidadeTotal >= 0 && quantidadeTotal >= quantidadeDisponivel){
            this.quantidadeTotal = quantidadeTotal;
        }else{
            throw new QuantidadeTotalLivrosInvalidaException("Quantidade total de livros inválida. Deve ser maior ou igual a zero e maior ou igual à quantidade de livros disponíveis.");
        }
    }
    public int getQuantidadeTotal() {
        return quantidadeTotal;
    }
    public void setQuantidadeDisponivel(int quantidadeDisponivel) throws QuantidadeLivrosDisponiveisInvalidaException {
        if(quantidadeDisponivel >= 0 && quantidadeDisponivel <= quantidadeTotal){
            this.quantidadeDisponivel = quantidadeDisponivel;
        }else{
            throw new QuantidadeLivrosDisponiveisInvalidaException("Quantidade disponível de livros inválida. Deve ser maior ou igual a zero e menor ou igual à quantidade total de livros.");
        }
    }
    public int getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }
}
