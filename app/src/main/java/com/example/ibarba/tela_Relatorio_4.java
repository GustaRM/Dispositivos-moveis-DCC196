package com.example.ibarba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

public class tela_Relatorio_4 extends AppCompatActivity {

    private LineChart lineChart;

    private String ano;
    private ArrayList<String> listaDados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio4);

        Intent intent = getIntent();
        if (intent != null) {
            ano = intent.getStringExtra("ano");
        }

        // Obtem os dados do gráfico
        listaDados = MainActivity.bancoDeDados.getFaturamentoMedioPorCliente(ano);

        List<Float> listaFaturamento =  new ArrayList<>();
        for (String dado : listaDados) {
            String[] partes = dado.split("::");
            String mes = partes[0];
            float faturamento = Float.parseFloat(partes[1]);
            listaFaturamento.add(faturamento);
        }
        List<Entry> entriesFaturamento = new ArrayList<>();
        for (int i = 0; i < listaFaturamento.size(); i++) {
            entriesFaturamento.add(new Entry(i, listaFaturamento.get(i)));
        }

        lineChart = findViewById(R.id.lineChart);
        // Configurar o título do gráfico
        Description chartDescription = new Description();
        chartDescription.setText("Faturamento mensal médio por cliente");
        chartDescription.setTextSize(16f);
        chartDescription.setPosition(1300, 50); // Posiciona o título acima do gráfico


        lineChart.setDescription(chartDescription);


        LineDataSet dataSetFaturamento = createDataSet(entriesFaturamento, "Faturamento", Color.BLUE);

        LineData lineData = new LineData(dataSetFaturamento);

        // Configurar o eixo X
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                int month = (int) value + 1;
                return String.format("%02d/"+ano, month);
            }
        });

        // Configurar o eixo Y
        YAxis yAxisLeft = lineChart.getAxisLeft();
        YAxis yAxisRight = lineChart.getAxisRight();
//        yAxisLeft.setAxisMinimum(2000f);
//        yAxisLeft.setAxisMaximum(8000f);
//        yAxisRight.setAxisMinimum(2000f);
 //       yAxisRight.setAxisMaximum(8000f);
  //      yAxisRight.setEnabled(false); // Desabilita o eixo Y da direita

        // Configurar a legenda
        Legend legend = lineChart.getLegend();
        legend.setForm(Legend.LegendForm.LINE);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);
        legend.setTextSize(14f);

        // Exibir o gráfico
        lineChart.setData(lineData);
        lineChart.animateX(1000);
        lineChart.invalidate();

    }

    private List<Entry> generateEntries(float minValue, float maxValue) {
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            float value = (float) (Math.random() * (maxValue - minValue) + minValue);
            entries.add(new Entry(i, value));
        }
        return entries;
    }

    private LineDataSet createDataSet(List<Entry> entries, String label, int color) {
        LineDataSet dataSet = new LineDataSet(entries, label);
        dataSet.setColor(color);
        dataSet.setCircleColor(color);
        dataSet.setDrawValues(false);
        return dataSet;
    }
}