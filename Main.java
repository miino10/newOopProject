import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        DataGenerator dataGenerator = new DataGenerator();

        try {
            
            dataGenerator.writeToFiles("KFC_simulated_data.txt", 300, 400, new int[]{31, 35, 46});
            dataGenerator.writeToFiles("MCDONALDS_simulated_data.txt", 200, 300, new int[]{24, 15, 12});
            dataGenerator.writeToFiles("BURGERKING_simulated_data.txt", 100, 200, new int[]{20, 9, 27});
            dataGenerator.writeToFilesGroundTruth("KFC_groundTruth_data.txt", 100, 200, new int[]{31, 35, 46});
            dataGenerator.writeToFilesGroundTruth("MCDONALDS_groundTruth_data.txt", 200, 250, new int[]{24, 15, 12});
            dataGenerator.writeToFilesGroundTruth("BURGERKING_groundTruth_data.txt", 50, 100, new int[]{20, 9, 27});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


