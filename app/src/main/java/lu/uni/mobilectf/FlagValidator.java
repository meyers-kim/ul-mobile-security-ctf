package lu.uni.mobilectf;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

final class FlagValidator {
    private static final Set<String> VALID_HASHES = new HashSet<>(Arrays.asList(
            "6649ed75e8749915c34646cb72c865fda11d83a5bb4913ad2f9d072985931b75",
            "2481e5131606c63c4dcdc7e222e0e8d5365f511a2fb84410034d170827a58842",
            "875de93a313ea9adcabea94a01fab4011e91f9370687898b949112f4f27b791d",
            "77249234db19ea5549305778cf5a5eeddc75905a05371ff08a145d982d08d6fd",
            "6a7e4fe5ee8eafe36b502c55f24dcd00efcb32d113513b6ec0cb1476d703a1aa",
            "544d66d651c5ed35a49fee531aec5b3360ac9b518e4caed255ac473a8127b96e",
            "d03a7ef33baa4749f20dbbde1c5dcf4d57cd4bce9e035b1bf8ddd8eaf44dbdd0",
            "11b7290074790ad0e5c9c388c1d01d1f6f4fe0788199f1491231af070fdaa9a2",
            "9ebd428fd6b6ac2168734c48d3cab73bc293a9ee54c9cafdffc94db296498041",
            "954528c5b225be8b292e60735e9156002052e823342d296f4dcebd11228fec25",
            "275ad3aee127968d79df49bda0bf4166603c709c292a3b1fb903081c2fcc7930",
            "fdd47b2dbb402900f38ec434906ab2bede085accbcafb9e618af142ae8899e9f",
            "02967c941f8698b5838030842d8077882e20ec1ec83999a44cedd61e171d7003",
            "8e6d82822cb1aa9d74e6596d720c56f992393aabc34f5512c5e1aed5724cd1fc",
            "a3e864b1ca1e4ee912327424e5fa4d4fb2cb81595ac171e484f03e9d5338b930",
            "c3a4dfe340b9cc946f300c6f7cb691460e17091aea09b563c1a3afa2eeee62e3",
            "26e3e13ff8b68e552b5787063291af1feb5077ec5163fe210f89e288c86013a5",
            "8d599e3903765260a6ecfc9357bc682e363a6afc59ae76088b5e8c1e3419baa4"
    ));

    private FlagValidator() {}

    static int totalFlags() {
        return VALID_HASHES.size();
    }

    static String validHashOrNull(String flag) {
        if (flag == null) return null;
        String hash = sha256(flag.trim());
        return VALID_HASHES.contains(hash) ? hash : null;
    }

    private static String sha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder hex = new StringBuilder();
            for (byte b : hash) {
                hex.append(String.format("%02x", b));
            }
            return hex.toString();
        } catch (Exception e) {
            return "";
        }
    }
}
