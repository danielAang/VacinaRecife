package br.com.recife.vacina.vacinarecife.model.vacina;

import java.util.List;

/**
 * Created by morae on 06/01/2018.
 */

public class Records {

    private String vacina;
    private String dose;
    private String idade;
    private String dose_qtd;
    private String doenca_protecao;
    private String via_administracao;
    private int _id;

    public Records(String vacina, String dose, String idade, String dose_qtd, String doenca_protecao, String via_administracao, int _id) {
        this.vacina = vacina;
        this.dose = dose;
        this.idade = idade;
        this.dose_qtd = dose_qtd;
        this.doenca_protecao = doenca_protecao;
        this.via_administracao = via_administracao;
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

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }
}
