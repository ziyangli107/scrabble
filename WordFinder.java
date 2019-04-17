
//main class for searching words from a rack of scrabble tile
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class WordFinder
{
   public static void main(String [] args)
   {
      try {
         AnagramDictionary dictionary = new AnagramDictionary("sowpods.txt");
         System.out.println("Type . to quit.");
         System.out.print("Rack?");
         Scanner in = new Scanner(System.in);
         String nextString = in.next();
         while(!nextString.equals(".") )
         {
            Rack rack = new Rack(nextString);
            ArrayList<String> allwords = findWord(rack, dictionary);
            printOut(new ScoreTable(), allwords, nextString);
            System.out.print("Rack?");
            nextString = in.next();
         }
         
         
      }
      catch (FileNotFoundException exp)
      {
         System.out.println("File Not Found.");
      }
   }
   /**
    * Given a rack,return all the words that it shows in a given anagram dictionary
    * @param rack a given list of string
    * @param ad a given anagram dictionary
    * @return all the words that it shows in a given anagram dictionary
    */
   private static ArrayList<String> findWord(Rack rack, AnagramDictionary ad)
   {
      ArrayList<String> allcombos = rack.getRack();
      ArrayList<String> allwords = new ArrayList<String>();
      
      for(int i = 0 ; i < allcombos.size() ; i++)
      {
         if(ad.getAnagramsOf(allcombos.get(i))!=null)
         {
            allwords.addAll(ad.getAnagramsOf(allcombos.get(i)));
         }
      }
      
      Collections.sort(allwords, new myComparator());
           
      return allwords;
   }
   /**
    * 
    * A comparator that will sort all strings by scores
    *
    */
   public static class myComparator implements Comparator<String>
   {
      
      public int compare (String str1 , String str2)
      {
         ScoreTable scoreTable = new ScoreTable();
         int score1 = scoreTable.getVal(str1);
         int score2 = scoreTable.getVal(str2);
         if(score1 != score2)
         {
            return score2 - score1;
         }
         else
            return str1.compareTo(str2);
      }
   }
   /**
    * print out the result that all the words a given string can comprise in the dictionary and their scores.
    * @param scoreTable a table that scores of each character worths
    * @param list a string of word list that a given string contains in dictionary
    * @param str a given string
    */
   private static void printOut(ScoreTable scoreTable, ArrayList<String> list, String str)
   {
      System.out.println("We can make " + list.size() + " words from "+ "\"" + str + "\"");
      System.out.println("All of the words with their scores(sorted by score):");
      for( int i = 0 ; i < list.size(); i++ )
      {
         System.out.println(scoreTable.getVal(list.get(i)) + ": " + list.get(i));
      }
   }
}

