package com.example.team.base;

import android.content.IntentFilter;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.team.R;
import com.example.team.api.MyApplication;
import com.example.team.plugutils.LoadingDialogWithContent;
import com.example.team.plugutils.NetStatusBroadCast;
import com.example.team.plugutils.NetworkUtils;
import com.example.team.plugutils.NormalConfig;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import javax.security.auth.login.LoginException;

import androidx.annotation.Nullable;

import static com.example.team.plugutils.DensityUtil.px2dp;
import static com.example.team.plugutils.NetworkUtils.NETWORK_MOBILE;
import static com.example.team.plugutils.NetworkUtils.NETWORK_NONE;
import static com.example.team.plugutils.NetworkUtils.NETWORK_WIFI;

/**
 * author : ZhiG
 * e-mail : 1120121044@163.com
 * date   : 2019/9/2614:51
 * desc   :
 * package: StuSpace:
 */
public class BaseActivity extends AppCompatActivity implements NetStatusBroadCast.NetStatusListener {

    private MyApplication application;
    private LoadingDialogWithContent loadingDialogWithContent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        showLog("类名" + this.getClass().getSimpleName());
        application = (MyApplication) getApplication();
        loadingDialogWithContent = new LoadingDialogWithContent(this, getString(R.string.loading));

    }

    public void initRecycleView(RecyclerView recyclerView, RefreshLayout refreshLayout) {
        if (refreshLayout != null) {
            refreshLayout.setHeaderHeight(px2dp(120));
            refreshLayout.setFooterHeight(px2dp(100));
            refreshLayout.setOnRefreshListener(new OnRefreshListener() {
                @Override
                public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                    refresh();
                }
            });
            refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
                @Override
                public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                    loadMore();
                }
            });
        }


    }

    public void registerNetWorkStatus() {
        IntentFilter intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        NetStatusBroadCast netStatusBroadCast = new NetStatusBroadCast();
        netStatusBroadCast.setNetStatusListener(this);
        registerReceiver(netStatusBroadCast, intentFilter);
    }

    private void loadMore() {

    }

    private void refresh() {

    }

    public void showLoadingDialog() {
        if (!loadingDialogWithContent.isShowing()) {
            loadingDialogWithContent.show();
        }
    }

    public void hideLoadingDialog() {
        if (loadingDialogWithContent.isShowing()) {
            loadingDialogWithContent.dismiss();
        }
    }

    @Override
    public void onNetChanged(int state) {
        if (state == NETWORK_MOBILE || state == NETWORK_WIFI) {
            onNetConnected();
        } else if (state == NETWORK_NONE) {
            onNetDisConnected();
        }
    }

    private void onNetDisConnected() {


    }

    private void onNetConnected() {

    }

    public void showLog(String content) {
        Log.e(NormalConfig.log1, content);
    }

    public void showLog(boolean content) {
        Log.e(NormalConfig.log1, content + "");
    }

    public void showToast(String content) {
        Toast.makeText(getApplicationContext(), content, Toast.LENGTH_SHORT).show();
    }

    public void showLongToast(String content) {
        Toast.makeText(getApplicationContext(), content, Toast.LENGTH_LONG).show();
    }
}
