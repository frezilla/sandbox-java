package eu.frezilla.d3.operation;

public final class Vector {
    
    private final float x;
    private final float y;
    private final float z;
    
    private Vector(float x, float y, float  z) {
        this.x = x;
        this.y= y;
        this.z = z;
    }
    
    public float getX() {
        return x;
    }
    
    public float getY() {
        return y;
    }
    
    public float getZ() {
        return z;
    }
    
    public static Vector of(float x, float y, float z) {
        return new Vector(x, y, z);
    }
    
    public static Vector of(Vector vector) {
        if (vector == null) throw new IllegalArgumentException("vector est null");
        return vector.toVector();
    }
    
    public Vector toVector() {
        return new Vector(x, y, z);
    }
    
}
