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

        this.apagaTabelas(db);
        onCreate(db);
    }

    public void apagaTabelas(SQLiteDatabase db) {
        String comandoSQL;

        comandoSQL =  "DROP TABLE IF EXISTS Usuarios";
        db.execSQL(comandoSQL);
        comandoSQL =  "DROP TABLE IF EXISTS Servicos";
        db.execSQL(comandoSQL);
        comandoSQL =  "DROP TABLE IF EXISTS AgendaProfissional";
        db.execSQL(comandoSQL);
        comandoSQL =  "DROP TABLE IF EXISTS Atendimentos";
        db.execSQL(comandoSQL);

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

    public Usuario getUsuarioByID(int IDusuario) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM Usuarios WHERE IDusuario=?";
        String[] selectionArgs = {  String.valueOf(IDusuario) };
        Cursor cursor = db.rawQuery(query, selectionArgs);

        Usuario usuario = null;
        if (cursor.moveToFirst()) {
            //int IDusuario = cursor.getInt(cursor.getColumnIndex("IDusuario"));
            String nome = cursor.getString(cursor.getColumnIndex("nome"));
            String telefone = cursor.getString(cursor.getColumnIndex("telefone"));
            String tipoUsuario = cursor.getString(cursor.getColumnIndex("tipoUsuario"));
            String email = cursor.getString(cursor.getColumnIndex("email"));
            String senha = cursor.getString(cursor.getColumnIndex("senha"));
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

    public void removerUsuarioByID(int IDusuario) {
        SQLiteDatabase db = this.getWritableDatabase();

        String whereClause = "IDusuario = ?";
        String[] whereArgs = { String.valueOf(IDusuario) };

        db.delete("Usuarios", whereClause, whereArgs);
        db.close();
    }
    public void atualizarUsuario(Usuario usuario) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("nome", usuario.getNome());
        valores.put("telefone", usuario.getTelefone());
        valores.put("email", usuario.getEmail());
        valores.put("tipoUsuario", usuario.getTipoUsuario());
        valores.put("senha", usuario.getSenha());

        String whereClause = "IDusuario = ?";
        String[] whereArgs = { String.valueOf(usuario.getIDusuario()) };

        db.update("Usuarios", valores, whereClause, whereArgs);
        db.close();
    }

    public Servico getServicoById(int IDservico) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM servicos WHERE IDservico = ?";
        String[] selectionArgs = { String.valueOf(IDservico) };
        Cursor cursor = db.rawQuery(query, selectionArgs);

        Servico servico = null;
        if (cursor.moveToFirst()) {
            String nome = cursor.getString(cursor.getColumnIndex("nome"));
            String descricao = cursor.getString(cursor.getColumnIndex("descricao"));
            double custo = cursor.getDouble(cursor.getColumnIndex("custo"));
            double precoSugerido = cursor.getDouble(cursor.getColumnIndex("precoSugerido"));
            int duracao = cursor.getInt(cursor.getColumnIndex("duracao"));

            servico = new Servico(IDservico, nome, descricao, custo, precoSugerido, duracao);
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
        ContentValues valores = new ContentValues();
        valores.put("nome", servico.getNome());
        valores.put("descricao", servico.getDescricao());
        valores.put("custo", servico.getCusto());
        valores.put("precoSugerido", servico.getPrecoSugerido());
        valores.put("duracao", servico.getDuracao());

        String whereClause = "IDservico = ?";
        String[] whereArgs = { String.valueOf(servico.getIDservico()) };

        db.update("Servicos", valores, whereClause, whereArgs);
        db.close();
    }

    public void removerServicoByID(int IDservico) {
        SQLiteDatabase db = this.getWritableDatabase();

        String whereClause = "IDservico = ?";
        String[] whereArgs = { String.valueOf(IDservico) };

        db.delete("Servicos", whereClause, whereArgs);
        db.close();
    }

    public List<Usuario> getListaProfissionais() {
        List<Usuario> listaProfissionais = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM Usuarios WHERE tipoUsuario = 'profissional'";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int IDusuario = cursor.getInt(cursor.getColumnIndex("IDusuario"));
                String nome = cursor.getString(cursor.getColumnIndex("nome"));
                String telefone = cursor.getString(cursor.getColumnIndex("telefone"));
                String email = cursor.getString(cursor.getColumnIndex("email"));
                String tipoUsuario = cursor.getString(cursor.getColumnIndex("tipoUsuario"));
                String senha = cursor.getString(cursor.getColumnIndex("senha"));

                Usuario profissional = new Usuario(IDusuario, nome, telefone, email, tipoUsuario, senha);
                listaProfissionais.add(profissional);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return listaProfissionais;
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

    public boolean isTabelaVazia(String tabela) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT 1 FROM " + tabela + " LIMIT 1";
        Cursor cursor = db.rawQuery(query, null);
        boolean tabelaVazia = (cursor.getCount() == 0);
        cursor.close();
        return tabelaVazia;
    }


    // Método para popular a lista de usuários com exemplos
    public void popularUsuarios() {

        this.adicionaUsuario(new Usuario(1, "João", "123456789", "joao@exemplo.com", "cliente", "senha1"));
        this.adicionaUsuario(new Usuario(2, "Maria", "987654321", "maria@exemplo.com", "cliente", "senha2"));
        this.adicionaUsuario(new Usuario(3, "Pedro", "456789123", "pedro@exemplo.com", "cliente", "senha3"));
        this.adicionaUsuario(new Usuario(4, "Ana", "321654987", "ana@exemplo.com", "cliente", "senha4"));
        this.adicionaUsuario(new Usuario(5, "Carlos", "654987321", "carlos@exemplo.com", "cliente", "senha5"));
        this.adicionaUsuario(new Usuario(6, "Mariana", "789123456", "mariana@exemplo.com", "cliente", "senha6"));
        this.adicionaUsuario(new Usuario(7, "Rafael", "987321654", "rafael@exemplo.com", "cliente", "senha7"));
        this.adicionaUsuario(new Usuario(8, "Camila", "789456123", "camila@exemplo.com", "cliente", "senha8"));
        this.adicionaUsuario(new Usuario(9, "Lucas", "321789654", "lucas@exemplo.com", "cliente", "senha9"));
        this.adicionaUsuario(new Usuario(10, "Fernanda", "456123789", "fernanda@exemplo.com", "cliente", "senha10"));
        this.adicionaUsuario(new Usuario(11, "Guilherme", "852963741", "guilherme@exemplo.com", "profissional", "senha11"));
        this.adicionaUsuario(new Usuario(12, "Patrícia", "369852147", "patricia@exemplo.com", "profissional", "senha12"));
        this.adicionaUsuario(new Usuario(13, "André", "147852369", "andre@exemplo.com", "profissional", "senha13"));
        this.adicionaUsuario(new Usuario(14, "Júlia", "963741852", "julia@exemplo.com", "profissional", "senha14"));
        this.adicionaUsuario(new Usuario(15, "Roberto", "258963147", "roberto@exemplo.com", "profissional", "senha15"));
        this.adicionaUsuario(new Usuario(16, "Carolina", "741852963", "carolina@exemplo.com", "profissional", "senha16"));
        this.adicionaUsuario(new Usuario(17, "Renato", "369147852", "renato@exemplo.com", "gestor", "senha17"));
        this.adicionaUsuario(new Usuario(18, "Amanda", "852147369", "amanda@exemplo.com", "cliente", "senha18"));
        this.adicionaUsuario(new Usuario(19, "Thiago", "147369852", "thiago@exemplo.com", "cliente", "senha19"));
        this.adicionaUsuario(new Usuario(20, "Gabriela", "741963852", "gabriela@exemplo.com", "cliente", "senha20"));
    }

    // Método para popular a lista de serviços com exemplos
    public void popularServicos() {
        this.adicionaServico(new Servico(1,"Corte de Cabelo", "Corte moderno e estilizado.", 20.0, 30.0, 30));
        this.adicionaServico(new Servico(2,"Barba", "Barba bem aparada e cuidada.", 15.0, 25.0, 20));
        this.adicionaServico(new Servico(3,"Sobrancelha", "Design de sobrancelha masculina.", 10.0, 15.0, 15));
        this.adicionaServico(new Servico(4,"Massagem", "Massagem relaxante para aliviar o estresse.", 40.0, 60.0, 60));
        this.adicionaServico(new Servico(5,"Limpeza de Pele", "Tratamento facial para limpar a pele.", 30.0, 45.0, 45));
        this.adicionaServico(new Servico(6,"Corte com Navalha", "Corte de cabelo feito com navalha.", 25.0, 35.0, 40));
        this.adicionaServico(new Servico(7,"Tintura de Barba", "Coloração da barba para um visual diferente.", 20.0, 30.0, 30));
        this.adicionaServico(new Servico(8, "Hidratação Capilar", "Tratamento para hidratar e fortalecer os cabelos.", 30.0, 45.0, 45));
        this.adicionaServico(new Servico(9,"Depilação", "Remoção de pelos indesejados.", 25.0, 35.0, 40));
        this.adicionaServico(new Servico(10,"Design de Sobrancelha", "Modelagem de sobrancelhas masculinas.", 10.0, 15.0, 15));
        this.adicionaServico(new Servico(11, "Corte Infantil", "Corte de cabelo para crianças.", 15.0, 25.0, 20));
        this.adicionaServico(new Servico(12,"Relaxamento Capilar", "Tratamento para relaxar e alisar os cabelos.", 40.0, 60.0, 60));
        this.adicionaServico(new Servico(13,"Pedicure", "Tratamento estético para os pés masculinos.", 20.0, 30.0, 30));
        this.adicionaServico(new Servico(14,"Manicure", "Tratamento estético para as unhas masculinas.", 15.0, 25.0, 20));
        this.adicionaServico(new Servico(15,"Tintura de Cabelo", "Coloração dos cabelos para um visual diferente.", 30.0, 45.0, 45));
        this.adicionaServico(new Servico(16,"Alinhamento de Barba", "Alinhamento e desenho da barba.", 20.0, 30.0, 30));
        this.adicionaServico(new Servico(17,"Massagem Capilar", "Massagem no couro cabeludo para relaxamento.", 25.0, 35.0, 40));
        this.adicionaServico(new Servico(18,"Limpeza de Orelhas", "Limpeza e cuidados com as orelhas.", 10.0, 15.0, 15));
        this.adicionaServico(new Servico(19,"Alisamento de Barba", "Alisamento dos pelos da barba.", 25.0, 35.0, 40));

    }


}