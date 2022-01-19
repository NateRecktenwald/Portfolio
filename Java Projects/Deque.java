// Nathan Recktenwald X500
public class Deque<Base> {
	
	private Node head;
	
	
	private class Node
	{	
		private Node right;
		private Node left;
		private Base object;
		
		public Node(Node left, Base object, Node right)
		{
			this.left = left;
			this.right = right;
			this.object = object;
		}
	}
	
	public Deque()
	{
		head = new Node(null,null,null);
		head.left = head;
		head.right = head;
		
	}
	//Constructor. Make a new, empty Deque. The variable head must be set so it points
	//to a new head Node here.
	
	public void enqueueFront(Base object)
	{
		Node root = new Node(head,object,head.right);
		head.right.left = root;
		head.right = root;
	}
	//Add a new Node at the front of the Deque. The object slot of the new Node must
	//point to the parameter object.
	
	public void enqueueRear(Base object)
	{
		Node rear = new Node(head.left,object,head);
		head.left.right = rear;
		head.left = rear;
	}
	//Add a new Node at the rear of the Deque. The object slot of the new Node must point
	//to the parameter object.
	
	public Base dequeueFront()
	{
		if(isEmpty())
		{
			throw new IllegalStateException("Empty");
		}
		else
		{
			Base temp = head.right.object;
			head.right.right.left = head;
			head.right = head.right.right;
			return temp;
		}
	}
	//If the Deque is empty, then throw an IllegalStateException. Otherwise, get the
	//Base object from the Node at the front of the Deque. Delete the Node at the front of
	//the Deque, and return the Base object.
	
	public Base dequeueRear()
	{
		if(isEmpty())
		{
			throw new IllegalStateException("Empty");
		}
		else
		{
			Base temp = head.left.object;
			head.left.left.right = head;
			head.left = head.left.left;
			return temp;
		}
	}
	//If the Deque is empty, then throw an IllegalStateException. Otherwise, get the
	//Base object from the the Node at the rear of the Deque. Delete the Node at the rear
	//of the Deque, and return the Base object.
	
	public boolean isEmpty()
	{
		return head.right == head && head.left == head ;
			
	}
	//Return true if the Deque is empty. Return false otherwise.
}
