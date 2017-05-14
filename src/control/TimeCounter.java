package control;

import javax.swing.JLabel;

public class TimeCounter extends Thread {
	private StringBuilder time;
	private int mins;
	private int secs;
	private JLabel label;

	public TimeCounter(JLabel label) {
		time = new StringBuilder();
		this.label = label;
		mins = 0;
		secs = 0;
	}

	public String getTime() {
		time.setLength(0);
		time = new StringBuilder();
		if (mins < 10)
			time.append("0" + mins);
		else if (mins < 60)
			time.append(mins);
		time.append(':');

		if (secs < 10)
			time.append("0" + secs);
		else if (secs < 60)
			time.append(secs);
		else {
			secs = 0;
			mins++;
			time.setLength(0);
			time.append(getTime());
		}

		return time.toString();
	}

	@Override
	public void run() {
		while (true) {
			secs++;
			try {
				label.setText(getTime());
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void reboot(){
		mins = 0;
		secs = 0;
		time.setLength(0);
		time = new StringBuilder();
	}
}
