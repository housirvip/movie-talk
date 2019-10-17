package edu.uta.movietalk.mapper;

import com.github.pagehelper.Page;
import edu.uta.movietalk.entity.Score;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * @author hxy
 */
@Mapper
public interface ScoreMapper {

    /**
     * delete by id
     *
     * @param id Integer
     * @return int
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * insert by record
     *
     * @param record Score
     * @return int
     */
    int insert(Score record);

    /**
     * insert by selective
     *
     * @param record Score
     * @return int
     */
    int insertSelective(Score record);

    /**
     * select by id
     *
     * @param id Integer
     * @return Score
     */
    Score selectByPrimaryKey(Integer id);

    /**
     * select by param
     *
     * @param param Map
     * @return Score
     */
    Page<Score> selectByPrimaryKeySelective(Map<String, Object> param);

    /**
     * update by Score
     *
     * @param record Score
     * @return int
     */
    int updateByPrimaryKeySelective(Score record);

    /**
     * update by Score
     *
     * @param record Score
     * @return int
     */
    int updateByPrimaryKey(Score record);
}