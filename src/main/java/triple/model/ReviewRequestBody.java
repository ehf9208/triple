package triple.model;

import java.util.List;

public class ReviewRequestBody {
    private String type;
    private String action;
    private String reviewId;
    private String content;
    private List<String> attachedPhotoIds;
    List<String> deleteAttacheId;
    private String userId;
    private String placeId;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getAttachedPhotoIds() {
        return attachedPhotoIds;
    }

    public void setAttachedPhotoIds(List<String> attachedPhotoIds) {
        this.attachedPhotoIds = attachedPhotoIds;
    }

    public List<String> getDeleteAttacheId() {
        return deleteAttacheId;
    }

    public void setDeleteAttacheId(List<String> deleteAttacheId) {
        this.deleteAttacheId = deleteAttacheId;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ReviewRequestBody{");
        sb.append("type='").append(type).append('\'');
        sb.append(", action='").append(action).append('\'');
        sb.append(", reviewId='").append(reviewId).append('\'');
        sb.append(", content='").append(content).append('\'');
        sb.append(", attachedPhotoIds=").append(attachedPhotoIds);
        sb.append(", deleteAttacheId=").append(deleteAttacheId);
        sb.append(", userId='").append(userId).append('\'');
        sb.append(", placeId='").append(placeId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
