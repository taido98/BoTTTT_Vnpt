package com.qlvb.vnpt.botttt.schedule.app.interceptor;

import com.qlvb.vnpt.botttt.schedule.domain.repository.LoginUserCookies;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AddCookieInterceptor implements Interceptor {

    private LoginUserCookies loginUserCookies;

    public AddCookieInterceptor(LoginUserCookies loginUserCookies) {
        this.loginUserCookies = loginUserCookies;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();

        HashSet<String> cookies = loginUserCookies.get();
        StringBuilder sb = new StringBuilder();
        for (String cookie : cookies) {
            sb.append(cookie).append("; ");
        }

        if (sb.length() > 0) {
            builder.addHeader("Cookie", sb.toString());
        }
        builder.addHeader("Content-Type", "application/json");
        builder.addHeader("x-authentication-token", loginUserCookies.getAuthent());
        return chain.proceed(builder.build());
    }

}