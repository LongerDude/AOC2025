import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;
public class Day2 {


    public static void main(String[] args) {
        // Test case
        String inputRanges = "11-22,95-115,998-1012,1188511880-1188511890,222220-222224," +
                "1698522-1698528,446443-446449,38593856-38593862,565653-565659," +
                "824824821-824824827,2121212118-2121212124";

        try (Scanner scanner = new Scanner(Paths.get("Input2.txt"))) {
            inputRanges = scanner.nextLine();

        } catch (IOException e) {
            System.out.println(e.getMessage() + "THIS OPERATION FAILED");
        }


        long totalInvalidSum = 0;

        // --- STEP 2: Parse the input and iterate through ranges ---

        String[] ranges = inputRanges.split(",");

        for (String range : ranges) {

            String[] parts = range.split("-");


            long startID = Long.parseLong(parts[0]);
            long endID = Long.parseLong(parts[1]);

            // --- STEP 3: Iterate through every ID in the range ---

            for (long id = startID; id <= endID; id++) {
                if (isInvalid(id)) {
                    totalInvalidSum += id;
                }
            }
        }

        System.out.println("Total sum of invalid IDs: " + totalInvalidSum);
    }

    private static boolean isInvalid(long id) {
        String idString = String.valueOf(id);
        int totalLength = idString.length();

        // 1. Check for leading zeroes (Rule unchanged)
        if (idString.startsWith("0")) {
            return false;
        }

        /*// --- PART ONE LOGIC  ---
        // 1. Check for leading zeroes (if length > 1) and length parity
        if (length % 2 != 0 || (length > 1 && idString.startsWith("0"))) {
            return false;
        }

        // 2. Check for the repeating pattern
        int halfLength = length / 2;

        // Get the first half
        String firstHalf = idString.substring(0, halfLength);

        // Get the second half
        String secondHalf = idString.substring(halfLength);

        // Check if the two halves are identical
        return firstHalf.equals(secondHalf);
        // ------------------------------------------
        */

        // --- PART TWO LOGIC ---

        // Iterate through all possible sequence lengths (Lp).

        for (int Lp = 1; Lp <= totalLength / 2; Lp++) {

            // Check if the total length is evenly divisible by Lp.
            if (totalLength % Lp == 0) {

                // 3. Define the potential sequence P (the first Lp characters)
                String potentialSequenceP = idString.substring(0, Lp);

                // 4. Construct the full expected string by repeating P
                int repetitionCount = totalLength / Lp;
                StringBuilder expectedBuilder = new StringBuilder();

                for (int n = 0; n < repetitionCount; n++) {
                    expectedBuilder.append(potentialSequenceP);
                }

                // 5. Compare the constructed string with the actual ID string
                if (idString.equals(expectedBuilder.toString())) {
                    return true;
                }
            }
        }
        // --- NEW PART TWO LOGIC END ---


        return false;
    }
}