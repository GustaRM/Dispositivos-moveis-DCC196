package com.example.ibarba;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ServicoAdapter extends ArrayAdapter<Servico> {

    private LayoutInflater inflater;

    public ServicoAdapter(Context context, List<Servico> servicos) {
        super(context, 0, servicos);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        Servico servico = getItem(position);
        if (servico != null) {
            TextView textView = convertView.findViewById(android.R.id.text1);
            textView.setTextColor(Color.WHITE);
            textView.setText(servico.getNome());

        }

        return convertView;
    }
}