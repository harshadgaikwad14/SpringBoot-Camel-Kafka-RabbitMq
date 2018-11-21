
package com.example.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


@Component
@Scope("singleton")
public final class AppProperties {
	
	
	@Value("${event.max.retry.duration}")
	private long eventDuration;
	
	
	@Value("${event.delay.requeue}")
	private long eventRequeueDelay;
	
	/**  Default connection timout in ms. */
	@Value("${connection.timeout}")
	private long defaultTimeout;
	
	/**  Camel messaging log level. */
	@Value("${CamelMessageLoggingLevel}")
	private String camelLogLevel;
	
	/**  Module name. */
	@Value("${Module.name}")
	private String module;
	
	/**  Threadpool size. */
	@Value("${event.consumer.thread.pool}")
	private String threadPool;
	
	/**  Camel component for channel state handler. */
	@Value("${Channel.StateHandler.CamelComponent}")
	private String rmqCamelComponent;
	
	/**  Camel component for backend state handler. */
	@Value("${Backend.StateHandler.CamelComponent}")
	private String kafkaCamelComponent;
	
	/**  RMQ user. */
	@Value("${Digital.RabbitMQ.User}")
	private String rmqUser;
	
	/**  RMQ password. */
	@Value("${Digital.RabbitMQ.Password}")
	private String rmqPassword;
	
	/**  Camel RMQ host. */
	@Value("${Digital.RabbitMQ.Host}")
	private String camelRmqHost;
	
	/**  Camel RMQ port. */
	@Value("${Digital.RabbitMQ.Port}")
	private String camelRmqPort;
	
	/**  Camel RMQ Vhost. */
	@Value("${Digital.RabbitMQ.VHost}")
	private String camelRmqVhost;
	
	/**  Rabbitmq exchange. */
	@Value("${ingestion.sweeps.rabbitmq.exchange}")
	private String rmqExchange;
	
	/**  Rabbitmq dead letter exchange. */
	@Value("${ingestion.sweeps.rabbitmq.dl.exchange}")
	private String rmDlExchange;
	
	/**  Rabbitmq dead letter queue exchange. */
	@Value("${ingestion.sweeps.rabbitmq.dl.queue}")
	private String rmDlq;
	
	/**  Rqbbitmq consumer queue. */
	@Value("${ingestion.sweeps.rabbitmq.consumer.queue}")
	private String rmqConsumerQueue;
	
	/**  Rmq event max re delivery. */
	@Value("${event.max.redelivery}")
	private String eventMaxRedelivery;
	
	/**  Event region. */
	@Value("${ingestion.sweeps.event.region}")
	private String region;
	
	/**  Event country. */
	@Value("${ingestion.sweeps.event.country}")
	private String country;
		
	/**  Bootstrap server configurations. */
	@Value("${kafka.config.bootstrapservers}")
	private String bootstrapServers;
	
	/**  Auto Offset Reset Config. */
	@Value("${kafka.config.autooffsetreset}")
	private String autoOffsetReset;
	
	/**  Consumer group id. */
	@Value("${kafka.consumer.group-id.config}")
	private String groupId;
	
	/**  Output topic from for reading data. */
	@Value("${kafka.output.topic}")
	private String outputTopic;
	
	/**  Retry kafka topic for reading re-deliverable messages. */
	@Value("${kafka.output.retry.topic}")
	private String kafkaRetryTopic;
	
	/**  Retry kafka message after mentioned time interval. */
	@Value("${event.retry.time.ms}")
	private String kafkaEventRetryTime; 
	
	/**  Dead letter queue topic. */
	@Value("${kafka.dlq.topic}")
	private String digitalDlqTopic;

	/**  Sweeps Elastic index name. */
	@Value("${elasticsearch.index.name}")
	private String esIndexName;
	
	/**  Commons Elastic index name. */
	@Value("${elasticsearch.common.index.name}")
	private String esCommonIndexName;
	
	/**  Host of elasticsearch. */
	@Value("${ES.DB.Protocol}")
	private String elasticProtocol;
	
	/**  Host of elasticsearch. */
	@Value("${ES.DB.Host}")
	private String elasticHost;
	
	/**  Elasticsearch port. */
	@Value("${ES.DB.Port}")
	private String elasticPort;
	
	/**  Elasticsearch username. */
	@Value("${ES.DB.User}")
	private String elasticUser;
	
	/**  Elasticsearch password. */
	@Value("${ES.DB.Password}")
	private String elasticPassword;
	
