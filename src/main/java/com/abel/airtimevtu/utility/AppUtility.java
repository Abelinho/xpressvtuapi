package com.abel.airtimevtu.utility;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class AppUtility {
    public static String sanitize(String data) {
        if (data.contains("_")) {
            data = data.replace("_", " ");
        } else if (data.contains("-")) {
            data = data.replace("-", " ");
        }
        return StringUtils.capitalize(data);
    }
}
