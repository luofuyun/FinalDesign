<%@ page contentType="image/jpeg" import="java.awt.*,
java.awt.image.*,java.util.*,javax.imageio.*" pageEncoding="utf-8" %>
<%! 
private final Font mFont = new Font("Arial Black", Font.PLAIN, 15); // 设置字体 
private final int lineWidth = 2; // 干扰线的长度=1.414*lineWidth 
private final int width = 93; // 定义图形大小 
private final int height = 38; // 定义图形大小 
private final int count = 200; 
private final int MIN_FONT_SIZE = 35;// 验证码最小字体
Color getRandColor(int fc,int bc){//给定范围获得随机颜色
Random random = new Random();
if(fc>255) fc=255;
if(bc>255) bc=255;
int r=fc+random.nextInt(bc-fc);
int g=fc+random.nextInt(bc-fc);
int b=fc+random.nextInt(bc-fc);
return new Color(r,g,b);
}
%>
<%

char[] codeSequence = { 'A', 'a','B','b','C','c', 'D','d', 'E', 'e','F', 'f','G', 'g','H','h', 'I', 'i','J', 'j',  
        'K', 'k','L','l', 'M','m', 'N', 'n','O','o', 'P','p', 'Q','q', 'R','r', 'S', 's','T', 't','U','u', 'V','v','W','w',   
        'X','x', 'Y','y', 'Z', 'z','0', '1', '2', '3', '4', '5', '6', '7', '8', '9' }; 

//字体数组
Font[] RANDOM_FONT = new Font[] {
            new Font("nyala", Font.BOLD, MIN_FONT_SIZE),
            new Font("Arial", Font.BOLD, MIN_FONT_SIZE),
            new Font("Bell MT", Font.BOLD, MIN_FONT_SIZE),
            new Font("Credit valley", Font.BOLD, MIN_FONT_SIZE),
            new Font("Impact", Font.BOLD, MIN_FONT_SIZE)
    };
 

//设置页面不缓存 
response.setHeader("Pragma", "No-cache"); 
response.setHeader("Cache-Control", "no-cache"); 
response.setDateHeader("Expires", 0); 
response.setContentType("image/gif"); 

//在内存中创建图象 
final BufferedImage image = new BufferedImage(width, height, 
BufferedImage.TYPE_INT_RGB); 

//获取图形上下文 
final Graphics2D g = (Graphics2D) image.getGraphics(); 

//生成随机类 
final Random random = new Random(); 

//设定背景色 
g.setColor(getRandColor(200, 250)); // ---1 

g.fillRect(0, 0, width, height); 

//设定字体 
g.setFont(mFont); 

//画边框 
g.setColor(getRandColor(0, 20)); // ---2 
g.drawRect(0, 0, width - 1, height - 1); 

//随机产生干扰线，使图象中的认证码不易被其它程序探测到 
for (int i = 0; i < count; i++) { 

g.setColor(getRandColor(150, 200)); // ---3 

final int x = random.nextInt(width - lineWidth - 1) + 1; // 保证画在边框之内 
final int y = random.nextInt(height - lineWidth - 1) + 1; 
final int xl = random.nextInt(lineWidth); 
final int yl = random.nextInt(lineWidth); 
g.drawLine(x, y, x + xl, y + yl); 
} 

//取随机产生的认证码(4位数字) 
String sRand = ""; 

for (int i = 0; i < 4; i++) { 

final String rand = String.valueOf(codeSequence[random.nextInt(62)]); 
sRand += rand; 

// 产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同。   
int red = random.nextInt(255);   
int green = random.nextInt(255);   
int blue = random.nextInt(255);   

// 用随机产生的颜色将验证码绘制到图像中。   
g.setColor(new Color(red, green, blue));   
g.setFont(RANDOM_FONT[random.nextInt(5)]);
g.drawString(rand, (18 * i) + 6, 36); 
} 

//将认证码存入SESSION 
request.getSession().setAttribute("validateCode", sRand); 

//图象生效 
g.dispose(); 
final java.io.OutputStream os = response.getOutputStream(); 
//输出图象到页面 
ImageIO.write(image, "PNG", os); 
out.clear();
out = pageContext.pushBody();
%>
