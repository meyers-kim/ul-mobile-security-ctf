package lu.uni.mobilectf;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.LinearLayout;

public class DeepLinkVaultActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout root = Ui.base(this, "Deep Link Vault", "This screen can be opened from outside the app.");
        LinearLayout card = Ui.card(this, root);

        Uri data = getIntent() == null ? null : getIntent().getData();
        String token = data == null ? null : data.getQueryParameter("token");

        if ("belval-blue-1337".equals(token)) {
            Ui.text(this, card, "Access granted: FLAG{deeplink_token_accepted}");
        } else {
            Ui.text(this, card, "Access denied. The vault expects a query parameter named token.");
        }
    }
}
