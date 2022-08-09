package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import javax.swing.*;

import application.Battle;
import main_classes.CharacterType;
import main_classes.GameCharacter;
import main_classes.GameCharacterFactory;

public class BattleGUI
{

    private JFrame frame;

    private JPanel heroesPanel;

    private JPanel beastsPanel;

    private JPanel battlePanel;
    
    private Battle battle;
    
    private JList<String> heroArmyJList;

    private JList<String> beastArmyJList;

    private JPanel heroArmyManagementPanel;
    
    private JPanel beastArmyManagementPanel;

    private JTextArea battleTextArea;

    public BattleGUI(Battle battle)
    { 

        this.battle = battle;

        this.heroArmyJList = new JList<>();
        this.heroArmyJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.heroArmyJList.setFixedCellHeight(30);

        this.beastArmyJList = new JList<>();
        this.beastArmyJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.beastArmyJList.setFixedCellHeight(30);

        // Create window
        frame = createFrame();

        // Create heroes panel
        List<CharacterType> heroTypes = new ArrayList<CharacterType>(Arrays.asList(CharacterType.values()));
        List<CharacterType> beastTypes = new ArrayList<CharacterType>(heroTypes);

        heroTypes.removeIf(characterType -> characterType.getTeam().equals(GameCharacter.TEAM_BEASTS));  // Get heroes by removing beasts
        beastTypes.removeIf(characterType -> characterType.getTeam().equals(GameCharacter.TEAM_HEROES)); // Get beasts by removing heroes

        // Create heroes panel
        heroesPanel = makeArmyCreationPanel(GameCharacter.TEAM_HEROES, heroTypes);

        // Create beasts panel
        beastsPanel = makeArmyCreationPanel(GameCharacter.TEAM_BEASTS, beastTypes);

        // Create battle panel
        battlePanel = createBattlePanel();

        
        frame.add(heroesPanel);
        frame.add(beastsPanel);
        frame.add(battlePanel);

        frame.setVisible(true);

        /*
        for (int i = 1; i <= 100; i++) {
            System.out.println("Printing to gui console: " + i);
        }
        */
    }

    private class GuiOutputStream extends OutputStream
    {

        public GuiOutputStream(JTextArea textArea)
        {
            battleTextArea = textArea;
            battleTextArea.setLineWrap(true);
            Font font = new Font("Monospaced Bold", Font.PLAIN, 15);
            battleTextArea.setFont(font);
        }

        @Override
        public void write(int data) throws IOException
        {
            battleTextArea.append(new String(new byte[] { (byte) data }));
        }
    }

