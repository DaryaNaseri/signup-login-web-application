package ir.maktabsharif.usersignuploginapplication.security;

import java.util.Base64;

public class Base64PhotoEncoder {


    public static String base64PhotoEncode(byte[] inputBytes) {
        Base64.Encoder encoder = Base64.getEncoder();
        String encoded = encoder.encodeToString(inputBytes);
        return encoded;
    }




    public static byte[] base64PhotoDecode(String base64String) {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decoded = decoder.decode(base64String);
        return decoded;
    }
}
