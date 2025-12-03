import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import java.util.ArrayList;
import java.util.List;

public class Day3 {

    public static int solveBank(String bank) {

        int[] digits = new int[bank.length()];
        int maxVal = -1;

        for (int i = 0; i < bank.length(); i++) {
            digits[i] = Character.getNumericValue(bank.charAt(i));
            if (digits[i] > maxVal) {
                maxVal = digits[i];
            }
        }

        List<Integer> maxIndices = new ArrayList<>();
        for (int i = 0; i < digits.length; i++) {
            if (digits[i] == maxVal) {
                maxIndices.add(i);
            }
        }

        int globalMaxJoltage = 0;

        for (int anchorIndex : maxIndices) {

            int leftMax = -1;

            for (int i = 0; i < anchorIndex; i++) {
                if (digits[i] > leftMax) {
                    leftMax = digits[i];
                }
            }

            if (leftMax != -1) {
                int joltage = (leftMax * 10) + maxVal;
                if (joltage > globalMaxJoltage) {
                    globalMaxJoltage = joltage;
                }
            }


            int rightMax = -1;

            for (int i = anchorIndex + 1; i < digits.length; i++) {
                if (digits[i] > rightMax) {
                    rightMax = digits[i];
                }
            }

            if (rightMax != -1) {
                int joltage = (maxVal * 10) + rightMax;
                if (joltage > globalMaxJoltage) {
                    globalMaxJoltage = joltage;
                }
            }
        }

        return globalMaxJoltage;
    }


    public static void main(String[] args) {

        ArrayList<String> inputs = new ArrayList<>();
        try (Scanner fileScanner = new Scanner(Paths.get("input Day 3.txt"))){
            while(fileScanner.hasNext()){
                inputs.add(fileScanner.nextLine());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        int totalOutput = 0;
        System.out.println("--- Results ---");
        for (String input : inputs) {
            int max = solveBank(input);
            System.out.println("Bank: " + input.substring(0, 10) + "... | Max Joltage: " + max);
            totalOutput += max;
        }
        System.out.println("--------------------");
        System.out.println("Total Output Joltage: " + totalOutput);
    }
}