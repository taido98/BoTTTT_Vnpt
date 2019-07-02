package com.qlvb.vnpt.botttt.schedule.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

import com.kaopiz.kprogresshud.KProgressHUD;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

/**
 * Created by linhl on 8/24/2018.
 */

public class BaseFragment extends Fragment {
    private KProgressHUD hud;
    private Toast toast;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
        initProgress();
    }

    protected void injectDependencies() {
        ((BoTTTTApplication) getActivity().getApplication()).inject(this);
    }
    protected void initProgress() {
        hud = KProgressHUD.create(getActivity())
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
            getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        } catch (Exception ex) {

        }

    }
    public void showToast(String message) {
        if (toast != null) {
            toast.cancel();
            toast = null;
        }
        toast = Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT);
        toast.show();
    }


    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
