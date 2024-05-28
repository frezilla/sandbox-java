package eu.frezilla.sandbox.image.objects;

import eu.frezilla.sandbox.image.objects.BufferedPicture;
import eu.frezilla.sandbox.image.utils.colors.ArgbUtils;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Test des méthodes publiques de la classe BufferedPicture.
 */
public class BufferedPictureTest {
    
    private static BufferedImage IMAGE;
    private BufferedPicture picture;
    
    @Test
    @DisplayName("Test de création d'un changement de couleur")
    public void changeColor() {
        int white = ArgbUtils.toARGB(255, 255, 255, 255);
        int black = ArgbUtils.toARGB(255, 0, 0, 0);
        
        picture.changeColor(white, black);
        assertEquals(picture.getARGB(0, 255), black);
        
        picture.changeColor(black, white);
        assertEquals(picture.getARGB(0, 255), white);
        assertEquals(picture.getARGB(0, 0), white);
    }
    
    @Test
    @DisplayName("Test de création d'un contexte graphique à partir d'un objet Picture")
    public void createGraphics() {
        Graphics2D g2d = picture.createGraphics();
        assertNotNull(g2d);
        g2d.setColor(Color.red);
        g2d.fillRect(0, 0, picture.getWidth(), picture.getHeight());
        g2d.dispose();
    }
    
    @Test
    @DisplayName("Test de création d'un objet Picture à partir d'un objet Image")
    @SuppressWarnings("ThrowableResultIgnored")
    public void fromImage() {
        assertThrows(NullPointerException.class, () -> { BufferedPicture.fromImage(null); });
        assertNotNull(BufferedPicture.fromImage(IMAGE));
    }
    
    @Test
    @DisplayName("Test de lecture de la couleur d'un pixel")
    @SuppressWarnings("ThrowableResultIgnored")
    public void getARGB() {
        assertEquals(0xff000000, picture.getARGB(0, 0));
        assertEquals(0xffff0000, picture.getARGB(1, 255));
        assertEquals(0xff00ff00, picture.getARGB(2, 255));
        assertEquals(0xff0000ff, picture.getARGB(3, 255));
        
        assertThrows(IllegalArgumentException.class, () -> { picture.getARGB(-1, 0); });
        assertThrows(IllegalArgumentException.class, () -> { picture.getARGB(0, -1); });
        assertThrows(IllegalArgumentException.class, () -> { picture.getARGB(IMAGE.getWidth(), 0); });
        assertThrows(IllegalArgumentException.class, () -> { picture.getARGB(0, IMAGE.getHeight()); });
    }
    
    @Test
    @DisplayName("Test de lecture des couleurs d'une zone")
    @SuppressWarnings("ThrowableResultIgnored")
    public void getARGBArray() {
        assertArrayEquals(new int[][] {{ 0xff000000, 0xff000000, 0xff000000, 0xff000000}}, picture.getARGB(0, 0, IMAGE.getWidth(), 1));
        assertArrayEquals(new int[][] {{ 0xffffffff, 0xffff0000, 0xff00ff00, 0xff0000ff}}, picture.getARGB(0, 255, IMAGE.getWidth(), 1));
        assertArrayEquals(
                new int[][] {
                    { 0xfffefefe, 0xfffe0000, 0xff00fe00, 0xff0000fe}, 
                    { 0xffffffff, 0xffff0000, 0xff00ff00, 0xff0000ff}
                }, 
                picture.getARGB(0, 254, IMAGE.getWidth(), 2)
        );
        assertArrayEquals(new int[][] {{ 0xffffffff, 0xffff0000, 0xff00ff00, 0xff0000ff}}, picture.getARGB(0, 255, IMAGE.getWidth(), 2));
        assertArrayEquals(new int[][] {{ 0xff0000ff}}, picture.getARGB(3, 255, IMAGE.getWidth(), 2));
        
        assertThrows(IllegalArgumentException.class, () -> { picture.getARGB(-1, 0, 10, 10); });
        assertThrows(IllegalArgumentException.class, () -> { picture.getARGB(0, -1, 10, 10); });
        assertThrows(IllegalArgumentException.class, () -> { picture.getARGB(IMAGE.getWidth(), 0, 10, 10); });
        assertThrows(IllegalArgumentException.class, () -> { picture.getARGB(0, IMAGE.getHeight(), 10, 10); });
    }
    
