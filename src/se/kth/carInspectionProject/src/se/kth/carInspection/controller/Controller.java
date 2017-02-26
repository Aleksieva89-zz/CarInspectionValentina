package se.kth.carInspection.controller;

import se.kth.carInspection.integration.CashRegistry;
import se.kth.carInspection.integration.ExternalPaymentAuthorizationSystem;
import se.kth.carInspection.integration.Printer;
import se.kth.carInspection.integration.Vehicle;
import se.kth.carInspection.model.Garage;
import se.kth.carInspection.model.GarageDoor;
import se.kth.carInspection.model.QueueNumberDisplay;
import se.kth.carInspection.view.InspectionStatsView;

/**
 * The Controller class gives information about the
 *
 * @author valentina
 * @version 0.1 2017/02/16
 */
public class Controller
{
    private Printer printer;
    private ExternalPaymentAuthorizationSystem paymentAuthorization;
    private InspectionProcess inspectionProcess;
    private GarageDoor door;
    private QueueNumberDisplay display;
    private InspectionStatsView inspectionStatsView;

    /**
     * Constructor for objects of class Contoller that is initializing the
     */

    public Controller(Printer printer, ExternalPaymentAuthorizationSystem paymentAuthorization, GarageDoor door, QueueNumberDisplay display, Garage garage)
    {
        this.printer = printer;
        this.paymentAuthorization = paymentAuthorization;
        this.door = door;
        this.display = display;
        this.inspectionStatsView = new InspectionStatsView();
        this.inspectionProcess.register(inspectionStatsView);
    }

    /**
     * This method is starting new inspection
     */
    public void startNewInspectionProcess(InspectorDTO inspector, Vehicle toInspect)
    {
        inspectionProcess = new InspectionProcess(inspector);
        inspectionProcess.inspect(toInspect);
    }

    /**
     * Gives the number of the next customer
     */
    public void nextCustomer()
    {

        door.open();
        display.incrementCurrentNumber();
        System.out.println("Current customer number is: " + display.getCurrentNumber());
    }

    /**
     * Closes the door
     */
    public void closeGarageDoor()
    {
        door.close();
    }
}
