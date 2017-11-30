package com.jackshenorion.smarts.util.io;


import com.amazonaws.util.IOUtils;
import com.google.common.base.Preconditions;

import java.io.*;
import java.nio.file.Files;

public class IOUtil {
	
	public static final File TMP_DIR = new File(System.getProperty("java.io.tmpdir"));
	public static final File USER_HOME_DIR = new File(System.getProperty("user.home"));

	/**
	 * Fly weight empty input stream.
	 */
	public static final InputStream EMPTY_INPUT_STREAM = new ByteArrayInputStream(new byte[0]);
	
	private static final int HALF_MEGABYTE = 512 << 10;
	
	private static final Object CreatingFileLock = new Object();


	public static void cleanDirectory(File directory) throws IOException {
		if (!directory.exists()) {
			return;
		}

		if (!directory.isDirectory()) {
			String message = directory + " is not a directory";
			throw new IllegalArgumentException(message);
		}

		File[] files = directory.listFiles();
		if (files == null) {  // null if security restricted
			throw new IOException("Failed to list contents of " + directory);
		}

		IOException exception = null;
		for (File file : files) {
			if (file.isFile()) {
				file.delete();
			}
			else {
				cleanDirectory(file);
			}
		}

		directory.delete();

		if (null != exception) {
			throw exception;
		}
	}

	/**
	 * Closes an input stream without noise.
	 * 
	 * @param in -
	 *            the InputStream to close
	 */
	public static void close(InputStream in) {
		if (in == null)
			return;
		try {
			in.close();
		}
		catch (IOException ignored) {
		}
	}
	
	/**
	 * Closes a reader without noise.
	 * 
	 * @param reader -
	 *            the reader to close
	 */
	public static void close(Reader reader) {
		if (reader == null)
			return;
		try {
			reader.close();
		}
		catch (IOException ignored) {
		}
	}
	
	/**
	 * Closes a output stream without noise.
	 * 
	 * @param out -
	 *            the OutputStream to close.
	 */
	public static void close(OutputStream out) {
		if (out == null)
			return;
		try {
			out.close();
		}
		catch (IOException ignored) {
		}
	}
	
	/**
	 * Closes a Writer without noise.
	 * 
	 * @param writer -
	 *            the writer to close
	 */
	public static void close(Writer writer) {
		if (writer == null)
			return;
		try {
			writer.close();
		}
		catch (IOException ignored) {
		}
	}
	
	/**
	 * Copy data from an InputStream to an OutputStream.
	 * 
	 * @param in
	 * @param out
	 * @throws IOException
	 */
	public static void copy(InputStream in, OutputStream out) throws IOException {
		byte[] buffer = new byte[256];
		while (true) {
			int bytesRead = in.read(buffer);
			if (bytesRead == -1)
				break;
			out.write(buffer, 0, bytesRead);
		}
	}

	public static void copy(File from, File to) throws IOException {
		if (!to.exists()) {
			touch(to);
		}
		else if (to.isDirectory()) {
			throw new IOException(to.getCanonicalPath() + " is a directory.");
		}
		try (FileOutputStream toStream = new FileOutputStream(to)) {
			Files.copy(from.toPath(), toStream);
		}
	}

	/**
	 * Copy data from an InputStream to an OutputStream. Close both streams when
	 * copy finishes.
	 *
	 * @param in
	 * @param out
	 * @throws IOException
	 */
	public static void copyAndClose(InputStream in, OutputStream out) throws IOException {
		try {
			copy(in, out);
		}
		finally {
			close(in);
			close(out);
		}
	}

	/**
	 * Create a new file. If the file already exists, a new file with "(i)"
	 * appended to name would be attempted.
	 * <p>
	 * For example, if file - "c:\xfile.txt" exist, then file -
	 * "c:\xfile(1).txt" would be attempted, etc.
	 * <p>
	 * This method is equivalent to <code>
	 *    createNewFile(baseDir, filePath, 999);
	 * </code>
	 *
	 * @param baseDir -
	 *            base directory. If filePath argument is a file name, then the
	 *            base directory would be the folder of the file to be created.
	 * @param filePath -
	 *            can be a file name or a relative path.
	 * @return a new file
	 * @throws IOException
	 */
	public static File createNewFile(File baseDir, String filePath) throws IOException {
		return createNewFile(baseDir, filePath, 999);
	}

