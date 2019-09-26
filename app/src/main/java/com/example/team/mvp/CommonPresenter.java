package com.example.team.mvp;

import com.example.team.base.BasePresenter;

/**
 * author : ZhiG
 * e-mail : 1120121044@163.com
 * date   : 2019/9/2619:04
 * desc   :
 * package: StuSpace:
 */
public class CommonPresenter extends BasePresenter implements ICommonPresenter, ICommonView {


    @Override
    public void getData(int whichApi, Object[] t) {
        if (getModel() != null) {
            getModel().getData(this, whichApi, t);
        }
    }

    @Override
    public void onError(int whichApi, Throwable e) {
        if (getView() != null) {
            getView().onError(whichApi, e);
        }
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        if (getView()!=null) {
            getView().onResponse(whichApi,t);
        }
    }
}
