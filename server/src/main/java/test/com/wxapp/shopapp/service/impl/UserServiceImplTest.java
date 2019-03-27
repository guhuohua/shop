package test.com.wxapp.shopapp.service.impl;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import javax.xml.crypto.Data;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * UserServiceImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>ʮ�� 19, 2018</pre>
 */
public class UserServiceImplTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: insert(User user)
     */
    @Test
    public void testInsert() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: update(User user)
     */
    @Test
    public void testUpdate() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: delete(int id)
     */
    @Test
    public void testDelete() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: findByUsername(String username)
     */
    @Test
    public void testFindByUsername() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: findByTel(String tel)
     */
    @Test
    public void testFindByTel() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: findById(int id)
     */
    @Test
    public void testFindById() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: findAll()
     */
    @Test
    public void testFindAll() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: findByWxOpenId(String openid)
     */
    @Test
    public void testFindByWxOpenId() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: findByUsernameAndPassword(String username, String password)
     */
    @Test
    public void testFindByUsernameAndPassword() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: findByInvitationCode(String invitationCode)
     */
    @Test
    public void testFindByInvitationCode() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: findByPromotionId(int promotionId)
     */
    @Test
    public void testFindByPromotionId() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: count()
     */
    @Test
    public void testCount() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: todayCount()
     */
    @Test
    public void testTodayCount() throws Exception {
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDate localDate = LocalDate.now();
        ZonedDateTime zdt = localDate.atStartOfDay(zoneId);
        Date date = Date.from(zdt.toInstant());
        long l = date.getTime();
    }


} 
