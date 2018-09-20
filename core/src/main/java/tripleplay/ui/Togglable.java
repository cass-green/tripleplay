//
// Triple Play - utilities for use in PlayN-based games
// Copyright (c) 2011-2018, Triple Play Authors - All rights reserved.
// http://github.com/threerings/tripleplay/blob/master/LICENSE

package tripleplay.ui;

import react.Value;

/**
 * Implemented by {@link Element}s that expose a selected state and can be clicked.
 */
public interface Togglable<T extends Element<?>> extends Clickable<T>
{
    /** A value that reflects the current selection state and is updated when said state changes. */
    Value<Boolean> selected ();
}
