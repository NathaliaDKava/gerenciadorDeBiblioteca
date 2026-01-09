package classes;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

import excecoes.FraseInvalidaException;
import excecoes.RegistroJaExistenteException;

public abstract class Usuario {
    protected int id;
    private String nome;
    private String usuario;
    private String senha;
    private String email;
    private String telefone;

    public Usuario(String nome, String email, String telefone, String usuario, String senha, ArrayList<Bibliotecario> bibliotecarios, ArrayList<Leitor> leitores) throws FraseInvalidaException, RegistroJaExistenteException {
        setNome(nome);
        setEmail(email, bibliotecarios, leitores);
        setTelefone(telefone, bibliotecarios, leitores);
        setUsuario(usuario, bibliotecarios, leitores);
        setSenha(senha);
    }

    public void atualizarDados(ArrayList<Bibliotecario> bibliotecarios, ArrayList<Leitor> leitores) {
        Scanner leitor = new Scanner(System.in);
        
        System.out.println("Atualizar dados do usuário ID: " + id);
        try{
            System.out.println("Novo nome: ");
            leitor.nextLine();
            setNome(leitor.nextLine());
            System.out.println("Novo email: ");
            leitor.nextLine();
            setEmail(leitor.nextLine(), bibliotecarios, leitores);
            System.out.println("Novo telefone: ");
            leitor.nextLine();
            setTelefone(leitor.nextLine(), bibliotecarios, leitores);
        }catch(Exception e){
            System.out.println("Erro ao atualizar dados: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public void setNome(String nome) throws FraseInvalidaException {
        if(Validadores.validarString(nome)) {
            this.nome = nome;
        }else{
            throw new FraseInvalidaException("Nome inválido. Deve conter apenas letras e espaços.");
        }
    }

    public String getNome() {
        return nome;
    }

    public void setUsuario(String usuario, ArrayList<Bibliotecario> bibliotecarios, ArrayList<Leitor> leitores) throws FraseInvalidaException, RegistroJaExistenteException {
        if(Validadores.validarUsuario(usuario)) {
            if(Validadores.validarUsuarioUnico(usuario, bibliotecarios, leitores)){
                this.usuario = usuario;
            }else{
                throw new RegistroJaExistenteException("Usuário já cadastrado para outro usuário.");
            }
        }else{
            throw new FraseInvalidaException("Usuário inválido. Deve começar com letra e ter mais de 4 caracteres.");
        }
    }

    public String getUsuario(){
        return this.usuario;
    }

    public void setSenha(String senha) throws FraseInvalidaException {
        if(Validadores.validarSenha(senha)) {
            this.senha = senha;
        }else{
            throw new FraseInvalidaException("Senha inválida. A senha deve ter:\n" +
                                            "- Pelo menos uma letra maiúscula.\n" +
                                            "- Pelo menos uma letra minúscula.\n" +
                                            "- Pelo menos um número.\n" +
                                            "- No mínimo 7 caracteres.\n" +
                                            "- Não conter espaços.");
        }
    }

    public String getSenha(){
        return this.senha;
    }

    public void setEmail(String email, ArrayList<Bibliotecario> bibliotecarios, ArrayList<Leitor> leitores) throws FraseInvalidaException, RegistroJaExistenteException {
        if(Validadores.validarEmail(email)) {
            if(Validadores.validarEmailUnico(email, bibliotecarios, leitores)){
                this.email = email;
            }else{
                throw new RegistroJaExistenteException("Email já cadastrado para outro usuário.");
            }
        }else{
            throw new FraseInvalidaException("Email inválido. Deve conter '@' e domínio.");
        }
    }

    public String getEmail() {
        return email;
    }

    public void setTelefone(String telefone, ArrayList<Bibliotecario> bibliotecarios, ArrayList<Leitor> leitores) throws FraseInvalidaException, RegistroJaExistenteException {
        if(Validadores.validarTelefone(telefone)) {
            if(Validadores.validarTelefoneUnico(telefone, bibliotecarios, leitores)){
                this.telefone = telefone;
            }else{
                throw new RegistroJaExistenteException("Telefone já cadastrado para outro usuário.");
            }
        }else{
            throw new FraseInvalidaException("Telefone inválido. Deve conter apenas números, espaços, '+' ou '-'.");
        }
    }

    public String getTelefone() {
        return telefone;
    }
}