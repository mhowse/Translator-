/**
 * englishWords.java
 * mhowse april 2016
 */

package translator;

public class englishWords{

  

    public String name;
    protected String translation;
    protected String type;
    protected String length; 
    protected String [] cases;
    protected String seeAlso;
    
    /**
     * word constructor
     * @param n
     * @param trans
     * @param typ
     * @param forms
     * @param s
     */
    public englishWords(String n, String trans,String typ, String [] forms, String s ){
        name = n;
        translation =trans; 
        type=typ;
        cases = forms;
        seeAlso =s; 
    }
    /**
     * basic constructor.
     */
    public englishWords(){
        
    }
    
    /**
     * prints out the word, and all its data. 
     */
    public void printWord (){
        System.out.println(name +" "+ translation );
        System.out.println("type ="+type);
        System.out.println("Cases = ");
        printArray(cases);
        
    }
    /**
     * 
     * @param array 
     */
     private  void printArray(String [] array){
       for (String array1 : array) {
           System.out.print(array1 + "\t");
       }
  }
    
}//end clas