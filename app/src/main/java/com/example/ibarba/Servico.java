package com.example.ibarba;

import java.util.UUID;

public class Servico {

    private int IDservico;
    private String nome;
    private String descricao;
    private double custo;
    private double PrecoSugerido;
    private int duracao;

    public Servico(int IDservico, String nome, String descricao, double custo, double PrecoSugerido, int duracao) {
        this.IDservico = IDservico;
        this.nome = nome;
        this.descricao = descricao;
        this.custo = custo;
        this.PrecoSugerido = PrecoSugerido;
        this.duracao = duracao;
    }

    // Getters e Setters

    public int  getIDservico() {
        return IDservico;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getCusto() {
        return custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }

    public double getPrecoSugerido() {
        return PrecoSugerido;
    }

    public void setPrecoSugerido(double PrecoSugerido) {
        this.PrecoSugerido = PrecoSugerido;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }
}

