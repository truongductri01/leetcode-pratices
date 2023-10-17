Problem: [678. Valid Parenthesis String](https://leetcode.com/problems/valid-parenthesis-string/description/)

<strong>First Approach</strong>

Can utilize a similar method like validating parenthesis.

Loop through each char `c` in the string `s` and check:
- if `c == *`, increase `countStar`
- if `c == (`, increase `countLeft`
- if `c == )`, decrease `countLeft` if `countLeft` > 0. otherwise, if `countStar` > 0, decrease it, else return false.

At the end, if `countStar >= countLeft`, return true, else, return false.

This may sound like a good approach but this will fail since there is another restriction that we have not taken into consideration:
`Left parenthesis '(' must go before the corresponding right parenthesis ')'.`

That means if there is a star and a left parenthesis, the star must come after.

<strong> Correct Approach</strong>

We will utilize Stack and Queue to keep track of the index of the left parenthesis and the stars.

After removing all the right parenthesis like mentioned above, we will check the positions of the stars and left parenthesis to make sure it meet the requirements

Here is a simple explanation:
- used stack for left paren and queue for star?
- will store the int value (the index of the parent and star only)
- if there is some right paren without left paren
    remove star from the top of the queue (smallest positions)


when there are only left paren left. 
- pop from the end => get the largest, if it does not satisfy => false
- convert queue into stack for popped out
- keep doing until empty

```java
class Solution {
    public boolean checkValidString(String s) {
        Queue<Integer> starQ = new LinkedList<>();
        Stack<Integer> leftS = new Stack<>();


        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '*') {
                starQ.add(i);
            } else if (c == '(') {
                leftS.add(i);
            } else {
                // ) only 
                if (!leftS.isEmpty()) {
                    leftS.pop();
                } else {
                    if (!starQ.isEmpty()) {
                        starQ.remove();
                    } else {
                        return false;
                    }
                }
            }
        }

        if (leftS.isEmpty()) {
            return true;
        } else if (starQ.isEmpty()) {
            // this means still has left but no star 
            return false;
        } else {
            // convert 
            Stack<Integer> starS = new Stack<>();
            while (!starQ.isEmpty()) {
                starS.add(starQ.remove());
            }

            while (!starS.isEmpty() && !leftS.isEmpty()) {
                int left = leftS.pop();
                int star = starS.pop();

                if (star < left) {
                    return false;
                }
            }

            if (leftS.isEmpty()) {
                return true;
            }
            return false;
        }
    }
}
```
