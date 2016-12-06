import java.util.*;

/**
 * Created by ignacioojanguren on 28/10/16.
 * In this class we will sort an array with integer values.
 * We will compare the different amount of time each type of Sort will take to sort the array.
 */
public class SortingTypes {

    /** O(n) Algorithm
     *
     * This method creates a new array with the size of the biggest value in the array.
     * Drawbacks: 1) Hard to handle the duplicate elements
     *            2) Have to find the biggest element in the unsorted list
     * Solution to the duplicate elements is to use a linked list to count the duplicate elements.
     *
     * @param sortIntegers
     *  is the int array that contains numbers unsorted
     * @postcondition
     *  sorts the array of integer counting the duplicates
     */
    public static void bucketSort(int[] sortIntegers){
        int[] bucketElements;
        int[] countDuplicated;
        int biggestElement = 0;
        for(int i = 0; i < sortIntegers.length; i++){
            if(sortIntegers[i] > biggestElement) biggestElement = sortIntegers[i];
        }
        bucketElements = new int[biggestElement + 1];
        countDuplicated = new int[biggestElement + 1];

        for(int i = 0; i < sortIntegers.length; i++){
            if(bucketElements[sortIntegers[i]] != 0) countDuplicated[sortIntegers[i]] += 1;
            else{
                bucketElements[sortIntegers[i]] = sortIntegers[i];
                countDuplicated[sortIntegers[i]] = 1;
            }
        }

        System.out.println("Bucket Sort ->" + printList(bucketElements, countDuplicated));

    }

    /** O(n^2) Algorithm
     *
     * This algorithm on the worst of the cases the runtime would be O(n^2).
     * The algorithm compares each value of the array with the next value, until until it finds a value bigger than the
     * current.
     * @param sortIntegers
     *  Array with integers to sort
     * @postcondition
     *  The array will be sorted and printed on the screen
     */
    public static void bubbleSort(int[] sortIntegers){
        int bubble = 0;
        for(int i = 0; i< sortIntegers.length; i++){
            for(int j = 0; j < sortIntegers.length; j++){
                bubble = sortIntegers[i];
                if(bubble < sortIntegers[j]){
                    sortIntegers[i] = sortIntegers[j];
                    sortIntegers[j] = bubble;
                }
            }
        }

        System.out.println("Bubble Sort ->" + printList(sortIntegers, null));

    }

    /** O(n^2) Algorithm
     *
     * This method searches for the smallest element in the list, once the element is found the element is moved to
     * the first position of the array, and so on with the next elements.
     * @param sortIntegers
     *  Array which contain the integers to sort
     * @postcondition
     *  Sorted the elements by looking for the smallest elements, and print the array sorted.
     */
    public static void selectionSort(int[] sortIntegers){
        int smallest = 0;
        int bubble;
        for(int i = 0; i < sortIntegers.length - 1; i++){
            smallest = i;
            for(int j = i + 1; j < sortIntegers.length; j++){
                if(sortIntegers[j] < sortIntegers[smallest])
                    smallest = j;
            }
            bubble = sortIntegers[i];
            sortIntegers[i] = sortIntegers[smallest] ;
            sortIntegers[smallest] = bubble;
        }

        System.out.println("Selection Sort ->" + printList(sortIntegers, null));

    }

    /** O(n^2) Algorithm
     *
     * This method starts sorting the array, the next element found in the array, will be compared with the
     * part of the array that has been sorted so far. This will happen with every element until the list is sorted.
     * @param sortIntegers
     *  Array with integers that has to be sorted
     * @postcondition
     *  The array will be sorting by comparing the part of the array sorted with the elements in the array not sorted.
     */
    public static void insertionSort(int[] sortIntegers){
        int bubble, index;
        for(int i = 0; i < sortIntegers.length;i++){
            bubble = sortIntegers[i];
            index = i;
            while (bubble > sortIntegers[index] && index > 0){
                sortIntegers[index] = bubble;
                index--;
            }
            sortIntegers[index] = bubble;
        }

        System.out.println("Insertion Sort ->" + printList(sortIntegers, null));

    }

