/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Translator.java 
 * @author mhowse 
 * February 2016. 
 */
package translator;

/**
 * TO DO
 * problem somewhere in translator constructor OR in getPathDictionary 
 * = this leads to the dictionary sheet not loading WHEN RUN IN NETBEANS. 
 * dictionary sheet loads perfectly in drjava.
 * make it so we can translate sentances. 
 */

import java.io.File; 
import java.util.Scanner;
import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;
/** The translations will be done word for word, from  a stored dictionary  spreadsheet. 
  * The contents of the spread sheet will be a simplified form of the translations found in the 
  * Oxford German Mini Dictionary (Oxford University Press, 2008). 
  * Earlier in this project we used a stored dictionary was based on the top 50 used German words, 
  * as found at 
  * http://www.languagedaily.com/learn-german/vocabulary/common-german-words
  * 
  * The spreadsheet manipulation comes from the jOpenDocument libraries.
  */


public class Translator {
  private static String direction = ""; 
  private String wordToTranslate ="";
  private String currentCell = ""; 
  private int rowCount;
  private int colCount;
  private String path =""; 
  private int length=0; 
  private final int low = 3;  /*this is a hard coded number because all the lists of words start in the same row.*/ 
  private int high; 
  private int xCoordinate=0;
  /* variables for the locations of particular letters in the dictionarys.*/
  private int a,b,c,d,e,f,g,h,i,j; 
  private static final String grtstring1="Welcome to Translator.java. "
          + "This is a simple translator which currently"
          + " runs word by word translations from german to"
          + "   english.";
   private static final String grtstring2="There are plans to change this and increase "
          + "functionality in future, but for now follow the prompts and watch it work."
          + "/n If at any point you wish to change what the translator is doing "
           +"merely enter \"exit()\" and the program will close down the running translator.";
  
  
   private static boolean state =true;
  /**main method
    * 
    * @param args 
    * @throws java.lang.Exception 
    */
  public static void main (String [] args) throws Exception{
    greeting();
    while(state){ //while loop so one can change between translators later, when there are dictionaries for other translations. 
    chooseTranslation(); //set direction
    //quick fix in case user derps, only one direction atm. 
    direction = "German"; 
    Translator simple = new Translator(); //create translator
    //do i want a seperate translator for each direction? and just switch which one is being used?
  }
    Scanner scans = new Scanner (System.in);
    System.out.println("Now enter a 3 word sentance, such as \' ich mag du\' ");
   String inp= scans.nextLine();
    Translator trans = new Translator(inp);
  }//end main method
  
  /**
   * translator for translating simple sentences. 
   * @param sentance 
   * 
   */
  private  Translator(String sentance){
      String [] sentan = sentance.split(" "); 
      String [] answers = new String [sentan.length]; 
      direction = "German"; 
       setCoordinates();
      try{
        getPathDictionary();
        File file = new File(path);  
        final Sheet sheet = SpreadSheet.createFromFile(file).getSheet(0);
        colCount = sheet.getColumnCount();
        rowCount = sheet.getRowCount();
        System.out.println("Sheet loaded, about to for loop");
        for (String s:sentan){
          System.out.println(s);
        }
        for(int ind =0; ind<sentan.length; ind++){
          wordToTranslate = sentan[ind]; 
          if(wordToTranslate.equals("exit()")){ //the brackets in the exit phrase are to prevent accidently
            //exiting while trying to search for words relating to  an egress. 
              state=false;
            return;
          }
         answers[ind] =  translateWord(wordToTranslate, sheet);
        } //end of for loop
      } catch (Exception err){
        System.out.println("Error in the translator constructor");
        System.out.println(err.getMessage());
        // e.printStackTrace(); for testing and finding problems
        System.out.println("error message end");
      }
      System.out.println("Answers are ");
    for(String s:answers){
      System.out.println(s);
    }
  } //end method 
  
      
      
  
  
  private static void greeting(){
      System.out.println(grtstring1);
      System.out.println(grtstring2);
  }
  
