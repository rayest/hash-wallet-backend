package com.hashwallet.address;

import com.hashwallet.foundation.Response;
import com.hashwallet.foundation.utils.BitcoinAddressGenerator;
import com.hashwallet.foundation.utils.PairKeyGenerator;
import com.hashwallet.keys.PairKey;
import org.springframework.stereotype.Service;

/***
 *  Created with IntelliJ IDEA.
 *  User:  lirui
 *  Date:  2018-06-04
 *  Time: 下午4:12
 *  Description:
 **/
@Service
public class AddressService {

    public Response generate() {

        PairKeyGenerator pairKeyGenerator = new PairKeyGenerator();
        PairKey pairKey = pairKeyGenerator.generate(true);
        String publicKey = pairKey.getPublicKey();
        BitcoinAddressGenerator bitcoinAddressGenerator = new BitcoinAddressGenerator();
        String bitcoinAddress = bitcoinAddressGenerator.generate(publicKey);

        return new Response(bitcoinAddress);
    }
}
