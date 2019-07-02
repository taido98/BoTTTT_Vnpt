package com.qlvb.vnpt.botttt.schedule.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.barteksc.pdfviewer.PDFView;
import com.qlvb.vnpt.botttt.schedule.R;
import com.qlvb.vnpt.botttt.schedule.app.BaseActivity;
import com.qlvb.vnpt.botttt.schedule.app.utils.AlertDialogManager;
import com.qlvb.vnpt.botttt.schedule.domain.model.response.DetailDocumentResponse;
import com.qlvb.vnpt.botttt.schedule.ui.presenter.document.InstallFilePresenter;
import com.qlvb.vnpt.botttt.schedule.ui.view.document.InstallFileView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;

public class ViewPDFActivity extends BaseActivity implements InstallFileView {

    public static Intent java;
    @BindView(R.id.pdfView)
    PDFView pdfView;
    @Inject
    InstallFilePresenter installFilePresenter;
    @BindView(R.id.ic_list)
    ImageView icList;
    @BindView(R.id.ic_print)
    ImageView icPrint;
    @BindView(R.id.tv_doc_name)
    TextView tvDocName;
    @BindView(R.id.ic_rotate_left)
    ImageView icRotateLeft;
    @BindView(R.id.ic_rotate_right)
    ImageView icRotateRight;
    @BindView(R.id.ic_exit)
    ImageView icExit;
    @BindView(R.id.btn_ky)
    ImageView btnKy;
    @BindView(R.id.btn_chuyen_file)
    ImageView btnChuyenFile;


    private String fileDownloadDir = "";

    private String fileName = "";
    private int fileId;
    private long fileDownloadSize = 0;
    private ArrayList<DetailDocumentResponse.FileDinhKem> listFileId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pdf);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initData() {
        listFileId = new ArrayList<>();
        fileName = getIntent().getExtras().getString("FILE_NAME");
        fileId = getIntent().getExtras().getInt("FILE_ID");
        fileName = fileName.replaceAll(".docx", ".pdf");
        fileName = fileName.replaceAll(".DOCX", ".pdf");
        fileName = fileName.replaceAll(".doc", ".pdf");
        fileName = fileName.replaceAll(".DOC", ".pdf");

        try {
            listFileId = (ArrayList<DetailDocumentResponse.FileDinhKem>)getIntent().getSerializableExtra("LIST_FILE_ID");;
            if(listFileId.size() > 1){
                btnChuyenFile.setVisibility(View.VISIBLE);
            }else {
                btnChuyenFile.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        tvDocName.setText(fileName);
        if (fileId != 0) {
            showProgressBar();
            installFilePresenter.getFile(fileId);
        }
    }

    private void initView() {
        installFilePresenter.setView(this);
        installFilePresenter.onViewCreate();
    }


    @Override
    public void onInstallFileSuccess(ResponseBody object) {
        hideProgressBar();
        ResponseBody responseBody = (ResponseBody) object;
        final File fileSave = writeResponseBodyToDisk(object, fileName);
        long fileLength = fileSave.length();
        if (fileSave != null) {
            pdfView.fromFile(fileSave)// all pages are displayed by default
                    .enableSwipe(true) // allows to block changing pages using swipe
                    .swipeHorizontal(false)
                    .enableDoubletap(true)
                    .defaultPage(0)
                    .enableAnnotationRendering(false) // render annotations (such as comments, colors or forms)
                    .password(null)
                    .scrollHandle(null)
                    .enableAntialiasing(true) // improve rendering a little bit on low-res screens
                    // spacing between pages in dp. To define spacing color, set view background
                    .spacing(0)
                    .autoSpacing(false) // add dynamic spacing to fit each page on its own on the screen
                    .pageSnap(true) // snap pages to screen boundaries
                    .pageFling(false) // make a fling change only a single page like ViewPager
                    .nightMode(false) // toggle night mode
                    .load();
        } else {
            AlertDialogManager.showAlertDialog(this, this.getString(R.string.DOWNLOAD_TITLE_ERROR), this.getString(R.string.DOWNLOAD_FILE_ERROR), true, AlertDialogManager.ERROR);
            fileDownloadSize = 0;
            fileDownloadDir = "";
        }
    }

    @Override
    public void onInstallFileFailed(String message) {
        hideProgressBar();
        showToast(message);
    }

    @Override
    public void onInstallFileError(Throwable e) {
        hideProgressBar();
        showToast(e.toString());
    }

    private File writeResponseBodyToDisk(ResponseBody body, String filename) {
        try {
            // todo change the file location/name according to your needs
            File fileDownload = new File(this.getExternalFilesDir(null) + File.separator + filename);
            fileDownloadDir = this.getExternalFilesDir(null) + File.separator + filename;
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                byte[] fileReader = new byte[4096];
                inputStream = body.byteStream();
                outputStream = new FileOutputStream(fileDownload);
                while (true) {
                    int read = inputStream.read(fileReader);
                    if (read == -1) {
                        break;
                    }
                    outputStream.write(fileReader, 0, read);
                }
                outputStream.flush();
                return fileDownload;
            } catch (IOException e) {
                Log.e("write file error:", e.toString());
                return null;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return null;
        }
    }

    @OnClick({R.id.ic_list, R.id.ic_print, R.id.ic_rotate_left, R.id.ic_rotate_right, R.id.ic_exit, R.id.btn_ky, R.id.btn_chuyen_file})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ic_list:
                break;
            case R.id.ic_print:
                break;
            case R.id.ic_rotate_left:

                break;
            case R.id.ic_rotate_right:
                break;
            case R.id.ic_exit:
                onBackPressed();
                break;
            case R.id.btn_ky:
                onBackPressed();
                break;
            case R.id.btn_chuyen_file:
                if(listFileId != null && listFileId.size() > 1){
                    for(int i = 0; i < listFileId.size(); i++){
                        if(fileId != listFileId.get(i).getId()){
                            Intent intent = new Intent(this, ViewPDFActivity.class);
                            intent.putExtra("FILE_NAME", listFileId.get(i).getName());
                            intent.putExtra("FILE_ID", listFileId.get(i).getId());
                            intent.putExtra("LIST_FILE_ID", (Serializable) listFileId);
                            startActivity(intent);
                            this.finish();
                        }
                    }
                }
                break;
        }
    }

}
