package edu.cwru.jfh86_jlk187.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

// made to ensure classes do not create duplicate objects
public final class Cache<T, V> {

	private Map<T, V> cache = new HashMap<T, V>();
	
	// If key present in cache -> returns  the corresponding cached  object,
	// otherwise  creates  a  new  object  with  the  provided constructor.
	// Throws NullPointerException with an appropriate error message if key or constructor are null.

	V get(T key, Function<? super T, ? extends V> constructor)
	{
		return cache.computeIfAbsent(Objects.requireNonNull(key, "Cannot pass in null key."), Objects.requireNonNull(constructor, "Cannot pass in null constructor."));
	}
	// cache.getIfAbsent
}
