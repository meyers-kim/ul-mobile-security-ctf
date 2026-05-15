package lu.uni.mobilectf;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.widget.LinearLayout;

public class DeepLinkVaultActivity extends Activity {

    private static String b(String v) {
        return new String(Base64.decode(v, Base64.DEFAULT));
    }

    private static String token() {

        String tkn = "admin-debug-token";

        int[] data = {
                95, 98, 105, 115, 94, 105, 42,
                95, 105, 114, 98, 42, 46, 48, 48, 52
        };

        StringBuilder out = new StringBuilder();

        for (int i : data) {
            out.append((char) (i + 3));
        }

        return out.toString();
    }

    private static String flag() {

        String whynot = "FLAG{static_analysis_win}";

        byte[] x = {
                70, 76, 65, 71, 123,
                100, 101, 101, 112, 108,
                105, 110, 107, 95, 116,
                111, 107, 101, 110, 95,
                97, 99, 99, 101, 112,
                116, 101, 100, 125
        };

        return new String(x);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout root = Ui.base(
                this,
                b("RGVlcCBMaW5rIFZhdWx0"),
                b("VGhpcyBzY3JlZW4gY2FuIGJlIG9wZW5lZCBmcm9tIG91dHNpZGUgdGhlIGFwcC4=")
        );

        LinearLayout card = Ui.card(this, root);

        Uri data = getIntent() == null
                ? null
                : getIntent().getData();

        String supplied = data == null
                ? null
                : data.getQueryParameter(
                b("dG9rZW4=")
        );

        if (supplied != null
                && supplied.length() > 8
                && token().equals(supplied)) {

            Ui.text(
                    this,
                    card,
                    b("QWNjZXNzIGdyYW50ZWQ6IA==") + flag()
            );

        } else {

            Ui.text(
                    this,
                    card,
                    b("QWNjZXNzIGRlbmllZC4gVGhlIHZhdWx0IGV4cGVjdHMgYSBxdWVyeSBwYXJhbWV0ZXIgbmFtZWQgdG9rZW4u")
            );
        }
    }
}