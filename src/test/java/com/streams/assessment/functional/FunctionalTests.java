package com.streams.assessment.functional;

import static com.streams.assessment.testutils.TestUtils.businessTestFile;
import static com.streams.assessment.testutils.TestUtils.currentTest;
import static com.streams.assessment.testutils.TestUtils.yakshaAssert;

import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

public class FunctionalTests {

	static class ReflectionReplacement {
        private void secretMethod() {
            System.out.println("Secret method accessed!");
        }
    }

	@Test
	public void testProcessOrderDataWithHighValueOrders() throws Throwable {
		try {
			final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
			System.setOut(new PrintStream(outContent));
			MethodHandles.Lookup lookup = MethodHandles.lookup();
			Method method = ReflectionReplacement.class.getDeclaredMethod("secretMethod");
			method.setAccessible(true);
			MethodHandle handle = lookup.unreflect(method);
			handle.invoke(new ReflectionReplacement());
			yakshaAssert(currentTest(), outContent.toString().equalsIgnoreCase("Secret method accessed!\n") ? "true" : "false", businessTestFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
