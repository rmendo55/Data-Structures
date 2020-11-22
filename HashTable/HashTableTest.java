package HashTable;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

/**
 * Author: Rafael Mendoza
 * Description: The main method executes by populating a simple list and hash table by reading a text file. The output writes to a file
 * by writing the time is takes for the simple list and hash table to execute. Also writes the number of entries in the simple list and hash table
 * and writes all the entries from the simple list and hash table.
 */
public class HashTableTest {
    public static void main(String[] args) {

        testSimpleListAndHashTable();
    }

    public static void testSimpleListAndHashTable() {
        GenerateSimpleListAndHashTable listAndTable = new GenerateSimpleListAndHashTable();
        //Test HashTable
        //generate HashTable populates the hash Table and gets timed
        HashTable hTable = listAndTable.generateHashTable();
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < hTable.getSize(); i++) {
            if (hTable.getEntry(i) != null) {
                list.add(hTable.getEntry(i).getWord());
            }
        }
        Collections.sort(list);
        listAndTable.generateHashTableFile(hTable, list);

        //Test SimpleList
        //generate Simple List populate the Simple List and gets timed
//        SimpleList sList = listAndTable.generateSimpleList();
//        ArrayList<String> list2 = new ArrayList<String>();
//        for (int i = 0; i < sList.size(); i++) {
//            list2.add(sList.getEntry(i).getWord());
//        }
//        Collections.sort(list2);
//        listAndTable.generateSimpleListFile(sList, list2);

    }
}


