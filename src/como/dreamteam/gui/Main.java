package como.dreamteam.gui;
import java.awt.BorderLayout;
import java.awt.ScrollPane;
import java.util.ArrayList;
import javax.swing.*;
import com.dreamteam.entities.Device;
import com.dreamteam.entities.Interruption;
import com.dreamteam.entities.InterruptionSolver;

public class Main {

	public static void main(String[] args)throws Exception {
		JOptionPane.showMessageDialog(null, "Bienvenido al simulador interrupciones 2k17");
		String nombre = null;
		int prio = 0, j=0;
		int main = 0;
		int time=0;
		int duration=0;
		int cantTiemposBitacora =0;
		boolean aye2=false;
		boolean aye=false;
		String nombres[];
		String options[] ={"Si", "No"};
		ArrayList<Device> dvc = new ArrayList<Device>();
		
		
		dvc.add(new Device("main", Integer.MAX_VALUE-1));
		//Itroducci�n de dispositivos y su prioridad
		do{
			nombre=JOptionPane.showInputDialog(null, "Introduzca el nombre de los un dispositivo que va a formar parte de la simulacion");
			
			do {
			
				try {
					prio=Integer.parseInt(JOptionPane.showInputDialog(null, "Introduzca el numero de prioridad"));
					aye2=false;
					}
				catch(Exception e) {
					JOptionPane.showMessageDialog(null, "Error, no ha introducido un numero de prioridad.");
					aye2=true;
					}
			
			}while(aye2==true);
				
			dvc.add(new Device(nombre, prio));
			j=JOptionPane.showOptionDialog(null, "Desea agregar mas dispositivos?", "Pregunta", 
				    JOptionPane.YES_NO_OPTION, 
				    JOptionPane.QUESTION_MESSAGE, 
				    null, 
				    options, 
				    options[0]);
			
		}while(j==JOptionPane.YES_OPTION);
		
		
		
		
		nombres = new String[dvc.size()];
		for(int i=1; i<dvc.size();i++)
		{
			Device tmpDevice = dvc.get(i);
			nombres[i] = tmpDevice.getName();
		}
		
		ArrayList<Interruption> itrs = new ArrayList<Interruption>();
		
		do 
		{
			try {
				main=Integer.parseInt(JOptionPane.showInputDialog(null, "Introduzca la duraci�n del Programa Principal?"));
				aye2=false;
				}
			catch(Exception e)
				{
				JOptionPane.showMessageDialog(null, "Disculpe. Debe ingresar un valor de tiempo. Intentelo otra vez.");
				aye2=true;
				}
		}while(aye2==true);
		
		
		itrs.add(new Interruption(0, dvc.get(0), main));
		do{
			JFrame frame = new JFrame();
			String interruption = (String) JOptionPane.showInputDialog(frame, 
				"Escoja el dispositivo que hara la interrupcion:",
				"Interrupcion",
				JOptionPane.QUESTION_MESSAGE, 
				null, 
				nombres, 
				nombres[0]);
	  /*  interruption=interruption+1;
	  JOptionPane.OK_CANCEL_OPTION,*/
			int interNum=0;
			j=0;
			do{
				if (interruption.equals(nombres[j])){
					interNum=j;
					aye=true;
				}
				j++;
			}while(aye!=true);
	    aye=false;
	    
	    
	    do 
	    {
	    	try {
	    		time = Integer.parseInt(JOptionPane.showInputDialog(null, "Inserte el segundo en el que se hara la interrupcion:"));
	    		aye2=false;
	    		}
	    	catch(Exception e) {
	    		JOptionPane.showMessageDialog(null, "Error, introduzca el segundo en el que se hara la interrupcion");
	    		aye2=true;
	    		}
	    }while(aye2==true);
	    
	    do {
			try {
				duration = Integer.parseInt(JOptionPane.showInputDialog(null, "Inserte la cantidad de segundos que tardara la interrupcion:"));
				aye2=false;
			}
			catch(Exception e) {
				JOptionPane.showMessageDialog(null, "Error, introduzca la cantidad de segundos que tarda la interrupci�n");
				aye2=true; 
			}
	    }while(aye2==true);
	    
			itrs.add(new Interruption(time, dvc.get(interNum), duration));
			j=JOptionPane.showOptionDialog(null, 
				"�Desea agregar mas interrupcciones?", 
			    "Pregunta", 
			    JOptionPane.YES_NO_OPTION, 
			    JOptionPane.QUESTION_MESSAGE, 
			    null, 
			    options, 
			    options[0]);
		}while(j==JOptionPane.YES_OPTION);
		
		InterruptionSolver iSolver = new InterruptionSolver(itrs);
		iSolver.solveSimulation();
		do {
			try{
			cantTiemposBitacora =Integer.parseInt(JOptionPane.showInputDialog(null, "�Cuantos tiempos necesita en la bitacora?"));
			aye2=false;
			}
			catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error, introduzca un valor numerico.");
			}
		}while(aye2==true);
		
		
		int tiempo[] = new int[cantTiemposBitacora];
		for(int i =0; i<cantTiemposBitacora; i++)
		{
			do{
				try{
					tiempo[i]=Integer.parseInt(JOptionPane.showInputDialog(null, "Inserte el tiempo "+(i)+" de la bitacora"));
					aye2=false;
					}
				catch(Exception e) {
				JOptionPane.showMessageDialog(null, "Error, introduzca valores numericos");
				aye2=true;
				}
		}while(aye2==true);
		}
		matrixResult mr = new matrixResult(iSolver.generarBitacora(tiempo), new String[]{"Tiempo","Area","I(S/N)","Rango","Tiempo restante"});
		mr.showme();
		
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	JFrame  frame = new JFrame();
            	
            	ScrollPane scroll = new ScrollPane();
            	scroll.add(new InterruptionSolverAnimation(iSolver.getFocusSimulation()));
            	frame.add(scroll,BorderLayout.CENTER);
            	frame.getContentPane().add(scroll);
            	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    			frame.setVisible(true);}});
	
		
	}
}
