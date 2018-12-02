package com.baizhi.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.baizhi.entity.Hint;
import com.baizhi.entity.User;
import com.baizhi.service.InterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class InterfaceController {
    @Autowired
    private InterfaceService interfaceService;


    //1.一级页面接口
    @RequestMapping("/showAllData")
    @ResponseBody
    public Map showAllData(int uid, String type, String sub_type) {
        Map map = new HashMap();
        if (type.equals("all")) {
            return interfaceService.showAllCarouse();
        } else if (type.equals("wen")) {
            return interfaceService.showAllAlbum();
        } else if (type.equals("si")) {
            if (sub_type.equals("ssyj")) {

            } else if (sub_type.equals("xmfy")) {

            } else {
                map.put("error", "error");
                return map;
            }
        } else {
            map.put("error", "error");
            return map;
        }
        return null;
    }

    //    3.闻的详情页接口
    @RequestMapping("/showAlbum")
    @ResponseBody
    public Map showAlbum(int id, int uid) {
        Map map = new HashMap();
        if (id != 0) {
            return interfaceService.showOneAlbum(id);
        } else {
            map.put("error", "error");
            return map;
        }

    }

    //    4.登录接口
    @RequestMapping("/loginOne")
    @ResponseBody
    public Object login(String phone, String password, String code) {
        Hint hint = new Hint();
        if (code.equalsIgnoreCase("code")) {
            User user = interfaceService.loginOne(phone);
            if (user != null) {
                if (user.getPassword().equals(password)) {
                    return user;
                } else {
                    hint.setErrno("-300");
                    hint.setErrmsg("密码错误！");
                    return hint;
                }
            } else {
                hint.setErrno("-200");
                hint.setErrmsg("用户不存在，请确认账号！");
                return hint;
            }
        } else {
            hint.setErrno("-100");
            hint.setErrmsg("验证码错误，请重新输入！");
            return hint;
        }
    }

    //  5.注册接口
    @RequestMapping("/logonOne")
    @ResponseBody
    public Object logonOne(String phone, String password) {
        Hint hint = new Hint();
        if (phone != null && password != null) {
            User user = interfaceService.logonOne(phone);
            if (user != null) {
                hint.setErrno("-200");
                hint.setErrmsg("用户名已存在，请重新输入！");
                return hint;
            } else {
                User user1 = new User();
                user1.setPhonenum(phone);
                user1.setPassword(password);
                interfaceService.logonOneUser(user1);
                return user1;
            }
        }

        return null;
    }

    //    6.1修改个人信息（查询）
    @RequestMapping("/selectOneUserUpdate")
    @ResponseBody
    public Object selectOneUserUpdate(int id) {
        Hint hint = new Hint();
        if (id != 0) {
            User user = interfaceService.selectOneUserUpdate(id);
            if (user != null) {
                return user;
            } else {
                hint.setErrno("-500");
                hint.setErrmsg("用户id有误！");
                return hint;
            }
        } else {
            hint.setErrno("-500");
            hint.setErrmsg("id不能为空！");
            return hint;
        }
    }

    //    6.1修改个人信息（修改）
    @RequestMapping("/updateOneUser")
    @ResponseBody
    public Object updateOneUser(User user) {
        Hint hint = new Hint();
        if (user != null) {
            interfaceService.updateOneUser(user);
            hint.setErrno("000");
            hint.setErrmsg("修改成功！");
            return hint;
        } else {
            hint.setErrno("100");
            hint.setErrmsg("修改失败！");
            return hint;
        }
    }

    //  7.获取短信验证码接口
    @RequestMapping("/mmsVerify")
    @ResponseBody
    public Object mmsVerify() {
        Hint hint = new Hint();
        //设置超时时间-可自行调整
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        //初始化ascClient需要的几个参数
        final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
        final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
        //替换成你的AK
        final String accessKeyId = "LTAIFBm4MggRQCE6";//你的accessKeyId,参考本文档步骤2
        final String accessKeySecret = "QvZuybTABR8Xmrkt2QH3cmex5Nqs2Q";//你的accessKeySecret，参考本文档步骤2
        //初始化ascClient,暂时不支持多region（请勿修改）
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
                accessKeySecret);
        try {
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        } catch (ClientException e) {
            e.printStackTrace();

        }
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象
        SendSmsRequest request = new SendSmsRequest();  //httpClient

        //使用post提交
        request.setMethod(MethodType.POST);
        //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式；发送国际/港澳台消息时，接收号码格式为国际区号+号码，如“85200000000”
        request.setPhoneNumbers("18860360341");
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("何腾飞");
        //必填:短信模板-可在短信控制台中找到，发送国际/港澳台消息时，请使用国际/港澳台短信模版
        request.setTemplateCode("SMS_141606919");
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
        request.setTemplateParam("{\"code\":\"8888\"}");
        //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");
        //请求失败这里会抛ClientException异常
        SendSmsResponse sendSmsResponse = null;
        try {
            sendSmsResponse = acsClient.getAcsResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
            //请求成功
            hint.setErrno("000");
            hint.setErrmsg("请求成功！");
            return hint;
        }

        return null;
    }
}
