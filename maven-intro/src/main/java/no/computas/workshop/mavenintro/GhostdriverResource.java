package no.computas.workshop.mavenintro;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import javax.imageio.ImageIO;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Enkel notatblokk.
 */
@Path("ghostdriver")
public class GhostdriverResource {
    @GET
    @Produces("image/png")
    public byte[] getScreenshot(@QueryParam("url") String url) throws IOException {
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
        //FileUtils.copyFile(scrFile, new File("src/main/webapp/screenshots/test.png"));
        driver.quit();

        BufferedImage bufferedImage = ImageIO.read(scrFile);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", baos);
        byte[] imageData = baos.toByteArray();


        return imageData;
    }

}
