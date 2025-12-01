import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Day1 {
    public static void main (String[] args) {


        try (Scanner filescanner = new Scanner(Paths.get("input.txt"))) {
            int position = 50;
            int zeroCount = 0;
            ArrayList<Integer> positionAndZeroCount;

            while (filescanner.hasNext()) {
                String row = filescanner.nextLine();
                char typeOfShift = row.charAt(0);
                String digits = row.substring(1);
                if (typeOfShift == 'L') {
                    positionAndZeroCount = leftShift(Integer.parseInt(digits),position);
                    position = positionAndZeroCount.get(0);
                    zeroCount += positionAndZeroCount.get(1);

                } else {
                    positionAndZeroCount = rightShift(Integer.parseInt(digits),position);
                    position = positionAndZeroCount.get(0);
                    zeroCount += positionAndZeroCount.get(1);
                }

            }
            System.out.println(zeroCount);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public static ArrayList<Integer> leftShift(int shift, int currentPosition) {
        int position = 0;
        int zeroCount = 0;
        ArrayList<Integer> results = new ArrayList<>();


        if (currentPosition == 0) {
            zeroCount += shift / 100;
            position = (100 - (shift % 100)) % 100;
            results.add(position);
            results.add(zeroCount);
            return results;
        }
        if (shift / 100 > 0) {
            zeroCount += shift / 100;
        }
        position = currentPosition - (shift % 100);
        if (position == 0) {
            zeroCount++;
        }
        if (position < 0) {
            position += 100;
            zeroCount++;
        }



        results.add(position);
        results.add(zeroCount);
        return results;

    }
    public static ArrayList<Integer> rightShift(int shift, int currentPosition) {
        int position = 0;
        int zeroCount = 0;
        if (currentPosition + shift >= 100) {
            position = currentPosition + shift - 100;
            zeroCount++;
            while (position >= 100) {
                position -= 100;
                zeroCount++;
            }
        } else {
            position = currentPosition + shift;
        }
        ArrayList<Integer> results = new ArrayList<>();
        results.add(position);
        results.add(zeroCount);
        return results;
    }
}
