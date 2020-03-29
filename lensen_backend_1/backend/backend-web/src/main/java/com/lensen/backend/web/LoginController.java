package com.lensen.backend.web;

import com.lensen.backend.constants.IConstants;
import com.lensen.backend.dal.domain.auth.AdminAuthPagePO;
import com.lensen.backend.dal.domain.auth.PagePO;
import com.lensen.backend.dal.domain.manager.ManagerDto;
import com.lensen.backend.service.manager.ManagerAO;
import com.lensen.backend.service.rolerights.RoleRightsAO;
import com.lensen.backend.utils.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
public class LoginController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private ManagerAO managerAO;

    @Autowired
    private RoleRightsAO roleRightsAO;

    @RequestMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("manager/login");
    }

    @PostMapping("/manager/isLogin")
    public ModelAndView isLogin(ManagerDto managerDto, HttpServletResponse response) throws IOException {
        String _code = managerDto.getCode();
        String username = managerDto.getUsername();
        String password = managerDto.getPassword();
        String ipAddress = managerDto.getIpAddress();
        String code = ManagerContext.getCheckCode();
        Result result = Result.getInitializedResult();
        try {
            if (null == username || null == password) {
                return new ModelAndView("manager/login");
            }
            result = managerAO.adminLogin(username, password, code, _code, ServletUtils.getRemortIp(), ipAddress);
            ResponseUtils.toJson(result, response);
        } catch (AuthenticationException e) {
            result.setIsSuccess(Boolean.FALSE);
            result.setMsg(IConstants.AUTH_FAIL);
            ResponseUtils.toJson(result, response);
            LOGGER.error(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @RequestMapping("/index")
    public ModelAndView index() {
        LoginManager manager = ManagerContext.getLoginUser();
        if (null == manager) {
            return new ModelAndView("manager/login");
        }

        Map<String, Object> params = new HashMap<>();
        AdminAuthPagePO result = roleRightsAO.queryRoleRightsList(manager.getId());
        if (null != result) {
            List<PagePO> pageDetail = roleRightsAO.queryManagerPageDetail(result.getApPO().getPageId());
            if (!pageDetail.isEmpty()) {
                params.put("canShowRoles", pageDetail);
            }

        }
        params.put("userName", manager.getUsername());
        return new ModelAndView("manager/index", params);
    }

    /**
     * 退出登录
     *
     * @return 跳转到登录页面
     */
    @RequestMapping("/manager/logOut")
    public ModelAndView adminLogOut(HttpServletRequest request) {
        request.getSession().removeAttribute(IConstants.SESSION_ADMIN);
        SecurityUtils.getSubject().logout();
        return new ModelAndView("manager/login");
    }

    /**
     * 生成验证码
     */
    @GetMapping("/verificationCode")
    public ModelAndView verificationCode(HttpServletResponse response) {
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
        ManagerContext.saveCheckCode(sRand.toString());
        // 图象生效
        g.dispose();
        try {
            ServletOutputStream responseOutputStream = response.getOutputStream();
            // 输出图象到页面
            ImageIO.write(image, "JPEG", responseOutputStream);
            // 以下关闭输入流！
            responseOutputStream.flush();
            responseOutputStream.close();
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }

        return null;
    }

    /**
     * 给定范围获得随机颜色
     *
     * @param fc
     * @param bc
     * @return
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

}
