package utils;

import services.DataExtractor;
import services.FileGenerator;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MenuOptions {

    private final Input input = new Input();
    private final DataExtractor dataExtractor = new DataExtractor();
    private final FileGenerator fileGenerator = new FileGenerator();
    List<String> conversionHistory = new ArrayList<>();

    public void convert() {
        String msgBaseCountry = """
                Digite o código do pais de origem da moeda:
                - Exemplo: "USD", "BRL"
                Certifique-se dos códigos disponíveis:
                - Na raíz do projeto "generated-files"
                """;

        String msgDestinyCountry = """
                Digite o código do pais que deseja converter:
                - Exemplo: "USD", "BRL"
                Certifique-se dos códigos disponíveis:
                - Na raíz do projeto "generated-files"
                """;

        String countryBaseCode = input.getCountryCode(msgBaseCountry);
        if(countryBaseCode == null) return;

        String countryDestinyCode = input.getCountryCode(msgDestinyCountry);
        if(countryDestinyCode == null) return;

        double valueToConvert = input.getDoubleValue();
        if(valueToConvert == -1) return;


        double conversionRate = dataExtractor.getConversionRate(countryBaseCode, countryDestinyCode);
        double convertedValue = valueToConvert * conversionRate;
        String response = getResponse(countryBaseCode, countryDestinyCode, valueToConvert, convertedValue);
        JOptionPane.showMessageDialog(null, response, "Conversão", JOptionPane.INFORMATION_MESSAGE);
    }

    private String getResponse(String countryBaseCode, String countryDestinyCode, double valueToConvert, double convertedValue) {
        String resultMessage = String.format(
                """
                Valor = %.2f | %s
                Resulta em =  %.2f | %s
                """,
                valueToConvert, countryBaseCode,
                convertedValue, countryDestinyCode
        );

        conversionHistory.add(resultMessage);
        fileGenerator.generateLog(resultMessage);
        return resultMessage;
    }

    public void showHistory(){
        if(this.conversionHistory.isEmpty()){
            JOptionPane.showMessageDialog(null, "Aqui ficaram salvas suas últimas conversões realizadas",
                    "Sem Histórico", JOptionPane.ERROR_MESSAGE);
        }else{
            StringBuilder sb = new StringBuilder();
            for(String conversao : this.conversionHistory){
                sb.append(conversao).append("-------------------------------------------").append("\n");
            }
            JOptionPane.showMessageDialog(null, sb,
                    "Histórico de Conversões Recentes", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void updateCurrencyCodes(){
        fileGenerator.generateCurrenciesCodesFromClient();;
    }
}
