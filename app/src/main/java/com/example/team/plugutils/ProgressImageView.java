package com.example.team.plugutils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.team.R;

import androidx.annotation.Nullable;

/**
 * author : ZhiG
 * e-mail : 1120121044@163.com
 * date   : 2019/9/2616:56
 * desc   :
 * package: StuSpace:
 */
@SuppressLint("AppCompatCustomView")
public class ProgressImageView extends ImageView {

    private Animation animation;

    public ProgressImageView(Context context) {
        super(context);
    }

    public ProgressImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ProgressImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        animation = AnimationUtils.loadAnimation(getContext(), R.anim.progress);
        if (getVisibility() == VISIBLE) {
            setAnimation(animation);
        }
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        if (visibility == VISIBLE) {
            startAnimation(animation);
        } else {
            clearAnimation();
        }
    }
}
