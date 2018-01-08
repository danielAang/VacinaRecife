package br.com.recife.vacina.vacinarecife.mvp.postos;

import java.util.List;

import br.com.recife.vacina.vacinarecife.model.Record;

/**
 * Created by morae on 07/01/2018.
 */

public class IPostosPresenterImpl implements IPostosPresenter, IPostosInteractor.OnLoadPostosFinish {

    private IPostosView view;
    private IPostosInteractor interactor;

    public IPostosPresenterImpl(IPostosView view, IPostosInteractorImpl interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void loadPostos() {
        if (view != null) {
            view.showProgressDialog();
            interactor.loadPostos(this);
        }
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void onLoadPostosSuccess(List<Record> records) {
        if (view != null) {
            view.dismissProgressDialog();
            view.loadVacinas(records);
        }
    }

    @Override
    public void onLoadPostosError(String message) {
        if (view != null) {
            view.dismissProgressDialog();
            view.showErro(message);
        }
    }
}
