package tamasgyorfi.composite.pattern;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import java.io.File;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import tamasgyorfi.composite.util.FileSystemEntryFactory;

@RunWith(MockitoJUnitRunner.class)
public class DirectoryEntryTest {

	private static final String[] OTHER_DIRECTORY_CONTENT = new String[] { "file", "file" };
	private static final String[] DIRECTORY_CONTENT = new String[] {
			"directory", "file1", "file2" };
	@Mock
	private FileSystemEntryFactory entryFactory;
	@Mock
	private File directory;
	@Mock
	private File otherDirectory;
	@Mock
	private File textFile;

	private DirectoryEntry directoryEntry;
	private DirectoryEntry subDirectoryEntry;

	@Before
	public void setup() {
		directoryEntry = new DirectoryEntry(directory) {
			@Override
			protected File getFile(String fileName) {
				if (fileName.equals("directory")) {
					return otherDirectory;
				}
				return textFile;
			}
		};
		
		subDirectoryEntry = new DirectoryEntry(otherDirectory) {
			@Override
			protected File getFile(String fileName) {
				return textFile;
			}
		};
	}

	@Test
	public void lengthOfDirectoryStructure() {
		directoryEntry.setFileSystemEntryFactory(entryFactory);

		when(directory.isDirectory()).thenReturn(true);
		when(directory.list()).thenReturn(DIRECTORY_CONTENT);

		when(entryFactory.getFileSystemEntry(otherDirectory)).thenReturn(subDirectoryEntry);
		when(otherDirectory.isDirectory()).thenReturn(true);
		when(otherDirectory.list()).thenReturn(OTHER_DIRECTORY_CONTENT);
		
		when(entryFactory.getFileSystemEntry(textFile)).thenReturn(new FileEntry(textFile));
		when(textFile.isDirectory()).thenReturn(false);
		when(textFile.length()).thenReturn(1l);

		assertEquals(4l, directoryEntry.getSize());
		
		verify(textFile, times(4)).length();
		verify(directory, times(1)).list();
		verify(otherDirectory, times(1)).list();
	}
}
