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
package eu.frezilla.sandbox.image.utils.colors;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Utilitaire d'accès aux informations d'une couleur au format ARGB sur 32 bits.
 * <pre>
 * Les informations des différents canaux sont répartis comme suit :
 * - 8 bits pour la couche de transparence Alpha
 * - 8 bits pour la couche rouge
 * - 8 bits pour la couche verte
 * - 8 bits pour la couche bleue
 *
 * Les valeurs pour chacun des canaux, sont comprises entre 0 et 255.
 * </pre>
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ArgbUtils {

    private static int filterByte(int v32Bits, int offset) {
        return (v32Bits >> offset) & 0xff;
    }

    /**
     * Retourne la valeur de la couche de transparence.
     *
     * @param argb valeur de la couleur
     * @return valeur de la couche de transparence
     */
    public static int getAlpha(int argb) {
        return getByte(argb, 0);
    }

    /**
     * Retourne la valeur de la couche bleue.
     *
     * @param argb valeur de la couleur
     * @return valeur de la couche bleue
     */
    public static int getBlue(int argb) {
        return getByte(argb, 3);
    }

    private static int getByte(int v32Bits, int byteNumber) {
        if (byteNumber < 0 && byteNumber > 3) {
            throw new IllegalArgumentException();
        }
        return filterByte(v32Bits, (3 - byteNumber) * 8);
    }

    /**
     * Retourne la valeur de la couche verte.
     *
     * @param argb valeur de la couleur
     * @return valeur de la couche verte
     */
    public static int getGreen(int argb) {
        return getByte(argb, 2);
    }

    /**
     * Retourne la valeur de la couche rouge.
     *
     * @param argb valeur de la couleur
     * @return valeur de la couche rouge
     */
    public static int getRed(int argb) {
        return getByte(argb, 1);
    }

    private static int normalizeValue(int v) {
        return Math.min(Math.max(0, v), 255);
    }

    /**
     * Retourne la valeur d'une couleur composée des quatres couches A, R, G, B.
     *
     * @param alpha valeur de la transparence
     * @param red valeur de la couche rouge
     * @param green valeur de la couche verte
     * @param blue valeur de la couche bleue
     * @return
     */
    public static int toARGB(int alpha, int red, int green, int blue) {
        return (normalizeValue(alpha) << 24) | (normalizeValue(red) << 16) | (normalizeValue(green) << 8) | normalizeValue(blue);
    }
}
