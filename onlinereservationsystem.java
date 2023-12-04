package onlinereservationsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class onlinereservationsystem extends JFrame {
    private static boolean[] seats = new boolean[15];
    private JTextArea seatMapTextArea;

    public onlinereservationsystem() {
        setTitle("Online Reservation System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLayout(new BorderLayout());

        seatMapTextArea = new JTextArea();
        seatMapTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(seatMapTextArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 2, 10, 10)); // 2 rows, 2 columns

        JButton viewButton = new JButton("View Seat Map");
        JButton reserveButton = new JButton("Reserve Seat");
        JButton cancelButton = new JButton("Cancel Reservation");
        JButton exitButton = new JButton("Exit");

        buttonPanel.add(viewButton);
        buttonPanel.add(reserveButton);
        buttonPanel.add(cancelButton);
        buttonPanel.add(exitButton);

        add(buttonPanel, BorderLayout.SOUTH);

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewSeatMap();
            }
        });

        reserveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reserveSeat();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelReservation();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void viewSeatMap() {
        StringBuilder seatMap = new StringBuilder("CURRENT SEAT MAP:\n");

        for (int i = 0; i < seats.length; i++) {
            if (seats[i]) {
                seatMap.append("X ");
            } else {
                seatMap.append(i + 1).append(" ");
            }
        }

        seatMapTextArea.setText(seatMap.toString());
    }

    private void reserveSeat() {
        try {
            int seatNumber = Integer.parseInt(showInputDialog("Enter seat number to reserve:"));
            if (seatNumber < 1 || seatNumber > 15) {
                JOptionPane.showMessageDialog(this, "Invalid seat number!");
            } else if (seats[seatNumber - 1]) {
                JOptionPane.showMessageDialog(this, "Seat already reserved!");
            } else {
                seats[seatNumber - 1] = true;
                JOptionPane.showMessageDialog(this, "Seat reserved!");
                viewSeatMap();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input! Please enter a number.");
        }
    }

    private void cancelReservation() {
        try {
            int seatNumber = Integer.parseInt(showInputDialog("Enter seat number to cancel reservation:"));
            if (seatNumber < 1 || seatNumber > 15) {
                JOptionPane.showMessageDialog(this, "Invalid seat number!");
            } else if (!seats[seatNumber - 1]) {
                JOptionPane.showMessageDialog(this, "Seat not reserved!");
            } else {
                seats[seatNumber - 1] = false;
                JOptionPane.showMessageDialog(this, "Reservation canceled!");
                viewSeatMap();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input! Please enter a number.");
        }
    }

    private String showInputDialog(String message) {
        return JOptionPane.showInputDialog(this, message);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                onlinereservationsystem gui = new onlinereservationsystem();
                gui.setVisible(true);
            }
        });
    }
}
