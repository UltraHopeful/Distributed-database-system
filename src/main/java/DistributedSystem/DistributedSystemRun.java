package DistributedSystem;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class DistributedSystemRun {
	public static void main(String[] args) {
		
		ChannelSftp channel = connectVM("sarth", "35.193.195.113");
		System.out.println(isFileAvailable("/home/sarthakpatel0301/uploadFile", channel, "uploadFile"));
		System.out.println(getFileData("/home/sarthakpatel0301/uploadHere/uploaded2.txt", channel));
		uploadFileData("/home/sarthakpatel0301/uploadHere/uploaded2.txt", channel, "hellooo");
		
	}

	/*
	 * vm1 ip - 35.193.195.113 vm1 
	 * userName - sarthakpatel0301
	 */
	public static ChannelSftp connectVM(String userName, String ip) {
		Session session = null;
		ChannelSftp channel = null;
		try {
			JSch jsch = new JSch();
			// jsch.setKnownHosts("/home/sarthakpatel0301/.ssh/authorized_keys");
			// jsch.addIdentity("/home/sarthakpatel0301/.ssh/id_rsa");
			jsch.setKnownHosts("C://Users/sarth/.ssh/known_host");
			jsch.addIdentity("C://Users/sarth/.ssh/id_rsa/sarth");
			session = jsch.getSession(userName, ip, 22);
			session.setConfig("StrictHostKeyChecking", "no");
			session.connect();
			channel = (ChannelSftp) session.openChannel("sftp");
			channel.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return channel;
	}

	public static String getFileData(String path, ChannelSftp channel) {
		String data = "";
		try {
			InputStream inputStream = channel.get(path);
			byte[] bytes = inputStream.readAllBytes();
			data = new String(bytes);
		} catch (SftpException | IOException e) {
			e.printStackTrace();
		}
		return data;
	}

	public static void uploadFileData(String path, ChannelSftp channel, String data) {
		try {
			String upload = getFileData(path, channel) + data;
			channel.put(new ByteArrayInputStream(upload.getBytes()), path);
		} catch (SftpException e) {
			e.printStackTrace();
		}
	}

	public static boolean isFileAvailable(String path, ChannelSftp channel, String name) {
		boolean status = false;
		Vector<?> filelist;
		try {
			filelist = channel.ls("/home/sarthakpatel0301/uploadHere");

			for (int i = 0; i < filelist.size(); i++) {
				LsEntry entry = (LsEntry) filelist.get(i);
				if (entry.getFilename().equals(name)) {
					status = true;
				}
				System.out.println(entry.getFilename());
			}
		} catch (SftpException e) {
			e.printStackTrace();
		}
		return status;
	}

}
