package TuitionManager;

/**
 Class for International students, extends the Student class and inherits the
 properties. International students are students who are not from the country
 where the school exists.

 @author Rizwan Chowdhury
 @author Tin Fung
 */
public class International extends Student {

    private boolean exchange;

    //constants
	private final int INTERNATIONAL_TUITION = 945;
	private final int INTERNATIONAL_FEE = 350;
	private final int FULL_TIME_FEE = 1441;
	private final int PART_TIME = 846;
	private final int OVER_FIFTEEN_CREDITS = 15;
	private final int FULL_TIME_THRESHOLD = 12;

    /**
     Constructor for the International class, instantiates the object by calling the
     Student super class' constructor.
     @param fname Student's First Name, type String
     @param lname Student's Las Name, type String
     @param credit Number of credits student is taking/has, type int
     @param exchange Whether or not the student is an exchange student, type boolean
     @author Rizwan Chowdhury
     @author Tin Fung
     */
    public International(String fname, String lname, int credit, boolean exchange){
        //creates the object by call super's constructor
        super(fname,lname,credit);

        //sets exchange property
        this.exchange = exchange;
    }


    /**
     Calculates the tuition that the International student has to pay, based on their
     status as an International student and how many credits they are taking.

     @return int, amount of tuition that the student has to pay

     @author Rizwan Chowdhury
     @author Tin Fung
     */
    @Override
    public int tuitionDue() {
    	if(this.exchange) {
    		return FULL_TIME_FEE+INTERNATIONAL_FEE;
    	}else {
    		if(this.credit>=OVER_FIFTEEN_CREDITS) {
        		return OVER_FIFTEEN_CREDITS*INTERNATIONAL_TUITION+FULL_TIME_FEE+INTERNATIONAL_FEE;
        	}else   if(this.credit>=FULL_TIME_THRESHOLD) {
    		return this.credit*INTERNATIONAL_TUITION+FULL_TIME_FEE+INTERNATIONAL_FEE;
    	}else {
    		return this.credit*INTERNATIONAL_TUITION+PART_TIME+INTERNATIONAL_FEE;
    	}
    	}
    
    }


    /**
     Creates the string representation for the International student.
     In the form:
        FirstName LastName Credit: (value of credit)
        International  Exchange: (Yes/No)
        Tuition Due: the value of tuition
     @return String representation of the International student


     @author Tin Fung
     */
    @Override
    public String toString(){
    	if(this.exchange) {
        	return super.toString()+"International\nExchange: Yes";

    	}else {
        	return super.toString()+"International\nExchange: No";

    	}

    }


    /**
     Testbed main to test the International class with different test cases.
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

		//check toString()
		//input: fname: United , lname: Kingdom, credit: 13, exchange: true
		//expected output: United Kingdom:\nCredit: 13\nStatus: International\nExchange:Yes
		International checkToString = new International("United","Kingdom",13,true);
		expected = "United Kingdom:\nCredit: 13\nStatus: International\nExchange: Yes";
		checkTestCases(checkToString.toString(),expected);

		//check constructor International()
		//input: fname: Aus, lname: Tralia, credits: 14, exchange: true
		//expected output: Aus Tralia:\nCredit: 14\nStatus: International\nExchange: Yes
		International checkConstructor1 = new International("Aus","Tralia",14,true);
		expected = "Aus Tralia:\nCredit: 14\nStatus: International\nExchange: Yes";
		checkTestCases(checkConstructor1.toString(),expected);
		//input: fname: Colom, lname: Bia, credit: 13, exhcange: false
		International checkConstructor2 = new International("Colom","Bia",13,false);
		expected = "Colom Bia:\nCredit: 13\nStatus: International\nExchange: No";
		checkTestCases(checkConstructor2.toString(),expected);

		//check tuitionDue()
		International exchangePartTime = new International("South","Africa",10,true);
		International exchangeAtTwelveCredits = new International("Chi","Na",12,true);
		International exchangeFullTime = new International("Ja","Pan",14,true);
		International exchangeBeyondFifteenCredit = new International("Fr","Ance",20,true);
		International nonExchangePartTime = new International("Ca","Nada",10,false);
		International nonExchangeAtTwelveCredits = new International("Bangla","Desh",12,false);
		International nonExchangeFullTime = new International("Mex","Ico",14,false);
		International nonExchangeBeyondFifteenCredit = new International("Ger","Many",19,false);
		//input: exchangePartTime = fname:South, lname: Africa, credit: 10, exchange: true
		//exepcted output: 1791
		expected = "1791";
		checkTestCases(Integer.toString(exchangePartTime.tuitionDue()),expected);
		//input: exchangeAtTwelveCredits = fname: Chi, lname: Na, credit:12,exchange:true
		//expected output: 1791
		expected = "1791";
		checkTestCases(Integer.toString(exchangeAtTwelveCredits.tuitionDue()),expected);
		//input: exchangeFullTime = fname: Ja, lname: Pan, credits: 14, exchange: true
		//expected output: 1791
		expected = "1791";
		checkTestCases(Integer.toString(exchangeFullTime.tuitionDue()),expected);
		//input: exchangeBeyondFifteenCredit = fname: Fr, lname: ance, credit: 20, exchange: true
		//expected output: 1791
		expected = "1791";
		checkTestCases(Integer.toString(exchangeBeyondFifteenCredit.tuitionDue()),expected);
		//input: nonExchangePartTime = fname: Ca, lname: Nada, credit: 10, exchange: false
		//expected output: 10646
		expected = "10646";
		checkTestCases(Integer.toString(nonExchangePartTime.tuitionDue()),expected);
		//input: nonExchangeAtTwelveCredits = fname: Bangla, lname: Desh, credits: 12, exchange: false
		//expected output: 13131
		expected = "13131";
		checkTestCases(Integer.toString(nonExchangeAtTwelveCredits.tuitionDue()),expected);
		//input: nonExchangeFullTime = fname: Mex, lname: Ico, credit: 14, exchange: false
		//expected output: 15021
		expected = "15021";
		checkTestCases(Integer.toString(nonExchangeFullTime.tuitionDue()),expected);
		//input: nonExchangeBeyondFifteenCredit = fname: Ger, lname: Many, credits: 19, exchange: false
		//expected output: 15966
		expected = "15966";
		checkTestCases(Integer.toString(nonExchangeBeyondFifteenCredit.tuitionDue()),expected);

		//check compareTo of Student class with International class
		International riz = new International("Rizwan","Chowdhury",13,true);
		International rizInDisguise = new International("Rizwan","Chowdhury",14,false);
		International tin = new International("Tin","Fung",15,true);
		International abe = new International("Abe","Lincoln",14,false);
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
