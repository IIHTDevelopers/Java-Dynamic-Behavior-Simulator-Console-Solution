package com.streams.assessment;

import jdk.incubator.vector.IntVector;
import jdk.incubator.vector.VectorSpecies;

public class GameVectorOperations {
	private static final VectorSpecies<Integer> SPECIES = IntVector.SPECIES_PREFERRED;

	public int[] modifyAttributes(int[] currentAttributes, int[] modificationValues) {
		int[] result = new int[currentAttributes.length];
		for (int i = 0; i < SPECIES.loopBound(currentAttributes.length); i += SPECIES.length()) {
			var current = IntVector.fromArray(SPECIES, currentAttributes, i);
			var modification = IntVector.fromArray(SPECIES, modificationValues, i);
			var modified = current.add(modification);
			modified.intoArray(result, i);
		}
		return result;
	}
}
