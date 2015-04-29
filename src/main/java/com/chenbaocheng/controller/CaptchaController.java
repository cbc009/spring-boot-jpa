package com.chenbaocheng.controller;

import com.chenbaocheng.consts.Consts;
import com.chenbaocheng.utils.SessionUtil;
import com.github.bingoohuang.patchca.background.MyCustomBackgroundFactory;
import com.github.bingoohuang.patchca.color.ColorFactory;
import com.github.bingoohuang.patchca.color.RandomColorFactory;
import com.github.bingoohuang.patchca.custom.ConfigurableCaptchaService;
import com.github.bingoohuang.patchca.filter.ConfigurableFilterFactory;
import com.github.bingoohuang.patchca.filter.library.AbstractImageOp;
import com.github.bingoohuang.patchca.filter.library.WobbleImageOp;
import com.github.bingoohuang.patchca.font.RandomFontFactory;
import com.github.bingoohuang.patchca.service.Captcha;
import com.github.bingoohuang.patchca.text.renderer.BestFitTextRenderer;
import com.github.bingoohuang.patchca.text.renderer.TextRenderer;
import com.github.bingoohuang.patchca.word.RandomWordFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CBC on 2015-04-15 17:45.
 */
@Controller
public class CaptchaController extends BaseController {

    private static final ConfigurableCaptchaService configurableCaptchaService = new ConfigurableCaptchaService();

    @PostConstruct
    public void init() {
        // 颜色创建工厂,使用一定范围内的随机色
        ColorFactory colorFactory = new RandomColorFactory();
        configurableCaptchaService.setColorFactory(colorFactory);

        // 随机字体生成器
        RandomFontFactory fontFactory = new RandomFontFactory();
        fontFactory.setMaxSize(32);
        fontFactory.setMinSize(28);
        configurableCaptchaService.setFontFactory(fontFactory);

        // 随机字符生成器,去除掉容易混淆的字母和数字,如o和0等
        RandomWordFactory wordFactory = new RandomWordFactory();
        wordFactory.setCharacters("abcdefghkmnpqstwxyz23456789");
        wordFactory.setMaxLength(5);
        wordFactory.setMinLength(4);
        configurableCaptchaService.setWordFactory(wordFactory);

        // 自定义验证码图片背景
        configurableCaptchaService.setBackgroundFactory(new MyCustomBackgroundFactory());

        // 图片滤镜设置
        List<BufferedImageOp> filters = new ArrayList<BufferedImageOp>();
        WobbleImageOp wobbleImageOp = new WobbleImageOp();
        wobbleImageOp.setEdgeMode(AbstractImageOp.EDGE_CLAMP);
        wobbleImageOp.setxAmplitude(3.0);
        wobbleImageOp.setyAmplitude(2.0);
        filters.add(wobbleImageOp);
        ConfigurableFilterFactory filterFactory = new ConfigurableFilterFactory();
        filterFactory.setFilters(filters);
        configurableCaptchaService.setFilterFactory(filterFactory);

        // 文字渲染器设置
        TextRenderer textRenderer = new BestFitTextRenderer();
        textRenderer.setBottomMargin(4);
        textRenderer.setTopMargin(4);
        configurableCaptchaService.setTextRenderer(textRenderer);

        // 验证码图片的大小
        configurableCaptchaService.setWidth(70);
        configurableCaptchaService.setHeight(30);
    }

    @RequestMapping(value = "/captcha.png")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("image/png");
        response.setHeader("cache", "no-cache");

        HttpSession session = request.getSession(true);
        OutputStream outputStream = response.getOutputStream();

        // 得到验证码对象,有验证码图片和验证码字符串
        Captcha captcha = configurableCaptchaService.getCaptcha();
        logger.info("===> Captcha code : {}", captcha.getChallenge());

        // 取得验证码字符串放入Session
        SessionUtil.setValue(request, Consts.CAPTCHA_SESSION_ID, captcha.getChallenge());

        // 取得验证码图片并输出
        BufferedImage bufferedImage = captcha.getImage();
        ImageIO.write(bufferedImage, "png", outputStream);
        outputStream.flush();
        outputStream.close();
    }
}