package com.portfolio1.base;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.HandlerMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BaseController {
    @Resource(name = "redisTemplate")
    private HashOperations<String, String, String> redisHash;

    protected HashMap<Object, Object> getAllParameters(HttpServletRequest request) {
        return getAllParameters(request, true);
    }

    protected HashMap<Object, Object>  getAllParameters(HttpServletRequest request, boolean session){
        HashMap<Object, Object> parameters = new HashMap<Object, Object>();

        // Path Variable 파라메터를 추가한다.
        addPathParameter(parameters, request);

        // 텍스트 파라메터를 추가한다.
        addTextParameter(parameters, request);

        if (request instanceof MultipartHttpServletRequest) {
            // 파일 파라메터를 추가한다.
            addFileParameter(parameters, (MultipartHttpServletRequest) request);
        }

        if (session) {
            // 사용자 파라메터를 추가한다.
            addUserParameter(parameters);
        }

        return parameters;
    }

    // Path Variable 파라메터를 추가한다.
    private void addPathParameter(HashMap<Object, Object> parameters, HttpServletRequest request) {

        // Path Variable 을 추가한다.
        Map<String, Object> pathVariables = (Map<String, Object>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        if (pathVariables == null)
            return;

        for (String key : pathVariables.keySet()) {
            parameters.put(key, pathVariables.get(key));
        }
    }

    private void addTextParameter(HashMap<Object, Object> parameters, HttpServletRequest request) {
        Enumeration<?> enumeration = request.getParameterNames();

        while (enumeration.hasMoreElements()) {
            String name = (String) enumeration.nextElement();

            parameters.put(getParameterName(name), request.getParameterValues(name));
        }
    }
    private void addFileParameter(HashMap<Object, Object> parameters, MultipartHttpServletRequest request) {
        Iterator<String> iterator = request.getFileNames();

        while (iterator.hasNext()) {
            String name = iterator.next();

            parameters.put(getParameterName(name), getFileParameter(request, name));
        }
    }

    private MultipartFile[] getFileParameter(MultipartHttpServletRequest request, String name) {
        Object[] source = request.getFiles(name).toArray();

        MultipartFile[] destination = new MultipartFile[source.length];

        System.arraycopy(source, 0, destination, 0, destination.length);

        return destination;
    }

    private String getParameterName(String name) {
        if (name.endsWith("[]")) {
            return name.substring(0, name.lastIndexOf("[]"));
        }

        return name;
    }

    private void addUserParameter(HashMap<Object, Object> parameters) {
        parameters.put("User",new HashMap<String, String>(redisHash.entries("User")));
    }
}
