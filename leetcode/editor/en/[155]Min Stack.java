//Design a stack that supports push, pop, top, and retrieving the minimum element in constant time. 
//
// 
// push(x) -- Push element x onto stack. 
// pop() -- Removes the element on top of the stack. 
// top() -- Get the top element. 
// getMin() -- Retrieve the minimum element in the stack. 
// 
//
// 
//
// Example: 
//
// 
//MinStack minStack = new MinStack();
//minStack.push(-2);
//minStack.push(0);
//minStack.push(-3);
//minStack.getMin();   --> Returns -3.
//minStack.pop();
//minStack.top();      --> Returns 0.
//minStack.getMin();   --> Returns -2.
// 
//
// 
//

// Two stacks
class MinStack {

    int min;
    Stack<Integer> stack, minStack;

    /** initialize your data structure here. */
    public MinStack() {
        stack = new Stack<Integer>();
        minStack = new Stack<Integer>();
    }

    public void push(int x) {
        stack.push(x);
        if (minStack.isEmpty())
            minStack.push(x);
        else if (x < minStack.peek())
            minStack.push(x);
        else
            minStack.push(minStack.peek());
    }

    public void pop() {
        stack.pop();
        minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}


// Better two stacks
// second stack compressed
class MinStack {

    int min;
    Stack<Integer> stack, minStack;

    /** initialize your data structure here. */
    public MinStack() {
        stack = new Stack<Integer>();
        minStack = new Stack<Integer>();
    }

    public void push(int x) {
        stack.push(x);
        if (minStack.isEmpty() || x <= minStack.peek())
            minStack.push(x);
    }

    public void pop() {
        int x = stack.pop();
        if (!minStack.isEmpty() && x == minStack.peek())
            minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}


// More better two stacks
// adding times to the second stack
class MinStack {

    int min;
    Stack<Integer> stack;
    Stack<int[]> minStack;

    /** initialize your data structure here. */
    public MinStack() {
        stack = new Stack<Integer>();
        minStack = new Stack<int[]>();
    }

    public void push(int x) {
        stack.push(x);
        if (minStack.isEmpty() || x < minStack.peek()[0])
            minStack.push(new int[]{x, 1});
        else if (x == minStack.peek()[0])
            minStack.peek()[1]++;
    }

    public void pop() {
        int x = stack.pop();
        if (!minStack.isEmpty() && x == minStack.peek()[0])
            minStack.peek()[1]--;
        if (minStack.peek()[1] == 0)
            minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek()[0];
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */