package test.com.wxapp.shopapp.util;

import com.wxapp.shopapp.util.CourierUtils;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * CourierUtils Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>ʮһ�� 10, 2018</pre>
 */
public class CourierUtilsTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: query(String shipSn)
     */
    @Test
    public void testQuery() throws Exception {

//        Object o = CourierUtils.query("3379390063473");
        Object o = CourierUtils.query("70845214648558");

        System.out.println(o);

    }


} 
