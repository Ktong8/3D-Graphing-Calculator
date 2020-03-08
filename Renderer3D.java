/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threed_calculator;
import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.*;
/**
 *
 * @author kevin
 */
public class Renderer3D extends JFrame{
    private final Point[] xAxis = {new Point(-1,0,0), new Point(1,0,0)};
    private final Point[] yAxis = {new Point(0,-1,0), new Point(0,1,0)};
    private final Point[] zAxis = {new Point(0,0,-1), new Point(0,0,1)};
    private static JPanel renderPanel;
    private Function3D[] func;
    JSlider horizontalSlider;
    JSlider verticalSlider;
    double scale;
    
    public Renderer3D(String[] equations){
        super();
        setSize(1200,800);
        setVisible(true);
        setTitle("3D Graph Output");
        scale = 10;
        setTitle("3D Grapher");
        func = new Function3D[equations.length];
        for(int i = 0; i<func.length; i++){
            func[i] = new Function3D(equations[i]);
        }
        Container pane = getContentPane();
        pane.setLayout(new BorderLayout()); /*Note: this layout/design was modeled off someone else's 3D renderer
        *And the method of rotation is the same (using Linear Algebra and having a fixed point for the camera),
        *but nothing was copied aside from the layout design idea. In fact, their renderer and mine are 
        *accomplishing different goals: theirs was for rotating solid objects while mine is for plotting functions. 
        *Just to clear up any suspicion of plagiarism :)
        */
        horizontalSlider = new JSlider(0,360,0);
        verticalSlider = new JSlider(SwingConstants.VERTICAL, -90, 90, 0); 
        horizontalSlider.addChangeListener(new ChangeListener(){
            public void stateChanged(ChangeEvent e){
                renderPanel.repaint();
            }
        });
        verticalSlider.addChangeListener(new ChangeListener(){
            public void stateChanged(ChangeEvent e){
                renderPanel.repaint();
            }
        });
        //Create panel for painting the function and axes
        renderPanel = new JPanel(){
            public void paintComponent(Graphics g){
                Graphics2D g2 = (Graphics2D) g;
                g2.setColor(Color.WHITE);
                g2.fillRect(0,0,getWidth(), getHeight());
                paintAxes(g2);
            }
        };
        pane.add(horizontalSlider, BorderLayout.SOUTH);
        pane.add(verticalSlider, BorderLayout.EAST);
        pane.add(renderPanel, BorderLayout.CENTER);
    }
    
    private void paintAxes(Graphics2D g){
        //making the axes, rotating them, and labeling. 
        g.setColor(Color.BLACK);
        Point px1 = transformedPoint(xAxis[0].scalarMultiply(scale));
        Point px2 = transformedPoint(xAxis[1].scalarMultiply(scale));
        Point py1 = transformedPoint(yAxis[0].scalarMultiply(scale));
        Point py2 = transformedPoint(yAxis[1].scalarMultiply(scale));
        Point pz1 = transformedPoint(zAxis[0].scalarMultiply(scale));
        Point pz2 = transformedPoint(zAxis[1].scalarMultiply(scale));
        Path2D path = new Path2D.Double();
        path.moveTo(px1.x,px1.y);
        path.lineTo(px2.x, px2.y);
        path.closePath();
        g.draw(path);
        g.drawString("x", (int)px2.x, (int)px2.y);
        path.moveTo(py1.x,py1.y);
        path.lineTo(py2.x, py2.y);
        path.closePath();
        g.draw(path);
        g.drawString("z", (int)py1.x, (int)py1.y);
        path.moveTo(pz1.x,pz1.y);
        path.lineTo(pz2.x, pz2.y);
        path.closePath();
        g.draw(path);
        g.drawString("y", (int)pz2.x, (int)pz2.y);
        graphEquation(g);
    }
    
