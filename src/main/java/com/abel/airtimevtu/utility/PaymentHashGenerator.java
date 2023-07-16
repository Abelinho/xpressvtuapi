package com.abel.airtimevtu.utility;

import org.apache.commons.codec.binary.Hex;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Component
public class PaymentHashGenerator {

//        public static void main() {
//
//            //convert request to JSON using gson or ObjectMapper
//            String toHash = "{
//            " +
//            "    "size":1,
//            " +
//            "    "page": 1
//            " +
//            "}";
//
//            var result = calculateHMAC512(toHash, "2FMm1t1c30iQLCMNZADpxb7CI0TLZgOF_CVASPRV");
//
//            System.out.println(result);
//        }

        public String calculateHMAC512(String data, String key) {
            String HMAC_SHA512 = "HmacSHA512";

            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), HMAC_SHA512);
            Mac mac = null;
            try {
                mac = Mac.getInstance(HMAC_SHA512);
                mac.init(secretKeySpec);

                return Hex.encodeHexString(mac.doFinal(data.getBytes()));//get clarification on this
            } catch (NoSuchAlgorithmException | InvalidKeyException e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }
        }
    }



