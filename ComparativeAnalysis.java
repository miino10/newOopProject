import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

public class ComparativeAnalysis {
    private List<Integer> actualData;
    private List<Integer> predictedData;

    public ComparativeAnalysis(List<Integer> actualData, List<Integer> predictedData) {
        this.actualData = actualData;
        this.predictedData = predictedData;
    }

    public double calculateRMSD() {
        int minSize = Math.min(actualData.size(), predictedData.size());

        if (minSize == 0) {
            throw new IllegalArgumentException("Data sizes are zero.");
        }

        double sumSquaredAbsoluteDifferences = 0;

        for (int i = 0; i < minSize; i++) {
            int absoluteDifference = Math.abs(actualData.get(i) - predictedData.get(i));
            sumSquaredAbsoluteDifferences += absoluteDifference * absoluteDifference;
        }

        double meanSquaredAbsoluteDifference = sumSquaredAbsoluteDifferences / minSize;

        return Math.sqrt(meanSquaredAbsoluteDifference);
    }

    public double calculateMAE() {
        int minSize = Math.min(actualData.size(), predictedData.size());

        if (minSize == 0) {
            throw new IllegalArgumentException("Data sizes are zero.");
        }

        double sumAbsoluteDifferences = 0;

        for (int i = 0; i < minSize; i++) {
            int difference = Math.abs(actualData.get(i) - predictedData.get(i));
            sumAbsoluteDifferences += difference;
        }

        return sumAbsoluteDifferences / minSize;
    }

    public double calculateMSE() {
        int minSize = Math.min(actualData.size(), predictedData.size());
        // System.out.println(actualData.size()+"the sizeeeee");
        // System.out.println(predictedData.size()+"the sizeeeee");

        if (minSize == 0) {
            throw new IllegalArgumentException("Data sizes are zero.");
        }

        double sumSquaredDifferences = 0;

        for (int i = 0; i < minSize; i++) {
            int difference = actualData.get(i) - predictedData.get(i);
            sumSquaredDifferences += difference * difference;

        }

        double meanSquaredDifference = sumSquaredDifferences / minSize;
        return meanSquaredDifference;

    }

    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        ReadData reader = new ReadData();
        String[] groundTruthDataFiles = { "KFC_groundTruth_data.txt", "MCDONALDS_groundTruth_data.txt",
                "BURGERKING_groundTruth_data.txt" };
        String[] simulatedDataFiles = { "KFC_simulated_data.txt", "MCDONALDS_simulated_data.txt",
                "BURGERKING_simulated_data.txt" };
        String[] shortNames = { "KFC", "McDonald's", "Burger King" };
        System.out.println("+--------------+---------------+---------------+---------------+");
        System.out.printf("%-15s %-15s %-15s %-15s%n", "Restaurent", "RMSD", "MAE", "MSE");
        System.out.println("+--------------+---------------+---------------+---------------+");

        try {
            for (int i = 0; i < groundTruthDataFiles.length; i++) {
                String groundTruthFile = groundTruthDataFiles[i];
                String simulatedDataFile = simulatedDataFiles[i];

                List<Integer> actualData = reader.readFromFiles(new String[] { groundTruthFile });
                List<Integer> predictedData = reader.readFromFiles(new String[] { simulatedDataFile });

                ComparativeAnalysis analysis = new ComparativeAnalysis(actualData, predictedData);

                System.out.printf("%-15s %-15s %-15s %-15s%n", shortNames[i],
                        df.format(analysis.calculateRMSD()),
                        df.format(analysis.calculateMAE()),
                        df.format(analysis.calculateMSE()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        
    }     

}
