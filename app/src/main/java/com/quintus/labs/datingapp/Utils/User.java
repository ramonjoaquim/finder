package com.quintus.labs.datingapp.Utils;

import java.io.Serializable;

/**
 * Created by Quintus Labs on 18-Dec-2018.
 * www.quintuslabs.com
 */

public class User implements Serializable {
    private String email;
    private String username;
    private String telefone;
    private boolean programacao;
    private boolean informatica;
    private boolean analise;
    private boolean suporte;
    private String nascimento;


    public User() {
    }

    public User(String email, String username, String telefone, boolean programacao, boolean informatica, boolean analise, boolean suporte, String nascimento) {
        this.email = email;
        this.username = username;
        this.telefone = telefone;
        this.programacao = programacao;
        this.programacao = informatica;
        this.programacao = analise;
        this.programacao = suporte;
        this.nascimento = nascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.toUpperCase();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username.toUpperCase();
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public boolean isProgramacao() {
        return programacao;
    }

    public void setProgramacao(boolean programacao) {
        this.programacao = programacao;
    }

    public boolean isInformatica() {
        return informatica;
    }

    public void setInformatica(boolean informatica) {
        this.informatica = informatica;
    }

    public boolean isAnalise() {
        return analise;
    }

    public void setAnalise(boolean analise) {
        this.analise = analise;
    }

    public boolean isSuporte() {
        return suporte;
    }

    public void setSuporte(boolean suporte) {
        this.suporte = suporte;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    @Override
    public String toString() {
        return "User{" +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", telefone='" + telefone + '\'' +
                ", programacao=" + programacao +
                ", informatica=" + informatica +
                ", analise=" + analise +
                ", suporte=" + suporte +
                ", nascimento='" + nascimento + '\'' +
                '}';
    }
}
