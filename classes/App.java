package classes;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class App {
    public static void main(String[] args) {
        System.out.println("Bem-vindo ao sistema de biblioteca!");
    }
    public void testeExceptions() {
        FileReader fr = null;
        try{
            fr = new FileReader("arquivo_inexistente.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + e.getMessage());
        }finally{
            if(fr != null){
                try {
                    fr.close();
                } catch (IOException e) {
                    System.out.println("Erro ao fechar o arquivo: " + e.getMessage());
                }
            }
        }

        try(FileReader fr2 = new FileReader("arquivo_inexistente.txt")) {
            // Operações com o arquivo
            //decimalToHex(255);
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Erro de I/O: " + e.getMessage());
        }
    }
    public String testeStringBuilder(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 100; i++){
            sb.append("Linha ").append(i).append("\n");
        }
        return sb.toString();
    }
}