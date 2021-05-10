package com.bridgelabz.addressbooksystemtest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.IntStream;

import org.junit.Assert;
import org.junit.Test;

public class FileIOTest {

	private static final String HOME = System.getProperty("user.home");
	private static final String PLAY_WITH_NIO = "TempPlayGround";

	@Test
	public void givenPath_WhenChecked_ShouldReturnTrue() throws IOException {

		Path homePath = Paths.get(HOME);
		Assert.assertTrue(Files.exists(homePath));

		Path playPath = Paths.get(HOME + "/" + PLAY_WITH_NIO);
		if (Files.exists(playPath))
			Files.delete(playPath);
		Assert.assertTrue(Files.notExists(playPath));

		Files.createDirectories(playPath);
		Assert.assertTrue(Files.exists(playPath));

		IntStream.range(0, 10).forEach(ctrl -> {
			Path temp = Paths.get(playPath + "file" + ctrl);
			try {

				Files.createFile(temp);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Assert.assertTrue(Files.exists(temp));
		});

	}

}
