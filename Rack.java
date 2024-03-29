
import java.util.ArrayList;
import java.util.Arrays;


/**
   A Rack of Scrabble tiles
 */

public class Rack {
      private ArrayList<String> rack;
      
      public Rack(String str) {
         rack =new ArrayList<String>();
         
         char[] charArray = str.toCharArray();
         
         Arrays.sort(charArray);
         ArrayList <Integer> coefficient = new ArrayList<Integer>();
         int count = 0;
         char compare = charArray[0];
         String newStr = "" + compare; //find the set of all characters in given string
         for(int i = 0 ; i < charArray.length; i++)//find how many times each character shows up
         {
            if(charArray[i] == compare)
            {
               count ++;
            }
            else 
            {
              coefficient.add(count);
              count = 1;
              compare = charArray[i];
              newStr = newStr + compare;
            }
            
         }
         coefficient.add(count);
         int [] multi = new int[coefficient.size()];
         for(int i = 0; i < coefficient.size(); i++)
         {
            multi[i] = coefficient.get(i);  
         }
         rack = allSubsets(newStr, multi, 0);//find all subset of given rack
         rack = reorder(rack);//reorder all the characters in alphabetic order in each string 
         
      }
      public ArrayList<String> getRack()
      {
         return rack;
      }
   /**
      Finds all subsets of the multiset starting at position k in unique and mult.
      unique and mult describe a multiset such that mult[i] is the multiplicity of the char
           unique.charAt(i).
      PRE: mult.length must be at least as big as unique.length()
           0 <= k <= unique.length()
      @param unique a string of unique letters
      @param mult the multiplicity of each letter from unique.  
      @param k the smallest index of unique and mult to consider.
      @return all subsets of the indicated multiset

    */
   private static ArrayList<String> allSubsets(String unique, int[] mult, int k) {
      ArrayList<String> allCombos = new ArrayList<>();
      
      if (k == unique.length()) {  // multiset is empty
         allCombos.add("");
         return allCombos;
      }
      
      // get all subsets of the multiset without the first unique char
      ArrayList<String> restCombos = allSubsets(unique, mult, k+1);
      
      // prepend all possible numbers of the first char (i.e., the one at position k) 
      // to the front of each string in restCombos.  Suppose that char is 'a'...
      
      String firstPart = "";          // in outer loop firstPart takes on the values: "", "a", "aa", ...
      for (int n = 0; n <= mult[k]; n++) {   
         for (int i = 0; i < restCombos.size(); i++) {  // for each of the subsets 
                                                        // we found in the recursive call
            // create and add a new string with n 'a's in front of that subset
            allCombos.add(firstPart + restCombos.get(i));  
         }
         firstPart += unique.charAt(k);  // append another instance of 'a' to the first part
      }
      
      return allCombos;
   }
   /**
     Reorder the characters in each string in the arraylist
    @param old an arraylist contains several strings
    @return all characters are in alphabetic order of each string in the arraylist
    */
   private static ArrayList<String> reorder(ArrayList<String> old)
   {
      ArrayList<String> newString = new ArrayList<String>();
      for(int i = 0 ; i < old.size(); i++)
      {
         char [] temp = old.get(i).toCharArray();
         Arrays.sort(temp);
         newString.add(String.valueOf(temp));
         
         
      }
      return newString;
   }
}
