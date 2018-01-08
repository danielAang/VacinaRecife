package br.com.recife.vacina.vacinarecife.asynctask;

import android.os.AsyncTask;

import java.io.IOException;
import java.util.List;

import br.com.recife.vacina.vacinarecife.interfaces.AsyncResponse;
import br.com.recife.vacina.vacinarecife.model.Data;
import br.com.recife.vacina.vacinarecife.model.Vacina;
import br.com.recife.vacina.vacinarecife.service.SaudeService;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by morae on 06/01/2018.
 */
public class RecordsAsyncTask extends AsyncTask<Object, Integer, List<Vacina>> {

    private AsyncResponse response = null;

    public RecordsAsyncTask(AsyncResponse response) {
        this.response = response;
    }

    @Override
    protected void onPostExecute(List<Vacina> records) {
        if (response != null)
            response.onAsyncFinish(records);
    }

    @Override
    protected List<Vacina> doInBackground(Object... objects) {
        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://dados.recife.pe.gov.br/api/action/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            SaudeService service = retrofit.create(SaudeService.class);
            Call<Data> repos = service.getVacinas();
            Response<Data> execute = repos.execute();
            Data data = execute.body();
            return data.getResult().getVacinas();
        } catch (IOException e) {
            e.printStackTrace();
            if (response != null)
                response.onAsyncFinishError("Erro ao carregar dados.");
        }
        return null;
    }
}
