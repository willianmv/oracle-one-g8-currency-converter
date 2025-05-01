package services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FileGenerator {

    private final DataExtractor dataExtractor = new DataExtractor();

    public void generateCurrenciesCodesFromClient(){
        File file = new File("generated-files/currencies-code.txt");
        try(FileWriter fw = new FileWriter(file)){
            fw.write(String.format("%-10s %-40s%n", "Código", "Moeda"));
            fw.write("------------------------------------------\n");

            Map<String, String> supportedCodes = dataExtractor.getSupportedCodesFromClient();
            List<Map.Entry<String, String>> entryList = new ArrayList<>(supportedCodes.entrySet());
            entryList.sort(Map.Entry.comparingByValue());

            for (Map.Entry<String, String> entry : entryList) {
                String currencyCode = entry.getKey();
                String currencyName = entry.getValue();
                fw.write(String.format("%-10s %-40s%n", currencyCode, currencyName));
            }

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void generateLog(String dataToSave){
        File file = new File("generated-files/conversion-logs.txt");
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        try(FileWriter fw = new FileWriter(file, true)){
            fw.write("Log de Conversão - "+ timestamp +"\n");
            fw.write(dataToSave);
            fw.write("------------------------------------------\n");

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
