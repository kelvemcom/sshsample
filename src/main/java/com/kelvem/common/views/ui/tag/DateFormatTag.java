/**============================================================
 * 版权： kelvem 版权所有 (c) 2012 - 2014
 * 文件：com.kelvem.common.views.ui.tag.AbstractTag.java
 * 所含类: AbstractTag
 * 修改记录：
 * 日期                           作者                            内容
 * =============================================================
 * 2014-02-22       kelvem       创建文件，实现基本功能
 * ============================================================*/

package com.kelvem.common.views.ui.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

public class DateFormatTag extends TagSupport {

	private static final long serialVersionUID = 2260617082823877709L;
	
	protected static final String ENTER_NEWLINE = "\r\n";
	protected static final String TAB = "          ";

	//@Autowired 
//	private SysDomainService sysDomainService;
	
	protected String name;
	protected String value;
	protected String format;
	

	public DateFormatTag() {
		super();
	}
	
	@Override
	public int doStartTag() throws JspException {
		
		JspWriter out;
		try {
			out = pageContext.getOut();
			out.print(createHtml());
		} catch (IOException ioe) {
			throw new JspException("I/O Error : " + ioe.getMessage(), ioe);
		}
		return Tag.SKIP_BODY;
	}

	/**
	 * 生成select控件
	 * 
	 * @return
	 */
	private String createHtml() {
		
		System.out.println("name = " + name);
		System.out.println("value = " + value);
		System.out.println("format = " + format);
		
		return value;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}
	
}
