package activity;

public class activity2 {
    public static void main(String[] args) {

        // Create an array with 6 numbers
        int[] numbers = {10, 77, 10, 54, -11, 10};

        // Variable to store the sum of all 10's
        int sum = 0;

        for (int i = 0; i < numbers.length; i++) {

            // Check if the current number is 10
            if (numbers[i] == 10) {
                // Add 10 to the sum
                sum = sum + 10;
            }
        }

        // Check if the sum is exactly 30
        boolean isThirty = (sum == 30);

        // Print the results
        System.out.println("Total of all 10's: " + sum);
        System.out.println("Is the total exactly 30? " + isThirty);
    }
}
