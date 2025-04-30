package com.badar.keylogger.Helpers;

import java.util.HashMap;
import java.util.Map;

public class KeyCodeConverter {

    //store key values into a Map
    private static final Map<Integer, String> KEY_NAMES = new HashMap<>();

    static {
        // Alphabet keys
        for (int i = 65; i <= 90; i++) {
            KEY_NAMES.put(i, String.valueOf((char) i));
        }

        // Numbers (top row)
        for (int i = 48; i <= 57; i++) {
            KEY_NAMES.put(i, String.valueOf((char) i));
        }

        // Function keys
        for (int i = 112; i <= 123; i++) { // F1-F12
            KEY_NAMES.put(i, "F" + (i - 111));
        }

        // Special keys
        KEY_NAMES.put(8, "[BACKSPACE]");
        KEY_NAMES.put(9, "[TAB]");
        KEY_NAMES.put(13, "[ENTER]");
        KEY_NAMES.put(16, "[SHIFT]");
        KEY_NAMES.put(17, "[CTRL]");
        KEY_NAMES.put(18, "[ALT]");
        KEY_NAMES.put(20, "[CAPSLOCK]");
        KEY_NAMES.put(27, "[ESC]");
        KEY_NAMES.put(32, " ");
        KEY_NAMES.put(37, "[LEFT]");
        KEY_NAMES.put(38, "[UP]");
        KEY_NAMES.put(39, "[RIGHT]");
        KEY_NAMES.put(40, "[DOWN]");
        KEY_NAMES.put(45, "[INSERT]");
        KEY_NAMES.put(46, "[DELETE]");
        KEY_NAMES.put(91, "[WIN]");
        KEY_NAMES.put(144, "[NUMLOCK]");
        KEY_NAMES.put(160, "[LSHIFT]");
        KEY_NAMES.put(161, "[RSHIFT]");
        KEY_NAMES.put(186, ";");
        KEY_NAMES.put(187, "=");
        KEY_NAMES.put(188, ",");
        KEY_NAMES.put(189, "-");
        KEY_NAMES.put(190, ".");
        KEY_NAMES.put(191, "/");
        KEY_NAMES.put(192, "`");
        KEY_NAMES.put(219, "[");
        KEY_NAMES.put(220, "\\");
        KEY_NAMES.put(221, "]");
        KEY_NAMES.put(222, "'");
    }

    public static String getKeyName(int vkCode) {
        return KEY_NAMES.getOrDefault(vkCode, "VK_" + vkCode);
    }

    public static String getKeyName(int vkCode, boolean shiftPressed) {
        String base = KEY_NAMES.get(vkCode);

        if (base == null) {
            return "VK_" + vkCode;
        }

        // Handle shift modifications
        if (shiftPressed) {
            switch (vkCode) {
                case 48: return ")";
                case 49: return "!";
                case 50: return "@";
                case 51: return "#";
                case 52: return "$";
                case 53: return "%";
                case 54: return "^";
                case 55: return "&";
                case 56: return "*";
                case 57: return "(";
                case 186: return ":";
                case 187: return "+";
                case 188: return "<";
                case 189: return "_";
                case 190: return ">";
                case 191: return "?";
                case 192: return "~";
                case 219: return "{";
                case 220: return "|";
                case 221: return "}";
                case 222: return "\"";
            }
        }

        return base;
    }
}