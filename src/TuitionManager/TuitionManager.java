package TuitionManager;

import java.util.Scanner;

/**
 Class to handle the I/O for the project and manage the functionalities and flow of the other
 classes. Will issue add/remove and print commands to be carried out by other classes. Will
 create the objects, based on input, that tasks will be carried out on.

 @author Rizwan Chowdhury
 @author Tin Fung
 */
public class TuitionManager {
    private StudentList students;
    private Scanner stdin;

    //constants:
    private final int COMMAND_POSITION = 0;
    private final int FIRST_NAME_POSITION = 0;
    private final int LAST_NAME_POSITION = 1;
    private final int CREDIT_POSITION = 2;
    private final int TYPE_SPECIFIC_POSITION = 3;
    private final int SPLIT_LIMIT = 2; //how many tokens to split initial command string into
    private final int COMMAND_LENGTH = 1;
    private final int STUDENT_INFO_POSITION = 1;
    private final int INTERNATIONAL_STUDENT_CREDIT_REQUIREMENT = 9;
    private final int MINIMUM_CREDIT_ALLOWED = 1;
    private final int NUMBER_OF_PROPER_INPUTS_FOR_ADD = 4;
    private final int NUMBRER_OF_PROPER_INPUTS_FOR_REMOVE = 2;
    private final int COMMAND_LENGTH_LIMIT_FOR_P_AND_Q = 1;
    private final int MINIMUM_FUNDING_ALLOWED = 0;

    /**
     method that will be called to run the project.

     @author Rizwan Chowdhury
     */
    public void run() {
        //initiate scanner and studentlist objects
        students = new StudentList();
        stdin = new Scanner(System.in);

        //message that program started:
        System.out.println("Welcome, please enter inputs below in proper format.");

        //loop to accept inputs and do appropriate procedures
        boolean loop = true;
        String command;
        String[] inputLine;
        while(loop == true){
            inputLine = stdin.nextLine().split(" ",SPLIT_LIMIT);
            command = inputLine[COMMAND_POSITION];
            if(command.length()!=COMMAND_LENGTH){
                handleBadCommands("Input does not match format");
                continue;
            }
            switch (command.charAt(0)){
                //cases based on required inputs:
                case 'I': addNewStudent('I',inputLine[STUDENT_INFO_POSITION]);
                break;
                case 'O': addNewStudent('O',inputLine[STUDENT_INFO_POSITION]);
                break;
                case 'N': addNewStudent('N',inputLine[STUDENT_INFO_POSITION]);
                break;
                case 'R': removeStudent(inputLine[STUDENT_INFO_POSITION]);
                break;
                case 'P': if(inputLine.length>COMMAND_LENGTH_LIMIT_FOR_P_AND_Q){
                              handleBadCommands("Input does not match format");
                          }
                          else{
                              printCommand();
                          }
                break;
                case 'Q': if(inputLine.length>COMMAND_LENGTH_LIMIT_FOR_P_AND_Q){
                              handleBadCommands("Input does not match format");
                          }
                          else{
                              System.out.println("Program Terminated");
                              loop = false;
                          }
                break;
                default : handleBadCommands("\'"+command.charAt(0)+"\'"+" is not a proper command");
            }
        }

    }


    /**
     Adds a new student to the running list within this class.
     @param studentType Type of student to add (I)nstate, (O)utstate, I(N)ternational
     @param studentDetails String that contains details inputted as part of command

     @author Rizwan Chowdhury
     @author Tin Fung
     */
    private void addNewStudent(char studentType, String studentDetails){
        String[] studentInfo = studentDetails.split(" ");

        if(studentInfo.length != NUMBER_OF_PROPER_INPUTS_FOR_ADD){
            handleBadCommands("Input does not match format");
            return;
        }

        String firstName = studentInfo[FIRST_NAME_POSITION];
        String lastName = studentInfo[LAST_NAME_POSITION];
        int credits = Integer.parseInt(studentInfo[CREDIT_POSITION]);
        String typeSpecificData = studentInfo[TYPE_SPECIFIC_POSITION];

        if(credits<MINIMUM_CREDIT_ALLOWED){
            System.out.println("Need to take 1 or more credits. Could not add student");
            return;
        }

        switch (studentType){
            case 'I': addNewInstateStudent(firstName,lastName,credits,typeSpecificData);
            break;
            case 'O': addNewOutstateStudent(firstName,lastName,credits,typeSpecificData);
            break;
            case 'N': addNewInternationalStudent(firstName,lastName,credits,typeSpecificData);
            break;
        }
    }


