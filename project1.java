//Levon Khachatryan
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class project1 {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please provide the input file name as a command line argument.");
            return;
        }

        String filename = args[0];
        int n = 0; // Initialize the number of men and women
        int[][] manList = new int[0][];
        int[][] womanList = new int[0][];

        try {
            File inputFile = new File(filename);
            Scanner scanner = new Scanner(inputFile);
            if (scanner.hasNextInt()) {
                n = scanner.nextInt(); // Read the number of men and women
                
            }

            manList = new int[n][n];
            womanList = new int[n][n];

            // Read men's preferences
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (scanner.hasNextInt()) {
                        manList[i][j] = scanner.nextInt();
                    }
                }
            }

            // Read women's preferences
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (scanner.hasNextInt()) {
                        womanList[i][j] = scanner.nextInt();
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
            return;
        }
    // Run the stable matching algorithms
    Map<Integer, Integer> menResult = menPropose(manList, womanList);
    Map<Integer, Integer> womenResult = womenPropose(manList, womanList);

    // Print the results
    System.out.println("Output when men propose:");
    printResult(menResult);

    System.out.println("\nOutput when women propose:");
    printResult(womenResult);
    }

    public static Map<Integer, Integer> menPropose(int[][] manList, int[][] womanList) {
        int n = manList.length;
        //engagements will be the result 
        Map<Integer, Integer> engagements = new HashMap<>();
        boolean[] menEngaged = new boolean[n];
        boolean[] womenEngaged = new boolean[n];
    
        while (engagements.size() < n) {
            for (int i = 0; i < n; i++) { // Iterate through all men
                if (!menEngaged[i]) { // If the man is not engaged
                    for (int j = 0; j < manList[i].length; j++) {
                        int woman = manList[i][j]; // The man's current top choice
                        if (!womenEngaged[woman - 1]) { // If the woman is not engaged
                            engagements.put(i + 1, woman); // Engage the man and the woman
                            menEngaged[i] = true; // Mark the man as engaged
                            womenEngaged[woman - 1] = true; // Mark the woman as engaged
                            break; // Move on to the next man
                        } else {
                            int currentMan = getKeyByValue(engagements, woman); // The woman's current partner
                            if (isWomanPreferred(i, currentMan - 1, womanList, woman)) {
                                // If the woman prefers the new proposer over her current partner
                                engagements.put(i + 1, woman); // Engage her with the new man
                                menEngaged[i] = true; // Mark the new man as engaged
                                menEngaged[currentMan - 1] = false; // Mark the old partner as not engaged
                                break; // Move on to the next man
                            } // If the woman prefers her current partner, do nothing
                        }
                    }
                }
            }
        }
    
        return engagements;
    }
    

    public static Map<Integer, Integer> womenPropose(int[][] manList, int[][] womanList) {
        int n = womanList.length;
        Map<Integer, Integer> engagements = new HashMap<>();
        boolean[] menEngaged = new boolean[n];
        boolean[] womenEngaged = new boolean[n];

        while (engagements.size() < n) {
            for (int woman = 0; woman < n; woman++) {
                if (!womenEngaged[woman]) {
                    for (int j = 0; j < womanList[woman].length; j++) {
                        int man = womanList[woman][j];
                        if (!menEngaged[man - 1]) {
                            engagements.put(man, woman + 1);
                            womenEngaged[woman] = true;
                            menEngaged[man - 1] = true;
                            break; // Break since engagement is successful
                        } else {
                            int currentWoman = engagements.get(man);
                            if (isManPreferred(woman, currentWoman - 1, manList, man)) {
                                engagements.put(man, woman + 1);
                                womenEngaged[woman] = true;
                                womenEngaged[currentWoman - 1] = false;
                                break; // Break since engagement is successful
                            }
                        }
                    }
                }
            }
        }

        return engagements;
    }


   private static boolean isWomanPreferred(int man, int currentMan, int[][] womanList, int woman) {
    // Iterate through the woman's preference list
    for (int i = 0; i < womanList[woman - 1].length; i++) {
        // Check if the new man is found in her preference list before the current man
        if (womanList[woman - 1][i] == man + 1) {
            // If the new man is preferred over the current, return true
            return true;
        }
        // Check if the current man is found in her preference list before the new man
        if (womanList[woman - 1][i] == currentMan + 1) {
            // If the current man is preferred over the new, return false
            return false;
        }
    }
    return false;
}

private static boolean isManPreferred(int woman, int currentWoman, int[][] manList, int man) {
    // Iterate through the man's preference list
    for (int i = 0; i < manList[man - 1].length; i++) {
         // Check if the new woman is found in her preference list before the current woman
        if (manList[man - 1][i] == woman + 1) {
            return true;
        }
        // Check if the current woman is found in her preference list before the new woman
        if (manList[man - 1][i] == currentWoman + 1) {
            return false;
        }
    }
    return false;
}

    private static int getKeyByValue(Map<Integer, Integer> map, int value) {
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == value) {
                return entry.getKey();
            }
        }
        return -1; // Not found, this should not happen if the map is valid
    }

    private static void printResult(Map<Integer, Integer> result) {
        System.out.print("{");
        int count = 0;
        for (Map.Entry<Integer, Integer> entry : result.entrySet()) {
            System.out.print("(" + entry.getKey() + "," + entry.getValue() + ")");
            count++;
            if (count < result.size()) {
                System.out.print(", ");
            }
        }
        System.out.println("}");
    }

}
