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

import eu.frezilla.sandbox.image.utils.colors.ArgbUtils;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import lombok.AccessLevel;
import lombok.Getter;

public class Pic extends AbstractPicture implements Picture {

    private final ColorsIndexes colorsIndexes;
    private final int height;
    private final int[] pixels;
    private final int width;

    private Pic(int w, int h) {
        if (w <= 0 || h <= 0) {
            throw new IllegalArgumentException(String.format("Width (%d) and height(%d) cannot be <= 0", w, h));
        }
        colorsIndexes = new ColorsIndexes();
        width = w;
        height = h;
        
        int blackValue = ArgbUtils.toARGB(255, 0, 0, 0);
        if (!colorsIndexes.addColor(blackValue)) {
            throw new RuntimeException();
        }
        Color blackColor = colorsIndexes.getColorByValue(blackValue);
        pixels = new int[width * height];
        Arrays.fill(pixels, blackColor.getIndex());
    }
    
    private void checkCoordinates(int x, int y) {
        checkCoordinates(x, y, getWidth(), getHeight());
    }

    @Override
    public void changeColor(int oldColor, int newColor) {
        

    }

    @Override
    public Graphics2D createGraphics() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getARGB(int x, int y) {
        checkCoordinates(x, y);
        return colorsIndexes.getColorByIndex(pixels[x + getHeight()*y]).value;
    }

    @Override
    public int[][] getARGB(int x, int y, int w, int h) {
        checkCoordinates(x, y);
        
        final int picHeight = getHeight();
        int[][] argbTab = new int[h][w];
        
        return null;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getNbColors() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public void setARGB(int x, int y, int argb) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setARGB(int x, int y, int[][] argb) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BufferedImage toBufferedImage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Getter(AccessLevel.MODULE)
    private final class Color {

        private final int index;
        private int value;

        Color(int i) {
            if (i <= 0) {
                throw new IllegalArgumentException();
            }
            index = i;
        }

        Color setValue(int v) {
            value = v;
            return this;
        }
    }

    private final class ColorsIndexes {

        private final Map<Integer, Color> argb2ColorMap;
        private final LinkedList<Integer> freeIndexes;
        private final Map<Integer, Color> index2ColorMap;
        private int maxIndex;

        ColorsIndexes() {
            argb2ColorMap = new TreeMap<>();
            freeIndexes = new LinkedList<>();
            index2ColorMap = new TreeMap<>();
            maxIndex = 1;
        }

        boolean addColor(int argb) {
            boolean added;
            if (!argb2ColorMap.containsKey(argb)) {
                int index = getFreeIndex();
                Color color = new Color(index).setValue(argb);
                argb2ColorMap.put(argb, color);
                index2ColorMap.put(index, color);
                added = true;
            } else {
                added = false;
            }
            return added;
        }
        
        boolean changeColor(int index, int argb) {
            boolean changed;
            if (index2ColorMap.containsKey(index)) {
                if (argb2ColorMap.containsKey(argb)) {
                    changed = false;
                } else {
                    Color c = index2ColorMap.get(index);
                    c.setValue(argb);
                    argb2ColorMap.put(argb, c);
                    changed = true;                    
                }
            } else {
                changed = false;
            }
            return changed;
        }
        
        Color getColorByIndex(int index) {
            return index2ColorMap.get(index);
        }

        Color getColorByValue(int argb) {
            return argb2ColorMap.get(argb);
        }        

        private int getFreeIndex() {
            int index;
            if (freeIndexes.isEmpty()) {
                index = maxIndex;
                maxIndex++;
            } else {
                index = freeIndexes.removeFirst();
            }
            return index;
        }
        
        int getNbColors() {
            return argb2ColorMap.size();
        }

        void removeColorByIndex(int index) {
            if (index2ColorMap.containsKey(index)) {
                Color color = index2ColorMap.remove(index);
                argb2ColorMap.remove(color.getValue());
            }
        }

        void removeColorByValue(int argb) {
            if (argb2ColorMap.containsKey(argb)) {
                Color color = argb2ColorMap.remove(argb);
                index2ColorMap.remove(color.getIndex());
            }
        }
    }
}
