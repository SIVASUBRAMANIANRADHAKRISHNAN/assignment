package com.auzmor.message;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.auzmor.protobuf.QueryProtoBuf.QueryString;

import java.util.Properties;
 
public class KafkaMessageProducer {
 
	private static KafkaMessageProducer instance;

	public static final String BOOTSTRAP_SERVER = "bootstrap.servers";
	public static final String BOOTSTRAP_SERVER_IP = "localhost:9092";
	public static final String KEY = "key.serializer";
	public static final String VALUE = "value.serializer";
	public static final String KEY_SEARIALIZER = "org.apache.kafka.common.serialization.StringSerializer";
	public static final String VALUE_SEARIALIZER = "org.apache.kafka.common.serialization.ByteArraySerializer";
	
	public static final String KAFKA_TOPIC = "topic_AUZMOR";
	public static final String MESSAGE_KEY = "ProtoBufMessage";
	
	/**
	 * Returns singleton producer instance.
	 */
	public static KafkaMessageProducer getInstance(){
		if (instance == null) {
			synchronized (KafkaMessageProducer.class) {
				if (instance == null) {
					instance = new KafkaMessageProducer();
				}
			}
		}
		return instance;
	}
	
	public synchronized void send(final byte[] queryParameterValue) {
 
        //properties for producer
        Properties props = new Properties();
        props.put(BOOTSTRAP_SERVER, BOOTSTRAP_SERVER_IP);
        props.put(KEY, KEY_SEARIALIZER);
        props.put(VALUE, VALUE_SEARIALIZER);
 
        //create producer
        Producer<String, byte[]> producer = new KafkaProducer<String, byte[]>(props);
 
        //send messages to topic_AUZMOR
        ProducerRecord<String, byte[]> producerRecord = new ProducerRecord<String, byte[]>(KAFKA_TOPIC, MESSAGE_KEY, queryParameterValue);
        producer.send(producerRecord);
 
        //close producer
        producer.close();
    }

 
}
