package ink.hs.controller.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 微信公众号《和尚的破功之路》
 * @date 2022/11/23 08:47
 * version: 1.0
 */
//@Component
@Order(1)
//@Slf4j
public class WebTraceLogFilter extends OncePerRequestFilter {
    private static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

        filterChain.doFilter(requestWrapper, responseWrapper);
        requestin(requestWrapper);
        requestout(requestWrapper, responseWrapper);


        responseWrapper.copyBodyToResponse();
    }


    private String getRequestParams(ContentCachingRequestWrapper request) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String name = parameterNames.nextElement();
            sb.append(name).append("=").append(request.getParameter(name));
            if (parameterNames.hasMoreElements()) {
                sb.append("&");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    private Map<String, String> getHeaderInfo(HttpServletRequest request) {
        HashMap<String, String> header = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            String vaule = request.getHeader(name);
            header.put(name, vaule);
        }

        return header;
    }


    private void requestin(ContentCachingRequestWrapper request) throws JsonProcessingException {
        HashMap<String, String> logMap = new HashMap<>();

        String requestUri = request.getRequestURI();
        //Map<String, String> headerInfo = getHeaderInfo(request);
        String args = getRequestParams(request);
        logMap.put("url", requestUri);
        //logMap.put("headerInfo", objectMapper.writeValueAsString(headerInfo));
        logMap.put("args", args);
        logMap.put("requestBody", getRequestBody(request));
        //log.info("_com_request_in" + objectMapper.writeValueAsString(logMap));
        System.out.println(("_com_request_in" + objectMapper.writeValueAsString(logMap)));
    }

    private void requestout(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        HashMap<String, String> requestLogMap = new HashMap<>();

        String requestUri = request.getRequestURI();
        //Map<String, String> headerInfo = getHeaderInfo(request);
        String args = getRequestParams(new ContentCachingRequestWrapper(request));
        requestLogMap.put("url", requestUri);
        //requestLogMap.put("headerInfo", objectMapper.writeValueAsString(headerInfo));
        requestLogMap.put("args", args);
        requestLogMap.put("requestBody", getRequestBody(request));

        HashMap<String, String> responseLogMap = new HashMap<>();
        String responseParams = getResponseParams(response);
        responseLogMap.put("response", objectMapper.writeValueAsString(responseParams));

        //log.info("_com_request_out" + objectMapper.writeValueAsString(requestLogMap) + "||" + objectMapper.writeValueAsString(responseLogMap));
        System.out.println(("_com_request_out" + objectMapper.writeValueAsString(requestLogMap) + "||" + objectMapper.writeValueAsString(responseLogMap)));
    }

    private String getRequestBody(HttpServletRequest request) {

        ContentCachingRequestWrapper wrapper = WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);

        if (wrapper == null) {
            return "";
        }
        byte[] requestArray = wrapper.getContentAsByteArray();
        try {
            return new String(requestArray, StandardCharsets.UTF_8);
        } catch (Exception e) {
            //log.error(String.valueOf(e));
        }
        return "";
    }

    private String genPayload(String payload, byte[] buf, String characterEncoding) {

        if (buf.length > 0 && buf.length < 1024 * 512) {
            try {
                payload = new String(buf, 0, buf.length, characterEncoding);
            } catch (UnsupportedEncodingException ex) {
                payload = "[unknown]";
            }
        }

        return payload;

    }

    private String getResponseParams(HttpServletResponse response) {

        ContentCachingResponseWrapper wrapper = WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
        if (wrapper == null) {
            return "";
        }
        byte[] responseArray = wrapper.getContentAsByteArray();
        try {
            String responseStr = new String(responseArray, StandardCharsets.UTF_8);
            return responseStr;
        } catch (Exception e) {
            //log.error(String.valueOf(e));
        }
        return "";
    }


}
