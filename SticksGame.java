import java.util.Scanner;

// Game of sticks, main method
// Written by Dave Musicant
public class SticksGame
{

   public static void playGameOnce(Player player1, Player player2, int totalSticks, boolean training)
   {
      // Print out opeing statements if the AIs are not training an it is a game
      // against a human.
      if (training == false)
      {
         System.out.println();
      }
      player1.startGame(training);
      player2.startGame(training);
      if (training == false)
      {
         System.out.println();
      }
      Scanner scanner = new Scanner(System.in);
      int numSticks = totalSticks;
      // Keep the game running until someone wins.
      
      while(numSticks > 0)
      {
         numSticks = player1.move(numSticks, training);
         // If there is no more sticks left, game ends and the last one who
         // takes stick lose.
         if(numSticks <= 0) 
         {
            if (training == false)
            {
               System.out.println();
            }
            player1.endGame(false, training);
            player2.endGame(true, training);
            return; 
         }
         if (training == false)
         {
            System.out.println();
         }
         
         numSticks = player2.move(numSticks, training);
         if(numSticks <= 0)
         {
            if (training == false)
            {
               System.out.println();
            }
            player1.endGame(true, training);
            player2.endGame(false, training);
            return;
         }
         if (training == false)
         {
            System.out.println();
         }
      }
   }  
   
   public static void main(String[] args)
   {
      // Initial setup
      System.out.println("Welcome to the game of sticks!");
      Scanner scanner = new Scanner(System.in);
      System.out.print("How many sticks are there on the table initally? (10-100)? ");
      int totalSticks = scanner.nextInt();
      while (totalSticks < 10 || totalSticks > 100)
      {
         System.out.println("Please enter a number between 10 and 100.");
         System.out.print("How many sticks are there on the table initally? (10-100)? ");
         totalSticks = scanner.nextInt();
      }
      
      System.out.println("Options:");
      System.out.println("Play against a friend (1)");
      System.out.println("Play against the computer (2)");
      System.out.println(
                "Ask two computer players to play against themselves(3)");
      System.out.print("Which option do you take (1 or 2 or 3)? ");
      int option = scanner.nextInt();
    
      // Choose which game: 1 = human v human, 2 = human v AI, 3 = AI(1) v AI(2) 
      while (option >= 4 || option <= 0)
      {
         System.out.println("Wrong choice, please input number 1 or 2 or 3");
         option = scanner.nextInt();
      }
      
      if (option == 1)
      {
         Player player1 = new Human("Player 1");
         Player player2 = new Human("Player 2");
         playGameOnce(player1, player2, totalSticks, false);
         playAgain(player1, player2, totalSticks);
      }
      else if (option == 2)
      {
         Player player1 = new Human("Player 1");
         Player player2 = new AI("Player 2", totalSticks);
         playGameOnce(player1, player2, totalSticks, false);
         playAgain(player1, player2, totalSticks);
      }
      else if (option == 3)
      {
         Player player1 = new AI("Player 1", totalSticks);
         Player player2 = new AI("Player 2", totalSticks);
         System.out.println("Training AI, please wait!");
         // trains AI by playing many games against an AI.
         for (int i = 0; i<= 100000; i++)
         { 
            playGameOnce(player1, player2, totalSticks, true);
         }
         player1 = new Human("Player 1");
         playGameOnce(player1, player2, totalSticks, false);
         playAgain(player1, player2, totalSticks);
      }
   }   
   
   // Asks the user if they want to play again and calls playGameOnce if so. 
   public static void playAgain(Player player1, Player player2, int totalSticks)
   {
      System.out.println(
                 "Do you want to play/play again? 1 = yes, 2 = no.");
      Scanner scanner = new Scanner(System.in);           
      int decision = scanner.nextInt();
      while(decision < 1 || decision > 2)
      {
         System.out.println("Please pick 1 or 2");
         System.out.println(
              "Do you want to play again? 1 = yes, 2 = no.");
         decision = scanner.nextInt();
      }
      
      if (decision == 1)
      {
         // If they want to play again, call playGameOnce again.
         playGameOnce(player1, player2, totalSticks, false);
         playAgain(player1, player2, totalSticks);
      }
      else
      {
         return;
      }
   }
}