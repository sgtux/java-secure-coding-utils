package org.securecoding.utils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SecurePathUtilsTests {

	@Test
	public void testValidPathWithoutManipulation() {
		assertFalse(SecurePathUtils.isPathManipulationPayload("/caminho/valido/arquivo.txt"), "");
	}

	@Test
	public void testPathWithDoubleDotManipulation() {
		assertTrue(SecurePathUtils.isPathManipulationPayload("/caminho/../arquivo.txt"));
	}

	@Test
	public void testPathWithDoubleSlashManipulation() {
		assertTrue(SecurePathUtils.isPathManipulationPayload("/caminho//arquivo.txt"));
	}

	@Test
	public void testPathWithSpecialCharactersButWithoutManipulation() {
		assertFalse(SecurePathUtils.isPathManipulationPayload("/caminho/vali&do/#arquivo.txt"));
	}

	@Test
	public void testPathWithDoubleDotAndDoubleSlashManipulation() {
		assertTrue(SecurePathUtils.isPathManipulationPayload("/caminho/..//arquivo.txt"));
	}

	@Test
	public void SafePath_1() {
		String safePath = "/usr/local/templates/template_DI.htm";
		boolean isTrue = SecurePathUtils.isSecurePath(safePath);
		assertTrue(isTrue);
	}

	@Test
	public void SafePath_2() {
		String safePath = "/usr/local/templates/";
		boolean isTrue = SecurePathUtils.isSecurePath(safePath);
		assertTrue(isTrue);
	}

	@Test
	public void SafePath_3() {
		String safePath = "/usr/local/templates/";
		boolean isTrue = SecurePathUtils.isSecurePath(safePath);
		assertTrue(isTrue);
	}

	@Test
	public void SafePath_4() {
		String safePath = "/nfs/local/staTic-reports/ReporT-2.html";
		boolean isTrue = SecurePathUtils.isSecurePath(safePath);
		assertTrue(isTrue);
	}	

	@Test
	public void UnsafePath_1() {
		String unsafePath = "/usr/local/templates/../file.conf";
		boolean isFalse = SecurePathUtils.isSecurePath(unsafePath);
		assertFalse(isFalse);
	}

	@Test
	public void UnsafePath_2() {
		String unsafePath = "/usr/local/templates//file.conf";
		boolean isFalse = SecurePathUtils.isSecurePath(unsafePath);
		assertFalse(isFalse);
	}

	@Test
	public void UnsafePath_3() {
		String unsafePath = "/etc/passwd";
		boolean isFalse = SecurePathUtils.isSecurePath(unsafePath);
		assertFalse(isFalse);
	}

	@Test
	public void UnsafePath_4() {
		String unsafePath = "/usr/local/staTic-reports/ReporT-2.html";
		boolean isFalse = SecurePathUtils.isSecurePath(unsafePath);
		assertFalse(isFalse);
	}
}