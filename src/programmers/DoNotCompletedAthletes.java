package programmers;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * https://programmers.co.kr/learn/courses/30/lessons/42576
 */
public class DoNotCompletedAthletes {

  public static void main(String[] args) {
    String answer = new Solution().solution(new String[] {"leo", "kiki", "eden"}, new String[] {"leo", "kiki"});
    System.out.println(answer);
  }

  static class Solution {
    public String solution(String[] participant, String[] completion) {
      String answer = "";

      Map<String, Long> participantMap = Arrays.stream(participant)
          .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

      for (String completed : completion) {
        long value = participantMap.get(completed).intValue();
        if (value == 1) {
          participantMap.remove(completed);
          continue;
        }

        participantMap.put(completed, value - 1);
      }

      answer = participantMap.keySet().stream().collect(Collectors.toList()).get(0);
      // or
      //answer = participantMap.keySet().iterator().next();
      return answer;
    }

    /**
     *
     * @param participant
     * @param completion
     * @return
     */
    public String otherSolution(String[] participant, String[] completion) {
      Arrays.sort(participant);
      Arrays.sort(completion);
      int i;
      for ( i=0; i<completion.length; i++){

        if (!participant[i].equals(completion[i])){
          return participant[i];
        }
      }
      return participant[i];
    }
  }
}
