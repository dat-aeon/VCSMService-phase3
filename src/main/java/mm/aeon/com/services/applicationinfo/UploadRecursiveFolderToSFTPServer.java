package mm.aeon.com.services.applicationinfo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;

public class UploadRecursiveFolderToSFTPServer {
	static ChannelSftp channelSftp = null;
	static Session session = null;
	static Channel channel = null;
	static String PATHSEPARATOR = "/";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String SFTPHOST = "10.1.9.69"; // SFTP Host Name or SFTP Host IP
										// Address
		int SFTPPORT = 22; // SFTP Port Number
		String SFTPUSER = "root"; // User Name
		String SFTPPASS = "123@dat"; // Password
		String SFTPWORKINGDIR = "/photoImage/kodehelp/upload"; // Source
																// Directory on
																// SFTP server
		String LOCALDIRECTORY = "C:\\temp"; // Local Target Directory

		try {
			JSch jsch = new JSch();
			session = jsch.getSession(SFTPUSER, SFTPHOST, SFTPPORT);
			session.setPassword(SFTPPASS);
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect(); // Create SFTP Session
			channel = session.openChannel("sftp"); // Open SFTP Channel
			channel.connect();
			channelSftp = (ChannelSftp) channel;
			channelSftp.cd(SFTPWORKINGDIR); // Change Directory on SFTP Server

			recursiveFolderUpload(LOCALDIRECTORY, SFTPWORKINGDIR);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (channelSftp != null)
				channelSftp.disconnect();
			if (channel != null)
				channel.disconnect();
			if (session != null)
				session.disconnect();

		}

	}

	/**
	 * This method is called recursively to Upload the local folder content to
	 * SFTP server
	 * 
	 * @param sourcePath
	 * @param destinationPath
	 * @throws SftpException
	 * @throws FileNotFoundException
	 */
	private static void recursiveFolderUpload(String sourcePath, String destinationPath) throws SftpException, FileNotFoundException {

		File sourceFile = new File(sourcePath);
		if (sourceFile.isFile()) {

			// copy if it is a file
			channelSftp.cd(destinationPath);
			if (!sourceFile.getName().startsWith("."))
				channelSftp.put(new FileInputStream(sourceFile), sourceFile.getName(), ChannelSftp.OVERWRITE);

		} else {

			System.out.println("inside else " + sourceFile.getName());
			File[] files = sourceFile.listFiles();

			if (files != null && !sourceFile.getName().startsWith(".")) {

				channelSftp.cd(destinationPath);
				SftpATTRS attrs = null;

				// check if the directory is already existing
				try {
					attrs = channelSftp.stat(destinationPath + "/" + sourceFile.getName());
				} catch (Exception e) {
					System.out.println(destinationPath + "/" + sourceFile.getName() + " not found");
				}

				// else create a directory
				if (attrs != null) {
					System.out.println("Directory exists IsDir=" + attrs.isDir());
				} else {
					System.out.println("Creating dir " + sourceFile.getName());
					channelSftp.mkdir(sourceFile.getName());
				}

				for (File f : files) {
					recursiveFolderUpload(f.getAbsolutePath(), destinationPath + "/" + sourceFile.getName());
				}

			}
		}

	}

}
