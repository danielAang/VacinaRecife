package br.com.recife.vacina.vacinarecife.mvp.vacinas;

import java.util.List;

import br.com.recife.vacina.vacinarecife.model.vacina.Records;

/**
 * Created by morae on 06/01/2018.
 */
public interface IVacinasInteractor {

    interface OnLoadVacinasFinish {
        void onLoadVacinasSuccess(List<Records> records);
        void onLoadVacinasError(String message);
    }
    void loadVacinas(OnLoadVacinasFinish listener);
}
