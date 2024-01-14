import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadData {
    public List<Integer> readFromFiles(String[] strings) throws IOException {
        List<Integer> allData = new ArrayList<>();

        for (String filename : strings) {
            try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
                String line;

                while ((line = br.readLine()) != null) {
                    
                    int dailySales = Integer.parseInt(line.trim());
                    allData.add(dailySales);
                }
            }
        }

        return allData;
    }

    public static void main(String[] args) {
        ReadData reader = new ReadData();
        String[] filenames = { "BURGERKING_groundTruth_data.txt", "MCDONALDS_groundTruth_data.txt",
                "KFC_groundTruth_data.txt", "KFC_simulated_data.txt", "BURGERKING_simulated_data.txt",
                "MCDONALDS_simulated_data.txt" };

        try {
            List<Integer> allData = reader.readFromFiles(filenames);
            System.out.println("Simulated data");
            for (int data : allData) {
                System.out.println(data);

            }

            // System.out.println("data:"+allData);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
