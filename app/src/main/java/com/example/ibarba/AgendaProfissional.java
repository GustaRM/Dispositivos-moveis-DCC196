package com.example.ibarba;

public class AgendaProfissional {
    private int IDagendaProfissional;
    private int IDusuario_Profissional;
    private String data;
    private String hora;
    private String status;

    public AgendaProfissional(int IDagendaProfissional, int IDusuario_Profissional, String data, String hora, String status) {
        this.IDagendaProfissional = IDagendaProfissional;
        this.IDusuario_Profissional = IDusuario_Profissional;
        this.data = data;
        this.hora = hora;
        this.status = status;
    }

    public int getIDagendaProfissional() {
        return IDagendaProfissional;
    }

    public void setIDagendaProfissional(int IDagendaProfissional) {
        this.IDagendaProfissional = IDagendaProfissional;
    }

    public int getIDusuario_Profissional() {
        return IDusuario_Profissional;
    }

    public void setIDusuario_Profissional(int IDusuario_Profissional) {
        this.IDusuario_Profissional = IDusuario_Profissional;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
