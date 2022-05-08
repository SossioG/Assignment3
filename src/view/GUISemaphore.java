package view;

import control.Control;
import model.Consumer;
import model.FoodItem;
import model.Producer;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 * The GUI for assignment 3.
 */
public class GUISemaphore {
	/**
	 * These are the components you need to handle.
	 * You have to add listeners and/or code
	 * Static controls are defined inline
	 */
	private JFrame frame;				// The control.Main window
	private JProgressBar bufferStatus;	// The progressbar, showing content in buffer

	private Control control;

	//<editor-fold desc="Data for model.Producer Scan">
	private JButton btnStartScan, btnStopScan;
	private JLabel lblStatusScan;
	//</editor-fold>
	//<editor-fold desc="Data for producer Arla">
	private JButton btnStartArla, btnStopArla;
	private JLabel lblStatusArla;
	//</editor-fold>
	//<editor-fold desc="Data for producer AxFood">
	private JButton btnStartAxFood, btnStopAxFood;
	private JLabel lblStatusAxFood;
	//</editor-fold>

	//<editor-fold desc="Data for consumer ICA">
	private JLabel lblIcaItems, lblIcaWeight, lblIcaVolume, lblIcaStatus;
	private JTextArea lstIca;
	private JButton btnIcaStart, btnIcaStop;
	private JCheckBox chkIcaCont;
	//</editor-fold>
	//<editor-fold desc="Data for consumer COOP">
	private JLabel lblCoopItems, lblCoopWeight, lblCoopVolume, lblCoopStatus;		// load status
	private JTextArea lstCoop;
	private JButton btnCoopStart, btnCoopStop;
	private JCheckBox chkCoopCont;
	//</editor-fold>
	//<editor-fold desc="Data for consumer CITY GROSS">
	private JLabel lblCGItems, lblCGWeight, lblCGVolume, lblCGStatus;
	private JTextArea lstCG;
	private JButton btnCGStart, btnCGStop;
	private JCheckBox chkCGCont;
	//</editor-fold>

	/**
	 * Constructor, creates the window
	 */
	public GUISemaphore(Control control) {
		this.control = control;
		Start();
	}
	
	/**
	 * Starts the application
	 */
	public void Start() {
		frame = new JFrame();
		frame.setBounds(0, 0, 730, 526);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setTitle("Food Supply System");
		InitializeGUI();					// Fill in components
		frame.setVisible(true);
		frame.setResizable(false);			// Prevent user from change size
		frame.setLocationRelativeTo(null);	// Start middle screen
	}
	
