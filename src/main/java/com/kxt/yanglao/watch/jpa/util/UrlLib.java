package com.kxt.yanglao.watch.jpa.util;

import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

public class UrlLib {
    /**
     * 获取Set-Cookie 写入session中，然后请求在头信息的时候写入
     */
    public static String doGet(HttpServletRequest request, String url, Map<String, String> param) throws Exception {
        //当传入的url返回不为空的时候，读取数据
        InputStream input = null;
        String strReturn = null;
        byte[] data = null;//提高字符数据的生成
        if (StringUtils.isNotBlank(url)) {
            try {
                //设置请求的头信息
                URL urlInfo = new URL(url  + getParam(param));
                URLConnection connection = urlInfo.openConnection();
                connection.addRequestProperty("Host", urlInfo.getHost());//设置头信息
                connection.addRequestProperty("Connection", "keep-alive");
                connection.addRequestProperty("Accept", "*/*");
                connection.addRequestProperty("Cookie", getSessionIncookie(request));//设置获取的cookie
                connection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64)");
                connection.connect();
                // 获取所有响应头字段
                getCookieToSession(request, connection);
                //获取请求回来的信息
                input = connection.getInputStream();//定义返回数据的格式
                data = new byte[input.available()];
                input.read(data);

                strReturn = new String(data);

            } catch (Exception e) {
                throw new Exception("读取Url数据失败：" + url, e);
            } finally {
                try {
                    input.close();
                } catch (Exception e) {
                }
            }
        }
        return strReturn;
    }

    /**
     * 获取post请求
     *
     * @param request
     * @param url
     * @param param
     * @return
     */
    public static String doPost(HttpServletRequest request, String url,
                                Map<String, String> param) throws Exception {
        //当传入的url返回不为空的时候，读取数据
        InputStream input = null;
        PrintWriter out = null;
        byte[] data = null;//提高字符数据的生成
        String strReturn = null;
        if (StringUtils.isNotBlank(url)) {
            try {
                //设置请求的头信息
                URL urlInfo = new URL(url);
                URLConnection connection = urlInfo.openConnection();
                connection.addRequestProperty("Host", urlInfo.getHost());//设置头信息
                connection.addRequestProperty("Connection", "keep-alive");
                connection.addRequestProperty("Accept", "*/*");
                connection.addRequestProperty("Cookie", getSessionIncookie(request));//设置获取的cookie
                connection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64)");
                // 发送POST请求必须设置如下两行
                connection.setDoOutput(true);
                connection.setDoInput(true);
                // 获取URLConnection对象对应的输出流
                out = new PrintWriter(connection.getOutputStream());
                // 发送请求参数
                out.print(getParam(param));
                // flush输出流的缓冲
                out.flush();
                // 获取所有响应头字段
                getCookieToSession(request, connection);
                //获取请求回来的信息
                input = connection.getInputStream();//定义返回数据的格式
                data = new byte[input.available()];
                input.read(data);
                strReturn = new String(data);
            } catch (Exception e) {
                throw new Exception("读取Url数据失败：" + url, e);
            } finally {
                try {
                    input.close();
                } catch (Exception e) {
                }
            }
        }
        return strReturn;
    }


    /**
     * 获取在session中保存的cookie
     *
     * @param request
     * @return
     */
    private static String getSessionIncookie(HttpServletRequest request) {
        String back = "";
        HttpSession session = request.getSession();

        //String verCode = (String) session.getAttribute("verCode");
        //String JSESSIONID = (String) session.getAttribute("JSESSIONID");

        String cookie = (String) session.getAttribute("user");

        //System.out.println(verCode);
        //System.out.println(JSESSIONID);
        System.out.println(cookie);

//        if (verCode != null) {
//            back = verCode;
//        }
//
//        if (JSESSIONID != null) {
//            if (!StringUtils.isEmpty(back)) {
//                back += " ";
//            }
//            back += JSESSIONID;
//        }

        if (cookie != null) {
            back = cookie;
        }
        System.out.println(back);
        return back;
    }

    /**
     * 将请求的头信息获取到session中
     *
     * @param request
     * @param connection
     */
    private static void getCookieToSession(HttpServletRequest request,
                                           URLConnection connection) {

        Map<String, List<String>> map = connection.getHeaderFields();
        for (String key : map.keySet()) {
            System.out.println(key + "--->" + map.get(key));
        }
        HttpSession session = request.getSession();

        List<String> cookie = map.get("Set-Cookie");
        if (cookie != null) {

            //String verCode=getCookieBySet("verCode",cookie.get(0));

            //String JSESSIONID=getCookieBySet("JSESSIONID",cookie.get(0));

            //if(verCode!=null){
            //session.setAttribute("verCode",verCode);
            //}

            //if(JSESSIONID!=null){
            //session.setAttribute("JSESSIONID",JSESSIONID);
            //}

            String user=getCookieBySet("user",cookie.get(0));
            if (user != null) {
                session.setAttribute("user", user);
            }
        }

    }

    /**
     * 获取verCode=b5pcogZaFGikpAc1mQ+G5wOJGBWtXLsHafpf5wlgF5s=; Path=/SSODAO/; HttpOnly
     * 的cookie信息
     */
    public static String getCookieBySet(String name, String set) {

        String regex = name + "=(.*?);";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(set);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;

    }

    /**
     * 将参数以 key=123&v=456 返回
     *
     * @param param
     * @return
     */
    public static String getParam(Map<String, String> param) {
        StringBuilder str = new StringBuilder();
        int size = 0;

        if (param == null)
            return "";
        for (Map.Entry<String, String> m : param.entrySet()) {
            str.append(m.getKey());
            str.append("=");
            str.append(m.getValue());
            if (size < param.size() - 1) {
                str.append("&");
            }
            size++;
        }
        System.out.println(str.toString());
        return str.toString();
    }
}
