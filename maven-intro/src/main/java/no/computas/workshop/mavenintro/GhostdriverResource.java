package no.computas.workshop.mavenintro;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import javax.imageio.ImageIO;
import javax.ws.rs.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

/**
 * Enkel notatblokk.
 */
@Path("ghostdriver")
public class GhostdriverResource {
    private static HashMap<String, String> takenScreenshots = new HashMap<String, String>();

    @POST
    @Produces("text/plain")
    public String postScreenshot(@FormParam("url") String url) throws IOException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setJavascriptEnabled(true);
        caps.setCapability("takesScreenshot", true);
        caps.setCapability(
                PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
                "/Users/nicho/Downloads/phantomjs-1.9.8-macosx/bin/phantomjs"
        );
        WebDriver driver = new PhantomJSDriver(caps);
        driver.manage().window().setSize(new Dimension(800, 800));


        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.connect();
        if (connection.getResponseCode() != 200) {
            throw new RuntimeException("URL did not return status code 200 OK.");
        }

        driver.get(url);
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File tempDir = new File(System.getProperty("java.io.tmpdir"));
        String name = System.currentTimeMillis() + ".png";

        File tempFile = File.createTempFile(name, ".tmp", tempDir);
        FileUtils.copyFile(scrFile, tempFile);

        driver.quit();

        takenScreenshots.put(name, tempFile.getAbsolutePath());
        return name;
    }


    @GET
    @Produces("image/png")
    public byte[] getImage(@QueryParam("imageName") String imageName) throws IOException {
        File scrFile = new File(takenScreenshots.get(imageName));

        BufferedImage bufferedImage = ImageIO.read(scrFile);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", baos);
        byte[] imageData = baos.toByteArray();


        return imageData;
    }


}
