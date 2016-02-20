/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package translator;

import java.io.File;
import java.util.Scanner;

/**
 * Translator.java 
 * @author mhowse 
 * February 2016. 
 */
public class Translator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
       
} // end class

/**simpleTranslator.java
  * mhowse February  2016.
  * 
  */


package smallprojectsgit.translator; 

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


public class simpleTranslator {
  private static String direction = ""; 
  private String wordToTranslate ="";
  private String currentCell = ""; 
  private int rowCount;
  private int colCount;
  private String path =""; 
  private int length=0; 
  private final int low = 4;  /*this is a hard coded number because all the lists of words start in the same row.*/ 
  private int hight =0; 
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
    simpleTranslator simple = new simpleTranslator(direction); //create translator
    //do i want a seperate translator for each direction? and just switch which one is being used?
    
  }
  
  
  /**
   * simple translate constructor 
   * the string indicates which direction the translation will run 
   */
  private simpleTranslator (String i ){
    if (direction.equals("German")){
      // Load the specific dictionary file.
        try{
         // getPathDictionary();
          File file = new File(getPathDictionary()); 
          System.out.println("loading file");
        final Sheet sheet = SpreadSheet.createFromFile(file).getSheet(0);
        System.out.println("Sheet created");
        colCount = sheet.getColumnCount();
        rowCount = sheet.getRowCount();
        System.out.println("Rows :"+rowCount);
        System.out.println("Cols :"+colCount);
        // so one can iterate through each row of the selected sheet
        //this is so one can search for the word. 
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
    }//if loop 
  } //end method 
  
  /**
   * Takes the word to be translated, searches the dictionary for it. 
   * Finds the translation of said word (in next column over) and returns that translation.
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
      char c=a.charAt(0); 
      String d =""+c;
     String e= d.toLowerCase();
      //find correct column based off first letter. 
      switch (e){
      case"a": 
      xCoordinate=1;
      case "b":
      xCoordinate=3;
      case "c":
      xCoordinate=5;
      case "d":
      xCoordinate=7;
      case "e":
      xCoordinate=9;
      case "f":
      xCoordinate=11;
      case "g":
      xCoordinate=13;
      case "h":
      xCoordinate=15;
      case "i":
      xCoordinate=17;
      case "j":
      xCoordinate=19; //etcetera, fill out completly once the dictionary is that far. 
      default:
      break;
  } // end of switch case
    
      findLength( b, xCoordinate);
      /*keep this stuff untill the change to hash system has been completed,*/
      int h =1; 
      System.out.println(binarySearch(a, b,1 ));
      System.out.println("starting for loop");
/*
     for (int i =0; i <rowCount; i++){
         c = b.getValueAt(1,i).toString();
         System.out.println("for loop "+ c);
         if (c.equals(a) ){
             System.out.println(a+ "means "+ b.getValueAt(2,i) + "in german." );
             break; //need to exit out of for loop here. 
         }
     } 
      */
     return d; 
  }
  
  /**Finds the length of the column of words in the dictionary.
   * 
   * @param a  the sheet that holds the words
   * @param x  the specific column. 
   * options: hard code line lengths, have to be updated each time words are added. 
   * store line lengths in different document
   */
  
  private void findLength(Sheet a, int x){
      
      
  }
  
  /**
   * binary searches the correct column. 
   * Choices to be made. 
   * Do I want to use an iterative, or recursive form of binary search?
   * probably iterative. 
   * @returns the translation, or a non found message. 
  */
  private String binarySearch(String s,Sheet b,  int a){
      String d ="This is where the search function will go"; 
      
      return d;
  }
  
  
  /**
   * getPathDictionary handles the path creation for loading the correct dictionary. 
   * @return string replace. 
   * 
   */
  
  private String getPathDictionary(){
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
        return replace;
  }
  
  /** chooses which way the translation will 
    * run in, English to German v.s. German to English
    */
  private static void chooseTranslation () {
    try (Scanner scan = new Scanner (System.in)) {
      System.out.println("What language do you want to translate to?");
      direction = scan.nextLine();
    }
  }
  
  
}//end class