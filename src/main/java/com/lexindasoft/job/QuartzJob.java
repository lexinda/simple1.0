package com.lexindasoft.job;

import org.apache.log4j.Logger;

import com.lexindasoft.model.weixin.AccessToken;
import com.lexindasoft.utils.RuntimeConfig;
import com.lexindasoft.utils.weixin.WeixinUtil;

public class QuartzJob
{
	private static Logger logger = Logger.getLogger(QuartzJob.class);
	// 第三方用户唯一凭证
	public static String appid = RuntimeConfig.getInstance().getAppId();
	// 第三方用户唯一凭证密钥
	public static String appsecret = RuntimeConfig.getInstance().getAppSecret();
	public static AccessToken accessToken = null;
	
    public void work()
    {
    	accessToken = WeixinUtil.getAccessToken(appid, appsecret);
		if (null != accessToken) {
			logger.info("获取access_token成功，有效时长{}秒 token:{}"+ accessToken.getExpiresIn()+ accessToken.getToken());
			RuntimeConfig.getInstance().setAssessToken(accessToken.getToken());
		}
    }
}