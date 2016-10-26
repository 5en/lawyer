/**
 * 
 */
package com.yanglaoClient.mvp.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.yanglaoClient.mvp.presenter.BaseApplication;


/**
 * 
 */
public class NetworkUtils {

	public static final int NETWORK_TYPE_NONE = 0;
	public static final int NETWORK_TYPE_2G = NETWORK_TYPE_NONE + 1;
	public static final int NETWORK_TYPE_3G = NETWORK_TYPE_2G + 1;
	public static final int NETWORK_TYPE_MOBILE = NETWORK_TYPE_3G + 1;
	public static final int NETWORK_TYPE_WIFI = NETWORK_TYPE_MOBILE + 1;
	public static final int NETWORK_TYPE_OTHER = NETWORK_TYPE_WIFI + 1;

	private static ConnectivityManager connectivityMgr;
	private static TelephonyManager telMgr;

	/**
	 * 判断网络类型
	 * 
	 */
	public static int getNetWorkType() {

		if (connectivityMgr == null) {
			connectivityMgr = (ConnectivityManager) BaseApplication.getApplication()
					.getSystemService(Context.CONNECTIVITY_SERVICE);
		}

		if (connectivityMgr == null) {
			return NETWORK_TYPE_NONE;
		}

		NetworkInfo networkInfo = connectivityMgr.getActiveNetworkInfo();

		if (networkInfo == null) {
			return NETWORK_TYPE_NONE;
		}

		if (ConnectivityManager.TYPE_MOBILE == networkInfo.getType()) {
			if (telMgr == null) {
				telMgr = (TelephonyManager) BaseApplication.getApplication()
						.getSystemService(Context.TELEPHONY_SERVICE);
			}

			if (telMgr == null) {
				return NETWORK_TYPE_MOBILE;
			}

			int mobileType = telMgr.getNetworkType();
			switch (mobileType) {
			case TelephonyManager.NETWORK_TYPE_GPRS:
			case TelephonyManager.NETWORK_TYPE_EDGE:
				return NETWORK_TYPE_2G;
			case TelephonyManager.NETWORK_TYPE_UMTS:
			case TelephonyManager.NETWORK_TYPE_HSDPA:
			case TelephonyManager.NETWORK_TYPE_HSUPA:
			case TelephonyManager.NETWORK_TYPE_HSPA:
				return NETWORK_TYPE_3G;
			default:
				return NETWORK_TYPE_MOBILE;
			}
		} else if (ConnectivityManager.TYPE_WIFI == networkInfo.getType()) {
			return NETWORK_TYPE_WIFI;
		} else {
			return NETWORK_TYPE_OTHER;
		}
	}

	public static boolean isNetworkAvailable() {
		if (connectivityMgr == null) {
			connectivityMgr = (ConnectivityManager) BaseApplication.getApplication()
					.getSystemService(Context.CONNECTIVITY_SERVICE);
		}

		if (connectivityMgr == null) {
			return false;
		}

		NetworkInfo networkInfo = connectivityMgr.getActiveNetworkInfo();

		return networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
	}

	/**
	 * 判断当前是否有网络连接
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isNetwork(Context context) {
		boolean network = isWifi(context);
		boolean mobilework = isMobile(context);
		if (!network && !mobilework) { // 无网络连接
			return false;
		} else if (network == true && mobilework == false) { // wifi连接
		} else { // 网络连接
			return true;
		}
		return true;
	}

	/**
	 * 判断当前网络是否是wifi局域网
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isWifi(Context context) {
		ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		if (info != null) {
			return info.isConnected(); // 返回网络连接状态
		}
		return false;
	}

	/**
	 * 判断当前网络是否是手机网络
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isMobile(Context context) {
		ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		if (info != null) {
			return info.isConnected(); // 返回网络连接状态
		}
		return false;
	}

	/**
	 * 获取手机运营商
	 * 
	 * @param context
	 * @return
	 */
	public static String getNetWorkName(Context context) {
		TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		String IMSI = telephonyManager.getSubscriberId();
		if(!TextUtils.isEmpty(IMSI)){
			// IMSI号前面3位460是国家，紧接着后面2位00 02是中国移动，01是中国联通，03是中国电信。
			if (IMSI.startsWith("46000") || IMSI.startsWith("46002")) {
				return "中国移动";
			} else if (IMSI.startsWith("46001")) {
				return "中国联通";
			} else if (IMSI.startsWith("46003")) {
				return "中国电信";
			}
		}
		return "无sim卡";
	}

	/**
	 * 判断网络类型
	 * 
	 */
	public static String getNetWorkType(Context context) {

		String type = "其他";

		if (connectivityMgr == null) {
			connectivityMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		}

		if (connectivityMgr == null) {
			return type;
		}

		NetworkInfo networkInfo = connectivityMgr.getActiveNetworkInfo();

		if (networkInfo == null) {
			return type;
		}
		if (ConnectivityManager.TYPE_WIFI == networkInfo.getType()) {
			type = "WIFI";
		} else if (ConnectivityManager.TYPE_MOBILE == networkInfo.getType()) {
			String _strSubTypeName = networkInfo.getSubtypeName();

			// TD-SCDMA networkType is 17
			int networkType = networkInfo.getSubtype();
			switch (networkType) {
			case TelephonyManager.NETWORK_TYPE_GPRS:
			case TelephonyManager.NETWORK_TYPE_EDGE:
			case TelephonyManager.NETWORK_TYPE_CDMA:
			case TelephonyManager.NETWORK_TYPE_1xRTT:
			case TelephonyManager.NETWORK_TYPE_IDEN: // api<8 : replace by 11
				type = "2G";
				break;
			case TelephonyManager.NETWORK_TYPE_UMTS:
			case TelephonyManager.NETWORK_TYPE_EVDO_0:
			case TelephonyManager.NETWORK_TYPE_EVDO_A:
			case TelephonyManager.NETWORK_TYPE_HSDPA:
			case TelephonyManager.NETWORK_TYPE_HSUPA:
			case TelephonyManager.NETWORK_TYPE_HSPA:
			case TelephonyManager.NETWORK_TYPE_EVDO_B: // api<9 : replace by 14
			case TelephonyManager.NETWORK_TYPE_EHRPD: // api<11 : replace by 12
			case TelephonyManager.NETWORK_TYPE_HSPAP: // api<13 : replace by 15
				type = "3G";
				break;
			case TelephonyManager.NETWORK_TYPE_LTE: // api<11 : replace by 13
				type = "4G";
				break;
			default:
				// http://baike.baidu.com/item/TD-SCDMA 中国移动 联通 电信 三种3G制式
				if (_strSubTypeName.equalsIgnoreCase("TD-SCDMA") || _strSubTypeName.equalsIgnoreCase("WCDMA")
						|| _strSubTypeName.equalsIgnoreCase("CDMA2000")) {
					type = "3G";
				}
				break;
			}
		}
		return type;
	}
}
