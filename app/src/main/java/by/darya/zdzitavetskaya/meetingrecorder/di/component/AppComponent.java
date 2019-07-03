package by.darya.zdzitavetskaya.meetingrecorder.di.component;

import javax.inject.Singleton;

import by.darya.zdzitavetskaya.meetingrecorder.MainActivity;
import by.darya.zdzitavetskaya.meetingrecorder.di.module.AppModule;
import by.darya.zdzitavetskaya.meetingrecorder.di.module.InteractorModule;
import by.darya.zdzitavetskaya.meetingrecorder.di.module.RoomModule;
import by.darya.zdzitavetskaya.meetingrecorder.presentation.list.ListInteractor;
import by.darya.zdzitavetskaya.meetingrecorder.presentation.list.ListPresenter;
import by.darya.zdzitavetskaya.meetingrecorder.presentation.meeting.MeetingInteractor;
import by.darya.zdzitavetskaya.meetingrecorder.presentation.meeting.MeetingPresenter;
import by.darya.zdzitavetskaya.meetingrecorder.presentation.record.RecordInteractor;
import by.darya.zdzitavetskaya.meetingrecorder.presentation.record.RecordPresenter;
import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, RoomModule.class, InteractorModule.class})
public interface AppComponent {

    void inject(MainActivity mainActivity);

    void inject(ListInteractor listInteractor);
    void inject(MeetingInteractor meetingInteractor);
    void inject(RecordInteractor recordInteractor);

    void inject(ListPresenter listPresenter);
    void inject(MeetingPresenter meetingPresenter);
    void inject(RecordPresenter recordPresenter);
}
