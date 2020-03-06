package TuitionManager;

/**
 Abstract Student class, representing the Student object. This class will hold
 the empty methods that will have to be implemented by classes that extend this class.
 Will hold Student name and credit being taken.

 @author Rizwan Chowdhury
 @author Tin Fung
 */

public abstract class Student implements Comparable {
    //fields for the abstract class:
    private String fname;
    private String lname;
    protected int credit;

    /**
     Constructor for the Student object, will take first&last name and number of credits
     as input and assign them to corresponding fields. This will be the super constructor for
     classes that extend this method.
     @param fname Student's first name, string type
     @param lname Student's Last name, String type
     @param credit The amount of credits Student is taking this semester
     @author Rizwan Chowdhury
     @author Tin Fung
     */
    public Student(String fname, String lname, int credit){

        //set fields
        this.fname = fname;
        this.lname = lname;
        this.credit = credit;
    }


    /**
     Compares two Student objects to check if they are the same student or different.
     This method will compare the names of the two students.

     @param obj Student object passed as argument to be compared to the caller of method
     @return 0 if the Student objects equal each other (same student), -1 if this Student is less than, 1 if this is more than
     @author Rizwan Chowdhury
     @author Tin Fung
     */
    @Override
    public int compareTo(Object obj) {
        if( (!(obj instanceof Student)) || (obj == null) ) {
  		    return -1;
  	    }
  	  
  	    //create Student obj for target
    	Student target= (Student) obj;

        //compare first and last names:
        int fnameCompare = this.fname.compareToIgnoreCase(target.fname);
        if(!(fnameCompare==0)){
            if(fnameCompare<0){
                return -1;
            }
            return 1;
        }

        int lnameCompare = this.lname.compareToIgnoreCase(target.lname);
        if(!(lnameCompare==0)){
            if(lnameCompare<0){
                return -1;
            }
            return 1;
        }

  	    //at this point target member's name and date equals calling member's so they are equal
  	    return 0;
    }


    /**
     Returns the string representation of a Student object, contains the students' full name and
     credit hours.
     @return String representation of the student object

     @author Rizwan Chowdhury
     @author Tin Fung
     */
    @Override
    public String toString(){
    	return this.fname+" "+this.lname+":\nCredit: "+this.credit + "\nStatus: ";
    }


    /**
     Calculates the tuition a Student has to pay based on credit hours and their status regarding
     where they are from (Instate, Outstate, International)
     @return int representing the amount of tuition Student has to pay

     @author Rizwan Chowdhury
     @author Tin Fung
     */
    public abstract  int tuitionDue();
   
}

