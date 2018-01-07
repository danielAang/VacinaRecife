package br.com.recife.vacina.vacinarecife.interfaces;

import java.util.List;

import br.com.recife.vacina.vacinarecife.model.vacina.Records;

/**
 * Created by morae on 06/01/2018.
 */
public interface AsyncResponse {

    void onAsyncFinish(List<Records> vacinas);
    void onAsyncFinishError(String message);
}
