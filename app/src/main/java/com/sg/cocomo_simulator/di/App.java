package com.sg.cocomo_simulator.di;

import android.app.Application;

public class App extends Application {
    private static App app;
    private Component component;

    @Override
    public void onCreate() {
        super.onCreate();
        app=this;
        component=DaggerComponent.builder().applicationModule(new ApplicationModule(this)).build();
    }

    public static App getApp() {
        return app;
    }

    public Component getComponent() {
        return component;
    }
}
