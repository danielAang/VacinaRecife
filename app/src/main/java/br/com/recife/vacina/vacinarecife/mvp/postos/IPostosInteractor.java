package br.com.recife.vacina.vacinarecife.mvp.postos;

import java.util.List;

import br.com.recife.vacina.vacinarecife.model.Record;

/**
 * Created by morae on 07/01/2018.
 */

public interface IPostosInteractor {

    interface OnLoadPostosFinish {
        void onLoadPostosSuccess(List<Record> records);
        void onLoadPostosError(int message);
    }
    void loadPostos(IPostosInteractor.OnLoadPostosFinish listener);

}