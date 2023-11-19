package inteview_exercises;

/*
You are given the heads of two sorted linked lists list1 and list2.

Merge the two lists into one sorted list. The list should be made by splicing together the nodes of the first two lists.

Return the head of the merged linked list.
*/
public class MergeTwoSortedLists {
    public static void main(String[] args) {
        ListNode first1 = new ListNode(1);
        ListNode first2 = new ListNode(3);
        ListNode first3 = new ListNode(4);
        first1.next = first2;
        first2.next = first3;

        ListNode second1 = new ListNode(2);
        ListNode second2 = new ListNode(2);
        ListNode second3 = new ListNode(2);
        second1.next = second2;
        second2.next = second3;

        ListNode result = mergeTwoLists(first1, second1);
        System.out.println(result);
    }

    public static ListNode mergeTwoLists(ListNode node1, ListNode node2) {
        if (node1 != null && node2 != null) {
            if (node1.val <= node2.val) {
                node1.next = mergeTwoLists(node1.next, node2);
                return node1;
            } else {
                node2.next = mergeTwoLists(node1, node2.next);
                return node2;
            }
        }
        if(node1==null)
            return node2;
        return node1;
    }
}


// Definition for singly-linked list.


