/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threed_calculator;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author kevin
 */
public class ThreeD_Calculator {
    
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(0,0);
        frame.setSize(1728,972);
        frame.setTitle("3D Graphing Calculator");
        frame.setLayout(null);
        frame.setVisible(true);
        JButton graph2D = new JButton("2D Graphing Calculator");
        graph2D.setSize(500, 400);
        graph2D.setLocation(182, 400);
        graph2D.setFont(new Font(graph2D.getFont().getName(), Font.PLAIN, 40));
        graph2D.setHorizontalTextPosition(SwingConstants.CENTER);
        graph2D.setVerticalTextPosition(SwingConstants.BOTTOM);
        graph2D.setVerticalAlignment(SwingConstants.BOTTOM);
        graph2D.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Equation2DInput window = new Equation2DInput();
            }
        });
        JButton graph3D = new JButton("3D Graphing Calculator");
        graph3D.setFont(new Font(graph3D.getFont().getName(), Font.PLAIN, 40));
        graph3D.setHorizontalTextPosition(JButton.CENTER);
        graph3D.setVerticalTextPosition(SwingConstants.BOTTOM);
        graph3D.setVerticalAlignment(SwingConstants.BOTTOM);
        graph3D.setSize(500,400);
        graph3D.setLocation(1046, 400);
        graph3D.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Equation3DInput window = new Equation3DInput();
            }
        });
        frame.add(graph2D);
        frame.add(graph3D);
        JLabel title = new JLabel("Graphing Calculator");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);
        title.setSize(1364, 300);
        title.setLocation(182,50);
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 108));
        JLabel author = new JLabel("By Kevin Tong");
        author.setHorizontalAlignment(JLabel.CENTER);
        author.setVerticalAlignment(JLabel.CENTER);
        author.setSize(600, 50);
        author.setLocation(564, 315);
        author.setFont(new Font(author.getFont().getName(), Font.BOLD, 40));
        frame.add(author);
        frame.add(title);
    }
}
