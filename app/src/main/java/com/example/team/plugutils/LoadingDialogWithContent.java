package com.example.team.plugutils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.team.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * author : ZhiG
 * e-mail : 1120121044@163.com
 * date   : 2019/9/2616:55
 * desc   :
 * package: StuSpace:
 */
public class LoadingDialogWithContent extends Dialog {
    String content;
    private ProgressImageView mProgerssView;
    private TextView mTvconten;

    public LoadingDialogWithContent(@NonNull Context context, String content) {
        super(context, R.style.DialogStyle);
        this.content = content;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_progress_content);
        mProgerssView = findViewById(R.id.view_list_empty_progress);
        mTvconten = findViewById(R.id.tv_content);
        mTvconten.setText(content);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (mProgerssView != null) {
            mProgerssView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mProgerssView != null) {
            mProgerssView.clearAnimation();
        }
    }
}
