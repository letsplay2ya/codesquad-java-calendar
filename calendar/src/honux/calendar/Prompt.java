package honux.calendar;

import java.text.ParseException;
import java.util.Scanner;

public class Prompt {
	
	public void printMenu() {
		System.out.println("+-------------------------------+");
		System.out.println("| 1. 일정 등록		");
		System.out.println("| 2. 일정 검색		");
		System.out.println("| 3. 달력 보기		");
		System.out.println("| h. 도움말 q. 종료");
		System.out.println("+-------------------------------+");
	}
	/**
	 * 
	 * @param week 요일명
	 * @return 0 ~ 6 (0 = Sunday, 6 = Saturday)
	 */
	public int parseDay(String weekday) {
		if (weekday.equals("SU")) return 0;
		else if (weekday.equals("MO")) return 1;
		else if (weekday.equals("TU")) return 2;
		else if (weekday.equals("WE")) return 3;
		else if (weekday.equals("TH")) return 4;
		else if (weekday.equals("FR")) return 5;
		else if (weekday.equals("SA")) return 6;
		else return 0;	
	}

	
	public void runPrompt() throws ParseException {
		printMenu();
		Scanner scanner = new Scanner(System.in);
		Calendar cal = new Calendar();

		while (true) {
			System.out.println("명령(1, 2, 3, h, q)");
			String cmd = scanner.next();
			if(cmd.equals("1")) cmdRegister(scanner, cal);
			else if (cmd.equals("2")) cmdSearch(scanner, cal);
			else if (cmd.equals("3")) cmdCal(scanner, cal);
			else if (cmd.equals("h")) printMenu();
			else if (cmd.equals("q")) break;
		}

		System.out.println("Thank you. Bye~");
		scanner.close();
	}

	private void cmdCal(Scanner s, Calendar c) {
//		Scanner s = new Scanner(System.in);
//		Calendar c = new Calendar();  
//		함수 호출할 때마다 생성 시 비효율적이므로 참조 호출로 대체
		
		int month = 1;
		int year = 2022;
		
		System.out.println("년도을 입력하세요.");
		System.out.print("YEAR> ");
		year = s.nextInt();
		
		System.out.println("월을 입력하세요.");
		System.out.print("MONTH> ");
		month = s.nextInt();
		
		if (month > 12 || month < 1) {
			System.out.println("잘못된 입력입니다.");
			return;
		}
		c.printCalendar(year, month);
		
	}
	private void cmdSearch(Scanner s, Calendar c) {
		System.out.println("[일정검색]");
		System.out.println("날짜를 입력해 주세요.(yyyy-MM-dd)");
		String date = s.next();
		String plan="";
		try {
			plan = c.searchPlan(date);
		} catch (ParseException e) {
			e.printStackTrace();
			System.err.println("일정 검색 중 오류가 발생했습니다.");
		}
		System.out.println(plan);
	}
	private void cmdRegister(Scanner s, Calendar c) throws ParseException {
		System.out.println("[새 일정 등록]");
		System.out.println("날짜를 입력해 주세요.(yyyy-MM-dd)");
		String date = s.next();
		String text = "";
		s.nextLine(); //ignore one newline
		System.out.println("일정을 입력해 주세요.");
		text = s.nextLine();
		
		c.registerPlan(date, text);
	}
	public static void main(String[] args) throws ParseException {
		// 셀 실행
		Prompt p = new Prompt();
		p.runPrompt();
	}

}