    /**
     * This methods is recursive and it divides the array to the smallest portion.
     * We use the class comparable to be able to compare the Integer values in the array.
     * @param sortIntegers
     *  Array that has to be sorted
     * @return
     *  sortIntegers is the array with Integers sorted.
     */
    public static Comparable[] merge(Comparable[] sortIntegers){
        if(sortIntegers.length <= 1 ){
            return sortIntegers;
        }

        Comparable[] firstHalf = new Comparable[sortIntegers.length/2];
        Comparable[] secondHalf = new Comparable[sortIntegers.length -  firstHalf.length];

        System.arraycopy(sortIntegers,0, firstHalf, 0, firstHalf.length);
        System.arraycopy(sortIntegers,firstHalf.length, secondHalf, 0, secondHalf.length);

        merge(firstHalf);
        merge(secondHalf);

        mergeSort(firstHalf,secondHalf,sortIntegers);

        return sortIntegers;
    }

    /**
     * This method organizes the arrays that were divided in small pieces, once the values are compared, the
     * arrays are merged to the sortList array.
     * The sorting time for the merge sort is O(n log n). The log n comes from the height of the array when we subdivide to smaller
     * arrays it takes log n.
     * @param firstHalf
     *  First half of the array to compare
     * @param secondHalf
     *  Second half of the array to compare
     * @param sortList
     *  Array were the sorted elements are going to be stored
     * @postcondition
     *  The array sorted
     */
    public static void mergeSort(Comparable[]firstHalf, Comparable[] secondHalf, Comparable[] sortList){
        int iFirst = 0, iSecond = 0, iList = 0;

        while(iFirst < firstHalf.length && iSecond < secondHalf.length){
            if(firstHalf[iFirst].compareTo(secondHalf[iSecond]) < 0){
                sortList[iList] = firstHalf[iFirst];
                iFirst++;
            }else{
                sortList[iList] = secondHalf[iSecond];
                iSecond++;
            }
            iList++;
        }
        System.arraycopy(firstHalf, iFirst, sortList,iList, firstHalf.length - iFirst);
        System.arraycopy(secondHalf, iSecond, sortList,iList, secondHalf.length - iSecond);
    }

    /**
     * This method prints the values in the array sorted.
     * @param bucketElements
     *  Contains the values of the array sorted
     * @param countDuplicated
     *  In case the sort wasn't bucket sort, the value will be null
     * @return
     *  Returns a String with the values of the array.
     */
    public static String printList(int[] bucketElements, int[] countDuplicated){
        String listSorted = "";

        //Check whether is a bucket list or not
        if(countDuplicated != null){
            for (int i = 0; i < bucketElements.length; i++){
                while(countDuplicated[bucketElements[i]] != 0){
                    listSorted += " " + bucketElements[i];
                    countDuplicated[bucketElements[i]]--;
                }
            }
        }else{
            for (int i = 0; i < bucketElements.length; i++){
                listSorted += " " + bucketElements[i];
            }
        }
        return listSorted;
    }


    public static void main(String[] args){
        long tStart = System.currentTimeMillis();
        long tEnd, tFinal;
        double seconds;

        int[] sortInt = {1,5,5,3,3,3,6,2,1,4,9,999999,2398,123,123,132,134,5423,24512,3541,4,5,462,2345,4,5,6,566,35,422,
        453,7,7,7,5,45,45,45,654,456,654,456,654,7546,1,4556,94,643,723,4567,2377,464,345,7293,666,746,345,345,345};
        bucketSort(sortInt);
        tEnd = System.currentTimeMillis();
        tFinal = tEnd - tStart;
        seconds = tFinal / 1000.0;
        System.out.println("Time -> " + seconds);

        bubbleSort(sortInt);
        tEnd = System.currentTimeMillis();
        tFinal = tEnd - tStart;
        seconds = tFinal / 1000.0;
        System.out.println("Time -> " + seconds);

        selectionSort(sortInt);
        tEnd = System.currentTimeMillis();
        tFinal = tEnd - tStart;
        seconds = tFinal / 1000.0;
        System.out.println("Time -> " + seconds);

        insertionSort(sortInt);
        tEnd = System.currentTimeMillis();
        tFinal = tEnd - tStart;
        seconds = tFinal / 1000.0;
        System.out.println("Time -> " + seconds);

        Integer[] sortIntegers = {1,5,5,3,3,3,6,2,1,4,9,999999,2398,123,123,132,134,5423,24512,3541,4,5,462,2345,4,5,6,566,35,422,
        453,7,7,7,5,45,45,45,654,456,654,456,654,7546,1,4556,94,643,723,4567,2377,464,345,7293,666,746,345,345,345};
        merge(sortIntegers);
        System.out.println("Merge Sort -> " + Arrays.toString(sortIntegers));
        tEnd = System.currentTimeMillis();
        tFinal = tEnd - tStart;
        seconds = tFinal / 1000.0;
        System.out.println("Time -> " + seconds);
    }
}
