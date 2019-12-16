import java.util.Random;

public class ArrayDataStructure {
    public static void main(String[] args) {
        //instantiating an integer array of size 10
        int[] array = new int[10];
        testAllMethods(array);

    }
    public static void testAllMethods(int[] array) {
        //populate Array
        populateArray(array);
        System.out.print("Current Array: ");
        printArray(array);
        System.out.println();
        randomAccess(array, 9);
        searchElement(array, 5);
        System.out.print("Removing element at index 2: ");
        removeElement(array, 2);
        printArray(array);
        System.out.println();
        System.out.print("Inserting 10 at index 2: ");
        insert(array, 2, 10);
        printArray(array);
        System.out.println();
        //Shallow Copy
        int[] array2 = array;

        //deep copy
        int[] array3 = new int[10];
        deepCopy(array, array3);
    }

    public static void populateArray(int[] arr) {
        //populate array with random number between 1 and 50
        Random randomGenerator = new Random();
        int randomInt = 0;
        for (int i = 0; i < arr.length; i++) {
            randomInt = randomGenerator.nextInt(50) + 1;
            arr[i] = randomInt;
        }
    }
    public static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            if (i != arr.length - 1) {
                System.out.print(arr[i] + ",");
            }
            else {
                System.out.print(arr[i]+"]");
            }
        }
    }
    public static void randomAccess(int[] arr, int index) {
        if (index < arr.length) {
            System.out.println(arr[index] + " at Index: " + index + " ");
        }
        else {
            System.out.println("Index: " + index + " is out of bounds");
        }
    }
    public static void searchElement(int[] arr, int element) {
        if (arr[0] == element || arr[arr.length - 1] == element) {
            System.out.println("This is the best case which is 0(1) access");
            System.out.println(element + " is at index: " + 0);

        }
        else {
            //you must travers through array which leads to 0(n)
            boolean elementExists = false;
            for (int i = 1; i < arr.length - 1; i++) {
                if (arr[i] == element) {
                    System.out.println("This is the worse case which is 0(n) access");
                    System.out.println(element + " is at index: " + i);
                    elementExists = true;
                    break;
                }
            }
            if (!elementExists) {
                System.out.println(element + " does not exists in the array");
            }
        }
    }

    public static void removeElement(int[] arr, int index) {
        if (index < arr.length) {
            for (int i = 0; i < arr.length; i++) {
                if (i >= index) {
                    if (i + 1 < arr.length) {
                        arr[i] = arr[i + 1];
                    }
                    else if (i + 1 == arr.length) {
                        arr[i] = 0;
                    }
                }
            }
        }
        else {
            System.out.println("Index is out of bounds!!!");
        }
    }

    public static void insert(int[] arr, int index, int element) {
        //if element in array is equal to 0 then that means that space is open
        if (index < arr.length) {
            if (arr[index] == 0 || index == arr.length - 1) {
                arr[index] = element;
            }
            else {
                int current = 0;
                int current2 = 0;
                for (int i = index; i < arr.length; i++) {
                    if (i == index) {
                        current = arr[i];
                        arr[i] = element;
                    }
                    else {
                        current2 = arr[i];
                        arr[i] = current;
                        current = current2;
                    }
                }
            }
        }
    }

    public static void deepCopy(int[] array, int[] array2) {
        //array2 is duplicating array
        for (int i = 0; i < array.length; i++) {
            if (i < array2.length) {
                array2[i] = array[i];
            }
        }
    }
}
