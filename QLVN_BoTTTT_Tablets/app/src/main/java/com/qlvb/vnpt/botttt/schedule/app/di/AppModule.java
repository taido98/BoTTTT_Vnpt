package com.qlvb.vnpt.botttt.schedule.app.di;

import android.content.Context;

import com.qlvb.vnpt.botttt.schedule.app.BaseActivity;
import com.qlvb.vnpt.botttt.schedule.app.BaseFragment;
import com.qlvb.vnpt.botttt.schedule.app.BoTTTTApplication;
import com.qlvb.vnpt.botttt.schedule.app.interceptor.AddCookieInterceptor;
import com.qlvb.vnpt.botttt.schedule.app.interceptor.ReceiveCookieInterceptor;
import com.qlvb.vnpt.botttt.schedule.domain.repository.LoginUserCookies;
import com.qlvb.vnpt.botttt.schedule.ui.activity.LoginActivity;
import com.qlvb.vnpt.botttt.schedule.ui.activity.MainActivity;
import com.qlvb.vnpt.botttt.schedule.ui.activity.XemDanhSachTatCaFileActivity;
import com.qlvb.vnpt.botttt.schedule.ui.activity.ViewPDFActivity;
import com.qlvb.vnpt.botttt.schedule.ui.dialog.DialogUserClass;
import com.qlvb.vnpt.botttt.schedule.ui.fragment.DetailDocumentComingFragment;

import com.qlvb.vnpt.botttt.schedule.ui.fragment.FragmentRightContentComming;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by linhl on 8/24/2018.
 */
@Module(
        includes = {
                RepositoryModule.class,
                RestAdapterProviderModule.class,
                PresenterModule.class,
                UserModule.class,
                DocumentModule.class,
                MainModule.class
        },
        injects = {
                //app
                BoTTTTApplication.class,
                //-View
                BaseActivity.class,
                BaseFragment.class,

                //Activity
                MainActivity.class,
                LoginActivity.class,
                XemDanhSachTatCaFileActivity.class,
                ViewPDFActivity.class,

                // Fragment
                FragmentRightContentComming.class,
                DetailDocumentComingFragment.class,

                // Dialog
                DialogUserClass.class,

        },
        library = true)
public class AppModule {
    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public Context provideApplicationContext() {
        return this.context;
    }

    @Provides
    public AddCookieInterceptor provideAddCookieInterceptor(LoginUserCookies loginUserCookies) {
        return new AddCookieInterceptor(loginUserCookies);
    }

    @Provides
    public ReceiveCookieInterceptor provideReceiveCookieInterceptor(LoginUserCookies loginUserCookies) {
        return new ReceiveCookieInterceptor(loginUserCookies);
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(AddCookieInterceptor addCookieInterceptor,
                                            ReceiveCookieInterceptor receiveCookieInterceptor) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
// set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .connectTimeout(TimeUnit.MINUTES.toMillis(5L), TimeUnit.MILLISECONDS)
                .readTimeout(TimeUnit.MINUTES.toMillis(10L), TimeUnit.MILLISECONDS)
                .addInterceptor(logging)
                .addInterceptor(addCookieInterceptor)
                .addInterceptor(receiveCookieInterceptor)
                .build();
    }
}
