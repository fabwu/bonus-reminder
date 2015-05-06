package ch.hslu.mobpro.bonusreminder.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ScanResultsReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent scanResultSerivce = new Intent(context, ScanResultsService.class);
        context.startService(scanResultSerivce);
    }
}
