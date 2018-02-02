package com.ssm.question.intercepter;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IpInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("----------------------------------------------------");
        System.out.println(getUserIpAddr(request));
        System.out.println("----------------------------------------------------");
        return true;
    }

    /**
     * 获取用户的真实IP
     *
     * @param request
     * @return
     */
    public static String getUserIpAddr(HttpServletRequest request) {
        String unknown = "unknown";
        String ip = request.getHeader("X-Real-IP");
        if (isBlank(ip) || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("x-forwarded-for");
        }

        if (isBlank(ip) || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (isBlank(ip) || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (isBlank(ip) || unknown.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (null != ip && ip.length() != 0) {
            String tmpIps[] = ip.split(",");
            for (String tmpIp : tmpIps) {
                if (null == tmpIp) {
                    continue;
                }
                tmpIp = tmpIp.trim();
                // 过滤本机IP和内网IP，内网IP地址:10.x.x.x;192.168.x.x;172.16.x.x至172.31.x.x。由于172.16.x.x至172.31.x.x比较少见故不做过滤。
                if (tmpIp.length() != 0 && !unknown.equalsIgnoreCase(tmpIp) && tmpIp.indexOf("127.0.0.1") == -1
                        && !tmpIp.startsWith("192.168.") && !tmpIp.startsWith("10.")) {
                    return tmpIp;
                }
            }
        }
        return request.getRemoteAddr();
    }

    public static boolean isBlank(String str) {
        if (str == null || str.trim().length() == 0) {
            return false;
        }
        return true;
    }
}
