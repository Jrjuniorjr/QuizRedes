/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizredes;

import java.io.IOException;
import java.net.Socket;


public class QuizRedes {

    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        Socket socket = new Socket("localhost", 5555);
        String mgs;
        TCPClient tcpClient = new TCPClient(socket);
        tcpClient.run();
        
    }

}
