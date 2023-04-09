/**************************************************************/
/* Justin Ha                                                  */
/* Login ID: 011706708                                        */
/* CS 3310, Spring 2023                                       */
/* Programming Assignment 2                                   */
/* Anagram class: Set of methods to find a set of possible    */
/* anagrams from words in an input file using a dictionary    */
/**************************************************************/

public class Anagram
{
    private String[] dict;
    private String[] words;
    public Anagram(String[] dictionary, String[] words)
    {
        this.dict = dictionary;
        this.words = words;
    }
    /**************************************************************/
    /* Method: sort                                               */
    /* Purpose: initializes the quickSort algorithm to sort the   */
    /* dictionary alphabetically                                  */
    /**************************************************************/
    public void sort()
    {
        quickSort(0, dict.length - 1);
    }
    /**************************************************************/
    /* Method: quickSort                                          */
    /* Purpose: recursive quickSort algorithm to sort an array of */
    /* String alphabetically                                      */
    /* Parameters:                                                */
    /* int low: bottom of the array partition                     */
    /* int high: top of the array partition                       */
    /**************************************************************/
    private void quickSort(int low, int high)
    {
        if (low < high) {
            String pivot = dict[high];      //set pivot to top of the array partition
            int i = (low - 1);              //initialize i to the index before the bottom of the array partition
 
            for (int j = low; j <= high - 1; j++) {
                if (dict[j].compareTo(pivot) < 0) { //if element at j is alphabetically lower than element at pivot, swap and increment i
                    i++;
                    swap(i, j);
                }
            }
            swap(i + 1, high);
            int pi = (i + 1);

            quickSort(low, pi - 1);
            quickSort(pi + 1, high);
        }
    }
    /**************************************************************/
    /* Method: swap                                               */
    /* Purpose: swap array elements in the dictionary             */
    /* Parameters:                                                */
    /* int i: index number to swap                                */
    /* int j: index number to swap                                */
    /**************************************************************/
    private void swap(int i, int j)
    {
        String temp = dict[i];
        dict[i] = dict[j];
        dict[j] = temp;
    }
}