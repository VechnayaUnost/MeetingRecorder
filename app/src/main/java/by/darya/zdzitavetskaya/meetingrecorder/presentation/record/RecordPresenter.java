package by.darya.zdzitavetskaya.meetingrecorder.presentation.record;

import android.widget.EditText;

import com.jakewharton.rxbinding3.widget.RxTextView;

import by.darya.zdzitavetskaya.meetingrecorder.presentation.BaseMvpPresenter;
import by.darya.zdzitavetskaya.meetingrecorder.room.model.Record;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class RecordPresenter extends MvpPresenter<RecordView> implements BaseMvpPresenter {
    private final RecordInteractor recordInteractor = new RecordInteractor();

    void insertRecord(Record record) {
        recordInteractor.insertRecord(record)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new SingleObserver<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(Long aLong) {
                        getViewState().onRecordSaved(aLong);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                });
    }

    void setButtonEnabled(EditText etMeetingTitle, EditText etMeetingSpeech){
        Observable<String> title = RxTextView
                .textChanges(etMeetingTitle)
                .switchMap(charSequence -> Observable.just(charSequence.toString()));

        Observable<String> text = RxTextView
                .textChanges(etMeetingSpeech)
                .switchMap(charSequence -> Observable.just(charSequence.toString()));

        Observable
                .combineLatest(title, text, (s, s2) -> !s.isEmpty() & !s2.isEmpty())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(Boolean enabled) {
                        getViewState().buttonEnabled(enabled);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        onDestroyPresenter();
    }
}
