package edu.uta.movietalk.mapper;

import com.github.pagehelper.Page;
import edu.uta.movietalk.entity.Report;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * @author hxy
 */
@Mapper
public interface ReportMapper {

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
     * @param record
     * @return int
     */
    int insert(Report record);

    /**
     * insert by record
     *
     * @param record
     * @return int
     */
    int insertSelective(Report record);

    /**
     * select by id
     *
     * @param id
     * @return Report
     */
    Report selectByPrimaryKey(Integer id);

    /**
     * select by selective
     *
     * @param param
     * @return Report
     */
    Page<Report> selectBySelective(Map<String, Object> param);

    /**
     * update by record
     *
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(Report record);

    /**
     * update by record
     *
     * @param record
     * @return int
     */
    int updateByPrimaryKey(Report record);
}