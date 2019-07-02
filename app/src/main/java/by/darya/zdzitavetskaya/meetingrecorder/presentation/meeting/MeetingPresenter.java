package by.darya.zdzitavetskaya.meetingrecorder.presentation.meeting;

import by.darya.zdzitavetskaya.meetingrecorder.presentation.BaseMvpPresenter;
import by.darya.zdzitavetskaya.meetingrecorder.room.model.Record;
import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class MeetingPresenter extends MvpPresenter<MeetingView> implements BaseMvpPresenter {
    private final MeetingInteractor meetingInteractor = new MeetingInteractor();

    public void getRecord(final Long id) {
        meetingInteractor.getRecordById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Record>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(Record record) {
                        getViewState().onRecordSuccess(record);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                });
    }

    void updateRecord(Record record) {
        meetingInteractor.updateRecord(record)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onComplete() {
                        getViewState().updateList();
                    }

                    @Override
                    public void onError(Throwable e) {
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
