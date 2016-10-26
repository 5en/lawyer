package com.yanglaoClient.mvp.util.core;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Message;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;

public class CleanUtils {

	public static boolean closeStream(Closeable paramCloseable) {
		if (paramCloseable != null)
			try {
				paramCloseable.close();
				return true;
			} catch (IOException localIOException) {
				localIOException.printStackTrace();
			}
		return false;
	}

	public static boolean deleteFile(File paramFile) {
		return FileUtils.deleteDependon(paramFile);
	}

	public static boolean deleteFile(String paramString) {
		return FileUtils.deleteDependon(paramString);
	}

	public static boolean cancelTask(AsyncTask<?, ?, ?> paramAsyncTask) {
		if (!isAsynctaskFinished(paramAsyncTask))
			return paramAsyncTask.cancel(true);
		return false;
	}

	public static boolean isAsynctaskFinished(AsyncTask<?, ?, ?> paramAsyncTask) {
		return (paramAsyncTask == null)
				|| (paramAsyncTask.getStatus() == AsyncTask.Status.FINISHED);
	}

	public static void recycleBitmap(Bitmap paramBitmap) {
		if ((paramBitmap == null) || (paramBitmap.isRecycled()))
			return;
		paramBitmap.recycle();
	}

	public static void recycleMessage(Message paramMessage) {
		if (paramMessage == null)
			return;
		paramMessage.recycle();
	}

}
