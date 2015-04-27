package ch.hslu.mobpro.bonusreminder.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class ScanResultsReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);

        Map<String, String> ssidBonus = new HashMap<String, String>();
        ssidBonus.put("hslu", "Mensagutschein");
        ssidBonus.put("IrgendeinAccessPoint", "Irgendein Gutschein");

        for (ScanResult scanResult : wifiManager.getScanResults()) {
            String ssid = scanResult.SSID.toLowerCase();
            if (ssidBonus.containsKey(ssid)) {
                String bonus = ssidBonus.get(ssid);
                Toast.makeText(context, "Folgender Gutschein zücken: " + bonus, Toast.LENGTH_LONG).show();
                break;
            }
        }
    }
}
