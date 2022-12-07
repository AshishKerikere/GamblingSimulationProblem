import java.util.Scanner;
public class GamblingSimulation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Stakes ");
        int stakes = scanner.nextInt();
        System.out.println("Enter the Bet Amount");
        int betAmount = scanner.nextInt();

        stakes = bettingResult(stakes, betAmount);
        System.out.println("The resultant stakes after one round of betting " +stakes);

    }

    public static int bettingResult(int stakes, int betAmount){
        int random = (int) Math.floor(Math.random() * 2 + 1);
        int wonBet = 1;
        int lostBet = 2;

        switch (random) {
            case 1:
                stakes = stakes + betAmount;
                break;
            case 2:
                stakes = stakes - betAmount;
                break;
        }
        return stakes;
    }
}
