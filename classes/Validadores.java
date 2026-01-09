package classes;

import java.util.ArrayList;

public abstract class Validadores {
    

    public static boolean validarString(String string){
        boolean isValid = true;

        if(string != null && !string.isEmpty()) {
            for(char c : string.toCharArray()) {
                if(!Character.isLetter(c) && c != ' ') {
                    isValid = false;
                    break;
                }
            }
        } else {
            isValid = false;
        }

        return isValid;
    }
    public static boolean validarData(String data){
        boolean isValid = true;

        if(data != null && !data.isEmpty()) {
            String[] parts = data.split("/");
            if(parts.length != 3) {
                isValid = false;
            } else {
                try {
                    int year = Integer.parseInt(parts[2]);
                    int month = Integer.parseInt(parts[1]);
                    int day = Integer.parseInt(parts[0]);

                    if(month < 1 || month > 12 || day < 1 || day > 31 || year < 0) {
                        isValid = false;
                    }
                } catch(NumberFormatException e) {
                    isValid = false;
                }
            }
        } else {
            isValid = false;
        }

        return isValid;
    }
    public static boolean validarIsbn(String isbn){
        boolean isValid = true;

        if(isbn != null && !isbn.isEmpty()) {
            for(char c : isbn.toCharArray()) {
                if(!Character.isDigit(c) && c != '-') {
                    isValid = false;
                    break;
                }
            }
        } else {
            isValid = false;
        }

        return isValid;
    }
    public static boolean validarIsbnUnico(String isbn, ArrayList<Livro> livros){
        boolean isValid = true;

        for(Livro livro : livros){
            if(isbn.equals(livro.getIsbn())){
                isValid = false;
            }
        }

        return isValid;
    }
    public static boolean validarEmail(String email){
        boolean isValid = true;

        if(email == null || email.isEmpty() || !email.contains("@") || !email.contains(".")) {
            isValid = false;
        }

        return isValid;
    }
    public static boolean validarEmailUnico(String email, ArrayList<Bibliotecario> bibliotecarios, ArrayList<Leitor> leitores){
        boolean isValid = true;

        for(Bibliotecario bibliotecario : bibliotecarios){
            if(email.equals(bibliotecario.getEmail())){
                isValid = false;
                return isValid;
            }
        }
        for(Leitor leitor : leitores){
            if(email.equals(leitor.getEmail())){
                isValid = false;
                break;
            }
        }

        return isValid;
    }
    public static boolean validarTelefone(String telefone){
        boolean isValid = true;

        if(telefone != null && !telefone.isEmpty()) {
            for(char c : telefone.toCharArray()) {
                if(!Character.isDigit(c) && c != '+' && c != '-' && c != ' ') {
                    isValid = false;
                    break;
                }
            }
        } else {
            isValid = false;
        }

        return isValid;
    }
    public static boolean validarTelefoneUnico(String telefone, ArrayList<Bibliotecario> bibliotecarios, ArrayList<Leitor> leitores){
        boolean isValid = true;

        for(Bibliotecario bibliotecario : bibliotecarios){
            if(telefone.equals(bibliotecario.getTelefone())){
                isValid = false;
                return isValid;
            }
        }
        for(Leitor leitor : leitores){
            if(telefone.equals(leitor.getTelefone())){
                isValid = false;
                break;
            }
        }

        return isValid;
    }
    public static boolean validarUsuario(String usuario){
        boolean isValid = true; 

        if(usuario == null || usuario.isEmpty() || usuario.length() <= 4 
        || !Character.isLetter(usuario.toCharArray()[0])){
            isValid = false;
        }

        return isValid;
    }
    public static boolean validarUsuarioUnico(String usuario, ArrayList<Bibliotecario> bibliotecarios, ArrayList<Leitor> leitores){
        boolean isValid = true;

        for(Bibliotecario bibliotecario : bibliotecarios){
            if(usuario.equals(bibliotecario.getUsuario())){
                isValid = false;
                return isValid;
            }
        }
        for(Leitor leitor : leitores){
            if(usuario.equals(leitor.getUsuario())){
                isValid = false;
                break;
            }
        }

        return isValid;
    }
    public static boolean validarSenha(String senha){
        boolean isValid = true;

        if(senha == null || senha.isEmpty() || senha.length() < 6){
            isValid = false;
        }else{
            boolean hasLowerCaseLetter = false;
            boolean hasUpperCaseLetter = false;
            boolean hasDigit = false;

            for(char c : senha.toCharArray()){
                if(Character.isLowerCase(c)){
                    hasLowerCaseLetter = true;
                } else if(Character.isDigit(c)){
                    hasDigit = true;
                } else if(Character.isUpperCase(c)){
                    hasUpperCaseLetter = true;
                } else if(Character.isWhitespace(c)){
                    isValid = false;
                    break;
                }
            }

            if(!hasLowerCaseLetter || !hasUpperCaseLetter || !hasDigit){
                isValid = false;
            }
        }

        return isValid;
    }
}
