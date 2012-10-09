package tamasgyorfi.composite;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import org.mockito.runners.MockitoJUnitRunner;

import tamasgyorfi.composite.pattern.DirectoryEntry;
import tamasgyorfi.composite.util.FileSystemEntryFactory;

@RunWith(MockitoJUnitRunner.class)
public class DirectoryListerTest {
	private static final String[] DIRECTORY_CONTENTS = new String[]{"file1", "file2", "directory"};  
	
	private DirectoryLister lister;
	@Mock
	private File textFile;
	@Mock
	private File directory;
	@Mock
	private FileSystemEntryFactory entryFactory;

	@Test
	public void returnValueForFileShouldBeFilesSize() {
		
		lister = new DirectoryLister(new FileSystemEntryFactory());
		
		when(textFile.length()).thenReturn(101l);
		when(textFile.isDirectory()).thenReturn(false);
		
		assertEquals(101l, lister.getSize(textFile));
		verify(textFile, times(1)).length();
		verify(textFile, times(1)).isDirectory();
	}
	
	@Test
	public void sizeOfAnEmptyDirectoryShouldBeZero() {

		lister = new DirectoryLister(new FileSystemEntryFactory());

		when(directory.isDirectory()).thenReturn(true);
		when(directory.list()).thenReturn(new String[]{});
		
		assertEquals(0l, lister.getSize(directory));
		verify(directory, times(1)).isDirectory();
		verify(directory, times(1)).list();
	}
	
	@Test
	public void sizeOfFlatDirectoryIsTheSumOfFilesLength() {
		DirectoryEntry directoryEntry = new DirectoryEntry(directory){
			@Override
			protected File getFile(String fileName) {
				return textFile;
			}
		};
		lister = new DirectoryLister(entryFactory);
		
		when(directory.list()).thenReturn(DIRECTORY_CONTENTS);
		when(entryFactory.getFileSystemEntry(directory)).thenReturn(directoryEntry);
		when(textFile.length()).thenReturn(1l);
		
		assertEquals(3l, lister.getSize(directory));
		
		verify(directory, times(1)).list();
		verify(textFile, times(3)).length();
	}
}
