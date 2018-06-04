package com.hashwallet.foundation.utils;

import com.hashwallet.foundation.Utils;
import org.bouncycastle.crypto.digests.RIPEMD160Digest;

import java.math.BigInteger;

/***
 *  Created with IntelliJ IDEA.
 *  User:  lirui
 *  Date:  2018-06-04
 *  Time: 下午3:28
 *  Description:
 **/
public class BitcoinAddressGenerator {

    public String generate(String publicKey) {

        byte[] sha256Bytes = Utils.sha256(publicKey.getBytes());
        System.out.println("sha256加密=" + Utils.bytesToHexString(sha256Bytes));

        RIPEMD160Digest digest = new RIPEMD160Digest();
        digest.update(sha256Bytes, 0, sha256Bytes.length);
        byte[] ripemd160Bytes = new byte[digest.getDigestSize()];
        digest.doFinal(ripemd160Bytes, 0);

        System.out.println("ripemd160加密=" + Utils.bytesToHexString(ripemd160Bytes));

        byte[] networkID = new BigInteger("00", 16).toByteArray();
        byte[] extendedRipemd160Bytes = Utils.add(networkID, ripemd160Bytes);

        System.out.println("添加NetworkID=" + Utils.bytesToHexString(extendedRipemd160Bytes));

        byte[] twiceSha256Bytes = Utils.sha256(Utils.sha256(extendedRipemd160Bytes));

        System.out.println("两次sha256加密=" + Utils.bytesToHexString(twiceSha256Bytes));

        byte[] checksum = new byte[4];
        System.arraycopy(twiceSha256Bytes, 0, checksum, 0, 4);

        System.out.println("checksum=" + Utils.bytesToHexString(checksum));

        byte[] binaryBitcoinAddressBytes = Utils.add(extendedRipemd160Bytes, checksum);

        System.out.println("添加checksum之后=" + Utils.bytesToHexString(binaryBitcoinAddressBytes));

        String bitcoinAddress = Base58.encode(binaryBitcoinAddressBytes);
        System.out.println("bitcoinAddress=" + bitcoinAddress);
        return bitcoinAddress;
    }


}
