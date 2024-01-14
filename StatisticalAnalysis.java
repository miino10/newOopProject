import java.io.IOException;
import java.util.List;

public class StatisticalAnalysis {
    private List<Integer> data;

    public StatisticalAnalysis(List<Integer> data) {
        this.data = data;
    }

    public int getMin() {
        if (data.isEmpty()) {
            throw new IllegalArgumentException("Data is empty.");
        }

        int min = data.get(0);
        for (int value : data) {
            if (value < min) {
                min = value;
            }
        }
        return min;
    }

    public int getMax() {
        if (data.isEmpty()) {
            throw new IllegalArgumentException("Data is empty.");
        }

        int max = data.get(0);
        for (int value : data) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    public double getMean() {
        if (data.isEmpty()) {
            throw new IllegalArgumentException("Data is empty.");
        }

        double sum = 0;
        for (int value : data) {
            sum += value;
        }
        return sum / data.size();
    }

    public double getVariance() {
        if (data.isEmpty()) {
            throw new IllegalArgumentException("Data is empty.");
        }

        double mean = getMean();
        double sumSquaredDifferences = 0;

        for (int value : data) {
            double difference = value - mean;
            sumSquaredDifferences += difference * difference;
        }

        return sumSquaredDifferences / data.size();
    }

    public double getStandardDeviation() {
        return Math.sqrt(getVariance());
    }

    public static void main(String[] args) {
        ReadData reader = new ReadData();

        // Assuming you have filenames for your data files
        String[] simulatedDataFiles = { "KFC_simulated_data.txt", "MCDONALDS_simulated_data.txt",
                "BURGERKING_simulated_data.txt" };
        String[] groundTruthDataFiles = { "KFC_groundTruth_data.txt", "MCDONALDS_groundTruth_data.txt",
                "BURGERKING_groundTruth_data.txt" };
        String[] shortNames = { "KFC", "McDonald's", "Burger King" };

       
        System.out.println("simulated data");
        System.out.println("+------------+--------+--------+--------+-----------+------------------------+");
        System.out.printf("| Restaurant |  Min   |  Max   |  Mean  | Variance  | Standard Deviation  |\n");
        System.out.println("+------------+--------+--------+--------+-----------+------------------------+");

        for (int i = 0; i < simulatedDataFiles.length; i++) {
            try {
                List<Integer> allSimulatedData = reader.readFromFiles(new String[] { simulatedDataFiles[i] });
                StatisticalAnalysis simulatedData = new StatisticalAnalysis(allSimulatedData);

                
                System.out.printf("| %-11s| %6d | %6d | %6.2f | %9.2f | %19.2f |\n",
                        shortNames[i],
                        simulatedData.getMin(),
                        simulatedData.getMax(),
                        simulatedData.getMean(),
                        simulatedData.getVariance(),
                        simulatedData.getStandardDeviation());
                // System.out.println(allData);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        
        System.out.println("+------------+--------+--------+--------+-----------+------------------------+");
        System.out.println();
        System.out.println("ground truth data");
        System.out.println("+------------+--------+--------+--------+-----------+------------------------+");
        System.out.printf("| Restaurant |  Min   |  Max   |  Mean  | Variance  | Standard Deviation  |\n");
        System.out.println("+------------+--------+--------+--------+-----------+------------------------+");

        for (int i = 0; i < groundTruthDataFiles.length; i++) {
            try {
                List<Integer> groudTruthallData = reader.readFromFiles(new String[] { groundTruthDataFiles[i] });
                StatisticalAnalysis groundTruthData = new StatisticalAnalysis(groudTruthallData);

               
                System.out.printf("| %-11s| %6d | %6d | %6.2f | %9.2f | %19.2f |\n",
                        shortNames[i],
                        groundTruthData.getMin(),
                        groundTruthData.getMax(),
                        groundTruthData.getMean(),
                        groundTruthData.getVariance(),
                        groundTruthData.getStandardDeviation());
                ;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("+------------+--------+--------+--------+-----------+------------------------+");

    }
}
