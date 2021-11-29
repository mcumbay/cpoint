package com.dfwcomputech.cpoint.java;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

public class Java10Tests {
	List<Integer> someIntList = Arrays.asList(1, 2, 3, 4);

	@Test
	public void whenModifyCopyOfList_thenThrowsException() {
		List<Integer> copyList = List.copyOf(someIntList);
		assertThrows(UnsupportedOperationException.class, () -> copyList.add(4));
	}

	@Test
	public void whenModifyToUnmodifiableList_thenThrowsException() {
		List<Integer> evenList = someIntList.stream().filter(i -> i % 2 == 0).collect(Collectors.toUnmodifiableList());

		assertThrows(UnsupportedOperationException.class, () -> evenList.add(4));
	}

	@Test
	public void whenListContainsInteger_OrElseThrowReturnsInteger() {
		Integer firstEven = someIntList.stream().filter(i -> i % 2 == 0).findFirst().orElseThrow();
		assertEquals(2, firstEven);

	}

}
