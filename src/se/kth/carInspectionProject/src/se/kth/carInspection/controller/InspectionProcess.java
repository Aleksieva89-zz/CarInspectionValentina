
package se.kth.carInspection.controller;
import se.kth.carInspection.exceptions.IlegalLicenceNumberException;
import se.kth.carInspection.integration.ExternalCheckingRegNoSystem;
import se.kth.carInspection.data.InspectionDTO;

import java.util.*;

import se.kth.carInspection.data.InspectionRegistriesCollection;
import se.kth.carInspection.integration.Vehicle;
import se.kth.carInspection.model.InspectionResultCollection;
import se.kth.carInspection.model.InspectionsSubject;
import se.kth.carInspection.view.InspectionsObserver;

/**
 * Inspection Process deals with everything needed in order to provide inspection
 * @author Lena Shervarly
 * @version 0.1
 */
public class InspectionProcess implements InspectionsSubject{
    private InspectorDTO inspectorWhoLogsIn;
    private ExternalCheckingRegNoSystem checkingRegNo;
    private InspectionRegistriesCollection inspectionRegistriesCollection;
    private ArrayList<InspectionDTO> inspectionsForVehicle;
    private ArrayList<InspectionsObserver> observers;

    /**
     * Created Inspection Process on the base of login details of the Inspector
     * @param inspector inspector who initializes the inspection process
     */
    public InspectionProcess(InspectorDTO inspector) {
        inspectorWhoLogsIn = inspector;
        checkingRegNo = new ExternalCheckingRegNoSystem();
        inspectionRegistriesCollection = new InspectionRegistriesCollection();
        observers = new ArrayList<>();
    }
    
    /**
     * Get the car type of the given <code>vehicleBeingInspected</code>
     * @param vehicleBeingInspected the car whose type is being searched
     * @return the car type of the vehicle
     */
    public String getCarType(Vehicle vehicleBeingInspected) throws IlegalLicenceNumberException {
        return checkingRegNo.getCarType(vehicleBeingInspected.getRegistrationNumber());
    }
    
    /**
     * Entering the registration number of the <code>vehicleBeingInspected</code> to the system
     * @param vehicleBeingInspected the car, which enters the garage for the inspection
     * @return true if the operation of entering the registration number to the system was successful
     */
    public boolean enterVehicleRegNumber(Vehicle vehicleBeingInspected){
        if(checkingRegNo.getApprovalOfTheCarRegNo(vehicleBeingInspected.getRegistrationNumber()))
            return true;
        else
            return false;
    }
    
    /**
     * Retrieve a collection of inspections for a specified <code>vehicleBeingInspected</code>
     * @param vehicleBeingInspected the vehicle being inspected
     * @return a collection of inspections for a specified vehicle
     * @throws NullPointerException 
     */
    public ArrayList<InspectionDTO> retrieveInspections(Vehicle vehicleBeingInspected) throws NullPointerException {
        String carType = null;
        try {
            carType = getCarType(vehicleBeingInspected);
        } catch (IlegalLicenceNumberException e) {
            e.printStackTrace();
        }

        if(enterVehicleRegNumber(vehicleBeingInspected)) {
            inspectionsForVehicle = inspectionRegistriesCollection.getInspectionCollection(carType);
            return inspectionsForVehicle;
        }
        else {
            throw new NullPointerException("This vehicle is number is not in service");
        }
    }
    
    /**
     * Get the total cost of all the inspections for the vehicle
     * @return  cost of all the inspections for the vehicle
     */
    public int calculateInspectionCost() {
        int totalCost = 0;
        for(InspectionDTO inspection : inspectionsForVehicle)
            totalCost += inspection.getCost();
        return totalCost;
    }
    
    /**
     * Inspect the given vehicle
     * @param vehicleBeingInspected the vehicle being inspected
     */
    public void inspect(Vehicle vehicleBeingInspected) {
        String carType = null;
        try {
            carType = getCarType(vehicleBeingInspected);
        } catch (IlegalLicenceNumberException e) {
            e.printStackTrace();
        }
        InspectionResultCollection inspectionResults  = new InspectionResultCollection(carType);
        HashMap<InspectionDTO, Boolean> resultsCollection = inspectionResults.getAllResults();

        for(Map.Entry<InspectionDTO, Boolean> result : resultsCollection.entrySet()) {
            inspectionResults.saveInspectionResult(result.getKey(), true);
        }

        notifyObservers();
    }

//    public int getPassedInspections() {
//        Collection<Boolean> resultValues = results.values();
//        int passedResultsNumber = 0;
//        for (Boolean passed : resultValues) {
//            if (passed == true) {
//                passedResultsNumber++;
//            }
//        }
//
//        return passedResultsNumber;
//    }
//
//    public int getFailedInspections() {
//        return results.values().size() - getPassedInspections();
//    }

    @Override
    public void register(InspectionsObserver o) {
        observers.add(o);
    }

    @Override
    public void unregister(InspectionsObserver o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        Random rand = new Random();
        int max = 10;
        int min = 1;

        for(InspectionsObserver o : observers) {
            o.update(rand.nextInt((max - min) + 1) + min, rand.nextInt((max - min) + 1) + min);
        }
    }
}