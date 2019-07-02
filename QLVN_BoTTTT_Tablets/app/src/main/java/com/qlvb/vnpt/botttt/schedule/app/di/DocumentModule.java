package com.qlvb.vnpt.botttt.schedule.app.di;

import com.qlvb.vnpt.botttt.schedule.domain.interactor.document.DocumentInteractor;
import com.qlvb.vnpt.botttt.schedule.domain.interactor.document.DocumentInteractorImpl;
import com.qlvb.vnpt.botttt.schedule.domain.repository.api.DocumentApi;
import com.qlvb.vnpt.botttt.schedule.ui.presenter.document.ChuyenVanBanPresenter;
import com.qlvb.vnpt.botttt.schedule.ui.presenter.document.ChuyenVanBanPresenterImpl;
import com.qlvb.vnpt.botttt.schedule.ui.presenter.document.DetailDocumentPresenter;
import com.qlvb.vnpt.botttt.schedule.ui.presenter.document.DetailDocumentPresenterImpl;
import com.qlvb.vnpt.botttt.schedule.ui.presenter.document.InstallFilePresenter;
import com.qlvb.vnpt.botttt.schedule.ui.presenter.document.InstallFilePresenterImpl;
import com.qlvb.vnpt.botttt.schedule.ui.presenter.document.ListDocumentPresenter;
import com.qlvb.vnpt.botttt.schedule.ui.presenter.document.ListDocumentPresenterImpl;
import com.qlvb.vnpt.botttt.schedule.ui.presenter.document.ListFileRelatedPresenter;
import com.qlvb.vnpt.botttt.schedule.ui.presenter.document.ListFileRelatedPresenterImpl;
import com.qlvb.vnpt.botttt.schedule.ui.presenter.document.LuanChuyenVanBanPresenter;
import com.qlvb.vnpt.botttt.schedule.ui.presenter.document.LuanChuyenVanBanPresenterImpl;
import com.qlvb.vnpt.botttt.schedule.ui.presenter.document.LuuVanBanPresenter;
import com.qlvb.vnpt.botttt.schedule.ui.presenter.document.LuuVanBanPresenterImpl;
import com.qlvb.vnpt.botttt.schedule.ui.presenter.document.SignFilePresenter;
import com.qlvb.vnpt.botttt.schedule.ui.presenter.document.SignFilePresenterImpl;


import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module(complete = false, library = true)
public class DocumentModule {
    @Provides
    DocumentInteractor provideDocumentInteractor(DocumentInteractorImpl documentInteractor) {
        return documentInteractor;
    }

    @Provides
    DocumentApi provideDocumentApi(Retrofit.Builder retrofitBuilder) {
        return retrofitBuilder.build().create(DocumentApi.class);
    }

    @Provides
    ListDocumentPresenter listDocumentPresenter(ListDocumentPresenterImpl listDocumentPresenter) {
        return listDocumentPresenter;
    }

    @Provides
    DetailDocumentPresenter detailDocumentPresenter(DetailDocumentPresenterImpl detailDocumentPresenter) {
        return detailDocumentPresenter;
    }

    @Provides
    ChuyenVanBanPresenter sendDocumentPresenter(ChuyenVanBanPresenterImpl chuyenVanBanPresenter){
        return chuyenVanBanPresenter;
    }

    @Provides
    LuanChuyenVanBanPresenter luanChuyenVanBanPresenter(LuanChuyenVanBanPresenterImpl luanChuyenVanBanPresenter){
        return luanChuyenVanBanPresenter;
    }

    @Provides
    InstallFilePresenter installFilePresenter(InstallFilePresenterImpl installFilePresenter) {
        return installFilePresenter;
    }

    @Provides
    SignFilePresenter signFilePresenter(SignFilePresenterImpl signFilePresenter) {
        return signFilePresenter;
    }

    @Provides
    LuuVanBanPresenter luuVanBanPresenter(LuuVanBanPresenterImpl luuVanBanPresenter){
        return luuVanBanPresenter;
    }

    @Provides
    ListFileRelatedPresenter listFileRelatedPresenter(ListFileRelatedPresenterImpl listFileRelatedPresenter) {
        return listFileRelatedPresenter;
    }
}
