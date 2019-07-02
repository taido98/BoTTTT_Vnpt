package com.qlvb.vnpt.botttt.schedule.ui.view.document;
import com.qlvb.vnpt.botttt.schedule.ui.view.View;

import okhttp3.ResponseBody;

public interface InstallFileView extends View {
    void onInstallFileSuccess(ResponseBody object);
    void onInstallFileFailed(String message);
    void onInstallFileError(Throwable e);
}
