package tamasgyorfi.composite.util;

import java.io.File;

import tamasgyorfi.composite.pattern.DirectoryEntry;
import tamasgyorfi.composite.pattern.FileEntry;
import tamasgyorfi.composite.pattern.FileSystemEntry;

public class FileSystemEntryFactory {

	public FileSystemEntry getFileSystemEntry(File inputFile) {
		if (inputFile.isDirectory()) {
			return new DirectoryEntry(inputFile);
		}
		return new FileEntry(inputFile);
	}

}
