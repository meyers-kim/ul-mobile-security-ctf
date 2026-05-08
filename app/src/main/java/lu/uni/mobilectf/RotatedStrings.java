package lu.uni.mobilectf;

final class RotatedStrings {
    private RotatedStrings() {}

    static String rebuild() {
        int[] shifted = new int[] {
                72, 78, 67, 73, 125, 108, 99, 102, 122, 97, 117, 118, 116, 107, 112, 105, 117, 97, 116, 103, 100, 119, 107, 110, 118, 127
        };
        StringBuilder out = new StringBuilder();
        for (int value : shifted) {
            out.append((char) (value - 2));
        }
        return out.toString();
    }
}
