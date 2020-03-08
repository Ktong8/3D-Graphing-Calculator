/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threed_calculator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
/**
 *
 * @author kevin
 */
public class Equation3DInput extends JFrame implements ActionListener{
    private ArrayList<JTextField> equations;
    private ArrayList<JButton> graphingButtons;
    private int n;
    private JButton addEquation, calcDeriv, calcInt, calcSurfaceInt;
    private JComboBox<String> derivativeSelector, integralSelector, partialDerivative, surfaceIntSelector;
    private JTextField[] derivAts, intAts, surfaceIntAts;
    private JLabel derivResult, intResult, surfaceIntResult;
    private JCheckBox inGraphD;
    
    public Equation3DInput(){
        super();
        setTitle("3D Graphing Calculator");
        n = 1;
        setBackground(Color.BLACK);
        equations = new ArrayList<JTextField>();
        graphingButtons = new ArrayList<JButton>();
        derivAts = new JTextField[2];
        intAts = new JTextField[4];
        surfaceIntAts = new JTextField[4];
        setVisible(true);
        setSize(1200,800);
        setLayout(null);
        setUpInts();
        setUpDerivs();
        setUpSurfaceInts();
        setUpEquations();
    }
    
    private void setUpDerivs(){
        derivativeSelector = new JComboBox<>();
        String[] variables = {"x", "y"};
        partialDerivative = new JComboBox<>(variables);
        partialDerivative.setBounds(800, 110, 50,30);
        add(partialDerivative);
        add(derivativeSelector);
        derivativeSelector.setBounds(500,110,200,30);
        JLabel derivativeCalc = new JLabel("Partial Derivative Calculator");
        derivativeCalc.setBounds(500,50,400,50);
        derivativeCalc.setVerticalAlignment(JLabel.CENTER);
        derivativeCalc.setHorizontalAlignment(JLabel.CENTER);
        add(derivativeCalc);
        derivativeCalc.setFont(new Font(derivativeCalc.getFont().getName(), Font.BOLD, 25));
        JLabel derivRespect = new JLabel("with respect to ");
        derivRespect.setBounds(708, 100, 100, 50);
        add(derivRespect);
        JLabel derivAtx = new JLabel("at x = ");
        JLabel derivAty = new JLabel("and y = ");
        derivAtx.setBounds(500, 150, 50, 50);
        derivAty.setBounds(608, 150, 50, 50);
        add(derivAtx);
        add(derivAty);
        JTextField xIn = new JTextField("0");
        JTextField yIn = new JTextField("0");
        xIn.setBounds(535, 160, 70, 30);
        yIn.setBounds(650, 160, 70, 30);
        add(xIn);
        add(yIn);
        derivAts[0] = xIn;
        derivAts[1] = yIn;
        calcDeriv = new JButton("Calculate!");
        calcDeriv.setBounds(730, 160, 100, 30);
        add(calcDeriv);
        calcDeriv.addActionListener(this);
        derivResult = new JLabel("Result: ");
        derivResult.setBounds(550, 180, 220, 50);
        inGraphD = new JCheckBox();
        inGraphD.setBounds(850, 165, 20,20);
        JLabel putIn = new JLabel("Put in Graph");
        putIn.setBounds(880, 165, 100,20);
        add(putIn);
        add(inGraphD);
        add(derivResult);
    }
    
    private void setUpInts(){
        integralSelector = new JComboBox<>();
        add(integralSelector);
        integralSelector.setBounds(500,510,200,30);
        JLabel integralCalc = new JLabel("Double Integral Calculator");
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
        JLabel y1 = new JLabel("y = ");
        JLabel y2 = new JLabel(" to y = ");
        x1.setBounds(500, 550, 50,50);
        x2.setBounds(600, 550, 50, 50);
        y1.setBounds(500, 600, 50, 50);
        y2.setBounds(600, 600, 50, 50);
        add(x1);
        add(x2);
        add(y1);
        add(y2);
        JTextField x1In = new JTextField("0");
        JTextField x2In = new JTextField("0");
        JTextField y1In = new JTextField("0");
        JTextField y2In = new JTextField("0");
        x1In.setBounds(525, 560, 70, 30);
        x2In.setBounds(640, 560, 70, 30);
        y1In.setBounds(525, 610, 70, 30);
        y2In.setBounds(640, 610, 70, 30);
        add(x1In);
        add(x2In);
        add(y1In);
        add(y2In);
        intAts[0] = x1In;
        intAts[1] = x2In;
        intAts[2] = y1In;
        intAts[3] = y2In;
        calcInt = new JButton("Calculate!");
        calcInt.setBounds(730, 560, 100, 80);
        calcInt.addActionListener(this);
        add(calcInt);
        intResult = new JLabel("Result: ");
        intResult.setBounds(550, 630, 220, 50);
        add(intResult);
    }
    
