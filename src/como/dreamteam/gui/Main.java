package como.dreamteam.gui;
import java.awt.BorderLayout;
import java.awt.ScrollPane;
import java.util.ArrayList;
import javax.swing.*;
import com.dreamteam.entities.Device;
import com.dreamteam.entities.Interruption;
import com.dreamteam.entities.InterruptionSolver;

public class Main {

	public static void main(String[] args) {
		JOptionPane.showMessageDialog(null, "Bienvenido al simulador 2017");
		String nombre;
		int prio, j=0;
		boolean aye=false;
		String nombres[];
		String options[] ={"Si", "No"};
		ArrayList<Device> dvc = new ArrayList<Device>();
		dvc.add(new Device("main", Integer.MAX_VALUE-1));
		do{
			nombre=JOptionPane.showInputDialog(null, "Introduzca el nombre de los un dispositivo que va a formar parte de la simulacion");
			prio=Integer.parseInt(JOptionPane.showInputDialog(null, "Introduzca el numero de prioridad"));
			dvc.add(new Device(nombre, prio));
			j=JOptionPane.showOptionDialog(null, 
					"Desea agregar mas dispositivos?", 
				    "Pregunta", 
				    JOptionPane.YES_NO_OPTION, 
				    JOptionPane.QUESTION_MESSAGE, 
				    null, 
				    options, 
				    options[0]);
		}while(j==JOptionPane.YES_OPTION);
		
		nombres = new String[dvc.size()];
		for(int i=1; i<dvc.size();i++){
			Device tmpDevice = dvc.get(i);
			nombres[i] = tmpDevice.getName();
		}
		
		ArrayList<Interruption> itrs = new ArrayList<Interruption>();
		int main=Integer.parseInt(JOptionPane.showInputDialog(null, "Cuanto demora el programa principal?"));
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
			int time = Integer.parseInt(JOptionPane.showInputDialog(null, "Inserte el segundo en el que se hara la interrupcion:"));

			int duration = Integer.parseInt(JOptionPane.showInputDialog(null, "Inserte cuantos segundos tardara la interrupcion:"));

			itrs.add(new Interruption(time, dvc.get(interNum), duration));
			j=JOptionPane.showOptionDialog(null, 
				"Desea agregar mas interrupcciones?", 
			    "Pregunta", 
			    JOptionPane.YES_NO_OPTION, 
			    JOptionPane.QUESTION_MESSAGE, 
			    null, 
			    options, 
			    options[0]);
		}while(j==JOptionPane.YES_OPTION);
		
		InterruptionSolver iSolver = new InterruptionSolver(itrs);
		iSolver.solveSimulation();
		int cantTiemposBitacora =Integer.parseInt(JOptionPane.showInputDialog(null, "¿Cuantos tiempos necesita en la bitacora?"));
		int tiempo[] = new int[cantTiemposBitacora];
		for(int i =0; i<cantTiemposBitacora; i++)
		{
			tiempo[i]=Integer.parseInt(JOptionPane.showInputDialog(null, "Inserte el tiempo "+(i+1)+" de la bitacora"));
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
    			frame.setVisible(true);
            }
});
	}

}
