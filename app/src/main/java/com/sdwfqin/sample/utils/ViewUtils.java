package com.sdwfqin.sample.utils;

import android.content.Context;

/**
 * 描述：视图工具类
 *
 * @author sdwfqin
 * @date 2017/4/26
 */
public class ViewUtils {

    /**
     * dp转为px(像素)
     */
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     *  px(像素)转为dp
     */
    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