    private void setUpSurfaceInts(){
        surfaceIntSelector = new JComboBox<>();
        add(surfaceIntSelector);
        surfaceIntSelector.setBounds(500,280,200,30);
        JLabel integralCalc = new JLabel("Surface Integral Calculator");
        integralCalc.setBounds(500,220,400,50);
        integralCalc.setVerticalAlignment(JLabel.CENTER);
        integralCalc.setHorizontalAlignment(JLabel.CENTER);
        add(integralCalc);
        integralCalc.setFont(new Font(integralCalc.getFont().getName(), Font.BOLD, 25));
        JLabel from = new JLabel(" from");
        from.setBounds(710, 270, 50,50);
        add(from);
        JLabel x1 = new JLabel("x = ");
        JLabel x2 = new JLabel(" to x = ");
        JLabel y1 = new JLabel("y = ");
        JLabel y2 = new JLabel(" to y = ");
        x1.setBounds(500, 320, 50,50);
        x2.setBounds(600, 320, 50, 50);
        y1.setBounds(500, 370, 50, 50);
        y2.setBounds(600, 370, 50, 50);
        add(x1);
        add(x2);
        add(y1);
        add(y2);
        JTextField x1In = new JTextField("0");
        JTextField x2In = new JTextField("0");
        JTextField y1In = new JTextField("0");
        JTextField y2In = new JTextField("0");
        x1In.setBounds(525, 330, 70, 30);
        x2In.setBounds(640, 330, 70, 30);
        y1In.setBounds(525, 380, 70, 30);
        y2In.setBounds(640, 380, 70, 30);
        add(x1In);
        add(x2In);
        add(y1In);
        add(y2In);
        surfaceIntAts[0] = x1In;
        surfaceIntAts[1] = x2In;
        surfaceIntAts[2] = y1In;
        surfaceIntAts[3] = y2In;
        calcSurfaceInt = new JButton("Calculate!");
        calcSurfaceInt.setBounds(730, 330, 100, 80);
        calcSurfaceInt.addActionListener(this);
        add(calcSurfaceInt);
        surfaceIntResult = new JLabel("Result: ");
        surfaceIntResult.setBounds(550, 400, 220, 50);
        add(surfaceIntResult);
    }
    
    private void setUpEquations(){
        JLabel equationLabel = new JLabel("Equations:");
        addEquation = new JButton("Add Equation");
        addEquation.setSize(325, 50);
        addEquation.setLocation(0,50);
        addEquation.addActionListener(this);
        JLabel support = new JLabel("Supports +,-,/,*,(),^,ln,log,sin,cos,tan,sqrt,cbrt");
        support.setSize(500,50);
        support.setLocation(0,50);
        support.setFont(new Font(support.getFont().getName(), Font.PLAIN, 15));
        add(support);
        add(addEquation);
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
        surfaceIntSelector.addItem("Graph" + (int)(n-1));
        equations.add(textInput);
        graphingButtons.add(graphIt);
    }
    
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == addEquation && n<9){
            addInput();
        }
        else if(e.getSource() == calcDeriv){
            Function3D func = new Function3D(equations.get(derivativeSelector.getSelectedIndex()).getText());
            if(partialDerivative.getSelectedItem().equals("x")){
                double x = Double.parseDouble(derivAts[0].getText());
                double y = Double.parseDouble(derivAts[1].getText());
                double result = func.calculateXPartialDerivative(x, y);
                double result1 = ((int)(result*100.0))/100.0;
                derivResult.setText("Result: " + result1);
            }
            else if(partialDerivative.getSelectedItem().equals("y")){
                double x = Double.parseDouble(derivAts[0].getText());
                double y = Double.parseDouble(derivAts[1].getText());
                double result = func.calculateYPartialDerivative(x, y);
                double result1 = ((int)(result*100.0))/100.0;
                derivResult.setText("Result: " + result1);
            }
        }
        else if(e.getSource() == calcInt){
            Function3D func = new Function3D(equations.get(integralSelector.getSelectedIndex()).getText());
            double x1 = Double.parseDouble(intAts[0].getText());
            double x2 = Double.parseDouble(intAts[1].getText());
            double y1 = Double.parseDouble(intAts[2].getText());
            double y2 = Double.parseDouble(intAts[3].getText());
            double result = func.calculateIntegral(x1, x2, y1, y2);
            double result1 = ((int)(result*100.0))/100.0;
            intResult.setText("Result: " + result1);
        }
        else if(e.getSource() == calcSurfaceInt){
            Function3D func = new Function3D(equations.get(surfaceIntSelector.getSelectedIndex()).getText());
            double x1 = Double.parseDouble(surfaceIntAts[0].getText());
            double x2 = Double.parseDouble(surfaceIntAts[1].getText());
            double y1 = Double.parseDouble(surfaceIntAts[2].getText());
            double y2 = Double.parseDouble(surfaceIntAts[3].getText());
            double result = func.calculateSurfaceIntegral(x1, x2, y1, y2);
            double result1 = ((int)(result*100.0))/100.0;
            surfaceIntResult.setText("Result: " + result1);
        }
        else{
            int index = ((((JButton)e.getSource()).getY()-1)/50)-1;
            int counter = 1;
            if(inGraphD.isSelected() && derivativeSelector.getSelectedIndex() == index){
                counter++;
            }
            String[] temp = new String[counter];
            temp[0] = equations.get(index).getText();
            if (inGraphD.isSelected() && derivativeSelector.getSelectedIndex() == index){
                Function3D func = new Function3D(equations.get(derivativeSelector.getSelectedIndex()).getText());
                double x = Double.parseDouble(derivAts[0].getText());
                double y = Double.parseDouble(derivAts[1].getText());
                double z = func.calculateValue(x, y);
                double px = func.calculateXPartialDerivative(x, y);
                double py = func.calculateYPartialDerivative(x, y);
                Matrix3 crossmat = new Matrix3(new double[] {
                    1,1,1,
                    1,0,px,
                    0,1,py
                });
                Point normalVec = crossmat.crossDeterminant();
                normalVec.y = (int)(1000.0*normalVec.y)/1000.0;
                normalVec.x = (int)(1000.0*normalVec.x)/1000.0;
                normalVec.z = (int)(1000.0*normalVec.z)/1000.0;
                double d = -(normalVec.x*x + normalVec.y*y + normalVec.z*z);
                String newEqua = "-(" + normalVec.x + "x+(" + normalVec.y + "y)+(" + d + "))/(" + normalVec.z + ")";
                temp[1] = newEqua;
            }
            Renderer3D rend = new Renderer3D(temp);
        }
    }
}
