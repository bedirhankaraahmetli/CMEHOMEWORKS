package searchEngine;

import searchEngine.ListEntry;

import java.util.Arrays;

public class AList<ListEntry> {
    private ListEntry[] list; // Array of list entries; ignore list[0]
    private int numberOfEntries;
    private static final int DEFAULT_CAPACITY = 25;

    public AList() {
        this(DEFAULT_CAPACITY);
    } // end default constructor

    public AList(int initialCapacity) {
        // Is initialCapacity too small?
        if (initialCapacity < DEFAULT_CAPACITY)
            initialCapacity = DEFAULT_CAPACITY;

        // The cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        ListEntry[] tempList = (ListEntry[]) new Object[initialCapacity + 1];
        list = tempList;
        numberOfEntries = 0;
    } // end constructor

    public void add(ListEntry listEntry) {
        add(numberOfEntries + 1, listEntry);
    } // end add

    // Precondition: The array list has room for another entry.
    public void add(int newPosition, ListEntry listEntry) {
        if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1)) { // checks that it's a valid position
            if (newPosition <= numberOfEntries) // if we're not adding to the end of the list, we need to make room
                makeRoom(newPosition);
            list[newPosition] = listEntry;
            numberOfEntries++;
            ensureCapacity(); // Ensure enough room for next add

        } else
            throw new IndexOutOfBoundsException(
                    "Given position of add's new entry is out of bounds.");
    } // end add

    public ListEntry remove(int givenPosition) {
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) { // make sure it's a valid position
            assert !isEmpty(); // we know this because of the check on the valid position
            ListEntry result = list[givenPosition]; // Get entry to be removed

            // Move subsequent entries towards entry to be removed,
            // unless it is last in list
            if (givenPosition < numberOfEntries)
                removeGap(givenPosition);

            numberOfEntries--;
            return result;
        } else
            throw new IndexOutOfBoundsException(
                    "Illegal position given to remove operation.");
    } // end remove

    public void clear() {

        // Clear entries but retain array; no need to create a new array
        for (int index = 1; index <= numberOfEntries; index++)
            // Loop is part of Q4
            list[index] = null;

        numberOfEntries = 0;
    } // end clear

    public ListEntry replace(int givenPosition, ListEntry listEntry) {
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            assert !isEmpty();
            ListEntry originalEntry = list[givenPosition];
            list[givenPosition] = listEntry;
            return originalEntry;
        } else
            throw new IndexOutOfBoundsException(
                    "Illegal position given to replace operation.");
    } // end replace

    public ListEntry getEntry(int givenPosition) {
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            assert !isEmpty();
            return list[givenPosition];
        } else
            throw new IndexOutOfBoundsException(
                    "Illegal position given to getEntry operation.");
    } // end getEntry

    public ListEntry[] toArray() {

        // The cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        ListEntry[] result = (ListEntry[]) new Object[numberOfEntries]; // Unchecked cast
        for (int index = 0; index < numberOfEntries; index++) {
            result[index] = list[index + 1];
        } // end for

        return result;
    } // end toArray

    public boolean contains(ListEntry anEntry) {
        boolean found = false;
        int index = 1;
        while (!found && (index <= numberOfEntries)) {
            if (anEntry.equals(list[index]))
                found = true;
            index++;
        } // end while
        return found;
    } // end contains

    public int getLength() {
        return numberOfEntries;
    } // end getLength

    public boolean isEmpty() {
        return numberOfEntries == 0;
    } // end isEmpty

    // Doubles the capacity of the array list if it is full
    private void ensureCapacity() {
        int capacity = list.length - 1;
        if (numberOfEntries >= capacity) {
            int newCapacity = 2 * capacity;
            list = Arrays.copyOf(list, newCapacity + 1);
        } // end if
    } // end ensureCapacity

    private void removeGap(int givenPosition) {
        assert (givenPosition >= 1) && (givenPosition < numberOfEntries);

        int removedIndex = givenPosition;
        int lastIndex = numberOfEntries;

        for (int index = removedIndex; index < lastIndex; index++)
            list[index] = list[index + 1];
    } // end removeGap

    private void makeRoom(int newPosition) {
        assert (newPosition >= 1) && (newPosition <= numberOfEntries + 1);

        int newIndex = newPosition;
        int lastIndex = numberOfEntries;

        // Move each entry to next higher index, starting at end of
        // list and continuing until the entry at newIndex is moved
        for (int index = lastIndex; index >= newIndex; index--)
            list[index + 1] = list[index];
    } // end makeRoom

    public void display() {
        for (int i = 1; i <= getLength(); i++) {
            System.out.println(getEntry(i));
        }
    }
}	