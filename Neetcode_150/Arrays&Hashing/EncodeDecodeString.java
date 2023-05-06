public class Codec {
    public String delimeter = "_tri_";
    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
         /**
    We can use some kind of special delimeter to join and split
     */
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strs.size(); i++) {
            if (i != 0) {
                sb.append(delimeter);
            }
            sb.append(strs.get(i));
        }
        sb.append(delimeter);
        // System.out.println(sb.toString());
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public String charToString(String s, int start, int end) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < end; i++) {
            sb.append(String.valueOf(s.charAt(i)));
        }
        if (sb.length() == 0) {
            return "";
        }
        return sb.toString();
    }

    public boolean checkIsDelimeter(String s, int start, int end) {
        if (end > s.length()) {
            return false;
        } else {
            for (int i = start; i < end; i++) {
                if (s.charAt(i) != delimeter.charAt(i - start)) {
                    return false;
                }
            }
            return true;
        }
    }

    public List<String> decode(String s) {
        int idx = 0;
        int prevIdx = idx;
        List<String> result = new ArrayList<>();

        while (idx < s.length()) {
            if (s.charAt(idx) == delimeter.charAt(0)) {
                int temp = idx + delimeter.length();
                if (checkIsDelimeter(s, idx, temp)) {
                    // skip idx ahead
                    String string = charToString(s, prevIdx, idx);
                    result.add(string);
                    idx = temp;
                    prevIdx = idx;
                    // append the result found
                    // else append
                } else {
                    // normal increase
                    idx ++;
                }
            } else {
                idx ++;
            }
        }
        return result;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(strs));
