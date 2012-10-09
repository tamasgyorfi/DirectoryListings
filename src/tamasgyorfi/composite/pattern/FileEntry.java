package tamasgyorfi.composite.pattern;

import java.io.File;


public class FileEntry implements FileSystemEntry {

	private final File inputFile;

	public FileEntry(File inputFile) {
		this.inputFile = inputFile;
	}

	@Override
	public long getSize() {
		return inputFile.length();
	}

}
