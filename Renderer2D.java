/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tong_3dcalculator;
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
public class Renderer2D extends JFrame{
    private static JPanel renderPanel;
    private Function2D[] func;
    double scale;
    
    public Renderer2D(String[] equations){
        super();
        setTitle("2D Graph Output");
        scale = 10;
        setTitle("3D Grapher");
        func = new Function2D[equations.length];
        for(int i = 0; i<func.length; i++){
            func[i] = new Function2D(equations[i]);
        }
        Container pane = getContentPane();
        
            renderPanel = new JPanel(){
            public void paintComponent(Graphics g){
                Graphics2D g2 = (Graphics2D) g;
                g2.setColor(Color.WHITE);
                g2.fillRect(0,0,getWidth(), getHeight());
                paintAxes(g2);
            }
        };
        pane.add(renderPanel, BorderLayout.CENTER);
        setSize(1200,800);
        setVisible(true);
    }
    
    private void paintAxes(Graphics2D g){
        g.setColor(Color.BLACK);
        Path2D path = new Path2D.Double();
        path.moveTo(getWidth()/2-getHeight()/2,getHeight()/2);
        path.lineTo(getWidth()/2+getHeight()/2, getHeight()/2);
        path.closePath();
        g.draw(path);
        g.drawString("x", getWidth()/2+getHeight()/2+5, getHeight()/2+5);
        path.moveTo(getWidth()/2,20);
        path.lineTo(getWidth()/2, getHeight()-80);
        path.closePath();
        g.draw(path);
        g.drawString("y", (int)getWidth()/2+10, 15);
        graphEquation(g);
    }
    
    private void graphEquation(Graphics2D g){
        for(int j = 0; j<func.length; j++){
            Function2D f = func[j];
            if(j==0){
                g.setColor(Color.BLACK);
            }
            else{
                g.setColor(Color.RED);
            }
            for(int i = -500; i<500; i++){
                //set point values to scale
                double x = i/500.0 * scale;
                
                //square grid points
                Point p1 = new Point(x,-f.calculateValue(x), 0);
                Point p2 = new Point(x, -f.calculateValue(x+scale/500.0), 0);
                p1.x = p1.x/scale;
                p1.y = p1.y/scale;
                p2.x = p2.x/scale;
                p2.y = p2.y/scale;
                //Draw if inFrame
                if(!(Math.abs(p1.x)>1 || Math.abs(p2.y)>1 || Math.abs(p1.y)>1 || Math.abs(p2.x)>1)){
                    g.drawLine(getWidth()/2 + (int)(p1.x*getHeight()/2), getHeight()/2 + (int)(p1.y*getHeight()/2), getWidth()/2 + (int)(p2.x*getHeight()/2), getHeight()/2 + (int)(p2.y*getHeight()/2));
                }
            }
        }
    }
}
