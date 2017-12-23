package com.tianrui.service.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;
import com.baidu.yun.push.auth.PushKeyPair;
import com.baidu.yun.push.client.BaiduPushClient;
import com.baidu.yun.push.constants.BaiduPushConstants;
import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.baidu.yun.push.model.AddDevicesToTagRequest;
import com.baidu.yun.push.model.AddDevicesToTagResponse;
import com.baidu.yun.push.model.CreateTagRequest;
import com.baidu.yun.push.model.CreateTagResponse;
import com.baidu.yun.push.model.DeleteDevicesFromTagRequest;
import com.baidu.yun.push.model.DeleteDevicesFromTagResponse;
import com.baidu.yun.push.model.DeleteTagRequest;
import com.baidu.yun.push.model.DeleteTagResponse;
import com.baidu.yun.push.model.DeviceInfo;
import com.baidu.yun.push.model.PushMsgToTagRequest;
import com.baidu.yun.push.model.PushMsgToTagResponse;
import com.baidu.yun.push.model.QueryDeviceNumInTagRequest;
import com.baidu.yun.push.model.QueryDeviceNumInTagResponse;

public class BaiduPushUtils {

	static String android_apiKey = "B0pttqMTh7f1zOR5YpfR2aXl";
	static String android_secretKey = "xuhQKjLZg8wuBxzStYGFeGc08yeTG6VE";
	
	static String ios_apiKey = "lTootP7bdqUxMtzmDmB6iOrG";
	static String ios_secretKey = "wlNwiNLploUCfpiy9D7nOX9oGMUEtIYh";
	
