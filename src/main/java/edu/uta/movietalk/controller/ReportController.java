package edu.uta.movietalk.controller;

import com.github.pagehelper.Page;
import com.google.common.base.Preconditions;
import edu.uta.movietalk.base.BaseResponse;
import edu.uta.movietalk.base.PageResponse;
import edu.uta.movietalk.base.ResultResponse;
import edu.uta.movietalk.constant.UserGroup;
import edu.uta.movietalk.dto.PageDto;
import edu.uta.movietalk.entity.Report;
import edu.uta.movietalk.service.ReportService;
import edu.uta.movietalk.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import static edu.uta.movietalk.constant.Constant.REPORT_UNSOLVE;
import static edu.uta.movietalk.constant.ErrorMessage.USER_GROUP_LIMIT;

@RestController
@RequestMapping(value = "/report")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    private final UserService userService;

    @GetMapping(value = "/getById/{reportId}")
    public BaseResponse<Report> getReportById(@PathVariable("reportId") Integer reportId, Authentication auth) {

        Boolean result = userService.oneByIdWithInfo((Integer) auth.getPrincipal()).getGroup().getValue().equals(UserGroup.Admin.getValue());
        Preconditions.checkArgument(result,USER_GROUP_LIMIT);

        return new ResultResponse<>(reportService.findReportById(reportId));
    }

    @GetMapping(value = "/all")
    public BaseResponse<Page> getReportBySelective(PageDto pageDto, Authentication auth) {

        Boolean result = userService.oneByIdWithInfo((Integer) auth.getPrincipal()).getGroup().getValue().equals(UserGroup.Admin.getValue());
        Preconditions.checkArgument(result,USER_GROUP_LIMIT);

        Page<Report> reportPage = reportService.pageReportBySelective(pageDto);

        return new PageResponse<>(reportPage, reportPage.getTotal());
    }

    @PutMapping(value = "")
    public BaseResponse<Integer> updateReport(@RequestBody Report report, Authentication auth) {

        Boolean result = userService.oneByIdWithInfo((Integer) auth.getPrincipal()).getGroup().getValue().equals(UserGroup.Admin.getValue());
        Preconditions.checkArgument(result,USER_GROUP_LIMIT);

        return new ResultResponse<>(reportService.updateReport(report));
    }

    @DeleteMapping(value = "")
    public BaseResponse<Integer> deleteReport(@RequestParam Integer id, Authentication auth) {

        Boolean result = userService.oneByIdWithInfo((Integer) auth.getPrincipal()).getGroup().getValue().equals(UserGroup.Admin.getValue());
        Preconditions.checkArgument(result,USER_GROUP_LIMIT);

        return new ResultResponse<>(reportService.deleteReport(id));
    }

    @PostMapping(value = "")
    public BaseResponse<Integer> createReport(@RequestBody Report report, Authentication auth) {

        report.setUid((Integer) auth.getPrincipal());
        report.setSolve(REPORT_UNSOLVE);

        return new ResultResponse<>(reportService.createReport(report));
    }

}
