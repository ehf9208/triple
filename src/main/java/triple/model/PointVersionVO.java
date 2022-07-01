package triple.model;

import java.time.LocalDateTime;

public class PointVersionVO {
    private int version;
    private int contentVal;
    private int contentLength;
    private int addFileVal;
    private int addFileCnt;
    private int addVal;
    private LocalDateTime regDate;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getContentVal() {
        return contentVal;
    }

    public void setContentVal(int contentVal) {
        this.contentVal = contentVal;
    }

    public int getContentLength() {
        return contentLength;
    }

    public void setContentLength(int contentLength) {
        this.contentLength = contentLength;
    }

    public int getAddFileVal() {
        return addFileVal;
    }

    public void setAddFileVal(int addFileVal) {
        this.addFileVal = addFileVal;
    }

    public int getAddFileCnt() {
        return addFileCnt;
    }

    public void setAddFileCnt(int addFileCnt) {
        this.addFileCnt = addFileCnt;
    }

    public int getAddVal() {
        return addVal;
    }

    public void setAddVal(int addVal) {
        this.addVal = addVal;
    }

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDateTime regDate) {
        this.regDate = regDate;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PointVersionVO{");
        sb.append("version=").append(version);
        sb.append(", contentVal=").append(contentVal);
        sb.append(", contentLength=").append(contentLength);
        sb.append(", addFileVal=").append(addFileVal);
        sb.append(", addFileCnt=").append(addFileCnt);
        sb.append(", addVal=").append(addVal);
        sb.append(", regDate=").append(regDate);
        sb.append('}');
        return sb.toString();
    }
}
