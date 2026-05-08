package lu.uni.mobilectf;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;

public class WebViewChallengeActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout root = Ui.base(this, "WebView Console", "The dashboard is loaded from local web assets.");
        LinearLayout card = Ui.card(this, root);
        Ui.text(this, card, "JavaScript can hide logic just like Java code can. The UI only exposes guest mode.");

        WebView webView = new WebView(this);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/web/index.html");
        card.addView(webView, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                Ui.dp(this, 420)
        ));
    }
}
