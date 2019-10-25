package edu.uta.movietalk.mapper;

import edu.uta.movietalk.entity.DirtyWord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author hxy
 */
@Mapper
public interface DirtyWordMapper {

    /**
     * delete by id
     *
     * @param id Integer
     * @return int
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * insert by id
     *
     * @param record DirtyWord
     * @return int
     */
    int insert(DirtyWord record);

    /**
     * insert by record
     *
     * @param record DirtyWord
     * @return int
     */
    int insertSelective(DirtyWord record);

    /**
     * select by id
     *
     * @param id Integer
     * @return DirtyWord
     */
    DirtyWord selectByPrimaryKey(Integer id);

    /**
     * select by dirtyWord
     *
     * @param dirtyWord DirtyWord
     * @return DirtyWord
     */
    List<String> selectWordBySelective(DirtyWord dirtyWord);

    /**
     * update by dirtyWord
     *
     * @param record DirtyWord
     * @return int
     */
    int updateByPrimaryKeySelective(DirtyWord record);

    /**
     * update by dirtyWord
     *
     * @param record DirtyWord
     * @return int
     */
    int updateByPrimaryKey(DirtyWord record);
}