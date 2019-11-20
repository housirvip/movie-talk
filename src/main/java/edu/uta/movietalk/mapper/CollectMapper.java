package edu.uta.movietalk.mapper;

import com.github.pagehelper.Page;
import edu.uta.movietalk.entity.Collect;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * @author hxy
 */
@Mapper
public interface CollectMapper {

    /**
     * delete by id
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * insert by record
     * @param record
     * @return
     */
    int insert(Collect record);

    /**
     * insert by record
     * @param record
     * @return
     */
    int insertSelective(Collect record);

    /**
     * select by id
     * @param id
     * @return
     */
    Collect selectByPrimaryKey(Integer id);

    /**
     * select by id
     * @param param
     * @return
     */
    Page<Collect> selectBySelective(Map<String, Object> param);

    /**
     * update by record
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Collect record);

    /**
     * update by record
     * @param record
     * @return
     */
    int updateByPrimaryKey(Collect record);

    /**
     * is collect by record
     * @param record
     * @return
     */
    int isCollect(Collect record);
}