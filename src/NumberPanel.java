import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class NumberPanel extends JPanel implements MouseListener{
	private static final long serialVersionUID = 1L;
	private boolean isFinished = false;
	//Declare array
	ArrayList<Number> arr = new ArrayList<>();
	int speed;
	
	public boolean isIsFinished() {
		return isFinished;
	}
	public void addNumber(Number number) {
		arr.add(number);
	}
	
	//Create value and positon of pic
	public void addNumbers(int num) {
		arr.clear();
		Random ran = new Random();
		for(int i=0; i<num;i++) {
			//limit of num is 100
			Number number= new Number(ran.nextInt(100), getGraphics());
			number.setX(i* Number.YSIZE);
			number.setY(getBounds().getHeight()/2 - Number.YSIZE);
			addNumber(number);
		}
	}
	
	//create speed
	public int createSpeed(int num) {
		return speed = num;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		for(int i=0;i<arr.size();i++) {
			Number number = arr.get(i);
			number.setGraph(g2);
			number.draw();
		}
	}
	
	//Create function to output the current position of value
	public void printInfor() {
		for(Number number : arr) {
			System.out.println(number.getNum()+ "(x: "+ number.getX()+", y: "+ number.getY()+ ")");
			
		}
	}
	
	//Sort position of circles
	public synchronized void moveControl(Number n1, Number n2) {	
		printInfor();
		//Khai bao position of pic
		double x1 =n1.getX();
		double y1 =n1.getY();
		double x2 =n2.getX();
		double y2 =n2.getY();
		//int speed ;
		
		try {
			//Use loop to up pic ( cho pic di len)
			//50 khoang cach di len
			for(int k=0; k<50;k++) {
				n1.setY(y1+k);
				n2.setY(y2-k);
				Thread.sleep(speed);
				
			//Update the new position of pic
				repaint();
			}
			
			int k=0;
			//cho pic di ngang
			while(n1.getX() <= x2-1 ) {
				k++;
				n1.setX(x1+k);
				n2.setX(x2-k);
				Thread.sleep(speed);
				repaint();
			}
			y1 = n1.getY();
			y2 = n2.getY();
			
			//cho pic di xuong
			for(k=1; k<50;k++) {
				n1.setY(y1-k);
				n2.setY(y2+k);
				Thread.sleep(speed);
				repaint();
			}
		}catch(InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
	}
	
	//khi 2 so da dc thoa man thi ta cho no hien len 
	public synchronized void moveControlSame(Number n1, Number n2) {
		try {
			printInfor();
			//Khai bao position of pic 
			double y1 =n1.getY();
			double y2 =n2.getY();
			
			//up
			for(int k=0; k<50;k++) {
				n1.setY(y1+k);
				n2.setY(y2-k);
				Thread.sleep(speed);				
			//Update the new position of pic
				repaint();
			}
			y1 = n1.getY();
			y2 = n2.getY();
			
			//cho pic di xuong
			for(int k=1; k<50;k++) {
				n1.setY(y1-k);
				n2.setY(y2+k);
				Thread.sleep(speed);
				repaint();
			}
		
		}catch(InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
	}
	
	public synchronized void doBubbleSort() {
		try {
			isFinished = false;
			//ktra giatri
			for(int i=0;i<arr.size()-1;i++) {
				for(int j=0; j<arr.size()-i-1; j++) {
					Number n1= arr.get(j);
					Number n2= arr.get(j+1);
					if(n1.getNum()>n2.getNum()) {
						//call moveControl to change position of pic
						moveControl(n1,n2);
						Number nt1 = n1;
						n1=n2;
						n2=nt1;
						arr.set(j, n1);
						arr.set(j+1,n2);
					}else {
						//neu n1 va n2 da thoa man thu tu
						moveControlSame(n1,n2);
					}
				}		
			}
			isFinished =true;
			System.out.println("------------");
			printInfor();
			Number.COLOR1= Color.BLUE;
			repaint();
		}catch(Exception e) {
			
		}
	}
	
	//Mot so ham sort khac...........................
	public synchronized void doSelectionSort() {
		try {
			isFinished = false;
			for(int i=0;i<arr.size()-1;i++) {
				//set min tai vi tri dau
				int minId=i;
				for(int j=i+1;j<arr.size();j++) {
					if( arr.get(j).getNum() < arr.get(minId).getNum()) {
						minId =j;
					}
				}
				//Neu minId # i thi thay doi vi tri cua hinh
				// swap
				Number n1 =arr.get(i);
				Number n2 = arr.get(minId);
				if(minId!= i) {
					moveControl(n1,n2);
					Number nt1 = n2;
					n2=n1;
					n1 = nt1;
					arr.set(i, n1);
					arr.set(minId, n2);
					
				}else {
					//neu n1 va n2 da thoa man thu tu
					moveControlSame(n1,n2);
				}
			}
			
			isFinished =true;
			System.out.println("----------");
			printInfor();
			Number.COLOR1 = Color.blue;
			repaint();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		for(Number number:arr) {
			boolean b=number.getFace().contains(e.getPoint().getX(), e.getPoint().getY());
			if(b==true) {
				System.out.println(b+" - "+number);
				Graphics2D g = (Graphics2D) number.getGraph();
				g.setColor(Color.YELLOW);
				g.fill(number.getFace());
				this.repaint(number.getFace().getBounds());
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub	
	}
}
