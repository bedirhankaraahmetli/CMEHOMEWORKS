import java.util.Locale;
import java.util.Random;

public class QuickSort {
    static void quickSort(int[] arrayToSort, String pivotType) { // Main function of quick sort algorithm
        switch (pivotType.toLowerCase(Locale.ENGLISH)) {
            case "firstelement" ->  // Taking first elements as a pivot
                    quickSortFirst(arrayToSort, 0, arrayToSort.length - 1);
            case "randomelement" ->  // Taking random element as a pivot (swapping with first elements it acts like first element)
                    quickSortRandom(arrayToSort, 0, arrayToSort.length - 1);
            case "midoffirstmidlastelement" ->  // Taking the middle of the first - mid - last elements as a pivot
                    quickSortMid(arrayToSort, 0, arrayToSort.length - 1);
        }
    }//end of quickSort

    static int partitionFirst(int[] arr, int low, int high)// Partition method for taking the pivot as a first element
    {
        // Taking first element as a pivot
        int pivot = arr[low];

        int k = high;
        for (int i = high; i > low; i--) {// Comparing from last to first with pivot
            if (arr[i] > pivot) {
                swap(arr, i, k--);
            }
        }
        swap(arr, low, k); // Placing the pivot to the right place
        return k; // Returning pivot's index
    }//end of partitionFirst

    static void quickSortFirst(int[] arr, int low, int high) // Recursive function of quick sort method taking a first element as a pivot
    {
        while (low < high)
        {
            int pivot = partitionFirst(arr, low, high);// Indexing pivot's place after that pivot will be right place

            if (pivot - low < high - pivot) // If pivot's left is smaller sort the left site than handle right side iteratively
            {
                quickSortFirst(arr, low, pivot - 1);
                low = pivot + 1;
            }
            else // Else sort the right side
            {
                quickSortFirst(arr, pivot + 1, high);
                high = pivot - 1;
            }
        }
    }//end of the quickSortFirst

    static int partitionRandom(int[] arr, int low, int high)// Partition method for taking the pivot as a random element(It act like pivot is a first element)
    {
        Random random = new Random();
        int pivot = random.nextInt(high - low) + low; // Choosing pivot randomly

        swap(arr, low, pivot); // Swaping selected random element with first element to act like a pivot is a first element
        int k = high;
        for (int i = high; i > low; i--) {// Comparing from last to first with pivot
            if (arr[i] > pivot)
                swap(arr, i, k--);
        }
        swap(arr, low, k); // Placing the pivot to the right place
        return k; // Returning pivot's index
    }//end of partitionRandom

    static void quickSortRandom(int[] arr, int low, int high)// Recursive function of quick sort method taking a random element as a pivot
    {
        if (low < high) {
            int index = partitionRandom(arr, low, high); // Partition function, after this pivot will be right place and
                                                        // left of the pivot is smaller than pivot and right of the pivot is bigger than pivot

            quickSortRandom(arr, low, index - 1); // Sorting the left side of the pivot
            quickSortRandom(arr, index + 1, high); // Sorting the right side of the pivot
        }
    }

    static int partitionMid(int[] arr, int low, int high) // Partition method for taking the pivot as a middle of the first - mid - last element
    {

        int first = arr[low];
        int mid = arr[(low + high) / 2];
        int last = arr[high];
        int pivot = middleOfThree(first, mid, last); //Choosing pivot as a middle of the first - mid - last element

        int i = low, j = high;
        while (i <= j) {
            while (arr[i] < pivot) i++;
            while (arr[j] > pivot) j--;

            if (i <= j) {
                swap(arr,i,j);
                i++;
                j--;
            }
        }
        return i; // Returning pivot's index
    }// end of partitionMid

    static void quickSortMid(int[] arr, int low, int high)// Recursive function of quick sort method taking a middle of the first - mid - last element as a pivot
    {
        int index = partitionMid(arr, low, high); // Partition function, after this pivot will be right place and
                                                 // left of the pivot is smaller than pivot and right of the pivot is bigger than pivot
        if (low < index - 1)
            quickSortMid(arr, low, index - 1); // Sorting the left side of the pivot
        if (index < high)
            quickSortMid(arr, index, high); // Sorting the right side of the pivot
    }// end of quickSortMid

    public static void swap(int[] arr, int i, int j)// Function to swap two elements in the array
    {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }// end of swap

    public static int middleOfThree(int a, int b, int c) // Function to choose middle of three integer
    {
        // Checking for b
        if ((a < b && b < c) || (c < b && b < a) || a == b || b == c)
            return b;
            // Checking for a
        else if ((b < a && a < c) || (c < a && a < b) || a == c)
            return a;
        else
            return c;

    }// end of middleOfThree

}
