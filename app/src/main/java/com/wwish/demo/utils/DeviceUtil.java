package com.wwish.demo.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import java.util.UUID;

/**
 * Created by wwish on 2017/9/11.
 */

public class DeviceUtil {

    public static String getDeviceId(Context context) {
        String deviceId = null;
        deviceId = ((TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
        if (deviceId == null && Build.VERSION.SDK_INT > 9) {
            deviceId = Settings.Secure.getString(context.getContentResolver(),
                    Settings.Secure.ANDROID_ID);
            if (deviceId == null) {
                ConnectivityManager cm = (ConnectivityManager) context
                        .getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = cm.getActiveNetworkInfo();
                if (networkInfo != null
                        && networkInfo.isAvailable()
                        && networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                    WifiManager wm = (WifiManager) context
                            .getSystemService(Context.WIFI_SERVICE);
                    deviceId = wm.getConnectionInfo().getMacAddress();
                } else {
                    deviceId = UUID.randomUUID().toString();
                }
            }
        }

        if (deviceId != null && deviceId.length() < 28) {
            int len = 28 - deviceId.length();
            for (int i = 0; i < len; i++) {
                deviceId = "0" + deviceId;
            }
        }

        return deviceId;
    }

}
