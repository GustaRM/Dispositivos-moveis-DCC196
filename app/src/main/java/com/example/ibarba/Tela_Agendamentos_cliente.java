package com.example.ibarba;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class Tela_Agendamentos_cliente extends AppCompatActivity {

    private ArrayList<String> listaAgendamentosCliente= new ArrayList<>();

    private ArrayList<Integer> listaIDAtendimento = new ArrayList<>();

    private int IDAtendimento=-1;

    private Boolean confirmaCancelamento = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendamentos_cliente);

        ListView lista = (ListView) findViewById(R.id.listaAgendamentos);
        ArrayList<String> agendamentos = preencherAgendamentos();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, agendamentos);
        lista.setAdapter(arrayAdapter);

        //Voltar para o menu inicial (Cliente)
        Button btnVoltar = findViewById((R.id.btnSair));
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                IDAtendimento = listaIDAtendimento.get(i).intValue();
                exibirDialogoConfirmacao();
                //Toast.makeText(getApplicationContext(), "Horários: "+agendamentos.get(i).toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private ArrayList<String> preencherAgendamentos() {

        ArrayList<String> listaTemp = MainActivity.bancoDeDados.getListaAgendamentosClienteByIDusuario(MainActivity.IDusuario);

        listaIDAtendimento.clear();
        listaAgendamentosCliente.clear();

        for (String elemento : listaTemp) {
            // Divide o elemento em partes separadas por "::"
            String[] partes = elemento.split("::");
            int idAtendimento = Integer.parseInt(partes[0].trim());
            listaIDAtendimento.add(idAtendimento);
            //Atribui a segunda parte à listaAgendamentosCliente
            listaAgendamentosCliente.add(partes[1].trim());

        }

        return listaAgendamentosCliente;
    }

    private void exibirDialogoConfirmacao() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Cancelar Agendamento");
        builder.setMessage("Deseja realmente cancelar o agendamento?");

        // Adicionar o botão "Sim" para confirmar o cancelamento
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Chamar o método para atualizar o status do atendimento
                String status = "Cancelado pelo cliente";
                MainActivity.bancoDeDados.atualizarAtendimento(IDAtendimento,  status);

                // Encerrar
                finish();
            }
        });

        // Adicionar o botão "Não" para cancelar o diálogo
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Fechar o diálogo sem fazer nada
                dialog.dismiss();
            }
        });

        // Exibir o diálogo
        builder.show();
    }

}