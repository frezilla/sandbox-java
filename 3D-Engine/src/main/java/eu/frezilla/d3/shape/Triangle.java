package eu.frezilla.d3.shape;

import eu.frezilla.d3.point.Point3D;

public final class Triangle extends Shape {
    
    private final Point3D point1;
    private final Point3D point2;
    private final Point3D point3;
    
    private Triangle(Point3D center, Point3D point1, Point3D point2, Point3D point3) {
        super(center);
        this.point1 = Point3D.of(point1);
        this.point2 = Point3D.of(point2);
        this.point3 = Point3D.of(point3);
    }
    
    public Point3D getPoint1() {
        return point1;
    }
    
    public Point3D getPoint2() {
        return point2;
    }
    
    public Point3D getPoint3() {
        return point3;
    }
    
    public static Triangle of(Point3D center, Point3D point1, Point3D point2, Point3D point3) {
        return new Triangle(center, point1, point2, point3);
    }
    
    public static Triangle of(Triangle triangle) {
        if (triangle == null) throw new IllegalArgumentException("triangle est null");
        return triangle.toTriangle();
    }
    
    public Triangle toTriangle() {
        return new Triangle(getCenter(), point1, point2, point3);
    }
    
}
