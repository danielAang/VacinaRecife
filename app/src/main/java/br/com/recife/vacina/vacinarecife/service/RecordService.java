package br.com.recife.vacina.vacinarecife.service;

import br.com.recife.vacina.vacinarecife.model.vacina.Data;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by morae on 06/01/2018.
 */
public interface RecordService {

    @GET("datastore_search?resource_id=8222148c-14d1-47ba-ae0e-39c879246284")
    Call<Data> getRecords();
}
