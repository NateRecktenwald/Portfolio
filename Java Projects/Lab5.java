//Nathan Recktenwald
public class Map<Key, Value>{
	
	private Key[] keys; //stores elements for keys
	private Value[] values; // stores elemets for values
	private int count;
	
	public Map(int length) {
		
		count = 0;
		
		if(length < 0) {
			throw new IllegalArgumentException("Length is lesss than 0");
		}
		else {
			keys = (Key[]) new Object[length]; // initializes keys
			values = (Value[]) new Object[length]; // initializes values
		}
		
		
	}

	

	public Value get(Key key) {
		
		for(int index = 0; index < keys.length; index += 1) {
			if(keys[index] == null) {
				if(keys[index] == key) {
					return values[index];
				}
			}
			else {
				if(keys[index].equals(key)) {
					return values[index];
				}
			}
		}
		
		throw new IllegalArgumentException("No element in keys is equal to key");
	}


	private boolean isEqual(Key leftKey, Key rightKey) {
		if(leftKey == null || rightKey == null) {
			if(leftKey == rightKey) {
				return true;
			}
		}
		else {
			if(leftKey.equals(rightKey)) {
				return true;
			}
		}
		return false;
	}



	public boolean isIn(Key key) {
		for(int index = 0; index < keys.length; index += 1) {
			if(keys[index] == null) {
				if(keys[index] == key) {
					return true;
				}
			}
			else {
				if(keys[index].equals(key)) {
					return true;
				}
			}
		}
		return false;
	}

	

	public void put(Key key, Value value) {
		boolean add = false; // checks if keys[] had key in it if it did it wont run to add at the end of the array
		if(key == null) {
			for(int index = 0; index < keys.length; index += 1) {
				if(keys[index] == null) {
					values[index] = value;
					add = true;
					break;
				}
			}
		}
		else {
			for(int i = 0; i < keys.length; i += 1) {
				if(keys[i] != null) {
					if(keys[i].equals(key)) {
						values[i] = value;
						add = true;
						break;
					}
				}
				else {
					if(keys[i] == key) {
						values[i] = value;
						add = true;
						break;
					}
				}
			}
		}

		if(!add) {
			if(count < keys.length - 1) {
				count += 1;
				keys[count] = key;
				values[count] = value;
			}
			else {
				throw new IllegalStateException("Cannot add an element to a full array");
			}
		}
	}

	private int where(Key key) {
		for(int index = 0; index < keys.length; index += 1) {
			if(keys[index] == null) {
				if(keys[index] == key) {
					return index;
				}
			}
			else {
				if(keys[index].equals(key)) {
					return index;
				}
			}
		}
		return -1;
	}
}
