package como.dreamteam.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.Timer;

import com.dreamteam.entities.Device;
import com.dreamteam.entities.FocusWindow;
import javax.swing.JScrollPane;

public class InterruptionSolverAnimation extends JPanel {
	private Timer timer;
	private static final int TIMERDELAY=100;
	private int time;
	private ArrayList<Graphics> rectangles;
	private ArrayList<FocusWindow> fwindow;
	private ArrayList<Device> uniqueDevices;
	private Map<Device,Integer[]> devicePositions;
	
	public InterruptionSolverAnimation(ArrayList<FocusWindow> fw)
	{
		devicePositions = new HashMap<Device,Integer[]>();
		uniqueDevices = new ArrayList<Device>();
		
		fwindow=fw;
		ActionListener redrawTask = new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				time ++;
				redrawOnScroll();
				
			}
		};
		rectangles = new ArrayList<Graphics>();
		time = 0;
		setLayout(new BorderLayout());
		timer=new Timer(TIMERDELAY,redrawTask);
		for(FocusWindow ifw: fwindow)
		{
			boolean found = false;
			for(Device idev: uniqueDevices)
			{
				if(idev==ifw.getInterruption().getDevice())
				{
					found = true;
					break;
				}
			}
			if(!found){uniqueDevices.add(ifw.getInterruption().getDevice());}
		}
		revalidate();
		timer.start();
	}
	@Override
    public Dimension getPreferredSize() {
        return new Dimension(3000, (fwindow.get(fwindow.size()-1).getFocusEnding()+4)*20);
    }

	
	@Override
	public void paintComponent(Graphics g) {
	   super.paintComponent(g);
	   Graphics2D g2 = (Graphics2D) g;
	   g2.setColor(Color.RED);
	   g2.setFont(new Font("TimesRoman", Font.PLAIN,24));
	   int i=0;
	   int lastStringWidth =0;
	   int lastStringPos=10;
	   
	   for(Device idev: uniqueDevices)
		{
			g2.drawString(idev.getName()+"-"+idev.getPriority(),lastStringWidth+lastStringPos+20,24);
			lastStringPos=lastStringWidth+lastStringPos+20;
			lastStringWidth=g.getFontMetrics().stringWidth(idev.getName()+"-"+idev.getPriority());
			devicePositions.put(idev,new Integer[]{lastStringPos,lastStringWidth});
			i=i+10;
			
			
		}
	   g2.setFont(new Font("TimesRoman", Font.PLAIN,20));
	   g2.drawString("0",0,(4)*20);
	   for(int y =1; y<=fwindow.get(fwindow.size()-1).getFocusEnding();y++)
		{
			g2.drawLine(0, (y+4)*20,lastStringPos+lastStringWidth, (y+4)*20);
			g2.drawString(y+"",0,(y+4)*20);
		}
	}
	public void redraw()
	{
		Graphics2D g2= (Graphics2D)this.getGraphics();
		for(FocusWindow ifw: fwindow)
		{
			if(time>=ifw.getFocusBegining() && time <= ifw.getFocusEnding())
			{
				Device idev = ifw.getInterruption().getDevice();
				Integer[] pos = devicePositions.get(idev);
				g2.setColor(Color.BLUE);
				g2.fillRect(pos[0], (time+1)*20, pos[1], 20);
			}
		}
	}
		public void redrawOnScroll()
		{
			Graphics2D g2= (Graphics2D)this.getGraphics();
			for(int time =0;time<fwindow.get(fwindow.size()-1).getFocusEnding() && time <=this.time;time++)
			{
				for(FocusWindow ifw: fwindow)
				{
					if(time>=ifw.getFocusBegining() && time < ifw.getFocusEnding())
					{
						Device idev = ifw.getInterruption().getDevice();
						Integer[] pos = devicePositions.get(idev);
						g2.setColor(Color.BLUE);
						g2.fillRect(pos[0], (time+4)*20, pos[1], 20);
					}
				}
			}
			
	}
}
