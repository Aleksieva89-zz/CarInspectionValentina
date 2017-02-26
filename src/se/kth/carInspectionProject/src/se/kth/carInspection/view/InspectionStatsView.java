package se.kth.carInspection.view;

public class InspectionStatsView implements InspectionsObserver {

    @Override
    public void update(int passedInspections, int failedInspections) {
        System.out.println("There were " + passedInspections + "passed inspections and " + failedInspections + "failed inspections.");
    }
}
