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

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public interface Picture {
    
    /**
     * Modifie une couleur par une autre pour tous les pixels de l'image.
     * <p>
     * Les valeurs des couleurs sont au format ARGB sur 32 bits (8 bits pour la
     * transparence, 8 bits pour le rouge, 8 bits pour le vert et 8 bits pour le
     * bleu)
     * 
     * @param oldColor Valeur de la couleur à remplacer
     * @param newColor Nouvelle valeur de la couleur
     */
    void changeColor(int oldColor, int newColor);

    /**
     * Créé un <code>Graphics2D</code> qui peut être utilisé pour dessiner "à
     * l'intérieur" de l'objet courant.
     *
     * @return Graphics2D
     * @see Graphics2D
     */
    Graphics2D createGraphics();

    /**
     * Retourne la couleur au format ARGB sur 32 bits (8 bits pour la
     * transparence, 8 bits pour le rouge, 8 bits pour le vert et 8 bits pour le
     * bleu) du pixel situé à la position spécifiée.
     *
     * @param x Abscisse
     * @param y Ordonnée
     * @return Couleur du pixel
     */
    int getARGB(int x, int y);

    /**
     * Retourne les couleurs au format ARGB sur 32 bits (8 bits pour la
     * transparence, 8 bits pour le rouge, 8 bits pour le vert et 8 bits pour le
     * bleu) des pixels situés dans la zone définie par une origine [x, y] et
     * des dimensions [width, height].
     * <p>Les dimensions du tableau retourné peuvent différées des dimensions 
     * passées en paramètre (dans le cas où la zone se situe au bord de l'image
     * par exemple)
     *
     * @param x Abscisse
     * @param y Ordonnée
     * @param width Largeur de la zone de sélection
     * @param height Hauteur de la zone de sélection
     * @return Tableau des couleurs des pixels
     */
    int[][] getARGB(int x, int y, int width, int height);
    
    /**
     * Retourne la hauteur de l'image.
     *
     * @return Hauteur
     */
    int getHeight();
    
    /**
     * Retourne le nombre de couleurs de l'image.
     * 
     * @return Nombre de couleur
     */
    int getNbColors();
    
    /**
     * Retourne la largeur de l'image.
     *
     * @return Largeur
     */
    int getWidth();
    
    /**
     * Défini la couleur au format ARGB sur 32 bits (8 bits pour la 
     * transparence, 8 bits pour le rouge, 8 bits pour le vert et 8 bits pour le
     * bleu) du pixel situé à la position spécifiée.
     * 
     * @param x Abscisse
     * @param y Ordonnée
     * @param argb Valeur de la couleur
     */
    void setARGB(int x, int y, int argb);
    

    /**
     * Défini les couleurs au format ARGB sur 32 bits (8 bits pour la 
     * transparence, 8 bits pour le rouge, 8 bits pour le vert et 8 bits pour le
     * bleu) des pixels situés dans la zone définie par son origine [x, y].
     * 
     * @param x Abscisse
     * @param y Ordonnée
     * @param argb Valeurs des couleurs
     */
    void setARGB(int x, int y, int[][] argb);
    
    /**
     * Retourne une représentation de l'image convertie dans un objet 
     * <code>BufferedImage</code>.
     * <p>
     * Le type de l'objet <code>BufferedImage</code> retourné est égal à 
     * <code>BufferedImage.TYPE_INT_ARGB</code>.
     * 
     * @return objet BufferedImage
     * @See BufferedImage
     */
    BufferedImage toBufferedImage();
}
