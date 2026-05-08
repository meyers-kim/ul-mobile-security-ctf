package lu.uni.mobilectf;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

final class ProgressStore {
    static final int INVALID = 0;
    static final int NEW = 1;
    static final int DUPLICATE = 2;

    private static final String PREFS = "flag_progress";
    private static final String KEY_SOLVED_HASHES = "solved_hashes";

    private ProgressStore() {}

    static int submit(Context context, String flag) {
        String hash = FlagValidator.validHashOrNull(flag);
        if (hash == null) {
            return INVALID;
        }

        Set<String> solved = solvedHashes(context);
        if (solved.contains(hash)) {
            return DUPLICATE;
        }

        solved.add(hash);
        preferences(context).edit().putStringSet(KEY_SOLVED_HASHES, solved).apply();
        return NEW;
    }

    static int solvedCount(Context context) {
        return solvedHashes(context).size();
    }

    static void reset(Context context) {
        preferences(context).edit().remove(KEY_SOLVED_HASHES).apply();
    }

    private static Set<String> solvedHashes(Context context) {
        Set<String> stored = preferences(context).getStringSet(KEY_SOLVED_HASHES, new HashSet<>());
        return new HashSet<>(stored);
    }

    private static SharedPreferences preferences(Context context) {
        return context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
    }
}
