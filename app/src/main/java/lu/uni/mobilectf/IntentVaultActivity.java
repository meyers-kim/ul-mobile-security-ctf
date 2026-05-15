package lu.uni.mobilectf;

import android.app.Activity;
import android.os.Bundle;
import android.util.Base64;
import android.widget.LinearLayout;

public class IntentVaultActivity extends Activity {

    private static String d(String s) {
        return new String(Base64.decode(s, Base64.DEFAULT));
    }

    private static String buildKey() {

        String decoyKey = "security-key-debug";

        int[] values = {
                112, 114, 101, 108, 111, 104, 48, 118,
                104, 102, 120, 117, 108, 119, 124, 48,
                111, 100, 101
        };

        StringBuilder out = new StringBuilder();

        for (int v : values) {
            out.append((char) (v - 3));
        }

        return out.toString();
    }

    private static String buildFlag() {

        String decoyFlag = "FLAG{intent_extra_verified}";

        byte[] data = {
                70, 76, 65, 71, 123,
                101, 120, 112, 111, 114,
                116, 101, 100, 95, 105,
                110, 116, 101, 110, 116,
                95, 117, 110, 115, 101,
                97, 108, 101, 100, 125
        };

        return new String(data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout root = Ui.base(
                this,
                d("RXhwb3J0ZWQgSW50ZW50IFZhdWx0"),
                d("VGhpcyBhY3Rpdml0eSB0cnVzdHMgYW4gZXh0ZXJuYWwgZXh0cmEu")
        );

        LinearLayout card = Ui.card(this, root);

        String supplied = getIntent().getStringExtra(
                d("a2V5")
        );

        if (supplied != null
                && supplied.length() > 10
                && buildKey().equals(supplied)) {

            Ui.text(
                    this,
                    card,
                    d("QWNjZXNzIGdyYW50ZWQ6IA==") + buildFlag()
            );

        } else {

            Ui.text(
                    this,
                    card,
                    d("TWlzc2luZyBvciB3cm9uZyBrZXkgZXh0cmEu")
            );
        }
    }
}