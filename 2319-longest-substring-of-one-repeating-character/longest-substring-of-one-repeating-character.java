class Solution {
    class Node {
        int maxLen;
        int prefixLen;
        int suffixLen;
        char leftChar;
        char rightChar;
        int len; // Total range length of this node

        Node(char c) {
            this.maxLen = 1;
            this.prefixLen = 1;
            this.suffixLen = 1;
            this.leftChar = c;
            this.rightChar = c;
            this.len = 1;
        }

        Node() {}
    }

    private Node[] tree;
    private char[] chars;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length();
        chars = s.toCharArray();
        tree = new Node[4 * n];

        build(1, 0, n - 1);

        int k = queryIndices.length;
        int[] ans = new int[k];

        for (int i = 0; i < k; i++) {
            int idx = queryIndices[i];
            char ch = queryCharacters.charAt(i);
            chars[idx] = ch;
            update(1, 0, n - 1, idx, ch);
            ans[i] = tree[1].maxLen;
        }

        return ans;
    }

    private Node merge(Node left, Node right) {
        Node parent = new Node();
        parent.len = left.len + right.len;
        parent.leftChar = left.leftChar;
        parent.rightChar = right.rightChar;

        // Base max length is the max of both child segments
        parent.maxLen = Math.max(left.maxLen, right.maxLen);
        parent.prefixLen = left.prefixLen;
        parent.suffixLen = right.suffixLen;

        // Check if the middle boundary characters match
        if (left.rightChar == right.leftChar) {
            int midLen = left.suffixLen + right.prefixLen;
            parent.maxLen = Math.max(parent.maxLen, midLen);

            // Extend prefix if the entire left node consists of the same character
            if (left.prefixLen == left.len) {
                parent.prefixLen = left.len + right.prefixLen;
            }

            // Extend suffix if the entire right node consists of the same character
            if (right.suffixLen == right.len) {
                parent.suffixLen = right.len + left.suffixLen;
            }
        }

        return parent;
    }

    private void build(int node, int start, int end) {
        if (start == end) {
            tree[node] = new Node(chars[start]);
            return;
        }

        int mid = start + (end - start) / 2;
        build(2 * node, start, mid);
        build(2 * node + 1, mid + 1, end);
        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }

    private void update(int node, int start, int end, int idx, char ch) {
        if (start == end) {
            tree[node] = new Node(ch);
            return;
        }

        int mid = start + (end - start) / 2;
        if (idx <= mid) {
            update(2 * node, start, mid, idx, ch);
        } else {
            update(2 * node + 1, mid + 1, end, idx, ch);
        }

        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }
}