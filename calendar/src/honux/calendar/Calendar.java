package honux.calendar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class Calendar {

	private static final int[] MAX_DAYS = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	private static final int[] LEAP_MAX_DAYS = { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	private static final String SAVE_FILE = "calendar.dat";
	private HashMap <Date, PlanItem> planMap;
	
	public Calendar() {
		planMap = new HashMap<Date, PlanItem>();
		File f = new File(SAVE_FILE);
		if (!f.exists()) {
			System.err.println("no save file");
			return;
		}
			
		try {
			Scanner s = new Scanner(f);
			while(s.hasNext()) {
				String line = s.nextLine();
				String[] words = line.split(",");
				String date = words[0]; 
				String detail = words[1].replaceAll("\"", "");
				PlanItem p = new PlanItem(date, detail);
				planMap.put(p.getDate(), p);
			}
			s.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @param date ex: "2017-06-20"
	 * @param plan
	 * @throws ParseException 
	 */
	public void registerPlan(String strDate, String plan) {
		PlanItem p = new PlanItem(strDate, plan);
		planMap.put(p.getDate(), p);
		
		File f = new File(SAVE_FILE);
		String item = p.saveString();
		try {
			FileWriter fw = new FileWriter(f, true);
			fw.write(item);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public PlanItem searchPlan(String strDate) {
		Date date = PlanItem.getDatefromString(strDate);
		return planMap.get(date);
	}
	public boolean isLeapYear(int year) {
		if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0))
			return true;
		else
			return false;
	}

	public int getMaxDaysOfMonth(int year, int month) {
		if (isLeapYear(year)) {
			return LEAP_MAX_DAYS[month - 1];
		} else {
			return MAX_DAYS[month - 1];
		}
	}

	public void printCalendar(int year, int month) {
		System.out.printf("    <<%d년 %d월>>\n", year, month);
		System.out.println(" SU MO TU WE TH FR SA");
		System.out.println("---------------------");

		int maxDay = getMaxDaysOfMonth(year, month);

		//get weekday automatically
		int weekday = getWeekDay(year,month,1);
		
		// print blank space
		for (int i = 0; i < weekday; i++) {
			System.out.printf("   ");
		}

		for (int i = 1; i <= 7 - weekday; i++) {
			System.out.printf("%3d", i);
		}
		
		System.out.println();
		
		int count = 0;
		for (int i = (7 - weekday + 1); i <= maxDay; i++) {
			System.out.printf("%3d", i);
			count ++;
			if (count % 7 == 0)
				System.out.println();
		}

		System.out.println();
		System.out.println();
	}

	private int getWeekDay(int year, int month, int day) {
		int syear = 1970;
		final int STANDARD_WEEKDAY = 4;//1970/Jan/1st = Thursday
		
		int count = 0;
		
		for (int i = syear; i < year ; i++) {
			int delta = isLeapYear(i) ? 366 :365;
			count += delta;
		}
		
		//System.out.println(count);
		for(int i = 1; i < month; i++) {
			int delta = getMaxDaysOfMonth(year, i);
			count += delta;
		}
		
		count += day -1;
		int weekday = (count + STANDARD_WEEKDAY) % 7;
		return weekday;
	}

	//simple test code here
	public static void main(String[] args) throws ParseException {
		Calendar c = new Calendar();
//		c.getWeekDay(1972, 1, 1);
		System.out.println(c.getWeekDay(1970, 1, 1) == 4);
		System.out.println(c.getWeekDay(1971, 1, 1) == 5);
		System.out.println(c.getWeekDay(1972, 1, 1) == 6);
		System.out.println(c.getWeekDay(1973, 1, 1) == 1);
		System.out.println(c.getWeekDay(1974, 1, 1) == 2);
		c.registerPlan("2017-06-23", "Let's eat beef!");
		System.out.println(c.searchPlan("2017-06-23").equals("Let's eat beef!"));
		
	}
	
//	public void printSampleCalendar31() {
//		System.out.println("일 월 화 수 목 금 토");
//		System.out.println("--------------------");
//		System.out.println(" 1  2  3  4  5  6  7");
//		System.out.println(" 8  9 10 11 12 13 14");
//		System.out.println("15 16 17 18 19 20 21");
//		System.out.println("22 23 24 25 26 27 28");
//		System.out.println("29 30 31");
//	}
//
//	public void printSampleCalendar30() {
//		System.out.println("일 월 화 수 목 금 토");
//		System.out.println("--------------------");
//		System.out.println(" 1  2  3  4  5  6  7");
//		System.out.println(" 8  9 10 11 12 13 14");
//		System.out.println("15 16 17 18 19 20 21");
//		System.out.println("22 23 24 25 26 27 28");
//		System.out.println("29 30");
//	}
//
//	public void printSampleCalendar28() {
//		System.out.println("일 월 화 수 목 금 토");
//		System.out.println("--------------------");
//		System.out.println(" 1  2  3  4  5  6  7");
//		System.out.println(" 8  9 10 11 12 13 14");
//		System.out.println("15 16 17 18 19 20 21");
//		System.out.println("22 23 24 25 26 27 28");
//	}

//	public static void main(String[] args) {

		// 입력받은 월의 최대 일수 출력하기
//		System.out.println("최대 일수를 알고 싶은 달은?");
//		Scanner scanner = new Scanner(System.in);
//		int number = scanner.nextInt();
//		
//		int[] Days = new int[13];
//		Days[1] = 31;
//		Days[3] = 31;
//		Days[5] = 31;
//		Days[7] = 31;
//		Days[8] = 31;
//		Days[10] = 31;
//		Days[12] = 31;
//		Days[2] = 28;
//		Days[4] = 30;
//		Days[6] = 30;
//		Days[9] = 30;
//		Days[11] = 30;
//				 		
//		System.out.println(number + "월의 최대 일수는 " + Days[number]);
//		scanner.close();

//		Scanner scanner = new Scanner(System.in);
//		Calendar cal = new Calendar();
//		System.out.println("반복횟수를 입력하세요.");
//		int repeat = scanner.nextInt();
//		int i = 0;
//		int[] months = new int[repeat];
//		while (i < repeat) {
//			System.out.println("달을 입력하세요");
//			int month = scanner.nextInt();
//			months[i] = month;
//			i ++;
//		}
//		int j = 0;
//		while (j < repeat) {
//			System.out.printf("%d월은 %d일까지 있습니다.\n", months[j], cal.getMaxDaysOfMonth(months[j]));
//			j ++;
//		}
//		scanner.close();

//		Scanner scanner = new Scanner(System.in);
//		Calendar cal = new Calendar();
//
//		System.out.println("반복횟수를 입력하세요.");
//		int repeat = scanner.nextInt();
//		
//		int[] months = new int[repeat];
//		for (int i = 0; i < repeat; i++) {
//			System.out.println("달을 입력하세요");
//			int month = scanner.nextInt();
//			System.out.printf("%d월은 %d일까지 있습니다.\n", month, cal.getMaxDaysOfMonth(month));	
//		}
//		
//		System.out.println("Bye~");
//		scanner.close();
//		cal.printSampleCalendar();

		// 반복문 사용 - while
//		String PROMPT = "cal> ";
//		Scanner scanner = new Scanner(System.in);
//		Calendar cal = new Calendar();
//		
//		int month = 1;
//		
//		while (true) {
//			System.out.println("달을 입력하세요");
//			System.out.print(PROMPT);
//			month = scanner.nextInt();
//			if (month < 1) {
//				break;
//			}
//			
//			if (month > 12) {
//				continue;
//			}
//			System.out.printf("%d월은 %d일까지 있습니다.\n", month, cal.getMaxDaysOfMonth(month));
//		}
//		System.out.println("Bye~");
//		scanner.close();

		// 가상의 달력 찍기
//		String PROMPT = "> ";
//		Scanner scanner = new Scanner(System.in);
//		Calendar cal = new Calendar();
//
//		int month = 1;
//
//		while (true) {
//			System.out.println("월을 입력하세요");
//			System.out.print(PROMPT);
//			month = scanner.nextInt();
//			if (month < 1) {
//				break;
//			}
//
//			if (month > 12) {
//				continue;
//			}
//			if (cal.getMaxDaysOfMonth(month) == 31) {
//				cal.printSampleCalendar31();
//			} else if (cal.getMaxDaysOfMonth(month) == 28) {
//				cal.printSampleCalendar28();
//			} else {
//				cal.printSampleCalendar30();
//			}
//
//		}
//		System.out.println("Bye~");
//		scanner.close();
//	}

}
