package triple.srv;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import triple.model.ReviewVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/config/spring/context/servlet-test-context.xml","/config/spring/context/mybatis-test-context.xml"})
public class ReviewSrvTest {
    @Test
    public void regReviewTest() throws Exception{

    }
}
