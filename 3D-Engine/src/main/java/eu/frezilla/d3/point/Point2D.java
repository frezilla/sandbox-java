package eu.frezilla.d3.point;

public final class Point2D {
    
    private final float x;
    private final float y;
    
    private Point2D(float x, float y) {
        this.x = x;
        this.y = y;
    }
    
    public float getX() {
        return x;
    }
    
    public float getY() {
        return y;
    }
    
    public static Point2D of(float x, float y) {
        return new Point2D(x, y);
    }
    
    public static Point2D of(Point2D point2D) {
        if (point2D == null) throw new IllegalArgumentException("point2D est null");
        return point2D.toPoint2D();
    }
    
    public Point2D toPoint2D() {
        return new Point2D(x, y);
    }
        
}
