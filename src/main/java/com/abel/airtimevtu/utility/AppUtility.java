package com.abel.airtimevtu.utility;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class AppUtility {

    private static long sequence = 0;

    public static String sanitize(String data) {
        if (data.contains("_")) {
            data = data.replace("_", " ");
        } else if (data.contains("-")) {
            data = data.replace("-", " ");
        }
        return StringUtils.capitalize(data);
    }

    public static String generateUniqueId(String mobile) {

        String[] alphabets = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

        String uniqueId = "01" + alphabets[new SecureRandom(String.valueOf(System.currentTimeMillis()).getBytes(StandardCharsets.UTF_8)).nextInt(26)] + alphabets[new SecureRandom(String.valueOf(System.nanoTime()).getBytes(StandardCharsets.UTF_8)).nextInt(26)] + mobile.substring(5, 6);

        int pos = new SecureRandom(String.valueOf(System.currentTimeMillis()).getBytes(StandardCharsets.UTF_8)).nextInt(mobile.length());
        uniqueId += mobile.substring(pos, pos + 1);
        java.text.DecimalFormat sequ = new java.text.DecimalFormat("000");
        long nxtSeq = sequence++;
        if (nxtSeq > 998) {
            sequence = 0;
        }
        String inCnt = sequ.format(nxtSeq);
        uniqueId += inCnt;
        String hash = encodeMD5(mobile + new SimpleDateFormat("yyyyMMddHHmmssS").format(new Date()));
        pos = new SecureRandom(String.valueOf(System.nanoTime()).getBytes(StandardCharsets.UTF_8)).nextInt(32);
        uniqueId += hash.substring(pos, pos + 1).toUpperCase();
        pos = new SecureRandom(String.valueOf(System.currentTimeMillis()).getBytes(StandardCharsets.UTF_8)).nextInt(32);
        uniqueId += hash.substring(pos, pos + 1).toUpperCase();
        String randomNo = new SecureRandom(String.valueOf(System.nanoTime()).getBytes(StandardCharsets.UTF_8)).nextInt(10000) + "";
        while (randomNo.length() < 4) {
            randomNo = "0" + randomNo;
        }
        uniqueId += randomNo;
//        log.info("uniqueId -> {}", uniqueId);
        return uniqueId;
    }

    public static String encodeMD5(String data)
            throws NullPointerException {
        if (data == null) {
            throw new NullPointerException("data to encode cannot be null");
        }
        return DigestUtils.md5Hex(data);//do not change
    }

}
