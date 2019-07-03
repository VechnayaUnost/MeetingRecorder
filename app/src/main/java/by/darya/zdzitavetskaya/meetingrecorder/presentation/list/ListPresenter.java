package by.darya.zdzitavetskaya.meetingrecorder.presentation.list;

import java.util.List;

import javax.inject.Inject;

import by.darya.zdzitavetskaya.meetingrecorder.App;
import by.darya.zdzitavetskaya.meetingrecorder.presentation.BaseMvpPresenter;
import by.darya.zdzitavetskaya.meetingrecorder.room.model.Record;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class ListPresenter extends MvpPresenter<ListView> implements BaseMvpPresenter {

    @Inject
    ListInteractor listInteractor;

    public ListPresenter() {
        App.getAppComponent().inject(this);
    }

    public void getAllRecords() {
        listInteractor.getAllRecordsFromDatabase()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Record>>() {
                    @Override
                    public void onSubscribe(final Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(final List<Record> records) {
                        getViewState().showList(records);
                    }

                    @Override
                    public void onError(final Throwable e) {
                        e.printStackTrace();
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        onDestroyPresenter();
    }
}
