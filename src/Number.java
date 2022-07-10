import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class Number {
	//Size of circle
	public static final int XSIZE =50;
	public static final int YSIZE =50;
	//Position of center of circle 
	private double x=0;
	private double y=0;
	
	//Declare value on pic
	private int num=0;
	
	private Graphics graph = null;
	
	//draw oval use Ellipse2D
	private Ellipse2D face;
	
	public Ellipse2D getShape() {
		//Declare ellipse2d 
		face = new Ellipse2D.Double(x,y,XSIZE,YSIZE);
		//x,y :center ; XSIZE,YSIZE : radius
		return face;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Graphics getGraph() {
		return graph;
	}

	public void setGraph(Graphics graph) {
		this.graph = graph;
	}

	public Ellipse2D getFace() {
		return face;
	}

	public void setFace(Ellipse2D face) {
		this.face = face;
	}

	public static int getXsize() {
		return XSIZE;
	}

	public static int getYsize() {
		return YSIZE;
	}
	
	
	//Create constructor truyen tham so
	public Number( int num, Graphics graph) {
		this.graph = graph;
		this.num =num;
	}
	
	//Declare color
	public static Color COLOR1= Color.BLACK;
	public static Color COLOR2= Color.WHITE;
	
	//Create function draw to set color and value for pic
	public void draw() {
		if(graph!= null) {
			Graphics2D g =(Graphics2D) graph;
			g.setColor(COLOR1);
			g.fill(getShape());
			
			//set color for word:
			Font font = new Font("arial", Font.ITALIC | Font.BOLD, 15);
			g.setFont(font);
			g.setColor(COLOR2);
			g.drawString(num+"", (float)x+18, (float) y+30); //set word into center of circle
		}
	}
	
	public Number() {
		this.num=0;
		this.graph=null;
	}
	
	@Override
	public String toString() {
		return this.num+"";
	}	
}
