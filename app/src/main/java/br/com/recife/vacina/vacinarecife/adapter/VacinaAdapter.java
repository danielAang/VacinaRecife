package br.com.recife.vacina.vacinarecife.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.recife.vacina.vacinarecife.R;
import br.com.recife.vacina.vacinarecife.model.vacina.Records;

/**
 * Created by morae on 06/01/2018.
 */
public class VacinaAdapter extends BaseAdapter {

    private List<Records> vacinas;
    private Context context;

    public VacinaAdapter(List<Records> vacinas, Context context) {
        this.context = context;
        this.vacinas = vacinas;
    }

    @Override
    public int getCount() {
        return vacinas.size();
    }

    @Override
    public Object getItem(int i) {
        return vacinas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return vacinas.get(i).get_id();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row = view;
        VacinaHolder holder = null;
        if (row == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            row = inflater.inflate(android.R.layout.simple_list_item_2, viewGroup, false);
            holder = new VacinaHolder();
            holder.txtTitle = (TextView)row.findViewById(android.R.id.text1);
            holder.txtDescricao = (TextView)row.findViewById(android.R.id.text2);
            row.setTag(holder);
        } else {
            holder = (VacinaHolder) row.getTag();
        }
        Records record = vacinas.get(i);
        holder.txtTitle.setText(record.getVacina());
        holder.txtDescricao.setText(record.getIdade());
        return row;
    }

    static class VacinaHolder {
        TextView txtTitle;
        TextView txtDescricao;
    }
}
