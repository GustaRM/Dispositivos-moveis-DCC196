package com.example.ibarba;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "iBarba.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String comandoSQL;

        //Cria, se não existir, a tabela de Usuários
        comandoSQL = "CREATE TABLE IF NOT EXISTS Usuarios (" +
                "IDusuario INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome TEXT, " +
                "telefone TEXT, " +
                "email TEXT, " +
                "tipoUsuario TEXT, " +
                "senha TEXT)";
        db.execSQL(comandoSQL);

        //Cria, se não existir, a tabela de Serviços
        comandoSQL =  "CREATE TABLE IF NOT EXISTS Servicos (" +
                "IDservico INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome TEXT, " +
                "descricao TEXT, " +
                "custo REAL, " +
                "precoSugerido REAL, " +
                "duracao INTEGER)";
        db.execSQL(comandoSQL);

        //Cria, se não existir, a tabela de Agenda do Profissional.
        comandoSQL =  "CREATE TABLE IF NOT EXISTS AgendaProfissional (" +
                "IDusuario_profissional INTEGER, " +
                "data TEXT, " +
                "hora TEXT, " +
                "status TEXT, " +
                "FOREIGN KEY (IDusuario_profissional) REFERENCES Usuario(IDusuario))";
        db.execSQL(comandoSQL);

        //Cria, se não existir, a tabela de Agenda de atendimentos.
        comandoSQL =  "CREATE TABLE IF NOT EXISTS Atendimentos (" +
                "IDatendimento INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "data TEXT, " +
                "horaInicio TEXT, " +
                "IDServico INTEGER, " +
                "IDusuario_profissional INTEGER, " +
                "IDusuario_cliente INTEGER, " +
                "precoFinal REAL, " +
                "status TEXT, " +
                "FOREIGN KEY (IDServico) REFERENCES Servico(IDservico), " +
                "FOREIGN KEY (IDusuario_profissional) REFERENCES Usuario(IDusuario), " +
                "FOREIGN KEY (IDusuario_cliente) REFERENCES Usuario(IDusuario))";
        db.execSQL(comandoSQL);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String comandoSQL;

        comandoSQL =  "DROP TABLE IF EXISTS Usuarios";
        db.execSQL(comandoSQL);
        comandoSQL =  "DROP TABLE IF EXISTS Servicos";
        db.execSQL(comandoSQL);
        comandoSQL =  "DROP TABLE IF EXISTS AgendaProfissional";
        db.execSQL(comandoSQL);
        comandoSQL =  "DROP TABLE IF EXISTS Atendimentos";
        db.execSQL(comandoSQL);
        //db.execSQL(UsuarioTable.DROP_TABLE);
        onCreate(db);
    }
}