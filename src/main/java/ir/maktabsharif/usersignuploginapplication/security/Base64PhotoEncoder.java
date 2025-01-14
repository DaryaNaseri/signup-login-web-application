package ir.maktabsharif.usersignuploginapplication.security;

import java.util.Base64;

public class Base64PhotoEncoder {


    public static String base64PhotoEncode(byte[] inputBytes) {
        if (inputBytes == null || inputBytes.length == 0) {
            throw new IllegalArgumentException("Input byte array cannot be null or empty");
        }
        return Base64.getEncoder().encodeToString(inputBytes);
    }


    public static byte[] base64PhotoDecode(String base64String) {
        if (base64String == null || base64String.isEmpty()) {
            throw new IllegalArgumentException("Base64 string cannot be null or empty");
        }
        return Base64.getDecoder().decode(base64String);}
}
