package com.practice.onlineTest.tier1;

/**
 * Problem statement
 * Ninja has given a nested list of integers nestedList of size 'N'. Each element is either an integer or a list whose elements may also be integers or other lists.
 *
 * Ninjas have asked to implement the following methods of the NestedIterator class.
 *
 * NestedIterator(vector<NestedInteger> nestedList) Initializes the
 * iterator with the nested list nestedList.
 * int next() Returns the next integer in the nested list.
 * bool hasNext() Returns true if there are still some integers in the nested list and false otherwise.
 * Your task is to help the ninja to implement the above methods.
 *
 * Your code will be tested with the following pseudocode:
 *
 * initialize iterator with nestedList
 *   answer = []
 *   while iterator.hasNext()
 *     append iterator.next() to the end of answer
 *     return answer
 * You will get the correct answer verdict if the answer matches the expected flattened list.
 *
 * EXAMPLE:
 * Input List: [[1, 1], 2, [1, 1]]
 * Output List: [1, 1, 2, 1, 1]
 * Detailed explanation ( Input/output format, Notes, Images )
 * Constraints :
 * 1 <= 'T' <= 10
 * 1 <= 'N' <= 10^5
 * 0 <= Values of integers in the nested list <= 10^5
 * Time Limit: 1sec
 */
import java.util.*;

public class FlattenedList {
    public interface NestedInteger {
        public boolean isInteger();
        public Integer getInteger();
        public List<NestedInteger> getList();
    }

    public class NestedIterator implements Iterator<Integer> {
        private Stack<NestedInteger> stack;

        public NestedIterator(List<NestedInteger> nestedList) {
            stack = new Stack<>();
            flattenList(nestedList);
        }

        private void flattenList(List<NestedInteger> nestedList) {
            for (int i = nestedList.size() - 1; i >= 0; i--) {
                stack.push(nestedList.get(i));
            }
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                return null;
            }
            return stack.pop().getInteger();
        }

        @Override
        public boolean hasNext() {
            while (!stack.isEmpty() && !stack.peek().isInteger()) {
                List<NestedInteger> nestedList = stack.pop().getList();
                flattenList(nestedList);
            }
            return !stack.isEmpty();
        }
    }

    public static void main(String[] args) {
        FlattenedList outer = new FlattenedList();
        
        // Create nested list: [[1, 1], 2, [1, 1]]
        List<NestedInteger> nestedList = new ArrayList<>();
        
        // Inner list [1, 1]
        List<NestedInteger> inner1 = new ArrayList<>();
        inner1.add(outer.new NestedIntegerImpl(1));
        inner1.add(outer.new NestedIntegerImpl(1));
        nestedList.add(outer.new NestedIntegerImpl(inner1));
        
        // Integer 2
        nestedList.add(outer.new NestedIntegerImpl(2));
        
        // Inner list [1, 1]
        List<NestedInteger> inner2 = new ArrayList<>();
        inner2.add(outer.new NestedIntegerImpl(1));
        inner2.add(outer.new NestedIntegerImpl(1));
        nestedList.add(outer.new NestedIntegerImpl(inner2));
        
        NestedIterator iterator = outer.new NestedIterator(nestedList);
        List<Integer> result = new ArrayList<>();
        
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }
        
        System.out.println("Flattened list: " + result);
    }

    private class NestedIntegerImpl implements NestedInteger {
        private Integer val;
        private List<NestedInteger> list;

        NestedIntegerImpl(Integer val) {
            this.val = val;
            this.list = null;
        }

        NestedIntegerImpl(List<NestedInteger> list) {
            this.val = null;
            this.list = list;
        }

        @Override
        public boolean isInteger() {
            return val != null;
        }

        @Override
        public Integer getInteger() {
            return val;
        }

        @Override
        public List<NestedInteger> getList() {
            return list;
        }
    }
}
