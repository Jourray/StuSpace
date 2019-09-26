package com.example.team.plugutils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.team.mvp.HomeModle;

/**
 * author : ZhiG
 * e-mail : 1120121044@163.com
 * date   : 2019/9/2619:35
 * desc   :
 * package: StuSpace:
 */
public class RlvHomeDrawerLayoutAdapter extends RecyclerView.Adapter<RlvHomeDrawerLayoutAdapter.ViewHolder> {
    private Context context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public interface HomeOnClickListener {
        void homeOnClick(int position);
    }

    private HomeOnClickListener homeOnClickListener;

    public void setHomeOnClickListener(HomeOnClickListener homeOnClickListener) {
        this.homeOnClickListener = homeOnClickListener;
    }
}
