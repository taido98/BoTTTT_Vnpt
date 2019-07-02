package com.qlvb.vnpt.botttt.schedule.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.lguipeng.library.animcheckbox.AnimCheckBox;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.qlvb.vnpt.botttt.schedule.R;
import com.qlvb.vnpt.botttt.schedule.app.BaseActivity;
import com.qlvb.vnpt.botttt.schedule.app.realm.RealmController;
import com.qlvb.vnpt.botttt.schedule.app.utils.AlertDialogManager;
import com.qlvb.vnpt.botttt.schedule.domain.model.GetGroupResult;
import com.qlvb.vnpt.botttt.schedule.domain.model.GroupObject;
import com.qlvb.vnpt.botttt.schedule.domain.model.LoginObject;
import com.qlvb.vnpt.botttt.schedule.domain.model.MinisterObject;
import com.qlvb.vnpt.botttt.schedule.domain.model.UnitObject;
import com.qlvb.vnpt.botttt.schedule.domain.model.UserObject;
import com.qlvb.vnpt.botttt.schedule.domain.repository.AppState;
import com.qlvb.vnpt.botttt.schedule.domain.repository.LoginUserCookies;
import com.qlvb.vnpt.botttt.schedule.ui.presenter.LoginPresenter;
import com.qlvb.vnpt.botttt.schedule.ui.presenter.UserPresenter;
import com.qlvb.vnpt.botttt.schedule.ui.view.LoginView;
import com.qlvb.vnpt.botttt.schedule.ui.view.UserView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

public class LoginActivity extends BaseActivity implements LoginView, UserView, Validator.ValidationListener {
    private boolean isValidateLogin;
    private Validator validator;

    //    private Realm realm;
    @BindView(R.id.btnDangNhap)

    RelativeLayout btnDangNhap;
    @BindView(R.id.ckGhiNhoTaiKhoan)
    AnimCheckBox ckGhiNhoTaiKhoan;
    @NotEmpty(messageResId = R.string.USERNAME_REQUIRED)
    @Length(max = 30, messageResId = R.string.USERNAME_INVALID_LENGTH)
    @BindView(R.id.txtUserName)
    EditText txtUsername;

    @NotEmpty(messageResId = R.string.PASSWORD_REQUIRED)
    @Length(min = 8, messageResId = R.string.PASSWORD_INVALID_LENGTH)
    @BindView(R.id.txtPassword)
    EditText txtPassword;

    @BindView(R.id.txtGhiNhoTaiKhoan)
    TextView txtGhiNhoTaiKhoan;

