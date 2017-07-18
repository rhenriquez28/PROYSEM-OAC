package com.dreamteam.entities;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.ScrollPane;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import como.dreamteam.gui.InterruptionSolverAnimation;

public class InterruptionSolver {
	private ArrayList<Interruption> interrupcciones;
	private ArrayList<FocusWindow> focusSimulation;

	public InterruptionSolver(ArrayList<Interruption> interrupcciones) {
		this.interrupcciones = interrupcciones;
		Collections.sort(interrupcciones, new Comparator<Interruption>()
				{
			
			
			@Override public int compare(Interruption i1, Interruption i2)
			{
				int c;
				c =i1.getDevice().getPriority() - i2.getDevice().getPriority();
				if(c==0)
				{
					c= i1.getTime() - i2.getTime();
				}
				return c;
			}
				});
		this.focusSimulation = new ArrayList<FocusWindow>() ;
	}

	public ArrayList<Interruption> getInterrupcciones() {
		return interrupcciones;
	}

	public void setInterrupcciones(ArrayList<Interruption> interrupcciones) {
		this.interrupcciones = interrupcciones;
	}

	public ArrayList<FocusWindow> getFocusSimulation() {
		return focusSimulation;
	}

	public void setFocusSimulation(ArrayList<FocusWindow> focusSimulation) {
		this.focusSimulation = focusSimulation;
	}
	
	public void solveSimulation()
	{
		int tick =0;
		Interruption focusInterruption =new Interruption();
		while(!checkInterruptionDuration())
		{
			
			for(Interruption itr : interrupcciones)
			{
				if(itr.getTime()<=tick && itr.getDuration()>0)
				{
					if((itr.getDevice().getPriority()<focusInterruption.getDevice().getPriority())||focusInterruption.getDuration()<=0)

					{
						focusInterruption=itr;
						if(focusSimulation.size()>0)
						{
							focusSimulation.get(focusSimulation.size()-1).setFocusEnding(tick);	
							focusSimulation.get(focusSimulation.size()-1).setInterruptionRemainingTime(focusInterruption.getDuration());
						}		
						focusSimulation.add(new FocusWindow(focusInterruption,tick));
					}
				}
			}
			tick++;
			focusInterruption.setDuration(focusInterruption.getDuration()-1);
		}
		focusSimulation.get(focusSimulation.size()-1).setFocusEnding(tick);	
		focusSimulation.get(focusSimulation.size()-1).setInterruptionRemainingTime(focusInterruption.getDuration());
	}
	public boolean checkInterruptionDuration()
	{
		boolean compleated = true;
		for(Interruption itr: interrupcciones)
		{
			if(itr.getDuration()>0)
			{
				compleated=false;
				break;
			}
				
			
		}
		return compleated;
		
	}
	
	public String[][] generarBitacora(int[] tiempos)
	{
		String[][] bitacora = new String[tiempos.length][5];
		for( int i= 0; i<tiempos.length;i++)
		{
			bitacora[i][0]= ""+i;
			for(FocusWindow fwindow: focusSimulation)
			{
				if(i>=fwindow.getFocusBegining() && i<=fwindow.getFocusEnding())
				{
					bitacora[i][1]=fwindow.getInterruption().getDevice().getName();
					if(fwindow.getInterruptionRemainingTime()>0)
					{
						bitacora[i][2]="S";
					}
					else
					{
						bitacora[i][2]="N";
					}
					bitacora[i][3]=fwindow.getFocusBegining()+"-"+fwindow.getFocusEnding();
					bitacora[i][4]=fwindow.getInterruptionRemainingTime()+"";
				}
			}
		}
		return bitacora;
	}
	/*public static void main(String args[])
	{
			ArrayList<Interruption> itrs = new ArrayList<Interruption>();
			Device com = new Device("Com3",12);
			Device coproc = new Device("co-proc",8);
			Device floppy = new Device("floppy",14);
			Device main = new Device("main", Integer.MAX_VALUE-1);
			Device red = new Device("red",4);
			itrs.add(new Interruption(0,main,10));
			itrs.add(new Interruption(4,com,8));
			itrs.add(new Interruption(9,coproc,5));
			itrs.add(new Interruption(12,floppy,15));
			itrs.add(new Interruption(25,red,12));
			itrs.add(new Interruption(35,com,3));
			itrs.add(new Interruption(43,coproc,5));
			
			InterruptionSolver iSolver = new InterruptionSolver(itrs);
			iSolver.solveSimulation();
			
			  SwingUtilities.invokeLater(new Runnable() {
		            public void run() {
		            	JFrame  frame = new JFrame();
		            	
		            	ScrollPane scroll = new ScrollPane();
		            	scroll.add(new InterruptionSolverAnimation(iSolver.getFocusSimulation()));
		            	frame.add(scroll,BorderLayout.CENTER);
		            	frame.getContentPane().add(scroll);
		    	 
		    			frame.setVisible(true);
		            }
		        });
	}*/
}
