package application;

import java.util.Scanner;

import javax.swing.SwingUtilities;

import gui.BattleGUI;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Battle battle = new Battle();

        BattleConsole battleConsole = new BattleConsole(battle, scanner);

        int input;

        do {
            input = battleConsole.getIntFromUserInput(Message.executionModeMessage, 3);

            switch (input) {
                case 1:
                    
                    battleConsole.run();
                    break;
            
                case 2:
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            new BattleGUI(battle);
                        }
                });
                    break;

                default:
            }
            
        } while (input != 3);

        scanner.close();
    }
}
