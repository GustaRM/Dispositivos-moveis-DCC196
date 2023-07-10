package com.example.ibarba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    public static BancoDeDados bancoDeDados;
    private SQLiteDatabase db;


    // Cria uma lista para armazenar os usuários
    private static List<Usuario> usuarios = new ArrayList<>();
    private static List<Servico> servicos = new ArrayList<>();

  //  public static List<Usuario> getUsuarios() {
   //     return usuarios;
    //}

    public static List<Servico> getServicos() {
        return servicos;
    }

  //  public static void adicionarUsuario(Usuario usuario) {
  //      usuarios.add(usuario);
  //  }

    public static void adicionarServico(Servico servico) {
        servicos.add(servico);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Instancia o banco de dados
        bancoDeDados = new BancoDeDados(this);
        db = bancoDeDados.getWritableDatabase();
//        bancoDeDados.apagaTabelas(db);
        if (bancoDeDados.isTabelaVazia("Usuarios")) {
            bancoDeDados.popularUsuarios();
        }

        if (bancoDeDados.isTabelaVazia("Servicos")) {
            bancoDeDados.popularServicos();
        }


        EditText txtEmail = findViewById(R.id.txtEmail);
        EditText txtSenha = findViewById(R.id.txtSenha);
        Button btnEntrar = findViewById(R.id.btnEntrar);
        TextView txtCadastro = findViewById(R.id.txtCadastro);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtEmail.getText().toString();
                String senha = txtSenha.getText().toString();

                // Verifique se o email e a senha fornecidos estão na lista de usuários

                Usuario usuario = bancoDeDados.getUsuarioByEmailSenha(email, senha);

                if (usuario != null) {
                    // Usuário válido, redirecione para a tela apropriada com base no tipo de usuário
                    if (usuario.getTipoUsuario().equals("cliente")) {
                        // Redirecionar para a tela "Bem vindo cliente"
                        Intent intent = new Intent(MainActivity.this, tela_Menu_cliente.class);
                        startActivity(intent);
                    } else if (usuario.getTipoUsuario().equals("profissional")) {
                        // Redirecionar para a tela "Bem vindo profissional"
                        Intent intent = new Intent(MainActivity.this, tela_Menu_profissional.class);
                        startActivity(intent);
                    } else if (usuario.getTipoUsuario().equals("gestor")) {
                        // Redirecionar para a tela "Bem vindo gestor"
                        Intent intent = new Intent(MainActivity.this, tela_Menu_gestor.class);
                        startActivity(intent);
                    }
                } else {
                    // Usuário inválido, exiba a mensagem "Usuário inexistente"
                    Toast.makeText(MainActivity.this, "Usuário inexistente", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Configurar um clique no texto de "Cadastre-se"

        txtCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirecionar para a tela de cadastro
                Intent intent = new Intent(MainActivity.this, tela_Cadastro_cliente.class);
                startActivity(intent);
            }
        });

    }

    // Método para popular a lista de usuários com exemplos
    public void popularUsuarios() {

        bancoDeDados.adicionaUsuario(new Usuario(1, "João", "123456789", "joao@exemplo.com", "cliente", "senha1"));
        bancoDeDados.adicionaUsuario(new Usuario(2, "Maria", "987654321", "maria@exemplo.com", "cliente", "senha2"));
        bancoDeDados.adicionaUsuario(new Usuario(3, "Pedro", "456789123", "pedro@exemplo.com", "cliente", "senha3"));
        bancoDeDados.adicionaUsuario(new Usuario(4, "Ana", "321654987", "ana@exemplo.com", "cliente", "senha4"));
        bancoDeDados.adicionaUsuario(new Usuario(5, "Carlos", "654987321", "carlos@exemplo.com", "cliente", "senha5"));
        bancoDeDados.adicionaUsuario(new Usuario(6, "Mariana", "789123456", "mariana@exemplo.com", "cliente", "senha6"));
        bancoDeDados.adicionaUsuario(new Usuario(7, "Rafael", "987321654", "rafael@exemplo.com", "cliente", "senha7"));
        bancoDeDados.adicionaUsuario(new Usuario(8, "Camila", "789456123", "camila@exemplo.com", "cliente", "senha8"));
        bancoDeDados.adicionaUsuario(new Usuario(9, "Lucas", "321789654", "lucas@exemplo.com", "cliente", "senha9"));
        bancoDeDados.adicionaUsuario(new Usuario(10, "Fernanda", "456123789", "fernanda@exemplo.com", "cliente", "senha10"));
        bancoDeDados.adicionaUsuario(new Usuario(11, "Guilherme", "852963741", "guilherme@exemplo.com", "profissional", "senha11"));
        bancoDeDados.adicionaUsuario(new Usuario(12, "Patrícia", "369852147", "patricia@exemplo.com", "profissional", "senha12"));
        bancoDeDados.adicionaUsuario(new Usuario(13, "André", "147852369", "andre@exemplo.com", "profissional", "senha13"));
        bancoDeDados.adicionaUsuario(new Usuario(14, "Júlia", "963741852", "julia@exemplo.com", "profissional", "senha14"));
        bancoDeDados.adicionaUsuario(new Usuario(15, "Roberto", "258963147", "roberto@exemplo.com", "profissional", "senha15"));
        bancoDeDados.adicionaUsuario(new Usuario(16, "Carolina", "741852963", "carolina@exemplo.com", "profissional", "senha16"));
        bancoDeDados.adicionaUsuario(new Usuario(17, "Renato", "369147852", "renato@exemplo.com", "gestor", "senha17"));
        bancoDeDados.adicionaUsuario(new Usuario(18, "Amanda", "852147369", "amanda@exemplo.com", "cliente", "senha18"));
        bancoDeDados.adicionaUsuario(new Usuario(19, "Thiago", "147369852", "thiago@exemplo.com", "cliente", "senha19"));
        bancoDeDados.adicionaUsuario(new Usuario(20, "Gabriela", "741963852", "gabriela@exemplo.com", "cliente", "senha20"));
    }

    // Método para popular a lista de serviços com exemplos
    public void popularServicos() {
        servicos.add(new Servico(1,"Corte de Cabelo", "Corte moderno e estilizado.", 20.0, 30.0, 30));
        servicos.add(new Servico(2,"Barba", "Barba bem aparada e cuidada.", 15.0, 25.0, 20));
        servicos.add(new Servico(3,"Sobrancelha", "Design de sobrancelha masculina.", 10.0, 15.0, 15));
        servicos.add(new Servico(4,"Massagem", "Massagem relaxante para aliviar o estresse.", 40.0, 60.0, 60));
        servicos.add(new Servico(5,"Limpeza de Pele", "Tratamento facial para limpar a pele.", 30.0, 45.0, 45));
        servicos.add(new Servico(6,"Corte com Navalha", "Corte de cabelo feito com navalha.", 25.0, 35.0, 40));
        servicos.add(new Servico(7,"Tintura de Barba", "Coloração da barba para um visual diferente.", 20.0, 30.0, 30));
        servicos.add(new Servico(8, "Hidratação Capilar", "Tratamento para hidratar e fortalecer os cabelos.", 30.0, 45.0, 45));
        servicos.add(new Servico(9,"Depilação", "Remoção de pelos indesejados.", 25.0, 35.0, 40));
        servicos.add(new Servico(10,"Design de Sobrancelha", "Modelagem de sobrancelhas masculinas.", 10.0, 15.0, 15));
        servicos.add(new Servico(11, "Corte Infantil", "Corte de cabelo para crianças.", 15.0, 25.0, 20));
        servicos.add(new Servico(12,"Relaxamento Capilar", "Tratamento para relaxar e alisar os cabelos.", 40.0, 60.0, 60));
        servicos.add(new Servico(13,"Pedicure", "Tratamento estético para os pés masculinos.", 20.0, 30.0, 30));
        servicos.add(new Servico(14,"Manicure", "Tratamento estético para as unhas masculinas.", 15.0, 25.0, 20));
        servicos.add(new Servico(15,"Tintura de Cabelo", "Coloração dos cabelos para um visual diferente.", 30.0, 45.0, 45));
        servicos.add(new Servico(16,"Alinhamento de Barba", "Alinhamento e desenho da barba.", 20.0, 30.0, 30));
        servicos.add(new Servico(17,"Massagem Capilar", "Massagem no couro cabeludo para relaxamento.", 25.0, 35.0, 40));
        servicos.add(new Servico(18,"Limpeza de Orelhas", "Limpeza e cuidados com as orelhas.", 10.0, 15.0, 15));
        servicos.add(new Servico(19,"Alisamento de Barba", "Alisamento dos pelos da barba.", 25.0, 35.0, 40));

        for (Servico servico : servicos) {
            bancoDeDados.adicionaServico(servico);
        }
    }

/*    public static List<Usuario> getProfissionais() {

        List<Usuario> profissionais = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            if (usuario.getTipoUsuario().equals("profissional")) {
                profissionais.add(usuario);
            }
        }
        return profissionais;
    }

 */

    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }
}