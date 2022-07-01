package triple.model;

public class PointVO {
    private String userId;
    private int point;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PointVO{");
        sb.append("userId='").append(userId).append('\'');
        sb.append(", point=").append(point);
        sb.append('}');
        return sb.toString();
    }

    public PointVO(String userId, int point) {
        this.userId = userId;
        this.point = point;
    }
}
