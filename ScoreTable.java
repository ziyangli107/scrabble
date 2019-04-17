/**
 * A score table of each character worths. 
 */
public class ScoreTable{

   private int[] scoreTable;
   public static final int LENGTH =26;
   /**
    * Set the point of each character worths.
    */
   public ScoreTable() {
      scoreTable = new int[]{1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};
      
   }
   /**
    * Calculate the point the given word worths
    * @param word a string
    * @return the point that the given word worths
    */
   public int getVal(String word) {
      int score = 0;
      for(int i =0; i < word.length(); i++) {
         if('a' <= word.charAt(i))
         {
          score = score + scoreTable[(word.charAt(i) - 'a') % LENGTH];
         }
         else
         {
          score = score + scoreTable[(word.charAt(i) - 'A') % LENGTH];

         }

         
      }
      return score;
  }
}