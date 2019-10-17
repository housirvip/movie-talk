package edu.uta.movietalk.service.impl;

import com.github.pagehelper.Page;
import edu.uta.movietalk.dto.PageDto;
import edu.uta.movietalk.entity.Score;
import edu.uta.movietalk.mapper.ScoreMapper;
import edu.uta.movietalk.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author hxy
 */
@Service
@RequiredArgsConstructor
public class ScoreServiceImpl implements ScoreService {

    private final ScoreMapper scoreMapper;

    @Override
    public int insertScore(Score score) {
        return scoreMapper.insert(score);
    }

    @Override
    public int updateScore(Score score) {
        return scoreMapper.updateByPrimaryKeySelective(score);
    }

    @Override
    public Page<Score> pageScore(PageDto pageDto) {
        return scoreMapper.selectByPrimaryKeySelective(pageDto.putParam().getParamAsMap());
    }
}