    @Test
    @DisplayName("Test de lecture de la hauteur de l'image")
    public void getHeight() {
        assertEquals(picture.getHeight(), IMAGE.getHeight());
    }
    
    @Test
    @DisplayName("Test de lecture du nombre de couleurs de l'image")
    public void getNbColors() {
        assertEquals(picture.getNbColors(), 1021);
    }
    
    @Test
    @DisplayName("Test de lecture de la largeur de l'image")
    public void getWidth() {
        assertEquals(picture.getWidth(), IMAGE.getWidth());
    }
    
    @Test
    @DisplayName("Test de création d'un objet Picture à partir de dimension")
    @SuppressWarnings("ThrowableResultIgnored")
    public void newInstance() {
        assertNotNull(BufferedPicture.newInstance(100, 100));
        assertThrows(IllegalArgumentException.class, () -> { BufferedPicture.newInstance(0, 0); });
        assertThrows(IllegalArgumentException.class, () -> { BufferedPicture.newInstance(0, 100); });
        assertThrows(IllegalArgumentException.class, () -> { BufferedPicture.newInstance(100, 0); });
    }
    
    @Test
    @DisplayName("Test d'affectation d'une couleur à un pixel")
    public void setARGB() {
        int color = ArgbUtils.toARGB(255, 255, 0, 0);
        picture.setARGB(0, 0, color);
        assertEquals(picture.getARGB(0, 0), color);
    }
    
    @Test
    @DisplayName("Test d'affectation de couleurs")
    public void setARGBArray() {
        
        int[][] colors = new int[][] {{ ArgbUtils.toARGB(255, 0, 0, 0), ArgbUtils.toARGB(255, 255, 0, 0), ArgbUtils.toARGB(255, 0, 255, 0) },
            { ArgbUtils.toARGB(255, 0, 0, 255), ArgbUtils.toARGB(255, 0, 0, 0), ArgbUtils.toARGB(255, 255, 0, 0) },
            { ArgbUtils.toARGB(255, 0, 255, 0), ArgbUtils.toARGB(255, 0, 0, 255), ArgbUtils.toARGB(255, 0, 0, 0) }
        };
        
        picture.setARGB(0, 0, colors);
        
        int[][] picColors = picture.getARGB(0, 0, 3, 3);
        
        for (int j = 0; j < colors.length; j++) {
            assertArrayEquals(picColors[j], colors[j]);
        }
    }
    
    @BeforeEach
    public void setUp() {
        picture = (BufferedPicture) BufferedPicture.fromImage(IMAGE);
    }
    
    @BeforeAll
    public static void setUpBeforeAll() throws IOException {
        final int imgWidth = 4;
        final int imgHeight = 256;
        
        IMAGE = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_ARGB);
        
        for (int y = 0; y < imgHeight; y++) {
            IMAGE.setRGB(0, y, ArgbUtils.toARGB(255, y, y, y));
            IMAGE.setRGB(1, y, ArgbUtils.toARGB(255, y, 0, 0));
            IMAGE.setRGB(2, y, ArgbUtils.toARGB(255, 0, y, 0));
            IMAGE.setRGB(3, y, ArgbUtils.toARGB(255, 0, 0, y));
        }
    }
    
    @Test
    @DisplayName("Test de conversion vers un objet BufferedImage")
    public void toBufferedImage() {
        BufferedImage bi = picture.toBufferedImage();
        assertEquals(bi.getWidth(), picture.getWidth());
        assertEquals(bi.getHeight(), picture.getHeight());
        assertEquals(bi.getRGB(0, 255), ArgbUtils.toARGB(255, 255, 255, 255));
        assertEquals(bi.getRGB(1, 255), ArgbUtils.toARGB(255, 255, 0, 0));
        assertEquals(bi.getRGB(2, 255), ArgbUtils.toARGB(255, 0, 255, 0));
        assertEquals(bi.getRGB(3, 255), ArgbUtils.toARGB(255, 0, 0, 255));
    }
}
