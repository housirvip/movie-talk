package edu.uta.movietalk.service;

import com.github.pagehelper.Page;
import edu.uta.movietalk.dto.PageDto;
import edu.uta.movietalk.entity.Collect;

/**
 * @author hxy
 */
public interface CollectService {

    Page<Collect> pageCollectBySelective(PageDto pageDto);

    int insertCollect(Collect collect);

    int deleteCollect(Integer id);

    int isCollect(Collect collect);
}
