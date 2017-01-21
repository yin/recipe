package recipe.server;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static recipe.server.JavaLanguageUtilitiesTest.TestOs.*;

public class JavaLanguageUtilitiesTest {
    // 'TestOs' - that's a name for an abstraction!
    enum TestOs {
        O0, O1, O2
    }

    @Test
    public void constructArray_head_null() throws Exception {
        try {
            TestOs[] ary = JavaLanguageUtilities.constructArray(TestOs.class, null);
            fail("should check for null lists");
        } catch (NullPointerException e) {
            // success
        }
    }

    @Test
    public void constructArray_head_null_tail_single() throws Exception {
        try {
            TestOs[] ary = JavaLanguageUtilities.constructArray(TestOs.class, null, O0);
            fail("should check for null lists");
        } catch (NullPointerException e) {
            // success
        }
    }

    @Test
    public void constructArray_head_empty() throws Exception {
        TestOs[] ary = JavaLanguageUtilities.constructArray(TestOs.class, ImmutableList.of());
        assertEquals(0, ary.length);
    }

    @Test
    public void constructArray_head_empty_tail_single() throws Exception {
        TestOs[] ary = JavaLanguageUtilities.constructArray(TestOs.class, ImmutableList.of(), O0);
        assertArrayEquals(new TestOs[]{O0}, ary);
    }

    @Test
    public void constructArray_head_single() throws Exception {
        TestOs[] ary = JavaLanguageUtilities.constructArray(TestOs.class, ImmutableList.of());
        assertEquals(0, ary.length);
    }

    @Test
    public void constructArray_head_single_tail_single() throws Exception {
        TestOs[] ary = JavaLanguageUtilities.constructArray(TestOs.class, ImmutableList.of(), O0);
        assertArrayEquals(new TestOs[]{O0}, ary);
    }

    @Test
    public void constructArray_head_multiple_tail_multiple_overlapping() throws Exception {
        TestOs[] ary = JavaLanguageUtilities.constructArray(TestOs.class, ImmutableList.of(O0, O1), O1, O2);
        assertArrayEquals(new TestOs[]{O0, O1, O1, O2}, ary);
    }
}