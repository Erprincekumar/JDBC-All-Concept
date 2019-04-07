package DateFormateInser;

import java.text.SimpleDateFormat;

public class Date1 {

	public static void main(String[] args) throws Exception  {
	  String dat="10-11-2010";
	  
	  SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-YYYY");
       java.util.Date  d=sdf.parse(dat);
       System.out.println("Date:"+d);
	 
	 
	}

}
