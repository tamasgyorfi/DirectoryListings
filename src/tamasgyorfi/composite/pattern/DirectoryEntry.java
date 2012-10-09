package tamasgyorfi.composite.pattern;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import tamasgyorfi.composite.util.FileSystemEntryFactory;

public class DirectoryEntry implements FileSystemEntry {

	private final File inputFile;
	private List<String> contents;
	private FileSystemEntryFactory entryFactory = new FileSystemEntryFactory();

	public DirectoryEntry(File inputFile) {
		this.inputFile = inputFile;
	}

	@Override
	public long getSize() {
		long sum = 0;
		contents = new ArrayList<String>(Arrays.asList(inputFile.list()));
		for (String entry:contents) {
			sum += entryFactory.getFileSystemEntry(getFile(entry)).getSize();
		}
		return sum;
	}

	protected File getFile(String fileName) {
		return new File(inputFile.getAbsolutePath() +"\\"+ fileName);
	}
	
	protected void setFileSystemEntryFactory(FileSystemEntryFactory entryFactory) {
		this.entryFactory = entryFactory;
	}
}
