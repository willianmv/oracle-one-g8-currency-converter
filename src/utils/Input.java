package utils;

import services.DataExtractor;

import javax.swing.*;
import java.util.Map;

public class Input {

    private double valor = 0.0;
    private String code = "";
    private final DataExtractor dataExtractor = new DataExtractor();

    public String getCountryCode(String msg){
        boolean validInput = false;
        while(!validInput){
                code = JOptionPane.showInputDialog(null, msg, "Código da moeda",JOptionPane.INFORMATION_MESSAGE);

                if(code == null) return null;

                Map<String, String> supportedCodes = dataExtractor.getSupportedCodesFromFile();
                if(supportedCodes.containsKey(code)){
                    validInput = true;
                } else{
                    JOptionPane.showMessageDialog(null,
                            "Valor inválido! Por favor, insira um código disponível.",
                            "Erro de entrada", JOptionPane.ERROR_MESSAGE);
                }
        }
        return code;
    }

    public double getDoubleValue(){
        boolean validInput = false;
        while(!validInput){
            try{
                String input = JOptionPane.showInputDialog(null,
                        """
                                Digite um valor para conversão:
                                - Exemplo: 1500.54
                                - Certifique-se de não usar caracteres inválidos
                                """,
                        "Valor para conversão",
                        JOptionPane.INFORMATION_MESSAGE);

                if(input == null) return -1;

                valor = Double.parseDouble(input);
                validInput = true;

            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null,
                        "Valor inválido! Por favor, insira um número decimal válido.",
                        "Erro de entrada", JOptionPane.ERROR_MESSAGE);
            }
        }
        return valor;
    }

}
