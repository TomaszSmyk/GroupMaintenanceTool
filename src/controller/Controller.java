package controller;


import model.Database;
import model.Model;
import model.Student;
import view.Command;
import view.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

/**
 * Main controller, used to control buttons and combo boxes
 */
public class Controller implements ActionListener {
    private int groupSelected = 1;

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String action = actionEvent.getActionCommand();
        Controller controller = new Controller();
        if(action.equals(Command.ADD.toString())){
            String fName = View.addFirstNameTextField.getText();
            String lName = View.addLastNameTextField.getText();
            int index = Integer.parseInt(View.addIndexTextField.getText());
            int groupNumber = Integer.parseInt(View.addGroupTextField.getText());
            String email = View.addEmailTextField.getText();
            Student student = new Student.Builder().firstName(fName).lastName(lName).index(index).groupNumber(groupNumber).email(email).build();
            controller.notifyStudentAdded();
            Database db = new Database();
            db.insertStudent(student.getFirstName(), student.getLastName(), student.getIndex(), student.getGroupNumber(), student.getEmail());
            Model model = new Model(student.getGroupNumber(), Model.lessonNumber);
            View.groupNumbers = new JComboBox<Integer>(new DefaultComboBoxModel<Integer>(model.updateGroupNumbers().toArray(new Integer[0])));
        }
        else if (action.equals(Command.DELETE.toString())){
            String fName = View.removeFirstNameTextField.getText();
            String lName = View.removeLastNameTextField.getText();
            int index = Integer.parseInt(View.removeIndexTextField.getText());
            Database db = new Database();
            db.removeStudentFromDatabase(fName, lName, index);
            Model model = new Model(Model.activeGroup, Model.lessonNumber);
            View.groupNumbers = new JComboBox<Integer>(new DefaultComboBoxModel<Integer>(model.updateGroupNumbers().toArray(new Integer[0])));
        }
        else if (action.equals(Command.GROUP_NUMBER_CHANGED.toString())) {
            JComboBox<Integer> jcb = (JComboBox)actionEvent.getSource();
            groupSelected = (Integer)jcb.getSelectedItem();
            new Model(groupSelected, Model.lessonNumber);
        }
        else if (action.equals(Command.WEEK_NUMBER_CHANGED.toString())) {
            JComboBox<Integer> jcb = (JComboBox)actionEvent.getSource();
            System.out.println("Week number:" + jcb.getSelectedItem());
            Model.lessonNumber = (Integer)jcb.getSelectedItem();
            new Model(Model.activeGroup, (Integer)jcb.getSelectedItem());
        }
    }

    /**
     * notifies model on student added, so it refreshes data.
     */
    private void notifyStudentAdded() {
        new Model(this.groupSelected, Model.lessonNumber);
    }
}