	public static String pushMsgToTage(String tageName,Integer type,String msg,String code ) throws PushServerException, PushClientException{
		String apiKey = "";
		String secretKey = "";
		if(type == 3){
			apiKey = android_apiKey;
			secretKey = android_secretKey;
		}else if(type == 4){
			apiKey = ios_apiKey;
			secretKey = ios_secretKey;
		}
		if(StringUtils.isNotBlank(tageName)){
			if(StringUtils.isNotBlank(apiKey)&&StringUtils.isNotBlank(secretKey)){
				 PushKeyPair pair = new PushKeyPair(apiKey, secretKey);

		        BaiduPushClient pushClient = new BaiduPushClient(pair,
		                BaiduPushConstants.CHANNEL_REST_URL);

		        pushClient.setChannelLogHandler(new YunLogHandler() {
		            @Override
		            public void onHandle(YunLogEvent event) {
		                System.out.println(event.getMessage());
		            }
		        });

		        try {
		        	JSONObject jsonAPS = new JSONObject();
					jsonAPS.put("alert", msg);
					JSONObject notification = new JSONObject();
					notification.put("title", "大易物流");
					notification.put("code", code);
					notification.put("description",msg);
					notification.put("aps", jsonAPS);
		        	
		            PushMsgToTagRequest request = new PushMsgToTagRequest()
		                    .addTagName(tageName)//分组名称
		                    .addMsgExpires(new Integer(3600))//有效时间
		                    .addMessageType(1)//0：透传消息   1：通知
		                    .addDeployStatus(1)//1：开发状态    2：生产状态
		                    .addMessage(notification.toString())
		                    .addDeviceType(type);//3：Android，4：IOS
		          
		            PushMsgToTagResponse response = pushClient.pushMsgToTag(request);
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
		return "";
	}
	
	/**
	 * 添加分组
	 * @param tageName -分组名称
	 * @param type - 3：Android，4：IOS
	 * @return
	 * @throws PushClientException 
	 * @throws PushServerException 
	 */
	public static String createTage(String tageName,Integer type ) throws PushClientException, PushServerException{
		String apiKey = "";
		String secretKey = "";
		if(type == 3){
			apiKey = android_apiKey;
			secretKey = android_secretKey;
		}else if(type == 4){
			apiKey = ios_apiKey;
			secretKey = ios_secretKey;
		}
		if(StringUtils.isNotBlank(tageName)){
			if(StringUtils.isNotBlank(apiKey)&&StringUtils.isNotBlank(secretKey)){
				PushKeyPair pair = new PushKeyPair(apiKey, secretKey);
				
				BaiduPushClient pushClient = new BaiduPushClient(pair,
						BaiduPushConstants.CHANNEL_REST_URL);
				
				pushClient.setChannelLogHandler(new YunLogHandler() {
					@Override
					public void onHandle(YunLogEvent event) {
						System.out.println(event.getMessage());
					}
				});
				
				try {
					CreateTagRequest request = new CreateTagRequest().addTagName(
							tageName).addDeviceType(type);//3：Android，4：IOS
					CreateTagResponse response = pushClient.createTag(request);
					System.out.println(String.format("tagName: %s, result: %d",
							response.getTagName(), response.getResult()));
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
		return "";
	}
	
	
	/**
	 * 删除分组
	 * @param tageName -分组名称
	 * @param type - 3：Android，4：IOS
	 * @return
	 * @throws PushClientException 
	 * @throws PushServerException 
	 */
	public static String deleteTage(String tageName,Integer type ) throws PushClientException, PushServerException{
		String apiKey = "";
		String secretKey = "";
		if(type == 3){
			apiKey = android_apiKey;
			secretKey = android_secretKey;
		}else if(type == 4){
			apiKey = ios_apiKey;
			secretKey = ios_secretKey;
		}
		if(StringUtils.isNotBlank(tageName)){
			if(StringUtils.isNotBlank(apiKey)&&StringUtils.isNotBlank(secretKey)){
				 PushKeyPair pair = new PushKeyPair(apiKey, secretKey);

		        BaiduPushClient pushClient = new BaiduPushClient(pair,
		                BaiduPushConstants.CHANNEL_REST_URL);

		        pushClient.setChannelLogHandler(new YunLogHandler() {
		            @Override
		            public void onHandle(YunLogEvent event) {
		                System.out.println(event.getMessage());
		            }
		        });

		        try {
		            DeleteTagRequest request = new DeleteTagRequest().addTagName(
		            		tageName).addDeviceType(new Integer(type));
		            DeleteTagResponse response = pushClient.deleteTag(request);
		            System.out.println(String.format("tagName: %s, result: %d",
		                    response.getTagName(), response.getResult()));
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
		return "";
	}
	
	/**
	 * 添加用户至组
	 * @param tageName -分组名称
	 * @param type - 3：Android，4：IOS
	 * channelIds 最多 10个
	 * @return
	 * @throws PushClientException 
	 * @throws PushServerException 
	 */
	public static String addUserToTage(String tageName,Integer type ,String[] channelIds) throws PushClientException, PushServerException{
		String apiKey = "";
		String secretKey = "";
		if(type == 3){
			apiKey = android_apiKey;
			secretKey = android_secretKey;
		}else if(type == 4){
			apiKey = ios_apiKey;
			secretKey = ios_secretKey;
		}
		if(StringUtils.isNotBlank(tageName)){
			if(StringUtils.isNotBlank(apiKey)&&StringUtils.isNotBlank(secretKey)){
				PushKeyPair pair = new PushKeyPair(apiKey, secretKey);
				BaiduPushClient pushClient = new BaiduPushClient(pair,
				        BaiduPushConstants.CHANNEL_REST_URL);
				pushClient.setChannelLogHandler(new YunLogHandler() {
				    @Override
				    public void onHandle(YunLogEvent event) {
				        System.out.println(event.getMessage());
				    }
				});
				
				try {
//				    String[] channelIds = {"3721538990210239497"};
				    AddDevicesToTagRequest request = new AddDevicesToTagRequest()
				            .addTagName(tageName).addChannelIds(channelIds)
				            .addDeviceType(type);
				    AddDevicesToTagResponse response = pushClient
				            .addDevicesToTag(request);
				    if (null != response) {
				        StringBuilder strBuilder = new StringBuilder();
				        strBuilder.append("devicesInTag：{");
				        List<?> devicesInfo = response.getDevicesInfoAfterAdded();
				        for (int i = 0; i < devicesInfo.size(); i++) {
				            Object object = devicesInfo.get(i);
				            if (i != 0) {
				                strBuilder.append(",");
				            }
				            if (object instanceof DeviceInfo) {
				                DeviceInfo deviceInfo = (DeviceInfo) object;
				                strBuilder.append("{channelId:"
				                        + deviceInfo.getChannelId() + ",result:"
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
		return "";
	}
	
	/**
	 * 删除用户组
	 * @param tageName -分组名称
	 * @param type - 3：Android，4：IOS
	 * channelIds 最多 10个
	 * @return
	 * @throws PushClientException 
	 * @throws PushServerException 
	 */
	public static String deleteUserToTage(String tageName,Integer type ,String[] channelIds) throws PushClientException, PushServerException{
		String apiKey = "";
		String secretKey = "";
		if(type == 3){
			apiKey = android_apiKey;
			secretKey = android_secretKey;
		}else if(type == 4){
			apiKey = ios_apiKey;
			secretKey = ios_secretKey;
		}
		if(StringUtils.isNotBlank(tageName)){
			if(StringUtils.isNotBlank(apiKey)&&StringUtils.isNotBlank(secretKey)){
				 PushKeyPair pair = new PushKeyPair(apiKey, secretKey);

		        BaiduPushClient pushClient = new BaiduPushClient(pair,
		                BaiduPushConstants.CHANNEL_REST_URL);

		        pushClient.setChannelLogHandler(new YunLogHandler() {
		            @Override
		            public void onHandle(YunLogEvent event) {
		                System.out.println(event.getMessage());
		            }
		        });

		        try {
//		            String[] channelIds = { "3721538990210239497" };
		            DeleteDevicesFromTagRequest request = new DeleteDevicesFromTagRequest()
		                    .addTagName(tageName).addChannelIds(channelIds)
		                    .addDeviceType(type);
		            DeleteDevicesFromTagResponse response = pushClient
		                    .deleteDevicesFromTag(request);
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
		return "";
	}
	
	public static int QueryDeviceNumInTag(String tageName,Integer type) throws PushClientException, PushServerException{
		int num = 0;
		String apiKey = "";
		String secretKey = "";
		if(type == 3){
			apiKey = android_apiKey;
			secretKey = android_secretKey;
		}else if(type == 4){
			apiKey = ios_apiKey;
			secretKey = ios_secretKey;
		}
		if(StringUtils.isNotBlank(tageName)){
			if(StringUtils.isNotBlank(apiKey)&&StringUtils.isNotBlank(secretKey)){
				PushKeyPair pair = new PushKeyPair(apiKey, secretKey);
				
				BaiduPushClient pushClient = new BaiduPushClient(pair,
						BaiduPushConstants.CHANNEL_REST_URL);
				
				pushClient.setChannelLogHandler(new YunLogHandler() {
					@Override
					public void onHandle(YunLogEvent event) {
						System.out.println(event.getMessage());
					}
				});
				
				try {
					QueryDeviceNumInTagRequest request = new QueryDeviceNumInTagRequest()
							.addTagName(tageName).addDeviceType(type);
					QueryDeviceNumInTagResponse response = pushClient
							.queryDeviceNumInTag(request);
					if (null != response) {
						System.out.println(String.format("deviceNum: %d",
								response.getDeviceNum()));
						num = response.getDeviceNum();
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
		
		
		return num;
	} 
	
	public static void main(String[] args) throws PushClientException, PushServerException {

		List<String> list = new ArrayList<String>();
		list.add("5345561099824707259");
		
		//		createTage("vender_push", 3);
//		createTage("vender_push", 4);
		String[] channelIds = list.toArray(new String[list.size()]);
//		String[] android = {"4570357197846312062"};
//		addUserToTage("vender_push", 3, android);
//		addUserToTage("vender_push", 4, channelIds);
		
//		pushMsgToTage("vender_push_tage", 3, "这是一个测试消息", "123");
//		pushMsgToTage("vender_push_tage", 4, "这是一个测试消息", "123");
		
		System.out.println("wqe="+QueryDeviceNumInTag("vender_push_tage", 3));
//		QueryDeviceNumInTag("vender_push", 4);
//		deleteTage("vender_push", 3);
//		deleteTage("vender_push", 4);
		
	}
}
