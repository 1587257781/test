package com.tenhisi.web.utils;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.List;

/**
 * 这里放要在业务里多次调用的方法
 */
@Slf4j
public class CgsUtil {

    public static byte[] toByteArray(InputStream input) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024*4];
        int n = 0;
        while (-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
        }
        return output.toByteArray();
    }

    public static void main(String[] args) throws Exception {
        String rp = "http://220.180.38.89:9002/live/3TPCA45046LCYQT/hls.m3u8?appid=maanshanzhsq&signature=F74D86307F8834E080607BE64F2020DF&timestamp=1633024840157";
        rp = StrUtil.replace(rp,"220.180.38.89:9002","223.240.76.158:83");
        System.out.println(rp);
        String secret = "XGUVwvWwB2ElT0VAJB6o9w";
        byte[] key = secret.getBytes();
        String params = null;
        String PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAL/d6TNsYXQ9FEwy" +
                "fx58ezSpHvhvi4vGJ/IxJt5U/v6UWYdnpa10lbT8MvjuGLk9UsnRgexL5p8ZcbgV" +
                "APhQ9y8+EUZo2fN2KRFzP/Ietsl+4j0/losAHBjkjXK4P2Xv9j4F17jnu63QuTW/" +
                "DQvyh+wxWW4H3s2/qGLSw5bstuxjAgMBAAECgYAo49NCSVPPoQDFaHGc/qyHQY+/" +
                "JI6Z4EY9IGqHMZgSi201JUqy18jcBG+ci6mrOL1/E25b/KUOvS52K8vEIAU9poje" +
                "3KxTvLMTljUWtOmt9nqksVcobBOF/LCJ851b87uGXc9PFoCT7SaQmi0xt4hRNKn5" +
                "si+Px1HcTXoLPNx5MQJBAO4F4sGDR7Qd8NM65EH24Y1Kup4/R/bJOVG00+iVGcoa" +
                "55Vyvw5NcEeRrfEvZEJbihPZvG3mNvCmqYazd20lxMsCQQDOW5uQsumw32hgqaGO" +
                "g8VovK2cohEH5SMOidS2tsAvw5ThKO6sEpszlgWQSM7OnfjtCKi+4Xobkr/HhS0/" +
                "KhvJAkAOLN2PRsE7cdkIy2YSo6BVKNEqYXxorx0xx4IjRNXvWmDWXqoQMP5x1LQ2" +
                "O+tNpGP5wKrfJKm69UH9WqzPHEO3AkEAqCxcMaa9cNoBVJWqBl05aswpqPcjbg29" +
                "bkHBy05QfhyknoMfT7iyJ25iBl5vvE9d6L8f1sAnJYWJKe9NGqcUkQJAITEom07D" +
                "vkjWJ+6vQxfyi2eIFh/WLEZmcwBxRyH4afZbSQVhk2Oi+TrTH0rTiIWTdvNVkaFu" +
                "xIFS3KjWC8IQxg==";
//        byte[] encoded = Base64.decodeBase64(PRIVATE_KEY);
//        PrivateKey privateKey = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(encoded));
//        String encFilePath = "H:\\gits\\communityGrids\\code\\thx-web\\src\\main\\java\\com\\tenhisi\\web\\utils\\enc.file";
//        Path encPath = Paths.get(encFilePath);
//        byte[] encBytes = Files.readAllBytes(encPath);
//        Cipher cipher1 = Cipher.getInstance("RSA");
//        cipher1.init(Cipher.DECRYPT_MODE, privateKey);
//        byte[] decstr = cipher1.doFinal(encBytes);

        RSA rsa = new RSA(PRIVATE_KEY, null);
        String encFilePath = "H:\\gits\\communityGrids\\code\\thx-web\\src\\main\\java\\com\\tenhisi\\web\\utils\\deviceCode.file";
        Path encPath = Paths.get(encFilePath);
        byte[] encBytes = Files.readAllBytes(encPath);
        byte[] decrypt = rsa.decrypt(encBytes, KeyType.PrivateKey);
        String str = StrUtil.str(decrypt, CharsetUtil.CHARSET_UTF_8);
        System.out.println(str);
        JSONArray jsonArray = JSONUtil.parseObj(str).getJSONArray("subs");
        List<Dict> list = JSONUtil.toList(jsonArray, Dict.class);
        for (Dict dict : list) {
            System.out.println(dict.get("id"));
            System.out.println(dict.get("name"));
        }

    }
}
