package com.tokioschool.controller;

import com.tokioschool.model.Board;
import com.tokioschool.model.BoardManager;
import com.tokioschool.view.BattleConsole;

import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        int input;
        Scanner scanner = new Scanner(System.in);
        Board board = new Board();
        BoardManager boardManager = new BoardManager(board, scanner);
        com.tokioschool.view.BattleConsole battleConsole = new BattleConsole(boardManager, scanner);
        String message = """
                Por favor escolha o modo de execução:
                1. Modo consola
                2. Modo interface Gráfico de Utilizador
                3. Sair
                Prima o número da opção que quer escolher:\s""";
        do
        {
            input = battleConsole.getInt(message, 3);
            if (input == 1)
                battleConsole.run();

        } while (input != 3);
        scanner.close();
    }
}