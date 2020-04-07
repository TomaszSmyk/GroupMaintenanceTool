import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.BiPredicate;

public class Controller implements ActionListener {
    private JTextField fNameTextField;
    private JTextField lNameTextField;
    private JComboBox<String> groupComboBox;
    private String groupNumberChosen = "";


    public Controller(JTextField fNameTextField, JTextField lNameTextField, JComboBox<String> groupComboBox) {
        this.fNameTextField = fNameTextField;
        this.lNameTextField = lNameTextField;
        this.groupComboBox = groupComboBox;
        updateStudentGroups();
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String action = actionEvent.getActionCommand();

        BiPredicate<String, String> compare = Object::equals;
        if(compare.test(action, "add")) {
            System.out.println("added: " + fNameTextField.getText() + " " + lNameTextField.getText());

        }

        if(compare.test(action, "groupNumberChanged")) {
            JComboBox temp = (JComboBox) actionEvent.getSource();
//            System.out.println(temp.getSelectedItem());
//            System.out.println(temp.getSelectedItem() instanceof String);
            groupNumberChosen = (String) temp.getSelectedItem();
            //todo update presence
            //todo update add
            //todo update delete
            //todo update chart
        }
    }

    private void updateStudentGroups() {
        for(String element: Model.studentGroupData) {
            groupComboBox.addItem(element);
        }
    }
    public static int getGroupNumber() {
        if (Model.studentGroupCombobox.getSelectedItem() == null || Model.studentGroupCombobox.getSelectedItem().equals("SELECT")) {
            Object[] groupToChose = Model.studentGroupData;
            int groupNum = Integer.parseInt((String) JOptionPane.showInputDialog(new JFrame(), "Please chose student group:", "Group number", JOptionPane.PLAIN_MESSAGE, null, groupToChose, "0"));
            System.out.println("Group number chosen: " + groupNum);
            return groupNum;//todo return correct value from option pane
        } else {
            return Integer.parseInt((String) Model.studentGroupCombobox.getSelectedItem());//todo return correct value from View.groupComboBox
        }
    }
}