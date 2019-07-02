package com.qlvb.vnpt.botttt.schedule.app.interceptor;

import com.qlvb.vnpt.botttt.schedule.domain.repository.LoginUserCookies;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.Response;

public class ReceiveCookieInterceptor implements Interceptor {

    private LoginUserCookies loginUserCookies;

    public ReceiveCookieInterceptor(LoginUserCookies loginUserCookies) {
        this.loginUserCookies = loginUserCookies;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());

        List<String> headers = response.headers("Set-Cookie");
        if (headers != null && !headers.isEmpty()) {
            HashSet<String> cookies = new HashSet<String>();
            for (String cookie : headers) {
                String[] values = cookie.split(";");
                if (values.length > 0) {
                    cookies.add(values[0]);
                }
            }
            loginUserCookies.put(cookies);
        }
        return response;
    }

}