    /**
     Adds specifically an Instate student to the list.

     @param firstName Students First name
     @param lastName Student's las name
     @param credits Number of credits student is taking
     @param fundString  funding for Instate student, is type-specific data

     @author Rizwan Chowdhury
     @author Tin Fung
     */
    private void addNewInstateStudent(String firstName, String lastName, int credits, String fundString){

        int funds = Integer.parseInt(fundString);

        if(funds<MINIMUM_FUNDING_ALLOWED){
            System.out.println("Funds has to be greater than or equal to 0. Could not add student.");
            return;
        }

        Student newInstateStudent = new Instate(firstName,lastName,credits,funds);
        if(!(students.contains(newInstateStudent))){
            students.add(newInstateStudent);
            System.out.println("Added new student: " + firstName + " " + lastName);
        }
        else{
            System.out.println("Student already in students list. Could not add Student");
        }
    }


    /**
     Specifically adds an Outstate student to the list. Gets first name - credit from calling method and parses
     type-specific string tristateString to obtain a boolean value.

     @param firstName Student's firstname
     @param lastName Student's last name
     @param credits number of credits the student is taking
     @param tristateString String represantion of wheter student lives in tristate area or not, type-specific data

     @author Rizwan Chowdhury
     */
    private void addNewOutstateStudent(String firstName, String lastName, int credits, String tristateString){

        boolean tristate;
        switch (getBooleanValue(tristateString)){
            case 1: tristate = true;
            break;
            case 0: tristate = false;
            break;
            default: System.out.println("Bad Input: Value for tristate should be \"T\" or \"F\"");
                     return;
        }

        Student newOutstateStudent = new Outstate(firstName,lastName,credits,tristate);
        if(!(students.contains(newOutstateStudent))){
            students.add(newOutstateStudent);
            System.out.println("Added new student: " + firstName + " " + lastName);
        }
        else{
            System.out.println("Student already in students list. Could not add student");
        }
    }


    /**
     Helper method to add new International students to the running list.
     @param firstName Student's first name
     @param lastName Student's last name
     @param credits  Student's credits
     @param exchangeString Is student an exchange student or not, boolean in string form, type-specific data

     @author Rizwan Chowdhury
     */
    private void addNewInternationalStudent(String firstName, String lastName, int credits, String exchangeString){

        if(credits<INTERNATIONAL_STUDENT_CREDIT_REQUIREMENT){
            System.out.println("Not enough credits for International Student. Could not add Student");
            return;
        }

        boolean exchange;
        switch (getBooleanValue(exchangeString)){
            case 1: exchange = true;
                break;
            case 0: exchange = false;
                break;
            default: System.out.println("Bad Input: Value for exchange should be \"T\" or \"F\"");
                     return;
        }

        Student newInternationalStudent = new International(firstName,lastName,credits,exchange);
        if(!(students.contains(newInternationalStudent))){
            students.add(newInternationalStudent);
            System.out.println("Added new student: " + firstName + " " + lastName);
        }
        else{
            System.out.println("Student already in students list. ");
        }
    }


    /**
     * Helper method to parse string containing type-specific-data for booleans from input
     * to determine which (if any) boolean value is being provided as input.
     * @param boolString String containing the boolean input, either "T" or "F"
     * @return int: 1 if true, 0 if false, -1 if not proper input format
     *
     * @author Rizwan Chowdhury
     */
    private int getBooleanValue(String boolString){
        if(boolString.equals("T")){
            return 1;
        }

        if(boolString.equals("F")){
            return 0;
        }

        return -1;
    }

    /**
     Removes given student from the running list in this class
     @param studentInfo Student's first and last names as a string

     @author Rizwan Chowdhury
     @author Tin Fung
     */
    private void removeStudent(String studentInfo){
        String[] studentDetails = studentInfo.split(" ");

        if(studentDetails.length != NUMBRER_OF_PROPER_INPUTS_FOR_REMOVE){
            handleBadCommands("Input does not match format");
            return;
        }

        String firstName = studentDetails[FIRST_NAME_POSITION];
        String lastName = studentDetails[LAST_NAME_POSITION];
        Student studentToBeRemoved = new Instate(firstName,lastName,0,0);

        boolean successfulRemoval = students.remove(studentToBeRemoved);
        if(successfulRemoval == false){
            System.out.println("Failed to remove Student");
        }
        else{
            System.out.println("Removed student: " + firstName + " " + lastName);
        }
    }


    /**
     Handles bad commands inputted into the command line.
     @param command identifier for certain bad commands, if they are lower case for example
     @author Rizwan Chowdhury
     @author Tin Fung
     */
    private void handleBadCommands(String command){
        System.out.println("Bad input: " + command);
    }


    /**
     * Prints a list of Students and the tuition amount due for each
     *
     * @author Rizwan Chowdhury
     * @author Tin Fung
     */
    private void printCommand(){

        if(students.isEmpty()){
            System.out.println("--Empty List--");
            return;
        }

        students.print();
        System.out.println("--End of List--");
    }
}