  /**
   * setCoordinates method sets the locations at which the columns for various 
   * letters words may be found. 
   * 
   */
  private void setCoordinates(){
    if(direction.equals("German")){
      a =0;
      b=2;
      c=4;
      d=6;
      e=8;
      f=10;
      g=12;
      h=14;
      i=16;
      j=18; //etcetera, fill out completly once the dictionary is that far. 
      
    }
  }
  
  
  /**
   *  Translator constructor 
   * the string indicates which direction the translation will run,
   * At some point in the future this will be a difference between loading an english 
   * or a german dictionary, and translating from english to german or vice versa. 
   * At the moment however we just have the one dictionary. 
   * @param string 
   */ 
  private Translator ( ){
    setCoordinates();
    if (direction.equals("German")){
      // Load the specific dictionary file.
      try{
        getPathDictionary();
        File file = new File(path);  //this is where issue occurs
        final Sheet sheet = SpreadSheet.createFromFile(file).getSheet(0);
        colCount = sheet.getColumnCount();
        rowCount = sheet.getRowCount();
        // so one can iterate through each row of the selected sheet
        //this is so one can search for the word.  colcount/2 = number of letters words start with.
        //setup complete, start taking and translating words. 
        Scanner scan = new Scanner(System.in);
        System.out.println("Type in the german word, and an english translation will appear"); 
        while (scan.hasNext()){ //
          wordToTranslate = scan.nextLine(); 
          if(wordToTranslate.equals("exit()")){ //the brackets in the exit phrase are to prevent accidently
            //exiting while trying to search for words relating to  an egress. 
              state=false;
            scan.close();
            return;
          }
          translateWord(wordToTranslate, sheet);
        } //end of while section
      } catch (Exception err){
        System.out.println("Error in the translator constructor");
        System.out.println(err.getMessage());
        // e.printStackTrace(); for testing and finding problems
        System.out.println("error message end");
      }
    }//if clause ends.
  } //end method 
  
  /**
   * Takes the word to be translated, searches the dictionary for it. 
   * Finds the translation of said word (in next column over) and returns that translation.
   * @param sheet the Sheet which contains the dictionary.
   * @param input string that is to be translated
   * @return string that is the translation of the word 
   * 
   * To do, change the dictionary file so that the words are sorted 
   * alphabetically rather then by most common usage. 
   * Then can use binary search or something similar, to make for a more 
   * efficient and fast word finding. 
   * Due to the large numbers of words, make a different column for each starting letter. 
   * A column of words that start with a, one for words that start with b. ect. 
   * The translateWord method can direct the search function to the correct column. 
   * and then call the search function. 
   * This is like a really basic hash table. 
   */
  private String translateWord(String input, Sheet sheet){
     System.out.println("translateWord has been called");
     input =input.toLowerCase();
    try {
      char character=input.charAt(0); 
      String small =""+character;
      String result= small.toLowerCase();
      setXCoord(result);    
      findLength( sheet, xCoordinate);
      /*keep this stuff untill the change to hash system has been completed,*/
     result = binarySearch(input,sheet, xCoordinate );
      return result;
    } catch (Exception err){
      System.out.println("error in translateWord");
      System.out.println(err.getMessage());
      // e.printStackTrace(); for testing and finding problems
      System.out.println("error message end");
    }
    return "Something went wrong."; 
  }
  /**
   * takes the character at the start of the word(as a string) and then sets the x 
   * coordinate for the correct column in the dictionary. 
   * uses a switch loop case thing to set the xCoordinate to the correct thing depending on input word. 
   * @param str the character at the start of the word
   */
  private void setXCoord(String str){
    //find correct column based off first letter. 
    switch (str){
      case"a":  
        xCoordinate=a;
        break;
      case "b":
        xCoordinate=b;
        break;
      case "c":
        xCoordinate=c;
        break;
      case "d":
        xCoordinate=d;
        break;
      case "e":
        xCoordinate=e;
        break;
      case "f":
        xCoordinate=f;
        break;
      case "g":
        xCoordinate=g;
        break;
      case "h":
        xCoordinate=h;
        break;
      case "i":
        xCoordinate=i;
        break;
      case "j":
        xCoordinate=j; //etcetera, fill out completly once the dictionary is that far. 
        break;
      default: 
        System.out.println("default case");
        xCoordinate =0;
        break;
    } // end switch case
  }
  
  
  /**Finds the length of the column of words in the dictionary.
    * 
    * @param a  the sheet that holds the words
    * @param x  the specific column. 
    * The lengths of the columns is stored in the 3rd cell down, just under the letter declaration.
    * so the first word is in the 4th cell down. 
    * store line lengths in different document
    * 
    */
  
