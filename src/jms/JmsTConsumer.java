package jms;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JmsTConsumer implements Runnable {

	ActiveMQConnectionFactory jms=new ActiveMQConnectionFactory();
	private Connection connection;
	public static void main(String[] args) {
	JmsTConsumer jms=new JmsTConsumer();
	Thread t=new Thread(jms);
	t.start();
		
	}

	@Override
	public void run() {
				try {
					connection = jms.createConnection();
					connection.start();
					Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
					Queue queue = session.createQueue("photos");
					MessageConsumer consumer = session.createConsumer(queue);
					while(true){
						TextMessage message =(TextMessage) consumer.receive();
						String text = message.getText();
						int i=1+1;
					}					
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(connection!=null){
					try {
						connection.close();
					} catch (JMSException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			
			
	}

}
