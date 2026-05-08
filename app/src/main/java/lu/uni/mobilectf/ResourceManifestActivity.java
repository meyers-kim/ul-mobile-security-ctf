package lu.uni.mobilectf;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class ResourceManifestActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout root = Ui.base(this, "Resources and Manifest", "Some clues live outside Java source code.");
        LinearLayout card = Ui.card(this, root);
        Ui.text(this, card, "Hint 1: AndroidManifest.xml can contain metadata values.");
        Ui.text(this, card, "Hint 2: resources can contain arrays. Old encodings sometimes make readable text look wrong.");
        Ui.muted(this, card, "Try apktool, jadx resources view, or a decoded APK directory.");

        String[] parts = getResources().getStringArray(R.array.resource_fragments);
        if (parts.length == 0) {
            Ui.text(this, card, rot13(""));
        }
    }

    private static String rot13(String input) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c >= 'a' && c <= 'z') {
                output.append((char) ('a' + (c - 'a' + 13) % 26));
            } else if (c >= 'A' && c <= 'Z') {
                output.append((char) ('A' + (c - 'A' + 13) % 26));
            } else {
                output.append(c);
            }
        }
        return output.toString();
    }
}
