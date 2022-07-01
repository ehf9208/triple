package triple.srv;

import triple.model.ReviewVO;

public interface ReviewSrv {
    public void regReview(ReviewVO vo);
    public void modifyReview(ReviewVO vo);
    public void eraseReview(ReviewVO vo);
}
