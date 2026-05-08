package lu.uni.mobilectf;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {
    private TextView progressText;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSharedPreferences("campus_profile", MODE_PRIVATE)
                .edit()
                .putString("marker", "FLAG{prefs_marker_recovered}")
                .putString("course", "Security of Mobiles")
                .putString("note", "A debug build can be inspected with adb run-as.")
                .apply();

        LinearLayout root = Ui.base(this, "UL Mobile Security CTF", "Belval campus incident-response console");

        LinearLayout intro = Ui.card(this, root);
        Ui.text(this, intro, "A fictional campus mobile app has been recovered during an incident. Your task is to inspect the APK, understand its Android components, and recover hidden flags.");
        Ui.muted(this, intro, "Scope: use only this app, your own emulator/device, and standard mobile security analysis tools.");

        progressText = Ui.text(this, intro, "Progress loading...");
        progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal);
        progressBar.setMax(FlagValidator.totalFlags());
        intro.addView(progressBar, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                Ui.dp(this, 22)
        ));

        Ui.button(this, intro, "Submit a flag", v -> Ui.open(this, SubmitFlagActivity.class));

        Ui.section(this, root, "Static analysis");
        LinearLayout staticCard = Ui.card(this, root);
        Ui.button(this, staticCard, "APK entry and app notes", v -> Ui.open(this, AboutActivity.class));
        Ui.button(this, staticCard, "Assets and archives", v -> Ui.open(this, AssetChallengeActivity.class));
        Ui.button(this, staticCard, "Resources and manifest", v -> Ui.open(this, ResourceManifestActivity.class));
        Ui.button(this, staticCard, "Instrumentation lab", v -> Ui.open(this, InstrumentationLabActivity.class));

        Ui.section(this, root, "Components and local storage");
        LinearLayout compCard = Ui.card(this, root);
        Ui.button(this, compCard, "Local preferences", v -> Ui.open(this, PreferencesChallengeActivity.class));
        Ui.button(this, compCard, "Storage and logs", v -> Ui.open(this, StorageLogActivity.class));
        Ui.button(this, compCard, "Content provider notes", v -> Ui.open(this, ProviderChallengeActivity.class));
        Ui.button(this, compCard, "Tap sequence", v -> Ui.open(this, TapSequenceActivity.class));

        Ui.section(this, root, "Runtime surfaces");
        LinearLayout runtimeCard = Ui.card(this, root);
        Ui.button(this, runtimeCard, "WebView console", v -> Ui.open(this, WebViewChallengeActivity.class));
        Ui.button(this, runtimeCard, "ABI gate", v -> Ui.open(this, NativeGateActivity.class));
        Ui.button(this, runtimeCard, "Network sync", v -> Ui.open(this, NetworkSyncActivity.class));

        Ui.muted(this, root, "External entry points include a deep link, an exported activity, a broadcast receiver, and a content provider. The manifest is part of the challenge.");
        courseEntryFlag();
        updateProgress();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateProgress();
    }

    private void updateProgress() {
        if (progressText == null || progressBar == null) return;
        int solved = ProgressStore.solvedCount(this);
        int total = FlagValidator.totalFlags();
        progressBar.setProgress(solved);
        progressText.setText("Current progress on this device: " + solved + " / " + total + " flags");
    }

    private String courseEntryFlag() {
        return "FLAG{apk_entry_checked}";
    }
}
