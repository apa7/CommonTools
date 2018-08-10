package cn.lidd.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * IO����
 *
 */
public class IOUtil {

	/**
	 * ���ļ�srcFile���Ƶ��ļ�destFile������JDK1.7.
	 *
	 * @param srcFile
	 *            ԭʼ�ļ�
	 * @param destFile
	 *            �����ļ�
	 * @throws IOException
	 */
	public static void copyFile(File srcFile, File destFile) throws IOException {
		try ( FileInputStream in = new FileInputStream(srcFile);
				FileOutputStream out = new FileOutputStream(destFile);
				FileChannel inC = in.getChannel();
				FileChannel outC = out.getChannel();) {
			int length = 2097152;
			ByteBuffer b = null;
			while (true) {
				if (inC.position() == inC.size()) {
					break;
				}
				if ((inC.size() - inC.position()) < length) {
					length = (int) (inC.size() - inC.position());
				} else {
					length = 2097152;
				}
				b = ByteBuffer.allocateDirect(length);
				inC.read(b);
				b.flip();
				outC.write(b);
				outC.force(false);
			}
		}
	}
}
