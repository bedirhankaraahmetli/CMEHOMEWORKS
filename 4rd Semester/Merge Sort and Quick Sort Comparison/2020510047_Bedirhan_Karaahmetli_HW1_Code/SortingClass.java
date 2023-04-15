import java.util.Arrays;
import java.util.Random;

public class SortingClass {
    public static void main(String[] args) {

        // Creating equal integer arrays
        int[] equalThousand = new int[1000];
        int[] equalTenThousand = new int[10000];
        int[] equalHundredThousand= new int[10000];
        Arrays.fill(equalThousand, 3);
        Arrays.fill(equalTenThousand, 3);
        Arrays.fill(equalHundredThousand, 3);

        // Creating random integer arrays
        int[] randomThousand = new int[1000];
        int[] randomTenThousand = new int[10000];
        int[] randomHundredThousand= new int[100000];
        Random random = new Random();
        for (int i = 0; i < randomThousand.length; ++i){
            randomThousand[i] = random.nextInt(0,randomThousand.length);
        }
        for (int i = 0; i < randomTenThousand.length; ++i){
            randomTenThousand[i] = random.nextInt(0,randomTenThousand.length);
        }
        for (int i = 0; i < randomHundredThousand.length; ++i){
            randomHundredThousand[i] = random.nextInt(0,randomHundredThousand.length);
        }

        // Creating increasing integer arrays
        int[] increasingThousand = new int[1000];
        int[] increasingTenThousand = new int[10000];
        int[] increasingHundredThousand= new int[100000];
        for (int i = 0; i < increasingThousand.length; ++i){
            increasingThousand[i] = i;
        }
        for (int i = 0; i < increasingTenThousand.length; ++i){
            increasingTenThousand[i] = i;
        }
        for (int i = 0; i < increasingHundredThousand.length; ++i){
            increasingHundredThousand[i] = i;
        }

        // Creating decreasing integer arrays
        int[] decreasingThousand = new int[1000];
        int[] decreasingTenThousand = new int[10000];
        int[] decreasingHundredThousand= new int[100000];
        for(int i = 0; i < decreasingThousand.length; i++) {
            decreasingThousand[i] = decreasingThousand.length - 1 - i;
        }
        for(int i = 0; i < decreasingTenThousand.length; i++) {
            decreasingTenThousand[i] = decreasingTenThousand.length - 1 - i;
        }
        for(int i = 0; i < decreasingHundredThousand.length; i++) {
            decreasingHundredThousand[i] = decreasingHundredThousand.length - 1 - i;
        }



        // ---------------- TESTING ------------------

        // MERGE SORT - NumberOfPartitions = 2
//        long startTime = System.nanoTime();
//        MergeSort.mergeSort(equalThousand,2); // Equal int 1.000
//        MergeSort.mergeSort(equalTenThousand,2); // Equal int 10.000
//        MergeSort.mergeSort(equalHundredThousand,2); // Equal int 100.000
//        MergeSort.mergeSort(randomThousand,2); // Random int 1.000
//        MergeSort.mergeSort(randomTenThousand,2); // Random int 10.000
//        MergeSort.mergeSort(randomHundredThousand,2); // Random int 100.000
//        MergeSort.mergeSort(increasingThousand,2); // Increasing int 1.000
//        MergeSort.mergeSort(increasingTenThousand,2); // Increasing int 10.000
//        MergeSort.mergeSort(increasingHundredThousand,2); // Increasing int 100.000
//        MergeSort.mergeSort(decreasingThousand,2); // Decreasing int 1.000
//        MergeSort.mergeSort(decreasingTenThousand,2); // Decreasing int 10.000
//        MergeSort.mergeSort(decreasingHundredThousand,2); // Decreasing int 100.000
//        long estimatedTime = System.nanoTime()-startTime;
//        System.out.println(estimatedTime);


        // MERGE SORT - NumberOfPartitions = 3
        //long startTime = System.nanoTime();
//        MergeSort.mergeSort(equalThousand,3); // Equal int 1.000
//        MergeSort.mergeSort(equalTenThousand,3); // Equal int 10.000
//        MergeSort.mergeSort(equalHundredThousand,3); // Equal int 100.000
//        MergeSort.mergeSort(randomThousand,3); // Random int 1.000
//        MergeSort.mergeSort(randomTenThousand,3); // Random int 10.000
//        MergeSort.mergeSort(randomHundredThousand,3); // Random int 100.000
//        MergeSort.mergeSort(increasingThousand,3); // Increasing int 1.000
//        MergeSort.mergeSort(increasingTenThousand,3); // Increasing int 10.000
//        MergeSort.mergeSort(increasingHundredThousand,3); // Increasing int 100.000
//        MergeSort.mergeSort(decreasingThousand,3); // Decreasing int 1.000
//        MergeSort.mergeSort(decreasingTenThousand,3); // Decreasing int 10.000
//        MergeSort.mergeSort(decreasingHundredThousand,3); // Decreasing int 100.000
        //long estimatedTime = System.nanoTime()-startTime;
        //System.out.println(estimatedTime);


        // QUICK SORT - Pivot as a first element
//        long startTime = System.nanoTime();
//        QuickSort.quickSort(equalThousand,"firstelement"); // Equal int 1.000
//        QuickSort.quickSort(equalTenThousand,"firstelement"); // Equal int 10.000
//        QuickSort.quickSort(equalHundredThousand,"firstelement"); // Equal int 100.000
//        QuickSort.quickSort(randomThousand,"firstelement"); // Random int 1.000
//        QuickSort.quickSort(randomTenThousand,"firstelement"); // Random int 10.000
//        QuickSort.quickSort(randomHundredThousand,"firstelement"); // Random int 100.000
//        QuickSort.quickSort(increasingThousand,"firstelement"); // Increasing int 1.000
//        QuickSort.quickSort(increasingTenThousand,"firstelement"); // Increasing int 10.000
//        QuickSort.quickSort(increasingHundredThousand,"firstelement"); // Increasing int 100.000
//        QuickSort.quickSort(decreasingThousand,"firstelement"); // Decreasing int 1.000
//        QuickSort.quickSort(decreasingTenThousand,"firstelement"); // Decreasing int 10.000
//        QuickSort.quickSort(decreasingHundredThousand,"firstelement"); // Decreasing int 100.000
//        long estimatedTime = System.nanoTime()-startTime;
//        System.out.println(estimatedTime);


        // QUICK SORT - Pivot as a random element
//        long startTime = System.nanoTime();
//        QuickSort.quickSort(equalThousand,"randomelement"); // Equal int 1.000
//        QuickSort.quickSort(equalTenThousand,"randomelement"); // Equal int 10.000
//        QuickSort.quickSort(equalHundredThousand,"randomelement"); // Equal int 100.000
//        QuickSort.quickSort(randomThousand,"randomelement"); // Random int 1.000
//        QuickSort.quickSort(randomTenThousand,"randomelement"); // Random int 10.000
//        QuickSort.quickSort(randomHundredThousand,"randomelement"); // Random int 100.000
//        QuickSort.quickSort(increasingThousand,"randomelement"); // Increasing int 1.000
//        QuickSort.quickSort(increasingTenThousand,"randomelement"); // Increasing int 10.000
//        QuickSort.quickSort(increasingHundredThousand,"randomelement"); // Increasing int 100.000
//        QuickSort.quickSort(decreasingThousand,"randomelement"); // Decreasing int 1.000
//        QuickSort.quickSort(decreasingTenThousand,"randomelement"); // Decreasing int 10.000
//        QuickSort.quickSort(decreasingHundredThousand,"randomelement"); // Decreasing int 100.000
//        long estimatedTime = System.nanoTime()-startTime;
//        System.out.println(estimatedTime);


        // QUICK SORT - Pivot as a middle of the first - mid - last element
        long startTime = System.nanoTime();
//        QuickSort.quickSort(equalThousand,"midoffirstmidlastelement"); // Equal int 1.000
//        QuickSort.quickSort(equalTenThousand,"midoffirstmidlastelement"); // Equal int 10.000
//        QuickSort.quickSort(equalHundredThousand,"midoffirstmidlastelement"); // Equal int 100.000
//        QuickSort.quickSort(randomThousand,"midoffirstmidlastelement"); // Random int 1.000
//        QuickSort.quickSort(randomTenThousand,"midoffirstmidlastelement"); // Random int 10.000
//        QuickSort.quickSort(randomHundredThousand,"midoffirstmidlastelement"); // Random int 100.000
//        QuickSort.quickSort(increasingThousand,"midoffirstmidlastelement"); // Increasing int 1.000
//        QuickSort.quickSort(increasingTenThousand,"midoffirstmidlastelement"); // Increasing int 10.000
//        QuickSort.quickSort(increasingHundredThousand,"midoffirstmidlastelement"); // Increasing int 100.000
//        QuickSort.quickSort(decreasingThousand,"midoffirstmidlastelement"); // Decreasing int 1.000
//        QuickSort.quickSort(decreasingTenThousand,"midoffirstmidlastelement"); // Decreasing int 10.000
        QuickSort.quickSort(decreasingHundredThousand,"midoffirstmidlastelement"); // Decreasing int 100.000
        long estimatedTime = System.nanoTime()-startTime;
        System.out.println(estimatedTime);

    }

}
