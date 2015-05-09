package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Comedian;
import model.DBComedian;
import model.Event;
import model.IComedians;
import view.GUI_Bookings;
import view.GUI_BoxOffice;

public class BoxOfficeController {

	private GUI_BoxOffice _viewMain;
	//private GUI_Bookings _viewBookings;
	//attr for model
	
	
	public BoxOfficeController()
	{
		this._viewMain = new GUI_BoxOffice();
		
		addMainListener();
	}
	
	public void showView(){
        this._viewMain.setVisible(true);
    }

	
	/**
	 * View is introduced to the Listeners
	 * to enable communication with controller
     */
	private void addMainListener() {
		// TODO Auto-generated method stub
		
		this._viewMain.setBookingsListener(new BookingsListener());
		this._viewMain.setFeedbackListener(new FeedbackListener());
		this._viewMain.setBookingsSelectionListener(new EventsListener());
		this._viewMain.setComediansSelectionListener(new ComediansListener());
	}
	
	private void updateMainView(){
		//TODO
	}
	
	 /**
     * Inneren Listener Klassen implementieren das Interface ActionListener
     *
     * 1: Hier wird erst der eingegebene Wert aus der View geholt
     * 2: Der Wert wird dem Model �bergeben und die Wurzel berechnet
     * 3: Die berechnete Wurzel wird aus dem Model geladen und
     * 4: Wieder der View zum Darstellen �bergeben
     *
     * TODO : check for errors
     * 
     * MAIN WINDOW
     */
	
	//Open popup for bookings
    class BookingsListener implements ActionListener{ 
    	GUI_Bookings _viewBookings;
    	
    
        public void actionPerformed(ActionEvent e) {
          //get values from view: value = (_view.getWhatever())
        	// get Event Data from view = _view.getSelectedEvent()
        	//use event Data to load the bookings _model.GetBookingsForEvent(data)
        	//Start Popup
        	_viewBookings= new GUI_Bookings();
        	 this._viewBookings.setVisible(true);
        	addBookingsListener();
        }
        
		private void addBookingsListener() {
			
			this._viewBookings.setSaveListener(new SaveListener());
			this._viewBookings.setRemoveListener(new RemoveListener());
			this._viewBookings.setClearListener(new ClearListener());
			this._viewBookings.setReturnListener(new ReturnListener());
			
		}
    }
    
    //Open window for feedback
    class FeedbackListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
          //get values from view: value = (_view.getWhatever())
          //use values to perform the needed action (_model.DoWhatever(value))
          //update view _view.setWhatever(....)
        }
    }
    
    //Click on Event in the list
    class EventsListener implements ListSelectionListener{
		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			
			//get selected value
			if(_viewMain.getLstEvents().getSelectedValue()!= null)
			{
				//get selected value
				Event selectedEvent = _viewMain.getLstEvents().getSelectedValue();
			
				//get associated comedians
				IComedians comedians = new DBComedian();
				
				//update data in the view
				_viewMain.setComedians(comedians.getComedianForEvent(selectedEvent.getId()));
				_viewMain.updateEvendData(selectedEvent);
			}
		}
    }
    
    class ComediansListener implements ListSelectionListener{
		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			
			//get selected value
			if(_viewMain.getLstComedians().getSelectedValue()!= null)
			{
				_viewMain.updateComedianData(_viewMain.getLstComedians().getSelectedValue());
			}
			
		}
    }
    
	/**
	 * Bookings Popup
     */
    class SaveListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
          //get values from view: value = (_view.getWhatever())
          //use values to perform the needed action (_model.DoWhatever(value))
          //update view _view.setWhatever(....)
        }
    }
    
    class RemoveListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
          //get values from view: value = (_view.getWhatever())
          //use values to perform the needed action (_model.DoWhatever(value))
          //update view _view.setWhatever(....)
        }
    }
    
    class ClearListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
          //get values from view: value = (_view.getWhatever())
          //use values to perform the needed action (_model.DoWhatever(value))
          //update view _view.setWhatever(....)
        }
    }
    
    class ReturnListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
          //get values from view: value = (_view.getWhatever())
          //use values to perform the needed action (_model.DoWhatever(value))
          //update view _view.setWhatever(....)
        }
    }

}