	/**  Elasticsearch Type for SweepStructure. */
	@Value("${es.type.swps}")
	private String elasticTypeSwpStruct;
	
	/**  Elasticsearch Type for SweepInstruction. */
	@Value("${es.type.swpinstr}")
	private String elasticTypeSwpInstr;
	
	/**  Elasticsearch Type for SweepProduct. */
	@Value("${es.type.swpprod}")
	private String elasticTypeSwpProd;
	
	/**  Elasticsearch Type for SweepExecution. */
	@Value("${es.type.exec}")
	private String elasticTypeSwpExec;

	/**  CamelComponent Options. */
	@Value("${CamelComponent.Options}")
	private String camelCompOption;
	
	/**  Concurrent RMQConsumer. */
	@Value("${event.concurrentConsumers}")
	private String concurrRMQConsumer;
	
			
	/**
	 * Instantiates a new AppProperties object.
	 */
	private AppProperties() {
		// intentionally defined "private" to avoid others instantiating this class
	}
	
	
	
	/**
	 * Gets the event duration.
	 *
	 * @return the event duration
	 */
	public long getEventDuration() {
		return eventDuration;
	}



	/**
	 * Gets the kafka retry topic.
	 *
	 * @return the kafka retry topic
	 */
	public String getKafkaRetryTopic() {
		return kafkaRetryTopic;
	}



	/**
	 * Gets the camel comp option.
	 *
	 * @return the camel comp option
	 */
	public String getCamelCompOption() {
		return camelCompOption;
	}



	/**
	 * Gets the concurr RMQ consumer.
	 *
	 * @return the concurr RMQ consumer
	 */
	public String getConcurrRMQConsumer() {
		return concurrRMQConsumer;
	}



	/**
	 * Gets the default timeout.
	 *
	 * @return the defaultTimeout
	 */
	public long getDefaultTimeout() {
		return defaultTimeout;
	}

	/**
	 * Gets the camel log level.
	 *
	 * @return the camelLogLevel
	 */
	public String getCamelLogLevel() {
		return camelLogLevel;
	}

	/**
	 * Gets the module.
	 *
	 * @return the module
	 */
	public String getModule() {
		return module;
	}


	/**
	 * Gets the thread pool.
	 *
	 * @return the threadPool
	 */
	public String getThreadPool() {
		return threadPool;
	}

	/**
	 * Gets the rmq camel component.
	 *
	 * @return the rmqCamelComponent
	 */
	public String getRmqCamelComponent() {
		return rmqCamelComponent;
	}

	/**
	 * Gets the kafka camel component.
	 *
	 * @return the kafkaCamelComponent
	 */
	public String getKafkaCamelComponent() {
		return kafkaCamelComponent;
	}

	/**
	 * Gets the rmq user.
	 *
	 * @return the rmqUser
	 */
	public String getRmqUser() {
		return rmqUser;
	}


	/**
	 * Gets the rmq password.
	 *
	 * @return the rmqPassword
	 */
	public String getRmqPassword() {
		return rmqPassword;
	}


	/**
	 * Gets the camel rmq host.
	 *
	 * @return the camelRmqHost
	 */
	public String getCamelRmqHost() {
		return camelRmqHost;
	}


	/**
	 * Gets the camel rmq port.
	 *
	 * @return the camelRmqPort
	 */
	public String getCamelRmqPort() {
		return camelRmqPort;
	}


	/**
	 * Gets the camel rmq vhost.
	 *
	 * @return the camelRmqVhost
	 */
	public String getCamelRmqVhost() {
		return camelRmqVhost;
	}


	/**
	 * Gets the rmq exchange.
	 *
	 * @return the rmqExchange
	 */
	public String getRmqExchange() {
		return rmqExchange;
	}


	/**
	 * Gets the rm dl exchange.
	 *
	 * @return the rmDlExchange
	 */
	public String getRmDlExchange() {
		return rmDlExchange;
	}


	/**
	 * Gets the rm dlq.
	 *
	 * @return the rmDlq
	 */
	public String getRmDlq() {
		return rmDlq;
	}


	/**
	 * Gets the rmq consumer queue.
	 *
	 * @return the rmqConsumerQueue
	 */
	public String getRmqConsumerQueue() {
		return rmqConsumerQueue;
	}

	/**
	 * Gets the event max redelivery.
	 *
	 * @return the eventMaxRedelivery
	 */
	public String getEventMaxRedelivery() {
		return eventMaxRedelivery;
	}

