/**
 * MIT License
 *
 * Copyright (c) 2019 frezilla
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package eu.frezilla.sandbox.image.utils.pictures;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Utilitaire de manipulation d'images.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PictureUtils {

    /**
     * Créé une image de type <code>BufferedImage</code> à partir d'une image
     * <code>Image</code>.
     * <pre>
     * <u>Note</u>
     * Si l'image de référence vaut null, la valeur null est retournée.
     * </pre>
     *
     * @param img image de référence
     * @param imageType Type de l'image (voir les types définis dans la classe
     * {@link BufferedImage})
     * @return BufferedImage
     * @see Image
     * @see BufferedImage
     */
    public static BufferedImage toBufferedImage(Image img, int imageType) {
        if (img == null) {
            return null;
        }
        BufferedImage bi = new BufferedImage(img.getWidth(null), img.getHeight(null), imageType);
        Graphics2D g2d = bi.createGraphics();
        g2d.drawImage(img, 0, 0, null);
        g2d.dispose();
        return bi;
    }
}
