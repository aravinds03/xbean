package com.xbean.converters;

import java.util.List;
import java.util.Set;

public interface BeanConverter {

	<S, D> D convert(S pSource, Class<D> pDestination) throws Exception;

	<S, D> List<D> convertToList(List<S> pSourceList, Class<D> pDestination) throws Exception;

	<S, D> Set<D> convertToSet(Set<S> pSourceSet, Class<D> pDestination) throws Exception;
}
