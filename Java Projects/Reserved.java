// Nathan Recktenwald X500
public class Reserved {
	private static final String [] reserved = 
		   { "and", 
		     "begin", 
		     "define", 
		     "do", 
		     "else", 
		     "end", 
		     "if", 
		     "not", 
		     "or", 
		     "return", 
		     "then", 
		     "while" }; 
		 
		  private static int hash(String name) 
		  { 
		    int h = 0;
		    h = 179 * h + (name.charAt(1) + name.charAt(0));
		    return Math.abs(h + name.charAt(0) + name.charAt(1) % (reserved.length * name.length()));
		  } 
		 
		  public static void main(String [] args) 
		  { 
		    for (int index = 0; index < reserved.length ; index += 1) 
		    { 
		      System.out.print("h(\"" + reserved[index] + "\") = "); 
		      System.out.print(hash(reserved[index])); 
		      System.out.println(); 
		    } 
		  } 
}
