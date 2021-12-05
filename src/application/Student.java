
package application;
/**
 * @author azeez + ahmed
 *
 */
public class Student implements Comparable<Student>, Cloneable{
	
	//Variables of student
	int id;				
	boolean hasExam;	
	boolean isCatch;
	int intendedArrivalTime;
	int showupTime;	
	
	//Constructors
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

	// ID
	public void setID(int id) {
		this.id = id;
	}
	
	public int getID() {
		return this.id;
	}

	//hasExam
	public void setHasExam(boolean HX) {
		this.hasExam = HX;
	}
	
	public boolean getHasExam() {
		return this.hasExam;
	}
	//IsCatch
	public void setIsCatch(boolean isCatch ) {
		this.isCatch = isCatch; 
		
	}
	
	public boolean isCatch() {
		return isCatch;
	}
	//ShowupTime
	public void setShowupTime(int mins) {
		showupTime = mins;
	}
	
	public int getShowupTime() {
		return showupTime;
	}
	
	//IntendedDepartureTime
	public void setIntendedArrivalTime(int mins) {
		intendedArrivalTime = mins;
	}
	
	public int getIntendedArrivalTime() {
		return intendedArrivalTime ;
	}
	
	public int randomID() {

		int id = (int) (Math.random() * (1000000) ) + 1000000;
		return id;
	}
	
	public boolean randomExamCondition() {
		boolean Exam = Math.random() < 0.95? false:true;
		return Exam;
	}
	
	public int randomShowupTime() {
		int randomMinutes =  getIntendedArrivalTime() - 60 + (int) (Math.random() * 30) ;
		return randomMinutes;
	}
	
	public int randomIntendedArrivalTime() {
		  int randomTime = (int) (Math.random() * ( 32 ) + 1) * 30;
		  if (randomTime == 960) {
			  return randomTime;
		  } else if (randomTime < 60) {
			  return randomTime + 60;
		  }else 
			  return randomTime;
	}

	@Override
	public int compareTo(Student comparestu)
    {
        int compareShowUpTime = ((Student)comparestu).getShowupTime();
        //  For Ascending order
        return this.showupTime - compareShowUpTime;

    }

	// From this line is just for testing you can delete it-------------
	@Override
	public String toString() {
//		return "Student ["id=" + id + ",      hasExam=" + hasExam + ",    intendedArrivalTime=" + getTime(intendedArrivalTime) + ",      showupTime=" + getTime(showupTime) + "]";
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
        return super.clone();
    }
    
    
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