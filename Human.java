import java.util.Scanner;

//Human class, which asks user to input the number 
// of sticks they want to take for a turn.
public class Human implements Player 
{
   private String playerName;
   
   //Constructor method.
   public Human(String name)
   {
      this.playerName = name;
   }
   
   //Asks input, counts sticks remain, and return it.
   public int move(int numSticks, boolean training) 
   {
      System.out.println("There are " + numSticks + " stick(s) on the board");
      Scanner scanner = new Scanner(System.in);
      System.out.print(playerName + ": How many sticks do you take (1-3)? ");
      int sticks = scanner.nextInt();
      while (sticks < 1 || sticks > 3)
      {
         System.out.println("Please enter a number between 1 and 3.");
         System.out.print(playerName + 
              ": How many sticks do you take (1-3)? ");
         sticks = scanner.nextInt();
      }
      while(sticks > numSticks)
      {
         System.out.println(
            "Please enter a number of sticks <= the sticks on the board.");
         System.out.print(playerName + 
              ": How many sticks do you take (1-3)? ");
         sticks = scanner.nextInt();
      }
      numSticks = numSticks - sticks;
      return numSticks;
   }
   
   //Prints out ending statements.
   public void endGame(boolean win, boolean training)
   {
      if (win == true)
      {
         System.out.println(playerName + ": You win!");
      }
      else
      {
         System.out.println(playerName + ": You lose.");
      }
   }
   
   //Prints out starting statements.
   public void startGame(boolean training)
   {
      System.out.println(playerName + ": Good luck!");
   }
   
   //Needs print method because it is used in AI training and they both
   //implement the Player interface. This does nothing because printing
   //is never a problem when the human is playing.
   public void print(boolean training, int numSticks, int sticks, double total)
   {
      return;
   }
}