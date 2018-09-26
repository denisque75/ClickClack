package com.clickclackmessenger;

import android.app.Application;

import com.clickclackmessenger.core.di.AppComponent;
import com.clickclackmessenger.core.di.DaggerAppComponent;
import com.clickclackmessenger.core.di.modules.AppModule;
import com.clickclackmessenger.core.di.modules.FirebaseModule;
import com.clickclackmessenger.core.di.modules.RepositoryModule;
import com.clickclackmessenger.core.di.modules.UseCaseModule;

public class App extends Application {
    private static App sApp;
    private AppComponent appComponent;

    public static AppComponent injector() {
        if (sApp == null) throw new RuntimeException("Application is not initialized yet");
        return sApp.appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sApp = this;
        setupAppComponent();
    }

    private void setupAppComponent() {
        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .repositoryModule(new RepositoryModule())
                .useCaseModule(new UseCaseModule())
                .firebaseModule(new FirebaseModule())
                .build();
    }


}
