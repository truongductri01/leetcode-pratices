class Solution {
    public boolean isAnagram(String s, String t) {
        /**
        First thought:
        - Make sure the length of s and t are the same
        - Create a Map of the freq of each char in s
        - Then create a map for char in t, if the char is not in s or in s but with a different freq, return false
        - Otherwise, return true

        O(n) with n = length of s
        */
        Map<Character, Integer> mapS = new HashMap<>();
        Map<Character, Integer> mapT = new HashMap<>();

        if (s.length() != t.length()) {
            // System.out.println("Different in length");
            return false;
        }

        for (int i = 0; i < s.length(); i++) {
            char cS = s.charAt(i);
            char cT = t.charAt(i);

            mapS.putIfAbsent(cS, 0);
            mapS.put(cS, mapS.get(cS) + 1);

            mapT.putIfAbsent(cT, 0);
            mapT.put(cT, mapT.get(cT) + 1);
        }

        for (char c: mapT.keySet()) {
            if (!mapS.containsKey(c)) {
                // System.out.println("S not contain the same char: " + c);
                return false;
            } else if ((int) mapS.get(c) != (int) mapT.get(c)) {
                // two Integer object although with the same value could still be different since we are comparing objects
                // System.out.println("Don't have the same frequency for: " + c + " s has: " + mapS.get(c) + " but t has: " + mapT.get(c));
                return false;
            }
        }
        return true;
    }
}
