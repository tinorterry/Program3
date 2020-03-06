package TuitionManager;

/**
 Class for an Outstate student, a student that lives in a different state than the
 one the school is located in. Extends the Student class and inherits the properties.
 Outstate students have the property whether they are in the tri-state area.

 @author Rizwan Chowdhury
 @author Tin Fung
 */
public class Outstate extends Student {

    private boolean tristate;

    //constants:
	private final int OUT_STATE_TUITION = 756;
	private final int FULL_TIME_FEE = 1441;
	private final int PART_TIME = 846;
	private final int TRISTATE_DISCOUNT = 200;
	private final int OVER_FIFTEEN = 15;
	private final int FULL_TIME_THRESHOLD = 12;

    /**
     Constructor for the Oustate class, instantiate the class by calling the
     super class's constructor.

     @param fname Outstate Student's First Name, type String
     @param lname Outstate Student's Last Name, type String
     @param credit Amount of credits the student is taking/has, type int
     @param tristate Whether the student is from the tristate area or not, type boolean

     @author Rizwan Chowdhury
     @author Tin Fung
     */
    public Outstate(String fname, String lname, int credit, boolean tristate){
        //instantiate the object by calling the super's constructor
        super(fname,lname,credit);

        //set tristate property:
        this.tristate = tristate;
    }


    /**
     Calculates the tuition that the Outstate student has to pay. Based on the
     property of being an outstate student as well as how many credits they are
     taking.

     @return int, the amount of tuition the student has to pay

     @author Tin Fung
     */
    @Override
    public int tuitionDue() {

    	if(this.credit>=FULL_TIME_THRESHOLD) {
    		if(this.tristate) {
        		if(this.credit>=OVER_FIFTEEN) {
    	    		return OVER_FIFTEEN*(OUT_STATE_TUITION-TRISTATE_DISCOUNT)+FULL_TIME_FEE;
        		}
        		return this.credit*(OUT_STATE_TUITION-TRISTATE_DISCOUNT)+FULL_TIME_FEE;
        	}
    		else {
        		if(this.credit>=OVER_FIFTEEN) {
    	    		return OVER_FIFTEEN*OUT_STATE_TUITION+FULL_TIME_FEE;
        		}
        		return this.credit*OUT_STATE_TUITION+FULL_TIME_FEE;
        	}
    	}else {
    		return this.credit*OUT_STATE_TUITION+PART_TIME;
    	}

    }


    /**
     Creates the string representation for the Outstate student object.
     In the form:
        FirstName LastName Credit: value of credit
        Oustate TristateArea: (Yes/No)

     @return String representation of an Outstate class

     @author Tin Fung
     */
    @Override
    public String toString(){
    	if(this.tristate) {
        	return super.toString()+"Outstate\nTristateArea: Yes";

    	}else {
        	return super.toString()+"Outstate\nTristateArea: No";

    	}
    }


    /**
     Testbed main to test the Outstate class with different test cases.
     @param args arguments passed into the testbed main class

     @author Tin Fung
	 @author Rizwan Chowdhury
     */
    public static void main(String[] args){

		//test checkTestCases(String,String)
		// input: "20" and "20", expected output: pass
		checkTestCases(String.valueOf(20),"20");
		//input: "hello" and "bye", expected output: fail
		checkTestCases("hello","bye");

		String expected; //string used to set expected string values for checkTestCases()

		// test toString()
		//input: fname: Out lname: State credit: 20 tristate: true
		//expected output: Out State\nCredit: 20\nStatus: Outstate\nTristateArea: Yes
		Outstate checkConstructorAndtoString = new Outstate("Out","State",20,true);
		expected = "Out State:\nCredit: 20\nStatus: Outstate\nTristateArea: Yes";
		checkTestCases(checkConstructorAndtoString.toString(),expected);

		//test constructor Outstate(String fname, String lname, int credit, boolean tristate)
		//input: fname: Out lname: State credit: 20 tristate: true ; will use object created for toString() test
		//expected output: Out State\nCredit: 20\nStatus: Outstate\nTristateArea: Yes
		expected = "Out State:\nCredit: 20\nStatus: Outstate\nTristateArea: Yes";
		checkTestCases(checkConstructorAndtoString.toString(),expected);
		//input: fname: Non lname: Tristate credit: 14 tristate: false
		//expected output: Non Tristate:\nCredit: 14\nStatus: Outstate\nTristateArea: No
		Outstate nonTristateCheck = new Outstate("Non","Tristate",14,false);
		expected = "Non Tristate:\nCredit: 14\nStatus: Outstate\nTristateArea: No";
		checkTestCases(nonTristateCheck.toString(),expected);

		//test tuitionDue() for Outstate
		Outstate nonTristatePartTime = new Outstate("Jim","Bob",6,false);
		Outstate nonTristateFullTime = new Outstate("Bob","Jim",14,false);
		Outstate nonTristateOverFifteen = new Outstate("Jay","Barry",19,false);
		Outstate tristatePartTime = new Outstate("Hello","Bye",6,true);
		Outstate tristateFullTime = new Outstate("Bye","Hello",14,true);
		Outstate tristateOverFifteen = new Outstate("Rizwan","Chowdhury",18,true);
		Outstate atFullTimeThreshold = new Outstate("Riz","Chow",12,true);
		Outstate atFullTimeThresholdNonTristate = new Outstate("Tin","Fung",12,false);
		//input: nonTristatePartTime =  fname: "Jim" lname: "Bob" credit: 6 tristate: false
		//expected output: 5382
		checkTestCases(Integer.toString(nonTristatePartTime.tuitionDue()),"5382");
		//input: nonTristateFullTime = Bob Jim credit:14,tristate:false
		//expected output: 12025
		checkTestCases(Integer.toString(nonTristateFullTime.tuitionDue()),"12025");
		//input: nonTristateOverFifteen = Jay Barry credit:19,tristate:false
		//expected output: 12781
		checkTestCases(Integer.toString(nonTristateOverFifteen.tuitionDue()),"12781");
		//input: tristatePartTime = Hello Bye credit: 6, tristate: true
		//expected output: 5382
		checkTestCases(Integer.toString(tristatePartTime.tuitionDue()),"5382");
		//input: tristateFullTime = Bye Hello credit:14 tristate: true
		//expected output: 9225
		checkTestCases(Integer.toString(tristateFullTime.tuitionDue()),"9225");
		//input: tristateOverFifteen = Rizwan Chowdhury credit:18, tristate: true
		//expected output: 9781
		checkTestCases(Integer.toString(tristateOverFifteen.tuitionDue()),"9781");
		//input: atFullTimeThreshold = Riz Chow credit:12, tristate: true
		//expected output: 8113
		checkTestCases(Integer.toString(atFullTimeThreshold.tuitionDue()),"8113");
		//input: atFullTimeThresholdNonTristate = Tin Fung credit:12, tristate: false
		//expected output: 10513
		checkTestCases(Integer.toString(atFullTimeThresholdNonTristate.tuitionDue()),"10513");

		//check compareTo() of the student class:
		Outstate riz = new Outstate("Rizwan","Chowdhury",13,true);
		Outstate rizInDisguise = new Outstate("Rizwan","Chowdhury",13,false);
		Outstate tin = new Outstate("Tin","Fung",12,true);
		Outstate abe = new Outstate("Abe","Lincoln",14,false);
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
