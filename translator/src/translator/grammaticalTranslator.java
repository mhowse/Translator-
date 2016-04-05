

package translator; 

import java.util.Scanner;


public class grammaticalTranslator {

   private static String wordToTranslate;
   private static final String [] german = {"bin","bist","drei", "du", "ein", "ich","mag", "magst","vier","wir" , "zwei"};
   private static final String [] english ={"am","are","three", "you","one", "I", "like","like", "four","we", "two" };
   
   
   
   
  /**main method
    * 
    * @param args 
    * @throws java.lang.Exception 
    */
  public static void main (String [] args) throws Exception{
    Scanner scans = new Scanner (System.in);
    System.out.println("Now enter a 3 word sentance, such as \' ich mag du\' or \'ich bin vier\' ");
    System.out.println("Using only the following words.");
    printArray(german);
     String inp= scans.nextLine();
    grammaticalTranslator trans = new grammaticalTranslator(inp);
  }//end main method
  
  /**
   * Will print a string array.
   * @param array 
   */
  private static void printArray(String [] array){
       for (String array1 : array) {
           System.out.print(array1 + "\t");
       }
  }
  /**
   * translator for translating simple sentences. 
   * @param sentance 
   * 
   */
  private  grammaticalTranslator(String sentance){
      String [] sentan = sentance.split(" "); 
      String [] answers = new String [sentan.length];
        printArray(sentan);
        for(int ind =0; ind<sentan.length; ind++){
          wordToTranslate = sentan[ind]; 
          if(wordToTranslate.equals("exit()")){ //the brackets in the exit phrase are to prevent accidently
            System.exit(1);
            return;
          }
         answers[ind] =  translateWord(wordToTranslate);
        } //end of for loop
     
      System.out.println("translated sentance is");
    printArray(answers);
    String finalSentance =grammarCheck(answers);
  } //end method 
  
  /**
   * 
   * @param input
   * @return 
   */
   private String translateWord(String input){
     int result =  binarySearch(german,input,0,german.length-1); 
     if (result == -1){
         return "Word "+input+"not found";            
     }
     System.out.println("result ="+result);
       return "derp";
       
   }// end method
   
   
   private int binarySearch (String[] array, String in,int low, int high ){
        if(high < low){
                return -1; //impossible index for "not found"
        }
        int guess = (high + low) / 2;
        if( AlphabeticallyHigherThen(array[guess], in)){
                return binarySearch(array, in, low, guess - 1);
        }else if(AlphabeticallyHigherThen(in, array[guess])){
                return binarySearch(array, in, guess + 1, high);
        }
        return guess;
}
     
  /**
   * @param string w the word that is in the current cell. 
   * @param string s the word that is being searched for in the dictionary. 
   * @return true if the string w is higher in alphabetical order then string s
   * so if the current cell's word is smaller then the searched word. 
   * To see how this method works,see the comments on this method in Translator.java.
   */
  private Boolean AlphabeticallyHigherThen( String w, String s){
    char [] wordArray = w.toCharArray();
    char [] sArray =s.toCharArray();
    int wSize = wordArray.length;
    int sSize =sArray.length; //size of searched for word
    for (int ind =0; ind<sSize; ind++){//for each character in the searching string.
      if (ind<wSize && ind<sSize){  // if i is within the array bounds of both arrays. 
        char ss=sArray[ind];
        char ww =wordArray[ind];
        if (ss == ww){ //if the characters are identical move to next character
        } else return (ss>ww);
         
      } //if it is outside the bounds of one array
      
      if(ind>wSize){
        return sSize >wSize;
      }
    }//end for loop
    System.out.println("for loop ended, default called.");
    return false;
  }
   
   private String grammarCheck(String [] toBeChecked){
       return "derpypanda";
   }
}//end class
  
    