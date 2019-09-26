package com.example.team.plugutils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import retrofit2.http.PUT;

/**
 * author : ZhiG
 * e-mail : 1120121044@163.com
 * date   : 2019/9/2614:29
 * desc   :
 * package: StuSpace:
 */
public class NetStatusBroadCast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            int networkState = NetworkUtils.getNetworkState(context);
            if (netStatusListener!=null) {
                netStatusListener.onNetChanged(networkState);
            }

        }

    }

    public interface NetStatusListener {
        void onNetChanged(int state);
    }

    private NetStatusListener netStatusListener;

    public void setNetStatusListener(NetStatusListener netStatusListener) {
        this.netStatusListener = netStatusListener;
    }
}
