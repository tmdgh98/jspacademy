package myBank;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;

public class DateTest {
	public static void main(String[] args) {
		Date today = new Date();
		long ldate = today.getTime();
		System.out.println(ldate);
		long cdate = System.currentTimeMillis();
		System.out.println(cdate);
		
		Calendar cal = Calendar.getInstance();
		System.out.println(cal);
		
		System.out.println(cal.get(1));
		
		System.out.println(cal.get(Calendar.DAY_OF_MONTH));
		System.out.println(cal.get(Calendar.MONTH)+1);
		System.out.println(cal.get(Calendar.DAY_OF_WEEK));
		
		//날짜 계산
		LocalDate ld = LocalDate.now();
		LocalDate a = LocalDate.of(2020, 12, 10);
		
		System.out.println(ld.isAfter(a));
		System.out.println("100일뒤 : "+ld.plusDays(100));
		
		//시간계산
		LocalTime lt = LocalTime.now();
		System.out.println("100분뒤 : "+lt.plusMinutes(100));
		
		System.out.println(Period.between(LocalDate.of(2020, 12, 25), LocalDate.now()));
		
		System.out.println(ld);
		System.out.println(lt);
		String aa = ld.toString().replaceAll("-", "") + lt.toString().replaceAll(":", "").substring(0,7);
		aa = aa.replace(".", "");
		System.out.println(aa);
		
		String bb = Long.toString(System.currentTimeMillis());
		System.out.println(bb.substring(4));
	}
}
