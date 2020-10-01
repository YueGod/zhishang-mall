package io.github.yuegod.zhishang.component.security.configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Calendar;
import java.util.HashMap;

/**
 * @author 屈子威
 * @date 2020/8/30 2:06
 * @description Jwt工具类
 */
@Slf4j
public class JwtUtils {

    private static final String jwtClaimKey = "jwtObjectKey";
    private static final String jwtSecretKey = "TUlJRW93SUJBQUtDQVFFQXZaY1lQR21NazRubzF2b3h6RXVOcXdXVGZuZVRRZzVQYW9RaDZjZkFiMWw3ZDNFbjB6NDZ3NDROcno2TER6SjBZNS8yR0RSdWtmOW5YRXozZDRlaHFQeiszNmdjeUZacTJ4NnQ5eUdBODYzTUpUUVhEQ1ZuSStVZUZkQ3lYOW5aQlhFQVNKTTlNUnM4b3RIWW9MOVNVUFh3MVhlVjVWbEVxZzB3Sko1c1RHSkg0RTk0ejJ4M05tWGlmQUdUc0pLZlQwbGFSVG0wMi90RDdDM0UrTDRhVlZLU2RnQlZ0RUpiZ1lObGhOVmFvRkJUVTc3YzVidmZYL2l2YUdzOUE1SHRVcTE1elZzbVliRk55Snl4SG5KOWliZ2ZXNmZBOHNHNTR2L210RGF4cUUxYWFmNHYrdDNCUlpVazZYdGdEQzRtOVNnQ1lvTjJ3cnFYMmRvdy9Hd1Jqd0lEQVFBQkFvSUJBSElXRXlwL1pXMS9CcE1lMllxVFNIdktqamdESXFVVVVXdktlVkhodHdZS2RjYXBYQXE5SHNXQW5QR0FsRk9meS9RTFZSNjVwblBLbWxzcm9aclhEa3ZQZWx3Q0lOZlpVTkhzdzBpOGE0bTNZdXBjVHlZcTk4YmMzOTdHeUIvdnlxMkFrMmdZMmpETHNCQldjS0pDbnZsREZocU9ySy9ucU84Zm9vSFdwMXJLbStEUTlkRGsyVHlWSGVPL3RsOWJES3d0bGoxdGdGeVZZUlUvdkxCVyt5Q004TkxudFVGL2liTGwzb0hmOVF5U1hCZXgxT3FYTGNOc08wdVVKa0wwYmZOWDY2dTAyQ1AwR1hNY2hqTitvUDNRcDZPWmVVMG9GenZBSktWN0JiZDk0UTRPWWg3ZHZSaXRUWUtaY0pqQitsZXVVc0lMV21xdDlmZ0hWL0xuTFVrQ2dZRUE2UHhENGxlSUI5YnBRRGNsNkd2QjBIamZtWjdhMkxRelFxODM4c3BwV2Ntem53VWIyM3EvTWc2Ry9EY0xZUXJidlg3UGNHZzFVSDRLait6NkRCdXh4cy9WU3BKNGdsdHdMZlhTcmdsR1BCTjhuMyt2Q05KUmJLU2ZqTFE2YlBNZHE2eGx3cGNzVm56eC9nOE10Y1FBKzg0MTBqY0Z1bVY2Q0Y4OTJaWkYrcE1DZ1lFQTBGRnpxT1JMTis4V0tIanI0amZBbGZwclJOQmlaSVRiTFE2b2t2eXVSWUxXYjh3TnhKbjB0czhPU3R4cnRUTklxUThRNEZiSTF0UTFHNTcrRXY0czZWVGFubUNyRUFrYjZjWmd0cXczRDRpcmtlZFRBSFpnRlhIbFdiL05DdHdmeE1ra0JlSUt1YitlUXpiMFBKby80ZXpkREtjNmxndW5QSmM4VUpUY0hwVUNnWUIxV1FJVUVjUlpObnk3TkV1cW1nTy9yZkhwWFNLYlBZQUlHVnJUdGZHRUlOeVF2ckQrUmYvaEJZSWxyTS9WYS9PcnFTQndYSHhsODJYOGdmNmJkS09CSUpNc2dVT2pYTXpPeVY3NCs0RGRwMjhBb3Y0Vk1tR0NtQlFvZVdwb0Q3ZWxEU3NmN0thUFZWVkpnbWtEY0JxU3VaVlFNUmg0ZnI0TlUzTkNrYmJqY3dLQmdEOGt3Vk1yUitWL2NnNzRnRCtFdUxwOU9uQ1VYWTZzZXFjUXIyWkdjeWljdlJVTUhBeEF3bDE3SzVEamVUNW1laHJNZDBnWTVpM0FDdzBCUjNCSVhTS28xczkwNjhVampiTnhyelVQeEd6UDkzbDFUY0p4SHpUQTNrMHpCeHJJbUZTVXgzZUZDYzA5Mk9IMDBWbGhSQVhlUHk2cnJlVjNTTXpqNXFOaVM4cDFBb0dCQU5rRHVTM2pwYmpsSUZWcGw4R3Y2NnlYVWlkeE44eWRpZUUvTHkvd2JBWlBWeXZ3dkxoczVoWTQrZTM5dG00TG9aTDlnZW5oSWMwWUoxVHdyRjNmNjJZclFJKzlCOTJTYkpseGJvWnR4c2RCTEVYTGx1ZGZrOU90L0tWaUIvNVZ3bWxENFk1N3VWc2tYQ0pYS3BIZUpoSys4NTBPVTFWK2JmTVNJNCtJMUFrYw==";

    /**
     * 生成jwt的token串
     *
     * @param value
     * @return
     */
    public static String createJwtToken(String value) {
        HashMap<String, Object> claims = new HashMap<>();
        claims.put(jwtClaimKey, value);
        Calendar calendar = Calendar.getInstance();
        //当前时间添加24是小时,即token在24小时后过期
        calendar.add(Calendar.HOUR_OF_DAY, 24);
        return Jwts.builder()
                //设置载荷部分
                .setClaims(claims)
                //设置过期时间
                .setExpiration(calendar.getTime())
                //设置加密算法
                .signWith(SignatureAlgorithm.HS512, jwtSecretKey)
                .compact();
    }

    /**
     * 从jwttoken串中获取载荷值
     *
     * @param tokenStr
     * @return
     */
    public static String getJwtTokenClaimValue(String tokenStr) {
        String result = null;
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(jwtSecretKey)
                    .parseClaimsJws(tokenStr)
                    .getBody();

            if (claims.getExpiration().compareTo(Calendar.getInstance().getTime()) > 0) {
                //token未过期
                result = claims.get(jwtClaimKey, String.class);
            }
        } catch (Exception ex) {
           log.info("jwt exception:{}",ex);
        }
        return result;
    }

}
