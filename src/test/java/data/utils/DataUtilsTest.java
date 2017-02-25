package test.java.data.utils;

import main.java.data.utils.DataUtils;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by timothylam on 10/29/16.
 *
 * Unit tests for DataUtils.
 */
public class DataUtilsTest {

    @Test
    public void testRotateElements() {
        Integer[] ints = {1, 2, 3, 4};
        Integer[] expectedInts = {2, 3, 4, 1};
        assertArrayEquals(expectedInts, DataUtils.rotateElements(ints));
    }

}
