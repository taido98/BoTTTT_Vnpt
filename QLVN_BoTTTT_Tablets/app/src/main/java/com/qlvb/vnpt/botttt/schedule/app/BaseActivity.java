package com.qlvb.vnpt.botttt.schedule.app;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by linhl on 8/24/2018.
 */

public class BaseActivity extends AppCompatActivity {
    private KProgressHUD hud;
    private Toast toast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
        initProgress();
    }

    protected void injectDependencies() {
        ((BoTTTTApplication) getApplication()).inject(this);
    }

    protected void initProgress() {
        hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setDetailsLabel("Đang kết nối...")
                .setCancellable(true)
                .setAnimationSpeed(3)
                .setDimAmount(0.5f);
    }

    public void showProgressBar() {
        try {
            if (hud != null && !hud.isShowing())
                hud.show();
        } catch (Exception ex) {

        }

    }

    public void hideProgressBar() {
        try {
            if (hud != null && hud.isShowing())
                hud.dismiss();
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        } catch (Exception ex) {

        }

    }

    public void showToast(String message) {
        if (toast != null) {
            toast.cancel();
            toast = null;
        }
        toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }
}
