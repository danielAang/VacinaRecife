package br.com.recife.vacina.vacinarecife.mvp.postos;

import java.util.List;

import br.com.recife.vacina.vacinarecife.model.Record;

/**
 * Created by morae on 07/01/2018.
 */

public interface IPostosView {

    void showProgressDialog();
    void dismissProgressDialog();
    void loadVacinas(List<Record> records);
    void showErro(String message);

}
