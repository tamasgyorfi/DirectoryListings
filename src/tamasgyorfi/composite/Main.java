package tamasgyorfi.composite;

import java.io.File;

import tamasgyorfi.composite.util.FileSystemEntryFactory;

public class Main {

	public static void main(String[] args) {
		Main main = new Main();
		main.run(args[0]);
	}

	private void run(String fileName) {
		DirectoryLister directoryLister = new DirectoryLister(new FileSystemEntryFactory());
		System.out.println("" + directoryLister.getSize(new File(fileName)));
	}
	
	
}
