package by.darya.zdzitavetskaya.meetingrecorder.presentation;

import io.reactivex.disposables.CompositeDisposable;

public interface BaseMvpPresenter {
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    default void onDestroyPresenter() {
        compositeDisposable.clear();
    }
}
