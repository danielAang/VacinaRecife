package br.com.recife.vacina.vacinarecife.mvp.vacinas;

import java.util.List;

import br.com.recife.vacina.vacinarecife.model.Record;

/**
 * Created by morae on 06/01/2018.
 */
public interface IVacinasInteractor {

    interface OnLoadVacinasFinish {
        void onLoadVacinasSuccess(List<Record> records);
        void onLoadVacinasError(int message);
    }
    void loadVacinas(OnLoadVacinasFinish listener);
}
