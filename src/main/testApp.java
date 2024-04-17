package main;
import static org.junit.Assert.*;

import org.junit.Test;

public class testApp {

    //test verifyDimension
    @Test
    public void testVerifyDimensionBelowLowerBound(){
        assertFalse(App.verifyDimension(1));
    }

    @Test
    public void testVerifyDimensionAtLowerBound(){
        assertTrue(App.verifyDimension(2));
    }

    @Test
    public void testVerifyDimensionAboveLowerBound(){
        assertTrue(App.verifyDimension(3));
    }

    @Test
    public void testVerifyDimensionStandardValue(){
        assertTrue(App.verifyDimension(10));
    }

    @Test
    public void testVerifyDimensionNegativeValue(){
        assertFalse(App.verifyDimension(-1));
    }

    //test verifyTargetCoord
    @Test
    public void testVerifyTargetCoordBelowLowerBound(){
        assertFalse(App.verifyTargetCoord(-1, 10));
    }

    @Test
    public void testVerifyTargetCoordAtLowerBound(){
        assertTrue(App.verifyTargetCoord(0, 10));
    }

    @Test
    public void testVerifyTargetCoordAboveLowerBound(){
        assertTrue(App.verifyTargetCoord(1, 10));
    }

    @Test
    public void testVerifyTargetCoordStandardValue(){
        assertTrue(App.verifyTargetCoord(5, 10));
    }

    @Test
    public void testVerifyTargetCoordNegativeValue(){
        assertFalse(App.verifyTargetCoord(-1, 10));
    }


    @Test
    public void testVerifyTargetCoordBelowUpperBound(){
        assertTrue(App.verifyTargetCoord(9, 10));
    }

    @Test
    public void testVerifyTargetCoordAtUpperBound(){
        assertTrue(App.verifyTargetCoord(10, 10));
    }

    @Test
    public void testVerifyTargetCoordAboveUpperBound(){
        assertFalse(App.verifyTargetCoord(11, 10));
    }

    //test verifyNum
    @Test
    public void testVerifyNumBelowLowerBound(){
        assertFalse(App.verifyNum(0));
    }

    @Test
    public void testVerifyNumAtLowerBound(){
        assertTrue(App.verifyNum(1));
    }

    @Test
    public void testVerifyNumAboveLowerBound(){
        assertTrue(App.verifyNum(2));
    }

    @Test
    public void testVerifyNumStandardValue(){
        assertTrue(App.verifyNum(10));
    }

    @Test
    public void testVerifyNumNegativeValue(){
        assertFalse(App.verifyNum(-1));
    }

    @Test
    public void testVerifyNumLargeNumber() {
        assertTrue(App.verifyNum(1000000000));
    }
    
}
