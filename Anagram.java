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
    private HashMap<String,List<String>> map;

    //Constructor
    public Anagram(String[] dictionary)
    {
        this.dict = dictionary;
    }

    /**************************************************************/
    /* Method: anagram                                            */
    /* Purpose: compares a given string with dictionary and       */
    /* returns a list of anagrams                                 */
    /* Parameters:                                                */
    /* String word: word to find list of anagrams for             */
    /**************************************************************/
    public String anagram(String word) 
    {
        String stringWord = word.toLowerCase();         //Converts String to all lowercase
        char[] charArray = stringWord.toCharArray();    //reformats to a char array to be used for sorting
        quickSort(charArray, 0, charArray.length - 1);
        String key = new String(charArray);
        String result;

        if (map.containsKey(key)) {
            List<String> list = map.get(key);
            // FORMAT STRING OUTPUT
            result = "{";
            for (int i = 0; i < list.size(); i++) {
                String s = list.get(i);
                if (!s.equals(word)) {
                    result += s;
                    // if last element is not equal to the word, print comma
                    if (i != list.size() - 1 && !(list.get(list.size() - 1).equals(word) && i == list.size() - 2)) {
                        result += ", ";
                    }
                }
            }
            result += "}";
        } else {
            result = "{}";
        }
        
        return result;
    }

    /**************************************************************/
    /* Method: transformArray                                     */
    /* Purpose: Creates a map with a sorted word as the key and   */
    /* the original word as its value                             */
    /* Parameters:                                                */
    /* String[] array: array that has words to map values to      */
    /**************************************************************/
    public void transformArray()
    {
        HashMap<String,List<String>> result = new HashMap<String,List<String>>();

        for (int i = 0; i < dict.length; i++) {
            String lowerWord = dict[i].toLowerCase();     //Converts String to all lowercase
            char[] charArray = lowerWord.toCharArray();    //reformats to a char array to be used for sorting
            quickSort(charArray, 0, charArray.length - 1);
            String key = new String(charArray);

            if (result.containsKey(key)) {    //If key is already in map and value is not a duplicate
                if (!result.get(key).contains(dict[i])){
                    result.get(key).add(dict[i]);
                }
            } else {    //if first time inserting into map
                List<String> values = new LinkedList<String>();
                values.add(dict[i]);
                result.put(key, values);
            }
        }

        this.map = result;
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
}