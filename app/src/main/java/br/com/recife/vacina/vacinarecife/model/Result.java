package br.com.recife.vacina.vacinarecife.model;

import java.util.List;

/**
 * Created by morae on 06/01/2018.
 */

public class Result {

    private String resource_id;
    private List<Fields> fields;
    private List<Record> records;

    public Result(String resource_id, List<Fields> fields, List<Record> records) {
        this.resource_id = resource_id;
        this.fields = fields;
        this.records = records;
    }

    public String getResource_id() {
        return resource_id;
    }

    public void setResource_id(String resource_id) {
        this.resource_id = resource_id;
    }

    public List<Fields> getFields() {
        return fields;
    }

    public void setFields(List<Fields> fields) {
        this.fields = fields;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

}
