package com.example.ibarba;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class tela_Relatorio_1 extends AppCompatActivity {

    private PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio1);


        TextView chartTitle = findViewById(R.id.chartTitle);
        pieChart = findViewById(R.id.pieChart);

        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(4280f, "Ana"));
        entries.add(new PieEntry(3850f, "Pedro"));
        entries.add(new PieEntry(4050f, "João"));
        entries.add(new PieEntry(6500f, "Maria"));


        PieDataSet dataSet = new PieDataSet(entries, "Data");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        dataSet.setValueTextColor(Color.WHITE);
        dataSet.setValueTextSize(16f);

        PieData data = new PieData(dataSet);

        pieChart.setData(data);
        pieChart.setDrawEntryLabels(true); // Exibir rótulos nas fatias
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setEntryLabelTextSize(10f);


        chartTitle.setText("Faturamento em reais, por Profissional, no mês 04/2023");
        pieChart.getDescription().setEnabled(false);
        pieChart.animateY(1000);
        pieChart.invalidate();
    }
}

