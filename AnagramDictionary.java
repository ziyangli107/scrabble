

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


/**
   A dictionary of all anagram sets. 

 */

public class AnagramDictionary {
     private Map<String,ArrayList<String>> dictionary;


   /**
      Create an anagram dictionary from the list of words given in the file
      indicated by fileName.  
      PRE: The strings in the file are unique.
      @param fileName  the name of the file to read from
      @throws FileNotFoundException  if the file is not found
    */
   public AnagramDictionary(String fileName) throws FileNotFoundException {
      dictionary = new HashMap<String,ArrayList<String>>();
      String temp;
      File inFile = new File(fileName);
      try(Scanner in = new Scanner(inFile))
      {
         while(in.hasNext())
         {
            temp = in.next();
            char[] s = temp.toCharArray();
            Arrays.sort(s);
            String result = String.valueOf(s);
            
            
            if(dictionary.containsKey(result))
            {
              ArrayList <String> value = dictionary.get(result);
              value.add(temp);
              dictionary.put(result, value);
            }
            else {
               ArrayList<String> value = new ArrayList<String>();
               value.add(temp);
               dictionary.put(result, value);
            }
              
                     
         }
      } 
 
   }
   

   /**
      Get all anagrams of the given string. This method is case-sensitive.
      E.g. "CARE" and "race" would not be recognized as anagrams.
      @param s string to process
      @return a list of the anagrams of s
    */
   public ArrayList<String> getAnagramsOf(String s) {
      char [] charArray = s.toCharArray();
      Arrays.sort(charArray);
      String newString = String.valueOf(charArray);
      
      return dictionary.get(newString);    
      }
   
   
}
