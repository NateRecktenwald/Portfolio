// Nathan Recktenwald x500
public class RunnyStack <Base> {
	
		private class Run { // creates a class called runs that can point to th top of the stack and the second element of the stack
			private Base value;
			private Run next;
			private Run(Base value, Run next) {
				this.value = value;
				this.next = next;
			}
		}
		//Your class must define at least the following methods, as described below. To simplify grading, your methods must have the same names as the ones shown here.
		
		private Run top;
		private int count = 0; //counts length of the stack
		private int num = 0; //counts number of runs
		
		public RunnyStack() { // constructs an empty stack
			top = null;
		}

		public int depth() { // return the amount of elements in the stack
			return count;
		}

		public boolean isEmpty() { // checks for if the stack is empty
			return top == null;
		}
	 

		public Base peek() { //gets the top value of the stack if it is not empty
			if(isEmpty()) {
				throw new IllegalStateException("can't have empty array");
			}
			else {
				return top.value;
			}
		}

		public void pop() { // takes an item off the top of the stack
			Base temp = peek();
			if(isEmpty()) {
				throw new IllegalStateException("can't have an empty stack");
			}
			else {
				count -= 1;
				if(runTest()) { // checks if the top two elements are equal and if they are nothing gets subtracted from runs and if not then subtract 1 from runs
						top = top.next;
				}
				else {
					num -= 1;
					top = top.next;
				}
			}
	
		}


		public void push(Base base) { // adds another item to the top of the stack 
			if(count > 0) {
				Base temp = top.value; // temperarely hold the top value on the stack
				top = new Run(base, top); // creates a new element to be on the top of the stack 
				count += 1;
				if(!temp.equals(base)) { // checks if the top two elements are equal and if they are and 1 to runs
					num += 1;
				}
			}
			else {
				num += 1;
				top = new Run(base, top);
				count += 1;
				
			}
		}

		

		public int runs() { // return the number of times similar chracters are grouped together
			return num;
		}
		
		public boolean runTest() {	// Tests to see if the top two elemets equal each other
			if(isEmpty()) {
				return false;
			}
			else {
				if(count < 1) { // checks if the stack is less than 1 and if it is then top.next will be null
					return false;
				}
				else {
					return top.value.equals(top.next.value); //success
				}
			}
		}
	}


