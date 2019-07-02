package com.qlvb.vnpt.botttt.schedule.ui.presenter.document;

import com.qlvb.vnpt.botttt.schedule.ui.presenter.Presenter;
import com.qlvb.vnpt.botttt.schedule.ui.view.document.LuuVanBanView;

/**
 * Created by THAOPX on 8/28/2018.
 */
public interface LuuVanBanPresenter extends Presenter<LuuVanBanView>{
    void saveDocument(String token, String id, Integer type);
    void cancelSaveDocument(String token, String id, Integer type);
}
