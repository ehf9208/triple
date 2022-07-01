package triple.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import triple.model.PointHistVO;
import triple.model.PointVO;
import triple.model.PointVersionVO;

import java.util.List;

public interface PointMapper {

    @Select("SELECT proc_type procType, `point`, user_id userId, refer_id referId, refer_table referTable FROM POINT_HIST WHERE refer_id = #{referId}")
    public List<PointHistVO> selectPointHist(String referId);

    @Select("SELECT version, content_val contentVal, content_length contentLength, add_file_val addFileVal, add_file_cnt addFileCnt, add_val addVal FROM POINT_VER ORDER BY reg_date DESC LIMIT 1")
    public PointVersionVO selectPointVersion();

    @Select("SELECT version, content_val contentVal, content_length contentLength, add_file_val addFileVal, add_file_cnt addFileCnt, add_val addVal FROM POINT_VER WHERE version = #{pointVer}")
    public PointVersionVO selectPointVersionByVersion(int pointVer);

    @Insert("INSERT INTO POINT (user_id, `point`) VALUES(#{userId}, #{point})")
    public void insertPoint(PointVO vo);

    @Update("UPDATE POINT SET `point` = `point` + #{point} WHERE USER_ID=#{userId}")
    public int updatePoint(PointVO vo);

    @Insert("<script>" +
            "INSERT INTO POINT_HIST (proc_type, `point`, reg_date, user_id, refer_id, refer_table) VALUES " +
            "<foreach collection=\"list\" item=\"item\" separator=\",\">(#{item.procType}, #{item.point}, NOW(), #{item.userId}, #{item.referId}, #{item.referTable})</foreach>" +
            "</script>")
    public void insertPointHist(List<PointHistVO> vos);

    @Select("SELECT 'Y' CHK FROM DUAL WHERE EXISTS (SELECT USER_ID FROM POINT WHERE USER_ID=#{userId})")
    public String selectUserExists(String userId);
}
