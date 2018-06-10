package com.jd.mobile.filter;

import com.jd.mobile.utils.TextUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

@WebFilter(filterName = "encoding", urlPatterns = "/*")
public class EncodingFilter implements Filter {
    private String charset = "UTF-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 解决response乱码
        response.setContentType("text/html;charset=" + charset);
        // 解决跨越
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept,token");
        filterChain.doFilter(new MyRequest(request), response);
    }

    @Override
    public void destroy() {

    }

    public class MyRequest extends HttpServletRequestWrapper{
        private HttpServletRequest request = null;
        private boolean flag = true;
        public MyRequest(HttpServletRequest request) {
            super(request);
            this.request = request;
        }

        @Override
        public Map<String, String[]> getParameterMap() {
            String method = request.getMethod();
            if ("post".equalsIgnoreCase(method)) {
                try {
                    request.setCharacterEncoding(charset);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return request.getParameterMap();
            }else if("get".equalsIgnoreCase(method)){
                Map<String, String[]> map = request.getParameterMap();
                if(flag){
                    for (String key :map.keySet()){
                        String[] arr = map.get(key);
                        for (int i = 0;i<arr.length;i++){
                            try {
                                arr[i] = new String(arr[i].getBytes("ISO-8859-1"),charset);
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    flag =false;
                }
                return map;
            }else {
                return super.getParameterMap();
            }
        }

        @Override
        public String[] getParameterValues(String name) {
            if(TextUtils.isEmpty(name))return null;
            Map<String, String[]> map = getParameterMap();
            if(map==null||map.size()==0)return null;
            return map.get(name);
        }

        @Override
        public String getParameter(String name) {
            if(TextUtils.isEmpty(name))return null;
            String[] values = getParameterValues(name);
            if(values==null||values.length==0)return null;
            return values[0];
        }
    }
}