	/**
	 * Create a new file. If the file it is attempting already exists, a new file with
	 * "(i)" inserted would be attempted next.
	 * <p>
	 * For example, if file - "c:\xfile.txt" exist, then it will try file -
	 * "c:\xfile(1).txt".
	 *
	 * @param baseDir -
	 *            base directory. If filePath argument is a file name, then the
	 *            base directory would be the folder of the file to be created.
	 * @param filePath -
	 *            can be a file name or a relative path.
	 * @param maxAttempts -
	 *            the maximum number of <code>i</code> to attempt.
	 * @return a new file
	 * @throws IOException
	 */
	public static File createNewFile(File baseDir, String filePath, int maxAttempts) throws IOException {

		Preconditions.checkNotNull(baseDir);
        Preconditions.checkNotNull(filePath);
        Preconditions.checkState(maxAttempts > -1);

		File file = new File(baseDir, filePath);
		if (!file.exists()) {
			synchronized (CreatingFileLock) {
				if (!file.exists()) {
					IOUtil.touch(file);
					return file;
				}
			}
		}

		File dir  = file.getParentFile();
		IOUtil.touchDirectory(dir);

		String filename = file.getName();
		int dot = filename.lastIndexOf('.');
		String prefix = (dot < 0) ? filename : filename.substring(0, dot);
		String suffix = (dot < 0) ? ""       : filename.substring(dot);

		StringBuilder newFileName = new StringBuilder();
		int i = 1;
		for (; i <= maxAttempts; i++) {
			newFileName.delete(0, newFileName.length());
			newFileName.append(prefix).
						append('(').
						append(i).
						append(')').
						append(suffix);

			synchronized(CreatingFileLock) {
				file = new File(dir, newFileName.toString());
				if (!file.exists()) {
					IOUtil.touch(file);
					return file;
				}
			}
		}

		throw new IOException("Failed to create " + filePath + " after " + maxAttempts + " attempts in " + baseDir.getAbsolutePath());
	}

	public static File createTempFile(String prefix, String extension) {
		try {
			File tempFile = File.createTempFile(prefix, extension);
			tempFile.deleteOnExit();
			return tempFile;
		}
		catch (IOException e) {
			throw new IllegalStateException("Failed to create temp file: " + e.getMessage(), e);
		}
	}

	/**
	 * Let a file filter go through a directory, inclusive of subdirectories.
	 *
	 * @param directory
	 * @param filter
	 */
	public static void browse(File directory, FileFilter filter) {
		filter.accept(directory);
		if (directory.isDirectory()) {
			File[] f = directory.listFiles();
			for (int i = 0; i < f.length; i++) {
				if (f[i].isFile())
					filter.accept(f[i]);
				else
					browse(f[i], filter);
			}
		}
	}

	/**
	 * Call delete method on the given file without noise.
	 */
	public static boolean delete(File file) {
		if (file == null)
			return false;

		try {
			return file.delete();
		}
		catch (RuntimeException e) {
			return false;
		}
	}

	public static byte[] serialize(Object target) throws IOException {
		ByteArrayOutputStream store = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(store);
		out.writeObject(target);
		out.flush();
		out.close();
		return store.toByteArray();
	}

