package com.kxt.yanglao.watch.controller;

import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import com.kxt.yanglao.watch.jpa.entity.Watch;
import com.kxt.yanglao.watch.jpa.reposity.WatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.*;
import javax.servlet.http.*;

import com.kxt.yanglao.watch.jpa.util.UrlLib;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.*;

@RestController
public class WatchController {

    @Value("${watch.url}")
    private String strUrl;

    @Autowired
    private WatchRepository watchRepository;

    @Autowired
    private HttpServletRequest request = new HttpServletRequest() {
        @Override
        public String getAuthType() {
            return null;
        }

        @Override
        public Cookie[] getCookies() {
            return new Cookie[0];
        }

        @Override
        public long getDateHeader(String s) {
            return 0;
        }

        @Override
        public String getHeader(String s) {
            return null;
        }

        @Override
        public Enumeration<String> getHeaders(String s) {
            return null;
        }

        @Override
        public Enumeration<String> getHeaderNames() {
            return null;
        }

        @Override
        public int getIntHeader(String s) {
            return 0;
        }

        @Override
        public String getMethod() {
            return null;
        }

        @Override
        public String getPathInfo() {
            return null;
        }

        @Override
        public String getPathTranslated() {
            return null;
        }

        @Override
        public String getContextPath() {
            return null;
        }

        @Override
        public String getQueryString() {
            return null;
        }

        @Override
        public String getRemoteUser() {
            return null;
        }

        @Override
        public boolean isUserInRole(String s) {
            return false;
        }

        @Override
        public Principal getUserPrincipal() {
            return null;
        }

        @Override
        public String getRequestedSessionId() {
            return null;
        }

        @Override
        public String getRequestURI() {
            return null;
        }

        @Override
        public StringBuffer getRequestURL() {
            return null;
        }

        @Override
        public String getServletPath() {
            return null;
        }

        @Override
        public HttpSession getSession(boolean b) {
            return null;
        }

        @Override
        public HttpSession getSession() {
            return null;
        }

        @Override
        public String changeSessionId() {
            return null;
        }

        @Override
        public boolean isRequestedSessionIdValid() {
            return false;
        }

        @Override
        public boolean isRequestedSessionIdFromCookie() {
            return false;
        }

        @Override
        public boolean isRequestedSessionIdFromURL() {
            return false;
        }

        @Override
        public boolean isRequestedSessionIdFromUrl() {
            return false;
        }

        @Override
        public boolean authenticate(HttpServletResponse httpServletResponse) throws IOException, ServletException {
            return false;
        }

        @Override
        public void login(String s, String s1) throws ServletException {

        }

        @Override
        public void logout() throws ServletException {

        }

        @Override
        public Collection<Part> getParts() throws IOException, ServletException {
            return null;
        }

        @Override
        public Part getPart(String s) throws IOException, ServletException {
            return null;
        }

        @Override
        public <T extends HttpUpgradeHandler> T upgrade(Class<T> aClass) throws IOException, ServletException {
            return null;
        }

        @Override
        public Object getAttribute(String s) {
            return null;
        }

        @Override
        public Enumeration<String> getAttributeNames() {
            return null;
        }

        @Override
        public String getCharacterEncoding() {
            return null;
        }

        @Override
        public void setCharacterEncoding(String s) throws UnsupportedEncodingException {

        }

        @Override
        public int getContentLength() {
            return 0;
        }

        @Override
        public long getContentLengthLong() {
            return 0;
        }

        @Override
        public String getContentType() {
            return null;
        }

        @Override
        public ServletInputStream getInputStream() throws IOException {
            return null;
        }

        @Override
        public String getParameter(String s) {
            return null;
        }

        @Override
        public Enumeration<String> getParameterNames() {
            return null;
        }

        @Override
        public String[] getParameterValues(String s) {
            return new String[0];
        }

        @Override
        public Map<String, String[]> getParameterMap() {
            return null;
        }

        @Override
        public String getProtocol() {
            return null;
        }

        @Override
        public String getScheme() {
            return null;
        }

        @Override
        public String getServerName() {
            return null;
        }

        @Override
        public int getServerPort() {
            return 0;
        }

        @Override
        public BufferedReader getReader() throws IOException {
            return null;
        }

        @Override
        public String getRemoteAddr() {
            return null;
        }

        @Override
        public String getRemoteHost() {
            return null;
        }

        @Override
        public void setAttribute(String s, Object o) {

        }

        @Override
        public void removeAttribute(String s) {

        }

        @Override
        public Locale getLocale() {
            return null;
        }

        @Override
        public Enumeration<Locale> getLocales() {
            return null;
        }

        @Override
        public boolean isSecure() {
            return false;
        }

        @Override
        public RequestDispatcher getRequestDispatcher(String s) {
            return null;
        }

        @Override
        public String getRealPath(String s) {
            return null;
        }

        @Override
        public int getRemotePort() {
            return 0;
        }

        @Override
        public String getLocalName() {
            return null;
        }

        @Override
        public String getLocalAddr() {
            return null;
        }

        @Override
        public int getLocalPort() {
            return 0;
        }

        @Override
        public ServletContext getServletContext() {
            return null;
        }

        @Override
        public AsyncContext startAsync() throws IllegalStateException {
            return null;
        }

        @Override
        public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse) throws IllegalStateException {
            return null;
        }

