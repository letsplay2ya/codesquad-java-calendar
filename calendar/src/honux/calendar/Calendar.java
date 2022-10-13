package honux.calendar;

import java.util.Scanner;

public class Calendar {

	private static final int[] MAX_DAYS = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	public int getMaxDaysOfMonth(int month) {
		return MAX_DAYS[month - 1];
	}

	public void printCalendar(int year, int month) {
		System.out.printf("    <<%4d년%3d월>>\n", year, month);
		System.out.println(" SU MO TU WE TH FR SA");
		System.out.println("---------------------");
		
		int maxDay = getMaxDaysOfMonth(month);
		
		for(int i = 1; i <= maxDay; i++) {
			System.out.printf("%3d", i);
			if(i % 7 == 0)
				System.out.println();
		}
		
		System.out.println();
//		System.out.println(" 1  2  3  4  5  6  7");
//		System.out.println(" 8  9 10 11 12 13 14");
//		System.out.println("15 16 17 18 19 20 21");
//		System.out.println("22 23 24 25 26 27 28");
//		System.out.println("29 30 31");
	}
	
	public void printSampleCalendar31() {
		System.out.println("일 월 화 수 목 금 토");
		System.out.println("--------------------");
		System.out.println(" 1  2  3  4  5  6  7");
		System.out.println(" 8  9 10 11 12 13 14");
		System.out.println("15 16 17 18 19 20 21");
		System.out.println("22 23 24 25 26 27 28");
		System.out.println("29 30 31");
	}
	
	public void printSampleCalendar30() {
		System.out.println("일 월 화 수 목 금 토");
		System.out.println("--------------------");
		System.out.println(" 1  2  3  4  5  6  7");
		System.out.println(" 8  9 10 11 12 13 14");
		System.out.println("15 16 17 18 19 20 21");
		System.out.println("22 23 24 25 26 27 28");
		System.out.println("29 30");
	}
	
	public void printSampleCalendar28() {
		System.out.println("일 월 화 수 목 금 토");
		System.out.println("--------------------");
		System.out.println(" 1  2  3  4  5  6  7");
		System.out.println(" 8  9 10 11 12 13 14");
		System.out.println("15 16 17 18 19 20 21");
		System.out.println("22 23 24 25 26 27 28");
	}
	
	public static void main(String[] args) {

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
		String PROMPT = "> ";
		Scanner scanner = new Scanner(System.in);
		Calendar cal = new Calendar();
		
		int month = 1;
		
		while (true) {
			System.out.println("월을 입력하세요");
			System.out.print(PROMPT);
			month = scanner.nextInt();
			if (month < 1) {
				break;
			}
			
			if (month > 12) {
				continue;
			}
			if (cal.getMaxDaysOfMonth(month) == 31) {
				cal.printSampleCalendar31();
			} else if (cal.getMaxDaysOfMonth(month) == 28) {
				cal.printSampleCalendar28();
			} else {
				cal.printSampleCalendar30();
			}
				
		}
		System.out.println("Bye~");
		scanner.close();
	}

}
