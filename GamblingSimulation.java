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

        System.out.println("Enter the Number of Days of Gambling in a Month");
        int daysOfPlayInMonth = scanner.nextInt();

        netResultOfGambling = bettingMonthResult(stakes, betAmount, maxSwing, daysOfPlayInMonth);
        System.out.println("Net Result of Gambling for " +daysOfPlayInMonth +"days of play =" +netResultOfGambling);

        determineToPlayNextMonth(netResultOfGambling);
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

    public static int[] printBettingMonthRecord(int stakes, int betAmount, int maxSwing, int daysOfPlayInMonth){
        int[] bettingDaysRecord = new int[daysOfPlayInMonth];
        for (int i = 0; i < daysOfPlayInMonth; i++){
            int resultantValueAtTheEndOfDay =  bettingDayResult(stakes, betAmount, maxSwing);
            int netProfitForTheDay = resultantValueAtTheEndOfDay - stakes;
            bettingDaysRecord[i] = netProfitForTheDay;
        }

        System.out.println("Day\tWinnings or Losses");
        for (int i = 0; i < daysOfPlayInMonth; i++){
            System.out.println((i + 1) +"\t" +bettingDaysRecord[i]);
        }

        return bettingDaysRecord;
    }

    public static void printLuckyAndUnluckyDays(int[] bettingDaysRecord){
        int maxiximumWinningDay = 0;
        int maximumWinningAmount = bettingDaysRecord[0];
        int maxiximumLoosingDay = 0;
        int maximumLoosingAmount = bettingDaysRecord[0];

        for (int i = 1; i<bettingDaysRecord.length; i++){
            if (bettingDaysRecord[i] > maximumWinningAmount){
               maximumWinningAmount = bettingDaysRecord[i];
               maxiximumWinningDay = i;
            }

            if (bettingDaysRecord[i] < maximumLoosingAmount){
                maximumLoosingAmount = bettingDaysRecord[i];
                maxiximumLoosingDay = i;
            }
        }

        System.out.println("The Luckiest day is " +(maxiximumWinningDay+1) +" with a win of " +maximumWinningAmount);
        System.out.println("The UnLuckiest day is " +(maxiximumLoosingDay+1) +" with a loss of " +maximumLoosingAmount);
    }

    public static void determineToPlayNextMonth(int netResultOfGambling ) {
        if (netResultOfGambling > 0){
            System.out.println("Continue Playing Next Month");
        }
        else {
            System.out.println("Stop Playing Next Month");
        }
    }
}
