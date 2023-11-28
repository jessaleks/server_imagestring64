package com.aleksanderjess;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Base64;
import java.util.Optional;


import io.quarkiverse.bucket4j.runtime.RateLimited;
import io.quarkiverse.bucket4j.runtime.resolver.IdentityResolver;
import io.quarkiverse.bucket4j.runtime.resolver.IpResolver;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;

import javax.imageio.ImageIO;

@Path("/api/image")
public class GreetingResource {

    @Produces("text/plain")
    @GET()
    public String hello(@QueryParam("imageUrl") String imageUrl) {
        // gets the image from the URL
        // encodes it into a base64 string and returns it
        if (!imageUrl.isEmpty()) {
            try (var inputStream = new URL(imageUrl).openStream()) {
                BufferedImage image = ImageIO.read(inputStream);
                var type = image.getType();
                BufferedImage outputImage = new BufferedImage(19,
                        12, type);
                Graphics2D g2d = outputImage.createGraphics();
                g2d.drawImage(image, 0, 0, 19, 18, null);
                g2d.dispose();

                var baos = new java.io.ByteArrayOutputStream();
                ImageIO.write(outputImage, "png", baos);
                baos.flush();
                var imageInByte = baos.toByteArray();
                baos.close();
                return "data:image/png;base64," + Base64.getEncoder().encodeToString(imageInByte);


            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return "No image URL provided";


    }
}
