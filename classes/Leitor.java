package classes;

import java.util.ArrayList;

import excecoes.LimiteLivrosEmprestadosAtingidoException;
import excecoes.LivroNaoEncontradoException;
import excecoes.NumeroMaximoLivrosInvalidoException;
import excecoes.FraseInvalidaException;
import excecoes.RegistroJaExistenteException;

public class Leitor extends Usuario {
    private static int contadorIds = 0;
    private Livro[] livrosEmprestados;
    private int maxLivrosEmprestados = 5;
    private ArrayList<Emprestimo> emprestimosAtivos;
    private double totalMultas;

    public Leitor(String nome, String email, String telefone, String usuario, String senha) throws FraseInvalidaException, RegistroJaExistenteException {
        super(nome, email, telefone, usuario, senha);
        this.id = ++contadorIds;
        this.livrosEmprestados = new Livro[maxLivrosEmprestados]; // Limite de 5 livros emprestados
        this.emprestimosAtivos = new ArrayList<Emprestimo>();
        this.totalMultas = 0.0;
    }

    public void emprestarLivro(Livro livro) throws LimiteLivrosEmprestadosAtingidoException {
        for (int i = 0; i < livrosEmprestados.length; i++) {
            if (livrosEmprestados[i] == null) {
                livrosEmprestados[i] = livro;
                return;
            }
        }
        throw new LimiteLivrosEmprestadosAtingidoException();
    }
    public void devolverLivro(int livroId) throws LivroNaoEncontradoException {
        for (int i = 0; i < livrosEmprestados.length; i++) {
            if (livrosEmprestados[i] != null && livrosEmprestados[i].getId() == livroId) {
                livrosEmprestados[i] = null;
                return;
            }
        }
        throw new LivroNaoEncontradoException(livroId);
    }
    public boolean podeEmprestar() {
        for(Livro livro : livrosEmprestados) {
            if(livro == null) {
                return true;
            }
        }
        return false;
    }
    public int getQuantidadeLivrosEmprestados() {
        int cont = 0;
        for(Livro livro : livrosEmprestados) {
            if(livro != null) {
                cont++;
            }
        }
        return cont;
    }
    public Livro buscarLivroEmprestado(int livroId) {
        for(Livro livro : livrosEmprestados) {
            if(livro != null && livro.getId() == livroId) {
                return livro;
            }
        }
        return null;
    }
    public String listarLivrosEmprestados() {
        StringBuilder sb = new StringBuilder();
        for(Livro livro : livrosEmprestados) {
            if(livro != null) {
                sb.append("Titulo: "+livro.getTitulo()).append("\nAutor: ").append(livro.getAutor()).append("\n");
            }
        }
        return sb.toString();
    }
    public Livro[] getLivrosEmprestados() {
        return livrosEmprestados;
    }
    public void setMaxLivrosEmprestados(int max) throws NumeroMaximoLivrosInvalidoException {
        if(max > 0){
            this.maxLivrosEmprestados = max;
        }else{
            throw new NumeroMaximoLivrosInvalidoException();
        }
    }
    public int getMaxLivrosEmprestados() {
        return maxLivrosEmprestados;
    }
    public void setTotalMultas(double totalMultas) {
        this.totalMultas = totalMultas;
    }
    public double getTotalMultas() {
        return totalMultas;
    }
    @Override
    public void exibirDados() {
        System.out.println("ID: " + this.id);
        System.out.println("Nome: " + getNome());
        System.out.println("Email: " + getEmail());
        System.out.println("Telefone: " + getTelefone());
        System.out.println("Usuário: " + getUsuario());
        System.out.println("Total de Multas: R$ " + String.format("%.2f", this.totalMultas));
        System.out.println("Livros Emprestados: " + this.getQuantidadeLivrosEmprestados() + "/" + this.maxLivrosEmprestados);
    }
}
