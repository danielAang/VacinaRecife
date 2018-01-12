package br.com.recife.vacina.vacinarecife.interfaces;

import java.util.List;

/**
 * Created by morae on 06/01/2018.
 */
public interface AsyncResponse {

    void onAsyncFinish(List<?> records);
    void onAsyncFinishError(int message);
}
