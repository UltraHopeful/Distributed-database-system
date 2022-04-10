package DistributedSystem;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Vector;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class DistributedSystemRun {
	public static void main(String[] args)  {
		Session session = null;
		ChannelSftp channel = null;
		try {
		JSch jsch = new JSch();
		jsch.setKnownHosts("/home/sarthakpatel0301/.ssh/authorized_keys");
		jsch.addIdentity("/home/sarthakpatel0301/.ssh/id_rsa");
		session = jsch.getSession("sarthakpatel0301", "35.193.195.113", 22);

        session.setConfig("StrictHostKeyChecking", "no");
		session.connect();
		channel = (ChannelSftp) session.openChannel("sftp");
		channel.connect();
		
		Vector<?> v = channel.ls("/home/sarthakpatel0301");
		for (Iterator<?> iterator = v.iterator(); iterator.hasNext();) {
			Object object = (Object) iterator.next();
			System.out.println("object - " + object);
		}
		
		InputStream inputStream = channel.get("/home/sarthakpatel0301/uploadHere/uploaded2.txt");
		byte[] bytes = inputStream.readAllBytes();
		String download = new String(bytes);
		System.out.println(download);

		String upload = download + "we are done with distributiondddfdg";
		channel.put(new ByteArrayInputStream(upload.getBytes()),"/home/sarthakpatel0301/uploadHere/uploaded2.txt");
		
		System.out.println("done");
		}
		catch(Exception e) {
			System.err.println(e);
		}finally {
			channel.disconnect();
			session.disconnect();
		}
		
	}
}
