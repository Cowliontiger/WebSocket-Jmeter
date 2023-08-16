package com.websocket;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WebSocketClient4Java implements JavaSamplerClient {
    private SampleResult results;
    private String x;
    private String y;
    private String z;
    private String filename;


    // 设置传入的参数，可以设置多个，已设置的参数会显示到Jmeter的参数列表中
    public Arguments getDefaultParameters() {
        Arguments params = new Arguments();
        // 这里的字符串跟其他地方的变量参数无关
        params.addArgument("filename", "0");// 设置参数，并赋予默认值0
        params.addArgument("x", "0");// 设置参数，并赋予默认值0
        params.addArgument("y", "0");// 设置参数，并赋予默认值0
        params.addArgument("z", "0");// 设置参数，并赋予默认值0
        return params;
    }

    // 初始化方法，实际运行时每个线程仅执行一次，在测试方法运行前执行
    public void setupTest(JavaSamplerContext arg0) {
        results = new SampleResult();
    }

    // 测试执行的循环体，根据线程数和循环次数的不同可执行多次
    public SampleResult runTest(JavaSamplerContext arg0) {
        x = arg0.getParameter("b"); // 获取在Jmeter中设置的参数值
        y = arg0.getParameter("a"); // 获取在Jmeter中设置的参数值
        z = arg0.getParameter("c");
        filename = arg0.getParameter("filename"); // 获取在Jmeter中设置的参数值
        results.sampleStart();// jmeter 开始统计响应时间标记
        try {
//                OutputService test = new OutputService();
//                test.output(filename, Integer.parseInt(x), Integer.parseInt(y),
//                        Integer.parseInt(z));

            URI url = new URI("ws://localhost:8080/websocketDemo_war_exploded/websocket");
            WebSocketUtil myClient = new WebSocketUtil(url);
            //myClient.connect();
            myClient.connectBlocking();
            // 往websocket服务端发送数据
            String message_data = String.valueOf((int)(1+Math.random()*(10-1+1)));
            results.sampleStart();
            myClient.send( message_data+"_"+WebSocketServer.MD5(message_data)+"_"+String.valueOf(new SimpleDateFormat("MM").format(new Date())));
            results.sampleEnd();
            myClient.closeBlocking();
            results.setSuccessful(true);
            // 被测对象调用
        } catch (Throwable e) {
            results.setSuccessful(false);
            e.printStackTrace();
        } finally {
            results.sampleEnd();// jmeter 结束统计响应时间标记
        }
        return results;
    }

    // 结束方法，实际运行时每个线程仅执行一次，在测试方法运行结束后执行
    public void teardownTest(JavaSamplerContext arg0) {

    }

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
//        Arguments params = new Arguments();
//        params.addArgument("x", "3");
//        // 设置参数，并赋予默认值0
//        params.addArgument("y", "1");// 设置参数，并赋予默认值0
//        params.addArgument("z", "1");
//        params.addArgument("filename", "xyz.txt");
//        JavaSamplerContext arg0 = new JavaSamplerContext(params);
//        WebSocketClient javatest = new WebSocketClient();
//        javatest.setupTest(arg0);
//        javatest.runTest(arg0);
//        javatest.teardownTest(arg0);

        URI url = new URI("ws://localhost:8080/websocketDemo_war_exploded/websocket");
        WebSocketUtil myClient = new WebSocketUtil(url);
        //myClient.connect();
        myClient.connectBlocking();
        // 往websocket服务端发送数据
        String message_data = String.valueOf((int)(1+Math.random()*(10-1+1)));
        myClient.send( message_data+"_"+WebSocketServer.MD5(message_data)+"_"+String.valueOf(new SimpleDateFormat("MM").format(new Date())));
        myClient.closeBlocking();
    }

}
