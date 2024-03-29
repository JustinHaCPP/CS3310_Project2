/**************************************************************/
/* Justin Ha                                                  */
/* Login ID: 011706708                                        */
/* CS 3310, Spring 2023                                       */
/* Programming Assignment 2                                   */
/* Anagram class: Prints a set of anagrams for each word      */
/* given from an input dictionary file                        */
/**************************************************************/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.LinkedList;

public class Project2
{
    /**************************************************************/
    /* Method: Main                                               */
    /* Purpose: Prints a set of possible anagrams for each word   */
    /* in an input file using a given dictionary                  */
    /* Parameters:                                                */
    /* Command line arguments                                     */
    /**************************************************************/
    public static void main(String[] args) throws FileNotFoundException
    {
        if (args.length == 2) {
            String[] dictionary = readDictData(args[0]);           //Get dictionary from readData function
            LinkedList<String> inFile = readFileData(args[1]);     //Get input file from readData function
            Anagram anagram = new Anagram(dictionary);
            anagram.transformArray();                              //Transforms the dictionary into a HashMap to be used for key comparisons
            for (String x : inFile) {
                System.out.println(x + ": " + anagram.anagram(x));
            }
        } else {
            System.out.println("Usage: java Project2 [Dictionary] [File]");
        }
    }

    /**************************************************************/
    /* Method: readDictData                                       */
    /* Purpose: Reads data from a dictionary file and stores as   */
    /* an array of String                                         */
    /* Parameters:                                                */
    /* String dictName: Path of the dictionary file               */
    /* Returns: String[] array of String                          */
    /**************************************************************/
    public static String[] readDictData(String dictName) throws FileNotFoundException
    {
        try {
            File file = new File(dictName);
            Scanner fileReader = new Scanner(file);
            LinkedList<String> linkedList = new LinkedList<String>();
            int size = 0;                             //number of lines read from dictionary

            //Read data
            while (fileReader.hasNext()) {
                String value = fileReader.next();     //get string from line and store in linked list
                linkedList.add(value);
                size++;
            }
            fileReader.close();
            if (size != 0) {
                String[] result = new String[size];
                linkedList.toArray(result);           //convert dictionary linkedlist to array to sort later
                return result;
            } else {
                System.out.println("Error: Empty dictionary file");
                System.exit(0);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: Dictionary \"" + dictName + "\" not found");
            System.exit(0);
        }

        return null;
    }

    /**************************************************************/
    /* Method: readFileData                                       */
    /* Purpose: Reads data from an input file and stores as       */
    /* an array of String                                         */
    /* Parameters:                                                */
    /* String fileName: Path of the input file                    */
    /* Returns: LinkedList<String> linked list of String          */
    /**************************************************************/
    public static LinkedList<String> readFileData(String fileName) throws FileNotFoundException
    {
        try {
            File file = new File(fileName);
            Scanner fileReader = new Scanner(file);
            LinkedList<String> linkedList = new LinkedList<String>();
            int size = 0;                           //number of lines read from input file

            //Read data
            while (fileReader.hasNext()) {
                String value = fileReader.next();   //get string from line and store in linked list
                linkedList.add(value);
                size++;
            }
            fileReader.close();
            if (size != 0) {
                return linkedList;
            } else {
                System.out.println("Error: Empty input file");
                System.exit(0);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File \"" + fileName + "\" not found");
            System.exit(0);
        }

        return null;
    }
}