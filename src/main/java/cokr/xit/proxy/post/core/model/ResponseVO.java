package cokr.xit.proxy.post.core.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter @ToString
public class ResponseVO<T> {

	private String errCode = null;

	private String errMsg  = null;

	private T resultInfo;
	
	@Builder(builderClassName = "okBuilder" ,builderMethodName = "okBuilder")
    ResponseVO(T resultInfo) {
		this.errCode = "OK";
		this.errMsg = "정상";
		this.resultInfo = resultInfo;
	}

	@Builder(builderClassName = "errBuilder" ,builderMethodName = "errBuilder")
    ResponseVO(String errCode, String errMsg) {
		this.errCode = errCode;
		this.errMsg = errMsg;
	}

	@Builder(builderClassName = "errRsltBuilder" ,builderMethodName = "errRsltBuilder")
    ResponseVO(String errCode, String errMsg, T resultInfo) {
		this.errCode = errCode;
		this.errMsg = errMsg;
		this.resultInfo = resultInfo;
	}
}
