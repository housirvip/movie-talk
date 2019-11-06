package edu.uta.movietalk.controller;

import com.github.pagehelper.Page;
import edu.uta.movietalk.base.BaseResponse;
import edu.uta.movietalk.base.PageResponse;
import edu.uta.movietalk.base.ResultResponse;
import edu.uta.movietalk.dto.PageDto;
import edu.uta.movietalk.entity.Report;
import edu.uta.movietalk.service.ReportService;
import edu.uta.movietalk.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import static edu.uta.movietalk.constant.Constant.REPORT_UNSOLVE;

/**
 * @author hxy
 */
@RestController
@RequestMapping(value = "/report")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    private final UserService userService;

    @GetMapping(value = "/getById/{reportId}")
    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<Report> getReportById(@PathVariable("reportId") Integer reportId) {

        return new ResultResponse<>(reportService.findReportById(reportId));
    }

    @GetMapping(value = "/all")
    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<Page> getReportBySelective(PageDto pageDto) {

        Page<Report> reportPage = reportService.pageReportBySelective(pageDto);

        return new PageResponse<>(reportPage, reportPage.getTotal());
    }

    @PutMapping(value = "")
    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<Integer> updateByAdmin(@RequestBody Report report) {

        return new ResultResponse<>(reportService.updateReport(report));
    }

    @DeleteMapping(value = "")
    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<Integer> deleteByAdmin(@RequestParam Integer id) {

        return new ResultResponse<>(reportService.deleteReport(id));
    }

    @PostMapping(value = "")
    public BaseResponse<Integer> createReport(@RequestBody Report report, Authentication auth) {

        report.setUid((Integer) auth.getPrincipal());
        report.setSolve(REPORT_UNSOLVE);

        return new ResultResponse<>(reportService.createReport(report));
    }

}
