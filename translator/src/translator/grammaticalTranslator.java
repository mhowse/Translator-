

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
        for (String str:sentan){
          System.out.println(str);
        }
        for(int ind =0; ind<sentan.length; ind++){
          wordToTranslate = sentan[ind]; 
          if(wordToTranslate.equals("exit()")){ //the brackets in the exit phrase are to prevent accidently
            System.exit(1);
            return;
          }
         answers[ind] =  translateWord(wordToTranslate);
        } //end of for loop
     
      System.out.println("translated sentance is");
    for(String s:answers){
      System.out.println(s);
    }
    String finalSentance =grammarCheck(answers);
  } //end method 
  
  /**
   * 
   * @param input
   * @return 
   */
   private String translateWord(String input){
       return "derp"; 
   }// end method
   
   private String grammarCheck(String [] toBeChecked){
       return "derpypanda";
   }
}//end class
  
    