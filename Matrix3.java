/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threed_calculator;

import java.awt.Point;

/**
 *
 * @author kevin
 * 
 * A 3x3 Matrix class
 */
public class Matrix3 {
    // A row-major array of values in the matrix;
    private final double[] values;
    
    /**
     * Create a 3x3 matrix from the given row-major array
     * @param values
     */
    Matrix3(double[] values){
        assert(values.length == 9);
        this.values = values;
    }
    
    /**
     * Matrix multiply this Matrix3 with another Matrix3
     * @param other the other Matrix3 to multiply with
     * @return The resulting Matrix3 from the matrix multiplication
     */
    public Matrix3 multiply(Matrix3 other){
        double[] result = new double[9];
        for(int row = 0; row<3; row++){
            for(int col = 0; col <3; col++){
                for(int i = 0; i<3; i++){
                    result[row*3 + col] += this.values[row*3 + i] * other.values[i*3+col];
                }
            }
        }
        return new Matrix3(result);
    }
    
    /**
     * Use this Matrix3 as a rotation matrix to the given point
     * @param orig The original point to be rotated
     * @return The result of rotating orig using this rotation matrix
     */
    public Point rotate(Point orig){
        return new Point(
            orig.x * values[0] + orig.y * values[3 * 1] + orig.z * values[3 * 2],
            orig.x * values[3 * 0 + 1] + orig.y * values[3 * 1 + 1] + orig.z * values[3 * 2 + 1],
            orig.x * values[3 * 0 + 2] + orig.y * values[3 * 2 + 2] + orig.z * values[3 * 2 + 2]);
     }
    
     /**
      * Gives the cross product determinant
      * @return A new point representing this determinant
      */
     public Point crossDeterminant(){
         double x = values[0]*(values[4]*values[8]-values[5]*values[7]);
         double y = values[1]*(values[3]*values[8]-values[5]*values[6]);
         double z = values[2]*(values[3]*values[7]-values[4]*values[6]);
         return new Point(x,-y,z);
     }
}
