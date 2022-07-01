package triple.model;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewVO {
    private String reviewId;
    private String userId;
    private String placeId;
    private String content;
    private LocalDateTime regDate;
    private int pointVer;
    private List<AddFileVO> addFileVOS;
    private List<String> deleteAttacheId;

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDateTime regDate) {
        this.regDate = regDate;
    }

    public int getPointVer() {
        return pointVer;
    }

    public void setPointVer(int pointVer) {
        this.pointVer = pointVer;
    }

    public List<AddFileVO> getAddFileVOS() {
        return addFileVOS;
    }

    public void setAddFileVOS(List<AddFileVO> addFileVOS) {
        this.addFileVOS = addFileVOS;
    }

    public List<String> getDeleteAttacheId() {
        return deleteAttacheId;
    }

    public void setDeleteAttacheId(List<String> deleteAttacheId) {
        this.deleteAttacheId = deleteAttacheId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ReviewVO{");
        sb.append("reviewId='").append(reviewId).append('\'');
        sb.append(", userId='").append(userId).append('\'');
        sb.append(", placeId='").append(placeId).append('\'');
        sb.append(", content='").append(content).append('\'');
        sb.append(", regDate=").append(regDate);
        sb.append(", pointVer=").append(pointVer);
        sb.append(", addFileVOS=").append(addFileVOS);
        sb.append(", deleteAttacheId=").append(deleteAttacheId);
        sb.append('}');
        return sb.toString();
    }
}
