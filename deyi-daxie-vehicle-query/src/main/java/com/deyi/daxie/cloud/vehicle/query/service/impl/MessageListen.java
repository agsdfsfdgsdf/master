package com.deyi.daxie.cloud.vehicle.query.service.impl;

import com.deyi.daxie.cloud.vehicle.query.kafka.KafkaProduct;
import com.deyi.daxie.cloud.vehicle.query.kafka.KafkaTopic;
import com.deyi.daxie.cloud.vehicle.query.util.Constant;
import com.deyi.daxie.cloud.vehicle.query.util.IsJsonObject;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * Description:
 * @author Huang ShuYing
 * @date 2022/9/23
 */
public class MessageListen {

    @Autowired
    private KafkaTemplate<Object, Object> template;

    /**
     * Description: 消息分类
     * @date 2022/9/26
     * @author Huang ShuYing
     */
    public  void messageListen(String message){
        boolean ifJson= IsJsonObject.isJsonObject(message);
        if (ifJson){
            JSONObject jsonObject=JSONObject.parseObject(message);
            if (jsonObject.containsValue(Constant.TASK_CODE)){
                messageByTask(jsonObject);
            }
        }
    }

    public  void messageByTask(JSONObject jsonObject){
        KafkaProduct.kafkaProduct.send(KafkaTopic.TASK,jsonObject.toString());
    }

}
