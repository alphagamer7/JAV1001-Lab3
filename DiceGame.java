import java.util.*;

public class DiceGame {

    // Main Method
    public static void main(String[] args){

        DiceGame DiceGame = new DiceGame();
        Die die = DiceGame.new Die();
        Die die2 = DiceGame.new Die(20);
        Die die3 = DiceGame.new Die(100, "Percentile");
        die.setDesiredSideUp(2);
        die.Roll();
        die2.setDesiredSideUp(2);
        die2.Roll();
        die3.setDesiredSideUp(2);
        die3.Roll();
        die.SetDesiredSideUpThenRoll();
        die2.SetDesiredSideUpThenRoll();
        die3.SetDesiredSideUpThenRoll();

        DiceGame.playYahtzee(5, 2);
    }

    /*
     YAHTZEE Game
     This game will roll all the number of dies and wait for all of current side ups of each die
     to equal the desired side up.  This will show how many roll counts were achieved.
    */
    public static void playYahtzee(int numberOfD6Dice, int desiredSideUp){

        System.out.println("Creating " + numberOfD6Dice + " d6...");

        Die dies[] = new Die[numberOfD6Dice];

        DiceGame DiceGame = new DiceGame();

        // loop and create the desired number of dies
        for(int diecounter = 0; diecounter < numberOfD6Dice; diecounter++){
            Die die = DiceGame.new Die();
            die.desiredSideUp = desiredSideUp;
            dies[diecounter] = die;
        }

        int rollscounter = 1;

        boolean yahtzee = false;
        while(!yahtzee){
            boolean hasPreviousNotDesiredSideUp = true;

            // Roll the dies
            for(int diecounter = 0; diecounter < dies.length; diecounter++){
                dies[diecounter].Roll();
            }

            // Check the current side up of each die
            for(int diecounter = 0; diecounter < dies.length; diecounter++){

                if(dies[diecounter].currentSideUp != desiredSideUp){
                    hasPreviousNotDesiredSideUp = true;
                    break;
                }
                else{
                    hasPreviousNotDesiredSideUp = false;
                }
            }

            /*
             If there are no previous current side up of each die that does not equal to desired side up
             then there is no yahtzee. Continue to the next roll.
            * */
            if(!hasPreviousNotDesiredSideUp){
                yahtzee = true;
            }

            rollscounter++;
        }

        if(yahtzee){
            System.out.println("YAHTZEE! It took " + rollscounter + " rolls");
        }
    }

    public class Die{

        private String type;
        private int numberOfSides;
        private int currentSideUp;
        public int desiredSideUp;


        // desiredSideUp setter
        public void setDesiredSideUp(int desiredSideUp) {
            this.desiredSideUp = desiredSideUp;
        }

        // Constructor of d6 default die
        public Die(){
            this.type = "d6";
            this.numberOfSides = 6;
            this.currentSideUp =  this.generateRandomNumber();
            System.out.println("Creating a default d6...");
        }

        // Constructor for variable number of sides die
        public Die(int numberOfSides){
            this.numberOfSides = numberOfSides;
            this.type = "d" + numberOfSides;
            this.currentSideUp = this.generateRandomNumber();
            System.out.println("Creating a " + type + "...");
        }

        // Constructor for variable number of sides and type die
        public Die(int numberOfSides, String type){
            this.numberOfSides = numberOfSides;
            this.type = type;
            currentSideUp = this.generateRandomNumber();
            System.out.println("Creating a " + type + " die (a special " + numberOfSides + ")...");
        }

        // Generates a random number (int) with math.random based on the maximum limit
        private int generateRandomNumber(){
            return (int)(Math.random()*numberOfSides+1);
        }

        // Simulate die rolling
        public void Roll(){

            System.out.println("The current side up for " + type + " is " + currentSideUp);

            if(desiredSideUp > 0 && desiredSideUp <= numberOfSides){
                System.out.println("Rolling the " + type + "...");

                this.currentSideUp = this.generateRandomNumber();

                System.out.println("The new value is " + currentSideUp);
            }
            else{
                System.out.println("Desired side up is not given in the dice.");
            }
        }

        // Set the desired side up and then roll the die
        public void SetDesiredSideUpThenRoll(){
            System.out.println("Setting the " + type + " to show " + desiredSideUp);

            int rollscounter = 1;

            while( this.currentSideUp !=  this.desiredSideUp){

                this.Roll();

                if( this.currentSideUp ==  this.desiredSideUp){
                    System.out.println("The side up is now "+ this.desiredSideUp+". Finally.\nAfter " +  rollscounter + " roll(s)");
                    break;
                }

                rollscounter++;
            }
        }
    }
}
