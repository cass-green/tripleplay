//
// Triple Play - utilities for use in PlayN-based games
// Copyright (c) 2011-2018, Triple Play Authors - All rights reserved.
// http://github.com/threerings/tripleplay/blob/master/LICENSE

package tripleplay.util;

import pythagoras.f.IPoint;
import pythagoras.f.IRectangle;

import playn.scene.ImageLayer;

/**
 * Models the frames of a flipbook animation. The image frames may be trimmed, in which case the
 * image for a given frame may have an offset within the logical bounds of the entire flipbook.
 */
public interface Frames
{
    /** Returns the width of a logical frame. */
    float width ();

    /** Returns the height of a logical frame. */
    float height ();

    /** Returns the number of frames available. */
    int count ();

    /** Returns the bounds for the specified frame. */
    IRectangle bounds (int index);

    /** Returns the offset (into the logical bounds) of the specified frame. */
    IPoint offset (int index);

    /** Configures the supplied image layer with the specified frame. The layer's image will be
      * updated and the layer's translation will be adjusted to the requested frame's offset */
    void apply (int index, ImageLayer layer);
}
