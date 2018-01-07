package br.com.recife.vacina.vacinarecife.mvp.vacinas;

import java.util.List;

import br.com.recife.vacina.vacinarecife.asynctask.RecordsAsyncTask;
import br.com.recife.vacina.vacinarecife.interfaces.AsyncResponse;
import br.com.recife.vacina.vacinarecife.model.vacina.Records;

/**
 * Created by morae on 06/01/2018.
 */
public class IVacinasInteractorImpl implements IVacinasInteractor, AsyncResponse {

    private OnLoadVacinasFinish listener;

    @Override
    public void loadVacinas(OnLoadVacinasFinish listener) {
        this.listener = listener;
        RecordsAsyncTask task = new RecordsAsyncTask(this);
        task.execute();
    }

    @Override
    public void onAsyncFinish(List<Records> vacinas) {
        listener.onLoadVacinasSuccess(vacinas);
    }

    @Override
    public void onAsyncFinishError(String message) {
        listener.onLoadVacinasError(message);
    }
}
