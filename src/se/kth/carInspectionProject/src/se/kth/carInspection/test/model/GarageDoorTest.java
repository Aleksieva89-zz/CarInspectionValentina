package se.kth.carInspection.model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class GarageDoorTest {

    private GarageDoor door;

    @Before
    public void setup() {
        door = new GarageDoor(false);
    }

    @After
    public  void  after() {
        door = null;
    }

    @Test
    public void testConstructor() throws Exception {
        assertEquals(false, door.getIsOpened());
    }

    @Test
    public void testOpen() throws Exception {
        door.open();
        assertEquals(true, door.getIsOpened());
    }

    @Test
    public void testClose() throws Exception {
        door.close();
        assertEquals(false, door.getIsOpened());
    }
}


