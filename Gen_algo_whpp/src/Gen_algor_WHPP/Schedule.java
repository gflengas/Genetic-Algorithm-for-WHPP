package ai2;

import java.util.Random;

public class Schedule {
    final static int EMP_NUM = 30;
    private Employee[] Empls;
    private double Fitness;
    //Initialize --------------------------------------------------------
    public Schedule() {
    	int countM,countA,countN; //counter for number of people working on each shift each day 
    	int shift; // current employee we are going to assign a shift
    	boolean next;
    	Random rand = new Random();
    	Empls = new Employee[EMP_NUM];
    	for(int Day=0;Day<Employee.SIZE;Day++) {
    		for (int i=0;i<EMP_NUM;i++) {
    			Empls[i] = new Employee();
    			Empls[i].setshift(Day, 0);
    		}
    	}
    	for(int Day=0;Day<Employee.SIZE;Day++) {
    		countM=0;countA=0;countN=0;
    		next=false;
    		while(!next) {
    			shift=rand.nextInt(30);
    			if(Empls[shift].getshift(Day)==0) {
    				if(countM<this.DayAndShiftCover(Day, 1)) {//Set him on morning
    					countM++;
    					Empls[shift].setshift(Day, 1);
    				}
    				else if (countA<this.DayAndShiftCover(Day, 2)) {//Set him on afternoon
    					countA++;
    					Empls[shift].setshift(Day, 2);
    				}
    				else if(countN<this.DayAndShiftCover(Day, 3)) {//Set him on night
    					countN++;
    					Empls[shift].setshift(Day, 3);
    				}
    				//else we leave him at initial 0 for free day 
    			}
    			if(countM==this.DayAndShiftCover(Day, 1) && countA==this.DayAndShiftCover(Day, 2) && countN==this.DayAndShiftCover(Day, 3)) {
    				next=true;
    			}
    		}
    	}
    	
    this.SchedScore();
    this.evaluate();
    }
    
    //Printer we are going to use for our best case at the end 
    public void printSched() {
    	System.out.print("Emp M T W T F S S M T W T F S S\n");
    	for(int i=0;i<EMP_NUM; i++) {
    		System.out.print(" "+(i+1)+" ");
    		if(i<9) {
				System.out.print(" ");
			}
    		for(int j=0;j<Employee.SIZE;j++) {
    			System.out.print(Empls[i].getshift(j)+" ");
    		}
    		System.out.print("\n");
    	}
    	System.out.print("Score: "+Fitness +"\n");
    }
    
    //setter and getter------------------------------------------
    
    public Employee getEmployee(int index) {
    	return Empls[index];
    }
    
    public void setEmployee(int index, Employee Emp) {
        this.Empls[index] = Emp;
    }
    
    
    public double getFitness() {
		return Fitness;
	}

	public void setFitness(double Fitness) {
		this.Fitness = Fitness;
	}
	//----------------------------------------------------------

    //Evaluate hard constraints 
    public int evaluate() {
    	int countM,countA,countN; //counter for number of people working on each shift each day 
    	int cur; // the shift of the current employee
    	int valuate=0;
    	for(int j =0; j< Employee.SIZE;j++) {
    		//reset counters
    		countM=0;
    		countA=0;
    		countN=0;
    		
    		for (int i = 0; i < EMP_NUM; i++) {
            	cur=this.getEmployee(i).getshift(j);
            	if(cur==1) {
            		countM++;
            	}
            	else if (cur==2) {
            		countA++;
            	}
            	else if(cur==3) {
            		countN++;
            	}
            }
    		if(j==0 ||j==1 ||j==7 ||j==8) { //Monday or Tuesday
    			if(countM==10 && countA==10 && countN==5) {
    				valuate++;
    				
    			}
    		}
    		if(j==2 ||j==4 ||j==9 ||j==11) { // Wednesday or Friday
    			if(countM==5 && countA==10 && countN==5) {
    				valuate++;
    			}
    		}
    		if(j==3 ||j==5 ||j==6 ||j==10 ||j==12 ||j==13) { //Thursday,Saturday or Sunday
    			if(countM==5 && countA==5 && countN==5) {
    				valuate++;
    			}
    		}
    		
    	}
        return valuate;
    }
    
