package com.qlvb.vnpt.botttt.schedule.app.di;

import android.content.Context;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qlvb.vnpt.botttt.schedule.R;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(complete = false, library = true)
public class RestAdapterProviderModule {

    Gson gson = new GsonBuilder()
            .setLenient()
            .create();
    @Provides
    public Retrofit.Builder provideRestAdapterBuilderSaigon(Context context, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(context.getString(R.string.api_base_url_boTTTT))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());
    }

}