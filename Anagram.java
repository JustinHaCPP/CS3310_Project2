/**************************************************************/
/* Justin Ha                                                  */
/* Login ID: 011706708                                        */
/* CS 3310, Spring 2023                                       */
/* Programming Assignment 2                                   */
/* Anagram class: Set of methods to find a set of possible    */
/* anagrams from words in an input file using a dictionary    */
/**************************************************************/

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Anagram
{
    private String[] dict;
    private String[] words;
    private HashMap<String,List<String>> map;
    public Anagram(String[] dictionary, String[] words)
    {
        this.dict = dictionary;
        this.words = words;
    }
    /**************************************************************/
    /* Method: sort                                               */
    /* Purpose: initializes the quickSort algorithm to sort the   */
    /* dictionary alphabetically and removes duplicates.          */
    /**************************************************************/
    public void sort()
    {
        this.map = transformArray(dict);
    }
    /**************************************************************/
    /* Method: transformArray                                     */
    /* Purpose: Creates a map with a sorted word as the key and   */
    /* the original word as its value                             */
    /* Parameters:                                                */
    /* String[] array: array that has words to map values to      */
    /**************************************************************/
    private HashMap<String,List<String>> transformArray(String[] array)
    {
        HashMap<String,List<String>> result = new HashMap<String,List<String>>();

        for (int i = 0; i < array.length; i++) {
            String stringWord = array[i].toLowerCase();     //Converts String to all lowercase
            char[] charArray = stringWord.toCharArray();    //reformats to a char array to be used for sorting
            quickSort(charArray, 0, charArray.length);
            String key = new String(charArray);

            if (!map.containsKey(key)) {    //if first time inserting into map
                List<String> values = new LinkedList<String>();
                values.add(stringWord);
                result.put(key, values);
            } else {
                result.get(key).add(stringWord);    //If key is already in map, add word to the corresponding LinkedList
            }
        }

        return result;
    }
    /**************************************************************/
    /* Method: quickSort                                          */
    /* Purpose: recursive quickSort algorithm to sort an array of */
    /* char alphabetically                                        */
    /* Parameters:                                                */
    /* char array: array of char to be sorted                     */
    /* int low: bottom of the array partition                     */
    /* int low: bottom of the array partition                     */
    /* int high: top of the array partition                       */
    /**************************************************************/
    private void quickSort(char[] array, int low, int high)
    {
        if (low < high) {
            char pivot = array[high];       //set pivot to top of the array partition
            int i = (low - 1);              //initialize i to the index before the bottom of the array partition
 
            for (int j = low; j <= high - 1; j++) {
                if (array[j] < pivot) {     //if element at j is alphabetically lower than element at pivot, swap and increment i
                    i++;
                    swap(array, i, j);
                }
            }
            swap(array, i + 1, high);
            int partitionIndex = (i + 1);   //Index used to split array and recurisvely run quickSort

            quickSort(array, low, partitionIndex - 1);
            quickSort(array, partitionIndex + 1, high);
        }
    }
    /**************************************************************/
    /* Method: swap                                               */
    /* Purpose: swap array elements in the dictionary             */
    /* Parameters:                                                */
    /* char array: array of char to be sorted                     */
    /* int i: index number to swap                                */
    /* int j: index number to swap                                */
    /**************************************************************/
    private void swap(char[] array, int i, int j)
    {
        char temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    /**************************************************************/
    /* Method: checkDuplicates                                    */
    /* Purpose: checks and removes any duplicates, shifting the   */
    /* array if a duplicate is found. Assumes input array is      */
    /* presorted.                                                 */
    /* Parameters:                                                */
    /* String[] array: array to check duplicates for              */
    /**************************************************************/
    private String[] checkDuplicates(String[] array)
    {
        int count = 0;
        String[] temp = new String[array.length];
        for (int i = 0; i < array.length - 1; i++) { //add unique elements to temp array. iterate count
            if (array[i] != array[i + 1]) {
                temp[count++] = array[i];
            }
        }
        temp[count++] = array[array.length - 1];    //add the last element of the array 

        String[] result = new String[count];        //create new result array with the appropriate size
        for (;count >= 0; count--) {                //copy contents of temp array to result array
            result[count] = temp[count];
        }
        
        return result;
    }
}