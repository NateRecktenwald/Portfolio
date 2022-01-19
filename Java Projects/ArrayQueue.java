//Nathan Recktenwald X500
//  ARRAY QUEUE. A fixed length queue represented as a circular array.

class ArrayQueue<Base>
{
  private int    front;    //  Index of front object in OBJECTS.
  private int    rear;     //  Index of rear object in OBJECTS.
  private Base[] objects;  //  The OBJECTs in the queue.
  
  
  
  public class Iterator
  {

 // This class must be nested inside ArrayQueue. An instance of this class may be used to visit the current elements of an instance of ArrayQueue.
//It must have one or more private variables that let it ‘‘know’’ which elements of ArrayQueue are to be visited next. 
//You must decide what those private variables are. Hint: you need not copy the array from ArrayQueue.
  private Base next;
  
  private Iterator(Base next)	
  {
	  this.next = next;
  }

 // This is Iterator’s constructor. Of course it must be inside Iterator. It must set Iterator’s private variables to the values of its parameters. 
  //You must decide what these parameters are.

  public boolean hasNext()
  {
	  return next != null;
  }

 // This method must be inside Iterator. It must return true if there are more elements of ArrayQueue that remain to be visited. 
  //It must return false otherwise. This method must use Iterator’s private variables only. Hint: use ideas from ArrayQueue’s method isEmpty.

  public Base next()
  {
	  if(!hasNext())
	  {
		  throw new IllegalStateException("There isn't another element");
	  }
	  else 
	  {
		 return next;
	  }
  }

  //This method must be inside the class Iterator. It must return the next Base element to be visited from ArrayQueue. 
  //If no more elements remain to be visited, then it must throw an IllegalStateException. This method must use Iterator’s private variables only. 
  //Hint: use ideas from ArrayQueue’s method dequeue.

  
  }
  
  public Iterator iterator()
  {
	  Iterator trace = new Iterator(objects[front]);	//objects[front]
	  return trace;
  }

  //This method must be inside ArrayQueue. It must call Iterator’s constructor to make a new instance of Iterator. It must then return the new instance.

  
  
  
//  Constuctor. Make a new empty queue that can hold SIZE - 1 elements.

  public ArrayQueue(int size)
  {
    if (size <= 1)
    {
      throw new IllegalArgumentException("Illegal size.");
    }
    else
    {
      front   = 0;
      rear    = 0;
      objects = (Base []) new Object[size];
    }
  }

//  DEQUEUE. Remove an object from the queue.

  public Base dequeue()
  {
    if (front == rear)
    {
      throw new IllegalStateException("Queue is empty.");
    }
    else
    {
      front = (front + 1) % objects.length;
      Base temp = objects[front];
      objects[front] = null;
      return temp;
    }
  }

//  ENQUEUE. Add a new OBJECT to the queue.

  public void enqueue(Base object)
  {
    int nextRear = (rear + 1) % objects.length;
    if (front == nextRear)
    {
      throw new IllegalStateException("Queue is full.");
    }
    else
    {
      rear = nextRear;
      objects[rear] = object;
    }
  }

//  IS EMPTY. Test if the queue is empty.

  public boolean isEmpty()
  {
    return front == rear;
  }

//  IS FULL. Test if the queue is full.

  public boolean isFull()
  {
    return front == (rear + 1) % objects.length;
  }
}
