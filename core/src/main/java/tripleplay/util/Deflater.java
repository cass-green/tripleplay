//
// Triple Play - utilities for use in PlayN-based games
// Copyright (c) 2011-2018, Triple Play Authors - All rights reserved.
// http://github.com/threerings/tripleplay/blob/master/LICENSE

package tripleplay.util;

/**
 * Encodes typed data into a string. This is the deflating counterpart to {@link Inflater}.
 */
public class Deflater extends Conflater
{
    public Deflater addBool (boolean value) {
        addChar(value ? 't' : 'f');
        return this;
    }

    public Deflater addChar (char c) {
        _buf.append(c);
        return this;
    }

    public Deflater addNibble (int value) {
        check(value, 0, 0xF, "Nibble");
        _buf.append(toHexString(value, 1));
        return this;
    }

    public Deflater addByte (int value) {
        check(value, Byte.MIN_VALUE, Byte.MAX_VALUE, "Byte");
        _buf.append(toHexString(value, 2));
        return this;
    }

    public Deflater addShort (int value) {
        check(value, Short.MIN_VALUE, Short.MAX_VALUE, "Short");
        _buf.append(toHexString(value, 4));
        return this;
    }

    public Deflater addInt (int value) {
        _buf.append(toHexString(value, 8));
        return this;
    }

    public Deflater addVarInt (int value) {
        assert value > Integer.MIN_VALUE : "Can't use varint for Int.MIN_VALUE";
        if (value < 0) {
            _buf.append(NEG_MARKER);
            value *= -1;
        }
        addVarInt(value, false);
        return this;
    }

    public Deflater addVarLong (long value) {
        assert value > Long.MIN_VALUE : "Can't use varlong for Long.MIN_VALUE";
        if (value < 0) {
            _buf.append(NEG_MARKER);
            value *= -1;
        }
        addVarLong(value, false);
        return this;
    }

    public Deflater addBitVec (BitVec value) {
        // find the index of the highest non-zero word
        int[] words = value._words;
        int wc = 0;
        for (int ii = words.length-1; ii >= 0; ii--) {
            if (words[ii] == 0) continue;
            wc = ii+1;
            break;
        }
        // now write out the number of words and their contents
        addVarInt(wc);
        for (int ii = 0; ii < wc; ii++) addInt(words[ii]);
        return this;
    }

    public Deflater addFLString (String value) {
        _buf.append(value);
        return this;
    }

    public Deflater addString (String value) {
        addShort(value.length());
        _buf.append(value);
        return this;
    }

    public <E extends Enum<E>> Deflater addEnum (E value) {
        return addString(value.name());
    }

    public String encoded () {
        return _buf.toString();
    }

    public Deflater reset () {
        _buf.setLength(0);
        return this;
    }

    protected void addVarInt (int value, boolean cont) {
        if (value >= BASE) addVarInt(value / BASE, true);
        _buf.append((cont ? VARCONT : VARABS).charAt(value % BASE));
    }

    protected void addVarLong (long value, boolean cont) {
        if (value >= BASE) addVarLong(value / BASE, true);
        _buf.append((cont ? VARCONT : VARABS).charAt((int)(value % BASE)));
    }

    protected final void check (int value, int min, int max, String type) {
        assert value >= min && value <= max : type + " must be " + min + " <= n <= " + max;
    }

    protected final StringBuilder _buf = new StringBuilder();
}
