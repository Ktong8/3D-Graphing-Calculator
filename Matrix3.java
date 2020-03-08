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
public class Matrix3 {
    public double[] values;
    
    Matrix3(double[] values){
        this.values = values;
    }
    
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
    
    public Point rotate(Point orig){
         return new Point(
            orig.x * values[0] + orig.y * values[3] + orig.z * values[6],
            orig.x * values[1] + orig.y * values[4] + orig.z * values[7],
            orig.x * values[2] + orig.y * values[5] + orig.z * values[8]);
     }
    
     public Point crossDeterminant(){
         double x = values[0]*(values[4]*values[8]-values[5]*values[7]);
         double y = values[1]*(values[3]*values[8]-values[5]*values[6]);
         double z = values[2]*(values[3]*values[7]-values[4]*values[6]);
         return new Point(x,-y,z);
     }
}
