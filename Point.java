/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threed_calculator;

/**
 *
 * @author kevin
 * 
 * An Immutable Point class representing a point in 3D space in rectangular coordinates
 */
public class Point {
    private final double x, y, z;
    
    /**
     * Creates a new Point given the rectangular coordinates
     * @param x the x coordinate
     * @param y the y coordinate
     * @param z the z coordinate
     */
    public Point(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    /**
     * Multiplies each coordinate in the point by the given scalar
     * @param n The scalar to multiply with
     * @return A new Point object with the new coordinates
     */
    public Point scalarMultiply(double n) {
        return new Point(x*n, y*n, z*n);
    }
    
    /**
     * Adds another Point to the current Point
     * @param other The other Point object to add with
     * @return A New Point resulting from adding the original two Points
     */
    public Point add(Point other){
        return new Point(other.x + this.x, other.y+this.y, other.z+this.z);
    }
    
    /**
     * Gives the magnitude of the current Point
     * @return A double representing the magnitude of the current Point
     */
    public double magnitude() {
        return Math.sqrt(Math.pow(x, 2)+Math.pow(y, 2)+Math.pow(z,2));
    }
    
    /**
     * Computes the Dot Product of this Point with another Point
     * @param other the other Point to dot product with
     * @return the scalar result of the dot product
     */
    public double dotProduct(Point other) {
        return this.x * other.x + this.y * other.y + this.z * other.z;
    }
}
