package se.kth.carInspection.view;


public interface InspectionsObserver {

    public void update(int passedInspections, int failedInspections);

}
