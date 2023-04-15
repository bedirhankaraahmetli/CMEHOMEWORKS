package searchEngine;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class HashedDictionary<K, V> {

    private Entry<K, V>[] hashTable;
    private int numberOfEntries;
    private static final int DEFAULT_SIZE = 101;
    private static final double MAX_LOAD_FACTOR = 0.5;
    private int collisionCount;

    public HashedDictionary() {
        this(DEFAULT_SIZE);
    }

    @SuppressWarnings("unchecked")
    HashedDictionary(int initialCapacity) {
        int primeSize = getNextPrime(initialCapacity);
        hashTable = new Entry[primeSize];
        numberOfEntries = 0;
        collisionCount = 0;
    }

    public void setCollisionCount(int collisionCount) {
        this.collisionCount = collisionCount;
    }

    public V add(K key, V value) {
        V oldValue;
        if (isHashTableTooFull()) {
            reHash();
        }
        //////// Find the index of the entry that will be added ///////
        int index = getHashIndex(key);
        assert (index >= 0 && index < hashTable.length);
        //////////////////////////////////////////////////////////////

        if (hashTable[index] == null) {
            hashTable[index] = new Entry<K, V>(key, value);
            numberOfEntries++;
            oldValue = null;
        } else {
            oldValue = hashTable[index].getValue();
            hashTable[index].setValue(value);
        }
        return oldValue;
    }

    public int getHashIndex(K key) {
//        int index = linearProbing(simpleSummation(key), key);
//		  int index = linearProbing(polynomialAccumulation(key),key);
//        int index = doubleHashing(simpleSummation(key), key);
        int index = doubleHashing(polynomialAccumulation(key), key);
        return index;
    }

    public V getValue(K key) {
        V result = null;
        //int index = simpleSummation(key);
        int index = polynomialAccumulation(key);
        index = locate(index, key);
        if (index != -1)
            result = hashTable[index].getValue();
        return result;
    }

    public int locate(int index, K key) {
        boolean found = false;
        while (!found && (hashTable[index] != null)) {
            //////// Find the index of the searched key in the probe sequence ///////
            if (hashTable[index].getKey().equals(key)) {
                found = true;
            } else {
                //index = linearProbing(index, key);
                index = doubleHashing(index, key);
            }
            /////////////////////////////////////////////////////////////////////////
        }
        int result = -1;
        if (found)
            result = index;
        return result;
    }

    public boolean contains(K key) {
        //int index = simpleSummation(key);
        int index = polynomialAccumulation(key);
        index = locate(index, key);
        if (index != -1)
            return true;
        return false;
    }

    public int getSize() {
        return hashTable.length;
    }

    private int simpleSummation(K key) {
        int summation = 0;
        for (int i = 0; i < key.toString().length(); i++) {
            summation += (int) key.toString().charAt(i);
        }
        int hashIndex = summation % hashTable.length;
        if (hashIndex < 0) {
            hashIndex = hashIndex + hashTable.length;
        }
        return hashIndex;
    }

    private int polynomialAccumulation(K key) {
        long accumulation = 1;
        for (int i = key.toString().length() - 1; i >= 0; i--) {
            accumulation += ((int) key.toString().charAt(i) - 96) * Math.pow(31, key.toString().length() - 1 - i);
        }
        int hashIndex = (int) (accumulation % hashTable.length);
        if (hashIndex < 0) {
            hashIndex = hashIndex + hashTable.length;
        }
        return hashIndex;
    }

    public boolean isHashTableTooFull() {
        double loadFactor = (double) numberOfEntries / hashTable.length;
        if (loadFactor >= MAX_LOAD_FACTOR) {
            return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    public void reHash() {
        Entry<K, V>[] oldTable = hashTable;
        int oldSize = hashTable.length;
        int newSize = getNextPrime(2 * oldSize);
        hashTable = new Entry[newSize];
        numberOfEntries = 0;

        for (int index = 0; index < oldSize; index++) {
            if (oldTable[index] != null) {
                add(oldTable[index].getKey(), oldTable[index].getValue());
            }
        }
    }

    private int linearProbing(int index, K key) {
        boolean found = false;
        while (!found && hashTable[index] != null) {
            if (key.equals(hashTable[index].getKey())) {
                found = true;
            } else {
                index = (index + 1) % hashTable.length;
                collisionCount++;
            }
        }
        return index;
    }

    private int doubleHashing(int index, K key) {
        int firstHashFunction = index % hashTable.length;
        int secondaryHashFunction = 31 - index % 31;
        int doubleHashIndex = 0;
        for (int i = 0; i < hashTable.length - 1; i++) {
            doubleHashIndex = (firstHashFunction + i * secondaryHashFunction) % hashTable.length;
            if (hashTable[doubleHashIndex] == null || key.equals(hashTable[doubleHashIndex].getKey())) {
                break;
            } else
                collisionCount++;

        }
        return doubleHashIndex;
    }

    //Follows the probe sequence that begins at index (key’s hash index) and returns either the index
    //of the entry containing key or -1, if no such entry exists.

    public int getCollisionCount() {
        return collisionCount;
    }

    public boolean isPrime(int num) {
        boolean prime = true;
        for (int i = 2; i <= num / 2; i++) {
            if ((num % i) == 0) {
                prime = false;
                break;
            }
        }
        return prime;
    }

    public int getNextPrime(int num) {
        if (num <= 1)
            return 2;
        else if (isPrime(num))
            return num;
        boolean found = false;
        while (!found) {
            num++;
            if (isPrime(num))
                found = true;
        }
        return num;
    }

    public boolean isEmpty() {
        return numberOfEntries == 0;
    }
}
