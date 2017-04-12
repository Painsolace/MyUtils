package com.painsolace.java.sysUtils;

import org.apache.activemq.broker.jmx.BrokerViewMBean;
import org.apache.activemq.broker.jmx.QueueViewMBean;
import org.apache.activemq.broker.jmx.TopicViewMBean;
import org.apache.activemq.web.RemoteJMXBrokerFacade;
import org.apache.activemq.web.config.SystemPropertiesConfiguration;

import javax.management.MBeanServerConnection;
import javax.management.MBeanServerInvocationHandler;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import javax.management.remote.rmi.RMIConnector;
import javax.management.remote.rmi.RMIServer;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Collection;


public class JMX {



    public void one() throws Exception{
        System.out.println("***************************START**********************************");
        //第一种连接方式
        JMXServiceURL url = new JMXServiceURL(
                "service:jmx:rmi:///jndi/rmi://172.16.102.138:1099/jmxrmi");
        JMXConnector connector = JMXConnectorFactory.connect(url, null);
        connector.connect();
        MBeanServerConnection connection = connector.getMBeanServerConnection();

        //第二种连接方式
        Registry registry= LocateRegistry.getRegistry("172.16.102.240",1099);
        RMIServer stub = (RMIServer) registry.lookup("jmxrmi");
        JMXConnector jmxc =  new RMIConnector(stub, null);
        jmxc.connect();
        MBeanServerConnection connection2=jmxc.getMBeanServerConnection();

        //连接之后获取内容
        ObjectName name = new ObjectName(
                "org.apache.activemq:brokerName=amq1,type=Broker");
        BrokerViewMBean mbean = (BrokerViewMBean) MBeanServerInvocationHandler
                .newProxyInstance(connection, name, BrokerViewMBean.class, true);
        System.out.println("Statistics for broker " + mbean.getBrokerId() + " - " + mbean.getBrokerName());
        System.out.println("-------------------------------------------------");
        System.out.println("Total message count: " + mbean.getTotalMessageCount() + "\n");
        System.out.println("Total number of consumers: " + mbean.getTotalConsumerCount());
        System.out.println("Total number of producers: " + mbean.getTotalProducerCount());
        System.out.println("Total number of Queues: " + mbean.getQueues().length);
        System.out.println("Total number of Topics: " + mbean.getTopics().length);


        for (ObjectName queueName : mbean.getQueues()) {
            //System.out.println(queueName);
            QueueViewMBean queueMbean = (QueueViewMBean) MBeanServerInvocationHandler
                    .newProxyInstance(connection, queueName,
                            QueueViewMBean.class, true);
            System.out.println("-------------------------------------------");
            System.out.println(queueName);
            System.out.println("Statistics for queue " + queueMbean.getName());
            System.out.println("Size: " + queueMbean.getQueueSize());
            System.out.println("Number of consumers: " + queueMbean.getConsumerCount());
            System.out.println("Number of producers: " + queueMbean.getProducerCount());
        }
        for (ObjectName topicName :mbean.getTopics()) {
            //System.out.println(topicName);
            TopicViewMBean queueMbean = (TopicViewMBean) MBeanServerInvocationHandler
                    .newProxyInstance(connection, topicName,
                            TopicViewMBean.class, true);
            System.out.println("--------------------------------------------");
            System.out.println(topicName);
            System.out.println("Statistics for queue " + queueMbean.getName());
            System.out.println("Size: " + queueMbean.getQueueSize());
            System.out.println("Number of consumers: " + queueMbean.getConsumerCount());
            System.out.println("Number of producers: " + queueMbean.getProducerCount());

        }
        //mbean.removeQueue("ActiveMQ.DLQ");
        //mbean.removeTopic("ActiveMQ.Advisory.TempTopic");

        System.out.println("***************************END**********************************");
    }

    public void two() throws Exception{
        RemoteJMXBrokerFacade createConnector = new RemoteJMXBrokerFacade();
        // 填写链接属性
        System.setProperty("webconsole.jmx.url", "service:jmx:rmi:///jndi/rmi://172.16.102.240:1099/jmxrmi");
        //System.setProperty("webconsole.jmx.user", "admin");
        //System.setProperty("webconsole.jmx.password", "admin");
        // 创建配置
        SystemPropertiesConfiguration configuration = new SystemPropertiesConfiguration();
        // 创建链接
        createConnector.setConfiguration(configuration);
        Collection<QueueViewMBean> queueViewList = createConnector.getQueues();


        for (QueueViewMBean queueMbean : queueViewList) {
            System.out.println("-------------------------------------------");
            //System.out.println(queueMbean);
            System.out.println("Statistics for queue " + queueMbean.getName());
            System.out.println("Size: " + queueMbean.getQueueSize());
            System.out.println("Number of consumers: " + queueMbean.getConsumerCount());
            System.out.println("Number of producers: " + queueMbean.getProducerCount());
        }
    }



}
