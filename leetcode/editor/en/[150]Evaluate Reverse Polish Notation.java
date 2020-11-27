//Evaluate the value of an arithmetic expression in Reverse Polish Notation. 
//
// Valid operators are +, -, *, /. Each operand may be an integer or another exp
//ression. 
//
// Note: 
//
// 
// Division between two integers should truncate toward zero. 
// The given RPN expression is always valid. That means the expression would alw
//ays evaluate to a result and there won't be any divide by zero operation. 
// 
//
// Example 1: 
//
// 
//Input: ["2", "1", "+", "3", "*"]
//Output: 9
//Explanation: ((2 + 1) * 3) = 9
// 
//
// Example 2: 
//
// 
//Input: ["4", "13", "5", "/", "+"]
//Output: 6
//Explanation: (4 + (13 / 5)) = 6
// 
//
// Example 3: 
//
// 
//Input: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
//Output: 22
//Explanation: 
//  ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
//= ((10 * (6 / (12 * -11))) + 17) + 5
//= ((10 * (6 / -132)) + 17) + 5
//= ((10 * 0) + 17) + 5
//= (0 + 17) + 5
//= 17 + 5
//= 22
// 
// Related Topics Stack


// Simple stack
class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        int n = tokens.length;

        for (int i = 0; i < n; i++) {
            String c = tokens[i];
            if (!"+-*/".contains(c)) {
                stack.push(Integer.valueOf(c));
            } else {
                int right = stack.pop();
                int left = stack.pop();
                if ("+".equals(c))
                    stack.push(left + right);
                else if ("-".equals(c))
                    stack.push(left - right);
                else if ("*".equals(c))
                    stack.push(left * right);
                else
                    stack.push(left / right);
            }
        }

        return stack.pop();
    }
}


// Use Lambda for operation
// TODO java 8
class Solution {
    private static final Map<String, BiFunction<Integer, Integer, Integer>> OPERATIONS = new HashMap<>();

    static {
        OPERATIONS.put("+", (a, b) -> a + b);
        OPERATIONS.put("-", (a, b) -> a - b);
        OPERATIONS.put("*", (a, b) -> a * b);
        OPERATIONS.put("/", (a, b) -> a / b);
    }

    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        int n = tokens.length;

        for (int i = 0; i < n; i++) {
            String c = tokens[i];
            if (!OPERATIONS.containsKey(c)) {
                stack.push(Integer.valueOf(c));
            } else {
                int right = stack.pop();
                int left = stack.pop();
                BiFunction<Integer, Integer, Integer> operation = OPERATIONS.get(c);
                stack.push(operation.apply(left, right));
            }
        }

        return stack.pop();
    }
}
