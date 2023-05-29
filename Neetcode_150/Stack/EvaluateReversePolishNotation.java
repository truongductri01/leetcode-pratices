class Solution {
    public boolean contains(String[] operators, String token) {
        for (String op: operators) {
            if (op.equals(token)) {
                return true;
            }
        }
        return false;
    } 
    public int evalRPN(String[] tokens) {
        /**
        Reverse polish notation:
        3 4 + => 3 + 4
        3 4 x 5 6 x + => 3 * 4 + 5 * 6

        - When we hit an operator, pop out the latest 2 operands and calculate the result
            Then put back the result into the stack for later calculation
        - Return the last elements in the stack which is the final result
        */

        String[] operators = {"+", "-", "*", "/"};
        Stack<String> stack = new Stack<>();

        for (String t: tokens) {
            // System.out.println(stack.toString());
            if (!contains(operators, t)) {
                stack.push(t);
            } else {
                int second = Integer.parseInt(stack.pop());
                int first = Integer.parseInt(stack.pop());
                int result;
                if (t.equals("+")) {
                    result = first + second;
                } else if (t.equals("-")) {
                    result = first - second;
                } else if (t.equals("*")) {
                    result = first * second;
                } else {
                    result = first / second;
                }

                stack.push(String.valueOf(result));
            }
        }

        return Integer.parseInt(stack.pop());
    }
}
