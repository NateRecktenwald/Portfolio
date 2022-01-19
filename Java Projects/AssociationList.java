// Nathan Recktenwald X500
public class AssociationList<Key,Value> {
	
	private class Node 
	{
		private Key key;
		private Value value;
		private Node next;
		
		public Node(Key key, Value value, Node next)
		{
			this.key = key;
			this.value = value;
			this.next = next;
		}
	}
	
	private Node head;
	private Node front;
	public AssociationList()
	{
		head = new Node(null,null,null);
		front = head.next;
	}

	//Constructor. Initialize an empty instance of AssociationList. Hint: make the head node here.

	public void delete(Key key)
	{
		if(isEmpty())
		{
			if(isEqual(key,front.key))
			{
				front = front.next;
			}
			else
			{
				Node left = front;
				Node right = front.next;
				while(right != null)
				{
					if(isEqual(key,right.key))
					{
						left.next = right.next;
						break;
					}
					else
					{
						left = right;
						right = right.next;
					}
				}
			}
		}
	}

	//Search the association list for a Node whose key slot equals the key parameter, according to isEqual. Delete that Node from the list. 
	//If no Node has a key slot that equals the key parameter, then do nothing. 
	//Your delete method must not use a special case to delete the first Node in the list after head. Hints: do not delete the head node,
	//and use the ‘‘left-right trick’’ discussed in the lectures.

	public Value get(Key key)
	{
		while(front.next != null)
		{
			if(isEqual(front.key, key)) 
			{
				return front.value;
			}
		}
		throw new IllegalArgumentException("No such node exists");
	}

	//Search the association list for a Node whose key slot equals the key parameter, according to isEqual. Return the value slot of that Node. 
	//If no Node has a key slot that equals the key parameter, then throw an IllegalArgumentException. Hint: do not test the head node’s key slot.

	private boolean isEqual(Key leftKey, Key rightKey)
	{
		if(leftKey == null || rightKey == null)
		{
			return leftKey == rightKey;
		}
		else
		{
			return leftKey.equals(rightKey);
		}
	}

	//Test if leftKey is equal to rightKey. Either or both may be null. This helper method is necessary because you must use == when leftKey or rightKey are null, 
	//but you must use the equals method when both are not null. (Recall that null has no methods.)

	public boolean isIn(Key key)
	{
		while(front.next != null)
		{
			if(isEqual(front.key, key))
			{
				return true;
			}
			front = front.next;
		}
		return false;
	}

	//Search the association list for a Node whose key slot equals the key parameter according to isEqual. Return true if you find such a Node, 
	//and return false otherwise. Hint: do not test the head node’s key slot.

	public void put(Key key, Value value)
	{
		while(front != null)
		{
			if(isEqual(front.key, key))
			{
				front = new Node(key, value, front.next);
			}
			front = front.next;
		}
		head.next = new Node(key,value,front);
		front = head.next;
	}

	//Search the association list for a Node whose key slot equals the key parameter, according to isEqual. Change the value slot of that Node to be the value parameter. 
	//If there is no such Node, then add a new Node immediately after the head node. The new Node’s key slot is the key parameter, 
	//and its value slot is the value parameter. Hint: do not change the head node’s key or value slots.
	
	private boolean isEmpty()
	{
		return head.next == null;
	}
}
