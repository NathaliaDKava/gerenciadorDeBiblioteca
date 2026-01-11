package classes;

import java.time.LocalDate;
import java.time.Month;

public class Emprestimo {
    private static int contadorIds;
    private static int diasEmprestimo;
    private static int vezesRenovacao;
    private int id;
    private Leitor leitor;
    private Livro livro;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucaoPrevista;
    private LocalDate dataDevolucaoReal;
    private boolean ativo;
    private int vezesRenovado;

    static{
        contadorIds = Biblioteca.getEmprestimos().get(Biblioteca.getEmprestimos().size() - 1).getId() + 1;
        diasEmprestimo = 7;
        vezesRenovacao = 3;
    }

    public Emprestimo(Leitor leitor, Livro livro){
        this.id = ++contadorIds;
        setLeitor(leitor);
        setLivro(livro);
        this.dataEmprestimo = LocalDate.now();
        this.dataDevolucaoPrevista = dataEmprestimo.plusDays(diasEmprestimo);
        this.ativo = true;
    }

    public int getId(){
        return this.id;
    }
    public static void setDiasEmprestimo(int dias){
        if(dias > 0){
            diasEmprestimo = dias;
        }
    }
    public static int getDiasEmprestimo(){
        return diasEmprestimo;
    }
    public static void setVezesRenovacao(int vezes){
        if(vezes > 0){
            vezesRenovacao = vezes;
        }
    }
    public static int getVezesRenovacao(){
        return vezesRenovacao;
    }
    public void setLeitor(Leitor leitor) throws IllegalArgumentException {
        for(Leitor leitorB : Biblioteca.getLeitores()){
            if(leitorB.equals(leitor)){
                this.leitor = leitor;
                return;
            }
        }
        throw new IllegalArgumentException("Leitor não encontrado.");
    }
    public Leitor getLeitor(){
        return this.leitor;
    }
    public void setLivro(Livro livro) throws IllegalArgumentException {
        for(Livro livroB : Biblioteca.getLivros()){
            if(livroB.equals(livro)){
                this.livro = livro;
                return;
            }
        }
        throw new IllegalArgumentException("Livro não encontrado.");
    }
    public Livro getLivro(){
        return this.livro;
    }
    public void setDataEmprestimo(int dia, Month mes, int ano){
        LocalDate dataEmprestimo = LocalDate.of(dia, mes, ano);
        this.dataEmprestimo = dataEmprestimo;
    }
    public LocalDate getDataEmprestimo(){
        return this.dataEmprestimo;
    }
    public LocalDate getDataDevolucaoPrevista(){
        return this.dataDevolucaoPrevista;
    }
    public void setDataDevolucaoReal(int dia, Month mes, int ano){
        LocalDate dataDevolucaoReal = LocalDate.of(dia, mes, ano);
        this.dataDevolucaoReal = dataDevolucaoReal;
    }
    public LocalDate getDataDevolucaoReal(){
        return this.dataDevolucaoReal;
    }
    public boolean isAtivo(){
        return this.ativo;
    }
    public void setVezesRenovado(int vezes){
        if(vezes > 0 && vezes <= vezesRenovacao){
            this.vezesRenovado = vezes;
            return;
        }
    }
    public int getVezesRenovado(){
        return this.vezesRenovado;
    }

    public boolean estaAtrasado(){
        return dataDevolucaoReal.isAfter(dataDevolucaoPrevista);
    }
    public int calcularTempoAtraso(){
        if(estaAtrasado()){
            return dataDevolucaoReal.getDayOfMonth() - dataDevolucaoPrevista.getDayOfMonth();
        }
        return 0;
    }

    public double calcularMulta(){
        if(estaAtrasado()){
            return calcularTempoAtraso() * Livro.getValorMultaPorDia();
        }
        return 0.0;
    }
    public void aplicarMulta(){
        if(estaAtrasado()){
            leitor.setTotalMultas(leitor.getTotalMultas() + calcularMulta());
        }
    }

    public boolean podeRenovar(){
        return vezesRenovado < vezesRenovacao;
    }
    public void renovarEmprestimo(){
        if(podeRenovar()){
            dataDevolucaoPrevista = dataDevolucaoPrevista.plusDays(diasEmprestimo);
            vezesRenovado++;
        }
    }
}
