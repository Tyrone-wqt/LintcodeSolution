package extent;

import java.util.Stack;

/**
 * Created by lenovo on 2017/1/9.
 */
public class MinStack {
    Stack<Integer> mData;
    Stack<Integer> mMin;

    //int min;
    public MinStack() {
        // do initialize if necessary
        mData = new Stack<>();
        mMin = new Stack<>();
        //min=Integer.MAX_VALUE;
    }

    public void push(int number) {
        // write your code here
        mData.push(number);
        if (mMin.size() == 0) {
            mMin.push(number);
        } else {
            if (mMin.peek() > number) {
                mMin.push(number);
            } else {
                mMin.push(mMin.peek());
            }
        }
    }

    public int pop() {
        // write your code here

        mMin.pop();
        return mData.pop();

    }

    public int min() {
        // write your code here
        return mMin.peek();
    }
}
