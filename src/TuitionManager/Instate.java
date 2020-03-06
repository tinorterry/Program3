package TuitionManager;

/**
 Class for an Instate student, extends the Student class. Represents a student that
 lives in the same state as the school. Instate students have funds that they receive from
 the state/school.

 @author Rizwan Chowdhury
 @author Tin Fung
 */
public class Instate extends Student {

    private int funds;

    //constants
    private final int IN_STATE_TUITION = 433;
    private final int FULL_TIME_FEE = 1441;
    private final int PART_TIME = 846;
    private final int OVER_FIFTEEN_CREDITS = 15;
    private final int FULL_TIME_THRESHOLD = 12;
    private final int MINIMUM_FUNDS = 0;

    /**
     Constructor for the Instate class. Creates a Instate object by creating an
     instance of the super Student class.
     @param fname Student's First name, of type String
     @param lname Student's Last name, of type String
     @param credit How many credits the Student is taking/has, of type int
     @param funds  Amound of funding student is receiving, type int

     @author Rizwan Chowdhury
     @author Tin Fung
     */
    public Instate(String fname, String lname, int credit, int funds){

        //create instance of the super class and this class
        super(fname,lname,credit);

        //set funds property
        this.funds = ((credit<FULL_TIME_THRESHOLD)||(funds<MINIMUM_FUNDS)) ? MINIMUM_FUNDS:funds;
    }


    /**
     Calculates the tuition that an instate student has to pay. Calculated based
     on student's credit hours and status as instate student.

     @return int, The amount of tuition the student has to pay.

     @author Rizwan Chowdhury
     @author Tin Fung
     */
    @Override
    public int tuitionDue(){

    	if(this.credit>=OVER_FIFTEEN_CREDITS) {
    		return OVER_FIFTEEN_CREDITS*IN_STATE_TUITION+FULL_TIME_FEE-this.funds;

    	}else if(this.credit>=FULL_TIME_THRESHOLD) {
    		return this.credit*IN_STATE_TUITION+FULL_TIME_FEE-this.funds;
    	}else {
    		return this.credit*IN_STATE_TUITION+PART_TIME;
    	}
    }


    /**
     Creates and returns the string representation of an Instate student.
     Will be in the form:
        FirstName LastName Credit:(value for credit)
        Instate Funds:(value for funds)
        Tuition Due: (value for tuitionDue)

     @return A String containing the information regarding the instate student

     @author Tin Fung
     */
    @Override
    public String toString(){
    	return super.toString()+ "Instate" + "\nInstate Funds: "+this.funds;
    }


    /**
     Testbed main to test the Instate class with different test cases.
     @param args arguments passed into the testbed main class
     @author Rizwan Chowdhury
     @author Tin Fung
     */
    public static void main(String[] args){
    	//test checkTestCases(String,String)
        // input: "20" and "20", expected output: pass
        checkTestCases(String.valueOf(20),"20");
        //input: "hello" and "bye", expected output: fail
        checkTestCases("hello","bye");


        String expected; //will be used to pass in expeceted string to checkTestCases

        //test constructor Instate(String,String,int,int)
        // input: Rizwan Chowdhury 13 200
        // expected output(from toString): Rizwan Chowdhury:\nCredit: 13\nStatus: Instate\nInstate Funds: 200
        Instate checkConstrucotr1_1 = new Instate("Rizwan", "Chowdhury", 13, 200);
        expected = "Rizwan Chowdhury:\nCredit: 13\nStatus: Instate\nInstate Funds: 200";
        checkTestCases(checkConstrucotr1_1.toString(),expected);
        //input: Rizwan Chowdhury 13 -200
        //expected output: Rizwan Chowdhury:\nCredit: 13\nStatus: Instate\nInstate Funds: 0
        Instate checkConstructor2 = new Instate("Rizwan","Chowdhury",13,-200);
        expected = "Rizwan Chowdhury:\nCredit: 13\nStatus: Instate\nInstate Funds: 0";
        checkTestCases(checkConstructor2.toString(),expected);
        //input: Rizwan Chowdhury 8 299
        //expected output: Rizwan Chowdhury:\nCredit: 8\nStatus: Instate\nInstate Funds: 0
        Instate checkConstructor3 = new Instate("Rizwan","Chowdhury",8,299);
        expected = "Rizwan Chowdhury:\nCredit: 8\nStatus: Instate\nInstate Funds: 0";
        checkTestCases(checkConstructor3.toString(),expected);

        //test toString()
        //input: Rizwan Chowdhury 13 200
        //expected output: Rizwan Chowdhury:\nCredit: 13\nStatus: Instate\nInstate Funds: 200
        //will use already created object checkConstructor1_1
        expected = "Rizwan Chowdhury:\nCredit: 13\nStatus: Instate\nInstate Funds: 200";
        checkTestCases(checkConstrucotr1_1.toString(),expected);


        //test tuitionDue()
        Instate checkTuitionDuePartTime = new Instate("Tin","Fung",7,300);
        Instate checkTuitionDueFullTime = new Instate("Tin","Orterry",13,200);
        Instate checkTuitionDueOverFifteen = new Instate("Riz","Chow",17,200);
        //input: Tin Fung 7 300 = checkTuitionDuePartTime
        //expected output: 3877
        checkTestCases(Integer.toString(checkTuitionDuePartTime.tuitionDue()),"3877");
        //input: Tin Orterry Credits: 13 and Funds: 200 = checkTuitionDueFullTime
        //expected output: 6870
        checkTestCases(Integer.toString(checkTuitionDueFullTime.tuitionDue()),"6870");
        //input: Riz Chow Credits: 17 and funds: 200 = checkTuitionDueOverFifteen
        //expected output: 7736
        checkTestCases(Integer.toString(checkTuitionDueOverFifteen.tuitionDue()),"7736");

        //test compareTo() of Student with Instate cases:
        Instate riz = new Instate("Rizwan","Chowdhury",13,100);
        Instate rizInDisguise = new Instate("Rizwan","Chowdhury",15,0);
        Instate tin = new Instate("Tin","Fung",12,200);
        Instate abe = new Instate("Abe","Lincoln",14,300);
        //input: two same students
        //expected outptut: 0
        checkTestCases(Integer.toString(riz.compareTo(rizInDisguise)),"0");
        //input: target student greater than, base will be riz
        //expected output: -1
        checkTestCases(Integer.toString(riz.compareTo(tin)),"-1");
        //input: target student less than, base will be riz
        //expected output: 1
        checkTestCases(Integer.toString(riz.compareTo(abe)),"1");

    }


    /**
     Checks if the test cases pass/fail by comparing String versions of the result returned and expected output.
     Prints pass if test cases matches expected or fail if test case does not match.
     @param methodResult value returned from method being tested, method output
     @param expected value of expected output

     @author Rizwan Chowdhury
     */
    private static void checkTestCases(String methodResult, String expected){
        boolean pass = expected.equals(methodResult);
        String statusString = (pass) ? "pass":"fail";
        System.out.println("-New Case:");
        System.out.println("--Status: " + statusString);
        System.out.println("--MethodResult:\n" + methodResult);
        System.out.println("--Expected:\n" + expected);
        System.out.println("\n");
    }
}
