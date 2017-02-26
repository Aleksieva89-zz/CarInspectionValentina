package se.kth.carInspection.model;


import se.kth.carInspection.view.InspectionsObserver;

public interface InspectionsSubject {

    public void register(InspectionsObserver o);
    public void unregister(InspectionsObserver o);
    public void notifyObservers();

}
