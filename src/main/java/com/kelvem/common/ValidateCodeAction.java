package com.kelvem.common;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import com.kelvem.common.utils.SessionUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ValidateCodeAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6950908478971552308L;

	// 生成随机类
	private static Random rnd = new Random(new Date().getTime());
	
	private ByteArrayInputStream inputStream;
	private Object d = null;
	
	private int NumSize = 4;

	public String execute() throws Exception {

		String rand = getRandString(this.NumSize);
		
		BufferedImage image = getRandImage(rand);
		
		// 将认证码存入SESSION
		SessionUtils.setValidateCode(rand);

		ByteArrayOutputStream output = new ByteArrayOutputStream();
		ImageOutputStream imageOut = ImageIO.createImageOutputStream(output);
		ImageIO.write(image, "JPEG", imageOut);
		imageOut.close();
		ByteArrayInputStream input = new ByteArrayInputStream(output.toByteArray());
		this.setInputStream(input);
		
		return SUCCESS;
	}
	
	private BufferedImage getRandImage(String rand){
		// 在内存中创建图象
		int width = 15 * NumSize, height = 20;

		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		// 获取图形上下文
		Graphics g = image.getGraphics();

		// 设定背景色
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);

		// 设定字体
		g.setFont(new Font("Times New Roman", Font.PLAIN, 18));

		// 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
		g.setColor(getRandColor(160, 200));
		for (int i = 0; i < 155; i++) {
			int x = rnd.nextInt(width);
			int y = rnd.nextInt(height);
			int xl = rnd.nextInt(12);
			int yl = rnd.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}

		// 取随机产生的认证码(N位数字)
		for (int i = 0; i < rand.length(); i++) {
			String ch = String.valueOf(rand.charAt(i));
			
			// 将认证码显示到图象中
			g.setColor(new Color(20 + rnd.nextInt(110), 20 + rnd.nextInt(110), 20 + rnd.nextInt(110)));
			
			// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
			g.drawString(ch, 13 * i + 6, 16);
		}

		// 图象生效
		g.dispose();
		
		return image;
	}
	
	private String getRandString(int NumSize){

		// 取随机产生的认证码(N位数字)
		String sRand = "";
		for (int i = 0; i < NumSize; i++) {
			String rand = String.valueOf(rnd.nextInt(10));
			sRand += rand;
		}
		
		return sRand;
	}
	
	/*
	 * 给定范围获得随机颜色
	 */
	private Color getRandColor(int fc, int bc) {
		
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + rnd.nextInt(bc - fc);
		int g = fc + rnd.nextInt(bc - fc);
		int b = fc + rnd.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	public void setInputStream(ByteArrayInputStream inputStream) {
		this.inputStream = inputStream;
	}

	public ByteArrayInputStream getInputStream() {
		return inputStream;
	}

	public Object getD() {
		return d;
	}

	public void setD(Object d) {
		this.d = d;
	}

}
