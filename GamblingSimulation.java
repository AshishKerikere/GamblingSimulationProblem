import java.util.Scanner;
public class GamblingSimulation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Stakes ");
        int stakes = scanner.nextInt();
        System.out.println("Enter the Bet Amount");
        int betAmount = scanner.nextInt();
        System.out.println("Enter the Percentage of Swing from 1 to 100");
        int swingPercentage = scanner.nextInt();

        int maxSwing = (int)((swingPercentage*1.0/100.0)*stakes);

        int netResultOfGambling = 0;

        int daysOfPlayInMonth = 20;

        netResultOfGambling = bettingMonthResult(stakes, betAmount, maxSwing, daysOfPlayInMonth);
        System.out.println("Net Result of Gambling for " +daysOfPlayInMonth +"days of play =" +netResultOfGambling);

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

    public static int bettingDayResult(int stakes, int betAmount, int maxSwing){
        int upSwing = 0;
        int downSwing = 0;

        int currentHoldingValue = stakes;
        int previousHoldingValue;

        while (upSwing < maxSwing && downSwing < maxSwing ){
            previousHoldingValue = currentHoldingValue;
            currentHoldingValue = bettingResult(previousHoldingValue, betAmount);
            if (currentHoldingValue > previousHoldingValue){
                upSwing++;
            }
            else {
                downSwing++;
            }
        }

        return currentHoldingValue;
    }

    public static int bettingMonthResult(int stakes, int betAmount, int maxSwing, int daysOfPlayInMonth){
        int netResultOfGambling = 0;
        for (int i = 1; i <= daysOfPlayInMonth; i++){
            int resultantValueAtTheEndOfDay =  bettingDayResult(stakes, betAmount, maxSwing);
            int netProfitForTheDay = resultantValueAtTheEndOfDay - stakes;
            netResultOfGambling = netResultOfGambling + netProfitForTheDay;
        }
        return netResultOfGambling;
    }
}
