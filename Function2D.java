/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tong_3dcalculator;
import java.util.*;
/**
 *
 * @author kevin
 */
public class Function2D{
    private String equation;
    
    public Function2D(String eq){
        equation = eq;
    }
    
    public double calculateIntegral(double a, double b){
        double sum = 0;
        for(int i = 0; i<500; i++){
            sum+=calculateValue(a+((b-a)*i/500.0))*(b-a)/500.0;
        }
        return sum;
    }
    
    public double calculateDerivative(double x){
        return((calculateValue(x)-calculateValue(x+0.001))/(-0.001));
    }
    
    /*Calculates value at a given point. A small part of this algorithm was modeled off an answer
    *on StackOverflow, specifically, the part that calculates +,-,*, and /. Everything else was
    *coded on my own. The use of the Stack was inspired by the algorithm. 
    */
    public double calculateValue(double x){
        char[] tokes = equation.toCharArray();
        Stack<Double> numbers = new Stack<Double>();
        Stack<Character> operations = new Stack<Character>();
        Stack<Character> specialOperations = new Stack<Character>();
        
        for(int i = 0; i<tokes.length; i++){
            if(tokes[i] == ' '){
                continue;
            }
            if(((int)tokes[i] >= 48 && (int)tokes[i] <= 57)||((int)tokes[i] == 46)){
                String temp = "";
                int w = i;
                while(i<tokes.length && ((((int)tokes[i] >= 48 && (int)tokes[i] <=57))|| (int)tokes[i] == 46)){
                    temp += tokes[i++];
                }
                i--;
                numbers.push(Double.parseDouble(temp));
                if(((i<tokes.length-1))&&(tokes[i+1] == 's' ||tokes[i+1] == 'l' ||tokes[i+1] == 'c' ||tokes[i+1] == 't' ||tokes[i+1] == 'x'||tokes[i+1] == '(')){
                    operations.push('*');
                }
            }
            else if(tokes[i] == '('){
                operations.push(tokes[i]);
                specialOperations.push(' ');
            }
            else if(tokes[i] == ')'){
                while(operations.peek() != '('){
                    numbers.push(calculateOperation(operations.pop(), numbers.pop(), numbers.pop()));
                }
                operations.pop();
                if(specialOperations.peek() != ' '){
                    if(specialOperations.peek() == '^'){
                        specialOperations.pop();
                        double num1 = numbers.pop();
                        double num2 = numbers.pop();
                        numbers.push(Math.pow(num2, num1));
                    }
                    else{
                        numbers.push(calculateSpecialOperation(specialOperations.pop(), numbers.pop()));
                    }
                }
                else{
                    specialOperations.pop();
                }
                if(((i<tokes.length-1))&&(tokes[i+1] == 's' ||tokes[i+1] == 'l' ||tokes[i+1] == 'c' ||tokes[i+1] == 't' ||tokes[i+1] == 'x'||tokes[i+1] == '(')){
                    operations.push('*');
                }
            }
            else if(tokes[i] == '-' || tokes[i] == '+' || tokes[i] == '*' || tokes[i] == '/'){
                if(i!=0 && (tokes[i-1] != '(')){
                    while(!operations.empty() && doFirst(tokes[i], operations.peek())){
                        numbers.push(calculateOperation(operations.pop(), numbers.pop(), numbers.pop()));
                    }
                    operations.push(tokes[i]);
                }
                else{
                    numbers.push(0.0);
                    operations.push(tokes[i]);
                }
            }
            else if(tokes[i] == '^'){
                if((int)tokes[i+1] >= 48 && (int)tokes[i+1] <= 57){
                    String temp = "";
                    i++;
                    while(i<tokes.length && (((int)tokes[i] >=48 && (int)tokes[i] <=57)|| (int)tokes[i] == 46)){
                        temp += tokes[i++];
                    }
                    i--;
                    numbers.push(calculateOperation('^', numbers.pop(), Double.parseDouble(temp)));
                }
                else{
                    specialOperations.push('^');
                }
            }
            else if(tokes[i] == 'x'){
                numbers.push(x);
            }
            else if(tokes[i] == 's' && tokes[i+1] == 'i' && tokes[i+2] == 'n'){
                i += 3;
                specialOperations.push('s');
                operations.push('(');
            }
            else if(tokes[i] == 's' && tokes[i+1] == 'q' && tokes[i+2] == 'r' && tokes[i+3] == 't'){
                i += 4;
                specialOperations.push('q');
                operations.push('(');
            }
            else if(tokes[i] == 'c' && tokes[i+1] == 'b' && tokes[i+2] == 'r' && tokes[i+3] == 't'){
                i += 4;
                specialOperations.push('r');
                operations.push('(');
            }
            else if(tokes[i] == 'c' && tokes[i+1] == 'o' && tokes[i+2] == 's'){
                i += 3;
                specialOperations.push('c');
                operations.push('(');
            }
            else if(tokes[i] == 't' && tokes[i+1] == 'a' && tokes[i+2] == 'n'){
                i += 3;
                specialOperations.push('t');
                operations.push('(');
            }
            else if(tokes[i] == 'l' && tokes[i+1] == 'o' && tokes[i+2] == 'g'){
                i += 3;
                specialOperations.push('l');
                operations.push('(');
            }
            else if(tokes[i] == 'l' && tokes[i+1] == 'n'){
                i += 2;
                specialOperations.push('n');
                operations.push('(');
            }
        }
        while (!operations.empty()){
            numbers.push(calculateOperation(operations.pop(), numbers.pop(), numbers.pop()));
        }
        return numbers.pop();
    }
    
    public boolean doFirst(char op1, char op2){
        if((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')){
            return false;
        }
        if(op2 == '(' || op2 == ')'){
            return false;
        }
        return true;
    }
    
    public double calculateOperation(char op, double a, double b){
        switch(op){
            case '-': return b-a;
            case '+': return a+b;
            case '*': return a*b;
            case '/': if(a!=0){return b/a;}else{return 0;}
            case '^': return Math.pow(a,b);
        }
        return 0;
    }
    
    public double calculateSpecialOperation(char op, double a){
        switch(op){
            case 's': return Math.sin(a);
            case 'c': return Math.cos(a);
            case 't': return Math.tan(a);
            case 'l': return Math.log10(a);
            case 'e': return Math.pow(Math.E, a);
            case 'n': return Math.log(a);
            case 'q': return Math.sqrt(a);
            case 'r': return Math.cbrt(a);
        }
        return 0;
    }
    
    public double calculateArcLength(double a, double b){
        double sum = 0;
        for(int i = 0; i<2000; i++){
            sum+=Math.sqrt(1+Math.pow(calculateDerivative(a+((b-a)*i/2000.0)),2))*(b-a)/2000.0;
        }
        return sum;
    }
    
    public void setEquation(String equation){
        this.equation = equation;
    }
}
