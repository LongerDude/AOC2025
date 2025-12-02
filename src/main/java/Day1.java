import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Day1 {

    public static void main (String[] args) {

        // --- MAIN EXECUTION BLOCK ---

        try (Scanner filescanner = new Scanner(Paths.get("input.txt"))) {
            // Starting position
            int position = 50;
            // Accumulator for the total number of times the 'zero point' is crossed (the final answer).
            int zeroCount = 0;
            // Temporary list to hold the updated position [0] and cycle count [1] returned by shift methods.
            ArrayList<Integer> positionAndZeroCount;

            // Process the input file line by line
            while (filescanner.hasNext()) {
                String row = filescanner.nextLine();

                // The first character determines the direction of movement ('L' or 'R').
                char typeOfShift = row.charAt(0);
                // The rest of the line contains the distance/amount of the shift.
                String digits = row.substring(1);

                // Determine direction and call the appropriate shift function
                if (typeOfShift == 'L') {
                    // Call left shift, converting the distance to an integer.
                    positionAndZeroCount = leftShift(Integer.parseInt(digits), position);
                    // Update the current position with the returned value.
                    position = positionAndZeroCount.get(0);
                    // Accumulate the zero crossings from this move.
                    zeroCount += positionAndZeroCount.get(1);

                } else { // Assuming 'R' or any other character means right shift
                    // Call right shift.
                    positionAndZeroCount = rightShift(Integer.parseInt(digits), position);
                    // Update the current position.
                    position = positionAndZeroCount.get(0);
                    // Accumulate the zero crossings.
                    zeroCount += positionAndZeroCount.get(1);
                }
            }
            // Print the final accumulated count of zero crossings.
            System.out.println(zeroCount);


        } catch (IOException e) {
            throw new RuntimeException("Could not read input file: " + e.getMessage(), e);
        }

    }

    // --- LEFT SHIFT LOGIC (Counter-Clockwise) ---
    public static ArrayList<Integer> leftShift(int shift, int currentPosition) {
        int zeroCount = 0;

        // Use modulus to isolate the part of the shift that wraps the circle
        // and the part that determines the final position.
        int positionalShift = shift % 100;
        int fullCycles = shift / 100;

        // Create the return container.
        ArrayList<Integer> results = new ArrayList<>();

        // Add cycles completed BEFORE considering the starting position.
        zeroCount += fullCycles;

        // Calculate the new position using modular subtraction.
        // The standard formula for left/negative circular shift is: (pos - shift) % size
        // To handle negative results, we use the (pos - shift) % size + size % size trick.
        int newPosition = (currentPosition - positionalShift) % 100;

        // Handle the wrapping: If the result is negative, it means we crossed the zero point.
        if (newPosition < 0) {
            newPosition += 100; // Wrap around to the positive side (0-99).
            zeroCount++;        // Count the cycle crossing (0-99 boundary).
        }


        results.add(newPosition);
        results.add(zeroCount);
        return results;
    }

    // --- RIGHT SHIFT LOGIC (Clockwise) ---
    public static ArrayList<Integer> rightShift(int shift, int currentPosition) {


        // Calculate the raw un-wrapped final position.
        int rawPosition = currentPosition + shift;

        // Calculate the number of times the movement passed the size 100 boundary.
        // Since the initial range is 0-99, the result must be at least 0.
        int zeroCount = rawPosition / 100;

        // Calculate the final position on the track (0-99) using modulus.
        int finalPosition = rawPosition % 100;

        // Create the return container.
        ArrayList<Integer> results = new ArrayList<>();

        results.add(finalPosition);
        results.add(zeroCount);
        return results;
    }
}