package com.example.ibarba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Cria uma lista para armazenar os usuários
    private static List<Usuario> usuarios = new ArrayList<>();
    private static List<Servico> servicos = new ArrayList<>();

    public static List<Usuario> getUsuarios() {
        return usuarios;
    }

    public static List<Servico> getServicos() {
        return servicos;
    }

    public static void adicionarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public static void adicionarServico(Servico servico) {
        servicos.add(servico);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializa a lista de usuários
        usuarios = new ArrayList<>();
        // Popula a lista com os usuários desejados
        popularUsuarios();
        popularServicos();

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
                Usuario usuarioEncontrado = null;
                for (Usuario usuario : usuarios) {
                    if (usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)) {
                        usuarioEncontrado = usuario;
                        break;
                    }
                }

                if (usuarioEncontrado != null) {
                    // Usuário válido, redirecione para a tela apropriada com base no tipo de usuário
                    if (usuarioEncontrado.getTipoUsuario().equals("cliente")) {
                        // Redirecionar para a tela "Bem vindo cliente"
                        Intent intent = new Intent(MainActivity.this, Menu_cliente.class);
                        startActivity(intent);
                    } else if (usuarioEncontrado.getTipoUsuario().equals("profissional")) {
                        // Redirecionar para a tela "Bem vindo profissional"
                        Intent intent = new Intent(MainActivity.this, Menu_gestor.class);
                        startActivity(intent);
                    } else if (usuarioEncontrado.getTipoUsuario().equals("gestor")) {
                        // Redirecionar para a tela "Bem vindo gestor"
                        Intent intent = new Intent(MainActivity.this, Menu_gestor.class);
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
                Intent intent = new Intent(MainActivity.this, Cadastro_cliente.class);
                startActivity(intent);
            }
        });

    }

    // Método para popular a lista de usuários com exemplos
    private void popularUsuarios() {
        usuarios.add(new Usuario("João", "123456789", "joao@exemplo.com", "cliente", "senha1"));
        usuarios.add(new Usuario("Maria", "987654321", "maria@exemplo.com", "cliente", "senha2"));
        usuarios.add(new Usuario("Pedro", "456789123", "pedro@exemplo.com", "cliente", "senha3"));
        usuarios.add(new Usuario("Ana", "321654987", "ana@exemplo.com", "cliente", "senha4"));
        usuarios.add(new Usuario("Carlos", "654987321", "carlos@exemplo.com", "cliente", "senha5"));
        usuarios.add(new Usuario("Mariana", "789123456", "mariana@exemplo.com", "cliente", "senha6"));
        usuarios.add(new Usuario("Rafael", "987321654", "rafael@exemplo.com", "cliente", "senha7"));
        usuarios.add(new Usuario("Camila", "789456123", "camila@exemplo.com", "cliente", "senha8"));
        usuarios.add(new Usuario("Lucas", "321789654", "lucas@exemplo.com", "cliente", "senha9"));
        usuarios.add(new Usuario("Fernanda", "456123789", "fernanda@exemplo.com", "cliente", "senha10"));
        usuarios.add(new Usuario("Guilherme", "852963741", "guilherme@exemplo.com", "profissional", "senha11"));
        usuarios.add(new Usuario("Patrícia", "369852147", "patricia@exemplo.com", "profissional", "senha12"));
        usuarios.add(new Usuario("André", "147852369", "andre@exemplo.com", "profissional", "senha13"));
        usuarios.add(new Usuario("Júlia", "963741852", "julia@exemplo.com", "profissional", "senha14"));
        usuarios.add(new Usuario("Roberto", "258963147", "roberto@exemplo.com", "profissional", "senha15"));
        usuarios.add(new Usuario("Carolina", "741852963", "carolina@exemplo.com", "profissional", "senha16"));
        usuarios.add(new Usuario("Renato", "369147852", "renato@exemplo.com", "gestor", "senha17"));
        usuarios.add(new Usuario("Amanda", "852147369", "amanda@exemplo.com", "cliente", "senha18"));
        usuarios.add(new Usuario("Thiago", "147369852", "thiago@exemplo.com", "cliente", "senha19"));
        usuarios.add(new Usuario("Gabriela", "741963852", "gabriela@exemplo.com", "cliente", "senha20"));
    }

    // Método para popular a lista de serviços com exemplos
    private void popularServicos() {
        servicos.add(new Servico("Corte de Cabelo", "Corte moderno e estilizado.", 20.0, 30.0, 30));
        servicos.add(new Servico("Barba", "Barba bem aparada e cuidada.", 15.0, 25.0, 20));
        servicos.add(new Servico("Sobrancelha", "Design de sobrancelha masculina.", 10.0, 15.0, 15));
        servicos.add(new Servico("Massagem", "Massagem relaxante para aliviar o estresse.", 40.0, 60.0, 60));
        servicos.add(new Servico("Limpeza de Pele", "Tratamento facial para limpar a pele.", 30.0, 45.0, 45));
        servicos.add(new Servico("Corte com Navalha", "Corte de cabelo feito com navalha.", 25.0, 35.0, 40));
        servicos.add(new Servico("Tintura de Barba", "Coloração da barba para um visual diferente.", 20.0, 30.0, 30));
        servicos.add(new Servico("Hidratação Capilar", "Tratamento para hidratar e fortalecer os cabelos.", 30.0, 45.0, 45));
        servicos.add(new Servico("Depilação", "Remoção de pelos indesejados.", 25.0, 35.0, 40));
        servicos.add(new Servico("Design de Sobrancelha", "Modelagem de sobrancelhas masculinas.", 10.0, 15.0, 15));
        servicos.add(new Servico("Corte Infantil", "Corte de cabelo para crianças.", 15.0, 25.0, 20));
        servicos.add(new Servico("Relaxamento Capilar", "Tratamento para relaxar e alisar os cabelos.", 40.0, 60.0, 60));
        servicos.add(new Servico("Pedicure", "Tratamento estético para os pés masculinos.", 20.0, 30.0, 30));
        servicos.add(new Servico("Manicure", "Tratamento estético para as unhas masculinas.", 15.0, 25.0, 20));
        servicos.add(new Servico("Tintura de Cabelo", "Coloração dos cabelos para um visual diferente.", 30.0, 45.0, 45));
        servicos.add(new Servico("Alinhamento de Barba", "Alinhamento e desenho da barba.", 20.0, 30.0, 30));
        servicos.add(new Servico("Massagem Capilar", "Massagem no couro cabeludo para relaxamento.", 25.0, 35.0, 40));
        servicos.add(new Servico("Limpeza de Orelhas", "Limpeza e cuidados com as orelhas.", 10.0, 15.0, 15));
        servicos.add(new Servico("Alisamento de Barba", "Alisamento dos pelos da barba.", 25.0, 35.0, 40));
    }
}