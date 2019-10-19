package edu.uta.movietalk.service;


import com.github.pagehelper.Page;
import edu.uta.movietalk.dto.PageDto;
import edu.uta.movietalk.entity.Score;

/**
 * @author hxy
 */
public interface ScoreService {


    /**
     * insert by score
     *
     * @param score Score
     * @return int
     */
    int insertScore(Score score);

    /**
     * update by score
     *
     * @param score Score
     * @return int
     */
    int updateScore(Score score);

    /**
     * select by id
     *
     * @param pageDto PageDto
     * @return Score
     */
    Page<Score> pageScore(PageDto pageDto);

    /**
     * select user Record
     *
     * @param uid Integer
     * @return UserRecord
     */
    Score selectMaxScoreByUid(Integer uid);
}
