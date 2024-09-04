package eu.frezilla.sandbox.image.utils.pictures;

import eu.frezilla.sandbox.image.utils.pictures.PictureUtils;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.image.BufferedImage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;

/**
 * Test des méthodes publiques de la classe PictureUtils.
 */
public class PictureUtilsTest {

    private static Image IMAGE;

    @BeforeAll
    public static void setUpBeforeAll() {
        IMAGE = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration().createCompatibleVolatileImage(640, 480);
    }

    @Test
    @DisplayName("Test de conversion d'un objet Image vers un objet BufferedImage")
    public void toBufferedImage() {
        assertNull(PictureUtils.toBufferedImage(null, BufferedImage.TYPE_INT_ARGB));
        BufferedImage bi = PictureUtils.toBufferedImage(IMAGE, BufferedImage.TYPE_INT_ARGB);
        assertNotNull(bi);
        assertEquals(IMAGE.getWidth(null), bi.getWidth());
        assertEquals(IMAGE.getHeight(null), bi.getHeight());
    }
}
