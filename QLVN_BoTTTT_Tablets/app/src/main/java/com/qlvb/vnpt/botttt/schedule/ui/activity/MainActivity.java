package com.qlvb.vnpt.botttt.schedule.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mikepenz.crossfader.Crossfader;
import com.mikepenz.crossfader.util.UIUtils;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.MiniDrawer;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;
import com.qlvb.vnpt.botttt.schedule.R;
import com.qlvb.vnpt.botttt.schedule.app.BaseActivity;
import com.qlvb.vnpt.botttt.schedule.app.CrossfadeWrapper;
import com.qlvb.vnpt.botttt.schedule.app.evenBus.EvenChangeGoCome;
import com.qlvb.vnpt.botttt.schedule.app.utils.AppDef;
import com.qlvb.vnpt.botttt.schedule.domain.model.GetCountResponse;
import com.qlvb.vnpt.botttt.schedule.domain.repository.AppState;
import com.qlvb.vnpt.botttt.schedule.domain.repository.LoginUserCookies;
import com.qlvb.vnpt.botttt.schedule.ui.fragment.FragmentRightContentComming;
import com.qlvb.vnpt.botttt.schedule.ui.presenter.MainPresenter;
import com.qlvb.vnpt.botttt.schedule.ui.view.MainView;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements MainView {

    @BindView(R.id.ll_document_comming)
    LinearLayout llDocumentComming;
    @BindView(R.id.ll_document_going)
    LinearLayout llDocumentGoing;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title_left)
    TextView toolbarTitleLeft;
    @BindView(R.id.toolbar_title_right)
    TextView toolbarTitleRight;

    @BindView(R.id.iv_document_going)
//    @BindView(R.id.crossfade_content)
            ImageView ivDocumentGoing;
    @BindView(R.id.iv_document_comming)
    ImageView ivDocumentComming;
    @BindView(R.id.ll_menu_left)
    LinearLayout llMenuLeft;

    @BindView(R.id.crossfade_content)
    LinearLayout crossfade_content;
//    TextView crossfade_content;

    private Drawer resultLeft = null;
    private MiniDrawer miniResultLeft = null;
    private Crossfader crossFaderLeft;
    private String nameVanBan = "";
    private String typeVanBan = "";
    private String titleRight = "";

    // fragment
    FragmentRightContentComming fragmentRightContentComming;

    @Inject
    MainPresenter mainPresenter;

    @Inject
    LoginUserCookies loginUserCookies;

    @Inject
    AppState appState;

    GetCountResponse.Data dataResponse = new GetCountResponse.Data();

    Bundle savedInstanceState;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        // Handle Toolbar
        setSupportActionBar(toolbar);

        // dem so van ban
        mainPresenter.setView(this);
        mainPresenter.onViewCreate();
        mainPresenter.getCount(loginUserCookies.getAuthent());
        this.savedInstanceState = savedInstanceState;

        toolbarTitleLeft.setText("Văn bản đi");
        toolbarTitleRight.setText(getString(R.string.drawer_item_go_watting));
        EventBus.getDefault().postSticky(new EvenChangeGoCome(0));

//        //set the back arrow in the toolbar
//        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//        getSupportActionBar().setTitle(R.string.drawer_item_mini_drawer);

