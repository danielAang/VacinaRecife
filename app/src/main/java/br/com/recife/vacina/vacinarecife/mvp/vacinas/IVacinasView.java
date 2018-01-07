package br.com.recife.vacina.vacinarecife.mvp.vacinas;

import java.util.List;

import br.com.recife.vacina.vacinarecife.model.vacina.Records;

/**
 * Created by morae on 06/01/2018.
 */
public interface IVacinasView {

    void showProgressDialog();
    void dismissProgressDialog();
    void loadVacinas(List<Records> records);
    void showErro(String message);

}
