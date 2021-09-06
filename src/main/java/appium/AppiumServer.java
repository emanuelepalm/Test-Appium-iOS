package appium;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.sql.Time;
import java.time.LocalTime;

import static parameter.GlobalParameters.USERDIR;

public class AppiumServer {
    private final AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder();
    private AppiumDriverLocalService server;
    private int port;
    private final String appiumLogsLoc = "appium-logs";
    private final String logFileName = "logs";

    public int getPort(){
        return this.port;
    }

    public AppiumServer() {
        this.port = getAvailablePort();
        this.serviceBuilder.usingPort(port);
        this.server = AppiumDriverLocalService.buildService(serviceBuilder);
        this.server.start();
    }

    public void stop() {
        this.server.stop();
    }

    public AppiumDriverLocalService get() {
        return this.server;
    }

    public void redirectLog() {
        this.server.clearOutPutStreams();
        File directory = new File(USERDIR + File.separator + this.appiumLogsLoc);

        try {
            this.server.addOutPutStream(new FileOutputStream(USERDIR + File.separator + this.appiumLogsLoc + logFileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static int getAvailablePort() {
        int port = 4723;

        try {
            ServerSocket serverSocket = new ServerSocket(0);
            port = serverSocket.getLocalPort();
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return port;
    }
}