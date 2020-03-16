package Program3GUI;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;

/**
 * Controller class used to define and control the functionalities of the GUI. Will coordinate with FXML file.
 * @author Rizwan Chowdhury
 * @author Tin Fung
 */
public class Controller {

    //following are instances of the fxml elements, using their fxml ids, that will be used
    @FXML
    private HBox triStateSelectionArea;

    @FXML
    private HBox exchangeSelectionArea;

    @FXML
    private HBox fundsArea;

    @FXML
    private TextArea outputArea;

    @FXML
    private ToggleGroup exchangeRadioGroup;


    /**
     * Controller Method to disable/enable different sections of student specific information input based on which
     * option (type of student) is selected. Only section corresponding to selected option (Instate, Outstate, International)
     * will be enabled, others will be disabled.
     * @param event Toggling of Instate, Outstate, or International to trigger this function.
     * @author Rizwan Chowdhury
     */
    @FXML
    public void typeSelectionDisabler(ActionEvent event){
        String eventSourceId = ((RadioButton)event.getSource()).getId(); // get which button was toggled

        //based on toggled button carry out appropriate action
        switch (eventSourceId){
            case "instateRadioButton":
                triStateSelectionArea.setDisable(true);
                exchangeSelectionArea.setDisable(true);
                fundsArea.setDisable(false);
                break;

            case "outstateRadioButton":
                fundsArea.setDisable(true);
                exchangeSelectionArea.setDisable(true);
                triStateSelectionArea.setDisable(false);
                break;

            case "internationalRadioButton":
                fundsArea.setDisable(true);
                triStateSelectionArea.setDisable(true);
                exchangeSelectionArea.setDisable(false);
                break;
        }
    }


    /**
     * The action(s) that will be performed when the add button is pressed, mainly to add student to TuitionManager list.
     * @param event Button press of Add button in the GUI
     * @author Rizwan Chowhdury
     */
    @FXML
    public void actionWhenAddButtonPressed(ActionEvent event){
        //- Will check for proper input format and that proper input exists
        //- Will check if information inputted obeys the rules of type of student selected.
        //- Will check if Student is already in list, if not then finally add the student.


    }


    /**
     * Remove student from running list when the Remove button is pressed.
     * @param event Pressing of the Remove button
     * @author Rizwan Chowdhury
     * @author Tin Fung
     */
    @FXML
    public void actionWhenRemoveButtonPressed(ActionEvent event){
        // - Check if inputted information is in correct format (maybe) and that proper input exists
        // - Check if list is empty, if it is will print no student/else go to next step
        // - Check if inputted student exists in list, if yes then remove else print student not in list
    }


    /**
     * Prints all the student to the output textbox area when "Print" button is pressed.
     * @param event Print button being pressed
     */
    @FXML
    public void actionWhenPrintButtonPressed(ActionEvent event){
        // -Will check if list is empty, if yes then print list empty else go to next line
        // -Will print all the students in list using the StudentList print() function
        // -Will print --end of list-- at the end
    }

}
