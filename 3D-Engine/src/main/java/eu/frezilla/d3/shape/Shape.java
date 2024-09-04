package eu.frezilla.d3.shape;

import eu.frezilla.d3.operation.Vector;
import eu.frezilla.d3.point.Point3D;

abstract class Shape {
    
    private Point3D center;
    
    public Shape(Point3D center) {
        this.center = Point3D.of(center);
    }
    
    public final Point3D getCenter() {
        return center;
    }
    
    public final void move(Vector vector) {
        if (vector == null) throw new IllegalArgumentException("vector est null");
        center = Point3D.of(
                center.getX() + vector.getX(), 
                center.getY() + vector.getY(), 
                center.getZ() + vector.getZ()
        );
    }
    
}
