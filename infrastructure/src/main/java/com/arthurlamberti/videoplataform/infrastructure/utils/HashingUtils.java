package com.arthurlamberti.videoplataform.infrastructure.utils;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class HashingUtils {
    public static final HashFunction CHECKSUM = Hashing.crc32c();

    public static String checksum(final byte[] bytes) {
        return CHECKSUM.hashBytes(bytes).toString();
    }

}
