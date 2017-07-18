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
		String nombre, confir;
		int prio, j=0;
		boolean aye=false;
		String nombres[];
		ArrayList<Device> dvc = new ArrayList<Device>();
		dvc.add(new Device("main", Integer.MAX_VALUE-1));
		do{
			nombre=JOptionPane.showInputDialog(null, "Introduzca el nombre de un dispositivo que va a formar parte de la simulacion");
			prio=Integer.parseInt(JOptionPane.showInputDialog(null, "Introduzca el numero de prioridad"));
			dvc.add(new Device(nombre, prio));
			confir=JOptionPane.showInputDialog(null, "Desea agregar mas dispositivos? (S/N)");
		}while(confir=="S");
		
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
	    do{
	    	if (interruption==nombres[j]){
	    		interNum=j;
	    		aye=true;
	    	}
	    	j++;
	    }while(aye!=true);
	    
	    int time = Integer.parseInt(JOptionPane.showInputDialog(null, "Inserte el segundo en el que se hara la interrupcion:"));
	    int duration = Integer.parseInt(JOptionPane.showInputDialog(null, "Inserte cuantos segundos tardara la interrupcion:"));
	    itrs.add(new Interruption(time, dvc.get(interNum), duration));
	    confir=JOptionPane.showInputDialog(null, "Desea agregar mas interrupciones? (S/N)");
		}while(confir=="S");
		
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
	}

}
