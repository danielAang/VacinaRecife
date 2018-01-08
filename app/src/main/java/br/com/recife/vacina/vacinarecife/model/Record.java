package br.com.recife.vacina.vacinarecife.model;

/**
 * Created by morae on 07/01/2018.
 */

public class Record {

    private int _id;

    private String vacina;
    private String dose;
    private String idade;
    private String dose_qtd;
    private String doenca_protecao;
    private String via_administracao;

    private String unidade;
    private String fone;
    private String longitude;
    private int rpa;
    private String bairro;
    private String latitude;
    private String endereco;

    public Record() {
    }

    public Record(int _id, String vacina, String dose, String idade, String dose_qtd, String doenca_protecao, String via_administracao, String unidade, String fone, String longitude, int rpa, String bairro, String latitude, String endereco) {
        this._id = _id;
        this.vacina = vacina;
        this.dose = dose;
        this.idade = idade;
        this.dose_qtd = dose_qtd;
        this.doenca_protecao = doenca_protecao;
        this.via_administracao = via_administracao;
        this.unidade = unidade;
        this.fone = fone;
        this.longitude = longitude;
        this.rpa = rpa;
        this.bairro = bairro;
        this.latitude = latitude;
        this.endereco = endereco;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getVacina() {
        return vacina;
    }

    public void setVacina(String vacina) {
        this.vacina = vacina;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getDose_qtd() {
        return dose_qtd;
    }

    public void setDose_qtd(String dose_qtd) {
        this.dose_qtd = dose_qtd;
    }

    public String getDoenca_protecao() {
        return doenca_protecao;
    }

    public void setDoenca_protecao(String doenca_protecao) {
        this.doenca_protecao = doenca_protecao;
    }

    public String getVia_administracao() {
        return via_administracao;
    }

    public void setVia_administracao(String via_administracao) {
        this.via_administracao = via_administracao;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public int getRpa() {
        return rpa;
    }

    public void setRpa(int rpa) {
        this.rpa = rpa;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
