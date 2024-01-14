import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class DataGenerator {
    public void writeToFiles(String filenames, int min, int max, int[] dataParms) throws IOException {
        int range = max - min, custNo = 0, product = 0, dailysales = 0;
        PrintWriter fileGen = new PrintWriter(filenames);
        for (int i = 0; i < 730; i++) {
            
            dailysales = 0;
            custNo = (int) (Math.random() * range) + min;

            for (int j = 0; j < custNo; j++) {

                product = (int) (Math.random() * (dataParms.length));

                dailysales += dataParms[product];
                // System.out.println(dataParms[product]);
                // System.out.println(product + ":products");

            }

            fileGen.println(dailysales);
        }
        fileGen.close();

    }


    public void writeToFilesGroundTruth(String filenames, int min, int max, int[] dataParms) throws IOException {
        int range = max - min, custNo = 0, product = 0, dailysalesGroundTruth = 0;
        PrintWriter fileGen = new PrintWriter(filenames);
        for (int i = 0; i < 365; i++) {
            // System.out.println("daytruth:" + i);
            dailysalesGroundTruth = 0;
            custNo = (int) (Math.random() * range) + min;

            for (int j = 0; j < custNo; j++) {

                product = (int) (Math.random() * (dataParms.length));

                dailysalesGroundTruth += dataParms[product];
               
                // System.out.println(product + ":products");

            }

            fileGen.println(dailysalesGroundTruth);
        }
        fileGen.close();

    }

    public void simulatedData() {
        ReadData reader = new ReadData();

       
        String[] simulatedDataFiles = { "KFC_sales_data.txt", "MCDONALDS_sales_data.txt", "BURGERKING_sales_data.txt" };
        String[] shortNames = { "KFC", "McDonald's", "Burger King" };

        
        System.out.println("+------------+--------+--------+--------+-----------+------------------------+");
        System.out.printf("| Restaurant |  Min   |  Max   |  Mean  | Variance  | Standard Deviation  |\n");
        System.out.println("+------------+--------+--------+--------+-----------+------------------------+");

        for (int i = 0; i < simulatedDataFiles.length; i++) {
            try {
                List<Integer> allData = reader.readFromFiles(new String[] { simulatedDataFiles[i] });
                StatisticalAnalysis analysis = new StatisticalAnalysis(allData);

                
                System.out.printf("| %-11s| %6d | %6d | %6.2f | %9.2f | %19.2f |\n",
                        shortNames[i],
                        analysis.getMin(),
                        analysis.getMax(),
                        analysis.getMean(),
                        analysis.getVariance(),
                        analysis.getStandardDeviation());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

       
        System.out.println("+------------+--------+--------+--------+-----------+------------------------+");

    }

}

