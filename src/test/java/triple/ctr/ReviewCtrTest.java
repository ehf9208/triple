package triple.ctr;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;
import triple.model.ReviewRequestBody;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"/config/spring/context/servlet-test-context.xml","/config/spring/context/mybatis-test-context.xml"})
public class ReviewCtrTest {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired ReviewCtr ctr;
    private MockMvc mockMvc;

    @Before
    public void initMockMvc() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        mockMvc = MockMvcBuilders.standaloneSetup(ctr).addFilter(filter).build();
    }

    @Test
    public void eventRegTest() throws Exception{
        ReviewRequestBody vo = new ReviewRequestBody();
        vo.setType("REVIEW");
        vo.setAction("ADD");
        vo.setReviewId("240a0658-dc5f-4878-9381-ebb7b26677");
        vo.setContent("좋아");
        List<String> tmp = new ArrayList<>();
        tmp.add("e4d1a64e-a531-46de-88d0-ff0ed70c0b");
        tmp.add("afb0cef2-851d-4a50-bb07-9cc15cbdc3");
        vo.setAttachedPhotoIds(tmp);
        vo.setUserId("3ede0ef2-92b7-4817-a5f3-0c575361f74");
        vo.setPlaceId("2e4baf1c-5acb-4efb-a1af-eddada31b0");

        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(vo);
        logger.debug("content = {}", content);

        MockHttpServletRequestBuilder requestBuilder
                = MockMvcRequestBuilders
                .post ("/event")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
                .accept(MediaType.APPLICATION_JSON);

        this.mockMvc.perform(requestBuilder).andDo(print())
                .andExpect(status().isOk());
    }

//    @Test
//    public void eventModTest() throws Exception{
//        ReviewRequestBody vo = new ReviewRequestBody();
//        vo.setType("REVIEW");
//        vo.setAction("MOD");
//        vo.setReviewId("240a0658-dc5f-4878-9381-ebb7b266777");
//        vo.setContent("좋아쿵");
//        List<String> tmp = new ArrayList<>();
//        tmp.add("e4d1a64e-a531-46de-88d0-ff0ed70c0b111");
//        tmp.add("afb0cef2-851d-4a50-bb07-9cc15cbdc31234543");
//        vo.setAttachedPhotoIds(tmp);
//        vo.setUserId("3ede0ef2-92b7-4817-a5f3-0c575361f74");
//        vo.setPlaceId("2e4baf1c-5acb-4efb-a1af-eddada31b0");
//
//        List<String> deleteAttacheId = new ArrayList<>();
//        deleteAttacheId.add("e4d1a64e-a531-46de-88d0-ff0ed70c0bb");
//        vo.setDeleteAttacheId(deleteAttacheId);
//
//
//        ObjectMapper mapper = new ObjectMapper();
//        String content = mapper.writeValueAsString(vo);
//        logger.debug("content = {}", content);
//
//        MockHttpServletRequestBuilder requestBuilder
//                = MockMvcRequestBuilders
//                .post ("/event")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(content)
//                .accept(MediaType.APPLICATION_JSON);
//
//        this.mockMvc.perform(requestBuilder).andDo(print())
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void eventDeleteTest() throws Exception{
//        ReviewRequestBody vo = new ReviewRequestBody();
//        vo.setType("REVIEW");
//        vo.setAction("DELETE");
//        vo.setReviewId("240a0658-dc5f-4878-9381-ebb7b26677");
//        vo.setContent("좋아");
//        List<String> tmp = new ArrayList<>();
//        tmp.add("e4d1a64e-a531-46de-88d0-ff0ed70c0b");
//        tmp.add("afb0cef2-851d-4a50-bb07-9cc15cbdc3");
//        vo.setAttachedPhotoIds(tmp);
//        vo.setUserId("3ede0ef2-92b7-4817-a5f3-0c575361f74");
//        vo.setPlaceId("2e4baf1c-5acb-4efb-a1af-eddada31b0");
//
//        ObjectMapper mapper = new ObjectMapper();
//        String content = mapper.writeValueAsString(vo);
//        logger.debug("content = {}", content);
//
//        MockHttpServletRequestBuilder requestBuilder
//                = MockMvcRequestBuilders
//                .post ("/event")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(content)
//                .accept(MediaType.APPLICATION_JSON);
//
//        this.mockMvc.perform(requestBuilder).andDo(print())
//                .andExpect(status().isOk());
//    }
}