//        createMenuLeft(savedInstanceState);
        initView();
        initType();
        llDocumentGoing.setSelected(true);
        llDocumentComming.setSelected(false);
        llDocumentGoing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Thay doi title
                llDocumentGoing.setSelected(true);
                llDocumentComming.setSelected(false);
                toolbarTitleLeft.setText("Văn bản đi");
                EventBus.getDefault().postSticky(new EvenChangeGoCome(0));

                resultLeft.removeAllItems();
                resultLeft.addItem(new PrimaryDrawerItem().withName(R.string.drawer_item_go_watting)
                        .withIcon(R.mipmap.ic_going_watting)
                        .withSelectedIcon(R.mipmap.ic_going_watting_press)
                        .withBadge(dataResponse.getVanBanDi()).withIdentifier(1));
                resultLeft.addItem(new PrimaryDrawerItem().withName(R.string.drawer_item_go_signed)
                        .withSelectedIcon(R.mipmap.ic_going_signed_press)
                        .withBadge(dataResponse.getDiDaKyDuyet())
                        .withIcon(R.mipmap.ic_going_signed));
                resultLeft.addItem(new PrimaryDrawerItem().withName(R.string.drawer_item_go_refuse)
                        .withIcon(R.mipmap.ic_going_refuse)
                        .withBadge(dataResponse.getDiTuChoiKy())
                        .withSelectedIcon(R.mipmap.ic_going_refuse_press)
                        .withIdentifier(2));
                resultLeft.addItem(new PrimaryDrawerItem().withName(R.string.drawer_item_go_all)
                        .withIcon(R.mipmap.ic_going_all)
                        .withSelectedIcon(R.mipmap.ic_going_all_press)
                        .withBadge(dataResponse.getDiTatCa()).withIdentifier(3));
                resultLeft.setSelection(1);
                miniResultLeft.createItems();

            }
        });
        llDocumentComming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llDocumentGoing.setSelected(false);
                llDocumentComming.setSelected(true);
                toolbarTitleLeft.setText("Văn bản đến");
                EventBus.getDefault().postSticky(new EvenChangeGoCome(1));

                resultLeft.removeAllItems();
                resultLeft.addItem(new PrimaryDrawerItem().withName(R.string.drawer_item_come_watting)
                        .withIcon(R.mipmap.ic_comming_watting)
                        .withSelectedIcon(R.mipmap.ic_comming_watting_press)
                        .withBadge(dataResponse.getVanBanDen())
                        .withIdentifier(1));
                resultLeft.addItem(new PrimaryDrawerItem().withName(R.string.drawer_item_come_processed)
                        .withIcon(R.mipmap.ic_comming_processed)
                        .withBadge(dataResponse.getDenDaXuLy())
                        .withSelectedIcon(R.mipmap.ic_comming_processed_press));
                resultLeft.addItem(new PrimaryDrawerItem().withName(R.string.drawer_item_come_saved)
                        .withIcon(R.mipmap.ic_comming_saved)
                        .withSelectedIcon(R.mipmap.ic_comming_saved_press)
                        .withBadge(dataResponse.getDenDaLuu())
                        .withIdentifier(2));
                resultLeft.addItem(new PrimaryDrawerItem().withName(R.string.drawer_item_come_all)
                        .withIcon(R.mipmap.ic_comming_all)
                        .withSelectedIcon(R.mipmap.ic_comming_all_press)
                        .withBadge(dataResponse.getDenTatCa())
                        .withIdentifier(3));
                resultLeft.setSelection(1);
                miniResultLeft.createItems();

            }
        });

    }

    private void initType() {

    }

    private void initView() {
        fragmentRightContentComming = new FragmentRightContentComming();
        nameVanBan = getString(R.string.str_van_ban_cho_ky_duyet);
        replaceFramgment(fragmentRightContentComming, AppDef.VAN_BAN_DI);
    }

    private void createMenuLeft(Bundle savedInstanceState) {
        //first create the main drawer (this one will be used to add the second drawer on the other side)
        resultLeft = new DrawerBuilder()
                .withActivity(this)
                .withTranslucentStatusBar(false)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.drawer_item_go_watting)
                                .withSelectedIcon(R.mipmap.ic_going_watting_press)
                                .withIcon(R.mipmap.ic_going_watting)
                                .withBadge(dataResponse.getVanBanDi())
                                .withIdentifier(1),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_go_signed)
                                .withIcon(R.mipmap.ic_going_signed)
                                .withBadge(dataResponse.getDiDaKyDuyet())
                                .withSelectedIcon(R.mipmap.ic_going_signed_press),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_go_refuse).withIcon(R.mipmap.ic_going_refuse)
                                .withSelectedIcon(R.mipmap.ic_going_refuse_press)
                                .withBadge(dataResponse.getDiTuChoiKy()).withIdentifier(2),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_go_all).withIcon(R.mipmap.ic_going_all)
                                .withSelectedIcon(R.mipmap.ic_going_all_press)
                                .withBadge(dataResponse.getDiTatCa()).withIdentifier(3)
                ) // add the items we want to use with our Drawer
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem instanceof Nameable) {
//                            Toast.makeText(MainActivity.this, ((Nameable) drawerItem).getName().getText(MainActivity.this), Toast.LENGTH_SHORT).show();
                            nameVanBan = ((Nameable) drawerItem).getName().getText(MainActivity.this);
                            fragmentRightContentComming = new FragmentRightContentComming();
                            checkTypeVanBan();
                            replaceFramgment(fragmentRightContentComming, typeVanBan);
                            toolbarTitleRight.setText(titleRight);
                        }
                        return false;
                    }
                })
                .withGenerateMiniDrawer(true)
                .withSavedInstance(savedInstanceState)
                // build only the view of the Drawer (don't inflate it automatically in our layout which is done with .build())
                .buildView();

        //the MiniDrawer is managed by the Drawer and we just get it to hook it into the Crossfader
        miniResultLeft = resultLeft.getMiniDrawer();

        //get the widths in px for the first and second panel
        int firstWidth = (int) UIUtils.convertDpToPixel(300, this);
        int secondWidth = (int) UIUtils.convertDpToPixel(72, this);

        //create and build our crossfader (see the MiniDrawer is also builded in here, as the build method returns the view to be used in the crossfader)
        //the crossfader library can be found here: https://github.com/mikepenz/Crossfader
        crossFaderLeft = new Crossfader()
                .withContent(findViewById(R.id.fragmentContainer))
                .withFirst(resultLeft.getSlider(), firstWidth)
                .withSecond(miniResultLeft.build(this), secondWidth)
                .withSavedInstance(savedInstanceState)
                .build();

        //define the crossfader to be used with the miniDrawer. This is required to be able to automatically toggle open / close
        miniResultLeft.withCrossFader(new CrossfadeWrapper(crossFaderLeft));
        //define a shadow (this is only for normal LTR layouts if you have a RTL app you need to define the other one
        crossFaderLeft.getCrossFadeSlidingPaneLayout().setShadowResourceLeft(R.drawable.material_drawer_shadow_left);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Only if you need to restore open/close state when
        // the orientation is changed
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Only if you need to restore open/close state when
        // the orientation is changed
    }

    private void replaceFramgment(Fragment fragment, String typeVanBan) {
        FragmentTransaction transList = getSupportFragmentManager()
                .beginTransaction();
        transList.replace(R.id.fragmentContainer, fragment);
        Bundle bundle = new Bundle();
        bundle.putString("TYPE_VAN_BAN", typeVanBan);
        bundle.putString("NAME_VAN_BAN", nameVanBan);
        fragment.setArguments(bundle);
        transList.commit();
    }

    private void checkTypeVanBan() {
        if (nameVanBan.equals(getString(R.string.drawer_item_come_watting))) {
            typeVanBan = AppDef.VAN_BAN_DEN;
            titleRight = getString(R.string.drawer_item_come_watting);
        } else if (nameVanBan.equals(getString(R.string.drawer_item_come_processed))) {
            typeVanBan = AppDef.VAN_BAN_DEN_DA_XU_LY;
            titleRight = getString(R.string.drawer_item_come_processed);
        } else if (nameVanBan.equals(getString(R.string.drawer_item_come_saved))) {
            typeVanBan = AppDef.VAN_BAN_DEN_DA_LUU;
            titleRight = getString(R.string.drawer_item_come_saved);
        } else if (nameVanBan.equals(getString(R.string.drawer_item_come_all))) {
            typeVanBan = AppDef.VAN_BAN_DEN_TAT_CA;
            titleRight = getString(R.string.drawer_item_come_all);
        } else if (nameVanBan.equals(getString(R.string.drawer_item_go_watting))) {
            typeVanBan = AppDef.VAN_BAN_DI;
            titleRight = getString(R.string.drawer_item_go_watting);
        } else if (nameVanBan.equals(getString(R.string.drawer_item_go_signed))) {
            typeVanBan = AppDef.VAN_BAN_DI_DA_KY_DUYET;
            titleRight = getString(R.string.drawer_item_go_signed);
        } else if (nameVanBan.equals(getString(R.string.drawer_item_go_refuse))) {
            typeVanBan = AppDef.VAN_BAN_DI_TU_CHOI_KY;
            titleRight = getString(R.string.drawer_item_go_refuse);
        } else if (nameVanBan.equals(getString(R.string.drawer_item_go_all))) {
            typeVanBan = AppDef.VAN_BAN_DI_TAT_CA;
            titleRight = getString(R.string.drawer_item_go_all);
        }
    }

    @Override
    public void onGetCountSuccess(GetCountResponse.Data dataResponse) {
        this.dataResponse = dataResponse;

        createMenuLeft(savedInstanceState);
    }

    @Override
    public void onLogOutSuccess() {
        hideProgressBar();
        appState.setState(AppState.PREF_AUTO_LOGIN_USER, true);
        appState.setState(AppState.PREF_IS_LOGOUT_USER, false);
        startActivity(new Intent(this, LoginActivity.class));
        MainActivity.this.finish();
    }

    @Override
    public void onGetCountFailed(String message) {
        hideProgressBar();
    }

    @Override
    public void onGetCountError(Throwable e) {
        hideProgressBar();
    }

    @OnClick(R.id.iv_logout)
    public void OnClickListener(View v) {
        ShowAlertLogout();
    }

    private void ShowAlertLogout() {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Thông báo");
        alertDialog.setMessage("Bạn thật sự muốn đăng xuất khỏi tài khoản ?");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Đồng ý",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        showProgressBar();
                        mainPresenter.getLogUser();
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Hủy bỏ",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}
