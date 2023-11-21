package com.deyi.daxie.cloud.vehicle.query.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Description: 监听kafka类
 *
 * @author Huang ShuYing
 * @date 2022/9/6
 */
@Slf4j
//@Component
public class KafkaProduct {

    public static KafkaProduct kafkaProduct;
    @Autowired
    private KafkaTemplate<Object, Object> template;

    @PostConstruct
    public void init(){
        KafkaProduct.kafkaProduct=this;
        KafkaProduct.kafkaProduct.template=this.template;
    }

    public  void send(String topic,String data){
        template.send(topic,data);
    }

}
