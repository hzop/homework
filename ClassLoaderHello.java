package com.eg.work01.learning_work;

import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.Base64;

/**
 * @program: java_advanced
 * @description 自定义一个 Classloader，加载一个 Hello.xlass 文件，
 * 执行 hello 方法，此文件内容是一个 Hello.class 文件所有字节（x=255-x）处理后的文件。
 * @author: zyoop
 * @create: 2021-05-16 09:23
 **/
public class ClassLoaderHello extends ClassLoader {

    private String path = "D:\\WorkSpace\\CodeStudyIDEA\\java_advanced\\jvm_practice\\src\\com\\eg\\work01\\learning_work\\Hello.xlass";
     // private String path = "D:\\WorkSpace\\CodeStudyIDEA\\java_advanced\\jvm_practice\\src\\com\\eg\\work01\\learning_work\\Hello.class";


    public static void main(String[] args)  throws Exception{

        //new ClassLoaderHello().findClass("com.eg.work01.learning_work.Hello").newInstance();
        //获取类的字节码对象
        Class<?> aClass =  new ClassLoaderHello().findClass("com.eg.work01.learning_work.Hello");
        //调用无参构造方法
        Method method = aClass.getMethod("hello");
        method.invoke(aClass.newInstance());


   /*     Constructor<?>[] declaredConstructors = aClass.getDeclaredConstructors();
        for(Constructor con : declaredConstructors){
            System.out.println(con);
        }
        Method[] methods = aClass.getMethods();
        for(Method method : methods){
            System.out.println(method);
        }*/

    }


    protected Class<?> findClass(String name) {
      /*  String base64CodeTest  = null;
        try {
            base64CodeTest = encodeBase64File(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(base64CodeTest);*/
        String   base64Code = "NQFFQf///8v/4/X/+f/x9v/w/+/3/+71/+3/7Pj/6/j/6v7/+cOWkZaLwf7//NfWqf7/+7yQm5r+//CzlpGasYqSnZqNq56dk5r+//qXmpOTkP7/9ayQio2cmrmWk5r+//W3mpOTkNGVnome8//4//f4/+nz/+j/5/7/7Leak5OQ09+ck56MjLOQnpuajd74/+bz/+X/5P7/+reak5OQ/v/vlZ6JntCTnpGY0LCdlZqci/7/75WeiZ7Qk56RmNCshoyLmpL+//yQiov+/+qzlZ6JntCWkNCvjZaRi6yLjZqeksT+/+yVnome0JaQ0K+NlpGLrIuNmp6S/v/4j42WkYuTkf7/6tezlZ6JntCTnpGY0KyLjZaRmMTWqf/e//r/+f///////f/+//j/9//+//b////i//7//v////rVSP/+Tv////7/9f////n//v////7//v/0//f//v/2////2v/9//7////2Tf/97fxJ//tO/////v/1////9f/9////+//3//r//v/z/////f/y";
        byte[] bytes = decode(base64Code);
        byte[] bytesNew = new byte[bytes.length];
        for (int i = 0 ; i< bytes.length ; i++){
            //System.out.println((byte) (255 - bytes[i]));
            bytesNew[i] = (byte) (255 - bytes[i]);
            System.out.print(bytesNew[i]);
        }

        return defineClass(name, bytesNew, 0, bytesNew.length);

    }

    //jdk自有的base64解码的方式 变成字节数组
    public byte[] decode(String base64) {
        return Base64.getDecoder().decode(base64);

    }

    //转base64
    public static String encodeBase64File(String path) throws Exception {
        File file = new File(path);
        FileInputStream inputFile = new FileInputStream(file);
        byte[] buffer = new byte[(int) file.length()];
        inputFile.read(buffer);
        inputFile.close();
        return new BASE64Encoder().encode(buffer);
    }


}
