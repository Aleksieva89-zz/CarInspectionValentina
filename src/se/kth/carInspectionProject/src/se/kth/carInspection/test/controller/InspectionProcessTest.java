package se.kth.carInspection.controller;

import org.junit.*;
import se.kth.carInspection.exceptions.IlegalLicenceNumberException;
import se.kth.carInspection.integration.Vehicle;

import static org.junit.Assert.*;
/**
 *
 * @author Lena Shervarly
 */
public class InspectionProcessTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void enterVehicleRegNumber() throws Exception {

    }

    @Test
    public void retrieveInspections() throws Exception {

    }

    @Test
    public void calculateInspectionCost() throws Exception {

    }

    @Test
    public void testInspect() throws Exception {

    }

    @Test(expected = IlegalLicenceNumberException.class)
    public void testGetCarType() throws IlegalLicenceNumberException {
        InspectorDTO inspector = new InspectorDTO("John" , "DBN");
        InspectionProcess inspection = new InspectionProcess(inspector);
        inspection.getCarType(new Vehicle("Illegal registration"));
    }
}