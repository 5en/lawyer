package com.yanglaoClient.mvp.util;

import android.app.AlertDialog.Builder;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.widget.Toast;

import java.util.Calendar;

public class DialogCreater {

	public static Builder createDialogBuilder(Context context) {
		Builder builder = new Builder(context);
		builder.setIcon(android.R.drawable.ic_dialog_info);
		return builder;
	}

	public static Builder createDialogBuilder(Context context, int title) {
		Builder builder = createDialogBuilder(context);
		builder.setTitle(title);
		return builder;
	}

	public static Builder createDialogBuilder(Context context, String title) {
		Builder builder = createDialogBuilder(context);
		builder.setTitle(title);
		return builder;
	}

	public static Builder createDialogBuilder(Context context, int title, int message) {
		Builder builder = createDialogBuilder(context, title);
		builder.setMessage(message);
		return builder;
	}

	public static Builder createDialogBuilder(Context context, String title, String message) {
		Builder builder = createDialogBuilder(context, title);
		builder.setMessage(message);
		return builder;
	}

	public static void showToast(Context context, String message) {
		Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
	}

	public static void showToastLong(Context context, String message) {
		Toast.makeText(context, message, Toast.LENGTH_LONG).show();
	}

	public static void showAlert(Context context, String title, String message,
			DialogInterface.OnClickListener listner) {
		Builder b = createDialogBuilder(context, title, message);
		b.setPositiveButton("确定", listner);
		b.create().show();
	}

	public static void showAlert(Context context, String title, String btnText, String message,
			DialogInterface.OnClickListener listner) {
		Builder b = createDialogBuilder(context, title, message);
		b.setPositiveButton(btnText, listner);
		b.create().show();
	}

	public static ProgressDialog showProgressDialog(Context context, CharSequence title, CharSequence message,
			boolean indeterminate, boolean cancelable) {
		ProgressDialog dialog = createProgressDialog(context, title, message, indeterminate, cancelable);
		return dialog;
	}

	public static ProgressDialog createProgressDialog(Context context, CharSequence title, CharSequence message,
			boolean indeterminate, boolean cancelable) {
		ProgressDialog dialog = new ProgressDialog(context);
		if (!TextUtils.isEmpty(title)) {
			dialog.setTitle(title);
		}
		if (!TextUtils.isEmpty(message)) {
			dialog.setMessage(message);

		}
		dialog.setIndeterminate(indeterminate);
		dialog.setCancelable(cancelable);
		return dialog;
	}

	public static ProgressDialog createProgressDialog(Context context, CharSequence title, CharSequence message,
			boolean indeterminate, boolean cancelable, int resStyleId) {
		ProgressDialog dialog = new ProgressDialog(context, resStyleId);
		if (!TextUtils.isEmpty(title)) {
			dialog.setTitle(title);
		}
		if (!TextUtils.isEmpty(message)) {
			dialog.setMessage(message);

		}
		dialog.setIndeterminate(indeterminate);
		dialog.setCancelable(cancelable);
		return dialog;
	}

	public static ProgressDialog createProgressDialog(Context context, CharSequence message, int resStyleId) {
		return createProgressDialog(context, "", message, true, true, resStyleId);
	}

	public static ProgressDialog createProgressDialog(Context context, CharSequence message) {
		return createProgressDialog(context, "", message, true, true);
	}

	public static DatePickerDialog createDatePickerDialog(Context context, OnDateSetListener listener, Calendar c) {
		DatePickerDialog dpd = new DatePickerDialog(context, listener, c.get(Calendar.YEAR), c.get(Calendar.MONTH),
				c.get(Calendar.DAY_OF_MONTH));
		return dpd;
	}

	public static TimePickerDialog createTimePickerDialog(Context context, OnTimeSetListener listener, Calendar c,
			boolean is24HourView) {
		TimePickerDialog ptd = new TimePickerDialog(context, listener, c.get(Calendar.HOUR_OF_DAY),
				c.get(Calendar.MINUTE), is24HourView);
		return ptd;
	}

}
