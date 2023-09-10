package sam.song.hanteo_codingtest.coinCombine;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class CoinCombineTest {

    @Test
    @DisplayName("주어진 코인의 조합으로 목표 값을 만드는 경우의 수 구하기")
    public void CoinCombineTest(){
        //given
        int q1Sum = 4;
        int[] q1Coins = {1,2,3};

        int q2Sum = 10;
        int[] q2Coins = {2,4,6};
        // when then

        Assertions.assertEquals(4, coinCombine(q1Sum, q1Coins));
        Assertions.assertEquals(5, coinCombine(q2Sum, q2Coins));
    }



    public int coinCombine(int sum, int[] coins){
        int[] coinCount = new int[sum + 1];
        coinCount[0] = 1;


        for(int i = 0; i < coins.length; i++){
            for(int j = coins[i]; j <= sum; j++){
//                System.out.printf("coins[i]=%d, j=%d cointCount=[",coins[i], j);
//                Arrays.stream(coinCount).forEach(c -> System.out.printf("%d ",c));
                System.out.println("]");
                coinCount[j] += coinCount[j - coins[i]];
            }
        }


        return coinCount[sum];
    }
}
