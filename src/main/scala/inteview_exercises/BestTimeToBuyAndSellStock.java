package inteview_exercises;

/*
You are given an array prices where prices[i] is the price of a given stock on the ith day.

You want to maximize your profit by choosing a single day to buy one stock and choosing a different
day in the future to sell that stock.

Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
 */
public class BestTimeToBuyAndSellStock {
    public static void main(String[] args) {
        int[] arr = new int[]{470,660,118,742,334};
        System.out.println(maxProfit(arr));
    }

    public static int maxProfit(int[] prices) {

        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                int profit = prices[j] - prices[i];
                if (profit > maxProfit) {
                    maxProfit = profit;
                }
            }

        }
        return maxProfit;
    }
}
