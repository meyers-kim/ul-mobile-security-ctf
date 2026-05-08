package lu.uni.mobilectf;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkSyncActivity extends Activity {
    private TextView status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout root = Ui.base(this, "Network Sync", "The sync client sends diagnostic headers.");
        LinearLayout card = Ui.card(this, root);
        Ui.text(this, card, "Hint: capture the request with a proxy or inspect the request construction in code.");
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
                connection.setRequestProperty("X-Campus-Mode", "belval-sync");
                connection.setRequestProperty("X-Flag-Hint", "FLAG{proxy_header_captured}");
                connection.connect();

                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    while (reader.readLine() != null) {
                        // Drain response so the request completes.
                    }
                }
                message = "Sync completed. The useful evidence was sent in a request header.";
            } catch (Exception e) {
                message = "Sync failed. Static analysis can still reveal the intended header.";
            }
            String finalMessage = message;
            runOnUiThread(() -> status.setText(finalMessage));
        }).start();
    }
}
