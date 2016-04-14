

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
      fillWords();
 
      
    
  }//end main method
  
  /**
   * sentance method, checks to see which sentence structure the collection of words makes, 
   * and thus what case endings should be relevant
   * currently checks if order is pronoun -verb- noun (he throws sticks);
   * or definitive-adjective-noun or pronoun-verb-adjective.
   * @param a
   * @param b
   * @param c 
   */
  
  private static void sentance( englishWords a, englishWords b, englishWords c){
      if(a.type.equals("noun")){
          if (b.type.equals("verb")){
              if (c.type.equals("adjective")){
                  System.out.println(a.name +"\t"+b.name+"\t"+c.name);
                  System.out.println("Fits the patter noun -verb-adjective");
                  System.out.println("Such as \' Bob is sad\' ");
                  System.out.println("The translation is ");
                  System.out.println (a.translation+" " +b.translation +" "+c.translation);
                     
              }
          }
      }
  }
  
  
  /**
   * will create a bunch of words. 
   */
  private  static void fillWords(){
      String i = "am"; 
      String t = "bin";
      String ty = "verb";
      String [] f = { "bin", "bist", "ist","ist", "ist", "sind," , "seid", "sind", "sind"};
      String s = "See also have been (past tense of am), will be (future tense)";
      englishWords  am = new englishWords(i, t,ty, f,  s );
      am.printWord();
       i = "I"; 
       t = "ich";
       ty = "noun";
       String [] p = { "ich", "du", "er","sie", "es", "wir," , "Ihr", "Sie", "sie"};
       s = "See also any other pronouns";
       englishWords  I = new englishWords(i,t,ty,p,s);
       i = "red"; 
       t = "rot";
       ty = "adjective";
       String [] pi = { "rot", "rot", "rot","rot", "rot", "rot," , "rot", "rot", "rot"};
       s = "remembr to double check the rot/rote/rotes conversions for red adjective.";
       englishWords  reds = new englishWords(i,t,ty,pi,s);
        sentance(I,am,reds);        
  }
  
  
  
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
        System.out.println("for each word entered");
        for(int ind =0; ind<sentan.length -1; ind++){
            System.out.println("word "+ind +"is "+sentan[ind]);
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
       System.out.println("BINARY SEARCH CALLED");
     int result =  binarySearch(german,input); 
     if (result == -1){
         return "Word "+input+"not found";            
     }
     System.out.println("result ="+result);
       return english[result];
       
   }// end method
   
   /**
    * Iterative  binarySearch.
    * @param array
    * @param in
    * @param low
    * @param high
    * @return 
    */
   private int binarySearch (String[] array, String in ){
       System.out.println("binaryseach called"); 
        int high = array.length - 1;
        int low = 0;
        while(high >= low){
                int guess = low + ((high - low) / 2);
                if(array[guess].equals(in)){
                return guess;
                }
                if(AlphabeticallyHigherThen( array[guess],in)){
                        high = guess - 1;
                }else if(AlphabeticallyHigherThen(in, array[guess])){
                        low = guess + 1;
                }
        }
        return -1; //impossible index for "not found"
} //end method 
       

     
  /**
   * @param string w the word that is in the current cell. 
   * @param string s the word that is being searched for in the dictionary. 
   * @return true if the string w is higher in alphabetical order then string s
   * so if the current cell's word is smaller then the searched word. 
   * To see how this method works,see the comments on this method in Translator.java.
   */
  private Boolean AlphabeticallyHigherThen( String w, String s){ 
     System.out.println("Comparing w="+w+" To s = "+s);
    char [] wordArray = w.toCharArray();
    char [] sArray =s.toCharArray();
    //if guess = w, input = s then, if thefirst letter of 
    //input is earlier in the alphabet then the guess
    if(sArray[0]<wordArray[0]){
        System.out.println("Input earlier in alphabet then guess");
        return false;
    }
    
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
    return false;
  }
   
   private String grammarCheck(String [] toBeChecked){
       return "derpypanda";
   }
}//end class
  
    