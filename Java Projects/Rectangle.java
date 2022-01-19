//Nathan Recktenwald && Kevin Le x500
public class Rectangle extends Polygon{
	
	int a; int b;
	
	public Rectangle(int a, int b) {
		super(4,a,b,a,b);
		this.a = a;
		this.b = b; 
	}
	
	public int area() {
		return a * b;
	}
}
