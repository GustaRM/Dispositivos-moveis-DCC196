package com.example.ibarba;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class BancoDeDados extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "iBarba.db";
    private static final int DATABASE_VERSION = 1;

    public BancoDeDados(Context context) {
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
                "PrecoSugerido REAL, " +
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
        onCreate(db);
    }


    public Usuario getUsuarioByEmailSenha(String email, String senha) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM Usuarios WHERE email=? AND senha=?";
        String[] selectionArgs = { email, senha };
        Cursor cursor = db.rawQuery(query, selectionArgs);

        Usuario usuario = null;
        if (cursor.moveToFirst()) {
            int IDusuario = cursor.getInt(cursor.getColumnIndex("IDusuario"));
            String nome = cursor.getString(cursor.getColumnIndex("nome"));
            String telefone = cursor.getString(cursor.getColumnIndex("telefone"));
            String tipoUsuario = cursor.getString(cursor.getColumnIndex("tipoUsuario"));
            usuario = new Usuario(IDusuario, nome, telefone, email, tipoUsuario, senha);
        }
        cursor.close();
        return usuario;
    }

    public void adicionaUsuario(Usuario usuario) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("nome", usuario.getNome());
        valores.put("telefone", usuario.getTelefone());
        valores.put("email", usuario.getEmail());
        valores.put("tipoUsuario", usuario.getTipoUsuario());
        valores.put("senha", usuario.getSenha());

        db.insert("Usuarios", null, valores);
        db.close();
    }

    public Servico getServicoById(int idServico) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM servicos WHERE IDservico = ?";
        String[] selectionArgs = { String.valueOf(idServico) };
        Cursor cursor = db.rawQuery(query, selectionArgs);

        Servico servico = null;
        if (cursor.moveToFirst()) {
            String nome = cursor.getString(cursor.getColumnIndex("nome"));
            String descricao = cursor.getString(cursor.getColumnIndex("descricao"));
            double custo = cursor.getDouble(cursor.getColumnIndex("custo"));
            double precoSugerido = cursor.getDouble(cursor.getColumnIndex("precoSugerido"));
            int duracao = cursor.getInt(cursor.getColumnIndex("duracao"));

            servico = new Servico(idServico, nome, descricao, custo, precoSugerido, duracao);
        }

        cursor.close();
        return servico;
    }


    public void adicionaServico(Servico servico) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("nome", servico.getNome());
        valores.put("descricao", servico.getDescricao());
        valores.put("custo", servico.getCusto());
        valores.put("PrecoSugerido", servico.getPrecoSugerido());
        valores.put("duracao", servico.getDuracao());

        db.insert("Servicos", null, valores);
        db.close();
    }

    public List<Servico> getListaServicos() {
        List<Servico> listaServicos = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM Servicos";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex("IDservico"));
                String nome = cursor.getString(cursor.getColumnIndex("nome"));
                String descricao = cursor.getString(cursor.getColumnIndex("descricao"));
                double custo = cursor.getDouble(cursor.getColumnIndex("custo"));
                double precoSugerido = cursor.getDouble(cursor.getColumnIndex("precoSugerido"));
                int duracao = cursor.getInt(cursor.getColumnIndex("duracao"));

                Servico servico = new Servico(id, nome, descricao, custo, precoSugerido, duracao);
                listaServicos.add(servico);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return listaServicos;
    }

    public void atualizarServico(Servico servico) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nome", servico.getNome());
        values.put("descricao", servico.getDescricao());
        values.put("custo", servico.getCusto());
        values.put("precoSugerido", servico.getPrecoSugerido());
        values.put("duracao", servico.getDuracao());

        String whereClause = "IDservico = ?";
        String[] whereArgs = { String.valueOf(servico.getIDservico()) };

        db.update("Servicos", values, whereClause, whereArgs);
        db.close();
    }

    public void removerServicoByID(int IDservico) {
        SQLiteDatabase db = this.getWritableDatabase();

        String whereClause = "IDservico = ?";
        String[] whereArgs = { String.valueOf(IDservico) };

        db.delete("Servicos", whereClause, whereArgs);
        db.close();
    }

    //Usado apenas para debug
    public void exibirUsuarios() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM Usuarios";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int ID = cursor.getInt(cursor.getColumnIndex("IDusuario"));
                String nome = cursor.getString(cursor.getColumnIndex("nome"));
                String telefone = cursor.getString(cursor.getColumnIndex("telefone"));
                String email = cursor.getString(cursor.getColumnIndex("email"));
                String tipoUsuario = cursor.getString(cursor.getColumnIndex("tipoUsuario"));
                String senha = cursor.getString(cursor.getColumnIndex("senha"));

                // Imprimir os valores do usuário no terminal
                System.out.println("ID: " + ID);
                System.out.println("Nome: " + nome);
                System.out.println("Telefone: " + telefone);
                System.out.println("Email: " + email);
                System.out.println("Tipo de Usuário: " + tipoUsuario);
                System.out.println("Senha: " + senha);
                System.out.println("-------------------");
            } while (cursor.moveToNext());
        }

        cursor.close();
    }


}