package com.badar.keylogger.Helpers;

import java.util.HashMap;
import java.util.Map;

public class KeyCodeConverter {

    private static final Map<Integer, String[]> KEY_MAP = new HashMap<>();
    static {
        // Numbers (top row)
        KEY_MAP.put(48, new String[]{"0", ")"});
        KEY_MAP.put(49, new String[]{"1", "!"});
        KEY_MAP.put(50, new String[]{"2", "@"});
        KEY_MAP.put(51, new String[]{"3", "#"});
        KEY_MAP.put(52, new String[]{"4", "$"});
        KEY_MAP.put(53, new String[]{"5", "%"});
        KEY_MAP.put(54, new String[]{"6", "^"});
        KEY_MAP.put(55, new String[]{"7", "&"});
        KEY_MAP.put(56, new String[]{"8", "*"});
        KEY_MAP.put(57, new String[]{"9", "("});

        // Alphabet (A-Z) - now properly handling case
        for (int i = 65; i <= 90; i++) {
            char lower = (char)(i + 32);
            KEY_MAP.put(i, new String[]{String.valueOf(lower), String.valueOf((char)i)});
        }

        // Numpad numbers
        KEY_MAP.put(96, new String[]{"0", "0"});
        KEY_MAP.put(97, new String[]{"1", "1"});
        KEY_MAP.put(98, new String[]{"2", "2"});
        KEY_MAP.put(99, new String[]{"3", "3"});
        KEY_MAP.put(100, new String[]{"4", "4"});
        KEY_MAP.put(101, new String[]{"5", "5"});
        KEY_MAP.put(102, new String[]{"6", "6"});
        KEY_MAP.put(103, new String[]{"7", "7"});
        KEY_MAP.put(104, new String[]{"8", "8"});
        KEY_MAP.put(105, new String[]{"9", "9"});

        // Special characters
        KEY_MAP.put(32, new String[]{" ", " "});  // Space
        KEY_MAP.put(13, new String[]{System.getProperty("line.separator"), "[ENTER]"});  // Enter
        KEY_MAP.put(9, new String[]{"[TAB]", "[TAB]"});  // Tab
        KEY_MAP.put(27, new String[]{"[ESC]", "[ESC]"});  // Escape
        KEY_MAP.put(46, new String[]{"[DEL]", "[DEL]"});  // Delete
        KEY_MAP.put(45, new String[]{"[INS]", "[INS]"});  // Insert
        KEY_MAP.put(36, new String[]{"[HOME]", "[HOME]"});  // Home
        KEY_MAP.put(35, new String[]{"[END]", "[END]"});  // End
        KEY_MAP.put(33, new String[]{"[PGUP]", "[PGUP]"});  // Page Up
        KEY_MAP.put(34, new String[]{"[PGDN]", "[PGDN]"});  // Page Down

        // Arrow keys
        KEY_MAP.put(37, new String[]{"[LEFT]", "[LEFT]"});  // Left
        KEY_MAP.put(38, new String[]{"[UP]", "[UP]"});  // Up
        KEY_MAP.put(39, new String[]{"[RIGHT]", "[RIGHT]"});  // Right
        KEY_MAP.put(40, new String[]{"[DOWN]", "[DOWN]"});  // Down

        // Function keys
        for (int i = 112; i <= 123; i++) {  // F1-F12
            KEY_MAP.put(i, new String[]{"[F" + (i-111) + "]", "[F" + (i-111) + "]"});
        }

        // Modifier keys
        KEY_MAP.put(16, new String[]{"[SHIFT]", "[SHIFT]"});  // Shift
        KEY_MAP.put(17, new String[]{"[CTRL]", "[CTRL]"});  // Ctrl
        KEY_MAP.put(18, new String[]{"[ALT]", "[ALT]"});  // Alt
        KEY_MAP.put(91, new String[]{"[WIN]", "[WIN]"});  // Windows key
        KEY_MAP.put(20, new String[]{"[CAPSLOCK]", "[CAPSLOCK]"});

        // Punctuation
        KEY_MAP.put(186, new String[]{";", ":"});  // ;:
        KEY_MAP.put(187, new String[]{"=", "+"});  // =+
        KEY_MAP.put(188, new String[]{",", "<"});  // ,<
        KEY_MAP.put(189, new String[]{"-", "_"});  // -_
        KEY_MAP.put(190, new String[]{".", ">"});  // .>
        KEY_MAP.put(191, new String[]{"/", "?"});  // /?
        KEY_MAP.put(192, new String[]{"`", "~"});  // `~
        KEY_MAP.put(219, new String[]{"[", "{"});  // [{
        KEY_MAP.put(220, new String[]{"\\", "|"});  // \|
        KEY_MAP.put(221, new String[]{"]", "}"});  // ]}
        KEY_MAP.put(222, new String[]{"'", "\""});  // '"
    };

//    private boolean isCapsLockOn() {
//        // 0x0001 checks the toggle state (1 = on, 0 = off)
//        return (GlobalKeyListenerService.User32Ex.INSTANCE.GetKeyState(WinUser.VK_SHIFT) & 0x0001) != 0;
//    }

    public static String getKeyName(int vkCode, boolean shiftPressed) {
        String[] mappings = KEY_MAP.get(vkCode);
        if (mappings != null) {
            // For non-alphabetic keys
            if (mappings[0].startsWith("[") && mappings[0].endsWith("]")) {
                return mappings[0];
            }

            // Handle alphabetic characters (A-Z)
            if (vkCode >= 65 && vkCode <= 90) {
//                boolean capsLockOn = (GlobalKeyListenerService.user32.GetKeyState(WinUser.VK_SHIFT) & 0x0001) != 0;
                boolean shouldUppercase = shiftPressed; // XOR operation
                return shouldUppercase ? mappings[1] : mappings[0];
            }

            // For all other keys
            return shiftPressed ? mappings[1] : mappings[0];
        }
        return "";
    }
}