package by.darya.zdzitavetskaya.meetingrecorder;

import android.app.Application;

import com.facebook.stetho.Stetho;

import by.darya.zdzitavetskaya.meetingrecorder.room.AppDatabase;

public class App extends Application {

    private static AppDatabase appDatabase;

    @Override
    public void onCreate(){
        super.onCreate();

        Stetho.InitializerBuilder initializerBuilder = Stetho.newInitializerBuilder(this);
        initializerBuilder.enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this));
        Stetho.initialize(initializerBuilder.build());

        appDatabase = AppDatabase.getDatabase(getApplicationContext());
    }

    public static AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
