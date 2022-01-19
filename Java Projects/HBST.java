// Nathan Recktenwald X500

import java.util.Random;

public class HBST<Key,Value> {

	private class PairNode  // nodes that will deal with the collisions of the nodes in the association list
	  { 
	    private Key key; 
	    private Value value; 
	    private PairNode next;
	 
	    private PairNode(Key key, Value value, PairNode next) // nodes of the association list
	    { 
	      this.key = key;
	      this.value = value;
	      this.next = next;
	    }
	  } 
	
	
	private class TreeNode // inner class that handles the nodes that will appear in the tree
	  { 
	    
		private TreeNode left;
		private TreeNode right;
		private int hash;
		private PairNode pairs;
		
	    private TreeNode(int hash, PairNode pairs, TreeNode left, TreeNode right)  // nodes of the binary search tree
	    { 
	      this.hash = hash;
	      this.pairs = pairs;
	      this.left = left; 
	      this.right = right; 
	    }
	  } 
	
	
	private TreeNode root;
	private Random generator;
	
	public HBST()
	{
		root = new TreeNode(-1,null,null,null);	// defines the root of the tree node to point to the head node
		generator  = new Random(); // sets generator to a random number
	}

	//(5 points.) Constructor. Initialize a new empty HBST. Set generator to an instance of Random. Set root to an instance of TreeNode.
	//Hint: the hash slot of root must be an int that cannot appear in the hash slot of any other TreeNode.

	public Value get(Key key)
	{
		TreeNode temp = root;
		int hash = hash(key); 
		
		while (temp != null)
		{
			if(hash < temp.hash)
			{
				temp = temp.left;	// move left
			}
			else if(hash > temp.hash)
			{
				temp = temp.right;	// move right
			}
			else
			{
				PairNode temp2 = temp.pairs;
			    while (temp2 != null)
			    {
			      if (isEqual(key, temp2.key))
			      {
			        return temp2.value;	// success
			      }
			      else
			      {
			        temp2 = temp2.next;	// moves through the association list
			      }
			    }
			    throw new IllegalArgumentException("No such key in pair nodes"); // failure to find the key in the association list
			}
		}
		
		throw new IllegalArgumentException("No such key in tree nodes"); // failure to find the key given in the tree
	}

	//(15 points.) Return the Value that is associated with key, as described in the previous section. If no Value is associated with key, 
	//then throw an IllegalArgumentException. Note that key may be null, and the returned Value may also be null. Hint: remember to skip the head node at root.

	
	
	public int height()
	{
		return heighting(root) - 1;
	}

	 private int heighting(TreeNode root) 	// the method that does all the work to find the height 
	  {
	    if (root == null)
	    {
	      return 0; 	// tree node with 0 nodes
	    }
	    else
	    {
	      int left  = heighting(root.left); // reccursion to move through the tree until all the way at the bottom left where the height would be
	      int right = heighting(root.right); // reccursion to move through the tree until all the way at the bottom right where the height would be
	      if (left > right)
	      {
	        return left + 1;	// success for the left
	      }
	      else
	      {
	        return right + 1; 	// success for the right
	      }
	    }
	  }

	//(5 points.) Return the height of this HBST. Hint: remember to skip the head node at root.

	 
	 
	private int hash(Key key)
	{
		if (key == null)
	    {
	      return 0;
	    }
	    else
	    {
	      int num = key.hashCode();
	      generator.setSeed(num);
	      return Math.abs(generator.nextInt());
	    }
	}

	//(5 points.) If key is null, then return 0. Otherwise, use generator to help compute the hash index of key, as described in the previous section,
	//and return that hash index. Hint: return an int that is guaranteed to be different from the one in the head node root.

	public boolean isEmpty()
	{
		return root.right == null && root.left == null;
	}

	//(5 points.) If the HBST is empty, then return true, otherwise return false. You must do this without using an if, in exactly one line of code. 
	//Hint: remember to skip the head node at root.

	private boolean isEqual(Key left, Key right)
	{
		if(left == null || right == null)  // if either is null the = sign must be used in java
		{
			return left == right;
		}
		else
		{
			return left.equals(right);		// if both are not null then the .equals method must be used in java
		}
	}

	//(0 points.) If left and right are equal, then return true, otherwise return false. You will need this as a helper for the methods get and put. 
	//Note that left may be null, right may be null, or both may be null. It's worth 0 points because you've already seen code for it in the lectures,
	//and in at least one lab.
	
	
	
	public void put(Key key, Value value)	
    {										

		TreeNode subtree = root;
		 int index = hash(key);		 
		 
	      while (true)		// will not end until a told to stop
	      {
	        if (subtree.hash > index) // moves through the tree to the left
	        {
	          if (subtree.left == null)
	          {
	            subtree.left = new TreeNode(index, new PairNode(key,value,null), null, null);
	            return;
	          }
	          else
	          {
	            subtree = subtree.left;		
	          }
	        }
	        else if (subtree.hash < index)  // moves through the tree to the right
	        {
	          if (subtree.right == null)
	          {
	        	  subtree.right = new TreeNode(index, new PairNode(key,value,null), null, null);
	        	  return;
	          }
	          else 
	          {
	        	  subtree = subtree.right;		
	          }
	        }
	        else
	        {
	        	PairNode temp = subtree.pairs;
	            while (temp != null)	// searches through the association list for the correct value
		      	{
		      	  if (isEqual(temp.key, key))
		      	  {
		      	    temp.value = value;	// found the correct value
		      	    return;
		      	  }
		      	  else
		      	  {
		      	    temp = temp.next;	// moves assocition list to the right
		      	  }
		        }
		      	  subtree.pairs = new PairNode(key,value,subtree.pairs);
		      	  return;
	         }
	       }
	     }
	   }
	
	//(20 points.) Associate key with value, as discussed in the previous section. Note that key may be null, or value may be null, or both may be null. 
	//Hint: you must use the head node at root to avoid a special case when adding the first TreeNode to the HBST.



