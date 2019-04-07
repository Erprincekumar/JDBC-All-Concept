package DateFormateInser;

import java.sql.Date;

public class ConvertStringDateIntoSqlDate {

	public static void main(String[] args) {
		
		String d2="2010-02-05";
		 java.sql.Date d=java.sql.Date.valueOf(d2);
		 System.out.println("SQL Date is:"+d2);

	}

}
