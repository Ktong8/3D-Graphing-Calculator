/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tong_3dcalculator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
/**
 *
 * @author kevin
 */
public class Equation2DInput extends JFrame implements ActionListener{
    private ArrayList<JTextField> equations;
    private ArrayList<JButton> graphingButtons;
    private int n;
    private JButton addEquation, calcDeriv, calcInt, calcArc;
    private JComboBox<String> derivativeSelector, integralSelector, arcSelector;
    private JTextField[] derivAts, intAts, arcAts;
    private JLabel derivResult, intResult, arcResult;
    private JCheckBox inGraphD;
    
    public Equation2DInput(){
        super();
        setTitle("2D Graphing Calculator");
        n = 1;
        setBackground(Color.BLACK);
        equations = new ArrayList<JTextField>();
        graphingButtons = new ArrayList<JButton>();
        derivAts = new JTextField[1];
        intAts = new JTextField[2];
        arcAts = new JTextField[2];
        setVisible(true);
        setSize(1200,800);
        setLayout(null);
        setUpInts();
        setUpDerivs();
        setUpArcLengths();
        setUpEquations();
    }
    
    private void setUpDerivs(){
        derivativeSelector = new JComboBox<>();
        add(derivativeSelector);
        derivativeSelector.setBounds(500,110,200,30);
        JLabel derivativeCalc = new JLabel("Derivative Calculator");
        derivativeCalc.setBounds(500,50,400,50);
        derivativeCalc.setVerticalAlignment(JLabel.CENTER);
        derivativeCalc.setHorizontalAlignment(JLabel.CENTER);
        add(derivativeCalc);
        derivativeCalc.setFont(new Font(derivativeCalc.getFont().getName(), Font.BOLD, 25));
        JLabel derivRespect = new JLabel("with respect to ");
        derivRespect.setBounds(708, 100, 100, 50);
        add(derivRespect);
        JLabel derivAtx = new JLabel("at x = ");
        derivAtx.setBounds(500, 150, 50, 50);
        add(derivAtx);
        JTextField xIn = new JTextField("0");
        xIn.setBounds(535, 160, 70, 30);
        add(xIn);
        derivAts[0] = xIn;
        calcDeriv = new JButton("Calculate!");
        calcDeriv.setBounds(615, 160, 100, 30);
        add(calcDeriv);
        calcDeriv.addActionListener(this);
        derivResult = new JLabel("Result: ");
        derivResult.setBounds(550, 180, 220, 50);
        inGraphD = new JCheckBox();
        inGraphD.setBounds(735, 165, 20,20);
        JLabel putIn = new JLabel("Put in Graph");
        putIn.setBounds(765, 165, 100,20);
        add(putIn);
        add(inGraphD);
        add(derivResult);
    }
    
    private void setUpInts(){
        integralSelector = new JComboBox<>();
        add(integralSelector);
        integralSelector.setBounds(500,510,200,30);
        JLabel integralCalc = new JLabel("Integral Calculator");
        integralCalc.setBounds(500,450,400,50);
        integralCalc.setVerticalAlignment(JLabel.CENTER);
        integralCalc.setHorizontalAlignment(JLabel.CENTER);
        add(integralCalc);
        integralCalc.setFont(new Font(integralCalc.getFont().getName(), Font.BOLD, 25));
        JLabel from = new JLabel(" from");
        from.setBounds(710, 500, 50,50);
        add(from);
        JLabel x1 = new JLabel("x = ");
        JLabel x2 = new JLabel(" to x = ");
        x1.setBounds(500, 550, 50,50);
        x2.setBounds(600, 550, 50, 50);
        add(x1);
        add(x2);
        JTextField x1In = new JTextField("0");
        JTextField x2In = new JTextField("0");
        x1In.setBounds(525, 560, 70, 30);
        x2In.setBounds(640, 560, 70, 30);
        add(x1In);
        add(x2In);
        intAts[0] = x1In;
        intAts[1] = x2In;
        calcInt = new JButton("Calculate!");
        calcInt.setBounds(730, 560, 100, 30);
        calcInt.addActionListener(this);
        add(calcInt);
        intResult = new JLabel("Result: ");
        intResult.setBounds(550, 580, 220, 50);
        add(intResult);
    }
    
