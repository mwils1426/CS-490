import com.jcraft.jsch.*;
//send and get functions 
public class SSHClient {
    private static final String REMOTE_HOST = "192.168.122.232";
    private static final String USERNAME = "ubuntu";
    private static final String PASSWORD = "1q3e5t7u";
    private static final int REMOTE_PORT = 22;
    private static final int SESSION_TIMEOUT = 10000;
    private static final int CHANNEL_TIMEOUT = 5000;

    public static void run() {

        String localFile = "local_files/example1.txt";
        String remoteFile = "/home/ubuntu/Desktop/test.txt";

        Session jschSession = null;

        try {

            JSch jsch = new JSch();
            jsch.setKnownHosts("/home/kali/.ssh/known_hosts");
            jschSession = jsch.getSession(USERNAME, REMOTE_HOST, REMOTE_PORT);

	    System.out.println("Session Aquired...\n");

            // authenticate using private key
            //jsch.addIdentity("/home/kali/.ssh/id_rsa");

            // authenticate using password
            jschSession.setPassword(PASSWORD);

	    System.out.println("Authenticating...\n");
	    //ssh-keyscan -t rsa 1.2.3.4 >> ~/.ssh/known_hosts
            
	    // 10 seconds session timeout
            jschSession.connect(SESSION_TIMEOUT);
	    System.out.println("Connection Established\n");

            Channel sftp = jschSession.openChannel("sftp");

            // 5 seconds timeout
            sftp.connect(CHANNEL_TIMEOUT);

            ChannelSftp channelSftp = (ChannelSftp) sftp;

            // transfer file from local to remote server
            channelSftp.put(localFile, remoteFile);
	    System.out.println("File Sent\n");

            // download file from remote server to local
            // channelSftp.get(remoteFile, localFile);

            channelSftp.exit();

        } catch (JSchException | SftpException e) {

            e.printStackTrace();

        } finally {
            if (jschSession != null) {
                jschSession.disconnect();
            }
        }

        System.out.println("Done");
    }
}