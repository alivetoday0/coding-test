package programmers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 해시 > 전화번호 목록
 * - https://programmers.co.kr/learn/courses/30/lessons/42577?language=java
 *
 * 정렬 되었는지 확인해야 한다.
 * - 정렬이 되지 않았을 경우 앞의 내용이 비교 대상으로 안 들어갈 수 있기 때문이다.
 * - CASE5 같은 경우 1195524421가 119에 포함되지만 정렬이 되지 않으면 올바른 결과값이 안 나온다.
 */
public class PhoneBook {

  public static void main(String[] args) {
    System.out.println(new Solution().solution(new String[]{"1","123","1235","567","88"}));
    System.out.println(new Solution().solution(new String[]{"123","124","567","88"}));
    System.out.println(new Solution().solution(new String[]{"123","1234","567","88"}));
    System.out.println(new Solution().solution(new String[]{"119", "97674223", "1195524421"}));
    System.out.println(new Solution().solution(new String[]{"1195524421", "97674223", "119"}));
  }

  static class Solution {
    public boolean solution(String[] phone_book) {
      Arrays.sort(phone_book);

      Set<String> matcher = new HashSet<>();
      for (String phoneBook : phone_book) {
        for (String prefix : getPrefix(phoneBook)) {
          if (matcher.contains(prefix)) {
            return false;
          }
        }

        matcher.add(phoneBook);
      }

      return true;
    }
  }

  static String[] getPrefix(String phoneBook) {
    int length = phoneBook.length();

    String[] prefixes = new String[length];
    for (int idx = 1; idx <= length; idx++) {
      prefixes[idx - 1] = phoneBook.substring(0, idx);
    }

    return prefixes;
  }
}
