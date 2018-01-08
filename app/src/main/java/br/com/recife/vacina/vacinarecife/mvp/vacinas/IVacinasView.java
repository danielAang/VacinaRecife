package br.com.recife.vacina.vacinarecife.mvp.vacinas;

import java.util.List;

import br.com.recife.vacina.vacinarecife.model.Record;

/**
 * Created by morae on 06/01/2018.
 */
public interface IVacinasView {

    void showProgressDialog();
    void dismissProgressDialog();
    void loadVacinas(List<Record> records);
    void showErro(String message);

}
