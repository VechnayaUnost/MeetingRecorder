package by.darya.zdzitavetskaya.meetingrecorder.presentation.list;

import java.util.List;

import by.darya.zdzitavetskaya.meetingrecorder.room.model.Record;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class ListPresenter extends MvpPresenter<ListView> {
    private ListInteractor listInteractor = new ListInteractor();

    public void getAllRecords() {
        listInteractor.getAllRecordsFromDatabase()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Record>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Record> records) {
                        getViewState().showList(records);
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

//    public void insertRecord(Record record) {
//        Completable.fromAction(() -> App.getAppDatabase().getRecordDao().insert(record))
//                .observeOn(Schedulers.io())
//                .subscribeOn(Schedulers.io())
//                .subscribe(new CompletableObserver() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.d("GOOD", "succsessfull");
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        e.printStackTrace();
//                    }
//                });
//    }
}