  private void findLength(Sheet a, int x){
    Object l;
    try {
      l = a.getValueAt(x, 2); 
      int result = 0; // we have to initialize it here!
      int foo = Integer.valueOf( l.toString());
      length = foo;
    } catch (Exception err){
      System.out.println("error in findLength");
      System.out.println(err.getMessage());
      //e.printStackTrace();// for testing and finding problems 
      System.out.println("error messages end");
    } //end catch
  }//end method,
  
  /**
   * binary searches the correct column. 
   * will use an iterative form of binary search. 
   * @param String which is the word which is searched. 
   * @param Sheet  which is the dictionary. 
   * @param int x which is the xcoordinate of the column, tells the function which column to search. 
   * @returns the translation, or a non found message. 
   */
  private String binarySearch(String s,Sheet b,  int x){
    System.out.println("binary search");
    int mid; 
    high =length+low;  // this is a key part, needs to be here to update length to the correct value for the column. 
    int whileCount=0;
    int currLow = low;  // low = 3 this is where the words begin. 0 based coordinates. 
    Object word;
    Object translation;
    //length = the number of words in the list.
    while(high >= currLow){ //while the current cell is less then the end of the list. 
      whileCount++;
      int guess = currLow + ((high - currLow) / 2);
      word = b.getValueAt(x, guess); 
      String currWord =(String) word;
      currWord =currWord.toLowerCase();
      if(currWord.equals(s)){
        translation =b.getValueAt(x+1, guess);
        return ""+translation;
      }
      if( AlphabeticallyHigherThen( (String)currWord, s)){ //if alpha returns true, then currentword is smaller, 
        //and higher in alphabet  order then the searched for word. so you need to look further down dictionary column
        currLow = guess +1;
      }else{ 
        high = guess - 1;
      }
    }
    return "word not found in dictionary";
  }//end binarySearch method
  
  /**
   * @param string w the word that is in the current cell. 
   * @param string s the word that is being searched for in the dictionary. 
   * @return true if the string w is higher in alphabetical order then string s
   * so if the current cell's word is smaller then the searched word
   */
  private Boolean AlphabeticallyHigherThen( String w, String s){
    char [] wordArray = w.toCharArray();
    char [] sArray =s.toCharArray();
    int wSize = wordArray.length;
    int sSize =sArray.length;//size of searched for word
    for (int ind =0; ind<sSize; ind++){//for each character in the searching string.
      if (ind<wSize && ind<sSize){  // if i is within the array bounds of both arrays. 
        char ss=sArray[ind];
        char ww =wordArray[ind];
        if (ss == ww){ //if the characters are identical move to next character
        } else return (ss>ww);
         //if the search string is smaller in value =higher alphabetically 
            //so if m is bigger then f return true; 
      } //if it is outside the bounds of one array
      /*What happens if both words are identical untill certain point, but then one is longer then other?
       If it gets to this code section, then that means it's been identical to here, and that 
       sSize is bigger then wSize, and is not already shown to be  further or later in dictionary. 
       then that means it is bigger. because it is identical to the point that wSize runs out at. 
       For example (in english) attract and attraction. The later has an extra 3 letters. 
       */
      if(ind>wSize){
        return sSize >wSize;
      }
    }//end for loop
    System.out.println("for loop ended, default called.");
    return false;
  }//end method                            
  
  
  /**
   * getPathDictionary handles the path creation for loading the correct dictionary. 
   * @return string replace. 
   * 
   */
  
  private void getPathDictionary(){
    try{
      String str=""; //adds in the path suffix to load the correct dictionary. 
      if (direction.equals ("German")){
        str = "/resources/dictionary.ods";
      }
      String halfpath=System.getProperty("user.dir");
      char ch='/';
      halfpath+=str;
      String replace = halfpath.replace("\\", "/");
      path = replace;
    } catch(Exception err){
      System.out.println("error in getPathDictionary");
      System.out.println(err.getMessage());
      //e.printStackTrace();// for testing and finding problems  
      System.out.println("error messages end");
    }
    //quick test. 
    path ="dictionary.ods";
  } //end method
  
  /** Chooses which way the translation will 
    * run in, English to German v.s. German to English
    * Currently does this by running the default to english. 
    * Later will need which language to you want to translate to -from which other language.
    * i.e. so that one could translate from german to english, english to french, or english to german. 
    */
  private static void chooseTranslation () {
    try (Scanner scan = new Scanner (System.in)) {
      System.out.println("What language do you want to translate from?");
      System.out.println("This translator will then translates words in that language to english.");
      direction = scan.nextLine();
    }
  }//end method
  
  
}//end class