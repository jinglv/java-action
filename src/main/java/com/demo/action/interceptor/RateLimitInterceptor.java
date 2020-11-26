package com.demo.action.interceptor;

import com.demo.action.excaption.BusinessException;
import com.demo.action.excaption.ErrorCodeEnum;
import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局限流拦截器
 *
 * @author jingLv
 * @date 2020/11/26
 */
@Slf4j
@Component
public class RateLimitInterceptor implements HandlerInterceptor {
    /**
     * 限流器实例（QPS限制为10）
     */
    private static final RateLimiter RATE_LIMITER = RateLimiter.create(10);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 尝试获取令牌
        if (!RATE_LIMITER.tryAcquire()) {
            log.error("系统已被限流...");
            throw new BusinessException(ErrorCodeEnum.RATE_LIMIT_ERROR);
        }
        return true;
    }
}
