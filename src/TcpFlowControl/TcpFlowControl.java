package naim;
/* TCP Flow Control using Sliding Window technique*/

import static java.lang.Math.random;
import java.util.Scanner;
import java.util.Random;

        public class TcpFlowControl {

        public static int generateFrame(int winSize) {
        //this function returns a randomly generated number of generated frames
        //that must be within the size of declared window of the receiver

        Random random = new Random();
        int i, noOfGeneratedFrame;

        noOfGeneratedFrame = random.nextInt(500) % winSize;
        //generated random value within 500 is mod within the window size
        //since number of sent frames cannot be more than the provided window size
          
        if(noOfGeneratedFrame == 0) return winSize;
        //if this value is zero, it means it is the window size and not more
        //else return this value as the number of generated frame in this iteration of the main while loop
        else return noOfGeneratedFrame;
        }

        public static int generateAck(int noOfSent) {
        //this function returns a randomly generated number of acks
        //that must be within the total number of frames to be sent by sender

        Random random = new Random();
        int i, noOfAckFrame;

        noOfAckFrame = (int) (random.nextInt(500) % noOfSent);
        //number of ack−ed frames is mod within the total number of frames to−be sent
        //since number of ack−ed frames can never be more than total number of frames to−be sent
        return noOfAckFrame;
        }

        public static void main(String[] args) {
        int noOfFrame, winSize, startByte = 0, endByte = 0, noOfAck = 0, noOfSent = 0;

        //The scanner class to take in any user inputs
        Scanner scn = new Scanner(System.in);

        System.out.println("Enter the total no of frame: ");
        //The number of frames to be sent by sender
        noOfFrame = scn.nextInt();

        System.out.println("Enter the window size: ");
        //The entire window size that will slide over the to−be sent frames
        winSize = scn.nextInt();

        int dueFrame = noOfFrame;
        //At this stage, the number of due frames is equal to total number of  frames

        while(dueFrame>=0){
            //Following loop iterates until all frames have been sent

            noOfSent = generateFrame(winSize);
            //number of sent frames are randomly generated using this function; not more than window size
            endByte += noOfSent;
            //the last byte upto now is previous byte added by the already sent frames

            if(endByte > noOfFrame)
                endByte = noOfFrame;
            //if last byte exceeds total frame to be sent, (which is not possible practically)
            //then reduce to the highest frames that could be sent,
            //which is number of frames to be send by sender

            for(int i = startByte+1; i <= endByte; i++){
                System.out.println("Sending frame " + i);
                }
            //all the sent frames up to the ack−ed frames are printed out

            noOfAck = generateAck(noOfSent);
            //number of ack−ed frames are generated randomly using function

            startByte += noOfAck;
            //the first byte of frame that is already acknowledged by receiver

            if(startByte > noOfFrame)
                startByte = noOfFrame;
            //if first byte exceeds total frame to be sent, (which is not possible practically)
            //then reduce to the highest frames that could be sent,
            //which is number of frames to be send by sender

            System.out.println("Acknowledgement for the frame upto " + startByte);

            //printing out upto how many frames have been ack−ed by receiver
            dueFrame -= noOfAck;
            //number of frames left will be reduced from previous value
            //by the number of frames ack−ed by receiver
            endByte = startByte;
            //the last byte is updated to the value last ack−ed by receiver
            }
        System.out.println("\nThe Sliding Window Protocol concludes here.");
        }
}
