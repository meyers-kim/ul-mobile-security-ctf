package lu.uni.mobilectf;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class IntentVaultActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout root = Ui.base(this, "Exported Intent Vault", "This activity trusts an external extra.");
        LinearLayout card = Ui.card(this, root);
        String key = getIntent().getStringExtra("key");

        if ("mobile-security-lab".equals(key)) {
            Ui.text(this, card, "Access granted: FLAG{exported_intent_unsealed}");
        } else {
            Ui.text(this, card, "Missing or wrong key extra.");
        }
    }
}
