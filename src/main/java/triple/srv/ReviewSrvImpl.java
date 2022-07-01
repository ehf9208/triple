package triple.srv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import triple.mapper.PointMapper;
import triple.mapper.ReviewMapper;
import triple.model.PointHistVO;
import triple.model.PointVO;
import triple.model.PointVersionVO;
import triple.model.ReviewVO;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewSrvImpl implements ReviewSrv{

    @Autowired
    private ReviewMapper reviewMapper;

    @Autowired
    private PointMapper pointMapper;

    @Override
    public void regReview(ReviewVO vo) {
        regPoint(vo);
        reviewMapper.insertReview(vo);
        reviewMapper.insertAddFile(vo.getAddFileVOS());
    }

    private void regPoint(ReviewVO vo){
        persistPoint(vo.getUserId(),regPointCalc(vo));
    }

    private void persistPoint(String userId, List<PointHistVO> pointHists){
        int totalPoint = 0;
        for (PointHistVO ph:pointHists) {
            totalPoint+=ph.getPoint();
        }

        PointVO point = new PointVO(userId,totalPoint);

        if("Y".equals(pointMapper.selectUserExists(userId))){
            pointMapper.updatePoint(point);
        }else{
            pointMapper.insertPoint(point);
        }
        pointMapper.insertPointHist(pointHists);
    }

    private List<PointHistVO> regPointCalc(ReviewVO vo){
        List<PointHistVO> pointHists = new ArrayList<>();
        PointVersionVO pointVersion =  pointMapper.selectPointVersion();
        vo.setPointVer(pointVersion.getVersion());

        //첫글인지 체크
        if("Y".equals(reviewMapper.selectFirstReview(vo.getPlaceId()))){
            PointHistVO ph1 = new PointHistVO();
            ph1.setProcType("IW");
            ph1.setPoint(pointVersion.getAddVal());
            ph1.setUserId(vo.getUserId());
            ph1.setReferId(vo.getReviewId());
            ph1.setReferTable("REVIEW");
            pointHists.add(ph1);
        }
        //컨텐츠 길이 체크
        if(vo.getContent().trim().length() > pointVersion.getContentLength()){
            PointHistVO ph2 = new PointHistVO();
            ph2.setProcType("CR");
            ph2.setPoint(pointVersion.getContentVal());
            ph2.setUserId(vo.getUserId());
            ph2.setReferId(vo.getReviewId());
            ph2.setReferTable("REVIEW");
            pointHists.add(ph2);
        }
        //첨부파일 개수 확인
        if(vo.getAddFileVOS().size() > pointVersion.getAddFileCnt()){
            PointHistVO ph3 = new PointHistVO();
            ph3.setProcType("FA");
            ph3.setPoint(pointVersion.getAddFileVal());
            ph3.setUserId(vo.getUserId());
            ph3.setReferId(vo.getReviewId());
            ph3.setReferTable("ADD_FILE");
            pointHists.add(ph3);
        }

        return pointHists;
    }

    @Override
    public void modifyReview(ReviewVO vo) {
        reviewMapper.updateReview(vo);
        if(vo.getDeleteAttacheId() != null){
            for (String attacheId:vo.getDeleteAttacheId()) {
                reviewMapper.deleteAddFile(attacheId);
            }
        }
        reviewMapper.insertAddFile(vo.getAddFileVOS());
        modifyPoint(vo);
    }

    private void modifyPoint(ReviewVO vo){
        persistPoint(vo.getUserId(),modifyPointCalc(vo));
    }

    private List<PointHistVO> modifyPointCalc(ReviewVO vo){
        PointVersionVO pointVersion =  pointMapper.selectPointVersionByVersion(vo.getPointVer());
        //기존 데이터 조회
        List<PointHistVO> pointHists = pointMapper.selectPointHist(vo.getReviewId());

        int cr_cd_point = 0;
        int fa_fd_point = 0;
        for (PointHistVO ph:pointHists) {
            if("CR".equals(ph.getProcType()) || "CD".equals(ph.getProcType())){
                cr_cd_point+=ph.getPoint();
            }

            if("FA".equals(ph.getProcType()) || "FD".equals(ph.getProcType())){
                fa_fd_point+=ph.getPoint();
            }
        }

        List<PointHistVO> regPointHists = new ArrayList<>();
        //수정 되는 데이터 기준과 달라지는지 확인
        if(vo.getContent().trim().length() > pointVersion.getContentLength() && cr_cd_point == 0){
            PointHistVO ph2 = new PointHistVO();
            ph2.setProcType("CR");
            ph2.setPoint(pointVersion.getContentVal());
            ph2.setUserId(vo.getUserId());
            ph2.setReferId(vo.getReviewId());
            ph2.setReferTable("REVIEW");
            regPointHists.add(ph2);
        }

        //첨부파일 개수 확인
        if(vo.getAddFileVOS().size() > pointVersion.getAddFileCnt() && fa_fd_point == 0){
            PointHistVO ph3 = new PointHistVO();
            ph3.setProcType("FA");
            ph3.setPoint(pointVersion.getAddFileVal());
            ph3.setUserId(vo.getUserId());
            ph3.setReferId(vo.getReviewId());
            ph3.setReferTable("ADD_FILE");
            pointHists.add(ph3);
        }

        return pointHists;
    }

    @Override
    public void eraseReview(ReviewVO vo) {
        if(reviewMapper.deleteReview(vo) == 1){
            reviewMapper.deleteAddFiles(vo.getReviewId());
            erasePoint(vo);
        }else{
            //삭제 불가
        }
    }

    private void erasePoint(ReviewVO vo){
        persistPoint(vo.getUserId(),erasePointCalc(vo));
    }

    private List<PointHistVO> erasePointCalc(ReviewVO vo){
        List<PointHistVO> pointHists = pointMapper.selectPointHist(vo.getReviewId());

        List<PointHistVO> eraseHists = new ArrayList<>();
        for (PointHistVO ph:pointHists) {
            PointHistVO eraseHist = new PointHistVO();
            if("IW".equals(ph.getProcType())){
                eraseHist.setProcType("ID");
            }

            if("CR".equals(ph.getProcType())){
                eraseHist.setProcType("CD");
            }

            if("FA".equals(ph.getProcType())){
                eraseHist.setProcType("FD");
            }
            eraseHist.setPoint(ph.getPoint() * -1);
            eraseHist.setUserId(ph.getUserId());
            eraseHist.setReferId(ph.getReferId());
            eraseHist.setReferTable(ph.getReferTable());
            eraseHists.add(eraseHist);
        }

        return eraseHists;
    }
}
