package lu.uni.mobilectf;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NativeGateActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout root = Ui.base(this, "ABI Gate", "A small phrase gate represents the native-analysis part of the lab.");
        LinearLayout card = Ui.card(this, root);
        Ui.text(this, card, "The passphrase is not printed in the UI. Inspect the gate logic and recover the accepted value.");

        EditText input = new EditText(this);
        input.setHint("passphrase");
        input.setSingleLine(true);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        card.addView(input);

        TextView result = Ui.text(this, card, "No phrase checked yet.");
        Ui.button(this, card, "Check phrase", v -> result.setText(NativeGate.checkPhrase(input.getText().toString())));
    }
}