    private void setUpArcLengths(){
        arcSelector = new JComboBox<>();
        add(arcSelector);
        arcSelector.setBounds(500,300,200,30);
        JLabel integralCalc = new JLabel("Arc Length Calculator");
        integralCalc.setBounds(500,240,400,50);
        integralCalc.setVerticalAlignment(JLabel.CENTER);
        integralCalc.setHorizontalAlignment(JLabel.CENTER);
        add(integralCalc);
        integralCalc.setFont(new Font(integralCalc.getFont().getName(), Font.BOLD, 25));
        JLabel from = new JLabel(" from");
        from.setBounds(710, 290, 50,50);
        add(from);
        JLabel x1 = new JLabel("x = ");
        JLabel x2 = new JLabel(" to x = ");
        x1.setBounds(500, 340, 50,50);
        x2.setBounds(600, 340, 50, 50);
        add(x1);
        add(x2);
        JTextField x1In = new JTextField("0");
        JTextField x2In = new JTextField("0");
        x1In.setBounds(525, 350, 70, 30);
        x2In.setBounds(640, 350, 70, 30);
        add(x1In);
        add(x2In);
        arcAts[0] = x1In;
        arcAts[1] = x2In;
        calcArc = new JButton("Calculate!");
        calcArc.setBounds(730, 350, 100, 30);
        calcArc.addActionListener(this);
        add(calcArc);
        arcResult = new JLabel("Result: ");
        arcResult.setBounds(550, 370, 220, 50);
        add(arcResult);
    }
    
    private void setUpEquations(){
        JLabel equationLabel = new JLabel("Equations:");
        addEquation = new JButton("Add Equation");
        addEquation.setSize(325, 50);
        addEquation.setLocation(0,50);
        addEquation.addActionListener(this);
        add(addEquation);
        JLabel support = new JLabel("Supports +,-,/,*,(),^,ln,log,sin,cos,tan,sqrt,cbrt");
        support.setSize(500,50);
        support.setLocation(0,50);
        support.setFont(new Font(support.getFont().getName(), Font.PLAIN, 15));
        add(support);
        add(equationLabel);
        equationLabel.setBounds(0,0, 325, 50);
        equationLabel.setVerticalAlignment(JLabel.CENTER);
        equationLabel.setHorizontalAlignment(JLabel.CENTER);
        equationLabel.setFont(new Font(equationLabel.getFont().getName(), Font.BOLD, 30));
        addInput();
    }
    
    public void addInput(){
        JTextField textInput = new JTextField("Enter Equation Here");
        n++;
        addEquation.setLocation(0, 50*(n+1));
        JButton graphIt = new JButton("Graph!");
        add(textInput);
        textInput.setLocation(0,50*n);
        textInput.setSize(250,50);
        graphIt.setBounds(250,50*n,75,50);
        add(graphIt);
        graphIt.addActionListener(this);
        derivativeSelector.addItem("Graph " + (int)(n-1));
        integralSelector.addItem("Graph " + (int)(n-1)); 
        arcSelector.addItem("Graph " + (int)(n-1));
        equations.add(textInput);
        graphingButtons.add(graphIt);
    }
    
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == addEquation){
            addInput();
        }
        else if(e.getSource() == calcDeriv){
            Function2D func = new Function2D(equations.get(derivativeSelector.getSelectedIndex()).getText());
            double x = Double.parseDouble(derivAts[0].getText());
            double result = func.calculateDerivative(x);
            double result1 = ((int)(result*100.0))/100.0;
            derivResult.setText("Result: " + result1);
        }
        else if(e.getSource() == calcInt){
            Function2D func = new Function2D(equations.get(integralSelector.getSelectedIndex()).getText());
            double x1 = Double.parseDouble(intAts[0].getText());
            double x2 = Double.parseDouble(intAts[1].getText());
            double result = func.calculateIntegral(x1, x2);
            double result1 = ((int)(result*100.0))/100.0;
            intResult.setText("Result: " + result1);
        }
        else if(e.getSource() == calcArc){
            Function2D func = new Function2D(equations.get(arcSelector.getSelectedIndex()).getText());
            double x1 = Double.parseDouble(arcAts[0].getText());
            double x2 = Double.parseDouble(arcAts[1].getText());
            double result = func.calculateArcLength(x1, x2);
            double result1 = ((int)(result*100.0))/100.0;
            arcResult.setText("Result: " + result1);
        }
        else{
            int index = ((((JButton)e.getSource()).getY()-1)/50)-1;
            int counter = 1;
            if(inGraphD.isSelected()){
                counter++;
            }
            String[] temp = new String[counter];
            temp[0] = equations.get(index).getText();
            if (inGraphD.isSelected()){
                Function2D func = new Function2D(equations.get(derivativeSelector.getSelectedIndex()).getText());
                double x = Double.parseDouble(derivAts[0].getText());
                double y = func.calculateValue(x);
                x = ((int)(x*1000.0))/1000.0;
                y = ((int)(y*1000.0))/1000.0;
                double derivativeX = func.calculateDerivative(x);
                derivativeX = ((int)(derivativeX*1000.0))/1000.0;
                String newEqua = "" + derivativeX + "*(-(" + x + ")+x)+(" + y + ")";
                System.out.println(newEqua);
                temp[1] = newEqua;
            }
            Renderer2D rend = new Renderer2D(temp);
        }
    }
}
