package com.guet.sportsgebe.config;

import org.springframework.context.annotation.Configuration;

import java.io.FileWriter;
import java.io.IOException;

@Configuration
public class AliPayConfig {

    //↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
//    netapp映射的地址，，，根据自己的需要修改
    private static String neturl = "http://6dynnj.natappfree.cc";

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号 按照我文章图上的信息填写
    public static String app_id = "2016101400686168";

    // 商户私钥，您的PKCS8格式RSA2私钥  刚刚生成的私钥直接复制填写
    public static String merchant_private_key ="MIIEugIBADANBgkqhkiG9w0BAQEFAASCBKQwggSgAgEAAoIBAQCWz/MUQQDwTsBRGD4tkZokwchZNFTXDChk79DdxkoFdWc82xvgLQLk325S7WLwahCBhmD/WqSm7Z+MMJUJNkXBLaIQagnBbuA9XNkDuXvvqAsEQtuOyinfFSQzL1ZyeDbzxz3YXiNnslDoBntF84WV/iyTNPgj63eWn70V6pR2XRxjSbRaJejIICIUquuvrXtU7NIFgJ70ac64Rve+e4+rE+2Pn+TGn99my98KRMgQeZVQ9rlDe768oQLIXGtLAPwa2YynijtrYyF5ZOUDnGJUcX8EFtmtBIJcirh43gnlUzejX6EzbT6vBSVjIzmqBJLKBfdyCElTajrZx94oBo77AgMBAAECgf97thmwg6JBflhELZ7TwpyvikbO5h6ySUbZhd/QDNiroaMOSK6VNlcpaB8X56NBk7/2gEURM0ndpQ878J5Q8DRIQyauUE2wX0OTEpbydzUzYqWm2TFB8JRoau9HDY3cWMC7OLWCx7ZDWgMEUkzNFPaAFAB8wPbMgFuZM2/oioU+kd2FG269bhE15IQdQ0nghi4FVVi6zxCwEHo7DLy+2z8G5pggUcg5auk0zpTQP9vJ+Y3h3wbAJv1q2z6ChpMwnTj22/CwaKHOEXOAiF/Pq8DGfQUbKYhZfmV40QLCjy7jd+JCRM7fGSgyFqJskv6F0FUG0nN/9agDsxWdGf6tCyECgYEA4FGvi7lR8rwFKWlvlVeEQ+WPwA8xsk//d/mediEoWHxvoPZh3JPvv0LA7T2kBCFry3nI7oZeIJ7NfBmXVY8NLI7QdxOLk/oYOY0SetEZu/u+0IWxoz/DyY0sLYbtB4hfqoJC8mbLFajJhwcnC4nTn22Mv1XHVudGIA0Roc/sI+MCgYEArBybb4T1HEarJvv1XETicUjykKqJsE20f9Uta+vW4f2zY7pDBRjYMHJn2eGO4STlJDlnMtGfJyykZVJnHWUrYli8ZpYB63LgiVx+c4fJ4sE95xB1gCosTstz5avCwTUlz0pfphhUG0Gqwk5SOPqif78HQ+iNjn3+MZRosaTbRAkCgYBR/+pRBFSdi/fDFKTSjeRuwXsT9TnweefkXp57hlFuT0arK4XIkVehrfBCiTbsv57dCc7Wys2b1RE+nUhB4YIqWKaB8OYkGNdEKnqn5MooerVdGpI+H/AdPHr6xYjeKQ73kJiSbdstNr88mfO+9woiDHTX+K5e6TosOzrH0O78iwKBgADeiB5xEnfZF4Yz4ZJcurQNxUPbyKQ+UDoRDpCr+ULc8CoBx9LfxD/CZCnijMEwWYPsUkwcaOxeulnUp7arSyPNBO6TtWDGDMJE2FKnDer6RODE4YMLM3wWZPh0/J/TL+SzIHaLSul0VHN0x8+H3Ig+o7/BDe35Tu5bLIgxJ/ZZAoGAG/+8bwe8qONma4AtyQkPMzlulo6+6ZVnpso8t0J83HidYIgSxsw9xeNPnqozXHEf8PDFsC+VjI0srgJ9SRTzaH1pXRrt19Lp2nW+2fR1IQzaFbrdJZpOr2tY7Cl5RhnevtjW/vntbj95f37joBBhHdhS9u5yE5AjNmgmi97mtWs=";

    // 支付宝公钥,对应APPID下的支付宝公钥。 按照我文章图上的信息填写支付宝公钥，别填成商户公钥
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAi9DLT7Cvvcy3J9pdZBawKVQu06CXVruHZAMazDMYHLBeClyRV8YT9eOfC6r8ZjTdT91SJUvZwQ3/JFvbiELZ0jcEFCJOD5N0DnH2RZx6g924sRCwxgE4MCRJrfoJ4ipeoeW9akraHretOCDoHlH6trk3rbUhm4VPnAtM47DBKxEU9M/2yC435FuKA8Cg4MGHz9YnvmLzAiKVlq0x6uL5fe+prY1BzRmXFBHzugjKvf9Ce5UIcZvY3DYt5cpLq3JxeaCa3+BwTr6KEs+Ibm3hkWH20UZuzin/vgpEGgkiQhJ57Uqc+9CadIyK3Vvv8PCTEHyu1c/uznGQCXNKX7ijbQIDAQAB";
    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，其实就是你的一个支付完成后返回的页面URL
    public static String notify_url = "http://192.168.200.105:8080/Users/user";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，其实就是你的一个支付完成后返回的页面URL
    public static String return_url = "http://192.168.200.105:8080/Users/user";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "https://openapi.alipaydev.com/gateway.do";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



}

