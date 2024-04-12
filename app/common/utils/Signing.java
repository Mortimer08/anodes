package common.utils;

import common.Data;
import org.apache.commons.codec.binary.Base64;
import play.Logger;
import play.Play;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.concurrent.TimeUnit;

public class Signing {
    public static final String KEY = Play.configuration.getProperty("application.secret");
    public static final Long MAX_AGE_MILLIS = TimeUnit.DAYS.toMillis(Integer.parseInt(Data.DAYS_FOR_ACTIVATION));

    public static String dump(final String value) {
        final String base64 = new String(Base64.encodeBase64(value.getBytes()));
        return signTimestamp(base64);
    }

    private static String signTimestamp(final String value) {
        long currentTimeMillis = System.currentTimeMillis();
        final String timestamp = Base64.encodeBase64URLSafeString(longToBytes(currentTimeMillis));
        String unsigned = String.format("%s:%s", value, timestamp);
        return sign(unsigned);
    }

    private static String unsignTimeStamp(final String signedValue) {
        final String result = unsign(signedValue);
        if (result == null)
            return null;
        int index = result.lastIndexOf(":");
        if (index > 0) {
            final String value = result.substring(0, index);
            final String sig = result.substring(index + 1);
            long timestamp = bytesToLong(Base64.decodeBase64(sig));
            if (System.currentTimeMillis() - timestamp < MAX_AGE_MILLIS)
                return value;
        }
        return null;
    }

    private static byte[] longToBytes(long x) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.SIZE / Byte.SIZE);
        buffer.putLong(x);
        return buffer.array();
    }

    private static long bytesToLong(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.SIZE / Byte.SIZE);
        buffer.put(bytes);
        buffer.flip();//need flip
        return buffer.getLong();
    }

    private static String sign(final String value) {
        return String.format("%s:%s", value, hmac(value));
    }

    private static String unsign(final String signedValue) {
        int index = signedValue.lastIndexOf(":");
        if (index > 0) {
            final String value = signedValue.substring(0, index);
            final String sig = signedValue.substring(index + 1);
            Logger.error(value);
            Logger.error(sig);
            Logger.error(hmac(value));
            if (sig.equals(hmac(value)))
                return value;
        }
        return null;
    }

    public static String hmac(final String value) {
        try {
            byte[] keyBytes = KEY.getBytes();
            Mac hmac = Mac.getInstance("HmacSHA1");
            SecretKeySpec macKey = new SecretKeySpec(keyBytes, "HmacSHA1");
            hmac.init(macKey);
            return Base64.encodeBase64URLSafeString(hmac.doFinal(value.getBytes()));
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
    }

    public static String load(final String value) {
        final String base64 = unsignTimeStamp(value);
        if (base64 != null)
            return new String(Base64.decodeBase64(base64));
        return null;
    }
}
