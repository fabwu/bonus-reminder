package ch.hslu.mobpro.bonusreminder.app;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.*;

public class ScanResultsService extends Service {

    private Set<String> usedSSIDs;

    @Override
    public void onCreate() {
        usedSSIDs = new HashSet<String>();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Set<String> foundSSIDs = findSSIDs();
        cleanUsedSSIDs(foundSSIDs);

        Map<String, String> ssidBonus = getMap();

        for (String foundSSID : foundSSIDs) {
            if (ssidBonus.containsKey(foundSSID) && !usedSSIDs.contains(foundSSID)) {
                String bonus = ssidBonus.get(foundSSID);
                buildBonusNotification(bonus);
                usedSSIDs.add(foundSSID);
            }
        }

        return START_STICKY;
    }

    private Set<String> findSSIDs() {
        WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        Set<String> foundSSIDs = new HashSet<String>();
        for (ScanResult scanResult : wifiManager.getScanResults()) {
            foundSSIDs.add(scanResult.SSID);
        }
        return foundSSIDs;
    }

    private void cleanUsedSSIDs(Set<String> foundSSIDs) {
        for (String usedSSID : usedSSIDs) {
            if (!foundSSIDs.contains(usedSSID)) {
                usedSSIDs.remove(usedSSID);
            }
        }
    }

    private Map<String, String> getMap() {
        HashMap<String, String> map = new HashMap<String, String>();

        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        Set<String> bonusSet = new HashSet<String>();
        bonusSet = prefs.getStringSet(MainActivity.BONUS_KEY, bonusSet);
        for (String bonus : bonusSet) {
            String ssid = prefs.getString(bonus, "");
            if (!ssid.equals("")) {
                map.put(ssid, bonus);
            }
        }
        return map;
    }

    private void buildBonusNotification(String bonus) {
        Notification.Builder builder = new Notification.Builder(this)
                .setSmallIcon(R.drawable.emo_im_money_mouth)
                .setContentTitle(bonus)
                .setContentText("Es wurde ein Gutschein erkannt!");
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        int notificationId = new Random().nextInt();
        mNotificationManager.notify(notificationId, builder.build());
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
