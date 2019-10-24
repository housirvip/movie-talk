package edu.uta.movietalk.service;

import com.github.pagehelper.Page;
import edu.uta.movietalk.dto.PageDto;
import edu.uta.movietalk.entity.Report;

/**
 * @author hxy
 */
public interface ReportService {

    Page<Report> pageReportBySelective(PageDto pageDto);

    Report findReportById(Integer id);

    int createReport(Report report);

    int deleteReport(Integer id);

    int updateReport(Report report);
}
