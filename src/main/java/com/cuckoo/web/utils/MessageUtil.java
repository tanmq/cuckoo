package com.cuckoo.web.utils;

import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.CloudTopic;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.common.ServiceException;
import com.aliyun.mns.model.BatchSmsAttributes;
import com.aliyun.mns.model.MessageAttributes;
import com.aliyun.mns.model.RawTopicMessage;
import com.aliyun.mns.model.TopicMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by tanmq on 2017/4/18.
 */
public class MessageUtil {

    private static Logger logger = LoggerFactory.getLogger(MessageUtil.class);

    private final static String ACCESS_ID   = Config.get("oss.accessKey");
    private final static String ACCESS_KEY  = Config.get("oss.accessSecret");
    private final static String MSN_ENDPOINT = Config.get("msn.endpoint");
    private final static String MSN_TOPIC    = Config.get("msn.topic");
    private final static String MSN_SIGN_NAME= Config.get("msn.signName");
    private final static String MSN_TMP_CODE = Config.get("msn.tmp.code");

    private static CloudTopic topic;


    static {
        CloudAccount cloudClient = new CloudAccount(ACCESS_ID, ACCESS_KEY, MSN_ENDPOINT);
        MNSClient mnsClient      = cloudClient.getMNSClient();
        topic                    = mnsClient.getTopicRef("sms.topic-cn-shenzhen");

    }

    public static void sendCode(String phone, long code) {
        RawTopicMessage msg = new RawTopicMessage();
        msg.setMessageBody("短信验证码");

        MessageAttributes messageAttributes = new MessageAttributes();
        BatchSmsAttributes batchSmsAttributes = new BatchSmsAttributes();
        batchSmsAttributes.setFreeSignName(MSN_SIGN_NAME);
        batchSmsAttributes.setTemplateCode(MSN_TMP_CODE);

        BatchSmsAttributes.SmsReceiverParams smsReceiverParams = new BatchSmsAttributes.SmsReceiverParams();
        smsReceiverParams.setParam("code", String.valueOf(code));

        batchSmsAttributes.addSmsReceiver(phone, smsReceiverParams);

        messageAttributes.setBatchSmsAttributes(batchSmsAttributes);
        try {
            /**
             * Step 4. 发布SMS消息
             */
            TopicMessage ret = topic.publishMessage(msg, messageAttributes);
            logger.info("MessageId: " + ret.getMessageId());
            logger.info("MessageMD5: " + ret.getMessageBodyMD5());
        } catch (ServiceException se) {
            logger.error(se.getErrorCode() + se.getRequestId());
            logger.error("Error", se);
        } catch (Exception e) {
            logger.error("Error to send message.", e);
        }

    }


    public static void main(String[] args) {
        sendCode("15902098344", 434343);
    }



}
