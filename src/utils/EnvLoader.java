package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class EnvLoader {

    public static void loadEnv(String filePath){

        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){

            String line;

            while((line = br.readLine()) != null){
                line = line.trim();
                if(line.isEmpty() || line.startsWith("#")) continue;

                String[] parts = line.split("=", 2);
                String key = parts[0];
                String value = parts[1];

                System.setProperty(key, value);
            }

        }catch (IOException e){
            System.err.println("Erro ao carregar .env: " + e.getMessage());
        }

    }
}
