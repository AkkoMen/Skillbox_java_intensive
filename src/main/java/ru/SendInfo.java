package main.java.ru;

import com.dropbox.core.v2.DbxClientV2;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SendInfo extends Thread {

    private DbxClientV2 client;
    private BufferedImage bufferedImage;
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd hh mm ss a");

    public SendInfo(DbxClientV2 client, BufferedImage bufferedImage) {
        this.client = client;
        this.bufferedImage = bufferedImage;
    }

    @Override
    public void run() {

        Date date = new Date();
        String fileName = "/" + formatter.format(date) + ".png";

        try {
            ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", arrayOutputStream);
            InputStream inputStream = new ByteArrayInputStream(arrayOutputStream.toByteArray());
            client.files().uploadBuilder(fileName).uploadAndFinish(inputStream);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
