package edu.uta.movietalk.aop;

import com.github.pagehelper.PageHelper;
import edu.uta.movietalk.constant.Constant;
import edu.uta.movietalk.dto.PageDto;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author housirvip
 */
@Aspect
@Component
public class PageHelperAop {

    @Before("execution(public com.github.pagehelper.Page edu.uta.movietalk.service.*Service.page*(..))")
    public void doBefore(JoinPoint joinPoint) {

        Object[] args = joinPoint.getArgs();
        if (args == null || args.length == 0 || !(args[0] instanceof PageDto)) {
            return;
        }

        PageDto pageDto = (PageDto) args[0];

        int pageNum = Optional.ofNullable(pageDto.getPageNum()).orElse(Constant.PAGE_NUM_VALUE);
        int pageSize = Optional.ofNullable(pageDto.getPageSize()).orElse(Constant.PAGE_SIZE_VALUE);

        PageHelper.startPage(pageNum, pageSize);
    }
}
