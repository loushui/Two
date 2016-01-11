package cn.com.loushui.mylibrary.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2015/12/22.
 */
public class Network {
    private static final String TAG = Network.class.getSimpleName();

    public Network() {
    }

    public static ConnectivityManager getConnManager(Context context) {
        return (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    public static boolean isConnected(Context context) {
        NetworkInfo net = getConnManager(context).getActiveNetworkInfo();
        return net != null && net.isConnected();
    }

    public static boolean isConnectedOrConnecting(Context context) {
        NetworkInfo[] nets = getConnManager(context).getAllNetworkInfo();
        if (nets != null) {
            NetworkInfo[] var5 = nets;
            int var4 = nets.length;

            for (int var3 = 0; var3 < var4; ++var3) {
                NetworkInfo net = var5[var3];
                if (net.isConnectedOrConnecting()) {
                    return true;
                }
            }
        }

        return false;
    }

    public static Network.NetType getConnectedType(Context context) {
        NetworkInfo net = getConnManager(context).getActiveNetworkInfo();
        if (net != null) {
            switch (net.getType()) {
                case 0:
                    return Network.NetType.Mobile;
                case 1:
                    return Network.NetType.Wifi;
                default:
                    return Network.NetType.Other;
            }
        } else {
            return Network.NetType.None;
        }
    }

    public static boolean isWifiConnected(Context context) {
        NetworkInfo net = getConnManager(context).getActiveNetworkInfo();
        return net != null && net.getType() == 1 && net.isConnected();
    }

    public static boolean isMobileConnected(Context context) {
        NetworkInfo net = getConnManager(context).getActiveNetworkInfo();
        return net != null && net.getType() == 0 && net.isConnected();
    }

    public static boolean isAvailable(Context context) {
        return isWifiAvailable(context) || isMobileAvailable(context) && isMobileEnabled(context);
    }

    public static boolean isWifiAvailable(Context context) {
        NetworkInfo[] nets = getConnManager(context).getAllNetworkInfo();
        if (nets != null) {
            NetworkInfo[] var5 = nets;
            int var4 = nets.length;

            for (int var3 = 0; var3 < var4; ++var3) {
                NetworkInfo net = var5[var3];
                if (net.getType() == 1) {
                    return net.isAvailable();
                }
            }
        }

        return false;
    }

    public static boolean isMobileAvailable(Context context) {
        NetworkInfo[] nets = getConnManager(context).getAllNetworkInfo();
        if (nets != null) {
            NetworkInfo[] var5 = nets;
            int var4 = nets.length;

            for (int var3 = 0; var3 < var4; ++var3) {
                NetworkInfo net = var5[var3];
                if (net.getType() == 0) {
                    return net.isAvailable();
                }
            }
        }

        return false;
    }

    public static boolean isMobileEnabled(Context context) {
        try {
            Method e = ConnectivityManager.class.getDeclaredMethod("getMobileDataEnabled", new Class[0]);
            e.setAccessible(true);
            return ((Boolean) e.invoke(getConnManager(context), new Object[0])).booleanValue();
        } catch (Exception var2) {
            var2.printStackTrace();
            return true;
        }
    }

    public static boolean printNetworkInfo(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo in = connectivity.getActiveNetworkInfo();
            Log.i(TAG, "-------------$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$-------------");
            Log.i(TAG, "getActiveNetworkInfo: " + in);
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; ++i) {
                    Log.i(TAG, "NetworkInfo[" + i + "]isAvailable : " + info[i].isAvailable());
                    Log.i(TAG, "NetworkInfo[" + i + "]isConnected : " + info[i].isConnected());
                    Log.i(TAG, "NetworkInfo[" + i + "]isConnectedOrConnecting : " + info[i].isConnectedOrConnecting());
                    Log.i(TAG, "NetworkInfo[" + i + "]: " + info[i]);
                }

                Log.i(TAG, "\n");
            } else {
                Log.i(TAG, "getAllNetworkInfo is null");
            }
        }

        return false;
    }

    public static enum NetType {
        None(1),
        Mobile(2),
        Wifi(4),
        Other(8);

        public int value;

        private NetType(int value) {
            this.value = value;
        }
    }
}
