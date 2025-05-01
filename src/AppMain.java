import services.FileGenerator;
import utils.EnvLoader;
import utils.MenuOptions;

import javax.swing.*;

public class AppMain {

    public static void main(String[] args) {

        EnvLoader.loadEnv("env");
        MenuOptions menuOptions = new MenuOptions();
        FileGenerator fileGenerator = new FileGenerator();

        while(true){
            String[] options = {"Realizar Conversão", "Verificar Histórico", "Atualizar", "Sair"};
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
                    fileGenerator.generateCurrenciesCodesFromClient();
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }

}
