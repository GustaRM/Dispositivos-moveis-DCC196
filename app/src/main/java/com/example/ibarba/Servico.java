package com.example.ibarba;

public class Servico {
    private String nome;
    private String descricao;
    private double custo;
    private double precoFinal;
    private int duracao;

    public Servico(String nome, String descricao, double custo, double precoFinal, int duracao) {
        this.nome = nome;
        this.descricao = descricao;
        this.custo = custo;
        this.precoFinal = precoFinal;
        this.duracao = duracao;
    }

    // Getters e Setters

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

    public double getPrecoFinal() {
        return precoFinal;
    }

    public void setPrecoFinal(double precoFinal) {
        this.precoFinal = precoFinal;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }
}

