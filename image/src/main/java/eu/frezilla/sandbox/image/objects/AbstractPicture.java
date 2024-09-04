package eu.frezilla.sandbox.image.objects;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.NONE)
abstract class AbstractPicture {
    
    protected final void checkCoordinates(int x, int y, int imgWidth, int imgHeight) {
        if (x >= imgWidth || y >= imgHeight || x < 0 || y < 0) {
            throw new IllegalArgumentException(String.format("x (%d) muste be between 0 and width (%d) and y (%d) must be between 0 and height (%d)", x, imgWidth, y, imgHeight));
        }
    }
}
