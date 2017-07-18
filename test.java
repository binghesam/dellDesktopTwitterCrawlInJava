package twitter4JBing;

//import writeStringToFile;

import java.util.*;

import java.time.ZonedDateTime;;
public class test {
	public static void main(String[] args) throws Exception{
//		System.out.println("testSomething");
//		String pathDirectory="./test.txt";
//		String test1="1 sentence";
//		String test2="2 sentence";
//		writeStringToFile.writeStringToFileProcess(pathDirectory,test1);
//		System.out.println("1 wrote");
//		writeStringToFile.writeStringToFileProcess(pathDirectory,test2);
//		System.out.println("2 wrote");
		
		//LocalDateTime a=new LocalDateTime();
		ZonedDateTime current=ZonedDateTime.now();
		
		int year=current.getYear();
		String month=String.format("%02d", current.getMonthValue());
		String day= String.format("%02d", current.getDayOfMonth());
		String hour=String.format("%02d", current.getHour());
		String minut=String.format("%02d", current.getMinute());
		System.out.println(year+month+day+hour+minut);
		
		
//		System.out.println(ZonedDateTime.now());
//		System.out.println(ZonedDateTime.now().getYear());
//		System.out.println(String.format("%02d", ZonedDateTime.now().getMonthValue()));
//		System.out.println(String.format("%02d", ZonedDateTime.now().getDayOfMonth()));
//		System.out.println(ZonedDateTime.now().getHour());
//		System.out.println(ZonedDateTime.now().getMinute());
		
	}
}
