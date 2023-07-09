package com.example.ibarba;

import java.util.UUID;

public class Usuario {

    private int IDusuario;
    private String nome;
    private String telefone;
    private String email;
    private String tipoUsuario; //cliente, profissional ou gestor
    private String senha;

    // Construtor
    public Usuario(int IDusuario, String nome, String telefone, String email, String tipoUsuario, String senha) {
        this.IDusuario = IDusuario;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.tipoUsuario = tipoUsuario;
        this.senha = senha;
    }

    // Getters e Setters

    public int  getIDusuario() {
        return IDusuario;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
