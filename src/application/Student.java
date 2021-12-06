
package application;
/**
 * @author azeez + ahmed
 *
 */
public class Student implements Comparable<Student>, Cloneable{
	
	//--------------------Student Variables----------------------
	int id;					// Represent ID of the student			
	boolean hasExam;		// Represent if the student has an exam
	boolean isCatch;		// Represent if the student caught the lecture on time
	int intendedArrivalTime;// Represent the Intended time of arriving to uni.
	int showupTime;			// Represent the time student showed up at bus station
	
	//--------------------Student Constructors-------------------
	public Student() {
		this.setID(randomID());
		this.setHasExam(randomExamCondition());
		this.setIntendedArrivalTime(randomIntendedArrivalTime());
		this.setShowupTime(randomShowupTime());
	}
	
	public Student(int id, boolean hasExam, int IAT, int ST) {
		this.setID(id);
		this.setHasExam(hasExam);
		this.setIntendedArrivalTime(IAT);
		this.setShowupTime(ST);
	}

	//--------------Setters & Getters-------------------
	public void setID(int id) {
		this.id = id;
	}
	
	public int getID() {
		return this.id;
	}

	public void setHasExam(boolean HX) {
		this.hasExam = HX;
	}
	
	public boolean getHasExam() {
		return this.hasExam;
	}

	public void setIsCatch(boolean isCatch ) {
		this.isCatch = isCatch; 
		
	}
	
	public boolean isCatch() {
		return isCatch;
	}

	public void setShowupTime(int mins) {
		showupTime = mins;
	}
	
	public int getShowupTime() {
		return showupTime;
	}
	
	public void setIntendedArrivalTime(int mins) {
		intendedArrivalTime = mins;
	}
	
	public int getIntendedArrivalTime() {
		return intendedArrivalTime ;
	}
	
	//---------------------Methods---------------------------
	
	// Generate random ID for the student
	public int randomID() {
		// Random number between 2200000 & 1000000
		int id = (int) (Math.random() * (1200000) ) + 1000000;
		return id;
	}
	
	// Generate random probability of student having an exam
	public boolean randomExamCondition() {
		// Random exam probability of 5%
		boolean Exam = Math.random() < 0.95? false:true;
		return Exam;
	}
	
	// Generate random showed up time at the bus station
	public int randomShowupTime() {
		// Random show up time of 30 minutes before bus departure time
		int randomMinutes =  getIntendedArrivalTime() - 60 + (int) (Math.random() * 30) ;
		return randomMinutes;
	}
	
	// Generate random Intended time of arriving to KAU.
	public int randomIntendedArrivalTime() {
		// Random intended time of arriving at campus between 6AM to 10PM
		  int randomTime = (int) (Math.random() * ( 32 ) + 1) * 30;
		  if (randomTime == 960) {
			  return randomTime;
		  // if the random time is less than 60min (no classes at that time) add 60 more minutes
		  } else if (randomTime < 60) {
			  return randomTime + 60;
		  }else 
			  return randomTime;
	}
	
	// Compare show up times of students
	@Override
	public int compareTo(Student comparestu)
    {
        int compareShowUpTime = ((Student)comparestu).getShowupTime();
        //  For Ascending order
        return this.showupTime - compareShowUpTime;

    }

	@Override
	public String toString() {
		int ID = this.getID();
		boolean hasE = this.getHasExam();
		String ST = Time.MinutesToTime(this.getShowupTime());
		String IAT =  Time.MinutesToTime(this.getIntendedArrivalTime());
		boolean c = this.isCatch;
		String a = String.format("ID: %s\tHas Exam: %s\tshowup Time: %s\tIntended Arrival Time: %s\tCatch: %s", 
                    ID, hasE, ST, IAT, c);
		return a;
		

	}
	
	
    @Override
    protected Object clone() throws CloneNotSupportedException {
    	//shallow copy
        return super.clone();
    }
    
    //deep copy
//    public Student clone(){
//    	Student p = new Student();
//        p.hasExam = this.hasExam;
//        p.id = this.id;
//        p.intendedArrivalTime = this.intendedArrivalTime;
//        p.isCatch = this.isCatch;
//        p.showupTime = this.showupTime;
//        return p;
//    }

}