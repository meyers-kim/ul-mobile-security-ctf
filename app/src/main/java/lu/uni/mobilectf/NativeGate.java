package lu.uni.mobilectf;

final class NativeGate {
    private NativeGate() {}

    static String checkPhrase(String phrase) {
        if ("belval-sensor".equals(phrase)) {
            return decode(new int[]{
                    65, 75, 70, 64, 124, 102, 101, 110, 88, 96, 102, 115, 98, 88, 119, 111, 117, 102, 116, 98, 88, 102, 100, 100, 98, 119, 115, 98, 99, 122
            });
        }
        return "wrong phrase";
    }

    private static String decode(int[] encoded) {
        StringBuilder out = new StringBuilder();
        for (int value : encoded) {
            out.append((char) (value ^ 7));
        }
        return out.toString();
    }
}
