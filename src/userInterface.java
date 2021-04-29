import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class userInterface extends javax.swing.JFrame {

	public userInterface() {
		initComponents();
	}

	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		txtBusStop = new javax.swing.JTextField();
		txtTripSearch = new javax.swing.JTextField();
		txtStart = new javax.swing.JTextField();
		jScrollPane1 = new javax.swing.JScrollPane();
		tblStudents = new javax.swing.JTable();
		btnSubmit = new javax.swing.JButton();
		btnRefresh = new javax.swing.JButton();
		btnUpdate = new javax.swing.JButton();

		jLabel4 = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Algorithms Group Assignment");
		setResizable(false);

		jLabel1.setFont(new java.awt.Font("Verdana", 0, 12));
		jLabel1.setText(" Bus Stop");

		jLabel2.setFont(new java.awt.Font("Verdana", 0, 12));
		jLabel2.setText(" Trip Search");

		jLabel3.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
		jLabel3.setText(" Start, Finsh");

		tblStudents.setCellSelectionEnabled(true);
		tblStudents.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				// tblStudentsMouseClicked(evt);
			}
		});

		btnSubmit.setText("Submit");
		btnSubmit.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
		btnSubmit.setIconTextGap(0);
		btnSubmit.setInheritsPopupMenu(true);
		btnSubmit.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnSaveActionPerformed(evt);
			}
		});

		btnRefresh.setText("Refresh");
		btnRefresh.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
		btnRefresh.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				// btnDelete1ActionPerformed(evt);
			}
		});

		jLabel4.setFont(new java.awt.Font("Tahoma", 0, 28));
		jLabel4.setText("BUS STOP SEARCH");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(layout.createSequentialGroup()
														.addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addGap(18, 18, 18)
														.addComponent(txtBusStop, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
												.addGroup(layout.createSequentialGroup()
														.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																.addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
																.addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
														.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
														.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																.addComponent(txtStart, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
																.addComponent(txtTripSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))))
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE))
								.addGroup(layout.createSequentialGroup()
										.addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(27, 27, 27)
										.addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap())
				.addGroup(layout.createSequentialGroup()
						.addGap(218, 218, 218)
						.addComponent(jLabel4)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(txtBusStop, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(30, 30, 30)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(txtTripSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(30, 30, 30)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(txtStart, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(39, 39, 39)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
												.addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
								.addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addContainerGap(29, Short.MAX_VALUE))
				);

		pack();
		setLocationRelativeTo(null);
	}
	public void alert(String msg) {
		JOptionPane.showMessageDialog(rootPane, msg);
	}   
	private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {
		String bStop = txtBusStop.getText().trim();
		String tSearch = txtTripSearch.getText().trim();
		String startFinish = txtStart.getText().trim();

		if (bStop.isEmpty() && tSearch.isEmpty() && startFinish.isEmpty()) {
			alert("Please enter a Bus Stop, Trip Search or Start/Finish time");
		}
	}


	public static void main(String args[]) throws FileNotFoundException {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(userInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(userInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(userInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(userInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}


		java.awt.EventQueue.invokeLater(() -> {
			new userInterface().setVisible(true);
		});
		
		TST tst = new TST("stops.txt");
		tst.getStopInformation("SHAUGHNESSY");
		
		
		 Dijkstra algorithm = new Dijkstra("stops.txt","transfers.txt");
		
			ArrayList<Vertex> vertexes = algorithm.vertexes;   /* vertex objects for the Algorithm */
			ArrayList<String> startStops = algorithm.startStops; /* Start point of the edge */
			ArrayList<String> endStops = algorithm.endStops; /* End point of the edge */
		
			ArrayList<Double> weight = algorithm.weight; /* weight of the edge defined in startStops and End Stops */
		
			Double cost = algorithm.cost; /* for storing the cost of the path taken, initially 0 */ 
		 
		   /////********* To be realised that the vertexes have been initalised but the vertexes still have no  ******\\\\\\\\\\
 ////////********  edges, hence I have provided the above variables to create the edges and then initalise the vertexes  *******\\\\\\\
		 
		 String inputSource = ""; //User input holder for source stop
		 String inputDestination = ""; // User Input holder for destination stop
		 
		 Vertex source = new Vertex(inputSource); //creates a vertex object of the source stop
		 Vertex destination = new Vertex(inputDestination);//creates a vertex object of the destination stop
		 
		 algorithm.computePath(source); //compute shortest path from source to all vertexes
		 // Printing the result- Display in the Display area of the UI
		  cost = algorithm.cost; 
		 
		 System.out.println(algorithm.getShortestPathTo(destination) + "\n" + cost);
		//getShortesPathTo(Destination), returns the path of the shortest path between the source and the destination
		 // algorithm.cost returns the cost of the path by the shortest path algorithm.
		
	}
	private javax.swing.JButton btnRefresh;
	private javax.swing.JButton btnSubmit;
	private javax.swing.JButton btnUpdate;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable tblStudents;
	private javax.swing.JTextField txtBusStop;
	private javax.swing.JTextField txtTripSearch;
	private javax.swing.JTextField txtStart;

}
