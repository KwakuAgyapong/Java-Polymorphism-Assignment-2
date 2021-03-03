package javapolymorphism2;

public class Q1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
Staff personnel = new Staff();
personnel.payday();

	}

}

class Staff {
	StaffMember[] staffList;
	
	public Staff () {
		staffList = new StaffMember[8];
		staffList [0] = new Executive ("Sam", "123 East Legon", "020-046-6589", "1166558", 2423.07);
		staffList [1] = new Employee ("Marian", "456 Osu Accra ", "0302-046-989", "1154798", 1005.07);
		staffList [2] = new Executive ("Marty", "766 Cantonments Accra", "024-485-6789", "1146565", 1320.07);
		staffList [3] = new Hourly ("Gloria", "773 Nungua", "0302-450-6789", "1125661", 30.03);
		staffList [4] = new Volunteer ("Mark", "22 East Legon", "020-484-6789");
		staffList [5] = new Commission ("Chrystal", "200 Nima Accra", "026-588-6322", "1125661", 10.33, 0.2);
		staffList [6] = new Commission ("Billy", "235 West Hills Accra", "026-458-7852", "1101258", 6.25, 0.2);
		staffList [7] = new Commission ("Pam", "200 East Legon", "054-759-4582", "1110447", 9.75, 0.2);
		
		
		((Executive)staffList[0]).awardBonus(500.00);
		((Hourly)staffList[3]).addHours(4);
		((Commission)staffList[5]).addSales(900);
		((Commission)staffList[5]).commissionRate(0.5);
		((Hourly)staffList[6]).addHours(35);
		((Commission)staffList[6]).addSales(400);
		((Hourly)staffList[7]).addHours(40);
		((Commission)staffList[7]).addSales(950);
		
	}

	public void payday() {
		double amount;
		for (int count=0; count < staffList.length; count++) {
			System.out.println (staffList[count]);
			amount = staffList[count].pay();
			
			if (amount == 0.0)
				System.out.println ("Thanks");
			else
				System.out.println ("Paid: " + amount);
			
			System.out.println ();
		}
	}

abstract class StaffMember {
	protected String name;
	protected String address;
	protected String phone;
	
	StaffMember(String name, String address, String phone) {
		this.name = name;
		this.address = address;
		this.phone = phone;
	}
	public String toString() {
		String result = "Name: " + name + "\n";
		result += "Address: " + address + "\n";
		return result += "Phone: " + phone;
	}
	public abstract double pay();
}

	class Volunteer	extends StaffMember {
		
		public Volunteer (String name, String address, String phone) {
		super(name, address, phone);
		}
		public double pay() {
			return 0.0;
		}
}
 class Employee extends StaffMember {
	protected String socialSecurityNumber;
	protected double payRate;

	public Employee (String name, String address, String phone,String socialSecurityNumber, double payRate) {
		super(name, address, phone);
		this.socialSecurityNumber = socialSecurityNumber;
		this.payRate= payRate;
	}
	public String toString() {
		String result = super.toString();
		result += "\nSocial Security Number: " + socialSecurityNumber;
		return result;
	}
	public double pay() {
		return payRate;
	}
}
class Executive extends Employee {
	private double bonus;
	
	public Executive (String name, String address, String phone,String socialSecurityNumber, double payRate) {
		super(name, address, phone, socialSecurityNumber, payRate);
		bonus = 0;
	}
	public void awardBonus (double bonus) {
		bonus = this.bonus;
	}
	public double pay() { 
		double payment = super.pay() + bonus;
		bonus =  0;
		return payment;
	}
}
class Hourly extends Employee {
	private int hoursWorked;
	
	public Hourly (String name, String address, String phone, String socialSecurityNumber, double payRate) {
		super(name, address, phone, socialSecurityNumber, payRate);
		hoursWorked = 0;
	}
	public void addHours (int moreHours) {
		hoursWorked += moreHours;
	}
	public double pay() {
		double payment = payRate * hoursWorked;
		hoursWorked = 0;
		return payment;
	}
	public String toString() {
		String result = super.toString();
		result += "\nCurrent hours: " + hoursWorked;
		return result;
	}
}
class Commission extends Hourly {
	public double totalSales;
	public double commissionRate;
	
	public Commission (String name, String address, String phone,String socialSecurityNumber, double payRate, double commissionRate) {
		super(name, address, phone, socialSecurityNumber,payRate);
		totalSales = 0;
	}
	public void commissionRate(double commissionRate) {
		commissionRate = (totalSales / 100);
	}
	public void addSales(double moreSales) {
		totalSales += moreSales;
	}
	public double pay() { 
		double payment = super.pay() + commissionRate;
		totalSales =  0;
		return payment;
}
	public String toString() {
		String result = super.toString();
		result += "\nTotal Sales: " + totalSales;
		return result;
}
}
}