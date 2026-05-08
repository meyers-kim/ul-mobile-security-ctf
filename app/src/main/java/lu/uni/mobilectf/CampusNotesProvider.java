package lu.uni.mobilectf;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;

public class CampusNotesProvider extends ContentProvider {
    @Override
    public boolean onCreate() {
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        MatrixCursor cursor = new MatrixCursor(new String[]{"id", "title", "body"});
        cursor.addRow(new Object[]{1, "triage", "Launcher and manifest reviewed."});
        cursor.addRow(new Object[]{2, "provider", decode(new int[]{
                65, 75, 70, 64, 124, 119, 117, 104, 113, 110, 99, 98, 117, 88, 105, 104, 115, 98, 116, 88, 107, 98, 102, 108, 98, 99, 122
        })});
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        return "vnd.android.cursor.dir/vnd.lu.uni.mobilectf.evidence";
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    private static String decode(int[] encoded) {
        StringBuilder out = new StringBuilder();
        for (int value : encoded) {
            out.append((char) (value ^ 7));
        }
        return out.toString();
    }
}
