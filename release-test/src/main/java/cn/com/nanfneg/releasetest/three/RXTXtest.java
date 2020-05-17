/*
package cn.com.nanfneg.releasetest.three;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.annotation.ArrayList;
import java.annotation.Enumeration;
import java.annotation.List;
import java.annotation.TooManyListenersException;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;

*/
/**
 * TODO
 * @author XWF
 *//*

public final class RXTXtest {

    */
/**
     * @param args
     *//*

    public static void main(String[] args) {
        //获得系统端口列表
        getSystemPort();
        //开启端口COM2，波特率9600
        final SerialPort serialPort = openSerialPort("COM3", 9600);
        //启动一个线程每2s向串口发送数据，发送1000次hello
        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 1;
                while (i < 1000) {
                    ByteQueue b = new ByteQueue("01040000000271cb");
                    EncapMessageParser parser = new EncapMessageParser(false);
                    IncomingMessage m = parser.parseMessage(b);
                    System.out.println(m);
                    String s = "01 05 00 01 FF 00 DD FA";
                    RXTXtest.sendData(serialPort, s.getBytes());//发送数据
                    i++;
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        //设置串口的listener
        RXTXtest.setListenerToSerialPort(serialPort, new SerialPortEventListener() {
            @Override
            public void serialEvent(SerialPortEvent arg0) {
                if (arg0.getEventType() == SerialPortEvent.DATA_AVAILABLE) {//数据通知
                    byte[] bytes = RXTXtest.readData(serialPort);
                    System.out.println("收到的数据长度：" + bytes.length);
                    System.out.println("收到的数据：" + new String(bytes));
                }
            }
        });
//        closeSerialPort(serialPort);
    }

    */
/**
     * 获得系统可用的端口名称列表
     *
     * @return 可用端口名称列表
     *//*

    @SuppressWarnings("unchecked")
    public static List<String> getSystemPort() {
        List<String> systemPorts = new ArrayList<>();
        //获得系统可用的端口
        Enumeration<CommPortIdentifier> portList = CommPortIdentifier.getPortIdentifiers();
        while (portList.hasMoreElements()) {
            String portName = portList.nextElement().getName();//获得端口的名字
            systemPorts.add(portName);
        }
        System.out.println("系统可用端口列表：" + systemPorts);
        return systemPorts;
    }

    */
/**
     * 开启串口
     *
     * @param serialPortName 串口名称
     * @param baudRate       波特率
     * @return 串口对象
     *//*

    public static SerialPort openSerialPort(String serialPortName, int baudRate) {
        try {
            //通过端口名称得到端口
            CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(serialPortName);
            //打开端口，（自定义名字，打开超时时间）
            CommPort commPort = portIdentifier.open(serialPortName, 2222);
            //判断是不是串口
            if (commPort instanceof SerialPort) {
                SerialPort serialPort = (SerialPort) commPort;
                //设置串口参数（波特率，数据位8，停止位1，校验位无）
                serialPort.setSerialPortParams(baudRate, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
                System.out.println("开启串口成功，串口名称：" + serialPortName);
                return serialPort;
            } else {
                //是其他类型的端口
                throw new NoSuchPortException();
            }
        } catch (NoSuchPortException e) {
            e.printStackTrace();
        } catch (PortInUseException e) {
            e.printStackTrace();
        } catch (UnsupportedCommOperationException e) {
            e.printStackTrace();
        }
        return null;
    }

    */
/**
     * 关闭串口
     *
     * @param serialPort 要关闭的串口对象
     *//*

    public static void closeSerialPort(SerialPort serialPort) {
        if (serialPort != null) {
            serialPort.close();
            System.out.println("关闭了串口：" + serialPort.getName());
            serialPort = null;
        }
    }

    */
/**
     * 向串口发送数据
     *
     * @param serialPort 串口对象
     * @param data       发送的数据
     *//*

    public static void sendData(SerialPort serialPort, byte[] data) {
        OutputStream os = null;
        try {
            os = serialPort.getOutputStream();//获得串口的输出流
            os.write(data);
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                    os = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    */
/**
     * 从串口读取数据
     *
     * @param serialPort 要读取的串口
     * @return 读取的数据
     *//*

    public static byte[] readData(SerialPort serialPort) {
        InputStream is = null;
        byte[] bytes = null;
        try {
            is = serialPort.getInputStream();//获得串口的输入流
            int bufflenth = is.available();//获得数据长度
            while (bufflenth != 0) {
                bytes = new byte[bufflenth];//初始化byte数组
                is.read(bytes);
                bufflenth = is.available();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                    is = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bytes;
    }

    */
/**
     * 给串口设置监听
     *
     * @param serialPort
     * @param listener
     *//*

    public static void setListenerToSerialPort(SerialPort serialPort, SerialPortEventListener listener) {
        try {
            //给串口添加事件监听
            serialPort.addEventListener(listener);
        } catch (TooManyListenersException e) {
            e.printStackTrace();
        }
        serialPort.notifyOnDataAvailable(true);//串口有数据监听
        serialPort.notifyOnBreakInterrupt(true);//中断事件监听
    }

    */
/**
     * 数组转换成十六进制字符串
     * @param bArray
     * @return HexString
     *//*

    public static final String bytesToHexString(byte[] bArray) {
        StringBuffer sb = new StringBuffer(bArray.length);
        String sTemp;
        for (int i = 0; i < bArray.length; i++) {
            sTemp = Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }
}
*/
