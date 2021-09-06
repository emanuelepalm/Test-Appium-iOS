package emu;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class AndroidEmu {
    private static String sdkPath = "C:" + File.separator + "ADB" + File.separator;
    private static String adbPath = sdkPath + "platform-tools" + File.separator + "adb";
    private static String emulatorPath = sdkPath +  "emulator" + File.separator + "emulator ";


    static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("win");
    }

    static String npm = isWindows() ? "npm.cmd" : "npm";
    /**
     * Starts an emulator
     *
     *
     */
    public static void launchEmulator() {
        System.out.println("Starting emulator...");
        String[] aCommand = new String[] { npm,"run-script","start"};
        try {
                Process process = new ProcessBuilder(aCommand).start();
                BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
                BufferedReader br1 = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                String line1 = null;
                while ((line1 = br1.readLine()) != null) {
                    System.out.println(line);
                }
                process.waitFor(60, TimeUnit.SECONDS);
                System.out.println("Waiting");
                Thread.sleep(5000);


            System.out.println("Emulator launched successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean checkDevices() {
        System.out.println("ADB DEVICES");
        String[] aCommand = new String[]{"adb","devices"};
        try {
            Process process = new ProcessBuilder(aCommand).start();
            String error = error(process);
            String output = output(process);

            if(output != "" && error != "+") {
                if(output.contains("offline"))
                        return false;
            }
            process.waitFor(60, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static String error(Process process) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        String error = "";
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
            error += line;
        }
        return error;
    }

    private static String output(Process process) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String output = "";
        String line;
        boolean emu = false;
        while ((line = br.readLine()) != null && !emu) {
            System.out.println(line);
            output += line;
            emu = line.contains("emulator");
        }
        System.out.println(line);
        output += line;
        return output;
    }

    public static void closeEmu() {
        System.out.println("Killing emulator session");
        String[] aCommand = new String[]{"adb","emu","kill"};
        try {
            Process process = new ProcessBuilder(aCommand).start();
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            BufferedReader br1 = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String line1 = null;
            while ((line1 = br1.readLine()) != null) {
                System.out.println(line);
            }
            process.waitFor(60, TimeUnit.SECONDS);
            System.out.println("Waiting");
            Thread.sleep(5000);


            System.out.println("Session Successfully killed!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
