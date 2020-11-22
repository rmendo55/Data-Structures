package HashTable;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Scanner;

public class GenerateSimpleListAndHashTable {
    private long simpleListTime;
    private long hashTableTime;

    public GenerateSimpleListAndHashTable() {

    }

    public long getSimpleListTime() {
        return this.simpleListTime;
    }

    public long getHashTableTime() {
        return this.hashTableTime;
    }

    public  SimpleList generateSimpleList() {
        JFileChooser jFile = new JFileChooser();
        SimpleList list = new SimpleList();

        if(jFile.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            try {
                File file = jFile.getSelectedFile();   //gets the file selected from the JFileChooser Dialog
                Scanner input = new Scanner(file);     //reads the file

                Instant start = Instant.now();
                while (input.hasNextLine()) {
                    String s = input.nextLine();
                    String[] arr = s.split(" ");
                    for (int i = 0; i < arr.length; i++) {
                        if (!(arr[i].equals(""))) {
                            if (list.find(arr[i].toLowerCase()) >= 0) {
                                int index = list.find(arr[i].toLowerCase());
                                list.getEntry(index).incrementCount();
                            } else {
                                list.add(new Entry(arr[i].toLowerCase()));
                            }
                        }
                    }
                }
                Instant end = Instant.now();
                this.simpleListTime = Duration.between(start,end).toSeconds();
            }
            catch(FileNotFoundException ex) {
                System.out.println("File could not upload.");
            }
        }
        return list;
    }

    public  HashTable generateHashTable() {
        JFileChooser jFile = new JFileChooser();
        HashTable table = null;
        if(jFile.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = jFile.getSelectedFile();   //gets the file selected from the JFileChooser Dialog
            table = new HashTable();
            Instant start = Instant.now();
            table.hashFunction(file);
            Instant end = Instant.now();
            this.hashTableTime = Duration.between(start,end).toSeconds();
        }
        return table;
    }

    public  void generateSimpleListFile(SimpleList list, ArrayList<String> list2) {
        SimpleList sList = list;
        try {
            PrintWriter pw = new PrintWriter("SimpleList.txt");
            pw.write("Time to compute SimpleList: " + this.simpleListTime + " Seconds" + "\n");
            pw.write("Number of Entries: " + list.size() + "\n");
            for (int i = 0; i < list2.size(); i++) {
                for(int j = 0; j < list.size(); j++) {
                    if (list2.get(i).equalsIgnoreCase(list.getEntry(j).getWord())) {
                        pw.write(list.getEntry(j).toString());
                        pw.write("\n");
                    }
                }
            }
            pw.flush();
        }
        catch(FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public  void generateHashTableFile(HashTable table, ArrayList<String> list) {
        HashTable hTable = table;
        try {
            PrintWriter pw = new PrintWriter("HashTable.txt");
            pw.write("Time to compute HashTable: " + this.hashTableTime + " Seconds" + "\n");
            pw.write("Number of Entries: " + table.getItemsInArray() + "\n");
            for (int i = 0; i < list.size(); i++) {
                int hash = table.hash(list.get(i));
                pw.write(table.getEntry(hash).toString());
                pw.write("\n");
            }
            pw.flush();
        }
        catch(FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }

    }

}
