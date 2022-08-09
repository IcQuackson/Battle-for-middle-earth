package application;

public abstract class Message {

    static final String executionModeMessage = "Por favor escolha o modo de execução:\n"
                                            + "1. Modo consola\n"
                                            + "2. Modo interface Gráfico de Utilizador\n"
                                            + "3. Sair\n"
                                            + "Prima o número da opção que quer escolher: ";

    static final String initialBattleMessage = "Por favor escolha uma das seguintes opções:\n"
                                            + "1. Gerir exército dos heróis\n"
                                            + "2. Gerir exército das bestas\n" 
                                            + "3. Lutar!\n"
                                            + "4. Voltar\n"
                                            + "Prima o número da opção que quer escolher: ";

    static final String manageHerosMessage = "Por favor escolha uma das seguintes opções:\n"
                                            + "1. Mostrar exército\n"
                                            + "2. Adicionar herói\n" 
                                            + "3. Remover herói\n"
                                            + "4. Subir posição do herói\n"
                                            + "5. Descer posição do herói\n"
                                            + "6. Voltar\n"
                                            + "Prima o número da opção que quer escolher: ";

    static final String manageBeastsMessage = "Por favor escolha uma das seguintes opções:\n"
                                            + "1. Mostrar exército\n"
                                            + "2. Adicionar besta\n" 
                                            + "3. Remover besta\n"
                                            + "4. Subir posição da besta\n"
                                            + "5. Descer posição da besta\n"
                                            + "6. Voltar\n"
                                            + "Prima o número da opção que quer escolher: ";
    
}
