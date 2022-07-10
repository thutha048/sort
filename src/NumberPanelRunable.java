

//Tao lop ke thua lop Runnable
public class NumberPanelRunable implements Runnable {
	private NumberPanel numberPanel = null;
	private boolean isFinished = false;
	
	public NumberPanelRunable(NumberPanel pn) {
		this.numberPanel = pn;
	}
	
	@Override
	public void run() {
		try {
			while(!Thread.currentThread().isInterrupted() && isFinished == false) {
				//dung switch case chon chuc nang
				switch(NumberFrame.SortType) {
				case BUBBLE_SORT:
					numberPanel.doBubbleSort();
					break;
				case SELECTION_SORT:
					numberPanel.doSelectionSort();
					break;
				
				}
				Thread.sleep(1000);
				isFinished = numberPanel.isIsFinished();
			}
			
		}catch(Exception e) {
			
		}
	}
}
