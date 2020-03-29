package com.lensen.mobile.web;

import com.lensen.backend.constants.IConstants;
import com.lensen.backend.dal.domain.manager.ManagerDto;
import com.lensen.backend.dal.domain.manager.ManagerPO;
import com.lensen.backend.security.Encrypt;
import com.lensen.backend.utils.*;
import com.lensen.mobile.service.manager.ManagerAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import sun.misc.BASE64Encoder;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

@RestController
public class LoginController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private ManagerAO managerAO;



    /**
     * 使用账号登录
     */
    @PostMapping(value = "/login_by_uname", produces = "application/json;charset=UTF-8")
    public String login_by_uname(@RequestBody ManagerDto managerDto) {
        long start = System.currentTimeMillis();
        String username = managerDto.getUsername();
        BaseResponse<Object> result = new BaseResponse<>();
        try {
            if (CommonUtil.isNullParam(username)) {
                result.setCode(Status.NULL_PARAM.getCode());
                result.setMsg(Status.NULL_PARAM.getMessage());
                return CommonUtil.responseFromObject(result);
            }
            result = managerAO.loginByUname(managerDto);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            result.setCode(Status.USER_NOT_EXISTED.getCode());
            result.setMsg(Status.USER_NOT_EXISTED.getMessage());
        }
        LOGGER.info("/login_by_uname cost: {}ms", System.currentTimeMillis() - start);
        return CommonUtil.responseFromObject(result);
    }



    @PostMapping(value = "/manager/isLogin", produces = "application/json;charset=UTF-8")
    public String isLogin(@RequestBody ManagerDto managerDto) {
        long start = System.currentTimeMillis();
        String _code = managerDto.getCode();
        String username = managerDto.getUsername();
        String password = managerDto.getPassword();
        String token = managerDto.getToken();
        BaseResponse<Object> result = new BaseResponse<>();
        try {
            if (CommonUtil.isNullParam(username, password, _code, token)) {
                result.setCode(Status.NULL_PARAM.getCode());
                result.setMsg(Status.NULL_PARAM.getMessage());
                return CommonUtil.responseFromObject(result);
            }
            result = managerAO.login(managerDto);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            result.setCode(Status.USER_NAME_OR_PWD_ERROR.getCode());
            result.setMsg(Status.USER_NAME_OR_PWD_ERROR.getMessage());
        }
        LOGGER.info("/manager/isLogin cost: {}ms", System.currentTimeMillis() - start);
        return CommonUtil.responseFromObject(result);
    }

    /**
     * 退出登录
     *
     * @return 跳转到登录页面
     */
    @RequestMapping(value = "/manager/logOut", produces = "application/json;charset=UTF-8")
    public ModelAndView adminLogOut(HttpServletRequest request) {
        return new ModelAndView("manager/login");
    }

    /**
     * 生成验证码
     */
    @GetMapping(value = "/verificationCode", produces = "application/json;charset=UTF-8")
    public String verificationCode() {
        BaseResponse<Object> result = new BaseResponse<>();
        result.setCode(Status.LSucceed.getCode());
        result.setMsg(Status.LSucceed.getMessage());
        // 在内存中创建图象
        int width = 65, height = 20;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 获取图形上下文
        Graphics g = image.getGraphics();
        // 生成随机类
        Random random = new Random();
        // 设定背景色
        g.setColor(getRandColor(230, 255));
        g.fillRect(0, 0, 100, 25);
        // 设定字体
        g.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 18));
        // 产生0条干扰线，
        g.drawLine(0, 0, 0, 0);
        // 取随机产生的认证码(4位数字)
        StringBuilder sRand = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand.append(rand);
            // 将认证码显示到图象中
            g.setColor(getRandColor(100, 150));// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
            g.drawString(rand, 15 * i + 6, 16);
        }
        LOGGER.info("Login verificationCode:{}", sRand.toString());
        for (int i = 0; i < (random.nextInt(5) + 5); i++) {
            g.setColor(new Color(random.nextInt(255) + 1, random.nextInt(255) + 1, random.nextInt(255) + 1));
            g.drawLine(random.nextInt(100), random.nextInt(30), random.nextInt(100), random.nextInt(30));
        }
        // 记录验证码
        String token = UUID.randomUUID().toString();
        ManagerPO po = new ManagerPO();
        po.setToken(token);
        po.setCode(sRand.toString());

        int ret = managerAO.saveCode(po);
        if (ret == 0) {
            result.setCode(Status.LFailed.getCode());
            result.setMsg(Status.LFailed.getMessage());
            return CommonUtil.responseFromObject(result);
        }

        // 图象生效
        g.dispose();
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            // 输出图象到页面
            ImageIO.write(image, "JPEG", byteArrayOutputStream);

            // 对字节数组Base64编码
            BASE64Encoder encoder = new BASE64Encoder();
            String base64Img = encoder.encode(byteArrayOutputStream.toByteArray());
            base64Img = base64Img.replaceAll("\n", "").replaceAll("\r", "");//删除 \r\n
            base64Img = "data:image/jpeg;base64," + base64Img;

            Map<String, Object> map = new HashMap<>();
            map.put("token", token);
            map.put("img", base64Img);
            result.setResponse(map);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            result.setCode(Status.LFailed.getCode());
            result.setMsg(Status.LFailed.getMessage());
        }

        return CommonUtil.responseFromObject(result);
    }

    /**
     * 给定范围获得随机颜色
     */
    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        fc = fc > 255 ? 255 : fc;
        bc = bc > 255 ? 255 : bc;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    /**
     * 判断原密码是否正确
     * @param managerDto
     * @return
     * @throws Exception
     */
    /*@PostMapping("manager/confirmPrePassword")
    public String confirmPrePassword(@RequestBody ManagerDto managerDto) throws Exception {
        String password = managerDto.getPassword();
        BaseResponse<Object> result = new BaseResponse<>();

        Long id = null;
        try {
            LoginManager manager = ManagerContext.getLoginUser();
            id = manager.getId();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            result.setCode(Status.NO_CACHE_USER.getCode());
            result.setMsg(Status.NO_CACHE_USER.getMessage());
            return CommonUtil.responseFromObject(result);
        }

        if (CommonUtil.isNullParam(password)) {
            result.setCode(Status.NULL_PARAM.getCode());
            result.setMsg(Status.NULL_PARAM.getMessage());
            return CommonUtil.responseFromObject(result);
        }
        try {
            password = Encrypt.encryptSES(CryptionUtil.md5((password)), IConstants.MD5_KEY);
        } catch (Exception e) {
            e.printStackTrace();
        }
        result = managerAO.isPasswordTrue(password,id);


        return CommonUtil.responseFromObject(result);
    }*/

    /**
     * 修改密码
     * @return
     */
    /*@PostMapping("manager/changePassword")
    public String changePassword(@RequestBody ManagerDto managerDto){
        String password = managerDto.getPassword();


        BaseResponse<Object> result = new BaseResponse<>();
        result.setCode(Status.LSucceed.getCode());
        result.setMsg(Status.LSucceed.getMessage());

        Long id = null;
        try {
            LoginManager manager = ManagerContext.getLoginUser();
            id = manager.getId();
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(Status.NO_CACHE_USER.getCode());
            result.setMsg(Status.NO_CACHE_USER.getMessage());
        }

        if (CommonUtil.isNullParam(password)) {
            result.setCode(Status.NULL_PARAM.getCode());
            result.setMsg(Status.NULL_PARAM.getMessage());
            return CommonUtil.responseFromObject(result);
        }

        try {
            password = Encrypt.encryptSES(CryptionUtil.md5((password)), IConstants.MD5_KEY);
        } catch (Exception e) {
            e.printStackTrace();
        }

        int ret = managerAO.changePassword(password,id);
        if (ret == 0) {
            result.setCode(Status.LFailed.getCode());
            result.setMsg(Status.LFailed.getMessage());
            return CommonUtil.responseFromObject(result);
        }
        return CommonUtil.responseFromObject(result);
    }*/

    /**
     * 修改密码
     * @return
     */
    @PostMapping("manager/changePassword")
    public String changePassword(@RequestBody ManagerDto managerDto){
        String newPassword = managerDto.getPassword();
        String confirmPassword = managerDto.getConfirmPassword();
        Long id = managerDto.getLoginUserId();
        System.out.println("long类型id为空怎么办"+id);

        System.out.println(newPassword+"----"+confirmPassword);
        BaseResponse<Object> result = new BaseResponse<>();
        result.setCode(Status.LSucceed.getCode());
        result.setMsg(Status.LSucceed.getMessage());

        /*Long id = null;
        try {
            LoginManager manager = ManagerContext.getLoginUser();
            id = manager.getId();
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(Status.NO_CACHE_USER.getCode());
            result.setMsg(Status.NO_CACHE_USER.getMessage());
            return CommonUtil.responseFromObject(result);
        }*/

        if (CommonUtil.isNullParam(newPassword,confirmPassword)) {
            result.setCode(Status.NULL_PARAM.getCode());
            result.setMsg(Status.NULL_PARAM.getMessage());
            return CommonUtil.responseFromObject(result);
        }
        if(id == null){
            result.setCode(Status.NULL_PARAM.getCode());
            result.setMsg(Status.NULL_PARAM.getMessage());
            return CommonUtil.responseFromObject(result);
        }

        //后台传的就是加密的密码
        try {
            newPassword = Encrypt.encryptSES(newPassword, IConstants.MD5_KEY);
            confirmPassword = Encrypt.encryptSES(confirmPassword, IConstants.MD5_KEY);
        } catch (Exception e) {
            e.printStackTrace();
        }

        result = managerAO.changePassword(newPassword,confirmPassword,id);
        return CommonUtil.responseFromObject(result);
    }

}
