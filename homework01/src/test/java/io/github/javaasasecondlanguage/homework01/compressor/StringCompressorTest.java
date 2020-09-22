package io.github.javaasasecondlanguage.homework01.compressor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StringCompressorTest {
    StringCompressor compressor;

    @BeforeEach
    void setup() {
        compressor = new StringCompressor();
    }

    @Test
    void nullIsIllegalArgument()
            throws IllegalArgumentException {
        assertThrows(
                IllegalArgumentException.class,
                () -> compressor.compress(null)
        );
    }

    @Test
    void stringWithIllegalCharacters()
            throws IllegalArgumentException {
        assertThrows(
                IllegalArgumentException.class,
                () -> compressor.compress("Што такое?!!1адинадин".toCharArray())
        );
    }

    @Test
    void noCompressionForSingleChar()
            throws IllegalArgumentException {
        var subject = "a".toCharArray();
        assertArrayEquals(subject, compressor.compress(subject));
    }

    @Test
    void noCompressionForSimpleString()
            throws IllegalArgumentException {
        var subject = "abc".toCharArray();
        assertArrayEquals(subject, compressor.compress(subject));
    }

    @Test
    void sample1()
            throws IllegalArgumentException {
        var subject = "aabbccc".toCharArray();
        assertArrayEquals("a2b2c3".toCharArray(), compressor.compress(subject));
    }

    @Test
    void sample2()
            throws IllegalArgumentException {
        var subject = "mcobtwwwwaokzngssaox".toCharArray();
        assertArrayEquals("mcobtw4aokzngs2aox".toCharArray(), compressor.compress(subject));
    }

    @Test
    void maxTest500()
            throws IllegalArgumentException {

        var subject = ("ubejrewmdnujlnrsqfrtrwnscangpjkbocajvsqdevtxifpbddds"
                + "xlrggcqhyevuxhrwxfqvjkhwmqoydt"
                + "yuocfvljxiiwsdhuxhryuwnjvckjjowffdusiourt"
                + "tfiiocudlukbyqfnnavbetjtfmlzbcppdjbjhts"
                + "pcaeotbnucazkazzysaxlpsrkpbzxwygksyozmidr"
                + "pvcdjbvpjkkcgcobhsojawgeozwohwzeglpkveh"
                + "wehepadbzlueayltfdcrayscrcokggdglanvcustf"
                + "olobekqteqpmabghvkedjaupghhiikdjdizarky"
                + "ndxzlxcmuivxlayajsclfxyzumczqfzsvysquenft"
                + "inkburtsassumpkghajevjgiqzebrgnhtggfwcf"
                + "qwmokceesaynxetpmaqodzwiulnhnevtlappgpbwx"
                + "pmyxpnhwmjmzzzpjjzijilltyrnwqosfrqzaytr"
                + "lbgzzoxafianxvwcbl"
        ).toCharArray();

        var result = ("ubejrewmdnujlnrsqfrtrwnscangpjkbocajvsqdevtxifpbd3s"
                + "xlrg2cqhyevuxhrwxfqvjkhwmqoydty"
                + "uocfvljxi2wsdhuxhryuwnjvckj2owf2dusiourt2"
                + "fi2ocudlukbyqfn2avbetjtfmlzbcp2djbjhtsp"
                + "caeotbnucazkaz2ysaxlpsrkpbzxwygksyozmidrpv"
                + "cdjbvpjk2cgcobhsojawgeozwohwzeglpkvehw"
                + "ehepadbzlueayltfdcrayscrcokg2dglanvcustfol"
                + "obekqteqpmabghvkedjaupgh2i2kdjdizarkyn"
                + "dxzlxcmuivxlayajsclfxyzumczqfzsvysquenftin"
                + "kburtsas2umpkghajevjgiqzebrgnhtg2fwcfq"
                + "wmokce2saynxetpmaqodzwiulnhnevtlap2gpbwxpm"
                + "yxpnhwmjmz3pj2zijil2tyrnwqosfrqzaytrlb"
                + "gz2oxafianxvwcbl"
        ).toCharArray();

        assertArrayEquals(result, compressor.compress(subject));
    }

    @Test
    void emptyArrayIsAlreadyCompressed()
            throws IllegalArgumentException {
        var emptyCharArray = new char[]{};
        var result = compressor.compress(emptyCharArray);
        assertArrayEquals(emptyCharArray, result);
    }
}