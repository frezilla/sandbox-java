package eu.frezilla.d3.point;

public final class Point3D {
    
    private final float x;
    private final float y;
    private final float z;
    
    private Point3D(float x, float y, float  z) {
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
    
    public static Point3D of(float x, float y, float z) {
        return new Point3D(x, y, z);
    }
    
    public static Point3D of(Point3D point3D) {
        if (point3D == null) throw new IllegalArgumentException("point3D est null");
        return point3D.toPoint3D();
    }
    
    public Point3D toPoint3D() {
        return new Point3D(x, y, z);
    }
    
}
