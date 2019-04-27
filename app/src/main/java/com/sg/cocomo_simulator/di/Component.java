package com.sg.cocomo_simulator.di;

import com.sg.cocomo_simulator.view.MainActivity;

import javax.inject.Singleton;

@Singleton
@dagger.Component(modules = {ApplicationModule.class})
public interface Component {

    void inject(MainActivity mainActivity);
}
