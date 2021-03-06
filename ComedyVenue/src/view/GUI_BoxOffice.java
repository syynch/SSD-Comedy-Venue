package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;

import model.Comedian;
import model.Event;

import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * GUI_BoxOffice This class represents the main view of this application. It
 * contains two JLists, one displaying the complete list of the events and the
 * other displaying the booked comedians for a selected event. Details for both
 * are displayed on the right side.
 * 
 * @author Sarah Bulk
 * @version 1.0
 * @since 19.05.2015
 */

public class GUI_BoxOffice extends JFrame {

	/**
	 * declatations
	 */
	private static final long serialVersionUID = -8617657665399466269L;

	// date format to display the dates
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

	// GUI elements
	private JPanel contentPane;
	private JLabel lblHead;

	// Top
	private JPanel pnlTop;

	// LEFT
	private JLabel lblEvents;
	private JList<Event> lstEvents;

	// RIGHT
	private JPanel pnlEventDetails;
	private JPanel pnlButtons;
	private JPanel pnlData;

	private JLabel lblEventName;
	private JLabel lblEventDate;
	private JLabel lblEventDesc;
	private JLabel lblEventSeats;
	private JLabel lblEventAge;
	private JButton btnBookings;
	private JButton btnFeedback;

	// Bottom
	private JPanel pnlBottom;

	// LEFT
	private JList<Comedian> lstComedians;

	// RIGHT
	private JPanel pnlComedianDetails;
	private JLabel lblComedianName;
	private JLabel lblComedianDesc;

	/**
	 * This method is used to create the frame.
	 */
	public GUI_BoxOffice() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		// TOP
		lblHead = new JLabel("Comedy Venue - Box Office");

		// TOP LEFT
		pnlTop = new JPanel();
		pnlTop.setLayout(new BoxLayout(pnlTop, BoxLayout.X_AXIS));

		// lblEvents = new JLabel("EVENTS");

		lstEvents = new JList<Event>();
		JScrollPane spEvents = new JScrollPane(lstEvents);
		spEvents.setMaximumSize(new Dimension(500, 600));
		spEvents.setBorder(BorderFactory.createTitledBorder("Events"));

		// TOP RIGHT
		pnlEventDetails = new JPanel();
		pnlEventDetails.setBorder(BorderFactory
				.createTitledBorder("Event details"));
		// pnlEventDetails.setLayout(new BoxLayout(pnlEventDetails,
		// BoxLayout.Y_AXIS));
		pnlEventDetails.setLayout(new BorderLayout());
		pnlEventDetails.setMaximumSize(new Dimension(500, 600));

