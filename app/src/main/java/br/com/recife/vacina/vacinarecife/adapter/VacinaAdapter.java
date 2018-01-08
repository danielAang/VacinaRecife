package br.com.recife.vacina.vacinarecife.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.recife.vacina.vacinarecife.R;
import br.com.recife.vacina.vacinarecife.model.Record;
import br.com.recife.vacina.vacinarecife.util.Utils;

/**
 * Created by morae on 06/01/2018.
 */
public class VacinaAdapter extends BaseAdapter {

    private List<Record> vacinas;
    private Context context;
    private Long dataNascimento;

    public VacinaAdapter(List<Record> vacinas, Context context, Long dataNascimento) {
        this.context = context;
        this.vacinas = vacinas;
        this.dataNascimento = dataNascimento;
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
            holder.txtTitle = (TextView) row.findViewById(android.R.id.text1);
            holder.txtDescricao = (TextView) row.findViewById(android.R.id.text2);
            row.setTag(holder);
        } else {
            holder = (VacinaHolder) row.getTag();
        }
        Record record = vacinas.get(i);
        holder.txtTitle.setText(record.getVacina());
        holder.txtDescricao.setText(record.getIdade());
        if (Utils.getIdades(record, dataNascimento)) {
            holder.txtDescricao.setTextColor(ContextCompat.getColor(context, R.color.green));
        } else {
            holder.txtDescricao.setTextColor(ContextCompat.getColor(context, android.R.color.black));
        }
        return row;
    }

    static class VacinaHolder {
        TextView txtTitle;
        TextView txtDescricao;
    }
}
