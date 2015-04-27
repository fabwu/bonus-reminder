package ch.hslu.mobpro.bonusreminder.app;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class ScanResultsReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);

        Map<String, String> ssidBonus = new HashMap<String, String>();
        ssidBonus.put("hslu", "Mensagutschein");
        ssidBonus.put("UPC0039811", "Irgendein Gutschein");

        for (ScanResult scanResult : wifiManager.getScanResults()) {
            String ssid = scanResult.SSID;
            if (ssidBonus.containsKey(ssid)) {
                String bonus = ssidBonus.get(ssid);
                Notification.Builder builder = new Notification.Builder(context)
                        .setSmallIcon(R.drawable.emo_im_money_mouth)
                        .setContentTitle(bonus)
                        .setContentText("Es wurde ein Gutschein erkannt!");
                NotificationManager mNotificationManager =
                        (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                int id = 0;
                mNotificationManager.notify(id, builder.build());
            }
        }
    }
}
