package com.hashwallet.foundation.utils;

import com.hashwallet.keys.PairKey;

public class PairKeyGenerator {


    public PairKey generate(boolean encodePubKey) {

        PairKey pair = new PairKey();
        String privateKey = ECDSAAlgorithm.generatePrivateKey();
        String pubKey = ECDSAAlgorithm.generatePublicKey(privateKey.trim(), encodePubKey);
        pair.setPrivateKey(privateKey);
        pair.setPublicKey(pubKey);
        return pair;
    }
}
