//
// Triple Play - utilities for use in PlayN-based games
// Copyright (c) 2011-2014, Three Rings Design, Inc. - All rights reserved.
// http://github.com/threerings/tripleplay/blob/master/LICENSE

package tripleplay.util;

import playn.core.Font;
import playn.core.TextFormat;

/**
 * Describes everything needed to style a particular run of text.
 */
public class TextStyle extends TextFormat
{
    /** A default text style from which custom styles can be derived. */
    public static TextStyle DEFAULT = new TextStyle(
        null, true, 0xFF000000, EffectRenderer.NONE, false);

    /**
     * Creates a text style with the specified configuration using default anti-aliasing, no effect
     * and no underline.
     */
    public static TextStyle normal (Font font, int textColor) {
        return normal(font, textColor, EffectRenderer.NONE);
    }

    /**
     * Creates a text style with the specified configuration using default anti-aliasing (true)
     * and no underline.
     */
    public static TextStyle normal (Font font, int textColor, EffectRenderer effect) {
        return new TextStyle(font, true, textColor, effect, false);
    }

    /** The color used to render the text. */
    public final int textColor;

    /** The text effect used when rendering the text. */
    public final EffectRenderer effect;

    /** Whether or not the text is underlined. */
    public final boolean underlined;

    /**
     * Creates a text style with the specified configuration.
     */
    public TextStyle (Font font, boolean antialias, int textColor, EffectRenderer effect,
                      boolean underlined) {
        super(font, antialias);
        this.textColor = textColor;
        assert effect != null;
        this.effect = effect;
        this.underlined = underlined;
    }

    @Override public TextStyle withFont (Font font) {
        return new TextStyle(font, antialias, textColor, effect, underlined);
    }

    @Override public TextStyle withAntialias (boolean antialias) {
        return new TextStyle(font, antialias, textColor, effect, underlined);
    }

    /**
     * Returns a copy of this text style with the color configured as {@code textColor}.
     */
    public TextStyle withTextColor (int textColor) {
        return new TextStyle(font, antialias, textColor, effect, underlined);
    }

    /**
     * Returns a copy of this text style with the effect configured as {@code effect}.
     */
    public TextStyle withEffect (EffectRenderer effect) {
        return new TextStyle(font, antialias, textColor, effect, underlined);
    }

    /**
     * Returns a copy of this text style with a shadow text effect.
     */
    public TextStyle withShadow (int shadowColor, float shadowX, float shadowY) {
        return withEffect(new EffectRenderer.Shadow(shadowColor, shadowX, shadowY));
    }

    /**
     * Returns a copy of this text style with a pixel outline text effect.
     */
    public TextStyle withOutline (int outlineColor) {
        return withEffect(new EffectRenderer.PixelOutline(outlineColor));
    }

    /**
     * Returns a copy of this text style with a vector outline text effect.
     */
    public TextStyle withOutline (int outlineColor, float outlineWidth) {
        return withEffect(new EffectRenderer.VectorOutline(outlineColor, outlineWidth));
    }

    /**
     * Returns a copy of this text style with (or without) underlining.
     */
    public TextStyle withUnderline (boolean underlined) {
        return new TextStyle(font, antialias, textColor, effect, underlined);
    }

    @Override public int hashCode () {
        return super.hashCode() ^ textColor ^ effect.hashCode() ^ (underlined ? 1 : 0);
    }

    @Override public boolean equals (Object other) {
        if (!(other instanceof TextStyle)) return false;
        TextStyle os = (TextStyle)other;
        return super.equals(other) && textColor == os.textColor && effect.equals(os.effect) &&
            underlined == os.underlined;
    }
}
