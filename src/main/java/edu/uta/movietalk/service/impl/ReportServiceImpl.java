package edu.uta.movietalk.service.impl;

import com.github.pagehelper.Page;
import edu.uta.movietalk.dto.PageDto;
import edu.uta.movietalk.entity.Report;
import edu.uta.movietalk.mapper.ReportMapper;
import edu.uta.movietalk.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author hxy
 */
@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportMapper reportMapper;

    @Override
    public Page<Report> pageReportBySelective(PageDto pageDto) {
        return reportMapper.selectBySelective(pageDto.putParam().getParamAsMap());
    }

    @Override
    public Report findReportById(Integer id) {
        return reportMapper.selectByPrimaryKey(id);
    }

    @Override
    public int createReport(Report report) {
        report.setCreateTime(new Date());
        return reportMapper.insert(report);
    }

    @Override
    public int deleteReport(Integer id) {
        if(id == null) {
            return 0;
        }

        return reportMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateReport(Report report) {
        report.setUpdateTime(new Date());
        return reportMapper.updateByPrimaryKeySelective(report);
    }
}
