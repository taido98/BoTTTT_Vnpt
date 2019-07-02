package com.qlvb.vnpt.botttt.schedule.app.di;

import android.content.Context;

import com.qlvb.vnpt.botttt.schedule.domain.repository.AppState;
import com.qlvb.vnpt.botttt.schedule.domain.repository.LoginUserCookies;
import com.qlvb.vnpt.botttt.schedule.domain.repository.Preferences;

import dagger.Module;
import dagger.Provides;

/**
 * Created by linhl on 4/13/2018.
 */
@Module(complete = false, library = true)
public class RepositoryModule {
    @Provides
    public Preferences providePreferences(Context context) {
        return new Preferences(context);
    }

    @Provides
    public AppState provideAppState(Preferences preferences) {
        return new AppState(preferences);
    }
    @Provides
    public LoginUserCookies provideLoginUserCookies(Preferences preferences) {
        return new LoginUserCookies(preferences);
    }
}
