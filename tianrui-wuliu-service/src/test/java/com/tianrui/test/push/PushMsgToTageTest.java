package com.tianrui.test.push;

import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;
import com.baidu.yun.push.auth.PushKeyPair;
import com.baidu.yun.push.client.BaiduPushClient;
import com.baidu.yun.push.constants.BaiduPushConstants;
import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.baidu.yun.push.model.PushMsgToTagRequest;
import com.baidu.yun.push.model.PushMsgToTagResponse;

public class PushMsgToTageTest {

	public static void main(String[] args) 
            throws PushClientException,PushServerException {
        // 1. get apiKey and secretKey from developer console
		String apiKey = "B0pttqMTh7f1zOR5YpfR2aXl";
        String secretKey = "xuhQKjLZg8wuBxzStYGFeGc08yeTG6VE";
        PushKeyPair pair = new PushKeyPair(apiKey, secretKey);

        // 2. build a BaidupushClient object to access released interfaces
        BaiduPushClient pushClient = new BaiduPushClient(pair,
                BaiduPushConstants.CHANNEL_REST_URL);

        // 3. register a YunLogHandler to get detail interacting information
        // in this request.
        pushClient.setChannelLogHandler(new YunLogHandler() {
            @Override
            public void onHandle(YunLogEvent event) {
                System.out.println(event.getMessage());
            }
        });

        try {
            // 4. specify request arguments
            // pushTagTpye = 1 for common tag pushing
            PushMsgToTagRequest request = new PushMsgToTagRequest()
                    .addTagName("IOS_vender")//分组名称
                    .addMsgExpires(new Integer(3600))//有效时间
                    .addMessageType(1)//0：透传消息   1：通知
//                  .addSendTime(System.currentTimeMillis() / 1000 + 70).
                    .addDeployStatus(1)//1：开发状态    2：生产状态
                    .addMessage("{\"title\":\"你好..\",\"description\":\"谢谢...\"}")
                    .addDeviceType(3);//3：Android，4：IOS
            // 5. http request
            PushMsgToTagResponse response = pushClient.pushMsgToTag(request);
            // Http请求返回值解析
            System.out.println("msgId: " + response.getMsgId() + ",sendTime: "
                    + response.getSendTime() + ",timerId: "
                    + response.getTimerId());
        } catch (PushClientException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                e.printStackTrace();
            }
        } catch (PushServerException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                System.out.println(String.format(
                        "requestId: %d, errorCode: %d, errorMsg: %s",
                        e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
            }
        }
    }   
}
