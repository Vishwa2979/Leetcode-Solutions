/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if(head==null|| head.next==null || k==0){
            return head;
        }
        int len=0;
        ListNode temp=head;

        while(temp!=null){
            len++;
            temp=temp.next;
        }
        k%=len;
        if(k==0){
            return head;
        }
        head=reverse(head);

        ListNode first=head;
        for(int i=1;i<k;i++){
            first=first.next;
        }

        ListNode second=first.next;
        first.next=null;
        first=reverse(head);
        second=reverse(second);
        temp=first;
        while(temp.next!=null){
            temp=temp.next;
        }
        temp.next=second;
        return first;
    }
    private ListNode reverse(ListNode head){

        ListNode prev=null;
        ListNode curr=head;
        
        while(curr!=null){
        ListNode next=curr.next;
        curr.next=prev;
        prev=curr;
        curr=next;
        }
        return prev;
    }
}