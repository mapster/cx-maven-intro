package no.computas.workshop.mavenintro;

import org.apache.commons.io.FileUtils;
//Se nærmere på disse pakkene, kan de ha sammenheng med hva som mangler i POM?
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

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
 * Tar screenshots av nettsider.
 */
@Path("webscreenshot")
public class WebScreenshotResource {
    private static HashMap<String, String> takenScreenshots = new HashMap<>();

    @POST
    @Produces("text/plain")
    public String postScreenshot(@FormParam("url") String url) throws IOException {
        //Kobler seg til Selenium, starter Firefox og maksimerer den.
        WebDriver driver = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), DesiredCapabilities.firefox());
        driver.manage().window().maximize();

        //Laster inn URL
        driver.get(url);

        //Tar screenshot og lagrer det blant temporære filer.
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File tempDir = new File(System.getProperty("java.io.tmpdir"));
        String filename = System.currentTimeMillis() + ".png";
        File tempFile = File.createTempFile(filename, ".tmp", tempDir);
        FileUtils.copyFile(screenshot, tempFile);

        driver.quit();

        //Legger stien til screenshot i et hashmap slik at det kan hentes igjen senere.
        takenScreenshots.put(filename, tempFile.getAbsolutePath());
        return filename;
    }


    @GET
    @Produces("image/png")
    public byte[] getImage(@QueryParam("filename") String filename) throws IOException {
        File scrFile = new File(takenScreenshots.get(filename));
        BufferedImage bufferedImage = ImageIO.read(scrFile);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", baos);
        byte[] imageData = baos.toByteArray();

        return imageData;
    }


}
