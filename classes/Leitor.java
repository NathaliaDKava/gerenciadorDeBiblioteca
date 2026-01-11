package classes;

import excecoes.LimiteLivrosEmprestadosAtingidoException;
import excecoes.LivroNaoEncontradoException;
import excecoes.NumeroMaximoLivrosInvalidoException;
import excecoes.FraseInvalidaException;
import excecoes.RegistroJaExistenteException;

public class Leitor extends Usuario {
    /* Implementar metodo para pagamento da multa */
    private static int contadorIds = 0;
    private static int maxLivrosEmprestados = 5;
    private Emprestimo[] emprestimosAtivos;
    private double totalMultas;

    public Leitor(String nome, String email, String telefone, String usuario, String senha) throws FraseInvalidaException, RegistroJaExistenteException {
        super(nome, email, telefone, usuario, senha);
        this.id = ++contadorIds;
        this.emprestimosAtivos = new Emprestimo[maxLivrosEmprestados]; // Limite de 5 livros emprestados
        this.totalMultas = 0.0;
    }

    public void emprestarLivro(Emprestimo emprestimo) throws LimiteLivrosEmprestadosAtingidoException {
        for (int i = 0; i < emprestimosAtivos.length; i++) {
            if (emprestimosAtivos[i] == null) {
                emprestimosAtivos[i] = emprestimo;
                return;
            }
        }
        throw new LimiteLivrosEmprestadosAtingidoException();
    }
    public void devolverLivro(int livroId) throws LivroNaoEncontradoException {
        for (int i = 0; i < emprestimosAtivos.length; i++) {
            if (emprestimosAtivos[i] != null && emprestimosAtivos[i].getLivro().getId() == livroId) {
                this.totalMultas += emprestimosAtivos[i].calcularMulta();
                emprestimosAtivos[i] = null;
                return;
            }
        }
        throw new LivroNaoEncontradoException(livroId);
    }
    public boolean podeEmprestar() {
        for(Emprestimo emprestimo : emprestimosAtivos) {
            if(emprestimo == null) {
                return true;
            }
        }
        return false;
    }
    public int getQuantidadeLivrosEmprestados() {
        int cont = 0;
        for(Emprestimo emprestimo: emprestimosAtivos) {
            if(emprestimo != null) {
                cont++;
            }
        }
        return cont;
    }
    public Livro buscarLivroEmprestado(int livroId) {
        for(Emprestimo emprestimo : emprestimosAtivos) {
            if(emprestimo != null && emprestimo.getLivro().getId() == livroId) {
                return emprestimo.getLivro();
            }
        }
        return null;
    }
    /* Listar dados do emprestimo */
    public String listarEmprestimosAtivos() {
        StringBuilder sb = new StringBuilder();
        for(Emprestimo emprestimo : emprestimosAtivos) {
            if(emprestimo != null) {
                sb.append("Titulo: "+emprestimo.getLivro().getTitulo()).append("\nAutor: "+emprestimo.getLivro().getAutor().getNome());
                sb.append("\nISBN: "+emprestimo.getLivro().getIsbn());
            }
        }
        return sb.toString();
    }
    public Livro[] getLivrosEmprestados() {
        Livro[] livrosEmprestados = new Livro[maxLivrosEmprestados];
        int cont = 0;
        
        for(Emprestimo emprestimo : emprestimosAtivos) {
            if(emprestimo != null) {
                livrosEmprestados[cont] = emprestimo.getLivro();
                cont++;
            }
        }

        return livrosEmprestados;
    }
    public Emprestimo[] getEmprestimosAtivos() {
        return emprestimosAtivos;
    }
    public static void setMaxLivrosEmprestados(int max) throws NumeroMaximoLivrosInvalidoException {
        if(max > 0){
            maxLivrosEmprestados = max;
        }else{
            throw new NumeroMaximoLivrosInvalidoException();
        }
    }
    public static int getMaxLivrosEmprestados() {
        return maxLivrosEmprestados;
    }
    public void setTotalMultas(double totalMultas) throws IllegalArgumentException {
        if(totalMultas >= 0){
            this.totalMultas = totalMultas;
        }else{
            throw new IllegalArgumentException("Total de multas inválido. Deve ser maior ou igual a zero.");
        }
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
