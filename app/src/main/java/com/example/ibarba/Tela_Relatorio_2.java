package com.example.ibarba;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class Tela_Relatorio_2 extends AppCompatActivity {

    private PieChart pieChart;

    private String mes;
    private ArrayList<String> listaDados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio2);


        Intent intent = getIntent();
        if (intent != null) {
            mes = intent.getStringExtra("mes");
        }

        TextView chartTitle = findViewById(R.id.chartTitle);
        pieChart = findViewById(R.id.pieChart);

        listaDados = MainActivity.bancoDeDados.getFaturamentoServicosNoMes(mes);
        List<PieEntry> entries = new ArrayList<>();

        for (String dados : listaDados) {

            String[] partes = dados.split("::");
            String nomeServico = partes[0];
            float valor = Float.parseFloat(partes[1]);

            entries.add(new PieEntry(valor, nomeServico));
        }


        PieDataSet dataSet = new PieDataSet(entries, "Data");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        dataSet.setValueTextColor(Color.WHITE);
        dataSet.setValueTextSize(16f);

        PieData data = new PieData(dataSet);

        pieChart.setData(data);
        pieChart.setDrawEntryLabels(true); // Exibir rótulos nas fatias
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setEntryLabelTextSize(10f);


        chartTitle.setText("Faturamento em reais, por Serviço, no mês "+mes);
        pieChart.getDescription().setEnabled(false);
        pieChart.animateY(1000);
        pieChart.invalidate();
    }
}

