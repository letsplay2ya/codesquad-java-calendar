package honux.calendar;
import java.util.Scanner;

public class Calendar {

	public static void main(String[] args) {
//		System.out.println("일  월  화 수  목  금 토");
//		System.out.println("--------------------");
//		System.out.println(" 1  2  3  4  5  6  7");
//		System.out.println(" 8  9 10 11 12 13 14");
//		System.out.println("15 16 17 18 19 20 21");
//		System.out.println("22 23 24 25 26 27 28");
	
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
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("달을 입력하세요");
		int month = scanner.nextInt();
		
		int[] maxDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		
		System.out.printf("%d월은 %d일까지 있습니다.\n", month, maxDays[month - 1]);
		scanner.close();
	}

}
