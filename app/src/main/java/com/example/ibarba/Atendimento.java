package com.example.ibarba;

public class Atendimento {
    private int IDatendimento;
    private String data;
    private String horaInicio;
    private int IDServico;
    private int IDusuario_profissional;
    private int IDusuario_cliente;
    private double precoFinal;
    private String status;

    public Atendimento(int IDatendimento, String data, String horaInicio, int IDServico, int IDusuario_profissional, int IDusuario_cliente, double precoFinal, String status) {
        this.IDatendimento = IDatendimento;
        this.data = data;
        this.horaInicio = horaInicio;
        this.IDServico = IDServico;
        this.IDusuario_profissional = IDusuario_profissional;
        this.IDusuario_cliente = IDusuario_cliente;
        this.precoFinal = precoFinal;
        this.status = status;
    }

    public int getIDatendimento() {
        return IDatendimento;
    }

    public void setIDatendimento(int IDatendimento) {
        this.IDatendimento = IDatendimento;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public int getIDServico() {
        return IDServico;
    }

    public void setIDServico(int IDServico) {
        this.IDServico = IDServico;
    }

    public int getIDusuario_profissional() {
        return IDusuario_profissional;
    }

    public void setIDusuario_profissional(int IDusuario_profissional) {
        this.IDusuario_profissional = IDusuario_profissional;
    }

    public int getIDusuario_cliente() {
        return IDusuario_cliente;
    }

    public void setIDusuario_cliente(int IDusuario_cliente) {
        this.IDusuario_cliente = IDusuario_cliente;
    }

    public double getPrecoFinal() {
        return precoFinal;
    }

    public void setPrecoFinal(double precoFinal) {
        this.precoFinal = precoFinal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}