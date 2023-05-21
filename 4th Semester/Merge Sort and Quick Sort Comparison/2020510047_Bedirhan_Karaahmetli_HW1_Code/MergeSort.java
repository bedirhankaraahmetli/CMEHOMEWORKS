public class MergeSort {
    static void mergeSort(int[] arrayToSort, int numberOfPartitions){ // Main function of mergeSort method
        if(numberOfPartitions == 2)                                   // If numberOfPartitions equals 2 split the array by 2
            sortTwo(arrayToSort, 0, arrayToSort.length - 1);     // If numberOfPartitions equals 3 split the array by 3
        else if (numberOfPartitions == 3)
            sortThree(arrayToSort);
    }//end of mergeSort
    static void mergeTwo(int[] arr, int l, int m, int r)
    {
        // First finding the sizes of new arrays after splitting
        int n1 = m - l + 1; // The size for the left temporary array Example: (l.....m)
        int n2 = r - m; // The size for the right temporary array Example: ((m+1)......r)

        // Creating temporary arrays to copy data from given arrays
        int[] L = new int[n1];
        int[] R = new int[n2];

        // Copying arrays to temporary arrays manually
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];


        // Merging two arrays (temporary L and R array) in the original array (arr)
        int i = 0, j = 0; // Initial values for choosing elements from temporary arrays
        int k = l;
        while (i < n1 && j < n2) {  // this loop will continue while one of the temporary arrays totally copied to the original array
            if (L[i] <= R[j]) { // Comparing L and R (temporary arrays) 's elements with each other and placing the smallest one to the original array
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        // Copying remaining elements if any from L array to original array even if array is odd
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        // Copying remaining elements if any from R array to original array even if array is odd
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }// Main function that sorts arr[l...r] using merge

    static void sortTwo(int[] arr, int l, int r)
    {
        if (l < r) {
            // Find the middle point
            int m = l + (r - l) / 2;

            // Sort first and second halves
            sortTwo(arr, l, m);
            sortTwo(arr, m + 1, r);

            // Merge the sorted halves
            mergeTwo(arr, l, m, r);
        }
    }//Recursive function that sorts arr[] by dividing into two parts

    public static void sortThree(int[] arr)
    {
        // If array is empty return null
        if (arr == null)
            return;

        // Creating temporary array with the same size as given array
        int[] tempArray = new int[arr.length];

        // Copying giving array to the temporary array manually
        for (int i = 0; i < tempArray.length; i++)
            tempArray[i] = arr[i];

        // Sort function
        mergeSortThree(tempArray, 0, arr.length, arr);

        // Placing elements to the original array from temporary array
        for (int i = 0; i < tempArray.length; i++)
            arr[i] = tempArray[i];
    }// Sorting function that sorts the given arr[] by dividing into three parts

    public static void mergeSortThree(int[] arr, int l, int r, int[] finalArr)
    {
        // If array size is 1 then do nothing
        if (r - l < 2)
            return;

        // Splitting array into 3 parts (l.....m1) - (m1....m2) - (m2.....r)
        int m1 = l + ((r - l) / 3);
        int m2 = l + 2 * ((r - l) / 3) + 1;

        // Sorting 3 arrays using recursive method
        mergeSortThree(finalArr, l, m1 , arr);
        mergeSortThree(finalArr, m1, m2, arr);
        mergeSortThree(finalArr, m2, r, arr);

        // Merging the sorted arrays
        mergeThree(finalArr, l, m1, m2, r, arr);
    }// Recursive function that sorts the given arr by splitting into three parts

    public static void mergeThree(int[] arr, int low, int m1, int m2, int high, int[] finalArr)
    {
        int i = low, j = m1, k = m2, l = low;

        // Choose smaller of the smallest in the three ranges
        while ((i < m1) && (j < m2) && (k < high)) // This loop will continue while indexes are not equal to the upper bound
        {
            if (arr[i] < arr[j])
            {
                if (arr[i]< arr[k])
                    finalArr[l++] = arr[i++];

                else
                    finalArr[l++] = arr[k++];
            }
            else
            {
                if (arr[j] < arr[k])
                    finalArr[l++] = arr[j++];
                else
                    finalArr[l++] = arr[k++];
            }
        }
        // First case (low....m1)
        // Second case (m1....m2)
        // Third case (m2.....high)

        // Placing the elements from first and second cases if any to the original array
        while ((i < m1) && (j < m2))
        {
            if (arr[i] < arr[j])
                finalArr[l++] = arr[i++];
            else
                finalArr[l++] = arr[j++];
        }

        // Placing the elements from second and third cases if any to the original array
        while ((j < m2) && (k < high))
        {
            if (arr[j] < arr[k])
                finalArr[l++] = arr[j++];

            else
                finalArr[l++] = arr[k++];
        }

        // Placing the elements from first and third cases if any to the original array
        while ((i < m1) && (k < high))
        {
            if (arr[i] < arr[k])
                finalArr[l++] = arr[i++];
            else
                finalArr[l++] = arr[k++];
        }

        // Placing the remaining elements if any from first case
        while (i < m1)
            finalArr[l++] = arr[i++];

        // Placing the remaining elements if any from second case
        while (j < m2)
            finalArr[l++] = arr[j++];

        // Placing the remaining elements if any from third case
        while (k < high)
            finalArr[l++] = arr[k++];
    }// // Main function that sorts arr[l...r] using merge
}
