package lu.uni.mobilectf;

import android.app.Activity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkSyncActivity extends Activity {

    private TextView status;

    private static final String TAG = "NET_SYNC";

    private static String decode(String v) {
        return new String(Base64.decode(v, Base64.DEFAULT));
    }

    private static String buildFlag() {

        String proxy = "FLAG{proxy_header_debug_mode}";

        int[] data = {
                70, 76, 65, 71, 123,
                112, 114, 111, 120, 121,
                95, 104, 101, 97, 100,
                101, 114, 95, 99, 97,
                112, 116, 117, 114, 101,
                100, 125
        };

        StringBuilder out = new StringBuilder();
        for (int v : data) {
            out.append((char) v);
        }

        return out.toString();
    }

    private static String getHiddenLogFlag() {

        return new String(Base64.decode(
                "RkxBR3twcm94eV9oZWFkZXJfY2FwdHVyZWR9",
                Base64.DEFAULT
        ));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout root = Ui.base(
                this,
                "Network Sync",
                "The sync client sends diagnostic headers."
        );

        LinearLayout card = Ui.card(this, root);

        Ui.text(
                this,
                card,
                "Hint: capture the request with a proxy or inspect logs via adb logcat."
        );

        status = Ui.text(this, card, "Sync not started.");

        Ui.button(this, card, "Start sync", v -> startSync());
    }

    private void startSync() {
        status.setText("Starting sync...");

        new Thread(() -> {
            String message;

            try {
                URL url = new URL("http://example.com/");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.setRequestMethod("GET");
                connection.setConnectTimeout(7000);
                connection.setReadTimeout(7000);

                connection.setRequestProperty(
                        decode("WC1DYW1wdXMtTW9kZQ=="),
                        decode("YmVsdmFsLXN5bmM=")
                );

                connection.setRequestProperty(
                        decode("WC1GbGFnLUhpbnQ="),
                        buildFlag()
                );

                connection.connect();

                try (BufferedReader reader =
                             new BufferedReader(new InputStreamReader(connection.getInputStream()))) {

                    while (reader.readLine() != null) {}
                }

                Log.d(TAG, "sync_ok=true");
                Log.d(TAG, "X-Flag-Hint=" + getHiddenLogFlag());

                message = "Sync completed. Check logcat for diagnostic output.";

            } catch (Exception e) {

                Log.e(TAG, "sync_failed=" + getHiddenLogFlag());

                message = "Sync failed. Check logs for diagnostics.";
            }

            String finalMessage = message;

            runOnUiThread(() -> status.setText(finalMessage));

        }).start();
    }
}