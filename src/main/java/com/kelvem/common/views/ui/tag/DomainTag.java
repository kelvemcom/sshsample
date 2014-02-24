/**============================================================
 * 版权： kelvem 版权所有 (c) 2012 - 2014
 * 文件：com.kelvem.common.views.ui.tag.DomainTag.java
 * 所含类: DomainTag
 * 修改记录：
 * 日期                           作者                            内容
 * =============================================================
 * 2014-02-22       kelvem       创建文件，实现基本功能
 * ============================================================*/

package com.kelvem.common.views.ui.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.beans.factory.annotation.Autowired;

import com.kelvem.common.utils.WebUtil;
import com.kelvem.sample.system.model.SysDomainModel;
import com.kelvem.sample.system.service.SysDomainService;

/**
 * <p>DomainTag</p>
 *
 * <p>类用途详细说明</p>
 *
 * <p>Copyright: 版权所有 (c) 2012 - 2014</p>
 * <p>Company: kelvem</p>
 * 
 * @ClassName DomainTag
 * @author kelvem
 * @version 1.0
 */
public class DomainTag extends TagSupport {

	private static final long serialVersionUID = -252679477588526704L;
	
	protected static final String ENTER_NEWLINE = "\r\n";
	protected static final String TAB = "          ";
	
	@Autowired private SysDomainService sysDomainService = null;
	
	private String name;
	private String value;
	private String type;
	private String cssClass;
	private String cssStyle;
	private String domainType;
	private String emptyItem;
	private String allItem;

	
	@Override
	public int doStartTag() throws JspException {
		
		JspWriter out;
		try {
			out = pageContext.getOut();
			if (type != null && type.equals("label")) {
				out.print(createLabelHtml());
			} else {
				out.print(createSelectHtml());
			}
		} catch (IOException ioe) {
			throw new JspException("I/O Error : " + ioe.getMessage(), ioe);
		}
		return Tag.SKIP_BODY;
	}
	
	private String createLabelHtml() {
		
		String result = value;
		// domain item
		sysDomainService = WebUtil.getBean(SysDomainService.class);
		List<SysDomainModel> list = sysDomainService.querySysDomain(domainType);
		for (SysDomainModel domain : list) {
			if (value!=null && value.equalsIgnoreCase(domain.getKeyCode().toString())) {
				result = domain.getKeyName();
			}
		}
		
		return result;
	}

	private String createSelectHtml() {
		StringBuffer sb = new StringBuffer();
		sb.append(TAB);
		sb.append("<SELECT ");

		if(id!=null)
		{
			sb.append(" id='"+id+"'");
		}
		if(name!=null)
		{
			sb.append(" name='"+name+"'");
		}
		if(value!=null)
		{
			sb.append(" value='"+value+"'");
		}
		if(cssClass!=null)
		{
			sb.append(" class='"+cssClass+"'");
		}
		if(cssStyle!=null)
		{
			sb.append(" style='"+cssStyle+"'");
		}
		if(domainType!=null)
		{
			sb.append(" domainType='"+domainType+"'");
		}

		//
		sb.append(">");
		
		// empty item
		if (emptyItem != null) {
			String selected = "";
			if (value!=null && value.equalsIgnoreCase("-1")) {
				selected = " selected ";
			}
			sb.append(ENTER_NEWLINE + TAB + TAB);
			sb.append("<option value='-1'" + selected + ">" + emptyItem + "</option>");
		}
		
		// all item
		if (allItem != null) {
			String selected = "";
			if (value!=null && value.equalsIgnoreCase("0")) {
				selected = " selected ";
			}
			sb.append(ENTER_NEWLINE + TAB + TAB);
			sb.append("<option value='0'" + selected + ">" + allItem + "</option>");
		}
		
		// domain item
		sysDomainService = WebUtil.getBean(SysDomainService.class);
		List<SysDomainModel> list = sysDomainService.querySysDomain(domainType);
		for (SysDomainModel domain : list) {
			String selected = "";
			if (value!=null && value.equalsIgnoreCase(domain.getKeyCode().toString())) {
				selected = " selected ";
			}
			sb.append(ENTER_NEWLINE + TAB + TAB);
			sb.append("<option value='" + domain.getKeyCode() + "'" + selected + ">" + domain.getKeyName() + "</option>");
		}
		
		sb.append(ENTER_NEWLINE + TAB + "</SELECT>");
		
		return sb.toString();
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCssClass() {
		return cssClass;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

	public String getCssStyle() {
		return cssStyle;
	}

	public void setCssStyle(String cssStyle) {
		this.cssStyle = cssStyle;
	}
	
	public void setDomainType(String domainType) {
		if(domainType!=null)
		{
			this.domainType = domainType.toLowerCase();
		}
	}

	public String getDomainType() {
		return domainType;
	}

	public String getEmptyItem() {
		return emptyItem;
	}

	public void setEmptyItem(String emptyItem) {
		this.emptyItem = emptyItem;
	}

	public String getAllItem() {
		return allItem;
	}

	public void setAllItem(String allItem) {
		this.allItem = allItem;
	}
}
