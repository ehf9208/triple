package triple.model;

import java.time.LocalDateTime;

public class AddFileVO {
    private String attacheId;
    private String referId;
    private String filePath;
    private LocalDateTime regDate;

    public String getAttacheId() {
        return attacheId;
    }

    public void setAttacheId(String attacheId) {
        this.attacheId = attacheId;
    }

    public String getReferId() {
        return referId;
    }

    public void setReferId(String referId) {
        this.referId = referId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDateTime regDate) {
        this.regDate = regDate;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("AddFile{");
        sb.append("attacheId='").append(attacheId).append('\'');
        sb.append(", referId='").append(referId).append('\'');
        sb.append(", filePath='").append(filePath).append('\'');
        sb.append(", regDate=").append(regDate);
        sb.append('}');
        return sb.toString();
    }
}
