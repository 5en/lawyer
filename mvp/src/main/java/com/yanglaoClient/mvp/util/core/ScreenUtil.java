package com.yanglaoClient.mvp.util.core;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;

import com.yanglaoClient.mvp.presenter.BaseApplication;


/**
 * 
 * ScreenUtil:[类说明] 用来做Screen不同像素的显示，
 * 
 */
public class ScreenUtil {
	/**
	 * 转换dip值
	 * 
	 * @param context
	 *            当前context
	 * @param dipValue
	 *            dip值
	 * @return dip值转成px值
	 * 
	 */
	public static int dip2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	public static int dip2px(int dipValue) {
		float reSize = BaseApplication.getApplication().getResources()
				.getDisplayMetrics().density;
		return (int) ((dipValue * reSize) + 0.5);
	}

	public static int px2dip(int pxValue) {
		float reSize = BaseApplication.getApplication().getResources()
				.getDisplayMetrics().density;
		return (int) ((pxValue / reSize) + 0.5);
	}
	
	public static Bitmap getScreenBitmap(Context context, Bitmap bitmap) {
		DisplayMetrics metrics = context.getResources().getDisplayMetrics();

		if(bitmap == null) return null;
		
		final int width = bitmap.getWidth();
		final int height = bitmap.getHeight();
		
		try {
			float widthScale = metrics.widthPixels / (float)bitmap.getWidth();
			Matrix matrix = new Matrix();
			matrix.postScale(widthScale, widthScale);
			bitmap = Bitmap.createBitmap(bitmap, 0, 0, width,
					height, matrix, true);
		} catch (OutOfMemoryError error) {
			if (null != bitmap) {
				bitmap.recycle();
				bitmap = null;
			}
		}
		
		return bitmap;
	}

	public static Bitmap getScreenBitmap(Context context, Bitmap bitmap, int maxHeight) {
		DisplayMetrics metrics = context.getResources().getDisplayMetrics();

		if(bitmap == null) return null;
		
		final int width = bitmap.getWidth();
		final int height = bitmap.getHeight();
		
		try {
			float widthScale = metrics.widthPixels / (float)bitmap.getWidth();
			Matrix matrix = new Matrix();
			matrix.postScale(widthScale, widthScale);
			bitmap = Bitmap.createBitmap(bitmap, 0, 0, width,
					height > maxHeight ? maxHeight : height, matrix, true);
		} catch (OutOfMemoryError error) {
			if (null != bitmap) {
				bitmap.recycle();
				bitmap = null;
			}
		}
		
		return bitmap;
	}

	public static String repalceBirthDayWithStar(String content){
		if (content.length()==18){
			String temp1 = content.substring(0,6);
			String temp2= content.substring(content.length()-4,content.length());

			return temp1 + "******"+ temp2;
		}else {
			return content;
		}

	}

	public static String replaceWithStartExceptBelowfourUnit(@NonNull String content){
		if (content.length()>=4){
			String temp = content.substring(content.length()-4,content.length());
			StringBuffer stringBuffer = new StringBuffer();
			for (int i = 0;i<content.length()-4;i++){
				stringBuffer.append("*");
			}
			return stringBuffer.append(temp).toString();
		}else {
			return content;
		}
	}

	public static String replaceWithStarWithoutAfter4(@NonNull String content){
		if (content.length()>=4){
			String temp = content.substring(content.length()-4,content.length());
			StringBuffer stringBuffer = new StringBuffer();
			for (int i = 0;i<4;i++){
				stringBuffer.append("*");
			}
			return stringBuffer.append(temp).toString();
		}else {
			return content;
		}
	}

	public static String translateFenToYuan(String number){
		int temp1 = Integer.parseInt(number);
		int temp2 = temp1/100; // 小数点整数部分
		int temp3 = temp1%100;//小数点小数部分
		StringBuffer stringBuffer = new StringBuffer().append(temp2).append(".").append(temp3);
		return stringBuffer.toString();

	}
}
