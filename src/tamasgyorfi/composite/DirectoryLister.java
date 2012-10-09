package tamasgyorfi.composite;

import java.io.File;

import tamasgyorfi.composite.pattern.FileSystemEntry;
import tamasgyorfi.composite.util.FileSystemEntryFactory;

public class DirectoryLister {

	private final FileSystemEntryFactory entryFactory;

	public DirectoryLister(FileSystemEntryFactory entryFactory) {
		this.entryFactory = entryFactory;
	}

	public long getSize(File inputFile) {
		FileSystemEntry fileSystemEntry = entryFactory.getFileSystemEntry(inputFile);
		return fileSystemEntry.getSize();
	}

}
