package com.yanglaoClient.mvp.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class UIUtils {

	/**
	 * 设置右侧小图标。
	 * 
	 * @param txtView
	 * @param resId
	 */
	public static void setDrawableRight(Context context, TextView txtView,
			int resId) {
		Drawable drawable = context.getResources().getDrawable(resId);
		// / 这一步必须要做,否则不会显示.
		drawable.setBounds(0, 0, drawable.getMinimumWidth(),
				drawable.getMinimumHeight());
		txtView.setCompoundDrawables(null, null, drawable, null);
	}

	/**
	 * 设置左侧小图标。
	 * 
	 * @param txtView
	 * @param resId
	 */
	public static void setDrawableLeft(Context context, TextView txtView,
			int resId) {
		Drawable drawable = context.getResources().getDrawable(resId);
		// / 这一步必须要做,否则不会显示.
		drawable.setBounds(0, 0, drawable.getMinimumWidth(),
				drawable.getMinimumHeight());
		txtView.setCompoundDrawables(drawable, null, null, null);
	}

	/**
	 * 验证手机格式
	 */
	public static boolean isMobileNO(String mobiles) {
    /*
    移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
    联通：130、131、132、152、155、156、185、186
    电信：133、153、180、189、（1349卫通）
    总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
    */
		String telRegex = "[1][358]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
		if (TextUtils.isEmpty(mobiles)) return false;
		else return mobiles.matches(telRegex);
	}

	/**
	 * 显示Toast提示。
	 * 
	 * @param context
	 * @param msg
	 */
	public static void showToast(Context context, int resId) {

		String msg = context.getResources().getString(resId);
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}

	/**
	 * 显示Toast提示。
	 * 
	 * @param context
	 * @param msg
	 */
	public static void showToast(Context context, String msg) {

		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}

	/**
	 * 设置View的背景。
	 * 
	 * @param view
	 * @param background
	 */
	@SuppressLint("NewApi")
	public static void setViewBackground(View view, Drawable background) {

		if (ReflectUtils.containMethod(TextView.class, "setBackground")) {
			view.setBackground(background);
		} else if (ReflectUtils.containMethod(TextView.class,
				"setBackgroundDrawable")) {
			view.setBackgroundDrawable(background);
		}
	}

	/**
	 * 设置View的背景。
	 * 
	 * @param view
	 * @param background
	 */
	@SuppressLint("NewApi")
	public static void setViewBackground(Context context, View view, int resId) {

		if (ReflectUtils.containMethod(TextView.class, "setBackground")) {
			view.setBackground(context.getResources().getDrawable(resId));
		} else if (ReflectUtils.containMethod(TextView.class,
				"setBackgroundDrawable")) {
			view.setBackgroundDrawable(context.getResources()
					.getDrawable(resId));
		}
	}

	/**
	 * 获得屏幕宽度
	 * @param context
	 * @return
	 */
	public static int getScreenWidth(Context context) {
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		return outMetrics.widthPixels;
	}

	/**
	 * 获得屏幕高度
	 * 
	 * @param context
	 * @return
	 */
	public static int getScreenHeight(Context context) {
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		return outMetrics.heightPixels;
	}

	/**
	 * 获得状态栏的高度
	 * 
	 * @param context
	 * @return
	 */
	public static int getStatusHeight(Context context) {

		int statusHeight = -1;
		try {
			Class<?> clazz = Class.forName("com.android.internal.R$dimen");
			Object object = clazz.newInstance();
			int height = Integer.parseInt(clazz.getField("status_bar_height")
					.get(object).toString());
			statusHeight = context.getResources().getDimensionPixelSize(height);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return statusHeight;
	}

	/**
	 * 获取当前屏幕截图，包含状态栏
	 * 
	 * @param activity
	 * @return
	 */
	public static Bitmap snapShotWithStatusBar(Activity activity) {
		View view = activity.getWindow().getDecorView();
		view.setDrawingCacheEnabled(true);
		view.buildDrawingCache();
		Bitmap bmp = view.getDrawingCache();
		int width = getScreenWidth(activity);
		int height = getScreenHeight(activity);
		Bitmap bp = null;
		bp = Bitmap.createBitmap(bmp, 0, 0, width, height);
		view.destroyDrawingCache();
		return bp;

	}

	/**
	 * 获取当前屏幕截图，不包含状态栏
	 * 
	 * @param activity
	 * @return
	 */
	public static Bitmap snapShotWithoutStatusBar(Activity activity) {
		View view = activity.getWindow().getDecorView();
		view.setDrawingCacheEnabled(true);
		view.buildDrawingCache();
		Bitmap bmp = view.getDrawingCache();
		Rect frame = new Rect();
		activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
		int statusBarHeight = frame.top;

		int width = getScreenWidth(activity);
		int height = getScreenHeight(activity);
		Bitmap bp = null;
		bp = Bitmap.createBitmap(bmp, 0, statusBarHeight, width, height
				- statusBarHeight);
		view.destroyDrawingCache();
		return bp;

	}

	public static int dip2px(Context context, int dp) {
		DisplayMetrics metrics = context.getResources().getDisplayMetrics();
		float density = metrics.density;
		return (int) (dp * density + 0.5f);
	}

	public static void showDialog(Context context, int res) {
		showDialog(context, context.getResources().getString(res));
	}

	public static void showDialog(Context context, String msg) {
		showDialog(context, msg, null);
	}

	public static void showDialog(Context context, int res,
			final OnClickListener listener) {
		showDialog(context, null, context.getResources().getString(res),
				listener);
	}

	public static void showDialog(Context context, String msg,
			final OnClickListener listener) {
		showDialog(context, null, msg, listener);
	}

	/**
	 * 一个确定按钮
	 * 
	 * @param context
	 * @param title
	 * @param msg
	 */
	public static void showDialog(Context context, String title, String msg,
			final OnClickListener listener) {
		showDialog(context, title, msg, "确定", listener);
	}

	/**
	 * 一个确定按钮
	 * 
	 * @param context
	 * @param title
	 * @param msg
	 */
	public static void showDialog(Context context, String title, String msg,
			String confirm, final OnClickListener listener) {
		Builder builder = new Builder(context);
		if (!TextUtils.isEmpty(title)) {
			builder.setTitle(title);
		}
		if (!TextUtils.isEmpty(msg)) {
			builder.setMessage(msg);
		}
		builder.setPositiveButton(confirm, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				if (listener != null) {
					listener.onClick(dialog, which);
				}
			}
		});
		builder.show();
	}

	/**
	 * 两个按钮
	 * 
	 * @param context
	 * @param title
	 * @param msg
	 * @param listener
	 */
	public static void showDialogWithCancel(Context context, String title,
			String msg, final OnClickListener listener) {
		Builder builder = new Builder(context);
		if (!TextUtils.isEmpty(title)) {
			builder.setTitle(title);
		}
		if (!TextUtils.isEmpty(msg)) {
			builder.setMessage(msg);
		}
		builder.setPositiveButton("确定", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				if (listener != null) {
					listener.onClick(dialog, which);
				}
			}
		});
		builder.setNegativeButton("取消", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				if (listener != null) {
					listener.onClick(dialog, which);
				}
			}
		});
		builder.show();
	}
}
