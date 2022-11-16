package kr.ac.jj.survey.infrastructure.framework.core.support.io.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.channels.FileChannel;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Base64Utils;
import org.springframework.util.ResourceUtils;

import kr.ac.jj.survey.infrastructure.framework.core.foundation.exception.BaseException;
import kr.ac.jj.survey.infrastructure.framework.core.support.io.util.IOUtil;
import kr.ac.jj.survey.infrastructure.framework.core.support.lang.StringUtil;

/**
 * 파일 관련 유틸리티.
 */
public class FileUtil {
    private static final Logger log = LoggerFactory.getLogger(FileUtil.class);

    private FileUtil() {
    }

    public static boolean exists(File file) {
        return file != null && file.exists();
    }

    public static boolean isDirectory(File file) {
        return file != null && file.exists() && file.isDirectory();
    }

    public static boolean isFile(File file) {
        return file != null && file.exists() && file.isFile();
    }

    public static ClassLoader getClassLoader() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        if (classLoader == null) {
            classLoader = ClassLoader.getSystemClassLoader();
        }

        if (classLoader == null) {
            throw new BaseException("ClassLoader is null.");
        }

        return classLoader;
    }

    public static URL getResourceURL(String location) {
        return getResourceURL(getClassLoader(), location);
    }

    private static URL getResourceURL(ClassLoader classLoader, String location) {
        String resourceName = location.substring(ResourceUtils.CLASSPATH_URL_PREFIX.length());

        while (resourceName.startsWith("/")) {
            resourceName = resourceName.replaceFirst("/", "");
        }

        return classLoader.getResource(resourceName);
    }

    public static boolean isResourceExists(String location) {
        if (location.startsWith(ResourceUtils.CLASSPATH_URL_PREFIX)) {
            return getResourceURL(location) != null;
        }

        if (location.startsWith(ResourceUtils.FILE_URL_PREFIX)) {
            return exists(new File(location.substring(ResourceUtils.FILE_URL_PREFIX.length())));
        }

        return exists(new File(location));
    }

    public static File getResourceFile(String location) {
        File resourceFile;

        if (location.startsWith(ResourceUtils.CLASSPATH_URL_PREFIX)) {
            ClassLoader classLoader = getClassLoader();
            URL url = getResourceURL(classLoader, location);
            if (url == null) {
                resourceFile = new File(classLoader.getResource("").getPath(),
                        location.substring(ResourceUtils.CLASSPATH_URL_PREFIX.length()));
            } else {
                resourceFile = new File(url.getPath());
            }
        } else if (location.startsWith(ResourceUtils.FILE_URL_PREFIX)) {
            resourceFile = new File(location.substring(ResourceUtils.FILE_URL_PREFIX.length()));
        } else {
            resourceFile = new File(location);
        }

        if (!resourceFile.getPath().toLowerCase().contains(".jar!") && !resourceFile.exists()) {
            log.warn("The location [{}]'s physical path [{}] is not exist.", location, resourceFile.getPath());
        }

        return resourceFile;
    }

    public static InputStream getResourceStream(String location) {
        return getResourceStream(FileUtil.getResourceFile(location), "utf-8");
    }

    public static InputStream getResourceStream(String location, String charsetName) {
        return getResourceStream(FileUtil.getResourceFile(location), "utf-8");
    }

    public static InputStream getResourceStream(File file) {
        return getResourceStream(file, "utf-8");
    }

    public static InputStream getResourceStream(File file, String charsetName) {
        if (file == null) {
            return null;
        }

        if (FileUtil.isFile(file)) {
            try {
                return new FileInputStream(file);
            } catch (FileNotFoundException e) {
                throw new BaseException(e);
            }
        }

        if (!file.getPath().toLowerCase().contains(".jar!")) {
            return null;
        }

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String resourcePath = StringUtil.cropIgnoreCase(file.getPath(), "classes!", null);

        if (StringUtils.isEmpty(resourcePath)) {
            resourcePath = StringUtil.cropIgnoreCase(file.getPath(), ".jar!", null);
        }

        if (StringUtils.isNotEmpty(resourcePath)) {
            String resourceName = resourcePath.replaceAll("\\\\", "/");
            while (resourceName.startsWith("/")) {
                resourceName = resourceName.replaceFirst("/", "");
            }
            return classLoader.getResourceAsStream(resourceName);
        }

        return null;
    }

    public static String getResourceString(String location) {
        return getResourceString(FileUtil.getResourceFile(location), "utf-8");
    }

    public static String getResourceString(String location, String charsetName) {
        return getResourceString(FileUtil.getResourceFile(location), charsetName);
    }

    public static String getResourceString(File file) {
        return getResourceString(file, "utf-8");
    }

    public static String getResourceString(File file, String charsetName) {
        InputStream inputStream = getResourceStream(file, charsetName);

        if (inputStream == null) {
            return null;
        }

        try {
            return IOUtils.toString(inputStream, charsetName);
        } catch (IOException e) {
            throw new BaseException(e);
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
    }

    /**
     * 파일의 정규 경로 문자열 반환.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1)String cononicalpath = NFileUtil.getCanonicalPath("D:/file.txt");
     * </pre>
     *
     * @param pathname 파일 경로 문자열
     * @return 파일 정규 경로 문자열
     */
    public static String getCanonicalPath(String pathname) {
        if (StringUtils.isEmpty(pathname)) {
            return pathname;
        }

        return getCanonicalPath(new File(pathname));
    }

    /**
     * 파일의 정규 경로 문자열 반환.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1)String cononicalpath = NFileUtil.getCanonicalPath(new File("D:/file.txt"));
     * </pre>
     *
     * @param file File 파일
     * @return 파일 정규 경로 문자열
     */
    public static String getCanonicalPath(File file) {
        if (file == null) {
            return null;
        }

        try {
            return file.getCanonicalPath();
        } catch (IOException e) {
            return file.getAbsolutePath();
        }
    }

    /**
     * 파일명 문자열 반환.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1)String cononicalpath = NFileUtil.getFileName("D:/file.txt");  //file.txt
     * </pre>
     *
     * @param pathname 파일 경로 문자열
     * @return 파일 정규 경로 문자열
     */
    public static String getFileName(String pathname) {
        int sepIndex = pathname.replaceAll("\\\\", "/").lastIndexOf("/");

        return pathname.substring(sepIndex + 1);
    }

    /**
     * 파일 확장자를 제외한 파일명 문자열 반환.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1)String cononicalpath = NFileUtil.getBaseName("D:/file.txt");  //file
     * </pre>
     *
     * @param filename 파일명 문자열
     * @return 파일명 문자열
     */
    public static String getBaseName(String filename) {
        int sepIndex = filename.replaceAll("\\\\", "/").lastIndexOf("/");
        int dotIndex = filename.lastIndexOf(".");

        if (dotIndex == -1) {
            return filename.substring(sepIndex + 1);
        }

        return filename.substring(sepIndex + 1, dotIndex);
    }

    /**
     * 파일의 확장자 문자열 반환.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1)String cononicalpath = NFileUtil.getExtension("D:/file.txt");  //txt
     * </pre>
     *
     * @param filename 파일명 문자열
     * @return 파일 확장자 문자열
     */
    public static String getExtension(String filename) {
        int dotIndex = filename.lastIndexOf(".");

        if (dotIndex == -1) {
            return "";
        }

        return filename.substring(dotIndex + 1);
    }

    /**
     * 파일의 내용을 문자열로 반환.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1)String cononicalpath = NFileUtil.readAll("D:/file.txt");
     * </pre>
     *
     * @param pathname 파일명 문자열
     * @return 파일내용 문자열
     */
    public static String readAll(String pathname) {
        return readAll(new File(pathname));
    }

    /**
     * 파일의 내용을 문자열로 반환.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1)String cononicalpath = NFileUtil.readAll("D:/file.txt","utf-8");
     * </pre>
     *
     * @param pathname    파일명 문자열
     * @param charsetName 캐릭터셋명 문자열
     * @return 파일 내용 문자열
     */
    public static String readAll(String pathname, String charsetName) {
        return readAll(new File(pathname), charsetName);
    }

    /**
     * 파일의 내용을 문자열로 반환.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1)String cononicalpath = NFileUtil.readAll(new File("D:/file.txt"));
     * </pre>
     *
     * @param file File 파일
     * @return 파일 내용 문자열
     */
    public static String readAll(File file) {
        return readAll(file, "utf-8");
    }

    /**
     * 파일의 내용을 문자열로 반환.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1)String cononicalpath = NFileUtil.readAll(new File("D:/file.txt"),"utf-8");
     * </pre>
     *
     * @param file        File 파일
     * @param charsetName 캐릭터셋명 문자열
     * @return 파일 내용 문자열
     */
    public static String readAll(File file, String charsetName) {
        if (file == null || !file.exists() || !file.isFile()) {
            return null;
        }

        FileInputStream fis = null;
        InputStreamReader isr = null;
        StringBuilder sb = null;

        try {

            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis, charsetName);
            sb = new StringBuilder();

            {
                int i;
                while ((i = isr.read()) != -1) {
                    sb.append((char) i);
                }
            }

        } catch (FileNotFoundException e) {
            throw new BaseException(e);
        } catch (UnsupportedEncodingException e) {
            throw new BaseException(e);
        } catch (IOException e) {
            throw new BaseException(e);
        } finally {
            IOUtil.closeQuietly(isr, fis);
        }

        String str = sb.toString();

        if (str.length() > 0 && str.charAt(0) == 65279) {
            return str.substring(1);
        }

        return str;
    }

    /**
     * 파일 생성.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1)NFileUtil.writeAll("D:/file.txt","test");
     * </pre>
     *
     * @param pathname 파일 경로 문자열
     * @param contents 파일 내용 문자열
     */
    public static void writeAll(String pathname, String contents) {
        writeAll(pathname, contents, false);
    }

    /**
     * 파일 생성.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1)NFileUtil.writeAll("D:/file.txt","test",true);
     * </pre>
     *
     * @param pathname 파일 경로 문자열
     * @param contents 파일 내용 문자열
     * @param append   기존 내용에 추가 여부
     */
    public static void writeAll(String pathname, String contents, boolean append) {
        writeAll(new File(pathname), contents, "utf-8", append);
    }

    /**
     * 파일 생성.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1)NFileUtil.writeAll("D:/file.txt","test","utf-8");
     * </pre>
     *
     * @param pathname 파일 경로 문자열
     * @param contents 파일 내용 문자열
     * @param charset  캐릭터셋 명 문자열
     */
    public static void writeAll(String pathname, String contents, String charset) {
        writeAll(new File(pathname), contents, charset);
    }

    /**
     * 파일 생성.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1)NFileUtil.writeAll("D:/file.txt","test","utf-8",true);
     * </pre>
     *
     * @param pathname 파일 경로 문자열
     * @param contents 파일 내용 문자열
     * @param charset  캐릭터셋 명 문자열
     * @param append   기존 내용에 추가 여부
     */
    public static void writeAll(String pathname, String contents, String charset, boolean append) {
        writeAll(new File(pathname), contents, charset, append);
    }

    /**
     * 파일 생성.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1)NFileUtil.writeAll(new File("D:/file.txt"),"test");
     * </pre>
     *
     * @param file     File 파일
     * @param contents 파일 내용
     */
    public static void writeAll(File file, String contents) {
        writeAll(file, contents, "utf-8");
    }

    /**
     * 파일 생성.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1)NFileUtil.writeAll(new File("D:/file.txt"),"test",true);
     * </pre>
     *
     * @param file     File 파일
     * @param contents 파일 내용 문자열
     * @param append   기존 내용에 추가 여부
     */
    public static void writeAll(File file, String contents, boolean append) {
        writeAll(file, contents, "utf-8", append);
    }

    /**
     * 파일 생성.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1)NFileUtil.writeAll(new File("D:/file.txt"),"test","utf-8");
     * </pre>
     *
     * @param file     File 파일
     * @param contents 파일 내용
     * @param charset  캐릭터셋 명 문자열
     */
    public static void writeAll(File file, String contents, String charset) {
        writeAll(file, contents, charset, false);
    }

    /**
     * 파일 생성.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1)NFileUtil.writeAll(new File("D:/file.txt"),"test","utf-8",true);
     * </pre>
     *
     * @param file     File 파일
     * @param contents 파일 내용
     * @param charset  캐릭터셋 명 문자열
     * @param append   기존 내용에 추가 여부
     */
    public static void writeAll(File file, String contents, String charset, boolean append) {
        if (file.isDirectory()) {
            return;
        }

        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                return;
            }
        }

        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;

        try {

            fos = new FileOutputStream(file, append);
            osw = new OutputStreamWriter(fos, charset);
            bw = new BufferedWriter(osw);

            if (contents != null) {
                bw.write(contents);
            }

        } catch (FileNotFoundException e) {
            throw new BaseException(e);
        } catch (UnsupportedEncodingException e) {
            throw new BaseException(e);
        } catch (IOException e) {
            throw new BaseException(e);
        } finally {
            IOUtil.closeQuietly(bw, osw, fos);
        }
    }

    /**
     * 파일 생성.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1)NFileUtil.writeAll("D:/file2.txt",new FileInputStream("D:/file.txt"));
     * </pre>
     *
     * @param pathname    파일 경로 문자열
     * @param inputStream InputStream
     */
    public static void writeAll(String pathname, InputStream inputStream) {
        writeAll(new File(pathname), inputStream);
    }

    /**
     * 파일 생성.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1)NFileUtil.writeAll("D:/file2.txt",new FileInputStream("D:/file.txt"),true);
     * </pre>
     *
     * @param pathname    파일 경로 문자열
     * @param inputStream InputStream
     * @param append      기존 파일에 추가 여부
     */
    public static void writeAll(String pathname, InputStream inputStream, boolean append) {
        writeAll(new File(pathname), inputStream, append);
    }

    /**
     * 파일 생성.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1)NFileUtil.writeAll(new File("D:/file2.txt"),new FileInputStream("D:/file.txt"));
     * </pre>
     *
     * @param file        File
     * @param inputStream InputStream
     */
    public static void writeAll(File file, InputStream inputStream) {
        writeAll(file, inputStream, false);
    }

    /**
     * 파일 생성.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1)NFileUtil.writeAll(new File("D:/file2.txt"),new FileInputStream("D:/file.txt"),true);
     * </pre>
     *
     * @param file        File
     * @param inputStream InputStream
     * @param append      기존 파일에 추가 여부
     */
    public static void writeAll(File file, InputStream inputStream, boolean append) {
        if (file.isDirectory()) {
            return;
        }

        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                return;
            }
        }

        FileOutputStream fos = null;

        try {

            fos = new FileOutputStream(file, append);

            {
                byte buf[] = new byte[1024];
                int len;
                while ((len = inputStream.read(buf, 0, buf.length)) != -1) {
                    fos.write(buf, 0, len);
                }
            }

        } catch (FileNotFoundException e) {
            throw new BaseException(e);
        } catch (IOException e) {
            throw new BaseException(e);
        } finally {
            IOUtil.closeQuietly(fos, inputStream);
        }
    }

    public static void writeAll(File file, OutputStream outputStream) {
        FileInputStream fis = null;

        try {
            fis = new FileInputStream(file);
            writeAll(fis, outputStream);
        } catch (FileNotFoundException e) {
            throw new BaseException(e);
        } finally {
            IOUtil.closeQuietly(fis);
        }
    }

    public static void writeAll(InputStream inputStream, OutputStream outputStream) {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        try {

            bis = new BufferedInputStream(inputStream);
            bos = new BufferedOutputStream(outputStream);

            {
                byte buf[] = new byte[1024 * 2];
                int len;
                while ((len = bis.read(buf, 0, buf.length)) != -1) {
                    bos.write(buf, 0, len);
                }
            }

        } catch (IOException e) {
            throw new BaseException(e);
        } finally {
            IOUtil.flushQuietly(bos);
            IOUtil.closeQuietly(bos, bis);
        }
    }

    /**
     * 디렉토리 복사.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1)NFileUtil.copyDirectory("D:/diretory","D:/diretory2");
     * </pre>
     *
     * @param sourcePath 복사할 디렉토리 경로 문자열
     * @param targetPath 복사될 디렉토리 경로 문자열
     */
    public static void copyDirectory(String sourcePath, String targetPath) {
        File sourceFile = new File(sourcePath);
        File targetFile = new File(targetPath);

        if (sourceFile.isFile()) {
            copyFile(sourcePath, targetPath);
            return;
        }

        if (!targetFile.isDirectory()) {
            targetFile.mkdir();
        }

        String[] children = sourceFile.list();

        for (int i = 0, ii = children.length; i < ii; i++) {
            copyDirectory(sourcePath + "/" + children[i], targetPath + "/" + children[i]);
        }
    }

    /**
     * 파일 복사.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1)NFileUtil.copyFile("D:/file.txt","D:/file3.txt");
     * </pre>
     *
     * @param sourcePath 복사할 파일 경로 문자열
     * @param targetPath 복사될 파일 경로 문자열
     */
    public static void copyFile(String sourcePath, String targetPath) {
        copyFile(sourcePath, targetPath, false);
    }

    /**
     * 파일 복사.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1)NFileUtil.copyFile("D:/file.txt","D:/file3.txt",true);
     * </pre>
     *
     * @param sourcePath     복사할 파일 경로 문자열
     * @param targetPath     복사될 파일 경로 문자열
     * @param makeParentDirs 복사될 경로의 부모 폴더 생성 여부
     */
    public static void copyFile(String sourcePath, String targetPath, boolean makeParentDirs) {
        if (makeParentDirs) {
            new File(targetPath).getParentFile().mkdirs();
        }

        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        FileChannel fileInputChannel = null;
        FileChannel fileOutputChannel = null;

        try {

            fileInputStream = new FileInputStream(sourcePath);
            fileOutputStream = new FileOutputStream(targetPath);

            fileInputChannel = fileInputStream.getChannel();
            fileOutputChannel = fileOutputStream.getChannel();

            fileInputChannel.transferTo(0, fileInputChannel.size(), fileOutputChannel);

        } catch (FileNotFoundException e) {
            throw new BaseException(e);
        } catch (IOException e) {
            throw new BaseException(e);
        } finally {
            IOUtil.closeQuietly(fileOutputChannel, fileInputChannel, fileOutputStream, fileInputStream);
        }
    }

    /**
     * 디렉토리 이동.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1)NFileUtil.moveDirectory("D:/diretory","D:/diretory2");
     * </pre>
     *
     * @param sourcePath 이동할 디렉토리 경로 문자열
     * @param targetPath 이동될 디렉토리 경로 문자열
     */
    public static void moveDirectory(String sourcePath, String targetPath) {
        copyDirectory(sourcePath, targetPath);
        deleteDirectory(sourcePath);
    }

    /**
     * 파일 이동.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1)NFileUtil.moveFile("D:/file.txt","D:/file2.txt");
     * </pre>
     *
     * @param sourcePath 이동할 파일 경로 문자열
     * @param targetPath 이동될 파일 경로 문자열
     */
    public static void moveFile(String sourcePath, String targetPath) {
        moveFile(sourcePath, targetPath, false);
    }

    /**
     * 파일 이동.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1)NFileUtil.moveFile("D:/directory/file.txt","D:/directory2/file2.txt",true);
     * </pre>
     *
     * @param sourcePath     이동할 파일 경로 문자열
     * @param targetPath     이동될 파일 경로 문자열
     * @param makeParentDirs 이동될 경로의 부모 폴더 생성 여부
     */
    public static void moveFile(String sourcePath, String targetPath, boolean makeParentDirs) {
        copyFile(sourcePath, targetPath, makeParentDirs);
        deleteFile(sourcePath);
    }

    public static void clearDirectory(String path) {
        clearDirectory(new File(path));
    }

    public static void clearDirectory(File file) {
        if (!file.exists() || !file.isDirectory()) {
            return;
        }

        File[] files = file.listFiles();

        for (int i = 0, ii = files.length; i < ii; i++) {
            if (files[i].isDirectory()) {
                deleteDirectory(files[i]);
            } else {
                files[i].delete();
            }
        }
    }

    /**
     * 디렉토리 삭제.<br>
     * 하위 파일 모두 삭제
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1)NFileUtil.deleteDirectory("D:/directory2");
     * </pre>
     *
     * @param path 삭제할 디렉토리 경로 문자열
     * @return
     */
    public static boolean deleteDirectory(String path) {
        return deleteDirectory(new File(path));
    }

    /**
     * 디렉토리 삭제.<br>
     * 하위 파일 모두 삭제
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1)NFileUtil.deleteDirectory(new File("D:/directory2"));
     * </pre>
     *
     * @param file File
     * @return
     */
    public static boolean deleteDirectory(File file) {
        clearDirectory(file);

        return file.delete();
    }

    /**
     * 파일 삭제.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1)NFileUtil.deleteFile("D:/directory2/test.txt");
     * </pre>
     *
     * @param pathname 파일 경로 문자열
     * @return 삭제 성공 여부 리턴
     */
    public static boolean deleteFile(String pathname) {
        return deleteFile(new File(pathname));
    }

    /**
     * 파일 삭제.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1)NFileUtil.deleteFile(new File("D:/directory2/test.txt"));
     * </pre>
     *
     * @param file File
     * @return 삭제 성공 여부 리턴
     */
    public static boolean deleteFile(File file) {
        if (file.exists() && file.isFile()) {
            return file.delete();
        }

        return false;
    }

    /**
     * 동일한 파일인지 여부 반환.
     *
     * @param file1
     * @param file2
     * @return
     */
    public static boolean isSameFile(File file1, File file2) {
        if (file1 == file2) {
            return true;
        }

        return getCanonicalPath(file1).equals(getCanonicalPath(file2));
    }

    /**
     * 파일을 Base64로 인코딩한 문자열 반환.
     *
     * @param file
     * @return
     */
    public static String encodeBase64(File file) {
        if (file == null || !file.exists()) {
            return null;
        }

        FileInputStream fis = null;
        ByteArrayOutputStream baos = null;

        try {

            fis = new FileInputStream(file);
            baos = new ByteArrayOutputStream();

            int len = 0;
            byte[] buf = new byte[1024];

            while ((len = fis.read(buf)) != -1) {
                baos.write(buf, 0, len);
            }

            byte[] fileArray = baos.toByteArray();
            String base64Str = new String(Base64Utils.encode(fileArray));

            return base64Str;

        } catch (IOException e) {
            throw new BaseException(e);
        } finally {
            IOUtil.closeQuietly(fis, baos);
        }
    }
}
