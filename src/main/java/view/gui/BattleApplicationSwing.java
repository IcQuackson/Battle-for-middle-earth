package view.gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import com.intellij.uiDesigner.core.*;

public class BattleApplicationSwing
{

    public BattleApplicationSwing() {
        initComponents();
    }

    private void createUIComponents()
    {

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Pedro
        createUIComponents();

        var panel1 = new JPanel();
        panelForm = new JPanel();
        textFieldName = new JTextField();
        labelType = new JLabel();
        comboBoxTypes = new JComboBox();
        labelHealth = new JLabel();
        textFieldHealth = new JTextField();
        labelArmor = new JLabel();
        textFieldArmor = new JTextField();
        buttonAddCharacter = new JButton();

        //======== panelMain ========
        {
            panelMain.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax
            . swing. border. EmptyBorder( 0, 0, 0, 0) , "JFor\u006dDesi\u0067ner \u0045valu\u0061tion", javax. swing
            . border. TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .
            Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ), java. awt. Color. red
            ) ,panelMain. getBorder( )) ); panelMain. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override
            public void propertyChange (java .beans .PropertyChangeEvent e) {if ("bord\u0065r" .equals (e .getPropertyName (
            ) )) throw new RuntimeException( ); }} );
            panelMain.setLayout(new FlowLayout(FlowLayout.LEFT));

            //======== panel1 ========
            {
                panel1.setPreferredSize(new Dimension(260, 300));
                panel1.setBorder(new TitledBorder(LineBorder.createBlackLineBorder(), ""));
                panel1.setLayout(new FlowLayout());

                //======== panelForm ========
                {
                    panelForm.setAlignmentX(0.5F);
                    panelForm.setAlignmentY(0.5F);
                    panelForm.setEnabled(true);
                    panelForm.setPreferredSize(new Dimension(250, 237));
                    panelForm.setLayout(new GridLayoutManager(4, 2, new Insets(0, 5, 0, 5), -1, -1));

                    //---- labelName ----
                    labelName.setAlignmentX(0.5F);
                    labelName.setText("Nome:");
                    panelForm.add(labelName, new GridConstraints(0, 0, 1, 1,
                        GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                        GridConstraints.SIZEPOLICY_FIXED,
                        GridConstraints.SIZEPOLICY_FIXED,
                        null, null, null));
                    panelForm.add(textFieldName, new GridConstraints(0, 1, 1, 1,
                        GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
                        GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                        GridConstraints.SIZEPOLICY_FIXED,
                        null, null, null));

                    //---- labelType ----
                    labelType.setText("Tipo:");
                    panelForm.add(labelType, new GridConstraints(1, 0, 1, 1,
                        GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                        GridConstraints.SIZEPOLICY_FIXED,
                        GridConstraints.SIZEPOLICY_FIXED,
                        null, null, null));
                    panelForm.add(comboBoxTypes, new GridConstraints(1, 1, 1, 1,
                        GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
                        GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_FIXED,
                        null, null, null));

                    //---- labelHealth ----
                    labelHealth.setText("Vida:");
                    panelForm.add(labelHealth, new GridConstraints(2, 0, 1, 1,
                        GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                        GridConstraints.SIZEPOLICY_FIXED,
                        GridConstraints.SIZEPOLICY_FIXED,
                        null, null, null));
                    panelForm.add(textFieldHealth, new GridConstraints(2, 1, 1, 1,
                        GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
                        GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                        GridConstraints.SIZEPOLICY_FIXED,
                        null, null, null));

                    //---- labelArmor ----
                    labelArmor.setText("Armadura:");
                    panelForm.add(labelArmor, new GridConstraints(3, 0, 1, 1,
                        GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                        GridConstraints.SIZEPOLICY_FIXED,
                        GridConstraints.SIZEPOLICY_FIXED,
                        null, null, null));
                    panelForm.add(textFieldArmor, new GridConstraints(3, 1, 1, 1,
                        GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
                        GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                        GridConstraints.SIZEPOLICY_FIXED,
                        null, null, null));
                }
                panel1.add(panelForm);

                //---- buttonAddCharacter ----
                buttonAddCharacter.setPreferredSize(new Dimension(78, 30));
                buttonAddCharacter.setText("Button");
                panel1.add(buttonAddCharacter);
            }
            panelMain.add(panel1);
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Pedro
    private JPanel panelMain;
    private JPanel panelForm;
    private JLabel labelName;
    private JTextField textFieldName;
    private JLabel labelType;
    private JComboBox comboBoxTypes;
    private JLabel labelHealth;
    private JTextField textFieldHealth;
    private JLabel labelArmor;
    private JTextField textFieldArmor;
    private JButton buttonAddCharacter;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