	/**
	 * Sets the event max redelivery.
	 *
	 * @param eventMaxRedelivery the eventMaxRedelivery to set
	 */
	public void setEventMaxRedelivery(String eventMaxRedelivery) {
		this.eventMaxRedelivery = eventMaxRedelivery;
	}

	/**
	 * Gets the region.
	 *
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}


	/**
	 * Gets the country.
	 *
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}


	/**
	 * Gets the bootstrap servers.
	 *
	 * @return the bootstrapServers
	 */
	public String getBootstrapServers() {
		return bootstrapServers;
	}


	/**
	 * Gets the auto offset reset.
	 *
	 * @return the autoOffsetReset
	 */
	public String getAutoOffsetReset() {
		return autoOffsetReset;
	}


	/**
	 * Gets the group id.
	 *
	 * @return the groupId
	 */
	public String getGroupId() {
		return groupId;
	}

	/**
	 * Gets the output topic.
	 *
	 * @return the outputTopic
	 */
	public String getOutputTopic() {
		return outputTopic;
	}

	/**
	 * Gets the digital dlq topic.
	 *
	 * @return the digitalDlqTopic
	 */
	public String getDigitalDlqTopic() {
		return digitalDlqTopic;
	}

	/**
	 * Gets the es index name.
	 *
	 * @return the esIndexName
	 */
	public String getEsIndexName() {
		return esIndexName;
	}

	/**
	 * Gets the es common index name.
	 *
	 * @return the esCommonIndexName
	 */
	public String getEsCommonIndexName() {
		return esCommonIndexName;
	}
	
	/**
	 * Gets the elastic protocol.
	 *
	 * @return the elasticProtocol
	 */
	public String getElasticProtocol() {
		return elasticProtocol;
	}

	/**
	 * Gets the elastic host.
	 *
	 * @return the elasticHost
	 */
	public String getElasticHost() {
		return elasticHost;
	}


	/**
	 * Gets the elastic port.
	 *
	 * @return the elasticPort
	 */
	public String getElasticPort() {
		return elasticPort;
	}


	/**
	 * Gets the elastic user.
	 *
	 * @return the elasticUser
	 */
	public String getElasticUser() {
		return elasticUser;
	}


	/**
	 * Gets the elastic password.
	 *
	 * @return the elasticPassword
	 */
	public String getElasticPassword() {
		return elasticPassword;
	}

	
	/**
	 * Gets the elastic type swp struct.
	 *
	 * @return the elasticTypeSwpStruct
	 */
	public String getElasticTypeSwpStruct() {
		return elasticTypeSwpStruct;
	}

	/**
	 * Gets the elastic type swp instr.
	 *
	 * @return the elasticTypeSwpInstr
	 */
	public String getElasticTypeSwpInstr() {
		return elasticTypeSwpInstr;
	}

	/**
	 * Gets the elastic type swp prod.
	 *
	 * @return the elasticTypeSwpProd
	 */
	public String getElasticTypeSwpProd() {
		return elasticTypeSwpProd;
	}

	/**
	 * Gets the elastic type swp exec.
	 *
	 * @return the elasticTypeSwpExec
	 */
	public String getElasticTypeSwpExec() {
		return elasticTypeSwpExec;
	}
		
	/**
	 * Gets the event requeue delay.
	 *
	 * @return the eventRequeueDelay
	 */
	public long getEventRequeueDelay() {
		return eventRequeueDelay;
	}

	/**
	 * Gets the kafka event retry time.
	 *
	 * @return the kafkaEventRetryTime
	 */
	public String getKafkaEventRetryTime() {
		return kafkaEventRetryTime;
	}

	/**
	 * Gets the rmq consumer endpoint.
	 *
	 * @return {@link String} Rabbitmq consumer endpoint
	 */
	public String getRmqConsumerEndpoint() {
		return getRmqCamelComponent() 
				+ "://" + getCamelRmqHost() + ":" + getCamelRmqPort()
				+ "/" + getRmqExchange()
				+ "?queue="+getRmqConsumerQueue()
				+ "&autoDelete=false&exchangeType=topic&autoAck=false"
				+ "&automaticRecoveryEnabled=false"
				+ "&routingKey="+getDefaultRoutingKey()
				+ "&deadLetterExchange="+ getRmDlExchange()
				+ "&deadLetterExchangeType=topic"
				+ "&deadLetterQueue="+ getRmDlq()
				+ "&deadLetterRoutingKey="+ getRabbitDlRoutingKey()
				+ "&threadPoolSize="+ getThreadPool()
				+ "&concurrentConsumers=" + concurrRMQConsumer
				+ "&username="+getRmqUser()
				+ "&password="+getRmqPassword()
				+ "&vhost="+getCamelRmqVhost() + "&"+camelCompOption;
	}
	
