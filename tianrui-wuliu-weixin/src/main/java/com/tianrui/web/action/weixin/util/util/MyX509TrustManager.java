package com.tianrui.web.action.weixin.util.util;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

/**
 * 
 * @类描述：
 * @创建人：lsj
 * @创建时间：2016年7月19日下午3:57:36
 *
 * @修改人：lsj
 * @修改时间：2016年7月19日下午3:57:36
 * @修改备注：
 *
 */
public class MyX509TrustManager implements X509TrustManager {

	// 检查客户端证书
	public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
	}

	// 检查服务器端证书
	public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
	}

	// 返回受信任的X509证书数组
	public X509Certificate[] getAcceptedIssuers() {
		return null;
	}
}
