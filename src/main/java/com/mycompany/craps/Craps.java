/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.craps;
import java.util.Scanner; //Import Scanner
/**
 *
 * @author 20231437
 */
public class Craps {
    public int rollingAttemp;
    public int point;
    public boolean keepRolling;
        
    public Craps() {
        this.keepRolling = true;
        this.rollingAttemp = 0;
        this.point = 0;
    }
    public int rollDice() {   
        //Generate random number for dices
        int dice1 = (int)(Math.random()*6)+1;
        int dice2 = (int)(Math.random()*6)+1;
        //Increase by step a attempts counter
        this.rollingAttemp++;
        return dice1 + dice2;
    }
    public String checkForWin(int lastRoll) {
        String result = "again";
        
        if(this.rollingAttemp == 1) {
            this.point = lastRoll;
            
            if(lastRoll == 2 || lastRoll == 3 || lastRoll == 12) {
                result = "lose";
            } else if (lastRoll == 7 || lastRoll == 11) {
                result = "win";
            } 
        } else {
            if(lastRoll == this.point) {
                result = "win";
            } else if(lastRoll == 7) {
                result = "lose";
            }
        }
        if(!result.equals("again")) {
            this.keepRolling = false;
        }
        return result;
    }
    public String evaluateMessage(String key, int lastRoll) {
        String result = "";
        switch(key) {
            case "win":
                result = "Made point, won";
                break;
            case "lose":
                result = "Lost with " + lastRoll;
                break;
            case "again":
                result = "No help";
                break;
        }
        return result;
    }
    public void print() {         
        //Saving current Roll
        int lastRoll;
        String key;
        if(this.rollingAttemp == 0) {
            lastRoll = rollDice();        
            key = checkForWin(lastRoll);
            System.out.println("GAME OF CRAPS!");
            System.out.println("Point: " + lastRoll);
            if(key != "again") {
                this.keepRolling = false;
                System.out.println(this.evaluateMessage(checkForWin(lastRoll), lastRoll));            
            } else {                    
                System.out.println("New Roll                     Outcome");
                System.out.println("------------------------------------");
            }  
        }
        while(this.keepRolling) {
            lastRoll = rollDice();
            String space = "";
            if(lastRoll < 10) {
                space = " ";
            }
            System.out.println(space + lastRoll + "                           " + evaluateMessage(checkForWin(lastRoll), lastRoll));
        }   
        

    }
    public void play() {
        while(this.keepRolling) {
            print();
        }        
    }
    public static void main(String[] args) {
        //Initializing Input/Output
        Scanner Input = new Scanner(System.in);
        //Initiate answer variable for game repeat
        char userInput;   
        boolean answer = true;
        boolean gameIteration = true;
        
        while(answer) {     
            if(gameIteration) {                
                //Intance of Class object
                Craps object = new Craps();             
                object.play();
            }
            System.out.print("Do you want to play again? Enter Y to play again, N to end: ");
            userInput = Input.next().charAt(0);
            switch(userInput) {
                case 'Y', 'y':        
                    gameIteration = true;
                    break;
                case 'N', 'n':
                    answer = false;
                    break;
                default:
                    System.out.println("Only Y,y or N,n is accepted to answer");
                    gameIteration = false;
                    break;
            }
        }
    }
}