    private Point transformedPoint(Point p){
        //get angle values
        double heading = Math.toRadians(horizontalSlider.getValue());
        double pitch = Math.toRadians(verticalSlider.getValue());
        //create vertical and horizontal rotation matrices
        Matrix3 VerticalTransform = new Matrix3(new double[] {
                    1,0,0,
                    0, Math.cos(pitch), Math.sin(pitch),
                    0, -Math.sin(pitch), Math.cos(pitch)
                });
        Matrix3 HorizontalTransform = new Matrix3(new double[] {
                    Math.cos(heading), 0 , -Math.sin(heading),
                    0,1,0,
                    Math.sin(heading), 0, Math.cos(heading)});
        //origin point for reference
        Point temp = new Point(getWidth()/2, getHeight()/2, 0);
        //rotate points and add to origin
        Point temp2 = VerticalTransform.multiply(HorizontalTransform).rotate(p.scalarMultiply((getWidth()/scale*2/5.0))).add(temp);
        return new Point(temp2.x, temp2.y, temp2.z);
    }
    
    private void graphEquation(Graphics2D g){
        //cycle through all necessary functions
        for(int j = 0; j<func.length; j++){
            Function3D f = func[j];
            //set appropriate color for function and tangential plane
            if(j==0){
                g.setColor(Color.BLUE);
            }
            else{
                g.setColor(Color.RED);
            }
            //take values and create guidelines evenly over the region
            for(int i = -40; i<40; i++){
                for(int w = -40; w<40; w++){
                    //set point values to scale
                    double x = i/40.0 * scale;
                    double y = w/40.0 * scale;
                    
                    //square grid points
                    Point p1 = new Point(x,-f.calculateValue(x, y), y);
                    Point p2 = new Point(x, -f.calculateValue(x, y+scale/40), y+scale/40);
                    Point p3 = new Point(x+scale/40, -f.calculateValue(x+scale/40, y), y);
                    Point p4 = new Point(x, -f.calculateValue(x+scale/40, y+scale/40), y);
                
                    //Rotate and convert to GUI coordinates
                    p1 = transformedPoint(p1);
                    p2 = transformedPoint(p2);
                    p3 = transformedPoint(p3);
                    p4 = transformedPoint(p4);
                    
                    //Draw if inFrame
                
                    if(!((int)p1.x<50 || (int)p1.y<50 || (int)p2.x<50 || (int)p2.y<50 || (int)p1.x>1200 || (int)p1.y > 800 || (int)p2.x>1200 || (int)p2.y>800)){
                        g.drawLine((int)p1.x, (int)p1.y, (int)p2.x, (int)p2.y);
                        /*
                        int[] xVals = {(int)p1.x, (int)p2.x, (int)p4.x, (int)p3.x, (int)p1.x};
                        int[] yVals = {(int)p1.y, (int)p2.y, (int)p4.y, (int)p3.y, (int)p1.y};
                        g.setColor(Color.RED);
                        g.fillPolygon(xVals, yVals, 5);*/
                    }
                }
            }
            if(j==0){
                g.setColor(Color.GREEN);
            }
            //create guidelines in perpendicular direction evenly over the region
            for(int w = -40; w<40; w++){
                for(int i = -40; i<40; i++){
                    double x = i/40.0 * scale;
                    double y = w/40.0 * scale;
                    Point p1 = new Point(x,-f.calculateValue(x, y), y);
                    Point p2 = new Point(x+scale/40, -f.calculateValue(x+scale/40, y), y);
                    p1 = transformedPoint(p1);
                    p2 = transformedPoint(p2);
                    if(!((int)p1.x<50 || (int)p1.y<50 || (int)p2.x<50 || (int)p2.x<50 || (int)p1.x>1200 || (int)p1.y > 800 || (int)p2.x>1200 || (int)p2.y>800)){
                        g.drawLine((int)p1.x, (int)p1.y, (int)p2.x, (int)p2.y);
                    }
                }
            }
        }
    }
}
