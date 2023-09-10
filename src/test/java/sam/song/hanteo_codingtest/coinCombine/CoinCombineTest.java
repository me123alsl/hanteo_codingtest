package sam.song.hanteo_codingtest.coinCombine;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;


public class CoinCombineTest {

    @Test
    @DisplayName("주어진 코인의 조합으로 목표 값을 만드는 경우의 수 구하기")
    public void CoinCombineTest() {
        //given
        int q1Sum = 4;
        int[] q1Coins = {1, 2, 3};

        int q2Sum = 10;
        int[] q2Coins = {2, 4, 6};

        int q3Sum = 8;
        int[] q3Coins = {1, 4, 6, 8, 12};
        // when then

        Assertions.assertEquals(4, coinCombine(q1Sum, q1Coins));
        /*
         * 2 2 2 2 2
         * 2 2 2 4
         * 2 2 6
         * 2 4 4
         * 4 6
         */
        Assertions.assertEquals(5, coinCombine(q2Sum, q2Coins));

        /*
        1 1 1 1 1 1 1 1
        1 1 1 1 4
        1 1 6
        4 4
        8
        */
        Assertions.assertEquals(5,coinCombine(q3Sum, q3Coins));

    }


    public int coinCombine(int sum, int[] coins) {
        int[] kindOfSum = new int[sum + 1];

        /* 가장 작은 동전이 1개일 때는 1가지 경우의 수만 갖음*/
        kindOfSum[0] = 1;


        /* 0번 동전 부터 하나씩 추가해 가며 합계에 대한 배열에 값 추가 */
        for (int i = 0; i < coins.length; i++) {
            /* j를 만들기 위한 경우의 수 누적 */
            for (int j = coins[i]; j <= sum; j++) {
                System.out.printf("coins[i]=%d, j=%d kindOfSum=[", coins[i], j);
                Arrays.stream(kindOfSum).forEach(c -> System.out.printf("%d ", c));
                System.out.println("]");
                kindOfSum[j] += kindOfSum[j - coins[i]];
            }
        }


        return kindOfSum[sum];
    }
}