    private JFrame createFrame()
    {   
        // Create new container 
        frame = new JFrame("Battle for Middle Earth");

        // Set layout
        frame.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));

        // Exit out of application on close
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Prevent frame from being resized 
        frame.setResizable(false);   

        // Set frame size
        frame.setSize(800, 800);
        
        // Create image icon
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\pedro\\OneDrive\\Desktop\\Programming\\Tokio School\\Java\\ProjetoFinal\\gui\\gollum.jpg");

        // Change icon of frame
        frame.setIconImage(imageIcon.getImage());

        return frame;

    }

    private JPanel makeArmyCreationPanel(String team, List<CharacterType> characterTypes)
    {

        String[] typesStrings = new String[characterTypes.size()];

        // Get type strings
        for (int i = 0; i < characterTypes.size(); i++)
        {
            typesStrings[i] = characterTypes.get(i).toString();
        }

        JPanel mainPanel = new JPanel();
        JPanel behindMainPanel = new JPanel();
        JPanel labelFieldPanel = new JPanel();


        // Set up main panel
        //mainPanel.setBounds(x,y, 250, 250);
        mainPanel.setPreferredSize(new Dimension(250, 248));
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        // Set up behindMainPanel
        behindMainPanel.setPreferredSize(new Dimension(250, 270));  // It's taller than the main panel
        behindMainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));

        // Set up panel that holds labels, text fields and comboBox
        labelFieldPanel.setPreferredSize(new Dimension((int) (mainPanel.getPreferredSize().getWidth() - 2),
                                                       (int) (mainPanel.getPreferredSize().getHeight() * 0.80)));
        labelFieldPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 15));

        // Add the panel to main panel
        mainPanel.add(labelFieldPanel);
        // Add "add" button to main panel
        ResizeToTextButton addJButton = new ResizeToTextButton("Adicionar");
        mainPanel.add(addJButton);

        // Create labels, text fields and comboBox and define their sizes
        JLabel teamLabel = new JLabel(team);
        JLabel nameLabel = new JLabel("Nome:");
        JLabel typeLabel = new JLabel("Tipo:");
        JLabel healthLabel = new JLabel(("Vida:"));
        JLabel armorLabel = new JLabel("Armadura:");

        JTextField nameField = new JTextField();
        JComboBox<String> typeComboBox = new JComboBox<>(typesStrings);
        JTextField healthField = new JTextField();
        JTextField armorField = new JTextField();

        Dimension labelDimension = new Dimension(60, 28);
        Dimension fieldDimension = new Dimension(145, 28);
    
        teamLabel.setPreferredSize(new Dimension((int) (behindMainPanel.getPreferredSize().getWidth() - 2), 10));
        teamLabel.setFont(new Font("Tahoma", Font.BOLD, 11));

        nameLabel.setPreferredSize(labelDimension);
        typeLabel.setPreferredSize(labelDimension);
        healthLabel.setPreferredSize(labelDimension);
        armorLabel.setPreferredSize(labelDimension);

        nameField.setPreferredSize(fieldDimension);
        typeComboBox.setPreferredSize(fieldDimension);
        healthField.setPreferredSize(fieldDimension);
        armorField.setPreferredSize(fieldDimension);


        // Add labels, text fields and comboBox to panel that holds them
        labelFieldPanel.add(nameLabel);
        labelFieldPanel.add(nameField);

        labelFieldPanel.add(typeLabel);
        labelFieldPanel.add(typeComboBox);

        labelFieldPanel.add(healthLabel);
        labelFieldPanel.add(healthField);

        labelFieldPanel.add(armorLabel);
        labelFieldPanel.add(armorField);

        // Adds team label and main panel so the team label will appear on top of the other components
        behindMainPanel.add(teamLabel);
        behindMainPanel.add(mainPanel);

        // Set actions
        addJButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent)
            {
                String name;
                CharacterType type;
                int health;
                int armor;

                // Validate Input
                try
                {
                    name = nameField.getText();
                    // Get CharacterType from string
                    type =  CharacterType.get(((String) typeComboBox.getSelectedItem()));
                    health = Integer.parseInt(healthField.getText());
                    armor = Integer.parseInt(armorField.getText());
                    if (health <= 0 || armor <= 0 || name.isEmpty())
                        throw new NumberFormatException();
                } catch (NumberFormatException e)
                {
                    System.out.println("Invalid arguments");
                    return;
                }

                // Create new game character and set its properties
                GameCharacter gameCharacter = GameCharacterFactory.getGameCharacter(type);
                gameCharacter.setName(name);
                gameCharacter.setHealthPoints(health);
                gameCharacter.setArmor(armor);
                
                if (team.equals(GameCharacter.TEAM_HEROES))
                {
                    battle.addHero(gameCharacter);
                    // Update changes in JList
                    updateStringJList(heroArmyJList, battle.getHeroArmy());
                    //System.out.println("Added " + characterInfo);
                }
                else if (team.equals(GameCharacter.TEAM_BEASTS))
                {
                    battle.addBeast(gameCharacter);
                    // Update changes in JList
                    updateStringJList(beastArmyJList,battle.getBeastArmy());
                    //System.out.println("Added " + characterInfo);
                }
                else
                    System.out.println("No such team exists!");
            }
        });
        return behindMainPanel;
    }

    private void updateStringJList(JList<String> jList, ArrayList<GameCharacter> army)
    {
        final DefaultListModel<String> soldiersModel = new DefaultListModel<>();

        for (GameCharacter character : army)
            soldiersModel.addElement(getCharacterInfo(character));
        jList.setModel(soldiersModel);
    }

    private JPanel createBattlePanel()
    {
        JPanel mainPanel = new JPanel();
        JPanel behindMainPanel = new JPanel();
        JTextArea battleLogsJTextArea = new JTextArea();

        // Set up main panel
        mainPanel.setPreferredSize(new Dimension((int) (frame.getWidth() * 0.92), (int) (frame.getHeight() * 0.57)));
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 35, 15));

        // Set up behindMainPanel
        // It's taller than the main panel
        behindMainPanel.setPreferredSize(new Dimension((int) (frame.getWidth() * 0.92), (int) (frame.getHeight() * 0.62)));
        behindMainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));

        JLabel battleLabel = new JLabel("Batalha");

        battleLabel.setPreferredSize(new Dimension((int) (behindMainPanel.getPreferredSize().getWidth() - 2), 10));
        battleLabel.setFont(new Font("Tahoma", Font.BOLD, 11));

        // Create army management panels
        this.heroArmyManagementPanel = createArmyManagementPanel(GameCharacter.TEAM_HEROES, this.heroArmyJList);
        this.beastArmyManagementPanel = createArmyManagementPanel(GameCharacter.TEAM_BEASTS, this.beastArmyJList);

        // Create text area that displays battle logs
        GuiOutputStream output = new GuiOutputStream(battleLogsJTextArea); // Stream for output to gui
            
        System.setOut(new PrintStream(output, true));   // Set new stream for System.out

        JScrollPane battleJScrollPane = new JScrollPane(battleLogsJTextArea);
        int battleJScrollPaneWidth = (int) (mainPanel.getPreferredSize().getWidth() * 0.90);
        int battleJScrollPaneHeight = (int) (mainPanel.getPreferredSize().getHeight() * 0.40);
        battleJScrollPane.setPreferredSize(new Dimension(battleJScrollPaneWidth, battleJScrollPaneHeight));

        // Adds components to main panel
        mainPanel.add(heroArmyManagementPanel);
        mainPanel.add(beastArmyManagementPanel);

        JButton fightJButton = new JButton("LUTAR!");
        fightJButton.setPreferredSize(new Dimension(100, 20));
        mainPanel.add(fightJButton);

        mainPanel.add(battleJScrollPane);

        // Adds team label and main panel so the team label will appear on top of the other components
        behindMainPanel.add(battleLabel);
        behindMainPanel.add(mainPanel);

        setFightButtonActions(fightJButton);

        return behindMainPanel;

    }

    private void setFightButtonActions(JButton fightJButton)
    {
        fightJButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                battleTextArea.setText("");
                battle.startBattle();
            }
        });
    }

    private String getCharacterInfo(GameCharacter character)
    {
        return String.format("%s - %s (%d, %d)", character.getName(),
                                                        character.getType().toString(),
                                                        character.getHealthPoints(),
                                                        character.getArmor());
    }

    private JPanel createArmyManagementPanel(String team, JList<String> soldierJList)
    {
        //JPanel mainPanel = new JPanel();
        JPanel behindMainPanel = new JPanel();

        // Set up main panel
        /*
        mainPanel.setPreferredSize(new Dimension(290, 140));
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        */

        // Set up behindMainPanel
        // It's taller than the main panel
        behindMainPanel.setPreferredSize(new Dimension(290, 185));
        behindMainPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 2));
        //behindMainPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Set up list with added soldiers
        /*final DefaultListModel<String> soldiers = new DefaultListModel<>();

        for (int i = 1; i < 10; i++) {
            soldiers.addElement("soldado " + i);
        }*/
        /*
        JList<String> soldierJList = new JList<>();
        soldierJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //soldierJList.setPreferredSize(new Dimension(280, 140));
        soldierJList.setFixedCellHeight(30);
        */

        // Add the list to a scroll pane
        JScrollPane soldierJScrollPane = new JScrollPane(soldierJList);
        soldierJScrollPane.setPreferredSize(new Dimension(280, 140));


        // Set up team label
        JLabel teamLabel = new JLabel(team);
        teamLabel.setPreferredSize(new Dimension(50, 10));
        teamLabel.setFont(new Font("Tahoma", Font.BOLD, 11));

        
        //behindMainPanel.add(mainPanel);

        JButton upButton = new JButton("Subir");
        JButton downButton = new JButton("Descer");
        JButton removeButton = new JButton("Remover");

        Dimension buttonDimension = new Dimension(90, 25);

        upButton.setPreferredSize(buttonDimension);
        downButton.setPreferredSize(buttonDimension);
        removeButton.setPreferredSize(buttonDimension);

        // Add team label and main panel so the team label will appear on top of the other components
        behindMainPanel.add(teamLabel);

        // Add soldier list
        //behindMainPanel.add(soldierJList);
        behindMainPanel.add(soldierJScrollPane);

        // Makes selection still visible after pressing button
        //upButton.setFocusable(false);
        //downButton.setFocusable(false);

        // Add buttons
        behindMainPanel.add(upButton);
        behindMainPanel.add(downButton);
        behindMainPanel.add(removeButton);

        setUpButtonActions(upButton, team);
        setDownButtonActions(downButton, team);
        setRemoveButtonActions(removeButton, team);

        return behindMainPanel;
    }

    // TODO Implement multiple selected values
    private void setRemoveButtonActions(JButton removeButton, String team)
    {
        removeButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent) 
            {
                if (team.equals(GameCharacter.TEAM_HEROES))
                {
                    battle.deleteHeroByIndex(heroArmyJList.getSelectedIndex());
                    updateStringJList(heroArmyJList, battle.getHeroArmy());
                }
                else if (team.equals(GameCharacter.TEAM_BEASTS))
                {
                    battle.deleteBeastByIndex(beastArmyJList.getSelectedIndex());
                    updateStringJList(beastArmyJList, battle.getBeastArmy());
                }
            }
        });
    }

    // TODO Implement multiple selected values
    private void setUpButtonActions(JButton upButton, String team)
    {
        upButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                int index = -1;
                boolean hasAscended = false;
                JList<String> armyJList = null;
                // Ascend character and update changes in JList
                if (team.equals(GameCharacter.TEAM_HEROES))
                {   
                    armyJList = heroArmyJList;
                    index = heroArmyJList.getSelectedIndex();
                    hasAscended = battle.ascendHeroInArmy(index);

                    // Update JList
                    updateStringJList(heroArmyJList, battle.getHeroArmy());

                    System.out.println("Hero ascended");
                }
                else if (team.equals(GameCharacter.TEAM_BEASTS))
                {
                    armyJList = beastArmyJList;
                    index = beastArmyJList.getSelectedIndex();
                    hasAscended =  battle.ascendBeastInArmy(index);

                    // Update JList
                    updateStringJList(beastArmyJList, battle.getBeastArmy());

                    System.out.println("Beast ascended");
                }
                else 
                {
                    System.out.println("No such team exists!");
                }
                if (armyJList != null)
                {   
                    // Check if character was ascended and set him as selected
                    setSelectedIndexIfSoldierAscended(hasAscended, armyJList, index);
                }
            }
        });
    }

    // TODO Implement multiple selected values
    private void setDownButtonActions(JButton downButton, String team)
    {
        downButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                int index = -1;
                boolean hasDescended = false;
                JList<String> armyJList = null;
                // Descend character and update changes in JList
                if (team.equals(GameCharacter.TEAM_HEROES))
                {   
                    armyJList = heroArmyJList;
                    index = heroArmyJList.getSelectedIndex();
                    hasDescended = battle.descendHeroInArmy(index);
                    updateStringJList(heroArmyJList, battle.getHeroArmy());

                    System.out.println("Hero descended");
                }
                else if (team.equals(GameCharacter.TEAM_BEASTS))
                {
                    armyJList = beastArmyJList;
                    index = beastArmyJList.getSelectedIndex();
                    hasDescended = battle.descendBeastInArmy(index);
                    updateStringJList(beastArmyJList, battle.getBeastArmy());

                    System.out.println("Beast descended");
                }
                else
                {
                    System.out.println("No such team exists!");
                }
                if (armyJList != null)
                {   
                    // Check if character was ascended and set him as selected
                    setSelectedIndexIfSoldierDescended(hasDescended, armyJList, index);
                }
            }
        });
    }

    
    private void setSelectedIndexIfSoldierAscended(boolean hasAscended, JList<String> armyJList, int index)
    {
        if (hasAscended)
        {
            armyJList.setSelectedIndex(index - 1);
        }
        else
        {
            armyJList.setSelectedIndex(index);
        }
    }

    
    private void setSelectedIndexIfSoldierDescended(boolean hasDescended, JList<String> armyJList, int index)
    {
        if (hasDescended)
        {
            armyJList.setSelectedIndex(index + 1);
        }
        else
        {
            armyJList.setSelectedIndex(index);
        }
    }
}
