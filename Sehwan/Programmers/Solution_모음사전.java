import java.io.*;
import java.util.*;

public class Main {

    // A E I O U
    static HashMap<String, Integer> map = new HashMap<>();

    public static int solution(String word) {
        int answer = 0;
        map.put("A", 0);
        map.put("E", 1);
        map.put("I", 2);
        map.put("O", 3);
        map.put("U", 4);

        int[] weight = {781, 156, 31, 6, 1};

        String[] arr = word.split("");

        for (int i = 0; i < arr.length; i++) {
            answer += 1 + map.get(arr[i]) * weight[i];
        }

        System.out.println(answer);
        return answer;
    }

    public static void main(String[] args) throws IOException {

        int ans1 = solution("AAAAE"); // 6
        int ans2 = solution("EIO"); // 1563
        int ans3 = solution("I"); // 1563
        int ans4 = solution("AAAE"); // 1189

    }

}