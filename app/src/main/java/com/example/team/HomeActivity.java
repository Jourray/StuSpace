package com.example.team;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.team.base.BaseMvpActivity;
import com.example.team.mvp.CommonPresenter;
import com.example.team.mvp.HomeModle;
import com.example.team.plugutils.ApiConfig;
import com.example.team.plugutils.RlvHomeDrawerLayoutAdapter;

public class HomeActivity extends BaseMvpActivity<HomeModle> implements RlvHomeDrawerLayoutAdapter.HomeOnClickListener {

    @Override
    protected void initData() {
        registerNetWorkStatus();
      // mpresenter.getData(ApiConfig.HOME_DRAWERLAYOUT); ????????????????
    }

    @Override
    protected void initView() {
        initToolBar();
    }

    private void initToolBar() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected HomeModle getModel() {
        return new HomeModle();
    }

    @Override
    protected CommonPresenter getPresenter() {
        return new CommonPresenter();
    }

    @Override
    public void onError(int whichApi, Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {

    }

    @Override
    public void homeOnClick(int position) {

    }

}
