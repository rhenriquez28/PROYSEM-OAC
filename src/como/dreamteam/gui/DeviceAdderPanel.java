package como.dreamteam.gui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.dreamteam.entities.Device;

import javax.swing.JScrollPane;

public class DeviceAdderPanel extends JPanel{
	
	public DeviceAdderPanel(Object[][] devices ,String[] names)
	{
		setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.5);
		add(splitPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		splitPane.setLeftComponent(panel);
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane.setRightComponent(scrollPane);
		

		JTable table = new JTable(new DefaultTableModel(devices,names));
		scrollPane.add(table);
		
	}

}
