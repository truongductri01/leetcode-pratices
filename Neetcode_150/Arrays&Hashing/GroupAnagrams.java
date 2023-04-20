class Solution {
    public String sortString(String s) {
        char[] c = s.toCharArray();
        Arrays.sort(c);
        StringBuilder sb = new StringBuilder();
        for (char charEl: c) {
            sb.append(charEl);
        }
        return sb.toString();
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        /**
        Clarify Questions:
        - Ask about the constraints of input: 10^4
        - So can run at most with O(n^2) time

        First Thought:
        - Need to use a function to check the valid anagram between two element
        - We can do 2 for loop to compare the strings with each other and group them
        - Run time with be n * n * m with n is the total of strings while m is the length of each string, assuming they have the same length of m.

        How to simplify this?
        - Two anagram string will have the same hash code after hashing
        - We can represent each char with a prime value
            => write a function generating 26 different prime value
            this could be hardcode to reduce the runtime

        Is there any other ways to hash it?

        - Or we can sort the strings. Then put them into map corresponding with their sorted form


\         */
    
        // String a = "asdfasdf";
        // char[] cList = a.toCharArray();
        // Arrays.sort(cList);
        // System.out.println(Arrays.toString(cList));

        Map<String, List<String>> map = new HashMap<>();

        for (String s: strs) {
            String sorted = sortString(s);
            map.putIfAbsent(sorted, new ArrayList<>());
            map.get(sorted).add(s);
        }

        List<List<String>> result = new ArrayList<>();
        for (String s: map.keySet()) {
            result.add(map.get(s));
        }

        return result;
    }
}
