package triple.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import triple.model.AddFileVO;
import triple.model.ReviewVO;

import java.util.List;

public interface ReviewMapper {
    @Select("SELECT 'Y' CHK FROM DUAL WHERE EXISTS (SELECT PLACE_ID FROM REVIEW WHERE PLACE_ID=#{placeId})")
    public String selectFirstReview(String placeId);

    @Insert("INSERT INTO REVIEW (review_id, user_id, place_id, content, reg_date, point_ver) VALUES(#{reviewId}, #{userId}, #{placeId}, #{content}, NOW(), #{pointVer})")
    public void insertReview(ReviewVO vo);

    @Insert("<script>" +
            "INSERT INTO ADD_FILE (attache_id, refer_id, file_path, reg_date) VALUES " +
            "<foreach collection=\"list\" item=\"item\" separator=\",\">(#{item.attacheId}, #{item.referId}, #{item.filePath}, NOW())</foreach>" +
            "</script>")
    public void insertAddFile(List<AddFileVO> vos);

    @Delete("DELETE FROM ADD_FILE WHERE attache_id=#{attacheId}")
    public int deleteAddFile(String attacheId);

    @Delete("DELETE FROM ADD_FILE WHERE refer_id=#{referId}")
    public int deleteAddFiles(String referId);

    @Delete("DELETE FROM REVIEW WHERE user_id=#{userId} AND review_id=#{reviewId}")
    public int deleteReview(ReviewVO vo);

    @Update("UPDATE db_erp_support.REVIEW SET content=#{content},modify_date=NOW() WHERE user_id=#{userId} AND review_id=#{reviewId}")
    public int updateReview(ReviewVO vo);

}