package br.com.recife.vacina.vacinarecife.mvp.vacinas;

import java.util.List;

import br.com.recife.vacina.vacinarecife.model.Record;

/**
 * Created by morae on 06/01/2018.
 */

public class IVacinasPresenterImpl implements IVacinasPresenter, IVacinasInteractor.OnLoadVacinasFinish {

    private IVacinasView vacinasView;
    private IVacinasInteractor vacinasInteractor;

    public IVacinasPresenterImpl(IVacinasView vacinasView, IVacinasInteractorImpl vacinasInteractor) {
        this.vacinasView = vacinasView;
        this.vacinasInteractor = vacinasInteractor;
    }

    @Override
    public void loadVacinas() {
        if (vacinasView != null) {
            vacinasView.showProgressDialog();
        }
        vacinasInteractor.loadVacinas(this);
    }

    @Override
    public void onDestroy() {
        vacinasView = null;
    }

    @Override
    public void onLoadVacinasSuccess(List<Record> records) {
        if (vacinasView != null) {
            vacinasView.dismissProgressDialog();
            vacinasView.loadVacinas(records);
        }
    }

    @Override
    public void onLoadVacinasError(int message) {
        if (vacinasView != null) {
            vacinasView.dismissProgressDialog();
            vacinasView.showErro(message);
        }
    }
}
