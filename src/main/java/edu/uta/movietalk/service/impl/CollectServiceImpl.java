package edu.uta.movietalk.service.impl;

import com.github.pagehelper.Page;
import edu.uta.movietalk.dto.PageDto;
import edu.uta.movietalk.entity.Collect;
import edu.uta.movietalk.mapper.CollectMapper;
import edu.uta.movietalk.service.CollectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author hxy
 */
@Service
@RequiredArgsConstructor
public class CollectServiceImpl implements CollectService {

    private final CollectMapper collectMapper;

    @Override
    public Page<Collect> pageCollectBySelective(PageDto pageDto) {
        return collectMapper.selectBySelective(pageDto.putParam().getParamAsMap());
    }

    @Override
    public int insertCollect(Collect collect) {
        return collectMapper.insert(collect);
    }

    @Override
    public int deleteCollect(Integer id) {
        return collectMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int isCollect(Collect collect) {
        return collectMapper.isCollect(collect);
    }
}
