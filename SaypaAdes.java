// Saypa Ades plants grow and die depending on some conditions

import java.util.*;

public class SaypaAdes {
	public static String[][] plot = new String[11][11];
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		String[] choices = {"Plant Saypa Ades", "Remove all plants", "View plants (current)", "View plants (next day)", "Exit"};

		clearPlot();

		while (true) {
			System.out.println("\nCHOOSE FROM THE FOLLOWING:");
			for (int x = 1; x <= choices.length; x++)
				System.out.println(x + ". " + choices[x - 1]);

			System.out.print("\nChoose: ");
			int choose = sc.nextInt();

			if (choose == 1) {
				plantSaypaAdes();
			} else if (choose == 2) {
				System.out.println("\nALL PLANTS HAVE BEEN REMOVED");
				clearPlot();
			} else if (choose == 3) {
				System.out.println("\nPLOT W/ SAYPA ADES PLANT (CURRENT DAY)");
				showPlot();
			} else if (choose == 4) {
				System.out.println("\nPLOT W/ SAYPA ADES PLANT (NEXT DAY)");
				conditions();
			} else if (choose == 5) {
				System.exit(0);
			}
		}
	}

	public static void plantSaypaAdes() {
		System.out.print("\nPLANT SAYPA ADES\nHow many spaces will have Saypa Ades plant: ");
		int sizeOfSpaces = sc.nextInt();

		System.out.println("Enter the spaces that will have the Saypa Ades plant:");
		for (int x = 0; x < sizeOfSpaces; x++) {
			int plant = sc.nextInt();
			plot[(plant - 1) / 11][(plant - 1) % 11] = "#";
		}
	}

	public static void showPlot() {
		for (int x = 0; x < plot.length; x++) {
			for (int y = 0; y < plot.length; y++)
				System.out.print(plot[x][y] + " ");

			System.out.println();
		}
	}

	public static void clearPlot() {
		for (int x = 0; x < plot.length; x++)
			for (int y = 0; y < plot.length; y++)
				plot[x][y] = "-";
	}

	public static void conditions() {
		int neighbor1 = 0, neighbor2 = 0, base = 0;
		Stack<Integer> xWillDie = new Stack<Integer>();
		Stack<Integer> yWillDie = new Stack<Integer>();
		Stack<Integer> xWillGiveGrow = new Stack<Integer>();
		Stack<Integer> yWillGiveGrow = new Stack<Integer>();

		for (int x = 0; x < plot.length; x++) {
			for (int y = 0; y < plot.length; y++) {
				if (plot[x][y] == "#") {
					if (x != 0 && y != 0)
						if (plot[x - 1][y - 1] == "#")
							neighbor1++;
					if (x != 0)
						if (plot[x - 1][y] == "#")
							neighbor1++;
					if (x != 0 && y != 10)
						if (plot[x - 1][y + 1] == "#")
							neighbor1++;
					if (y != 0)
						if (plot[x][y - 1] == "#")
							neighbor1++;
					if (y != 10)
						if (plot[x][y + 1] == "#")
							neighbor1++;
					if (x != 10 && y != 0)
						if (plot[x + 1][y - 1] == "#")
							neighbor1++;
					if (x != 10)
						if (plot[x + 1][y] == "#")
							neighbor1++;
					if (x != 10 && y != 10)
						if (plot[x + 1][y + 1] == "#")
							neighbor1++;

					if (neighbor1 < 2 || neighbor1 > 3) {
						xWillDie.push(x);
						yWillDie.push(y);
					}
				}

				if (plot[x][y] == "-") {
					if (x != 0 && y != 0)
						if (plot[x - 1][y - 1] == "#")
							neighbor2++;
					if (x != 0)
						if (plot[x - 1][y] == "#")
							neighbor2++;
					if (x != 0 && y != 10)
						if (plot[x - 1][y + 1] == "#")
							neighbor2++;
					if (y != 0)
						if (plot[x][y - 1] == "#")
							neighbor2++;
					if (y != 10)
						if (plot[x][y + 1] == "#")
							neighbor2++;
					if (x != 10 && y != 0)
						if (plot[x + 1][y - 1] == "#")
							neighbor2++;
					if (x != 10)
						if (plot[x + 1][y] == "#")
							neighbor2++;
					if (x != 10 && y != 10)
						if (plot[x + 1][y + 1] == "#")
							neighbor2++;

					if (neighbor2 == 3) {				
						xWillGiveGrow.push(x);
						yWillGiveGrow.push(y);
					}
				}

				neighbor1 = 0;
				neighbor2 = 0;
			}
		}

		while (base < xWillDie.size()) {
			int x = xWillDie.pop();
			int y = yWillDie.pop();

			if (plot[x][y] == "#")
				plot[x][y] = "-";
		}
		while (base < xWillGiveGrow.size()) {
			int x = xWillGiveGrow.pop();
			int y = yWillGiveGrow.pop();

			if (plot[x][y] == "-")
				plot[x][y] = "#";
		}

		showPlot();
	}
}