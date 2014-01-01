/**============================================================
 * 版权：kelvem.com 版权所有 (c) 2012 - 2014
 * 文件：com.kelvem.sample.system.model.SeqModel.java
 * 所含类: SeqModel.java
 * 修改记录：
 * 日期                           作者                            内容
 * =============================================================
 * 2013-11-15	kelvem			创建文件，实现基本功能
 * ============================================================*/
package com.kelvem.sample.system.model;



/**
 * <p>SeqModel</p>
 *
 * <p>seqModel</p>
 *
 * <p>Copyright: 版权所有 (c) 2012 - 2014</p>
 * <p>Company: kelvem.com</p>
 * 
 * @ClassName SeqModel
 * @author kelvem
 * @version 1.0
 *
 */
public class SeqModel implements java.io.Serializable {

	private static final long serialVersionUID = -1L;
	
	// Fields
	private Integer seqId;



	/** default constructor */
	public SeqModel() {
	}

	
	public Integer getSeqId() {
		return this.seqId;
	}
	public void setSeqId(Integer seqId) {
		this.seqId = seqId;
	}
	
	

}
