package com.scl.baselibrary.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.ArrayRes;
import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.IntegerRes;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

/**
 * Created by admin on 2021/6/7.
 * name:系统UI操作工具类
 */
public class UIUtils {
    public static final String RMB = "¥";

    public static String BLANK = "&#12288;";
    //-------------------------以下方法需初始化init----------------------------->>>
    private static Context mContext;

    /**
     * 工具类初始化.一般在程序入口初始化一次.
     *
     * @param context 上下文对象
     */
    public static void init(Context context) {
        mContext = context;
    }

    public static Resources getResources() {
        isNull();
        return mContext.getResources();
    }

    public static int getInteger(@IntegerRes int id) {
        isNull();
        return mContext.getResources().getInteger(id);
    }

    public static int[] getIntArray(@ArrayRes int id) {
        isNull();

        return mContext.getResources().getIntArray(id);
    }

    public static String[] getStringArray(@ArrayRes int id) {
        isNull();

        return mContext.getResources().getStringArray(id);
    }

    /**
     * 获取color.xml中的颜色
     *
     * @param id 颜色id,例如R.id.c_ffffff
     * @return 颜色值
     */
    public static int getColor(@ColorRes int id) {
        isNull();

        return mContext.getResources().getColor(id);
    }

    /**
     * Returns a localized formatted string from the application's package's
     * default string table, substituting the format arguments as defined in
     * {@link java.util.Formatter} and {@link String#format}.
     *
     * @param resId      Resource id for the format string
     * @param formatArgs The format arguments that will be used for
     *                   substitution.
     * @return The string data associated with the resource, formatted and
     * stripped of styled text information.
     */
    public static String getString(@StringRes int resId, Object... formatArgs) {
        isNull();
        //getString(@StringRes int resId, Object... formatArgs)
        return mContext.getString(resId, formatArgs);
    }


    /**
     * Returns a localized string from the application's package's
     * default string table.
     *
     * @param resId Resource id for the string
     * @return The string data associated with the resource, stripped of styled
     * text information.
     */
    public static String getString(@StringRes int resId) {
        isNull();
        return mContext.getString(resId);
    }

    //&#12288;"门&#12288;&#12288;店:"

    /**
     * 代表一个字宽度空格.
     *
     * @param text 内容
     * @return 带空格的字符串.BLANK
     */
    public static String getBlankString(String text) {
        return Html.fromHtml(text).toString();
    }

    /**
     * 获取屏幕宽度。
     *
     * @return 屏幕宽度
     */
    public static int getScreenWidth() {
        isNull();
        WindowManager wmManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        return getScreenWidth(wmManager);
    }

    public static int getScreenWidth(Context context) {
        WindowManager wmManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        return getScreenWidth(wmManager);
    }

    public static int getScreenHeight() {
        isNull();
        WindowManager wmManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        return getScreenHeight(wmManager);
    }

    public static int getScreenHeight(Context context) {
        WindowManager wmManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        return getScreenHeight(wmManager);
    }

