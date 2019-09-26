package com.example.team.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.team.R;

/**
 * author : ZhiG
 * e-mail : 1120121044@163.com
 * date   : 2019/9/2610:32
 * desc   :
 * package: StuSpace:
 */
public class RlvHomeDrawerLayoutAdapter extends RecyclerView.Adapter<RlvHomeDrawerLayoutAdapter.ViewHolder> {
    private Context context;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.activity_main, null);
        return new ViewHolder(view);
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
}
