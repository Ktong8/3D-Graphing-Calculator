/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tong_3dcalculator;

/**
 *
 * @author kevin
 */
public class Point {
    double x, y, z;
    
    public Point(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public Point scalarMultiply(double n){
        return new Point(x*n, y*n, z*n);
    }
    
    public Point add(Point other){
        return new Point(other.x + this.x, other.y+this.y, other.z+this.z);
    }
    
    public double magnitude(){
        return Math.sqrt(Math.pow(x, 2)+Math.pow(y, 2)+Math.pow(z,2));
    }
}
