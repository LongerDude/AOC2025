import java.nio.file.Paths;
import java.util.*;

public class Day3 {

    // Function to get the largest number by selecting exactly n digits in order
    public static String largestNumber(String bank, int n) {
        int len = bank.length();
        char[] result = new char[n];
        int start = 0;

        for (int i = 0; i < n; i++) {
            int end = len - (n - i); // Last possible index to pick for this digit
            char maxDigit = '0';
            int maxIndex = start;

            for (int j = start; j <= end; j++) {
                if (bank.charAt(j) > maxDigit) {
                    maxDigit = bank.charAt(j);
                    maxIndex = j;
                }
            }

            result[i] = maxDigit;
            start = maxIndex + 1; // Move start past chosen digit
        }

        return new String(result);
    }

    public static void main(String[] args) {
        // Example input

        ArrayList<String> banks = new ArrayList();
        try ( Scanner fileScanner = new Scanner(Paths.get("input Day 3.txt"))){
            while(fileScanner.hasNext()) {
                banks.add(fileScanner.nextLine());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        int partOneDigits = 2;
        int partTwoDigits = 12;

        long sumPartOne = 0;
        long sumPartTwo = 0;

        for (String bank : banks) {
            sumPartOne += Long.parseLong(largestNumber(bank, partOneDigits));
            sumPartTwo += Long.parseLong(largestNumber(bank, partTwoDigits));
        }

        System.out.println("Part One Total Joltage: " + sumPartOne);
        System.out.println("Part Two Total Joltage: " + sumPartTwo);
    }
}
