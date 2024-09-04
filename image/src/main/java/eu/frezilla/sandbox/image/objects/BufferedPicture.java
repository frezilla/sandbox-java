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
package eu.frezilla.sandbox.image.objects;

import eu.frezilla.sandbox.image.utils.pictures.PictureUtils;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class BufferedPicture extends AbstractPicture implements Picture {

    private static final int TYPE = BufferedImage.TYPE_INT_ARGB;

    private final BufferedImage image;

    private BufferedPicture(int width, int height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException(String.format("Width (%d) and height(%d) cannot be <= 0", width, height));
        }
        image = new BufferedImage(width, height, TYPE);
    }

    private BufferedPicture(Image img) {
        Objects.requireNonNull(img, "img argument cannot be null");
        image = PictureUtils.toBufferedImage(img, TYPE);
    }

    @Override
    public void changeColor(int oldColor, int newColor) {
        int imgHeight = getHeight();
        int imgWidth = getWidth();
        
        int[] colors = new int[imgHeight * imgWidth];
        
        image.getRGB(0, 0, imgWidth, imgHeight, colors, 0, imgWidth);
        for (int i = 0; i < colors.length; i++) {
            if (colors[i] == oldColor) {
                colors[i] = newColor;
            }
        }
        
        image.setRGB(0, 0, imgWidth, imgHeight, colors, 0, imgWidth);
    }

    private void checkCoordinates(int x, int y) {
        checkCoordinates(x, y, getWidth(), getHeight());
    }

    @Override
    public Graphics2D createGraphics() {
        return image.createGraphics();
    }

    /**
     * Créé un nouvel objet <code>Picture</code> à partir d'une image
     * <code>Image</code>.
     *
     * @param image Image
     * @return Nouvel objet <code>Picture</code>
     * @see Image
     */
    public static Picture fromImage(Image image) {
        return new BufferedPicture(image);
    }

    @Override
    public int getARGB(int x, int y) {
        checkCoordinates(x, y);

        return image.getRGB(x, y);
    }

    @Override
    public int[][] getARGB(int x, int y, int width, int height) {
        int imgWidth = getWidth();
        int imgHeight = getHeight();

        checkCoordinates(x, y, imgWidth, imgHeight);

        int w = Math.min(width, imgWidth - x);
        int h = Math.min(height, imgHeight - y);

        int[][] rgbArray = new int[h][w];

        int array[] = image.getRGB(x, y, w, h, null, 0, w);
        for (int j = 0; j < h; j++) {
            for (int i = 0; i < w; i++) {
                rgbArray[j][i] = array[i + j * w];
            }
        }

        return rgbArray;
    }

    @Override
    public int getHeight() {
        return image.getHeight();
    }

    @Override
    public int getNbColors() {
        Set<Integer> colors = new HashSet<>();
        int width = getWidth();
        int height = getHeight();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int color = image.getRGB(x, y);
                colors.add(color);
            }
        }
        return colors.size();
    }

    @Override
    public int getWidth() {
        return image.getWidth();
    }

    /**
     * Créé un nouvel objet <code>Picture</code> à partir des dimensions passées
     * en paramètre.
     *
     * @param width largeur
     * @param height hauteur
     * @return Nouvel objet <code>Picture</code>
     */
    public static Picture newInstance(int width, int height) {
        return new BufferedPicture(width, height);
    }

    @Override
    public void setARGB(int x, int y, int argb) {
        checkCoordinates(x, y);

        image.setRGB(x, y, argb);
    }

    @Override
    public void setARGB(int x, int y, int[][] argb) {
        int imgWidth = getWidth();
        int imgHeight = getHeight();

        checkCoordinates(x, y, imgWidth, imgHeight);

        int height = argb.length;
        int width = argb[0].length;

        int w = Math.min(width, imgWidth - x);
        int h = Math.min(height, imgHeight - y);

        int[] array = new int[w * h];

        for (int j = 0; j < h; j++) {
            System.arraycopy(argb[j], 0, array, j * h, w);
        }

        image.setRGB(x, y, w, h, array, 0, w);
    }

    @Override
    public BufferedImage toBufferedImage() {
        return PictureUtils.toBufferedImage(image, TYPE);
    }

}
