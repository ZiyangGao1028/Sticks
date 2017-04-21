import java.util.Scanner;

//Artificial intelligence class, which learns to take the best
//number of sticks over time.

public class AI implements Player 
{
   private String playerName;
   private int[][] options;
   private int totalSticks;
   private int[] memory;
    
   //Constructor method.
   public AI(String name, int totalSticks)
   {
      this.playerName = name;
      this.totalSticks = totalSticks;
      this.options = new int[totalSticks][3];
      // Initialize the counts to be 1 for each possible option.
      for(int i = 0; i < totalSticks; i++)
      {
         for(int j = 0; j < 3; j++)
         {
            options[i][j] = 1;
         }
      }
      // Make it so the computer can never choose a number of sticks
      // greater than the number of sticks on the board.
      this.options[1][1] = 0;
      this.options[1][2] = 0;
      this.options[2][2] = 0;
      this.memory = new int[totalSticks];
   }
   // Take sticks.
   public int move(int numSticks, boolean training) 
   {  
      int[] optionArray = options[numSticks - 1];//this is the hat
      
      double total = 0;
      for(int i = 0; i < 3; i++)
      {
         total += optionArray[i];
      }
      // Find the percent of time the AI should select a certain number
      // of sticks by dividing the number of times an option has worked
      // successfully by the total number of options in the hat.
      double a = optionArray[0] / total;
      double b = a + optionArray[1] / total;
      double c = b + optionArray[2] / total;
      // Select a random number between 0 and 1.
      double x = Math.random();
      int sticks = 0;
      // Select a number of sticks based on the percentages found earlier
      // and the random number.
      if (x <= a)
      {
         sticks = 1;
      }
      else if (x > a & x <= b)
      {
         sticks = 2;
      }
      else 
      {
         sticks = 3;
      }
      // prints the number of sticks on the table, the number of sticks taken
      // by the AI, and the total of the current hat if the AI is not training.
      print(training, numSticks, sticks, total);
      // Keep track of the number of sticks chosen each time the computer
      // picked up sticks to save if they won.
      memory[numSticks - 1] = sticks;
      numSticks = numSticks - sticks;
      return numSticks;
   }
   
   //Print ending statements and updates options.
   public void endGame(boolean win, boolean training)
   {
      if (win == true)
      {
         // only prints if not training.
         if (training == false)
         {
            System.out.println(playerName + ": You win!");
         }
         // If the computer won, it should save the successful moves by
         // adding one to the specific count, so that there is a higher
         // chance that it will pick it in future games.
         for(int i = 0; i < memory.length; i++)
         {
            if (memory[i] == 1)
            {
               options[i][0] += 1;
            }
            else if (memory[i] == 2)
            {
               options[i][1] += 1;
            }
            else if (memory[i] == 3)
            {
               options[i][2] += 1;
            }
         }
      }
      else
      {
         if (training == false)
         {
            System.out.println(playerName + ": You lose.");
         }
      }
      if (training == false)
      {
         System.out.println();
         System.out.println(playerName + " says 'That was fun, thank you!'");
      }
      // Clear the memory list after each game so that it can correctly
      // save successful moves in the future.
      for(int i = 0; i < memory.length; i++)
      {
         memory[i] = 0;
      }
   }
   //Print starting statement.
   public void startGame(boolean training)
   {
      if (training == false)
      {
         System.out.println(playerName +
            " says 'I, the AI, will hope to defeat you!'");
      }
   }
   
   // Prints the AI's move if it isn't training.
   public void print(boolean training, int numSticks, int sticks, double total)
   {
      if (training == false)
      {
         System.out.println("There are " + numSticks + " stick(s) on the board");
         System.out.println(total);
         System.out.println(playerName + " selects " + sticks  + " stick(s).");
      }
   }
}