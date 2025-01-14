package aff;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileDataReader {
    private String filePath;
    private Object[] data;

    public FileDataReader(String filePath) {
        this.filePath = filePath;
        this.data = readDataFromFile();
    }

    public Object[] getData() {
        return data;
    }

    private Object[] readDataFromFile() {
        List<Object> dataList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                dataList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataList.toArray();
    }    
}