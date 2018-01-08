package br.com.recife.vacina.vacinarecife.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.recife.vacina.vacinarecife.model.Record;

/**
 * Created by morae on 07/01/2018.
 */
public class PostosAdapter extends BaseAdapter {

    private List<Record> postos;
    private Context context;

    public PostosAdapter(List<Record> postos, Context context) {
        this.postos = postos;
        this.context = context;
    }

    @Override
    public int getCount() {
        return postos.size();
    }

    @Override
    public Object getItem(int i) {
        return postos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return postos.get(i).get_id();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row = view;
        PostosAdapter.PostoHolder holder = null;
        if (row == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            row = inflater.inflate(android.R.layout.simple_list_item_2, viewGroup, false);
            holder = new PostosAdapter.PostoHolder();
            holder.txtTitle = (TextView) row.findViewById(android.R.id.text1);
            holder.txtDescricao = (TextView) row.findViewById(android.R.id.text2);
            row.setTag(holder);
        } else {
            holder = (PostosAdapter.PostoHolder) row.getTag();
        }
        Record record = postos.get(i);
        holder.txtTitle.setText(record.getUnidade());
        holder.txtDescricao.setText(record.getBairro());
        return row;
    }

    static class PostoHolder {
        TextView txtTitle;
        TextView txtDescricao;
    }
}
