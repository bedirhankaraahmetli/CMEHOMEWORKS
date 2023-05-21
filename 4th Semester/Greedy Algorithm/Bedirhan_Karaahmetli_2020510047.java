import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Bedirhan_Karaahmetli_2020510047 {
    public static int Greedy(int[] y, int[] salaries, int n, int p, int c) {
        int totalCost = 0;
        int unrentedPlayers = 0;
        for (int i = 1; i <= n; i++) {
            if (p < y[i]) { // If players to promote smaller than demand for i th year rent a coach
                int coachCost = c * (y[i] - p - unrentedPlayers); // Need demand - yearly player to promote - unrented players from past years
                unrentedPlayers = 0; // Resetting unrentedPlayers bcs they used
                totalCost += coachCost;
            }
            else if (y[i] < p) { // If demand is smaller than players to promote for i th year
                int minimum = 0;
                if (i != y.length-1 && y[i+1] > p) { // Check next year if next year's demand bigger than p
                    minimum = 999999999;
                    unrentedPlayers = Math.min(p-y[i],y[i+1]-p); // Comparison between how many extra player can be promoted and pay salary to them and next year how many players needed
                    int cost = 0;
                    for (int j = 0; j <= unrentedPlayers; j++){// Checking all possible combinations of #keeping player and #renting coach
                        if (j == 0)
                            cost = c * (unrentedPlayers-j); // If j = 0, that means for next year buying coach is better choice than keeping players
                        else {
                            cost = salaries[j] + c * (unrentedPlayers-j); // If j different from 0, check every possibilities like 1 promote 2 coach or 2 promote 1 coach etc.
                        }
                        if (minimum > cost) { // Checking the minimum value
                            minimum = cost;
                        }
                    }
                }
                totalCost += minimum;
            }
        }
        return totalCost;
    }// end of Greedy
    public static int[] readTxt(String txtName) throws FileNotFoundException { //Creating int[] from given txt:txtName
        File file = new File(txtName);
        int i = 0;
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()){ //Reading the txt for finding the raw size to create array's size
            sc.nextLine();
            ++i;
        }
        sc.close();
        int[] arr = new int[i];
        int j = 1;
        Scanner scanner = new Scanner(file);
        scanner.nextLine();
        while (scanner.hasNextLine()){
            String[] string = scanner.nextLine().split("\t");
            arr[j] = Integer.parseInt(string[1]);
            ++j;
        }
        return arr;
    }// end of readTxt
    public static void main(String[] args) throws FileNotFoundException {
        int n=24, p=6, c=7; // Initial values for n = year, p = players that will be promoted, c = coach costs

        // READING FILES
        int[] y = readTxt("yearly_player_demand.txt"); // Assigning demands to integer array y[] for each year
        int[] salary = readTxt("players_salary.txt"); // Assigning total salary for 1...n players salary[]

        int cost = Greedy(y,salary,n,p,c); // Calculating the minimum cost for n years using Greedy Algorithm
        System.out.println("Greedy Results: " + cost); // Printing cost
    }//end of main
}