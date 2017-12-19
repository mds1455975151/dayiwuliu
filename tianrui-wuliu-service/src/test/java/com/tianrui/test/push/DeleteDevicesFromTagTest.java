package com.tianrui.test.push;

import java.util.List;

import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;
import com.baidu.yun.push.auth.PushKeyPair;
import com.baidu.yun.push.client.BaiduPushClient;
import com.baidu.yun.push.constants.BaiduPushConstants;
import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.baidu.yun.push.model.DeleteDevicesFromTagRequest;
import com.baidu.yun.push.model.DeleteDevicesFromTagResponse;
import com.baidu.yun.push.model.DeviceInfo;

public class DeleteDevicesFromTagTest {

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
            String[] channelIds = { "3721538990210239497" };
            DeleteDevicesFromTagRequest request = new DeleteDevicesFromTagRequest()
                    .addTagName("IOS_vender").addChannelIds(channelIds)
                    .addDeviceType(4);
            // 5. http request
            DeleteDevicesFromTagResponse response = pushClient
                    .deleteDevicesFromTag(request);
            // Http请求返回值解析
            if (null != response) {
                StringBuilder strBuilder = new StringBuilder();
                strBuilder.append("devicesInfoAfterDel:{");
                List<?> list = response.getDevicesInfoAfterDel();
                for (int i = 0; i < list.size(); i++) {
                    if (i != 0) {
                        strBuilder.append(",");
                    }
                    Object object = list.get(i);
                    if (object instanceof DeviceInfo) {
                        DeviceInfo deviceInfo = (DeviceInfo) object;
                        strBuilder.append("{channelId: "
                                + deviceInfo.getChannelId() + ", result: "
                                + deviceInfo.getResult() + "}");
                    }
                }
                strBuilder.append("}");
                System.out.println(strBuilder.toString());
            }
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