        @Override
        public boolean isAsyncStarted() {
            return false;
        }

        @Override
        public boolean isAsyncSupported() {
            return false;
        }

        @Override
        public AsyncContext getAsyncContext() {
            return null;
        }

        @Override
        public DispatcherType getDispatcherType() {
            return null;
        }
    };

    @RequestMapping(value = "/location")
    private String SaveLocation(@RequestParam("imei") String strImei, @RequestParam("city") String strCity,
                                @RequestParam(value = "time_begin",defaultValue = "") String strBeginTime, @RequestParam("lon") String strLon,
                                @RequestParam("lat") String strLat, @RequestParam("address") String strAddress,
                                @RequestParam("type") String strLocationType) {
        Watch watch = new Watch();
        watch.setImei(strImei);
        watch.setCity(strCity);
        watch.setBegin_Time(strBeginTime);
        watch.setLon(Double.valueOf(strLon));
        watch.setLat(Double.valueOf(strLat));
        watch.setAddress(strAddress);
        watch.setData_Type("Location");
        watch.setLocation_Type(strLocationType);
        watchRepository.save(watch);
        return "0";
    }

    @RequestMapping(value = "/sos")
    private String SaveSos(@RequestParam("imei") String strImei, @RequestParam("city") String strCity,
                           @RequestParam(value = "time_begin",defaultValue = "") String strBeginTime, @RequestParam("lon") String strLon,
                           @RequestParam("lat") String strLat, @RequestParam("address") String strAddress,
                           @RequestParam("type") String strLocationType, @RequestParam("heartrate") String strHeartRate) {
        Watch watch = new Watch();
        watch.setImei(strImei);
        watch.setCity(strCity);
        watch.setBegin_Time(strBeginTime);
        watch.setLon(Double.valueOf(strLon));
        watch.setLat(Double.valueOf(strLat));
        watch.setAddress(strAddress);
        watch.setData_Type("SOS");
        watch.setHeart_Rate(Integer.valueOf(strHeartRate));
        watch.setLocation_Type(strLocationType);
        watchRepository.save(watch);
        return "0";
    }

    @RequestMapping(value = "/heartrate")
    private String SaveHeartRate(@RequestParam("heartrate") String strHeartRate,@RequestParam(value = "time_begin",defaultValue = "") String strBeginTime,
                                 @RequestParam("imei") String strImei) {
        Watch watch = new Watch();
        watch.setImei(strImei);
        watch.setBegin_Time(strBeginTime);
        watch.setHeart_Rate(Integer.valueOf(strHeartRate));
        watch.setData_Type("HeartRate");
        watchRepository.save(watch);
        return "0";
    }

    @RequestMapping(value = "/steps")
    private String SaveSteps(@RequestParam("value") String strSteps, @RequestParam(value = "time_begin",defaultValue = "") String strBeginTime,
                             @RequestParam("imei") String strImei) {
        Watch watch = new Watch();
        watch.setImei(strImei);
        watch.setBegin_Time(strBeginTime);
        watch.setSteps(Integer.valueOf(strSteps));
        watch.setData_Type("Steps");
        watchRepository.save(watch);
        return "0";
    }

    @RequestMapping(value = "/sleeps")
    private String SaveSleeps(@RequestParam("data") String strSleepData, @RequestParam(value = "time_begin",defaultValue = "") String strBeginTime,
                              @RequestParam("time_end") String strEndTime, @RequestParam("imei") String strImei) {
        Watch watch = new Watch();
        watch.setImei(strImei);
        watch.setBegin_Time(strBeginTime);
        watch.setEnd_Time(strEndTime);
        watch.setSleep_Data(strSleepData);
        watch.setData_Type("Sleeps");
        watchRepository.save(watch);
        return "0";
    }

    @RequestMapping(value = "/switch")
    private String SaveSwitch(@RequestParam(value = "time_begin",defaultValue = "") String strBeginTime,
                              @RequestParam("type") String strType, @RequestParam("imei") String strImei,
                              @RequestParam("remaining_power") String strPower) {
        Watch watch = new Watch();
        watch.setImei(strImei);
        watch.setBegin_Time(strBeginTime);
        watch.setSwitch_Type(strType);
        watch.setRemaining_Power(Integer.valueOf(strPower));
        watch.setData_Type("Switch");
        watchRepository.save(watch);
        return "0";
    }

    @RequestMapping(value = "/fall")
    private String SaveFall(@RequestParam("imei") String strImei, @RequestParam("city") String strCity,
                            @RequestParam(value = "time_begin",defaultValue = "") String strBeginTime, @RequestParam("lon") String strLon,
                            @RequestParam("lat") String strLat, @RequestParam("address") String strAddress,
                            @RequestParam("type") String strLocationType) {
        Watch watch = new Watch();
        watch.setImei(strImei);
        watch.setCity(strCity);
        watch.setBegin_Time(strBeginTime);
        watch.setLon(Double.valueOf(strLon));
        watch.setLat(Double.valueOf(strLat));
        watch.setAddress(strAddress);
        watch.setData_Type("Fall");
        watch.setLocation_Type(strLocationType);
        watchRepository.save(watch);
        return "0";
    }

    @RequestMapping(value = "/login")
    private String Login(@RequestParam("username") String strUserName,
                         @RequestParam("password") String strPassWord) {
        String strReturn = null;
        String url = strUrl + "/api/auth/login";
        Map<String, String> map = new HashMap<>();
        map.put("username", strUserName);
        map.put("password", strPassWord);
        try {
            strReturn = UrlLib.doPost(request, url, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strReturn;
    }

    @RequestMapping(value = "/getlocation")
    private String GetLocation(@RequestParam(value = "imei",defaultValue = "") String strImei){
        String strReturn = null;
        String url = strUrl + "/api/device/" + strImei + "/get_locationdata";
        try {
            strReturn = UrlLib.doPost(request, url, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strReturn;
    }


    @RequestMapping(value = "/getnewlocation")
    private String GetNewLoction(@RequestParam("imei") String strImei) {
        String url = strUrl + "/api/device/" + strImei + "/data/locationdata";
        String strReturn = null;
        try {
            strReturn = UrlLib.doGet(request, url, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strReturn;
    }

    @RequestMapping(value = "/getbloodpressuredata")
    private String GetBloodPressureData(@RequestParam(value = "imei", defaultValue = "") String strImei,
                                        @RequestParam(value = "time_begin", defaultValue = "") String strTimeBegin) {
        String url = "";
        if (strTimeBegin.length() ==0){
            url = strUrl + "/api/bloodpressuredata?device=" + strImei + "&depth=1&rows_per_page=1";
        } else{
            url = strUrl + "/api/bloodpressuredata?device=" + strImei + "&time_begin=" + strTimeBegin + "&depth=1&rows_per_page=1";
        }
        String strReturn = null;
        try {
            strReturn = UrlLib.doGet(request, url, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strReturn;
    }

    @RequestMapping(value = "/setfrequency_location")
    private String SetFrequency_Location(@RequestParam("imei") String strImei,
                                         @RequestParam("values") String strValues) {
        String strReturn = null;
        String url = strUrl + "/api/device/" + strImei + "/edit";
        Map<String, String> map = new HashMap<>();
        map.put("frequency_location", strValues);
        try {
            strReturn = UrlLib.doPost(request, url, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strReturn;
    }
}
