/**
 * Project Name:trustsql_sdk
 * File Name:ECDSAAlgoUtil.java
 * Package Name:com.tencent.trustsql.sdk.algo
 * Date:Jul 26, 20175:17:04 PM
 * Copyright (c) 2017, Tencent All Rights Reserved.
 */

package com.hashwallet.foundation.utils;


import org.apache.tomcat.util.codec.binary.Base64;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;
import org.bouncycastle.math.ec.ECPoint;
import org.spongycastle.asn1.x9.X9ECParameters;
import org.spongycastle.crypto.ec.CustomNamedCurves;
import org.spongycastle.crypto.params.ECDomainParameters;
import org.spongycastle.math.ec.FixedPointUtil;

import java.math.BigInteger;
import java.security.SecureRandom;

public class ECDSAAlgorithm {
    public static final ECDomainParameters CURVE;
    public static final BigInteger HALF_CURVE_ORDER;

    static {
        X9ECParameters CURVE_PARAMS = CustomNamedCurves.getByName("secp256k1");
        // Tell Bouncy Castle to precompute data that's needed during secp256k1
        // calculations. Increasing the width
        // number makes calculations faster, but at a cost of extra memory usage
        // and with decreasing returns. 12 was
        // picked after consulting with the BC team.
        FixedPointUtil.precompute(CURVE_PARAMS.getG(), 12);
        CURVE = new ECDomainParameters(CURVE_PARAMS.getCurve(), CURVE_PARAMS.getG(), CURVE_PARAMS.getN(), CURVE_PARAMS.getH());
        HALF_CURVE_ORDER = CURVE_PARAMS.getN().shiftRight(1);
    }

    public static String generatePrivateKey() {
        SecureRandom secureRandom;
        try {
            secureRandom = SecureRandom.getInstance("SHA1PRNG", "SUN");
        } catch (Exception e) {
            secureRandom = new SecureRandom();
        }
        // Generate the key, skipping as many as desired.
        byte[] privateKeyAttempt = new byte[32];
        secureRandom.nextBytes(privateKeyAttempt);
        BigInteger privateKeyCheck = new BigInteger(1, privateKeyAttempt);
        BigInteger MAXPRIVATEKEY = new BigInteger("00FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEBAAEDCE6AF48A03BBFD25E8CD0364140", 16);
        while (privateKeyCheck.compareTo(BigInteger.ZERO) == 0 || privateKeyCheck.compareTo(MAXPRIVATEKEY) > 0) {
            secureRandom.nextBytes(privateKeyAttempt);
            privateKeyCheck = new BigInteger(1, privateKeyAttempt);
        }
        String result = Base64.encodeBase64String(privateKeyAttempt);
        result = result.replaceAll("[\\s*\t\n\r]", "");
        return result;
    }

    /**
     * 生成公钥，encode为true时为短公钥
     *
     * @param privateKeyBase64String 私钥
     * @param encode                 是否使用base64缩短
     * @return 公钥
     */
    public static String generatePublicKey(String privateKeyBase64String, boolean encode) {
        try {
            byte[] privateKeyBytes = Base64.decodeBase64(privateKeyBase64String);
            ECNamedCurveParameterSpec spec = ECNamedCurveTable.getParameterSpec("secp256k1");
            ECPoint pointQ = spec.getG().multiply(new BigInteger(1, privateKeyBytes));
            String result = Base64.encodeBase64String(pointQ.getEncoded(encode));
            result = result.replaceAll("[\\s*\t\n\r]", "");
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
