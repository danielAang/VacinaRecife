package br.com.recife.vacina.vacinarecife.model.vacina;

/**
 * Created by morae on 06/01/2018.
 */

public class Fields {

    private String type;
    private String id;

    public Fields(String type, String id) {
        this.type = type;
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
