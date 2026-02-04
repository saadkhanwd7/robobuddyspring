package com.example.robobuddyspring.util;

import java.util.Objects;

public class PasswordUtil {

    public static String hash(String raw) {
        return Integer.toHexString(raw.hashCode());
    }

    public static boolean matches(String raw, String hash) {
        return Objects.equals(hash(raw), hash);
    }
}
