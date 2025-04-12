import services.DataExtractor;
import services.FileGenerator;
import utils.MenuOptions;

import javax.swing.*;

public class AppMain {

    public static void main(String[] args) {

//        Usar quando precisar atualizar a lista de codes disponíveis
//        FileGenerator fileGenerator = new FileGenerator();
//        fileGenerator.generateCurrenciesCodesFromClient();

        MenuOptions menuOptions = new MenuOptions();

        while(true){
            String[] options = {"Realizar Conversão", "Verificar Histórico", "Sair"};
            int choice = JOptionPane.showOptionDialog(
                    null,
                    "Escolha uma opção:",
                    "Menu Currency Converter",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            switch (choice) {
                case 0:
                    menuOptions.convert();
                    break;
                case 1:
                    menuOptions.showHistory();
                    break;
                case 2:
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }

}
