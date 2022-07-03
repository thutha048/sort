import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class MyNumberPanel extends JPanel implements MouseListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean isFinished = false;

	//Declare array
	ArrayList<MyNumber> arr = new ArrayList<>();
	


	
	public boolean isIsFinished() {
		return isFinished;
	}

	/*public void setFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}*/

	public void addNumber(MyNumber number) {
		arr.add(number);
	}
	
	//Create value and positon of pic
	public void addNumbers(int num) {
		arr.clear();
		Random ran = new Random();
		for(int i=0; i<num;i++) {
			MyNumber number= new MyNumber(ran.nextInt(100), getGraphics());
			number.setX(i* MyNumber.YSIZE);
			number.setY(getBounds().getHeight()/2 - MyNumber.YSIZE);
			addNumber(number);
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		for(int i=0;i<arr.size();i++) {
			MyNumber number = arr.get(i);
			number.setGraph(g2);
			number.draw();
		}
	}
	
	//Create function to output the current position of value
	public void printInfor() {
		for(MyNumber number : arr) {
			System.out.println(number.getNum()+ "(x: "+ number.getX()+",y: "+ number.getY()+ " )");
			
		}
	}
	
	
	//Sort position of circles
	public synchronized void moveControl(MyNumber n1, MyNumber n2) {
		
		printInfor();
		//Khai bao position of pic
		double x1 =n1.getX();
		double y1 =n1.getY();
		double x2 =n2.getX();
		double y2 =n2.getY();
		
		try {
			//Use loop to up pic ( cho pic di len)
			for(int k=0; k<50;k++) {
				n1.setY(y1+k);
				n2.setY(y2-k);
				Thread.sleep(10);
				
			//Update the new position of pic
				repaint();
			}
			
			int k=0;
			//cho pic di ngang
			while(n1.getX() <= x2-1 ) {
				k++;
				n1.setX(x1+k);
				n2.setX(x2-k);
				Thread.sleep(10);
				repaint();
			}
			y1 = n1.getY();
			y2 = n2.getY();
			
			//cho pic di xuong
			for(k=1; k<50;k++) {
				n1.setY(y1-k);
				n2.setY(y2+k);
				Thread.sleep(10);
				repaint();
			}
		}catch(InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
	}
	
	
	//Tao ham dong bo
	//Tao ham sap xep cac gtri tu nho den lon
	public synchronized void doBubbleSort() {
		try {
			isFinished = false;
			//ktra giatri
			for(int i=0;i<arr.size();i++) {
				for(int j=i+1; j<arr.size(); j++) {
					MyNumber n1= arr.get(i);
					MyNumber n2= arr.get(j);
					if(n1.getNum()>n2.getNum()) {
						//call moveControl to change position of pic
						moveControl(n1,n2);
						MyNumber nt1 = n1;
						n1=n2;
						n1=nt1;
						arr.set(i, n1);
						arr.set(j,n2);
					}
				}
				
			}
			isFinished =true;
			System.out.println("------------");
			printInfor();
			MyNumber.COLOR1= Color.BLUE;
			repaint();
		}catch(Exception e) {
			
		}
	}
	
	//Mot so ham sort khac...........................
	public synchronized void doSelectionSort() {
		try {
			isFinished = false;
			//duyet tu 1 den size-1
			for(int i=0;i<arr.size()-1;i++) {
				//set min tai vi tri dau
				int minId=i;
				//loop tu i+1 den size
				for(int j=i+1;j<arr.size();j++) {
					//ktra neu
					if(arr.get(minId).getNum()> arr.get(j).getNum()) {
						minId =j;
					}
				}
				//Neu minId # i thi thay doi vi tri cua hinh
				// swap
				if(minId!= i) {
					MyNumber n1 =arr.get(minId);
					MyNumber n2 = arr.get(i);
					moveControl(n2,n1);
					MyNumber nt1 = n1;
					n1=n2;
					n2 = nt1;
					arr.set(minId, n1);
					arr.set(i, n2);
					
				}
			}
			
			isFinished =true;
			System.out.println("----------");
			printInfor();
			MyNumber.COLOR1 = Color.blue;
			repaint();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		for(MyNumber number:arr) {
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

/*
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}*/

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
