package com.example.team.plugutils;

import android.content.res.Resources;

/**
 * author : ZhiG
 * e-mail : 1120121044@163.com
 * date   : 2019/9/2618:34
 * desc   :
 * package: StuSpace:
 */
public class DensityUtil {
    //dp float px int
    private final float density;

    public DensityUtil() {
        density = Resources.getSystem().getDisplayMetrics().density;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     *
     * @param dpValue 虚拟像素
     * @return 像素
     */
    public static int dp2px(float dpValue) {
        return (int) (0.5f + dpValue * Resources.getSystem().getDisplayMetrics().density);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     *
     * @param pxValue 像素
     * @return 虚拟像素
     */
    public static float px2dp(int pxValue) {
        return pxValue / Resources.getSystem().getDisplayMetrics().density;
    }

    public int dip2px(float dpValue) {
        return (int) (0.5f + dpValue * density);
    }

    public float px2dip(int pxValue) {
        return pxValue / density;
    }
}
