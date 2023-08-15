package com.tokioschool.controller;

import com.tokioschool.model.Board;
import com.tokioschool.model.BoardManager;
import com.tokioschool.model.CharacterType;
import com.tokioschool.model.GameUnit;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    // Hero Creation Pane
    @FXML
    private TextField heroNameTxt;
    @FXML
    private ComboBox<String> heroTypeCmb;
    @FXML
    private TextField heroHealthTxt;
    @FXML
    private TextField heroArmorTxt;
    @FXML
    private ListView<String> heroListView;

    // Beast Creation Pane
    @FXML
    private TextField beastNameTxt;
    @FXML
    private ComboBox<String> beastTypeCmb;
    @FXML
    private TextField beastHealthTxt;
    @FXML
    private TextField beastArmorTxt;
    @FXML
    private ListView<String> beastListView;

    @FXML
    private TextArea battleTxtArea;
    private BoardManager boardManager;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.boardManager = new BoardManager(new Board());
        heroListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        beastListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        setComboBoxes();
    }

    /**
	 * The function adds a hero to a game using the provided data and updates the hero list view.
	 * 
	 * @param event The event parameter is of type ActionEvent. It represents the event that triggered
	 * the method, such as a button click.
	 */
	@FXML
    void addHero(ActionEvent event) {
        String nameValue = heroNameTxt.getText();
        String healthValue = heroHealthTxt.getText();
        String armorValue = heroArmorTxt.getText();

        if (!nameValue.isEmpty() && heroTypeCmb.getValue() != null && !healthValue.isEmpty() && !armorValue.isEmpty()) {
            try {
                int healthInt = Integer.parseInt(healthValue);
                int armorInt = Integer.parseInt(armorValue);
                // Add hero to game using provided data and put it in the hero list view
                if (CharacterType.get(heroTypeCmb.getValue()).getTeam().equals(GameUnit.TEAM_HEROES)) {
                    boardManager.addHero(nameValue, heroTypeCmb.getValue(), healthInt, armorInt);
                    heroListView.getItems().setAll(boardManager.getHeroArmyGUI());
                } else {
                    System.out.println("AddHero type error");
                }
                System.out.println("Added");
            } catch (NumberFormatException e) {
                System.out.println("Invalid field!");
            }
        } else {
            System.out.println("All fields must be filled!");
        }
    }

    /**
	 * The function adds a beast to a game if all the required fields are filled.
	 * 
	 * @param event The event parameter is an ActionEvent object that represents the event that
	 * triggered the method.
	 */
	@FXML
    void addBeast(ActionEvent event) {
        String nameValue = beastNameTxt.getText();
        String healthValue = beastHealthTxt.getText();
        String armorValue = beastArmorTxt.getText();

        if (!nameValue.isEmpty() && beastTypeCmb.getValue() != null && !healthValue.isEmpty() && !armorValue.isEmpty()) {
            try {
                int healthInt = Integer.parseInt(healthValue);
                int armorInt = Integer.parseInt(armorValue);
                // Add beast to game using provided data and put it in the beast list view
                if (CharacterType.get(beastTypeCmb.getValue()).getTeam().equals(GameUnit.TEAM_BEASTS)) {
                    boardManager.addBeast(nameValue, beastTypeCmb.getValue(), healthInt, armorInt);
                    beastListView.getItems().setAll(boardManager.getBeastArmyGUI());
                } else {
                    System.out.println("AddBeast type error");
                }
                System.out.println("Added");
            } catch (NumberFormatException e) {
                System.out.println("Invalid field!");
            }
        } else {
            System.out.println("All fields must be filled!");
        }
    }

    /**
	 * The function deletes a selected hero from a list view and updates the data source.
	 * 
	 * @param event The event parameter is an ActionEvent object that represents the event that
	 * triggered the method.
	 */
	@FXML
    void deleteHero(ActionEvent event) {
        int index = heroListView.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            System.out.println(boardManager.deleteHero(index));
            // Remove the selected items from the data source
            heroListView.getItems().remove(index);
            heroListView.refresh();
        } else {
            System.out.println("No items selected.");
        }
    }

    /**
	 * This function deletes a selected item from a ListView and updates the data source.
	 * 
	 * @param event The event parameter is an ActionEvent object that represents the event that
	 * triggered the method.
	 */
	@FXML
    void deleteBeast(ActionEvent event) {
        int index = beastListView.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            System.out.println(boardManager.deleteBeast(index));
            // Remove the selected items from the data source
            beastListView.getItems().remove(index);
            beastListView.refresh();
        } else {
            System.out.println("No items selected.");
        }
    }

    /**
	 * This function moves the selected hero in a list up by one position.
	 * 
	 * @param event The event parameter is an ActionEvent object that represents the event that
	 * triggered the method.
	 */
	@FXML
    void upHero(ActionEvent event) {
        int index = heroListView.getSelectionModel().getSelectedIndex();
        boardManager.ascendHeroIndex(index);
        heroListView.getItems().setAll(boardManager.getHeroArmyGUI());
        heroListView.getSelectionModel().select(index - 1);
        heroListView.refresh();
    }

    /**
	 * The function "upBeast" is used to move a selected beast up in the list by one position.
	 * 
	 * @param event The event parameter is an ActionEvent object that represents the event that
	 * triggered the method.
	 */
	@FXML
    void upBeast(ActionEvent event) {
        int index = beastListView.getSelectionModel().getSelectedIndex();
        boardManager.ascendBeastIndex(index);
        beastListView.getItems().setAll(boardManager.getBeastArmyGUI());
        beastListView.refresh();
        beastListView.getSelectionModel().select(index - 1);
    }

    /**
	 * The function `downHero` is used to move a selected hero down in a list and update the GUI
	 * accordingly.
	 * 
	 * @param event The event parameter is an ActionEvent object that represents the event that
	 * triggered the method.
	 */
	@FXML
    void downHero(ActionEvent event) {
        int index = heroListView.getSelectionModel().getSelectedIndex();
        boardManager.descendHeroIndex(index);
        heroListView.getItems().setAll(boardManager.getHeroArmyGUI());
        heroListView.getSelectionModel().select(index + 1);
        heroListView.refresh();
    }

    /**
	 * The function "downBeast" is used to move a selected item in a ListView down by one position.
	 * 
	 * @param event The event parameter is an ActionEvent object that represents the event that
	 * triggered the method.
	 */
	@FXML
    void downBeast(ActionEvent event) {
        int index = beastListView.getSelectionModel().getSelectedIndex();
        boardManager.descendBeastIndex(index);
        beastListView.getItems().setAll(boardManager.getBeastArmyGUI());
        beastListView.getSelectionModel().select(index + 1);
        beastListView.refresh();
    }

    /**
	 * The function "fightBattle" clears the battle text area and then simulates a battle using the
	 * board manager, appending the battle logs to the text area.
	 * 
	 * @param event The event parameter is an ActionEvent object that represents the event that
	 * triggered the method.
	 */
	@FXML
    public void fightBattle(ActionEvent event) {
        battleTxtArea.clear();
        StringBuilder battleLogs = boardManager.simulateBattle();
        battleTxtArea.appendText(battleLogs.toString());
    }

    /**
	 * The function sets the items of two ComboBoxes with the hero types and beast types obtained from
	 * the board manager.
	 */
	private void setComboBoxes() {
        ObservableList<String> heroItems = heroTypeCmb.getItems();
        ObservableList<String> beastItems = beastTypeCmb.getItems();
        heroItems.addAll(boardManager.getHeroTypes());
        beastItems.addAll(boardManager.getBeastTypes());
    }
}