	/**
	 * Gets the rmq producer endpoint.
	 *
	 * @return {@link String} Rabbitmq producer endpoint
	 */
	public String getRmqProducerEndpoint() {
		return getRmqCamelComponent() 
				+ "://" + getCamelRmqHost() + ":" + getCamelRmqPort()
				+ "/" + getRmqExchange()
				+ "?autoDelete=false&exchangeType=topic&skipQueueDeclare=true"
				+ "&username="+getRmqUser()
				+ "&password="+getRmqPassword()
				+ "&vhost="+getCamelRmqVhost();
	}
	
	/**
	 * Gets the kafka consumer endpoint.
	 *
	 * @return {@link String} Kafka consumer endpoint
	 */
	public String getKafkaConsumerEndpoint() {
		return getKafkaCamelComponent() 
				+ ":" +getOutputTopic()
				+ "?brokers="+getBootstrapServers()
				+ "&groupId="+getGroupId()
				+ "&autoOffsetReset="+getAutoOffsetReset()
				+ "&pollTimeoutMs="+getDefaultTimeout()
				+ "&autoCommitEnable=true"
				+ "&keyDeserializer=org.apache.kafka.common.serialization.StringDeserializer"
				+ "&valueDeserializer=org.apache.kafka.common.serialization.StringDeserializer"
				;
	}
	
	/**
	 * Gets the kafka producer endpoint.
	 *
	 * @return {@link String} Kafka producer endpoint
	 */
	public String getKafkaProducerEndpoint() {
		return getKafkaCamelComponent() + ":" + getOutputTopic() + "?brokers=" + getBootstrapServers()
				+ "&keyDeserializer=org.apache.kafka.common.serialization.StringDeserializer"
				+ "&valueDeserializer=org.apache.kafka.common.serialization.StringDeserializer";
	}

	
	/**
	 * Gets the kafka consumer retry endpoint.
	 *
	 * @return {@link String} Kafka consumer endpoint
	 */
	public String getKafkaConsumerRetryEndpoint() {
		return getKafkaCamelComponent() 
				+ ":" +kafkaRetryTopic
				+ "?brokers="+getBootstrapServers()
				+ "&groupId="+getGroupId()
				+ "&autoOffsetReset="+getAutoOffsetReset()
				+ "&pollTimeoutMs="+getDefaultTimeout()
				+ "&retryBackoffMs="+kafkaEventRetryTime
				+ "&autoCommitEnable=false&autoCommitIntervalMs=0"
				+ "&keyDeserializer=org.apache.kafka.common.serialization.StringDeserializer"
				+ "&valueDeserializer=org.apache.kafka.common.serialization.StringDeserializer"
				;
	}
	
	/**
	 * Gets the kafka retry producer endpoint.
	 *
	 * @return {@link String} Kafka Retry producer endpoint
	 */
	public String getKafkaRetryProducerEndpoint() {	
		return getKafkaCamelComponent() 
				+ ":" + kafkaRetryTopic
				+ "?brokers="+getBootstrapServers()
				+ "&keySerializerClass=org.apache.kafka.common.serialization.StringSerializer"
				;
	}
	
	/**
	 * Gets the kafka dlq endpoint.
	 *
	 * @return {@link String} Kafka DLQ producer endpoint
	 */
	public String getKafkaDlqEndpoint() {	
		return getKafkaCamelComponent() 
				+ ":" + getDigitalDlqTopic()
				+ "?brokers="+getBootstrapServers()
				+ "&keySerializerClass=org.apache.kafka.common.serialization.StringSerializer"
				;
	}
	
	
	/**
	 * Gets the default routing key.
	 *
	 * @return {@link String} The consumer routing key
	 */
	public String getDefaultRoutingKey() {
		return "business"
				+ "." + ( StringUtils.isEmpty(getRegion()) ? "*" : getRegion() )
				+ "." + ( StringUtils.isEmpty(getCountry()) ? "*" : getCountry() )
				+ "." + "*"
				+ "." + "plain"
				;	
	}
	
	/**
	 * Gets the rabbit dl routing key.
	 *
	 * @return {@link String} The dead letter routing key
	 */
	public String getRabbitDlRoutingKey() {
		return getModule()+".dead";
	}
}