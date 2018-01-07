package br.com.recife.vacina.vacinarecife.model.vacina;

/**
 * Created by morae on 06/01/2018.
 */

public class Data {

    private String help;
    private boolean success;
    private Result result;

    public Data(String help, boolean success, Result result) {
        this.help = help;
        this.success = success;
        this.result = result;
    }

    public String getHelp() {
        return help;
    }

    public void setHelp(String help) {
        this.help = help;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
