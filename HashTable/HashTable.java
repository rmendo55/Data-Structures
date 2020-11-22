package HashTable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HashTable {
    //data fields
    private Entry[] e;
    private int size;
    private int itemsInArray;
    private double loadFactor;
    //Initial size of the internal array.
    private static final int INITIAL_CAPACITY = 17;

    //empty Constructor
    public HashTable() {
        this.e = new Entry[INITIAL_CAPACITY];
        this.size = INITIAL_CAPACITY;
        this.itemsInArray = 0;
        this.loadFactor = 0.0;
    }

    //getters
    public Entry getEntry(int index) {
        return this.e[index];
    }

    public Entry[] getEntryArray() {
        return this.e;
    }

    public int getSize() {
        return this.size;
    }

    public int getItemsInArray() {
        return this.itemsInArray;
    }

    public void hashFunction(File file) {
        try {
            Scanner input = new Scanner(file);     //reads the file

            while (input.hasNextLine()) {
                this.loadFactor = (this.itemsInArray / (double)this.size);
                if (this.loadFactor > 0.5) {
                    //rehash
                    //less collisions will occur
                    rehash();
                }
                String s = input.next();
                int hash = hash(s.toLowerCase());
                if (this.e[hash] == null) {
                    this.e[hash] = new Entry(s.toLowerCase());
                    this.itemsInArray++;
                }
            }
        }
        catch(FileNotFoundException ex) {
            System.out.println("File could not upload.");
        }
    }

    public int hash(String word) {
        int hash = word.hashCode() % this.size;
        if (hash < 0) {
            hash = hash * (-1);
        }

        if (this.e[hash] != null) {
            if (!(this.e[hash].getWord().equals(word))) {
                    //implement a collision resolution technique
                    //quadratic probing
                    int j = 1;
                    //int index = (hash + (int)(Math.pow(j, 2))) % this.size;
                int index = 7 - (hash % 7);
                    while (this.e[index] != null && !(this.e[index].getWord().equals(word))) {
                        j++;
                        index = (hash + (int)(Math.pow(j, 2))) % this.size;
                    }
                    hash = index;
                if (hash < 0) {
                    hash = hash * (-1);
                }
            }
            else {
                this.e[hash].incrementCount();
            }
        }
        return hash;
    }

   //method that rehashes the hast table. Resized the array and gives a hashcode value to each index since the hash value is based on the size of the hash table.
    public void rehash() {
        Entry[] oldArray = this.e;
        this.size = (this.size * 2) + 1;
        this.e = new Entry[this.size];
        for (int i = 0; i < oldArray.length; i++) {
            if (oldArray[i] != null) {
                int hash = hash(oldArray[i].getWord().toLowerCase());
                    if (this.e[hash] == null) {
                        this.e[hash] = oldArray[i];
                    }
            }
        }
    }
}
