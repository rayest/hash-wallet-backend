package com.hashwallet.keys;

import com.hashwallet.foundation.utils.PairKeyGenerator;
import org.springframework.stereotype.Service;

@Service
public class PairKeyService {

    public PairKey generate()  {
        PairKeyGenerator pairKeyGenerator = new PairKeyGenerator();
        return pairKeyGenerator.generate(true);
    }
}
