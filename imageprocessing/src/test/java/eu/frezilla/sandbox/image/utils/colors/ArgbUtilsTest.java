package eu.frezilla.sandbox.image.utils.colors;

import eu.frezilla.sandbox.image.utils.colors.ArgbUtils;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;

/**
 * Test des méthodes publiques de la classe ARGBUtils.
 */
public class ArgbUtilsTest {

    private static int COLOR, FULLCOLOR, ZEROCOLOR;

    @Test
    @DisplayName("Test de lecture de la composante transparente d'une couleur")
    public void getAlpha() {
        assertEquals(0, ArgbUtils.getAlpha(COLOR));
        assertEquals(255, ArgbUtils.getAlpha(FULLCOLOR));
        assertEquals(0, ArgbUtils.getAlpha(ZEROCOLOR));
    }

    @Test
    @DisplayName("Test de lecture de la composante bleue d'une couleur")
    public void getBlue() {
        assertEquals(255, ArgbUtils.getBlue(COLOR));
        assertEquals(255, ArgbUtils.getBlue(FULLCOLOR));
        assertEquals(0, ArgbUtils.getBlue(ZEROCOLOR));
    }

    @Test
    @DisplayName("Test de lecture de la composante verte d'une couleur")
    public void getGreen() {
        assertEquals(192, ArgbUtils.getGreen(COLOR));
        assertEquals(255, ArgbUtils.getGreen(FULLCOLOR));
        assertEquals(0, ArgbUtils.getGreen(ZEROCOLOR));
    }

    @Test
    @DisplayName("Test de lecture de la composante rouge d'une couleur")
    public void getRed() {
        assertEquals(85, ArgbUtils.getRed(COLOR));
        assertEquals(255, ArgbUtils.getRed(FULLCOLOR));
        assertEquals(0, ArgbUtils.getRed(ZEROCOLOR));
    }

    @BeforeAll
    public static void setUpBeforeAll() {
        COLOR = 0x55c0ff;
        FULLCOLOR = 0xffffffff;
        ZEROCOLOR = 0x00000000;
    }

    @Test
    @DisplayName("Test de \"création\" d'une couleur à partir des valeurs des composantes")
    public void toARGB() {
        assertEquals(COLOR, ArgbUtils.toARGB(0, 85, 192, 255));
        assertEquals(FULLCOLOR, ArgbUtils.toARGB(255, 255, 255, 255));
        assertEquals(ZEROCOLOR, ArgbUtils.toARGB(0, 0, 0, 0));
    }
}
