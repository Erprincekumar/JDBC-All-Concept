package DateFormateInser;

import java.text.SimpleDateFormat;
import java.util.Scanner;

public class SimpleDateFormate {

	public static void main(String[] args) throws Exception  {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the Date(MM-DD-YYYY)");
		String date=sc.next();
		SimpleDateFormat sdf=new SimpleDateFormat("DD-MM-YYYY");
		java.util.Date ud1=sdf.parse(date);
		
		System.out.println("Date:"+ud1);
	}

}
