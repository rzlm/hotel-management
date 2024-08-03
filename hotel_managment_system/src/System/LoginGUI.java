
package System;

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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class LoginGUI extends JFrame {
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JButton loginButton;

	public LoginGUI() {
		// Setting up the frame
		setTitle("Snuggly In - Login");
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // Center the frame
		setLayout(new GridBagLayout());
		getContentPane().setBackground(new Color(240, 240, 240));

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		// Adding Form title
		JLabel formLabel = new JLabel("Administrator Login");
		formLabel.setFont(new Font("Roboto", Font.BOLD, 20));
		formLabel.setHorizontalAlignment(SwingConstants.CENTER);

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		add(formLabel, gbc);

		// Adding username field
		JLabel usernameLabel = new JLabel("Username:");
		usernameLabel.setFont(new Font("Roboto", Font.PLAIN, 16));

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		add(usernameLabel, gbc);

		usernameField = new JTextField(20);
		gbc.gridx = 1; // Column 1
		gbc.gridy = 1; // Same row as usernameLabel
		gbc.gridwidth = 1; // One column
		add(usernameField, gbc);

		// Adding password field
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setFont(new Font("Roboto", Font.PLAIN, 16));
		gbc.gridx = 0;
		gbc.gridy = 2; // Adjusted row index
		gbc.gridwidth = 1; // One column
		add(passwordLabel, gbc);

		passwordField = new JPasswordField(20);
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		add(passwordField, gbc);

		// Adding login button
		loginButton = new JButton("Login");
		loginButton.setFont(new Font("Roboto", Font.BOLD, 16));
		loginButton.setBackground(new Color(33, 150, 243)); // Blue color
		loginButton.setForeground(Color.WHITE);
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.gridwidth = 3;
		add(loginButton, gbc);

		// Login button action
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Perform login validation here

				// On successful login, navigate to HotelManagementSystem
				new HotelManagementSystem().setVisible(true);
				dispose(); // Close the login window
			}
		});
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
