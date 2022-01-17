package console.LadderGameV1;

import java.util.Scanner;

class TicTackToe {

	public static final String[] SHAPE_ARR = {"│", "──", "┤", "├"};
	public static final int STANDARD_LINE = 1;
	public static final int BRIDGE_LINE = 3;
	public static final int FINAL_LINE = STANDARD_LINE + BRIDGE_LINE;
	public static final int HEIGHT= 8;

	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int playNum = 0;

		while(true) {

			try {
				playNum = setNumber();
			} catch(Exception e) {
				System.out.println(e.getMessage());
				continue;
			}
			String[][] sadariField = new String[HEIGHT][playNum * FINAL_LINE];
			String[] user = new String[playNum];
			String[] result = new String[playNum];

			setField(sadariField, playNum);
			setInput(user, "플레이어");
			setInput(result, "결과");
			setBridge(sadariField, playNum);
			result(sadariField, playNum, user, result);
			break;
		}
	}////////////main


	private static int setNumber() throws Exception {

		System.out.println("플레이하려는 인원이 몇명입니까?");
		int tmp = Integer.parseInt(sc.nextLine());

		if(tmp <= 0 || tmp > 20) {
			Exception e = new Exception("플레이 인원은 1~20명으로 설정하세요.");
			throw e;
		}

		System.out.println("사다리 갯수가 "+tmp+"개로 설정 되었습니다.");
		return tmp;
	}

	private static void setField(String[][] arr, int num) {

		for(int q = 0 ; q < HEIGHT ; q++) {
			for(int w = 0 ; w < FINAL_LINE * num ; w++) {

				if(w % 4 == STANDARD_LINE - 1) {
					arr[q][w] = SHAPE_ARR[0];
					System.out.print(SHAPE_ARR[0]);
				} else {
					arr[q][w] = "  ";
					System.out.print("  ");
				}
			}
			System.out.println();
		}
	}//setSadari

	private static void setInput(String[] userArr,String msg) {

		for(int q = 0 ; q < userArr.length ; q++) {
			System.out.println((q+1)+"번째 "+msg+"를 입력하세요.");
			userArr[q] = sc.nextLine();
		}
	}

	private static void setBridge(String[][] arr, int num) {
		int lineNumber = arr[0].length / FINAL_LINE;
		int[] checkLine = new int[lineNumber];

		for(int q = 1 ; q < arr.length - 1 ; q++) {
			for(int w = 0 ; w < lineNumber - 1; w++) {

				if((int)(Math.random() * 2) == 0)
					checkLine[w] += checkLine[w] < 3 ? setBridgeDetail(arr , q, w * FINAL_LINE) : 0;
			}

			if(q == arr.length - 2)
				for(int w = 0 ; w < lineNumber - 1 ; w++) {
					if(checkLine[w] == 0) {
						q = 1;
						break;
					}
				}
		}
	}

	private static int setBridgeDetail(String[][] sadari, int q, int w) {

		if(sadari[q][w].equals(SHAPE_ARR[0]) && sadari[q][w + FINAL_LINE].equals(SHAPE_ARR[0])) {
			sadari[q][w] = SHAPE_ARR[3];

			for(int e = 1 ; e < FINAL_LINE + 1 ; e++) {
				if(e < FINAL_LINE) {
					sadari[q][w + e] = SHAPE_ARR[1];
				} else {
					sadari[q][w + e] = SHAPE_ARR[2];
				}
			}
			return 1;
		}
		return 0;
	}

	private static void result(String[][] arr, int sadariNum, String[] user, String[] result) {
		realTimeScreenDraw(arr, "");
		String tmp = "";

		for(int q = 0 ; q < arr[0].length ; q += FINAL_LINE) {
			int x = 0, y = q;

			for(int w = 0 ; w < arr.length ; w++) {
				if(arr[x][y].equals(SHAPE_ARR[3])) y += FINAL_LINE;
				else if(arr[x][y].equals(SHAPE_ARR[2])) y -= FINAL_LINE;

				//"♥"이동 로직
				tmp = arr[x][y];
				arr[x][y] = "♥";
				//realTimeScreenDraw(arr, user[q / FINAL_LINE]);
				//waitTime(250);
				arr[x][y] = tmp;
				x ++;
			}
			//System.out.println(user[q / FINAL_LINE]+"의 결과는 "+result[y / FINAL_LINE]);
			user[q / FINAL_LINE] += "의 결과 -> "+result[y / FINAL_LINE];
			//waitTime(1500);
		}
		//blank();
		System.out.println("────── 결과창 ──────");
		for(String temp : user) {
			System.out.printf("%s %n",temp);
		}
	}

	private static void realTimeScreenDraw(String[][] arr, String user) {

		for(int q = 0 ; q < arr.length ; q++) {
			for(int w = 0 ; w <arr[q].length ; w++) {
				System.out.print(arr[q][w]);
			}
			System.out.println();
		}
	}

}//class