		lblEventName = new JLabel("NAME");
		lblEventName.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));

		lblEventDate = new JLabel("DATE");
		lblEventDate.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));

		lblEventDesc = new JLabel("DESCRIPTION");
		lblEventDesc.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));

		lblEventSeats = new JLabel("SEATS");
		lblEventSeats
				.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));

		lblEventAge = new JLabel("AGE");
		lblEventAge.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));

		pnlButtons = new JPanel();
		pnlButtons.setLayout(new BoxLayout(pnlButtons, BoxLayout.X_AXIS));

		btnBookings = new JButton("Bookings");
		btnFeedback = new JButton("Feedback");

		pnlButtons.add(btnBookings);
		pnlButtons.add(btnFeedback);

		pnlData = new JPanel();
		pnlData.setLayout(new BoxLayout(pnlData, BoxLayout.Y_AXIS));

		// pnlEventDetails.setMaximumSize(new Dimension(400,200));
		pnlData.add(lblEventName);
		pnlData.add(lblEventDate);
		pnlData.add(lblEventDesc);
		pnlData.add(lblEventSeats);
		pnlData.add(lblEventAge);

		pnlEventDetails.add(pnlData, BorderLayout.CENTER);
		pnlEventDetails.add(pnlButtons, BorderLayout.SOUTH);

		pnlTop.add(spEvents);
		pnlTop.add(pnlEventDetails);

		// BOTTOM
		pnlBottom = new JPanel();
		pnlBottom.setLayout(new BoxLayout(pnlBottom, BoxLayout.X_AXIS));

		// LEFT
		lstComedians = new JList<Comedian>();
		JScrollPane spComedians = new JScrollPane(lstComedians);
		spComedians.setMaximumSize(new Dimension(500, 200));
		spComedians.setBorder(BorderFactory.createTitledBorder("Comedians"));

		// RIGHT
		pnlComedianDetails = new JPanel();
		pnlComedianDetails.setBorder(BorderFactory
				.createTitledBorder("Comedian details"));
		pnlComedianDetails.setLayout(new BoxLayout(pnlComedianDetails,
				BoxLayout.Y_AXIS));
		pnlComedianDetails.setMaximumSize(new Dimension(500, 200));

		lblComedianName = new JLabel("COMEDIAN");
		lblComedianName.setBorder(BorderFactory.createEmptyBorder(10, 20, 10,
				10));

		lblComedianDesc = new JLabel("DESCRIPTION");
		lblComedianDesc.setBorder(BorderFactory.createEmptyBorder(10, 20, 10,
				10));

		pnlComedianDetails.add(lblComedianName);
		pnlComedianDetails.add(lblComedianDesc);

		// COMEDIANS
		pnlBottom.add(spComedians);
		pnlBottom.add(pnlComedianDetails);

		// add both sides to parent panel
		contentPane.add(pnlTop);
		contentPane.add(pnlBottom);

	}

	/**
	 * displays the events in the JList
	 * 
	 * @param e
	 *            The list of events
	 */
	public void setEvents(ArrayList<Event> e) {

		if (e != null) {
			lstEvents.setListData(e.toArray(new Event[0]));
		}

	}

	/**
	 * Update the details of an event displayed on the right side of the frame
	 * 
	 * @param e
	 *            the event of which the data is used for display
	 */
	public void updateEvendData(Event e) {

		if (e != null) {
			lblEventName.setText(e.getName());
			lblEventDate.setText(dateFormat.format(e.getDate()));
			lblEventDesc.setText(e.getDesc());
			lblEventSeats.setText(Integer.toString(e.getSeats())
					+ " total seats.");
			lblEventAge.setText("Ages " + Integer.toString(e.getMinAge())
					+ " and up.");
		}

	}

	/**
	 * displays the comedians in the JList
	 * 
	 * @param c
	 *            The list of comedians
	 */
	public void setComedians(ArrayList<Comedian> c) {

		if (c != null) {
			// update Comedian List
			lstComedians.setListData(c.toArray(new Comedian[0]));
		}
	}

	/**
	 * Update the details of a comedian displayed on the right side of the frame
	 * 
	 * @param c
	 *            the comedian of which the data is used for display
	 */
	public void updateComedianData(Comedian c) {

		if (c != null) {
			lblComedianName.setText(c.getName());
			lblComedianDesc.setText(c.getDesc());
		}

	}

	/**
	 * Data Accessor Methods
	 */

	public void setBookingsListener(ActionListener l) {
		this.btnBookings.addActionListener(l);
	}

	public void setFeedbackListener(ActionListener l) {
		this.btnFeedback.addActionListener(l);
	}

	public void setBookingsSelectionListener(ListSelectionListener l) {
		this.lstEvents.addListSelectionListener(l);
	}

	public void setComediansSelectionListener(ListSelectionListener l) {
		this.lstComedians.addListSelectionListener(l);
	}

	public JList<Event> getLstEvents() {
		return lstEvents;
	}

	public void setLstEvents(JList<Event> lstEvents) {
		this.lstEvents = lstEvents;
	}

	public JList<Comedian> getLstComedians() {
		return lstComedians;
	}

	public void setLstComedians(JList<Comedian> lstComedians) {
		this.lstComedians = lstComedians;
	}

	public void setBtnBookingsEnabled(boolean b) {
		btnBookings.setEnabled(b);
	}

	public void setBtnFeedbackEnabled(boolean b) {
		btnFeedback.setEnabled(b);
	}

}
