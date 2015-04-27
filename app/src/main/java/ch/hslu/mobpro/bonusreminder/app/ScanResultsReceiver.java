package ch.hslu.mobpro.bonusreminder.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.Toast;

public class ScanResultsReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        for (ScanResult scanResult : wifiManager.getScanResults()) {
            if(scanResult.SSID.toLowerCase().equals("hslu")) {
                Toast.makeText(context, "SSID: " + scanResult.SSID, Toast.LENGTH_LONG).show();
                break;
            }
        }

    }
}
