package activity;

public class activity4 {
    public static void main(String[] args) {

        int[] numbers = {5, 2, 9, 1, 5, 6};

        // Insertion Sort
        for (int i = 1; i < 2; i++) {

            int key = numbers[i];
            int j = i - 1;

            // Move left to find correct position using FOR + IF/ELSE
            for (; j >= 0; j--) {

                if (numbers[j] > key) {
                    // Shift element to the right
                    numbers[j + 1] = numbers[j];
                } 
                else {
                    // Correct position found → stop early
                    break;
                }
            }
                        
            // Insert the key into correct position
            numbers[j+1] = key;
        }

        // Print sorted array
        System.out.println("Sorted array:");
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + " ");
        }
    }
}
