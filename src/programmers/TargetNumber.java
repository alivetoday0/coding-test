package programmers;

import java.util.Arrays;

/**
 * https://programmers.co.kr/learn/courses/30/lessons/43165
 *
 * 문제 설명
 * n개의 음이 아닌 정수가 있습니다. 이 수를 적절히 더하거나 빼서 타겟 넘버를 만들려고 합니다. 예를 들어 [1, 1, 1, 1, 1]로 숫자 3을 만들려면 다음 다섯 방법을 쓸 수 있습니다.
 *
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 * 사용할 수 있는 숫자가 담긴 배열 numbers, 타겟 넘버 target이 매개변수로 주어질 때 숫자를 적절히 더하고 빼서 타겟 넘버를 만드는 방법의 수를 return 하도록 solution 함수를 작성해주세요.
 *
 * 제한사항
 * 주어지는 숫자의 개수는 2개 이상 20개 이하입니다.
 * 각 숫자는 1 이상 50 이하인 자연수입니다.
 * 타겟 넘버는 1 이상 1000 이하인 자연수입니다.
 * 입출력 예
 * numbers	target	return
 * [1, 1, 1, 1, 1]	3	5
 */
public class TargetNumber {

  public static void main(String[] args) {
    int answer = new Solution().solution(new int[]{1, 1, 1, 1, 1}, 3);

    System.out.println(" // answer : " + answer);
  }

  static class Solution {
    int solution(int[] numbers, int target) {
      int answerPlusValue = depthFirstSearch(numbers[0], 0, 0, target, numbers);
      int answerMinusValue = depthFirstSearch(-numbers[0], 0, 0, target, numbers);

      return answerPlusValue + answerMinusValue;
    }


    /**
     * 나의 풀이
     * @param value
     * @param sum
     * @param answer
     * @param target
     * @param numbers
     * @return
     */
    int depthFirstSearch(int value, int sum, int answer, int target, int[] numbers) {
//      System.out.print(value + " -> ");
      if (numbers.length == 1) {
//        System.out.println(" ====== " + (sum + value));
        if ((sum + value) == target) {
//          System.out.println(" true ");
          return answer + 1;
        }

        return 0;
      }

      int[] copyOfRange = Arrays.copyOfRange(numbers, 1, numbers.length);
      int answerPlusValue =  depthFirstSearch(copyOfRange[0], value + sum, answer, target, copyOfRange);
      int answerMinusValue = depthFirstSearch(-copyOfRange[0], value + sum, answer, target, copyOfRange);

      return answerPlusValue + answerMinusValue;
    }

    /**
     * 모든 풀이
     *
     * @param numbers
     * @param n
     * @param sum
     * @param target
     * @return
     */
    int dfs(int[] numbers, int n, int sum, int target) {
      if(n == numbers.length) {
        if(sum == target) {
          return 1;
        }
        return 0;
      }
      return dfs(numbers, n + 1, sum + numbers[n], target) + dfs(numbers, n + 1, sum - numbers[n], target);
    }
  }
}
