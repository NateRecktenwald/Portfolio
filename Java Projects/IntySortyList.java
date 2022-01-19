//  INTY SORTY LIST. A linear singly linked list of INTs which can be sorted by
//  Selection Sort. Do not change any part of this file, except for the body of
//  the SORT method.

class IntySortyList
{

//  NODE. A node in a singly linked linear list of INT's.

  private class Node
  {
    private int  key;   //  The key at this NODE, duh.
    private Node next;  //  The next NODE in the list, or NULL.

//  Constructor. Initialize a new NODE with KEY and NEXT.

    private Node(int key, Node next)
    {
      this.key  = key;
      this.next = next;
    }
  }

  private Node first;  //  Head node.

//  Constructor. Let FIRST be a singly linked linear list of the INT's in KEYS,
//  with a head node.

  public IntySortyList(int ... keys)
  {
     first = new Node(0, null);
     Node last = first;
     for (int key: keys)
     {
       last.next = new Node(key, null);
       last = last.next;
     }
  }

//  SORT. Sort FIRST using Selection Sort.

  public void sort()
  {
	   Node head = new Node(0,null);
	   Node count = first;
	   while(first.next != null)
	   { 
		   Node temp = first;
		   Node left = first;
		   Node right = first.next;
		   Node max = new Node(right.key,null);
		   while(temp.next != null)
		   {
			   if(max.key < temp.next.key)
			   {
				   max = temp.next;
				   left = temp;
				   right = temp.next;
			   }
			   temp = temp.next;
		   }
		   head.next = new Node(max.key,head.next);
		   left.next = right.next;
	   }
	   first = head;
  }
 
/*
 Let unsorted be a list of integers to be sorted. Let sorted be [ ], an empty list.

If unsorted is empty, then stop. The list sorted contains all the original elements of unsorted, sorted into nondecreasing order.

Select the largest integer max from unsorted. If max appears more than once in unsorted, then select any one of those appearances, arbitrarily.

Add max to the front of sorted. Delete max from unsorted. If max appears more than once in unsorted, then delete any one of those appearances, arbitrarily.

Go to step 2.
 */
//  TO STRING. Convert FIRST to a STRING, for printing.

  public String toString()
  {
    StringBuilder builder = new StringBuilder();
    builder.append('[');
    Node temp = first.next;
    if (temp != null)
    {
      builder.append(temp.key);
      temp = temp.next;
      while (temp != null)
      {
        builder.append(',');
        builder.append(' ');
        builder.append(temp.key);
        temp = temp.next;
      }
    }
    builder.append(']');
    return builder.toString();
  }
}
