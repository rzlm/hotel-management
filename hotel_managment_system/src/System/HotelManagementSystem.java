
package System;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class HotelManagementSystem extends JFrame {
	private CardLayout cardLayout;
	private JPanel mainPanel;
	private JPanel menuPanel;

	public HotelManagementSystem() {
		// Setting up the frame
		setTitle("Snuggly Inn - Hotel Management System");
		setSize(800, 600);
		setBackground(new Color(240, 240, 240));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// getContentPane().setBackground(new Color(240, 240, 240));
		setLayout(new BorderLayout());

		cardLayout = new CardLayout();
		mainPanel = new JPanel(cardLayout);

		// Menu panel setup
		menuPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		// Title label
		JLabel titleLabel = new JLabel("Snuggly Inn");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Roboto", Font.BOLD, 25));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		menuPanel.add(titleLabel, gbc);

		// Menu label
		JLabel menuLabel = new JLabel("Menu");
		menuLabel.setHorizontalAlignment(SwingConstants.CENTER);
		menuLabel.setFont(new Font("Roboto", Font.PLAIN, 20));
		gbc.gridy = 1;
		menuPanel.add(menuLabel, gbc);

		// Adding buttons to menu panel
		addMenuButton(menuPanel, "Manage Guest", 2, new GuestGUI(), "Guest");
		addMenuButton(menuPanel, "Manage Reservation", 3, new ReservationGUI(), "Reservation");
		addMenuButton(menuPanel, "Manage Room", 4, new RoomGUI(), "Room");
		addMenuButton(menuPanel, "Manage Bill", 5, new BillGUI(), "Bill");
		addMenuButton(menuPanel, "Manage Phone Service", 6, new PhoneServiceGUI(), "PhoneService");

		// Adding menu panel to main panel
		mainPanel.add(new JPanel(), "Empty"); // An empty panel for initial view
		mainPanel.add(new GuestGUI(), "Guest");
		mainPanel.add(new ReservationGUI(), "Reservation");
		mainPanel.add(new RoomGUI(), "Room");
		mainPanel.add(new BillGUI(), "Bill");
		mainPanel.add(new PhoneServiceGUI(), "PhoneService");

		// Split pane to hold menu and main panels
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, menuPanel, mainPanel);
		splitPane.setDividerLocation(200); // Set initial divider location
		add(splitPane, BorderLayout.CENTER);

		// Show the menu initially
		cardLayout.show(mainPanel, "Empty");
	}

	private void addMenuButton(JPanel panel, String text, int gridY, JPanel targetPanel, String cardName) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.gridx = 0;
		gbc.gridy = gridY;
		gbc.gridwidth = 1;

		JButton button = new JButton(text);
		button.setBackground(new Color(33, 150, 243)); // Blue color
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Roboto", Font.PLAIN, 16));
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(mainPanel, cardName);
			}
		});
		panel.add(button, gbc);
	}

	public static void main(String[] args) {
		try {
			for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Create and display the GUI
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new LoginGUI().setVisible(true);
			}
		});
	}
}
