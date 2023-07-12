package com.example.ibarba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;
import java.util.List;

public class Tela_Relatorio_3 extends AppCompatActivity {

    private BarChart barChart;

    private String ano;
    private ArrayList<String> listaDados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio3);

        TextView txtTituloGrafico = findViewById(R.id.txtTitulo);

        Intent intent = getIntent();
        if (intent != null) {
            ano = intent.getStringExtra("ano");
        }

        txtTituloGrafico.setText("Faturamento e Lucro Mensal no ano de "+ano);
        BarChart chart = findViewById(R.id.chart);

        // Obtem os dados do gráfico
        listaDados = MainActivity.bancoDeDados.getFaturamentoELucroPorMes(ano);

        List<String> listaMes = new ArrayList<>();
        List<Float> listaFaturamento =  new ArrayList<>();
        List<Float> listaLucro =  new ArrayList<>();
        for (String dado : listaDados) {
            String[] partes = dado.split("::");
            String mes = partes[0];
            float faturamento = Float.parseFloat(partes[1]);
            float lucro = Float.parseFloat(partes[2]);
            listaMes.add(mes);
            listaFaturamento.add(faturamento);
            listaLucro.add(lucro);
        }

        List<BarEntry> entriesFaturamento = new ArrayList<>();
        List<BarEntry> entriesLucro = new ArrayList<>();
        for (int i = 0; i < listaMes.size(); i++) {
            entriesFaturamento.add(new BarEntry(i, listaFaturamento.get(i)));
            entriesLucro.add(new BarEntry(i, listaLucro.get(i)));
        }

        // Criar conjuntos de dados para o faturamento e o lucro
        BarDataSet dataSetFaturamento = new BarDataSet(entriesFaturamento, "Faturamento");
        dataSetFaturamento.setColor(Color.GREEN);
        dataSetFaturamento.setDrawValues(false); // Desativar exibição dos valores nas barras

        BarDataSet dataSetLucro = new BarDataSet(entriesLucro, "Lucro");
        dataSetLucro.setColor(Color.BLUE);
        dataSetLucro.setDrawValues(false); // Desativar exibição dos valores nas barras

        // Adicionar os conjuntos de dados ao gráfico
        IBarDataSet[] dataSets = { dataSetFaturamento, dataSetLucro };

        // Criar uma instância de BarData com os conjuntos de dados
        BarData barData = new BarData(dataSets);
        float barWidth = 0.35f; // Largura das barras
        barData.setBarWidth(barWidth);

        // Ajustar as posições das barras para exibi-las lado a lado
        float groupSpace = 0.1f; // Espaço entre os grupos de barras
        float barSpace = (.5f - barWidth) / 2; // Espaço entre as barras dentro de cada grupo
        barData.groupBars(0f, groupSpace, barSpace);


        chart.setData(barData);

        // Configurar o eixo horizontal
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f); // Mostrar todas as labels dos meses
        xAxis.setValueFormatter(new IndexAxisValueFormatter(listaMes));
        xAxis.setTextSize(12f);

        // Configurar o eixo vertical
        YAxis yAxis = chart.getAxisLeft();
        yAxis.setAxisMinimum(0f); // Definir o valor mínimo do eixo como zero
        // Atualizar a exibição do gráfico
        chart.invalidate();
    }

}