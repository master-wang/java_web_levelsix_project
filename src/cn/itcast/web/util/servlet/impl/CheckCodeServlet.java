package cn.itcast.web.util.servlet.impl;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * 验证码的制作
 */
@WebServlet("/checkCodeServlet")
public class CheckCodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //创建一个对象，在内存中图片（验证码图片）
        int width = 100;
        int height = 50;
        String sb;
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        //美化图片
            //填充背景色
            Graphics g = image.getGraphics();
            g.setColor(Color.PINK);
            g.fillRect(0,0,width,height);
            //画边框
            g.setColor(Color.BLUE);
            g.drawRect(0,0,width-1,height-1);
            //写验证码

            sb=getCharInfo();
            String checkCode = sb;
            g.drawString(sb,20,25);
            sb=getCharInfo();
        checkCode += sb;
            g.drawString(sb,40,25);
            sb=getCharInfo();
        checkCode += sb;
            g.drawString(sb,60,25);
            sb=getCharInfo();
        checkCode += sb;
            g.drawString(sb,80,25);


        System.out.println(checkCode);
            req.getSession().setAttribute("checkCode_session",checkCode);
            //划线干扰
            g.setColor(Color.GREEN);
            Random rand=new Random();

            for (int i = 0;i<10;i++){
                int x1 = rand.nextInt(width);
                int x2 = rand.nextInt(width);
                int y1 = rand.nextInt(height);
                int y2 = rand.nextInt(height);
                g.drawLine(x1,x2,y1,y2);
            }

        //讲图片输出的页面显示
        ImageIO.write(image,"jpg",resp.getOutputStream());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    public static String getCharInfo(){
        StringBuffer sb=new StringBuffer();
        //产生A-Z的ASCII码
        long result=Math.round(Math.random()*25+97);
        //将ASCII码转换成字符
        sb.append(String.valueOf((char)result));

        String a = sb+"";
        return  a;
    }
}