	/**
	 * Sets up the GUI with components
	 */
	private void InitializeGUI() {

		// First create the three main panels
		JPanel pnlBuffer = new JPanel();
		pnlBuffer.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Storage"));
		pnlBuffer.setBounds(13, 403, 693, 82);
		pnlBuffer.setLayout(null);

		// Then create the progressbar, only component in buffer panel
		bufferStatus = new JProgressBar();
		bufferStatus.setBounds(155, 37, 500, 23);
		bufferStatus.setBorder(BorderFactory.createLineBorder(Color.black));
		bufferStatus.setForeground(Color.GREEN);
		pnlBuffer.add(bufferStatus);
		JLabel lblmax = new JLabel("Max capacity (items):");
		lblmax.setBounds(10, 42, 126,13);
		pnlBuffer.add(lblmax);
		frame.add(pnlBuffer);
		
		JPanel pnlProd = new JPanel();
		pnlProd.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Producers"));
		pnlProd.setBounds(13, 13, 229, 379);
		pnlProd.setLayout(null);
		
		JPanel pnlCons = new JPanel();
		pnlCons.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Consumers"));
		pnlCons.setBounds(266, 13, 440, 379);
		pnlCons.setLayout(null);
		
		// Now add the three panels to producer panel
		JPanel pnlScan = new JPanel();
		pnlScan.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Scan"));
		pnlScan.setBounds(6, 19, 217, 100);
		pnlScan.setLayout(null);
		
		// Content Scan panel
		btnStartScan = new JButton("Start Producing");
		btnStartScan.setBounds(10, 59, 125, 23);
		btnStartScan.addActionListener(l -> Producer.startScan());
		pnlScan.add(btnStartScan);
		btnStopScan = new JButton("Stop");
		btnStopScan.setBounds(140, 59, 65, 23);
		btnStopScan.addActionListener(l -> Producer.stopScan());
		pnlScan.add(btnStopScan);
		lblStatusScan = new JLabel("Idle");
		lblStatusScan.setBounds(10, 31, 200, 13);
		pnlScan.add(lblStatusScan);
		// Add Scan panel to producers		
		pnlProd.add(pnlScan);
		
		// The Arla panel
		JPanel pnlArla = new JPanel();
		pnlArla.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Arla"));
		pnlArla.setBounds(6, 139, 217, 100);
		pnlArla.setLayout(null);
		
		// Content Arla panel
		btnStartArla = new JButton("Start Producing");
		btnStartArla.setBounds(10, 59, 125, 23);
		btnStartArla.addActionListener(l -> Producer.startArla());
		pnlArla.add(btnStartArla);
		btnStopArla = new JButton("Stop");
		btnStopArla.setBounds(140, 59, 65, 23);
		btnStopArla.addActionListener(l -> Producer.stopArla());
		pnlArla.add(btnStopArla);
		lblStatusArla = new JLabel("Idle");
		lblStatusArla.setBounds(10, 31, 200, 13);
		pnlArla.add(lblStatusArla);
		// Add Arla panel to producers		
		pnlProd.add(pnlArla);
		
		// The AxFood Panel
		JPanel pnlAxfood = new JPanel();
		pnlAxfood.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"AxFood"));
		pnlAxfood.setBounds(6, 262, 217, 100);
		pnlAxfood.setLayout(null);
		
		// Content AxFood Panel
		btnStartAxFood = new JButton("Start Producing");
		btnStartAxFood.setBounds(10, 59, 125, 23);
		btnStartAxFood.addActionListener(l -> Producer.startAxFood());
		pnlAxfood.add(btnStartAxFood);
		btnStopAxFood = new JButton("Stop");
		btnStopAxFood.setBounds(140, 59, 65, 23);
		pnlAxfood.add(btnStopAxFood);
		lblStatusAxFood = new JLabel("Idle");
		lblStatusAxFood.setBounds(10, 31, 200, 13);
		btnStopAxFood.addActionListener(l -> Producer.stopAxFood());
		pnlAxfood.add(lblStatusAxFood);
		// Add Axfood panel to producers		
		pnlProd.add(pnlAxfood);
		// model.Producer panel done, add to frame
		frame.add(pnlProd);
		
		// Next, add the three panels to model.Consumer panel
		JPanel pnlICA = new JPanel();
		pnlICA.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"ICA"));
		pnlICA.setBounds(19, 19,415, 100);
		pnlICA.setLayout(null);
		
		// ========== Content ICA panel ========== //
		JPanel pnlLim = new JPanel();
		pnlLim.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Package Limits"));
		pnlLim.setBounds(6, 19, 107, 75);
		pnlLim.setLayout(null);
		JLabel lblItems = new JLabel("Items:");
		lblItems.setBounds(7, 20, 50, 13);
		pnlLim.add(lblItems);
		JLabel lblWeight = new JLabel("Weight:");
		lblWeight.setBounds(7, 35, 50, 13);
		pnlLim.add(lblWeight);
		JLabel lblVolume = new JLabel("Volume:");
		lblVolume.setBounds(7, 50, 50, 13);
		pnlLim.add(lblVolume);
		lblIcaItems = new JLabel("-");
		lblIcaItems.setBounds(60, 20, 47, 13);
		pnlLim.add(lblIcaItems);
		lblIcaWeight = new JLabel("-");
		lblIcaWeight.setBounds(60, 35, 47, 13);
		pnlLim.add(lblIcaWeight);
		lblIcaVolume = new JLabel("-");
		lblIcaVolume.setBounds(60, 50, 47, 13);
		pnlLim.add(lblIcaVolume);
		pnlICA.add(pnlLim);

		// Then rest of controls
		lstIca = new JTextArea();
		lstIca.setEditable(false);
		JScrollPane spane = new JScrollPane(lstIca);		
		spane.setBounds(307, 16, 102, 69);
		spane.setBorder(BorderFactory.createLineBorder(Color.black));
		pnlICA.add(spane);
		btnIcaStart = new JButton("Start Loading");
		btnIcaStart.setBounds(118, 64, 120, 23);
		btnIcaStart.addActionListener(l -> Consumer.startIca());
		pnlICA.add(btnIcaStart);
		btnIcaStop = new JButton("Stop");
		btnIcaStop.setBounds(240, 64, 60, 23);
		btnIcaStop.addActionListener(l -> Consumer.stopIca());
		pnlICA.add(btnIcaStop);
		lblIcaStatus = new JLabel("Idle");
		lblIcaStatus.setBounds(118, 16, 150, 23);
		pnlICA.add(lblIcaStatus);
		chkIcaCont = new JCheckBox("Continue load");
		chkIcaCont.setBounds(118, 39, 130, 17);
		pnlICA.add(chkIcaCont);
		// All done, add to consumers panel
		pnlCons.add(pnlICA);
		
		JPanel pnlCOOP = new JPanel();
		pnlCOOP.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"COOP"));
		pnlCOOP.setBounds(19, 139, 415, 100);
		pnlCOOP.setLayout(null);
		pnlCons.add(pnlCOOP);
		
		// ========== Content COOP panel ========== //
		JPanel pnlLimC = new JPanel();
		pnlLimC.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Package Limits"));
		pnlLimC.setBounds(6, 19, 107, 75);
		pnlLimC.setLayout(null);
		JLabel lblItemsC = new JLabel("Items:");
		lblItemsC.setBounds(7, 20, 50, 13);
		pnlLimC.add(lblItemsC);
		JLabel lblWeightC = new JLabel("Weight:");
		lblWeightC.setBounds(7, 35, 50, 13);
		pnlLimC.add(lblWeightC);
		JLabel lblVolumeC = new JLabel("Volume:");
		lblVolumeC.setBounds(7, 50, 50, 13);
		pnlLimC.add(lblVolumeC);
		lblCoopItems = new JLabel("-");
		lblCoopItems.setBounds(60, 20, 47, 13);
		pnlLimC.add(lblCoopItems);
		lblCoopWeight = new JLabel("-");
		lblCoopWeight.setBounds(60, 35, 47, 13);
		pnlLimC.add(lblCoopWeight);
		lblCoopVolume = new JLabel("-");
		lblCoopVolume.setBounds(60, 50, 47, 13);
		pnlLimC.add(lblCoopVolume);
		pnlCOOP.add(pnlLimC);

		// Then rest of controls
		lstCoop = new JTextArea();
		lstCoop.setEditable(false);
		JScrollPane spaneC = new JScrollPane(lstCoop);		
		spaneC.setBounds(307, 16, 102, 69);
		spaneC.setBorder(BorderFactory.createLineBorder(Color.black));
		pnlCOOP.add(spaneC);
		btnCoopStart = new JButton("Start Loading");
		btnCoopStart.setBounds(118, 64, 120, 23);
		btnCoopStart.addActionListener(l -> Consumer.startCoop());
		pnlCOOP.add(btnCoopStart);
		btnCoopStop = new JButton("Stop");
		btnCoopStop.setBounds(240, 64, 60, 23);
		btnCoopStop.addActionListener(l -> Consumer.stopCoop());
		pnlCOOP.add(btnCoopStop);
		lblCoopStatus = new JLabel("Idle");
		lblCoopStatus.setBounds(118, 16, 150, 23);
		pnlCOOP.add(lblCoopStatus);
		chkCoopCont = new JCheckBox("Continue load");
		chkCoopCont.setBounds(118, 39, 130, 17);
		pnlCOOP.add(chkCoopCont);
		// All done, add to consumers panel
		pnlCons.add(pnlCOOP);
		
		JPanel pnlCG = new JPanel();
		pnlCG.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"CITY GROSS"));
		pnlCG.setBounds(19, 262, 415, 100);
		pnlCG.setLayout(null);
		pnlCons.add(pnlCG);
		
		// ========== Content CITY GROSS panel ========== //
		JPanel pnlLimG = new JPanel();
		pnlLimG.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Package Limits"));
		pnlLimG.setBounds(6, 19, 107, 75);
		pnlLimG.setLayout(null);
		JLabel lblItemsG = new JLabel("Items:");
		lblItemsG.setBounds(7, 20, 50, 13);
		pnlLimG.add(lblItemsG);
		JLabel lblWeightG = new JLabel("Weight:");
		lblWeightG.setBounds(7, 35, 50, 13);
		pnlLimG.add(lblWeightG);
		JLabel lblVolumeG = new JLabel("Volume:");
		lblVolumeG.setBounds(7, 50, 50, 13);
		pnlLimG.add(lblVolumeG);
		lblCGItems = new JLabel("-");
		lblCGItems.setBounds(60, 20, 47, 13);
		pnlLimG.add(lblCGItems);
		lblCGWeight = new JLabel("-");
		lblCGWeight.setBounds(60, 35, 47, 13);
		pnlLimG.add(lblCGWeight);
		lblCGVolume = new JLabel("-");
		lblCGVolume.setBounds(60, 50, 47, 13);
		pnlLimG.add(lblCGVolume);
		pnlCG.add(pnlLimG);

		// Then rest of controls
		lstCG = new JTextArea();
		lstCG.setEditable(false);
		JScrollPane spaneG = new JScrollPane(lstCG);		
		spaneG.setBounds(307, 16, 102, 69);
		spaneG.setBorder(BorderFactory.createLineBorder(Color.black));
		pnlCG.add(spaneG);
		btnCGStart = new JButton("Start Loading");
		btnCGStart.setBounds(118, 64, 120, 23);
		btnCGStart.addActionListener(l -> Consumer.startCG());
		pnlCG.add(btnCGStart);
		btnCGStop = new JButton("Stop");
		btnCGStop.setBounds(240, 64, 60, 23);
		btnCGStop.addActionListener(l -> Consumer.stopCG());
		pnlCG.add(btnCGStop);
		lblCGStatus = new JLabel("Idle");
		lblCGStatus.setBounds(118, 16, 150, 23);
		pnlCG.add(lblCGStatus);
		chkCGCont = new JCheckBox("Continue load");
		chkCGCont.setBounds(118, 39, 130, 17);
		pnlCG.add(chkCGCont);
		// All done, add to consumers panel
		pnlCons.add(pnlCOOP);
		
		// Add consumer panel to frame
		frame.add(pnlCons);
	}

	// ========== Getters and setters ========== //

	public String getLblStatusScan() {
		return lblStatusScan.getText();
	}
	public void setLblStatusScan(String lblStatusScan) {
		this.lblStatusScan.setText(lblStatusScan);
	}

	public String getLblStatusArla() {
		return lblStatusArla.getText();
	}
	public void setLblStatusArla(String lblStatusArla) {
		this.lblStatusArla.setText(lblStatusArla);
	}

	public String getLblStatusAxFood() {
		return lblStatusAxFood.getText();
	}
	public void setLblStatusAxFood(String lblStatusAxFood) {
		this.lblStatusAxFood.setText(lblStatusAxFood);
	}





	public String getLblIcaItems() {
		return lblIcaItems.getText();
	}
	public void setLblIcaItems(String lblIcaItems) {
		this.lblIcaItems.setText(lblIcaItems);
	}

	public String getLblIcaWeight() {
		return lblIcaWeight.getText();
	}
	public void setLblIcaWeight(String lblIcaWeight) {
		this.lblIcaWeight.setText(lblIcaWeight);
	}

	public String getLblIcaVolume() {
		return lblIcaVolume.getText();
	}
	public void setLblIcaVolume(String lblIcaVolume) {
		this.lblIcaVolume.setText(lblIcaVolume);
	}

	public String getLblIcaStatus() {
		return lblIcaStatus.getText();
	}
	public void setLblIcaStatus(String lblIcaStatus) {
		this.lblIcaStatus.setText(lblIcaStatus);
	}




	public String getLblCoopItems() {
		return lblCoopItems.getText();
	}
	public void setLblCoopItems(String lblCoopItems) {
		this.lblCoopItems.setText(lblCoopItems);
	}

	public String getLblCoopWeight() {
		return lblCoopWeight.getText();
	}
	public void setLblCoopWeight(String lblCoopWeight) {
		this.lblCoopWeight.setText(lblCoopWeight);
	}

	public String getLblCoopVolume() {
		return lblCoopVolume.getText();
	}
	public void setLblCoopVolume(String lblCoopVolume) {
		this.lblCoopVolume.setText(lblCoopVolume);
	}

	public String getLblCoopStatus() {
		return lblCoopStatus.getText();
	}
	public void setLblCoopStatus(String lblCoopStatus) {
		this.lblCoopStatus.setText(lblCoopStatus);
	}


	public void setLstCG(String item) {
		this.lstCG.append(item + "\n");
	}

	public void setLstCoop(String item) {
		this.lstCoop.append(item + "\n");
	}

	public void setLstIca(String item) {
		this.lstIca.append(item + "\n");
	}

	public String getLblCGItems() {
		return lblCGItems.getText();
	}
	public void setLblCGItems(String lblCGItems) {
		this.lblCGItems.setText(lblCGItems);
	}

	public String getLblCGWeight() {
		return lblCGWeight.getText();
	}
	public void setLblCGWeight(String lblCGWeight) {
		this.lblCGWeight.setText(lblCGWeight);
	}

	public String getLblCGVolume() {
		return lblCGVolume.getText();
	}
	public void setLblCGVolume(String lblCGVolume) {
		this.lblCGVolume.setText(lblCGVolume);
	}

	public String getLblCGStatus() {
		return lblCGStatus.getText();
	}
	public void setLblCGStatus(String lblCGStatus) {
		this.lblCGStatus.setText(lblCGStatus);
	}

}