	public static Serializable deserialize(byte[] data) throws IOException, ClassNotFoundException {
		ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(data));
		return (Serializable) in.readObject();
	}

	/**
	 * Reads bytes from an input stream until EOF.
	 *
	 * @param in -
	 *            the InputStream to read.
	 * @return the bytes read.
	 * @throws IOException
	 *             if an I/O error occurs.
	 */
	public static byte[] read(InputStream in) throws IOException {
		if (in == null) throw new NullPointerException();

		BufferedInputStream bin = new BufferedInputStream(in);

		byte[] buffer = new byte[4096];
		int offset = 0;

		while (true) {
			int read = bin.read(buffer, offset, buffer.length
					- offset);
			if (read == -1) {
				break;
			} else {
				offset += read;
				if (offset == buffer.length) {
					byte[] bigger = new byte[Math.min(buffer.length + HALF_MEGABYTE, buffer.length * 2)];
					System.arraycopy(buffer, 0, bigger, 0, buffer.length);
					buffer = bigger;
				}
			}
		}

		byte[] trim = new byte[offset];
		System.arraycopy(buffer, 0, trim, 0, offset);
		return trim;
	}

	/**
	 * Reads bytes from an input stream until EOF. And close the stream when it
	 * finishes with it.
	 *
	 * @param in -
	 *            the InputStream to read.
	 * @return the bytes read.
	 * @throws IOException
	 *             if an I/O error occurs.
	 */
	public static byte[] readAndClose(InputStream in) throws IOException {
		try {
			return read(in);
		} finally {
			close(in);
		}
	}

	/**
	 * Reads characters from a reader until EOF.
	 *
	 * @param reader -
	 *            the Reader to read.
	 * @return the characters read.
	 * @throws IOException
	 *             if an I/O error occurs.
	 */
	public static String read(Reader reader) throws IOException {
		if (reader == null) throw new NullPointerException();

		BufferedReader r = new BufferedReader(reader);
		char[] buffer = new char[4096];
		int offset = 0;

		while (true) {
			int read = r.read(buffer, offset, buffer.length - offset);
			if (read == -1) {
				break;
			} else {
				offset += read;
				if (offset == buffer.length) {
					char[] bigger = new char[Math.min(HALF_MEGABYTE, buffer.length * 2)];
					System.arraycopy(buffer, 0, bigger, 0, buffer.length);
					buffer = bigger;
				}
			}
		}

		return new String(buffer, 0, offset);
	}

	/**
	 * Reads characters from a reader until EOF. And close the reader when it
	 * finishes with it.
	 *
	 * @param reader -
	 *            the Reader to read.
	 * @return the characters read.
	 * @throws IOException
	 *             if an I/O error occurs.
	 */
	public static String readAndClose(Reader reader) throws IOException {
		try {
			return read(reader);
		} finally {
			close(reader);
		}
	}

	public static String readText(InputStream reader) {
		try {
			return IOUtils.toString(reader);
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static String readTextAndClose(InputStream in) throws IOException {
		try {
			return readText(in);
		} finally {
			close(in);
		}
	}

	/**
	 * Create an file if the file doesn't exist.
	 *
	 * @param -
	 *            a file
	 * @return the file.
	 */
	public static File touch(File file) throws IOException {
		Preconditions.checkState(!file.isDirectory(), file.getAbsolutePath() + " is a directory");
		if (!file.exists()) {
			touchDirectory(file.getParentFile());
			file.createNewFile();
		}
		return file;
	}

	/**
	 * Create a directory if the directory doesn't exist.
	 *
	 * @param dir -
	 *            a directory
	 * @return the directory
	 * @throws IOException
	 */
	public static File touchDirectory(File dir) throws IOException {
		Preconditions.checkState(!dir.isFile(), dir.getAbsolutePath() + " is a file");
		if (dir != null && !dir.exists()) {
			dir.mkdirs();
		}
		return dir;
	}

	/**
	 * Writes data to an output stream until all data has been written.
	 *
	 * @param out -
	 *            the stream to write to.
	 * @param data -
	 *            the data to be written
	 * @throws IOException
	 */
	public static void write(OutputStream out, byte[] data) throws IOException {
		out = new BufferedOutputStream(out);
		out.write(data);
		out.flush();
	}

	/**
	 * Writes data to a writer until all data has been written.
	 *
	 * @param out -
	 *            the writer to write to.
	 * @param data -
	 *            the data to be written.
	 * @throws IOException
	 */
	public static void write(Writer out, String data) throws IOException {
		out = new BufferedWriter(out);
		out.write(data);
		out.flush();
	}

	/**
	 * Writes data to an output stream until all data has been written, and then
	 * close the stream.
	 *
	 * @param out -
	 *            the stream to write to
	 * @param data -
	 *            the data to be written
	 * @throws IOException
	 */
	public static void writeAndClose(OutputStream out, byte[] data) throws IOException {
		try {
			write(out, data);
		}
		finally {
			close(out);
		}
	}

	public static void writeAndClose(File file, byte[] data) {
		try {
			writeAndClose(new FileOutputStream(file), data);
		}
		catch (IOException e) {
			throw new IllegalStateException("Failed to write file: " + e.getMessage(), e);
		}
	}

	public static void writeAndClose(File file, String data) {
		try {
			if (true) { // for findbugs
				writeAndClose(new FileWriter(file), data);
			}
		} catch (IOException e) {
			throw new IllegalStateException("Failed to write file: " + e.getMessage(), e);
		}
	}

	/**
	 * Writes data to a writer until all data has been written, and then close
	 * the writer.
	 *
	 * @param out -
	 *            the writer to write to
	 * @param data -
	 *            the data to be written
	 * @throws IOException
	 */
	public static void writeAndClose(Writer out, String data) throws IOException {
		try {
			write(out, data);
		}
		finally {
			close(out);
		}
	}

	public static void writeBinaryFile(String filePath, byte[] data) throws IOException {
		writeBinaryFile(new File(filePath), data);
	}

	public static void writeBinaryFile(File file, byte[] data) throws IOException {
		touch(file);
		writeAndClose(file, data);
	}

	public static void writeTextFile(String filePath, String data) throws IOException {
		writeTextFile(new File(filePath), data);
	}

	public static void writeTextFile(File file, String data) throws IOException {
		touch(file);
		writeAndClose(new FileWriter(file), data);
	}

}
