package inteview_exercises;

import java.util.HashSet;
import java.util.Set;

/*
Given head, the head of a linked list, determine if the linked list has a cycle in it.

There is a cycle in a linked list if there is some node in the list that can be reached again by continuously
following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to.
Note that pos is not passed as a parameter.

Return true if there is a cycle in the linked list. Otherwise, return false.
*/
public class LinkedListCycle {
    public static void main(String[] args) {
        System.out.println(hasCycle(new ListNode(5)));
    }

    public static boolean hasCycle(ListNode head) {
        Set<ListNode> links = new HashSet<>();
        links.add(head);
        while (head != null && head.next != null) {
            head = head.next;
            if (links.contains(head)) {
                return true;
            }
            links.add(head);
        }
        return false;
    }
}


// Definition for singly-linked list.

class ListNode {

    int val;

    ListNode next;

    ListNode(int x) {

        val = x;

        next = null;

    }

}