    //Calculating the score of the Sched of a sing Employee
    public double ScoreCalculation(Employee Empl) {
    	double score=0.0;
    	int Total_Hours=0,Days,Daysoff;
    	boolean Consec_Days=false,Consec_Nights=false,NightDay=false,AfternoonDay=false,Nightafternoon=false;
    	boolean FourTwo=false,SevenTwo=false,sos=false,oso=false,SaSu=false,SuSa=false,End=false;
    	
    	//Max work hours
    	for(int i=0;i<Employee.SIZE;i++) {
    		if(Empl.getshift(i)==1 || Empl.getshift(i)==2) Total_Hours+=8;
    		else if(Empl.getshift(i)==3) Total_Hours+=10;
    	}
    	if(Total_Hours>70) score+=1000;
    	
    	//Max 7 consecutive days
    	for(int i=0;i<Employee.SIZE-7;i++) {
    		Days=0;
    		for(int j=0;j<=7;j++) {
    			if(Empl.getshift(i+j)!=0) {
    				Days++;
    			}
    		}
    		if(Days>7)Consec_Days=true;
    	}
    	if(Consec_Days) score+=1000;
    	
    	//Max 4 consecutive days
    	for(int i=0;i<Employee.SIZE-4;i++) {
    		Days=0;
    		for(int j=0;j<=4;j++) {
    			if(Empl.getshift(i+j)==3) {
    				Days++;
    			}
    		}
    		if(Days>4)Consec_Nights=true;
    	}
    	if(Consec_Nights) score+=1000;
    	
    	//Night shift, next day morning 
    	for(int i=0;i<Employee.SIZE-1;i++) {
    		Days=0;
    		if(Empl.getshift(i)==3 && Empl.getshift(i+1)==1) {
    			Days++;
    		}
    		if(Days!=0)NightDay=true;
    	}
    	if(NightDay) score+=1000;

    	//afternoon shift, next day morning 
    	for(int i=0;i<Employee.SIZE-1;i++) {
    		Days=0;
    		if(Empl.getshift(i)==2 && Empl.getshift(i+1)==1) {
    			Days++;
    		}
    		if(Days!=0)AfternoonDay=true;
    	}
    	if(AfternoonDay) score+=800;
    	
    	//Night shift, next day afternoon 
    	for(int i=0;i<Employee.SIZE-1;i++) {
    		Days=0;
    		if(Empl.getshift(i)==3 && Empl.getshift(i+1)==2) {
    			Days++;
    		}
    		if(Days!=0)Nightafternoon=true;
    	}
    	if(Nightafternoon) score+=600;
    	
    	//2 days off after 4 consecutive night shifts 
    	for(int i=0;i<Employee.SIZE-6;i++) {
    		Days=0;
    		Daysoff=0;
    		for(int j=0;j<4;j++) {
    			if(Empl.getshift(i+j)==3) {
    				Days++;
    			}
    		}
    		for(int j=4;j<6;j++) {
    			if(Days==4 && Empl.getshift(i+j)==0) {
    				Daysoff++;
    			}
    		}
    		if(Days==4 && Daysoff<2)FourTwo=true;
    	}
    	if(FourTwo) score+=100;
    	
    	//2 days off after 7 consecutive shifts 
    	for(int i=0;i<Employee.SIZE-9;i++) {
    		Days=0;
    		Daysoff=0;
    		for(int j=0;j<7;j++) {
    			if(Empl.getshift(i+j)!=0) {
    				Days++;
    			}
    		}
    		for(int j=7;j<9;j++) {
    			if(Days==4 && Empl.getshift(i+j)==0) {
    				Daysoff++;
    			}
    		}
    		if(Days==7 && Daysoff<2)SevenTwo=true;
    	}
    	if(SevenTwo) score++;
    	
    	//Dont have shift-dayoff-shift 
    	for(int i=0;i<Employee.SIZE-2;i++) {
    		if(Empl.getshift(i)!=0 && Empl.getshift(i+1)==0 && Empl.getshift(i+2)!=0) {
    			sos=true;
    		}
    	}
    	if(sos) score++;
    	
    	//Dont have dayoff-shift-dayoff
    	for(int i=0;i<Employee.SIZE-2;i++) {
    		if(Empl.getshift(i)==0 && Empl.getshift(i+1)!=0 && Empl.getshift(i+2)==0) {
    			oso=true;
    		}
    	}
    	if(oso) score++;
    	
    	//Working Saturday then work Sunday too
    	if((Empl.getshift(5)!=0 && Empl.getshift(6)==0) || (Empl.getshift(12)!=0 && Empl.getshift(13)==0)) {
    			SaSu=true;
    		}
    	
    	if(SaSu) score++;
    	
    	//Working Sunday then work Saturday too
    	if((Empl.getshift(5)==0 && Empl.getshift(6)!=0) || (Empl.getshift(12)==0 && Empl.getshift(13)!=0)) {
			SuSa=true;
		}
    	if(SuSa) score++;
    	
    	//Work only 1 weekend in this period 
    	if((Empl.getshift(5)!=0 && Empl.getshift(6)!=0) && (Empl.getshift(12)!=0 && Empl.getshift(13)!=0)) {
			End=true;
		}
    	if(End) score++;
    	
    	return score;
    }
    
    //Calculating total score aka Fitness of the sched
    public void SchedScore() {
    	double total_score=0;
    	for(int i=0;i<EMP_NUM;i++) {
    		total_score+=ScoreCalculation(this.Empls[i]);
    	}
    	this.setFitness(total_score);
    }
    
    //Random mutation for this Schedule we flip the schedule of 2 Employees base on a random number between [0,EMP_NUM)
    public void mutate() {
        Random rand = new Random();
        Employee emp1 =new Employee();
        Employee emp2 =new Employee();
        int index = rand.nextInt(EMP_NUM);
        emp1=this.getEmployee(index);
        if(index==29) {
        	 emp2=this.getEmployee(index-11);
        }
        else {
            emp2=this.getEmployee(index+1);
        	
        }
        this.setEmployee(index, emp2);
        this.setEmployee(index, emp1);
    }
    //Function for checking The amount of people we need a specific day on a specific shift
    public int DayAndShiftCover(int Day,int Shift) {
    	if(Day==0 ||Day==1 ||Day==7 ||Day==8) {
    		if(Shift==1) {
    			return 10;
    		}
    		else if(Shift==2) {
    			return 10;
    		}
    		else if(Shift==3) {
    			return 5;
    		}
    	}
    	else if(Day==2 ||Day==4 ||Day==9 ||Day==11) {
    		if(Shift==1) {
    			return 5;
    		}
    		else if(Shift==2) {
    			return 10;
    		}
    		else if(Shift==3) {
    			return 5;
    		}
    	}
    	else {
    		if(Shift==1) {
    			return 5;
    		}
    		else if(Shift==2) {
    			return 5;
    		}
    		else if(Shift==3) {
    			return 5;
    		}
    	}
    	return -1;
    }
}