    private ProgressDialog progressDialog;
    @Inject
    UserPresenter userPresenter;
    @Inject
    LoginPresenter loginPresenter;
    private Realm realm;
    @Inject
    LoginUserCookies loginUserCookies;
    @Inject
    AppState appState;
    List<UnitObject> dataResponse = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        userPresenter.setView(this);
        loginPresenter.setView(this);
        loginPresenter.onViewCreate();
        userPresenter.onViewCreate();
        validator = new Validator(this);
        validator.setValidationListener(this);
        this.realm = RealmController.with(this).getRealm();
        //ghi nho pass
        if(txtUsername.getText()!= null && txtPassword.getText() != null && ckGhiNhoTaiKhoan.isChecked()==true )
        {
            txtUsername.setText(appState.getState(AppState.PREF_KEY_ACCOUNT_USER, ""));
            txtPassword.setText(appState.getState(AppState.PREF_KEY_PASSWORD_USER, ""));
        }
        //check logout
        if(appState.getState(AppState.PREF_IS_LOGOUT_USER, false)){
            checkDataSave();
        }
    }

    private void checkDataSave() {
        if (appState.getState(AppState.PREF_AUTO_LOGIN_USER, false)) {
            txtUsername.setText(appState.getState(AppState.PREF_KEY_ACCOUNT_USER, ""));
            txtPassword.setText(appState.getState(AppState.PREF_KEY_PASSWORD_USER, ""));
            txtGhiNhoTaiKhoan.setSelected(appState.getState(AppState.PREF_AUTO_LOGIN_USER, false));
            validator.validate();
            if (isValidateLogin) {
                showProgressBar();
                loginPresenter.getLogin(txtUsername.getText().toString(), txtPassword.getText().toString(), "stringToken");
            }
        } else {
            txtUsername.setText("");
            txtPassword.setText("");
            txtGhiNhoTaiKhoan.setSelected(false);
        }
    }

    private void setFont() {
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/SFUFuturaBook.TTF");
        txtUsername.setTypeface(face);
        txtPassword.setTypeface(face);
        txtGhiNhoTaiKhoan.setTypeface(face);
    }

    @OnClick({R.id.btnDangNhap})
    public void clickEvent(View view) {
        switch (view.getId()) {
            case R.id.btnDangNhap:
                validator.validate();
                if (isValidateLogin) {
                    showProgressBar();
                    loginPresenter.getLogin(txtUsername.getText().toString(), txtPassword.getText().toString(), "stringToken");
                }
                break;
        }

    }

    @Override
    public void onValidationSucceeded() {
        isValidateLogin = true;
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        isValidateLogin = false;
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                AlertDialogManager.showAlertDialog(this, getString(R.string.LOGIN_TITLE_ERROR), message, true, AlertDialogManager.ERROR);
            }
        }
    }

    @Override
    public void onGetloginSuccess(LoginObject dataResponse) {
//        hideProgressBar();
        Log.d("Thao", dataResponse.username);
        loginUserCookies.putAuthen(dataResponse.getToken());
        appState.setState(AppState.PREF_KEY_UNIT_ID_LOGIN_USER, dataResponse.getUnitId());

//        appState.setState();
        if (ckGhiNhoTaiKhoan.isChecked()) {
            appState.setState(AppState.PREF_IS_LOGOUT_USER, true);
            appState.setState(AppState.PREF_KEY_ACCOUNT_USER, txtUsername.getText().toString().trim());
            appState.setState(AppState.PREF_KEY_PASSWORD_USER, txtPassword.getText().toString().trim());
            appState.setState(AppState.PREF_AUTO_LOGIN_USER, ckGhiNhoTaiKhoan.isChecked());
        }
        getListUnits();
    }

    @Override
    public void onGetLoginFailed(String message) {
        hideProgressBar();
        Log.d("Thao", message);
    }

    @Override
    public void onGetLoginError(Throwable e) {
        hideProgressBar();
        Log.d("Thao", e.toString());
    }
//
//    @Override
//    public void onLoadTokenDevUser(String tokenDev) {
//
//    }

    @Override
    public void onGetListUserSuccess(List<UserObject> dataResponse) {
//        hideProgressBar();
        if (dataResponse != null && dataResponse.size() > 0) {
            realm.beginTransaction();
            realm.insertOrUpdate(dataResponse);
            realm.commitTransaction();
        }
        getListGroup();
    }

    @Override
    public void onGetListFailed(String message) {
        hideProgressBar();
        Log.d("onGetListFailed", message);
    }

    @Override
    public void onGetListUnitSuccess(List<UnitObject> dataResponse) {
//        hideProgressBar();
        if (dataResponse != null && dataResponse.size() > 0) {
//            this.dataResponse = dataResponse;
            realm.beginTransaction();
            realm.insertOrUpdate(dataResponse);
            realm.commitTransaction();
        }
        getListUser();
//
    }

    @Override
    public void onGetListGroupSuccess(List<GroupObject> dataResponse) {
        hideProgressBar();
        if (dataResponse != null && dataResponse.size() > 0) {
            realm.beginTransaction();
            realm.insertOrUpdate(dataResponse);
            realm.commitTransaction();
        }
        startActivity(new Intent(this, MainActivity.class));
//        getListMinisters();
    }

    @Override
    public void onGetListMinisterSuccess(List<MinisterObject> dataResponse) {
        hideProgressBar();
        if (dataResponse != null && dataResponse.size() > 0) {
            realm.beginTransaction();
            realm.insertOrUpdate(dataResponse);
            realm.commitTransaction();
        }
        startActivity(new Intent(this, MainActivity.class));
//        this.finish();
    }

    @Override
    public void onGetListError(Throwable e) {
        hideProgressBar();
        Log.d("onGetListError", e.toString());
    }

    private void getListUser() {
        showProgressBar();
        userPresenter.getListUser();
    }

    private void getListUnits() {
        showProgressBar();
        userPresenter.getListUnit();
    }

    private void getListGroup() {
        showProgressBar();
        userPresenter.getListGroup();
    }

    private void getListMinisters() {
        showProgressBar();
        userPresenter.getListMinister();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        userPresenter.onViewDestroy();
    }
}
