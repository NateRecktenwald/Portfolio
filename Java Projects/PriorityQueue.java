
// Nathan Recktenwald X500
public class PriorityQueue<Base> {
	
	private class Node 
	  { 
	    private Base object; 
	    private int  rank; 
	    private Node left; 
	    private Node right; 
	 
	    private Node(Base object, int rank) 
	    { 
	      this.object = object; 
	      this.rank = rank; 
	      left = null; 
	      right = null; 
	    }
	  } 
	 
	  private Node root;  //  Head node of the BST. 
	  
	 
	  public PriorityQueue()
	  {
		root = new Node(null,37);
	  }

	  //Constructor. Make a new, empty priority queue. You must set root to a head node, to simplify insertion and deletion by the other methods. 
	  //You must decide what the rank slot of the head node should be. Whatever you choose, it should minimize the number of special cases needed 
	  //by the other methods. Don’t use special cases to add the first node, or to delete the last node!

	  public Base dequeue()
	  {
		  if (isEmpty())  
		  {     
			  throw new IllegalStateException("MTQ");   
		  }   
		  else
		  {
			  Node temp = root.left;
			  Node parent = root;
		      while (temp.left != null)
		      {
		    	parent = parent.left;
		        temp = temp.left;
		      }
		      Base temp2 = temp.object;
		      parent.left = temp.right;
		      return temp2;
		  }
		  
	  }

	  //If the priority queue is empty, then throw an IllegalStateException. Otherwise, remove the highest ranked object (with the lowest rank number) 
	  //from the priority queue, using the algorithm described in the previous section. Resolve ties in rank arbitrarily. Return the removed object.

	  public void enqueue(Base object, int rank)
	  {
		  if(rank < 0)
		  {
			  throw new IllegalArgumentException("rank cannot be zero");
		  }
		  else if(root == null)
		  {
			  root.left = new Node(object, rank);
		  }
		  else
		  {
			  Node temp = root;
			  while (true)
			  {
				  if(rank <= temp.rank)
				  {
					  if(temp.left == null)
					  {
						  temp.left = new Node(object, rank);
						  return;
					  }		
					  else
					  {
						  temp = temp.left;
					  }
				  }
				  else
				  {
					  if(temp.right == null)
					  {
						  temp.right = new Node(object, rank);
						  return;
					  }
					  else
					  {
						  temp = temp.right;
					  }
				  } 
			  }
		  }
	  }

	  //If rank is negative, then throw an IllegalArgumentException. Otherwise, add object to the priority queue with the given rank,
	  //using the algorithm described in the previous section.

	  public boolean isEmpty()
	  {
		  return root.left == null;
	  }

	  //Return true if the priority queue is empty. Return false otherwise.
	  
}
