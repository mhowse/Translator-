/* 
 * File:   main.cpp
 * Author: mhowse
 * This will be a german-english translator. 
 * It will be somewhat simpler then the java translator.  
 * Created on 22 March 2016, 10:20 PM
 */

#include <cstdlib>

using namespace std;

/*
 * 
 */
int main(int argc, char** argv) {
    char* eng_days [7] ={"Monday","Tuesday","Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    char* ger_days[7] ={"Monntag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag", "Sonntag"};
    char*  German [4]= {"alles","die","der","das"};
    char*  English [4]={"all","the, feminine or plural", "the, masculine","the, neuter"};
    
    return 0;
}

/**
 * @param char* input, the word to be translated
 * @param int language 1==english to german, 0 == german to english 
 */
void translate(char* input, int language){
    
}
