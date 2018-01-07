package br.com.recife.vacina.vacinarecife.asynctask;

import android.os.AsyncTask;

import java.io.IOException;
import java.util.List;

import br.com.recife.vacina.vacinarecife.interfaces.AsyncResponse;
import br.com.recife.vacina.vacinarecife.model.vacina.Data;
import br.com.recife.vacina.vacinarecife.model.vacina.Records;
import br.com.recife.vacina.vacinarecife.service.RecordService;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by morae on 06/01/2018.
 */
public class RecordsAsyncTask extends AsyncTask<Object, Integer, List<Records>> {

    private AsyncResponse response = null;

    public RecordsAsyncTask(AsyncResponse response) {
        this.response = response;
    }

    @Override
    protected void onPostExecute(List<Records> records) {
        if (response != null)
            response.onAsyncFinish(records);
    }

    @Override
    protected List<Records> doInBackground(Object... objects) {
        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://dados.recife.pe.gov.br/api/action/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            RecordService service = retrofit.create(RecordService.class);
            Call<Data> repos = service.getRecords();
            Response<Data> execute = repos.execute();
            Data data = execute.body();
            return data.getResult().getRecords();
        } catch (IOException e) {
            e.printStackTrace();
            if (response != null)
                response.onAsyncFinishError("Erro ao carregar dados.");
        }
        return null;
    }
}
