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
    
}//end clas