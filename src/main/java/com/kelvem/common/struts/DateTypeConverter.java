package com.kelvem.common.struts;

import java.util.Date;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

import com.kelvem.common.utils.DateUtils;

public class DateTypeConverter extends StrutsTypeConverter {

	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		if (values == null || values.length <= 0) {
			return null;
		}
		
		try {
			String strDate = values[0];
			Date date = DateUtils.parseDate(strDate);
			return date;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public String convertToString(Map context, Object o) {
		if (o == null) {
			return null;
		}
		if (o instanceof Date) {
			return DateUtils.getDateString((Date)o);
		} else {
			return o.toString();
		}
	}

}
