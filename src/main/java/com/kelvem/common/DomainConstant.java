package com.kelvem.common;

public class DomainConstant {

	public static final int FLAG_DEL_TRUE  = 1; 
	public static final int FLAG_DEL_FALSE = 0;  
	
	public enum STATUS_CODE {
		
		ENABLE(10), DISENABLE(90);
		
		public Integer val = 10;
		
		private STATUS_CODE(Integer val){
			this.val = val;
		}
		public String toString(){
			return super.toString()+"("+val+")";  
		}
		public Integer getValue() {
			return val;
		}
	};
	
//	public enum DEL_FLAG {
//		
//		DEL(10), NOT_DEL(0);
//		
//		public Integer val = 0;
//		
//		private DEL_FLAG(Integer val){
//			this.val = val;
//		}
//		public String toString(){  
//			return super.toString()+"("+val+")";  
//		}
//		public Integer getValue() {
//			return val;
//		}
//	};

}
