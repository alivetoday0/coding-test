package programmers;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.reducing;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Disguise {

  public static void main(String[] args) {
    String[][] input = new String[][] {
        new String[]{"yellowhat", "headgear"},
        new String[]{"bluesunglasses", "eyewear"},
        new String[]{"green_turban", "headgear"}};

    String[][] input2 = new String[][] {
        new String[]{"A", "headgear"},
        new String[]{"D", "eyewear"},
        new String[]{"E", "eyewear"},
        new String[]{"F", "eyewear"},
        new String[]{"G", "shoes"},
        new String[]{"H", "shoes"},
        new String[]{"B", "headgear"},
        new String[]{"C", "headgear"}};

    int answer = new Solution().solution(input);
    System.out.println(answer);

    int answer2 = new Solution().solution(input2);
    System.out.println(answer2);
  }

  static class Solution {
    public int solution(String[][] clothes) {
      Map<String, Integer> clothMap = new HashMap<>();
      for (String[] cloth : clothes) {
        String key = cloth[1];
        if (Objects.isNull(clothMap.get(key))) {
          clothMap.put(key, 1);
          continue;
        }

        clothMap.put(key, clothMap.get(key) + 1);
      }

      int[] results = clothMap.keySet().stream().mapToInt(it -> clothMap.get(it)).toArray();
      if (1 >= results.length) {
        return results[0];
      }

      int multiply = 1;
      for (int result: results) {
        multiply *= result + 1;
      }

      return multiply - 1;
    }

    public int solutionOther(String[][] clothes) {
      return Arrays.stream(clothes)
          .collect(groupingBy(p -> p[1], mapping(p -> p[0], counting())))
          .values()
          .stream()
          .collect(reducing(1L, (x, y) -> x * (y + 1))).intValue() - 1;
    }
  }
}
