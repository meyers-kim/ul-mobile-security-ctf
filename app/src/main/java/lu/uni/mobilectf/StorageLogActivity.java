package lu.uni.mobilectf;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

public class StorageLogActivity extends Activity {
    private static final String TAG = "CampusCTF";
    private TextView status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout root = Ui.base(this, "Storage and Logs", "Forensic traces are sometimes left on the device.");
        LinearLayout card = Ui.card(this, root);
        Ui.text(this, card, "Hint 1: create the incident cache, then inspect the app database directory with adb run-as on a debug build.");
        Ui.text(this, card, "Hint 2: some diagnostics are only visible in Logcat with tag CampusCTF.");
        Ui.text(this, card, "Hint 3: an exported broadcast receiver is declared in the manifest.");
        status = Ui.text(this, card, "No action yet.");

        Ui.button(this, card, "Create incident cache", v -> createIncidentCache());
        Ui.button(this, card, "Send debug beacon to Logcat", v -> writeLogcatBeacon());
    }

    private void createIncidentCache() {
        SQLiteDatabase db = openOrCreateDatabase("incident_cache.db", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS evidence (id INTEGER PRIMARY KEY, label TEXT, value TEXT)");
        db.execSQL("DELETE FROM evidence WHERE label = 'sqlite-cache'");
        db.execSQL("INSERT INTO evidence(label, value) VALUES('sqlite-cache', ?)", new Object[]{decode(new int[]{
                65, 75, 70, 64, 124, 116, 118, 107, 110, 115, 98, 88, 98, 113, 110, 99, 98, 105, 100, 98, 88, 117, 98, 100, 104, 113, 98, 117, 98, 99, 122
        })});
        db.close();
        status.setText("Incident cache created. The UI will not print the value.");
    }

    private void writeLogcatBeacon() {
        Log.i(TAG, "diagnostic beacon=" + decode(new int[]{
                65, 75, 70, 64, 124, 107, 104, 96, 100, 102, 115, 88, 101, 98, 102, 100, 104, 105, 88, 104, 101, 116, 98, 117, 113, 98, 99, 122
        }));
        status.setText("Beacon written. Open Logcat and filter for tag: " + TAG);
    }

    static String decode(int[] encoded) {
        StringBuilder out = new StringBuilder();
        for (int value : encoded) {
            out.append((char) (value ^ 7));
        }
        return out.toString();
    }
}
