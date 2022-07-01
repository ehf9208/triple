package triple.ctr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import triple.model.AddFileVO;
import triple.model.ReviewRequestBody;
import triple.model.ReviewVO;
import triple.srv.ReviewSrv;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ReviewCtr {

    @Autowired
    private ReviewSrv reviewSrv;

    @PostMapping(value = "/event")
    public Map<String,Object> event(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestBody ReviewRequestBody vo
            ){
        Map<String, Object> result = new HashMap<String, Object>();

        if("REVIEW".equals(vo.getType())){
            ReviewVO reviewVO = new ReviewVO();
            reviewVO.setReviewId(vo.getReviewId());
            reviewVO.setContent(vo.getContent());
            reviewVO.setPlaceId(vo.getPlaceId());
            reviewVO.setUserId(vo.getUserId());

            List<AddFileVO> addFiles = new ArrayList<>();
            for (String addFile:vo.getAttachedPhotoIds()) {
                AddFileVO fileVO = new AddFileVO();
                fileVO.setAttacheId(addFile);
                fileVO.setReferId(vo.getReviewId());
                fileVO.setFilePath("/file/"+addFile);
                addFiles.add(fileVO);
            }
            reviewVO.setAddFileVOS(addFiles);
            reviewVO.setDeleteAttacheId(vo.getDeleteAttacheId());

            if("ADD".equals(vo.getAction())){
                reviewSrv.regReview(reviewVO);
            }

            if("DELETE".equals(vo.getAction())){
                reviewSrv.eraseReview(reviewVO);
            }

            if("MOD".equals(vo.getAction())){
                reviewSrv.modifyReview(reviewVO);
            }
        }

        return result;
    }
}
