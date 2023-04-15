package searchEngine;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        HashedDictionary<String, AList<ListEntry>> hashTable = new HashedDictionary<>(2477); //Creating hashTable
        File file = new File("C:\\Users\\bedoo\\Desktop\\JavaProjects\\SearchEngine\\src\\searchEngine\\stop_words_en.txt");
        Scanner scanner = new Scanner(file);
        AList<String> stopWords = new AList<>();
        while (scanner.hasNext()) {
            stopWords.add(scanner.next().toLowerCase(Locale.ENGLISH));
        }
        scanner.close();
        File fileFolder = new File("C:\\Users\\bedoo\\Desktop\\JavaProjects\\SearchEngine\\src\\searchEngine\\sport");
        File[] fileList = Objects.requireNonNull(fileFolder.listFiles());
        String word;
        int wordCount = 0;
        long firstTime = 0;
        long secondTime = 0;
        long totalTime = 0;
        for (File txt : fileList) {
            Scanner sc = new Scanner(txt);
            String fileName = txt.getName();

            while (sc.hasNext()) { //Reading words from file and placing them to the hashTable
                word = sc.next().replaceAll("\\W+", "").replaceAll("\\d", "").replaceAll("\\s+", "");
                word = word.toLowerCase(Locale.ENGLISH);

                if (!stopWords.contains(word) && word.length() != 0) {

                    if (!hashTable.contains(word)) {
                        AList<ListEntry> list = new AList<>();
                        ListEntry listEntry = new ListEntry(fileName);
                        list.add(listEntry);

                        firstTime = System.currentTimeMillis();
                        hashTable.add(word, list);
                        secondTime = System.currentTimeMillis();
                    } else {
                        AList<ListEntry> list = hashTable.getValue(word);
                        ListEntry listEntry = new ListEntry(fileName);
                        boolean flag = false;
                        for (int i = 1; i <= list.getLength(); i++) {
                            if (list.getEntry(i).getTxtName().equals(fileName)) {
                                firstTime = System.currentTimeMillis();
                                list.getEntry(i).setCount(list.getEntry(i).getCount() + 1);
                                secondTime = System.currentTimeMillis();
                                flag = true;
                            }
                        }
                        if (!flag) {
                            firstTime = System.currentTimeMillis();
                            list.add(listEntry);
                            secondTime = System.currentTimeMillis();
                        }
                    }
                    wordCount++;
                    totalTime += secondTime - firstTime;
                }
            }
            sc.close();
        }

        //////////////////////////////////////////// SEARCH /////////////////////////////////////////////////////////////
        File search = new File("C:\\Users\\bedoo\\Desktop\\JavaProjects\\SearchEngine\\src\\searchEngine\\search.txt");
        Scanner scanSearched = new Scanner(search);
        AList<String> searchWords = new AList<>();
        while (scanSearched.hasNext()) {
            searchWords.add(scanSearched.next().toLowerCase(Locale.ENGLISH));
        }
        hashTable.setCollisionCount(0);
        long totalSearch = 0;
        long firstSearch = 0;
        long secondSearch = 0;
        long min = 999999999;
        long max = 0;
        for (int i = 1; i <= searchWords.getLength(); i++) {
            firstSearch = System.nanoTime();
            hashTable.locate(hashTable.getHashIndex(searchWords.getEntry(i)), searchWords.getEntry(i));
            secondSearch = System.nanoTime();
            totalSearch += (secondSearch - firstSearch);
            if ((secondSearch - firstSearch) > max)
                max = secondSearch - firstSearch;
            if ((secondSearch - firstSearch) < min)
                min = secondSearch - firstSearch;
        }

        ///////////////////////////////////// PERFORMANCE - MONITORING //////////////////////////////////////////////////
