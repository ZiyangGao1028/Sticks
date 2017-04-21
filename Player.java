interface Player
{
   int move(int numSticks, boolean training);
   void startGame(boolean training);
   void endGame(boolean win, boolean training);
   void print(boolean training, int numSticks, int sticks, double total);
}