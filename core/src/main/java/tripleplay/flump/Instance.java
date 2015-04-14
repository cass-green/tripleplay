//
// Triple Play - utilities for use in PlayN-based games
// Copyright (c) 2011-2014, Three Rings Design, Inc. - All rights reserved.
// http://github.com/threerings/tripleplay/blob/master/LICENSE

package tripleplay.flump;

import playn.core.Disposable;
import playn.scene.Layer;

/** A created instance of a Flump symbol. */
public interface Instance extends Disposable {

    /** The layer that displays this instance. */
    Layer layer ();

    /**
     * Notifies this instance that time has passed, for animation.
     * @param dt The time since the last update, in milliseconds.
     */
    void paint (float dt);
}
