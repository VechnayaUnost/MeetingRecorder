package by.darya.zdzitavetskaya.meetingrecorder.di.module;

import javax.inject.Singleton;

import by.darya.zdzitavetskaya.meetingrecorder.presentation.list.ListInteractor;
import by.darya.zdzitavetskaya.meetingrecorder.presentation.meeting.MeetingInteractor;
import by.darya.zdzitavetskaya.meetingrecorder.presentation.record.RecordInteractor;
import dagger.Module;
import dagger.Provides;

@Module
public class InteractorModule {

    @Provides
    @Singleton
    public ListInteractor providesListInteractor() {
        return new ListInteractor();
    }

    @Provides
    @Singleton
    public MeetingInteractor providesMeetingInteractor() {
        return new MeetingInteractor();
    }

    @Provides
    @Singleton
    public RecordInteractor recordInteractor() {
        return new RecordInteractor();
    }
}
