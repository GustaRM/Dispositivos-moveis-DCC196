package com.example.ibarba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Tela_Cadastro_servico_editar extends AppCompatActivity {

    private EditText txtNome;
    private EditText txtDescricao;
    private EditText txtCusto;
    private EditText txtPrecoSugerido;
    private EditText txtDuracao;

    private Button btnConcluir;

    private Button btnExcluir;

    private Servico servico;
    private int IDservico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_servico_editar);

        // Obter o objeto Servico passado como extra
        Intent intent = getIntent();
        if (intent != null) {
            IDservico = intent.getIntExtra("IDservico", -1);
            if (IDservico!=-1) {
                servico = MainActivity.bancoDeDados.getServicoById(IDservico);
            }
        }


        // Configurar os campos de texto
        txtNome = findViewById(R.id.txtNome);
        txtDescricao = findViewById(R.id.txtDescricao);
        txtCusto = findViewById(R.id.txtCusto);
        txtPrecoSugerido = findViewById(R.id.txtPrecoSugerido);
        txtDuracao = findViewById(R.id.txtDuracao);
        btnConcluir = findViewById((R.id.btnConcluir));
        btnExcluir = findViewById((R.id.btnExcluir));

        // Preencher os campos com base nas informações do serviço
        if (servico != null) {
            txtNome.setText(servico.getNome());
            txtDescricao.setText(servico.getDescricao());
            txtCusto.setText(String.valueOf(servico.getCusto()));
            txtPrecoSugerido.setText(String.valueOf(servico.getPrecoSugerido()));
            txtDuracao.setText(String.valueOf(servico.getDuracao()));
        }

        // Configurar o clique do botão "Concluir"
        btnConcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obter os valores dos campos
                String nome = txtNome.getText().toString();
                String descricao = txtDescricao.getText().toString();
                double custo = Double.parseDouble(txtCusto.getText().toString());
                double PrecoSugerido = Double.parseDouble(txtPrecoSugerido.getText().toString());
                int duracao = Integer.parseInt(txtDuracao.getText().toString());

                servico.setNome(nome);
                servico.setDescricao(descricao);
                servico.setCusto(custo);
                servico.setPrecoSugerido(PrecoSugerido);
                servico.setDuracao(duracao);

                MainActivity.bancoDeDados.atualizarServico(servico);
                Toast.makeText(Tela_Cadastro_servico_editar.this, "Serviço alterado com sucesso!", Toast.LENGTH_SHORT).show();

                // Encerrar a tela de edição e voltar para a MainActivity
                finish();

            }
        });

        // Configurar o clique do botão "Excluir Serviço"
        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obter os valores dos campos
                MainActivity.bancoDeDados.removerServicoByID(IDservico);

                Toast.makeText(Tela_Cadastro_servico_editar.this, "Serviço excluído com sucesso!", Toast.LENGTH_SHORT).show();

                // Encerrar a tela de edição e voltar para a MainActivity
                finish();

            }
        });


    }
}