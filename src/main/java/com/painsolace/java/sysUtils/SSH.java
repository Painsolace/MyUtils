package com.painsolace.java.sysUtils;


import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.InputStream;
import java.io.OutputStream;


/**此工具用于使用ssh到linux执行命令
 *
 * Created by painsolace on 2017/2/13.
 */
public class SSH {

    public static void main(String[] args) throws Exception{
        Session session = null;
        Channel channel = null;
        String user = "root";
        String ip = "172.16.102.129";
        String pwd = "123456";
        Integer port = 22;

        JSch jsch = new JSch();
        session = jsch.getSession(user,ip,port);

        session.setPassword(pwd);//设置密码
        //设置第一次登陆的时候提示，可选值：(ask | yes | no)
        session.setConfig("StrictHostKeyChecking", "no");
        //设置登陆超时时间
        session.connect(10000);

        try {
            channel = (Channel) session.openChannel("exec");

           InputStream instream = channel.getInputStream();
            OutputStream outstream = channel.getOutputStream();

            String shellCommand = "echo I love you  \n";
           /* outstream.write(shellCommand.getBytes());
            outstream.flush();*/
            ((ChannelExec)channel).setCommand(shellCommand);

            channel.connect();

           //获取命令执行的结果
            if (instream.available() > 0) {
                byte[] data = new byte[instream.available()];
                int nLen = instream.read(data);

                if (nLen < 0) {
                    throw new Exception("network error.");
                }

                //转换输出结果并打印出来
                String temp = new String(data, 0, nLen,"iso8859-1");
                System.out.println(temp);
            }
            outstream.close();
            instream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.disconnect();
            channel.disconnect();
        }
    }


}
