package triple.model;

import java.time.LocalDateTime;

public class PointHistVO {
    private int id;
    private String procType;
    private int point;
    private LocalDateTime regDate;
    private String userId;
    private String referId;
    private String referTable;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProcType() {
        return procType;
    }

    public void setProcType(String procType) {
        this.procType = procType;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDateTime regDate) {
        this.regDate = regDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getReferId() {
        return referId;
    }

    public void setReferId(String referId) {
        this.referId = referId;
    }

    public String getReferTable() {
        return referTable;
    }

    public void setReferTable(String referTable) {
        this.referTable = referTable;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PointHistVO{");
        sb.append("id=").append(id);
        sb.append(", procType='").append(procType).append('\'');
        sb.append(", point=").append(point);
        sb.append(", regDate=").append(regDate);
        sb.append(", userId='").append(userId).append('\'');
        sb.append(", referId='").append(referId).append('\'');
        sb.append(", referTable='").append(referTable).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
