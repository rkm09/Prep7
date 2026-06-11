package misc;

public class FirstDup {
    public static void main(String[] args) {
        String s = "beananae";
        System.out.println(firstDuplicate(s));
    }

//    we want the first duplicate globally [could also use linked hashmap]
    public static char firstDuplicate(String s) {
        int[] freq = new int[26];
        for (int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i) - 'a';
            freq[idx]++;
        }

        for (char c : s.toCharArray()) {
            int idx = c - 'a';
            if (freq[idx] > 1)
                return c;
        }

//        we should not reach here
        return 'a';
    }


//    we want the first duplicate witnessed
    public static char firstDuplicate1(String s) {
        int[] freq = new int[26];
        for (int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i) - 'a';
            if (freq[idx] != 0)
                return s.charAt(i);
            freq[idx]++;
        }

        return 0;
    }
}
