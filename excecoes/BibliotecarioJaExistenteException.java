package excecoes;

public class BibliotecarioJaExistenteException extends Exception {
    private String nomeBibliotecario;
    
    public BibliotecarioJaExistenteException(String nomeBibliotecario) {
        this.nomeBibliotecario = nomeBibliotecario;
    }
    
    @Override
    public String getMessage(){
        return "Bibliotecario " + nomeBibliotecario + " ja existente na biblioteca.";
    }
    public String getNomeBibliotecario() {
        return nomeBibliotecario;
    }
}
