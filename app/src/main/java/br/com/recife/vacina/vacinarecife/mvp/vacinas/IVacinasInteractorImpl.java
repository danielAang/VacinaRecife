package br.com.recife.vacina.vacinarecife.mvp.vacinas;

import java.util.List;

import br.com.recife.vacina.vacinarecife.asynctask.VacinasAsyncTask;
import br.com.recife.vacina.vacinarecife.interfaces.AsyncResponse;
import br.com.recife.vacina.vacinarecife.model.Record;

/**
 * Created by morae on 06/01/2018.
 */
public class IVacinasInteractorImpl implements IVacinasInteractor, AsyncResponse {

    private OnLoadVacinasFinish listener;

    @Override
    public void loadVacinas(OnLoadVacinasFinish listener) {
        this.listener = listener;
        VacinasAsyncTask task = new VacinasAsyncTask(this);
        task.execute();
    }

    @Override
    public void onAsyncFinish(List<?> vacinas) {
        List<Record> lst = (List<Record>) vacinas;
        listener.onLoadVacinasSuccess(lst);
    }

    @Override
    public void onAsyncFinishError(int message) {
        listener.onLoadVacinasError(message);
    }
}
