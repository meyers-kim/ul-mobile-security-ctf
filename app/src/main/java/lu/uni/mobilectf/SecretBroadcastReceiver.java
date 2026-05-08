package lu.uni.mobilectf;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class SecretBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String code = intent == null ? null : intent.getStringExtra("code");
        if ("campus-radio".equals(code)) {
            String flag = decode(new int[]{
                    65, 75, 70, 64, 124, 101, 117, 104, 102, 99, 100, 102, 116, 115, 88, 116, 110, 96, 105, 102, 107, 88, 117, 98, 100, 98, 110, 113, 98, 99, 122
            });
            Log.i("CampusCTF", "broadcast receiver unlocked=" + flag);
            Toast.makeText(context, "Broadcast accepted. Check Logcat.", Toast.LENGTH_LONG).show();
        } else {
            Log.i("CampusCTF", "broadcast receiver denied");
        }
    }

    private static String decode(int[] encoded) {
        StringBuilder out = new StringBuilder();
        for (int value : encoded) {
            out.append((char) (value ^ 7));
        }
        return out.toString();
    }
}
