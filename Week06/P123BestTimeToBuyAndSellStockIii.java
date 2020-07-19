//给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。 
//
// 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。 
//
// 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。 
//
// 示例 1: 
//
// 输入: [3,3,5,0,0,3,1,4]
//输出: 6
//解释: 在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
//     随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。 
//
// 示例 2: 
//
// 输入: [1,2,3,4,5]
//输出: 4
//解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。   
//     注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。   
//     因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
// 
//
// 示例 3: 
//
// 输入: [7,6,4,3,1] 
//输出: 0 
//解释: 在这个情况下, 没有交易完成, 所以最大利润为 0。 
// Related Topics 数组 动态规划

package algorithm10.week06;

import java.util.Arrays;

public class P123BestTimeToBuyAndSellStockIii {

    public static void main(String[] args) {
        Solution solution = new P123BestTimeToBuyAndSellStockIii().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 暴力解法
        //可以通过定义dp状态数组来解决
        public int maxProfit(int[] prices) {
            int result = 0;
            //分成2个区间，每个区间一笔交易。与只完成一笔交易比较，取最大值
            for (int i = 1; i < prices.length; i++) {
                int[] leftPrices = Arrays.copyOfRange(prices, 0, i);
                int[] rightPricces = Arrays.copyOfRange(prices, i - 1, prices.length);
                result = Math.max(result,maxOneProfit(leftPrices)+maxOneProfit(rightPricces));
            }
            return Math.max(result, maxOneProfit(prices));
        }

        public int maxOneProfit(int[] prices) {
            int result = 0, minPrice = Integer.MAX_VALUE;
            for (int i = 0; i < prices.length; i++) {
                minPrice = prices[i] < minPrice ? prices[i] : minPrice;
                result = (prices[i] - minPrice > result) ? prices[i] - minPrice : result;
            }
            return result;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}

