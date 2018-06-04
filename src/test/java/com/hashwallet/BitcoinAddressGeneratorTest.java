package com.hashwallet;

import com.hashwallet.foundation.utils.BitcoinAddressGenerator;
import org.junit.Test;

/***
 *  Created with IntelliJ IDEA.
 *  User:  lirui
 *  Date:  2018-06-04
 *  Time: 下午3:56
 *  Description:
 **/
public class BitcoinAddressGeneratorTest {

    @Test
    public void testGenerateBitcoinAddress() {
        String publicKey = "Awv5Tn6aD1OEaFjnn3yKpwJIPrsi7+vRVWQTbOO+lMnn";
        BitcoinAddressGenerator bitcoinAddressGenerator = new BitcoinAddressGenerator();
        String bitcoinAddress = bitcoinAddressGenerator.generate(publicKey);
        System.out.println(bitcoinAddress);
    }
}