//        System.out.println("Collision count : " + hashTable.getCollisionCount());
//        System.out.println("Total words : " + wordCount);
//        System.out.println("Total indexing time : " + totalTime);
//        System.out.println("Avg. search time : " + totalSearch / searchWords.getLength());
//        System.out.println("Min search time : " + min);
//        System.out.println("Max search time : " + max);
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        /////////////////////// QUERY //////////////////////////////
        while (true) {
            System.out.println();
            System.out.println("Please enter three words you want to search with a space between them:");
            Scanner searchWord = new Scanner(System.in);
            String searchString = searchWord.nextLine();
            String[] words = searchString.split(" ");
            if (words.length != 3) {
                System.out.println("Please enter only three words! Try again!");
            } else {
                Query(words, hashTable);
                break;
            }
        }
    }

    static void Query(String[] words, HashedDictionary<String, AList<ListEntry>> hashTable) {

        AList<ListEntry> word1 = hashTable.getValue(words[0]);
        AList<ListEntry> word2 = hashTable.getValue(words[1]);
        AList<ListEntry> word3 = hashTable.getValue(words[2]);
        String searchString = words[0] + " " + words[1] + " " + words[2];

        if ((word1 != null && word2 == null && word3 == null) ||
                (word1 == null && word2 != null && word3 == null) ||
                (word1 == null && word2 == null && word3 != null)) { //Hash table contains only one word

            if (word1 != null) { //Hash table contains only first word
                OneWordQuery(word1, searchString);
            } else if (word2 != null) { //Hash table contains only second word
                OneWordQuery(word2, searchString);
            } else { //Hash table contains only third word
                OneWordQuery(word3, searchString);
            }
        } else if (word1 != null && word2 != null && word3 == null || word1 != null && word2 == null || word1 == null && word2 != null) { //Hash table contains only two words
            if (word3 == null) { //Hash table contains first and second word
                TwoWordQuery(word1, word2, searchString);
            } else if (word2 == null) { //Hash table contains first and third word
                TwoWordQuery(word1, word3, searchString);
            } else // Hash table contains second and third word
                TwoWordQuery(word2, word3, searchString);
        } else if (word1 != null) { //Hash table contains all three words
            ThreeWordQuery(word1, word2, word3, searchString);
        } else {
            System.out.println("There isn't any relevant file for the search \" " + searchString + "\"");
        }
    }

    static void OneWordQuery(AList<ListEntry> word, String searchString) {
        int max = word.getEntry(1).getCount();
        String txtName = word.getEntry(1).getTxtName();
        for (int i = 1; i < word.getLength(); i++) {
            if (word.getEntry(i).getCount() > max) {
                max = word.getEntry(i).getCount();
                txtName = word.getEntry(i).getTxtName();
            }
        }
        System.out.println("Most relevant file for the search \" " + searchString + "\" is : " + txtName);
    }

    static void TwoWordQuery(AList<ListEntry> word1, AList<ListEntry> word2, String searchString) {
        int max1 = word1.getEntry(1).getCount();
        String txtName1 = word1.getEntry(1).getTxtName();
        for (int i = 1; i < word1.getLength(); i++) {
            if (word1.getEntry(i).getCount() > max1) {
                max1 = word1.getEntry(i).getCount();
                txtName1 = word1.getEntry(i).getTxtName();
            }
        }
        int max2 = word2.getEntry(1).getCount();
        String txtName2 = word2.getEntry(1).getTxtName();
        for (int i = 1; i < word2.getLength(); i++) {
            if (word2.getEntry(i).getCount() > max2) {
                max2 = word2.getEntry(i).getCount();
                txtName2 = word2.getEntry(i).getTxtName();
            }
        }
        int newCount = 1;
        int oldCount = 1;
        String txtName = "";
        for (int i = 1; i <= word1.getLength(); i++) {
            for (int j = 1; j <= word2.getLength(); j++) {
                if (word1.getEntry(i).getTxtName().equals(word2.getEntry(j).getTxtName())) {
                    newCount = word1.getEntry(i).getCount() + word2.getEntry(j).getCount();
                    if (newCount > oldCount) {
                        txtName = word1.getEntry(i).getTxtName();
                        oldCount = newCount;
                    }

                }
            }
        }
        if (newCount > 1) {
            System.out.println("Most relevant file for the search \" " + searchString + "\" is : " + txtName);
        } else {
            if (max1 >= max2)
                System.out.println("Most relevant file for the search \" " + searchString + "\" is : " + txtName1);
            else
                System.out.println("Most relevant file for the search \" " + searchString + "\" is : " + txtName2);
        }
    }

    static void ThreeWordQuery(AList<ListEntry> word1, AList<ListEntry> word2, AList<ListEntry> word3, String searchString) {
        int max1 = word1.getEntry(1).getCount();
        for (int i = 1; i < word1.getLength(); i++) {
            if (word1.getEntry(i).getCount() > max1) {
                max1 = word1.getEntry(i).getCount();
            }
        }
        int max2 = word2.getEntry(1).getCount();
        for (int i = 1; i < word2.getLength(); i++) {
            if (word2.getEntry(i).getCount() > max2) {
                max2 = word2.getEntry(i).getCount();
            }
        }
        int max3 = word3.getEntry(1).getCount();
        for (int i = 1; i < word3.getLength(); i++) {
            if (word3.getEntry(i).getCount() > max3) {
                max3 = word3.getEntry(i).getCount();
            }
        }
        int oldCount = 1;
        int newCount = 1;
        String txtName = "";
        for (int i = 1; i <= word1.getLength(); i++) {
            for (int j = 1; j <= word2.getLength(); j++) {
                for (int k = 1; k <= word3.getLength(); k++) {
                    if (word1.getEntry(i).getTxtName().equals(word2.getEntry(j).getTxtName()) && word1.getEntry(i).getTxtName().equals(word3.getEntry(k).getTxtName())) {
                        newCount = word1.getEntry(i).getCount() + word2.getEntry(j).getCount() + word3.getEntry(k).getCount();
                        if (newCount > oldCount) {
                            txtName = word1.getEntry(i).getTxtName();
                            oldCount = newCount;
                        }
                    }
                }
            }
        }
        if (newCount > 1) {
            System.out.println("Most relevant file for the search \" " + searchString + "\" is : " + txtName);
        } else {
            if ((max1 < max2 && max2 < max3) || (max1 < max3 && max3 < max2)) {
                word1 = null;
                TwoWordQuery(word2, word3, searchString);
            } else if ((max2 < max1 && max1 < max3) || (max2 < max3 && max3 < max1)) {
                word2 = null;
                TwoWordQuery(word1, word3, searchString);
            } else {
                word3 = null;
                TwoWordQuery(word1, word2, searchString);
            }
        }
    }
}

