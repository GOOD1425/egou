package com.zbdx.egou.utils;


import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IPutils {
    /**
     * 读取
     *
     * @param rd
     * @return
     * @throws IOException
     */
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    /**
     * 创建链接
     *
     * @param url
     * @return
     * @throws IOException
     * @throws JSONException
     */
    private static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = JSONObject.parseObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }


    public static String getV4IP(){
        String ip = "";
        String chinaz = "http://ip.chinaz.com";

        StringBuilder inputLine = new StringBuilder();
        String read = "";
        URL url = null;
        HttpURLConnection urlConnection = null;
        BufferedReader in = null;
        try {
            url = new URL(chinaz);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedReader( new InputStreamReader(urlConnection.getInputStream(),"UTF-8"));
            while((read=in.readLine())!=null){
                inputLine.append(read+"\r\n");
            }
            //System.out.println(inputLine.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(in!=null){
                try {
                    in.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }


        Pattern p = Pattern.compile("\\<dd class\\=\"fz24\">(.*?)\\<\\/dd>");
        Matcher m = p.matcher(inputLine.toString());
        if(m.find()){
            String ipstr = m.group(1);
            ip = ipstr;
            //System.out.println(ipstr);
        }
        return ip;
    }
    public static String  getAddr() throws IOException {
        String ip = IPutils.getV4IP();
        // 百度地图申请的ak
        String ak = "F26NxSs4iAgQLzQGfxVT1Wy4XFwO3B63";
        JSONObject json = readJsonFromUrl("http://api.map.baidu.com/location/ip?ip="+ip+"&ak="+ak);
        JSONObject obj = (JSONObject) ((JSONObject) json.get("content")).get("address_detail");
        String city=obj.getString("city");
        int index=city.indexOf("市");
        city=city.substring(0,index);
        return city;
    }
}