import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Generator {
    private ArrayList<String> nameField;
    private int pathId;
    private int querySize;
    private static final int maxPathLength = 15;
    private static final int maxNode = 10;
    private static final int minNode = -10;
    private int Super = 20;

    public Generator(int querySize) {
        this.nameField = initNameField();
        this.pathId = 0;
        this.querySize = querySize;
    }

    private ArrayList<String> initNameField() {
        ArrayList<String> nameField = new ArrayList<>();
        nameField.add("PATH_ADD");
        nameField.add("PATH_REMOVE");
        nameField.add("PATH_REMOVE_BY_ID");
        nameField.add("PATH_GET_ID");
        nameField.add("PATH_GET_BY_ID");
        nameField.add("PATH_COUNT");
        nameField.add("PATH_SIZE");
        nameField.add("PATH_DISTINCT_NODE_COUNT");
        nameField.add("CONTAINS_PATH");
        nameField.add("CONTAINS_PATH_ID");
        nameField.add("DISTINCT_NODE_COUNT");
        nameField.add("COMPARE_PATHS");
        nameField.add("PATH_CONTAINS_NODE");
        nameField.add("CONTAINS_NODE");
        nameField.add("CONTAINS_EDGE");
        nameField.add("IS_NODE_CONNECTED");
        nameField.add("SHORTEST_PATH_LENGTH");
        return nameField;
    }

    //return a random integer in [min,max] 
    private int numRandom(int min, int max) {
        return (int) (min + Math.random() * (max - min + 1));
    }

    //generate a random query
    private String generateQueue() {
        int choice;
        while (true) {
            choice = numRandom(0, nameField.size() - 1);
            if (choice == 0 || choice == 1 || choice == 2) {
                if (Super-- <= 0) {
                    continue;
                }
            }
            break;
        }
        String result = nameField.get(choice);
        switch (choice) {
            case 0:
                //PATH_ADD
                pathId++;
                result = addRandomPath(result);
                break;
            case 1:
                //PATH_REMOVE
                result = addRandomPath(result);
                break;
            case 2:
                //PATH_REMOVE_BY_ID
                result = addRandomId(result);
                break;
            case 3:
                //PATH_GET_ID
                result = addRandomPath(result);
                break;
            case 4:
                //PATH_GET_BY_ID
                result = addRandomId(result);
                break;
            case 5:
                //PATH_COUNT
                break;
            case 6:
                //PATH_SIZE
                result = addRandomId(result);
                break;
            case 7:
                //PATH_DISTINCT_NODE_COUNT
                result = addRandomId(result);
                break;
            case 8:
                //CONTAINS_PATH
                result = addRandomPath(result);
                break;
            case 9:
                //CONTAINS_PATH_ID
                result = addRandomId(result);
                break;
            case 10:
                //DISTINCT_NODE_COUNT
                break;
            case 11:
                //COMPARE PATHS
                result = addRandomId(result);
                result = addRandomId(result);
                break;
            case 12:
                //PATH_CONTAINS_NODE
                result = addRandomId(result);
                result = addRandomNode(result);
                break;
            case 13:
                //CONTAINS_NODE
                result = addRandomNode(result);
                break;
            case 14:
                //CONTAINS_EDGE
                result = addRandomNode(result);
                result = addRandomNode(result);
                break;
            case 15:
                //IS_NODE_CONNECTED
                result = addRandomNode(result);
                result = addRandomNode(result);
                break;
            case 16:
                //SHORTEST_PATH_LENGTH
                result = addRandomNode(result);
                result = addRandomNode(result);
                break;
            default:
                break;
        }
        return result;
    }

    public void go() throws FileNotFoundException {
        System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream("./out.txt")), true));
        for (int i = 0; i < querySize; i++) {
            System.out.println(generateQueue());
        }
        System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream(FileDescriptor.out)), true));
        System.out.println("Successfully generate " + querySize + " queries");
    }

    private ArrayList<Integer> randomPath(int length) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        Random fucklzh = new Random();
        for (int i = 0; i < length; i++) {
            arrayList.add(fucklzh.nextInt() % 11);
        }
        return arrayList;
    }

    //add a random path to the end of string
    private String addRandomPath(String string) {
        String result = string;
        ArrayList<Integer> numberList = randomPath(numRandom(2, maxPathLength));
        for (int node : numberList) {
            result = result.concat(" " + node);
        }
        return result;
    }

    //add a random pathId to the end of string
    private String addRandomId(String string) {
        return string.concat(" " + numRandom(1, pathId));
    }

    //add a random nodeId to the end of string
    private String addRandomNode(String string) {
        return string.concat(" " + numRandom(minNode, maxNode));
    }
}