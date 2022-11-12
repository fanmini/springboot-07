package com.fql.util;


import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Base64Utils {
    /**生成base64位的图片*/
    public static String drawImage(HttpServletResponse resp, String number) throws IOException {
        BufferedImage image = new BufferedImage(125, 50, BufferedImage.TYPE_INT_RGB);
        //1.获取图片画笔
        Graphics g = image.getGraphics();
        Random r = new Random();//随机数

//        r.next(255)，随机数在0到255之间 而且必须是整数 0-1之间的小数
        //2.设置画笔颜色
//        设置背景颜色，RGB 三个参数
        g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
        //3. .绘制矩形的背景
        g.fillRect(0, 0, 125, 50);

        g.setColor(new Color(0, 0, 0));
        g.setFont(new Font("宋体", Font.BOLD, 33));
        //5.设置颜色字体后，绘制字符串
        g.drawString(number, 18, 35);
        //6.绘制18条干扰线
        for (int i = 0; i < 18; i++) {
            g.setColor(new
                    Color(r.nextInt(255), r.nextInt(255), r.nextInt(255), r.nextInt(255)));

            g.drawLine(r.nextInt(100),
                    r.nextInt(30),
                    r.nextInt(100),
                    r.nextInt(30));
        }

        resp.setContentType("image/jpeg");
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        /**把图片写入到ByteArrayOutputStream字节数组输出流*/
        ImageIO.write(image, "png", stream);

        /**流对象转base64*/
        String base64 = "data:image/png;base64," +  Base64.encode(stream.toByteArray());
        stream.close();

        //String uuid = UUID.randomUUID().toString().replaceAll("-","");

        /**把用户验证的UUID以ResponseHead的形式返回给客户端
         * 在响应头中添加自定义的响应头：
         *  resp.setHeader("Access-Control-Expose-Headers", "codeKey");
         *  否则在跨域的情况下，客户端的ajax无法获取该自定义的响应头
         * */
        //resp.setHeader("Access-Control-Expose-Headers", "codeKey");
        //resp.setHeader("codeKey", codeKey);
        return base64;
    }

    /**
     * 产生指定位数的随机数
     * */
    public static String getNumber(int size) {
        String str = "abcdefghijkmnpqrstuvwsxz1234567890";//我把I和O都去掉，因为和1,0不好识别
        StringBuilder number = new StringBuilder();
        Random r = new Random();
        for (int i = 0; i < size; i++) {
            int leng = str.length();//得到字符串的长度
//            int suiji = r.nextInt();//随机数0-1之间，
            int le = r.nextInt(leng);//得到str的长度之间的随机数
            char ss = str.charAt(le);//根据charAt的下标的值，得到str对应的字符
//            需要对字符进行拼接
            number.append(ss);
//            str.charAt 拿到下标为0-str长度之间的下标，下标从0开始
//            number += str.charAt(r.nextInt(str.length()));
        }
        return number.toString();
    }

    /**字符串转时间*/
    public static Date stringToDate(String dateString){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        //String dateString = "20071128175545";
        Date date = null;
        try {
            date = df.parse(dateString);
            //System.out.println(date.toLocaleString());
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return date;
    }

    /**字符串转时间*/
    public static String dateToString(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = sdf.format(date);
        return dateString;
    }

}
