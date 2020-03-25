package mm.aeon.com.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

public class FileHandler {
	public static String getFileName(String filePath) {
		int lastIndex = filePath.lastIndexOf("/") + 1;
		return filePath.substring(lastIndex, filePath.length());
	}

	public static void forceMakeDirectory(String fullFilePath) throws IOException {
		File file = new File(fullFilePath);
		String filePath = file.getAbsolutePath();
		int lastIndex = filePath.lastIndexOf(File.separator);
		forceMakeDirectory(new File(filePath.substring(0, lastIndex)));
	}

	public static void forceMakeDirectory(File file) throws IOException {
		FileUtils.forceMkdir(file);
	}

	public static void setPermission(String filePath) throws IOException {
		String OS = System.getProperty("os.name").toLowerCase();
		if (!isWindows(OS)) {
			Path path = Paths.get(filePath);
			Set<PosixFilePermission> perms = Files.readAttributes(path, PosixFileAttributes.class).permissions();
			perms.add(PosixFilePermission.OWNER_WRITE);
			perms.add(PosixFilePermission.OWNER_READ);
			perms.add(PosixFilePermission.OWNER_EXECUTE);
			perms.add(PosixFilePermission.GROUP_WRITE);
			perms.add(PosixFilePermission.GROUP_READ);
			perms.add(PosixFilePermission.GROUP_EXECUTE);
			perms.add(PosixFilePermission.OTHERS_WRITE);
			perms.add(PosixFilePermission.OTHERS_READ);
			perms.add(PosixFilePermission.OTHERS_EXECUTE);
			Files.setPosixFilePermissions(path, perms);
		}
	}

	public static boolean isWindows(String OS) {

		return (OS.indexOf("win") >= 0);

	}

	public static void createFile(File file, byte[] content) throws IOException {
		file.setExecutable(true, false);
		file.setReadable(true, false);
		file.setWritable(true, false);
		/* At First : Create directory of target file */
		String filePath = file.getAbsolutePath();
		int lastIndex = filePath.lastIndexOf(File.separator);

		File filePathFile = new File(filePath.substring(0, lastIndex));
		filePathFile.setExecutable(true, false);
		filePathFile.setReadable(true, false);
		filePathFile.setWritable(true, false);
		FileUtils.forceMkdir(filePathFile);
		/* Create target file */
		FileOutputStream outputStream = new FileOutputStream(file);

		IOUtils.write(content, outputStream);
		outputStream.flush();
		outputStream.close();
		setPermission(filePath);
	}

	public static void forceDelete(String systemPath, String filePath) throws IOException {
		forceDelete(systemPath + filePath);
	}

	public static void forceDelete(String fullFilePath) throws IOException {
		forceDelete(new File(fullFilePath));
	}

	public static void forceDeleteFolder(String fullFilePath) throws IOException {
		File file = new File(fullFilePath);
		String filePath = file.getAbsolutePath();
		int lastIndex = filePath.lastIndexOf(File.separator);
		forceDelete(new File(filePath.substring(0, lastIndex)));
	}

	public static void forceDelete(File file) throws IOException {
		FileUtils.forceDelete(file);
	}

	public static void moveFiles(String systemPath, String sourceDirectoryPath, String destinationDirectoryPath) throws IOException {
		File sourceDir = new File(systemPath + sourceDirectoryPath);
		File targetDir = new File(systemPath + destinationDirectoryPath);
		FileUtils.copyDirectory(sourceDir, targetDir);
		FileUtils.deleteDirectory(sourceDir);
	}

	public static void renameFile(String srcPath, String renamePath) {
		File srcFile = new File(srcPath);
		File renameFile = new File(renamePath);
		srcFile.renameTo(renameFile);
	}

	public static void copyDirectory(String srcPath, String destPath) throws IOException {
		File srcFile = new File(srcPath);
		File destFile = new File(destPath);
		FileUtils.copyDirectory(srcFile, destFile);
	}
}
