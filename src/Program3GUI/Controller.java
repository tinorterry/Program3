package Program3GUI;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.event.ActionEvent;

/**
 * Controller class used to define and control the functionalities of the GUI. Will coordinate with FXML file.
 * @author Rizwan Chowdhury
 * @author Tin Fung
 */
public class Controller {

    //type-specific variables
    String typeOfStudent;
    int instateStudentFunds;
    boolean exchangeStudent;
    boolean tristateareaStudent;

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
    private TextField fnameInput;

    @FXML
    private TextField lnameInput;

    @FXML
    private TextField creditsInput;

    //type-specific elements:
    @FXML
    private ToggleGroup studentType;

    @FXML
    private ToggleGroup triStateRadioGroup;

    @FXML
    private ToggleGroup exchangeRadioGroup;


    /**
     * Function to clear the gui inputs after each time "Add"/"Remove"/"Print" button is pressed.
     * @author Rizwan Chowdhury
     * @author Tin Fung
     */
    private void clearGuiInputs(){
        //needs to be done
        // - should clear text fields and de-select any radio/toggle buttons
        // - should NOT clear output text area
    }


    /**
     * Clear the type specific inputs when user selects different student type: Instate, Outstate, International
     *
     * @param event radio button for student type being pressed
     *
     * @author Rizwan Chowdhury
     * @author Tin Fung
     */
    private void clearTypeSpecificInputs(ActionEvent event){
        //needs to be done
        // - should : everytime user selects a different option for type of student clear any currently entered type
        //            specific inputs already placed. For example: if user first clicked Outstate and selected Yes for
        //            tri-state area and then selected Instate, then should de-select the Yes in tri-state if possible


    }


    /**
     * Will deselect all the radio buttons in the given group.
     * @param groupToBeCleared group for which radio button are to be cleared.
     */
    private void clearRadioButtons(ToggleGroup groupToBeCleared){

    }


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

        setStudentType(event);
    }


    /**
     * Sets type specific (type-specific = type of student) information for type of student selected based on certain
     * events. Info such as funds or either exchange or not are set. Events that trigger this are filling out funds or
     * making selection of exchange or not and etc.
     *
     * @param event Actions performed to specify type-specific information
     * @author Rizwan Chowdhury
     * @author Tin Fung
     */
    public void setStudentType(ActionEvent event){
        //needs to be done
        // should set the proper variable with information based on user inputs in the type-specific area
        // the variables will be initialized above, will be in section labeled type-specific variables
        RadioButton selectedButton  = (RadioButton) event.getSource();
        switch (selectedButton.getId()){
            case "instateRadioButton":
                typeOfStudent = "Instate";
                break;

            case "outstateRadioButton":
                typeOfStudent = "Outstate";
                break;

            case "internationalRadioButton":
                typeOfStudent = "International";
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
        //- Will check for proper input format and that proper input exists --> will req additional methods
        //- Will check if information inputted obeys the rules of type of student selected. --> will req additional methods
        //- Will check if Student is already in list, if not then finally add the student.
        String fname;
        String lname;
        int credits;

        //check if name inputs are correct:
        String fnameInputString = fnameInput.getText();
        String lnameInputString = lnameInput.getText();
        if( (checkNameTextFieldContent(fnameInputString)) && (checkNameTextFieldContent(lnameInputString)) ){
            fname = fnameInputString;
            lname = lnameInputString;
        }
        else{
            outputArea.appendText("First/Last name not inputted at all/in correct format.\n");
            return;
        }

        //check if credits input is correct
        try {
            credits = parseCreditsValue(creditsInput.getText());
        } catch(NumberFormatException e){
            outputArea.appendText("Credits must be entered as numbers only (integers).\n");
            return;
        }

        //check if proper value for credits input:
        if(!isGreaterThanZero(credits)){
            outputArea.appendText("Must take greater than zero credits.\n");
            return;
        }
    }


    /**
     * Will call the specific function based on the type of student being added.
     * @param fname Student's first name
     * @param lname Student's last name
     * @param credits Amount of credits student is taking
     */
    private void addSpecificTypeStudent(String fname, String lname, int credits){

    }


    /**
     * Will add instate student to the list
     * @param fname student's first name
     * @param lname student's last name
     * @param credits credits the student is taking
     */
    private void addInstateStudent(String fname, String lname, int credits){

    }


    /**
     * Will add outstate student to the list
     * @param fname student's first name
     * @param lname student's last name
     * @param credits credits the student is taking
     */
    private void addOutstateStudent(String fname, String lname, int credits){

    }


    /**
     * Will add international student to the list
     * @param fname student's first name
     * @param lname student's last name
     * @param credits credits the student is taking
     */
    private void addInternationalStudent(String fname, String lname, int credits){

    }

    /**
     * Remove student from running list when the Remove button is pressed.
     * @param event Pressing of the Remove button
     * @author Rizwan Chowdhury
     * @author Tin Fung
     */
    @FXML
    public void actionWhenRemoveButtonPressed(ActionEvent event){
        //needs to be done

        // - Check if inputted information is in correct format (maybe) and that proper input exists
        // - Check if list is empty, if it is will print no student/else go to next step
        // - Check if inputted student exists in list, if yes then remove else print student not in list
    }


    /**
     * Checks to see if names entered in text fields are there and are in proper format.
     * @param content The content of the text field being checked
     * @return true if checks succeed, false otherwise
     *
     * @author Rizwan Chowdhury
     */
    private boolean checkNameTextFieldContent(String content){
        // if the field is empty then check fails
        if(content.equals("")){
            return false;
        }

        //check to see how many tokens there are; if more than 1 than too many
        String[] nameTokens = content.split(" ");
        if(nameTokens.length > 1){
            return false;
        }

        //now the content has passed the tests:
        return true;
    }


    /**
     * Will parse the value inputted for credits to see how many credits a student is taking. Take input from the
     * credits input textfield.
     *
     * @param creditString string that holds the number of credits inputted
     * @return int value of the credits being taken
     * @throws NumberFormatException if input is not an integer or contains non-numeric characters then this will be thrown
     *
     * @author Rizwan Chowdhury
     */
    private int parseCreditsValue(String creditString) throws NumberFormatException {
        int credits = Integer.parseInt(creditString);
        return credits;
    }


    /**
     * Will check if a number inputted is greater than zero or not
     * @param numericValue number being checked
     * @return true if is greater than zero, false otherwise.
     * @author Rizwan Chowdhury
     */
    private boolean isGreaterThanZero(int numericValue){
        return (numericValue>0) ? true:false;
    }


    /**
     * Prints all the student to the output textbox area when "Print" button is pressed.
     * @param event Print button being pressed
     */
    @FXML
    public void actionWhenPrintButtonPressed(ActionEvent event){
        //needs to be done

        // -Will check if list is empty, if yes then print list empty else go to next line
        // -Will print all the students in list using the StudentList print() function
        // -Will print --end of list-- at the end
    }

}
