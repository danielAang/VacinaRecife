package br.com.recife.vacina.vacinarecife.mvp.postos;

import java.util.List;

import br.com.recife.vacina.vacinarecife.asynctask.PostosAsyncTask;
import br.com.recife.vacina.vacinarecife.interfaces.AsyncResponse;
import br.com.recife.vacina.vacinarecife.model.Record;

/**
 * Created by morae on 07/01/2018.
 */
public class IPostosInteractorImpl implements IPostosInteractor, AsyncResponse {

    OnLoadPostosFinish listener;

    @Override
    public void loadPostos(OnLoadPostosFinish listener) {
        this.listener = listener;
        PostosAsyncTask task = new PostosAsyncTask(this);
        task.execute();
    }

    @Override
    public void onAsyncFinish(List<?> postos) {
        if (listener != null) {
            List<Record> lst = (List<Record>) postos;
            listener.onLoadPostosSuccess(lst);
        }
    }

    @Override
    public void onAsyncFinishError(int message) {
        if (listener != null) {
            listener.onLoadPostosError(message);
        }
    }
}
