package com.example.jsoup_xss.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * kbsys
 *
 * @param model
 * @param menuModel
 * @param authId
 * @param request
 * @return
 */

@Slf4j
public class RequestWrapper extends HttpServletRequestWrapper {
    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request The request to wrap
     * @throws IllegalArgumentException if the request is null
     */
    HttpServletRequest request;
    private String requestData;
    public RequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        ObjectMapper mapper = new ObjectMapper();
        requestData=requestDataByte(request);
        // String to JSONObject
        Map<String,Object> map= mapper.readValue(requestData, HashMap.class);


    }

    @Override
    public Map<String, String[]> getParameterMap() {
        Map<String, String[]> paramMap = super.getParameterMap();
        return super.getParameterMap();
    }
    /**
     * The default behavior of this method is to return getInputStream() on the
     * wrapped request object.
     */
    @Override
    public ServletInputStream getInputStream() throws IOException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(this.requestData.getBytes(StandardCharsets.UTF_8));
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return inputStream.available() == 0;
            }

            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setReadListener(ReadListener listener) {
                throw new UnsupportedOperationException();
            }

            @Override
            public int read() {
                return inputStream.read();
            }
        };
    }

    /**
     * The default behavior of this method is to return getReader() on the
     * wrapped request object.
     */
    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }
    @Override
    public String getParameter(String paramName) {
        String value = super.getParameter(paramName);
        return doFilter(paramName, value);
    }
    private String doFilter(String pathName,String value){
        log.debug("pathName::{} value::{}",pathName,value);
        return "test";
    }
    //==request Body 가로채기==//
    private String requestDataByte(HttpServletRequest request) throws IOException {
        byte[] rawData = new byte[128];
        InputStream inputStream = request.getInputStream();
        rawData = IOUtils.toByteArray(inputStream);
        return new String(rawData);
    }

}
