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
 * findLength has an issue, arrayIndexOutOfBounds when reading the numerical FIXED
 * length value from the cells in the spread sheet. 
 * After that binary search needs to be completed. 
 * AlphabeticallyHigherThen needs to be completed.
 * then the entire project needs a tidy up 
 */

import java.math.BigDecimal.*;
import java.io.File; 
import java.util.Scanner;
import java.util.*;
import java.io.*;
import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;
/** The translations will be done word for word, from  a stored dictionary  spreadsheet. 
  * The contents of the spread sheet will be a simplified form of the translations found in the 
  * Oxford German Mini Dictionary (Oxford University Press, 2008). 
  * Earlier in this project we used a stored dictionary will be based on the top 50 used German words, 
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
  private int high =0; 
  private int xCoordinate=0;
  
  
  /**main method
    * 
    * @param args 
    * @throws java.lang.Exception 
    */
  public static void main (String [] args) throws Exception{
    System.out.println("running");
    chooseTranslation(); //set direction
    //quick fix in case user derps, only one direction atm. 
    direction = "German"; 
    Translator simple = new Translator(direction); //create translator
    //do i want a seperate translator for each direction? and just switch which one is being used?
    
  }
  
  
  /**
   *  translate constructor 
   * the string indicates which direction the translation will run 
   * @param string 
   */ 
  private Translator (String i ){
    if (direction.equals("German")){
      // Load the specific dictionary file.
      try{
        getPathDictionary();
        System.out.println("Path is " +path);
        File file = new File(path);  //this is where issue occurs
        System.out.println("loading file");
        
        
        
        
        final Sheet sheet = SpreadSheet.createFromFile(file).getSheet(0);
        System.out.println("Sheet created");
        colCount = sheet.getColumnCount();
        rowCount = sheet.getRowCount();
        System.out.println("Rows :"+rowCount);
        System.out.println("Cols :"+colCount);
        // so one can iterate through each row of the selected sheet
        //this is so one can search for the word.  colcount/2 = number of letters words start with.
        //setup complete, start taking and translating words. 
        Scanner scan = new Scanner(System.in);
        System.out.println("Type in the german word, and an english translation will appear"); 
        wordToTranslate = scan.nextLine(); 
        System.out.println(colCount +"cols accross");
        System.out.println(rowCount+"rows down");
        translateWord(wordToTranslate, sheet);
      } catch (Exception e){
        System.out.println("error");
        System.out.println(e.getMessage());
        // e.printStackTrace(); for testing and finding problems
        System.out.println("error message end");
      }
    }//if clause ends.
  } //end method 
  
  /**
   * Takes the word to be translated, searches the dictionary for it. 
   * Finds the translation of said word (in next column over) and returns that translation.
   * @param the Sheet which contains the dictionary.
   * @param the string that is to be translated
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
  private String translateWord(String a, Sheet b){
    System.out.println("translateWord has been called");
    try {
      char c=a.charAt(0); 
      System.out.println("word starts with "+ c);
      String d =""+c;
      String e= d.toLowerCase();
      //find correct column based off first letter. 
      switch (e){
        case"a":  
          xCoordinate=0;
          break;
        case "b":
          xCoordinate=2;
          break;
        case "c":
          xCoordinate=4;
          break;
        case "d":
          xCoordinate=6;
          break;
        case "e":
          xCoordinate=8;
          break;
        case "f":
          xCoordinate=10;
          break;
        case "g":
          xCoordinate=12;
          break;
        case "h":
          xCoordinate=14;
          break;
        case "i":
          xCoordinate=16;
          break;
        case "j":
          xCoordinate=18; //etcetera, fill out completly once the dictionary is that far. 
          break;
        default: 
          System.out.println("default case");
          xCoordinate =0;
          break;
      } // end of switch case
      
      System.out.println("Column "+xCoordinate+" words begining with " + e);
      System.out.println("Column "+ (xCoordinate+1) +"contains their translations");
      findLength( b, xCoordinate);
      /*keep this stuff untill the change to hash system has been completed,*/
      System.out.println(binarySearch(a, b, xCoordinate ));
      return d;
    } catch (Exception e){
      System.out.println("error in translateWord");
      System.out.println(e.getMessage());
      // e.printStackTrace(); for testing and finding problems
      System.out.println("error message end");
      
    }
    
    return "Somethign went wrong"; 
  }
  
  /**Finds the length of the column of words in the dictionary.
    * 
    * @param a  the sheet that holds the words
    * @param x  the specific column. 
    * The length's of the columns is stored in the 3rd cell down, just under the letter declaration.
    * so the first word is in the 4th cell down. 
    * store line lengths in different document
    * 
    */
  
  private void findLength(Sheet a, int x){
    System.out.println("findLength Method running");
    System.out.println("x = " +x);
    Object l;
    try {
      l = a.getValueAt(x, 2); 
      System.out.println("got here so far l = "+ l);
      int result = 0; // we have to initialize it here!
      int foo = Integer.valueOf( l.toString());
      System.out.println("got this far");
      length = foo;
    } catch (Exception e){
      System.out.println("error in findLength");
      System.out.println(e.getMessage());
      e.printStackTrace();// for testing and finding problems 
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
    int high =length+low; 
    int whileCount=0;
    int currLow = low;  // low = 3 this is where the words begin. 0 based coordinates. 
    Object word;
    Object translation;
    //length = the number of words in the list. 
    System.out.println("Entering While ");
    while(high >= currLow){ //while the current cell is less then the end of the list. 
      whileCount++;
      int guess = currLow + ((high - currLow) / 2);
      word = b.getValueAt(x, guess); 
      String currWord =(String) word;
      currWord =currWord.toLowerCase();
      System.out.println("Current word is "+word);
      if(currWord.equals(s)){
        translation =b.getValueAt(x+1, guess);
        System.out.println("translation = "+translation);
        return ""+translation;
      }
      if(AlphabeticallyHigherThen( (String)currWord, s)){
        high = guess - 1;
      }else{ 
        currLow = guess + 1;
      }
    }
    return "word not found in dictionary";
  }//end binarySearch method
  
  /**
   * @param string w the word that is in the current cell. 
   * @param string s the word that is being searched for in the dictionary. 
   * @return true if the string w is higher in alphabetical order then string w
   */
  private Boolean AlphabeticallyHigherThen( String w, String s){
    char [] wordArray = w.toCharArray();
    char [] sArray =s.toCharArray();
    int wSize = wordArray.length-1;
    int sSize =sArray.length-1;
   
    
    for (int i =0; i<sSize; i++){//for each character in the searching string.
      if (i<wSize && i<sSize){  // if i is within the array bounds of both arrays. 
        char ss=sArray[i];
        char ww =wordArray[i];
        if (ss ==ww){ //if the characters are identical move to next character
          
        } else if (ss>ww){ //if the search string is smaller in value =higher alphabetically 
          return false; 
        } else {
          return true;
        }
        //what happens if both words are identical untill certain point, but then one is longer then other?
      } 
      
    }//end for loop
    return false;
  }//end method                            
  
  
  /**
   * getPathDictionary handles the path creation for loading the correct dictionary. 
   * @return string replace. 
   * 
   */
  
  private void getPathDictionary(){
    try{
      String s=""; //adds in the path suffix to load the correct dictionary. 
      if (direction.equals ("German")){
        s = "/resources/dictionary.ods";
        System.out.println("s is set");
      }
      String y =System.getProperty("user.dir");
      System.out.println(s +"\n"+ y);
      char c='/';
      y+=s;
      System.out.println(y);
      System.out.println("c set");
      String replace = y.replace("\\", "/");
      System.out.println("fixed path " +replace);
      path = replace;
    } catch(Exception e){
      System.out.println("error in getPathDictionary");
      System.out.println(e.getMessage());
      //e.printStackTrace();// for testing and finding problems 
      
      System.out.println("error messages end");
    }
    
    
  } //end method
  
  /** chooses which way the translation will 
    * run in, English to German v.s. German to English
    */
  private static void chooseTranslation () {
    try (Scanner scan = new Scanner (System.in)) {
      System.out.println("What language do you want to translate to?");
      direction = scan.nextLine();
    }
  }//end method
  
  
  }//end class