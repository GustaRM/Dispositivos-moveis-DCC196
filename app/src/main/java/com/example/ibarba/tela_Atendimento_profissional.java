package com.example.ibarba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class tela_Atendimento_profissional extends AppCompatActivity {

    private int IDAtendimento;
    private Double precoFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atendimento_profissional);

        TextView txtDescricaoAtendimento = findViewById(R.id.txtDescricaoAtendimento);
        TextView txtDesconto = findViewById(R.id.txtDesconto);
        Spinner spinnerStatusAtendimento = findViewById(R.id.spinnerStatusAtendimento);
        Button btnConcluir = findViewById(R.id.btnConcluir);

        // Obter o objeto Usuario passado como extra
        Intent intent = getIntent();
        if (intent != null) {
            IDAtendimento = intent.getIntExtra("IDAtendimento", -1);
            precoFinal = intent.getDoubleExtra("precoFinal", -1);
            String descricaoAtendimento = intent.getStringExtra("descricaoAtendimento");
            if (IDAtendimento!=-1) {
                txtDescricaoAtendimento.setText(descricaoAtendimento);
            }
        }

        //Configura o spinner
        ArrayAdapter<String> spinnerAdapterStatus = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new String[]{"Agendado", "Realizado (pago)", "Realizado (a pagar))", "Cancelado pelo profissional", "Cancelado pelo cliente", "Cliente não compareceu" });
        spinnerStatusAtendimento.setAdapter(spinnerAdapterStatus);

        // Configurar o clique do botão "Concluir"
        btnConcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double percentualDesconto = Double.parseDouble(txtDesconto.getText().toString());
                double novoPrecoFinal = precoFinal * (1 - percentualDesconto / 100);
                String status = spinnerStatusAtendimento.getSelectedItem().toString();

                MainActivity.bancoDeDados.atualizarAtendimento(IDAtendimento, precoFinal, status);
                Toast.makeText(tela_Atendimento_profissional.this, "Atendimento concluído com sucesso!", Toast.LENGTH_SHORT).show();

                // Encerrar
                finish();
            }
        });
    }
}