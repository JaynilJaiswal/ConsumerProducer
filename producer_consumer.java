import java.util.concurrent.Semaphore;
public class ConsumerProducer{
    
    public static void main(String[] args) {
 
           Semaphore semPro=new Semaphore(1);
           Semaphore semCon=new Semaphore(0);
           System.out.println("semPro permit=1 | semCon permit=0");
           
       Producer producer=new Producer(semPro,semCon);
       Consumer consumer=new Consumer(semCon,semPro);
      
        Thread producerThread = new Thread(producer, "ProducerThread");
        Thread consumerThread = new Thread(consumer, "ConsumerThread");
 
        producerThread.start();
        consumerThread.start();
 
    }
}
class Producer implements Runnable{
    
    Semaphore semPro;
    Semaphore semCon;
    
    
    public Producer(Semaphore semPro,Semaphore semCon) {
           this.semPro=semPro;
           this.semCon=semCon;
    }
 
    public void run() {
           for(int i=1;i<=5;i++){
                  try {
                      semPro.acquire();
                      System.out.println("Produced : "+i);
                      semCon.release();
                        
                  } catch (InterruptedException e) {
                        e.printStackTrace();
                  }
           }          
    }
}
class Consumer implements Runnable{
 
    Semaphore semCon;
    Semaphore semPro;
    
    public Consumer(Semaphore semCon,Semaphore semPro) {
           this.semCon=semCon;
           this.semPro=semPro;
    }
 
    public void run() {
           
           for(int i=1;i<=5;i++){
                  try {
                      semCon.acquire();
                      System.out.println("Consumed : "+i);
                      semPro.release();
                  } catch (InterruptedException e) {
                        e.printStackTrace();
                  }
           }
    }
    
}