    private static int getScreenHeight(WindowManager windowManager) {
        int heightPixels = 0;
        Display defaultDisplay = windowManager.getDefaultDisplay();
        if (aboveApiLevel(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
                && !aboveApiLevel(Build.VERSION_CODES.JELLY_BEAN_MR1)) {
            try {
                heightPixels = (Integer) Display.class.getMethod("getRawHeight").invoke(defaultDisplay);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            android.graphics.Point realSize = new android.graphics.Point();
            defaultDisplay.getRealSize(realSize);
            heightPixels = realSize.y;
        } else {
            DisplayMetrics metrics = new DisplayMetrics();
            defaultDisplay.getMetrics(metrics);
            heightPixels = metrics.heightPixels;
        }
        return heightPixels;
    }


    /**
     * 获取dimes.xml中dimen的尺寸值。
     *
     * @param id 资源id
     * @return 返回float类型的尺寸值。
     */
    public static float getDimension(@DimenRes int id) {
        isNull();
        return mContext.getResources().getDimension(id);
    }

    /**
     * dp转px
     *
     * @param dpValue dp值
     * @return px值
     */
    public static int dp2px(final float dpValue) {
        isNull();
        return dp2px(mContext, dpValue);
    }

    /**
     * px转dp
     *
     * @param pxValue px值
     * @return dp值
     */
    public static int px2dp(final float pxValue) {
        isNull();
        return px2dp(mContext, pxValue);
    }

    /**
     * sp转px
     *
     * @param spValue sp值
     * @return px值
     */
    public static int sp2px(final float spValue) {
        isNull();
        return sp2px(mContext, spValue);
    }

    /**
     * px转sp
     *
     * @param pxValue px值
     * @return sp值
     */
    public static int px2sp(final float pxValue) {
        isNull();
        return px2sp(mContext, pxValue);
    }

    /**
     * Causes the Runnable r to be added to the message queue, to be run
     * after the specified amount of time elapses.
     *
     * @param r           The Runnable that will be executed.
     * @param delayMillis The delay (in milliseconds) until the Runnable will be executed.
     * @return true:执行成功
     */
    public static boolean postDelayed(Runnable r, long delayMillis) {
        Handler mHandler = new Handler(Looper.getMainLooper());
        return mHandler.postDelayed(r, delayMillis);
    }

    /**
     * 获取color.xml中的颜色
     *
     * @param view 控件
     * @param id   颜色id,例如R.id.c_ffffff
     * @return 颜色值
     */
    public static int getColor(View view, @ColorRes int id) {
        return view.getResources().getColor(id);
    }

//<<<-------------------------以下方法需初始化init----------------------------|

//|-------------------------完全静态方法,无需初始化init----------------------->>>

    /**
     * 获取Context
     *
     * @return mContext
     */
    public static Context getContext() {
        return mContext;
    }

    /**
     * 去除字符串空格
     *
     * @param text 要处理的字符串.
     * @return 去除空格后的字符串.
     */
    public static String trim(String text) {
        if (null == text) {
            return "";
        }
        return text.trim();
    }

    /**
     * Returns true if the string is null or 0-length or 含有空格视为空串.
     *
     * @param str the string to be examined
     * @return true if str is null or zero length
     */
    public static boolean isEmpty(@Nullable CharSequence str) {
        return str == null || str.length() == 0 || str.toString().trim().length() == 0;
    }

    /**
     * 获取屏幕的宽度。
     *
     * @param windowManager 窗口管理者。
     * @return 宽度
     */
    public static int getScreenWidth(WindowManager windowManager) {
        int widthPixels = 0;
        Display defaultDisplay = windowManager.getDefaultDisplay();
        if (aboveApiLevel(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
                && !aboveApiLevel(Build.VERSION_CODES.JELLY_BEAN_MR1)) {
            try {
                widthPixels = (Integer) Display.class.getMethod("getRawWidth").invoke(defaultDisplay);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            android.graphics.Point realSize = new android.graphics.Point();
            defaultDisplay.getRealSize(realSize);
            widthPixels = realSize.x;
        } else {
            DisplayMetrics metrics = new DisplayMetrics();
            defaultDisplay.getMetrics(metrics);
            widthPixels = metrics.widthPixels;
        }
        return widthPixels;
    }

    /**
     * 获取dimes.xml中dimen的尺寸值，类似于方法
     * {@link #getDimension}，不同的是返回值是int类型的值
     *
     * @param id 资源id。
     * @return 尺寸值
     * @see #getDimension
     * @see #getDimensionPixelOffset
     */
    public static int getDimensionPixelSize(@DimenRes int id) {
        isNull();
        return mContext.getResources().getDimensionPixelSize(id);
    }

    /**
     * 获取color.xml中的颜色
     *
     * @param context 上下文
     * @param id      颜色id,例如R.id.c_ffffff
     * @return 颜色值
     */
    public static int getColor(Context context, @ColorRes int id) {
        return context.getResources().getColor(id);
    }

    /**
     * dp转px
     *
     * @param context Context
     * @param dpValue dp值
     * @return px值
     */
    public static int dp2px(Context context, final float dpValue) {
        return SizeUtils.dp2px(context, dpValue);
    }

    /**
     * px转dp
     *
     * @param context Context
     * @param pxValue px值
     * @return dp值
     */
    public static int px2dp(Context context, final float pxValue) {
        return SizeUtils.px2dp(context, pxValue);
    }

    /**
     * sp转px
     *
     * @param context Context
     * @param spValue sp值
     * @return px值
     */
    public static int sp2px(Context context, final float spValue) {
        return SizeUtils.sp2px(context, spValue);
    }

    /**
     * px转sp
     *
     * @param context Context
     * @param pxValue px值
     * @return sp值
     */
    public static int px2sp(Context context, final float pxValue) {
        return SizeUtils.px2sp(context, pxValue);
    }

    /**
     * 将数字按照4位进行分割.
     *
     * @param numText 分割前的文字
     * @return 分割后的文字.
     */
    public static String makeInterval(String numText) {
        if (numText == null || numText.length() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(numText);
        int length = numText.length() / 4 + numText.length();

        for (int i = 0; i < length; i++) {
            if (i % 5 == 0) {
                sb.insert(i, " ");
            }
        }
        sb.deleteCharAt(0);
        return sb.toString();
    }

    /**
     * 根据view创建bitmap.
     *
     * @param view  View
     * @return  Bitmap
     */
    public static Bitmap createBitmap(View view) {
        final Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        view.draw(new Canvas(bitmap));
        return bitmap;
    }

    /**
     * Sets the current primary clip on the clipboard.  This is the clip that
     * is involved in normal cut and paste operations.
     *
     * @param context  context
     * @param copeText copeText The clipped data item to set.
     */
    public static void setPrimaryClip(Context context, String copeText) {
        ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setPrimaryClip(ClipData.newPlainText(null, copeText));
    }

    private static boolean aboveApiLevel(int sdkInt) {
        return getApiLevel() >= sdkInt;
    }


    private static int getApiLevel() {
        return Build.VERSION.SDK_INT;
    }

    /**
     * mContext 是否为null.
     */
    private static void isNull() {
        if (null == mContext) {
            throw new RuntimeException("null == mContext,should call init()");
        }
    }

    /**
     * 用于TextView,出现两种颜色的情况.
     *
     * @param text        要展示的完整字符串
     * @param colorId     默认颜色
     * @param deepColorId 要加深高亮的颜色
     * @param startIndex  加深色的起始索引
     * @param endIndex    加深色的结束索引
     * @return SpannableString
     */
    public static SpannableString spannableString(String text, @ColorRes int colorId, @ColorRes int deepColorId, int startIndex, int endIndex) {
        isNull();
        SpannableString spannableString = new SpannableString(text);
        if (startIndex > 1) {
            spannableString.setSpan(new ForegroundColorSpan(getColor(colorId)), 1, startIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(new ForegroundColorSpan(getColor(deepColorId)), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(new ForegroundColorSpan(getColor(colorId)), endIndex, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        } else {
            spannableString.setSpan(new ForegroundColorSpan(getColor(deepColorId)), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(new ForegroundColorSpan(getColor(colorId)), endIndex, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return spannableString;
    }

    /**
     * 获取dimes.xml中dimen的尺寸值。类似于方法
     * {@link #getDimension}，不同的是返回值是int类型的值
     *
     * @param id 资源id。
     * @return 返回强转成int类型的值。
     */
    public int getDimensionPixelOffset(@DimenRes int id) {
        isNull();
        return mContext.getResources().getDimensionPixelOffset(id);
    }
}
