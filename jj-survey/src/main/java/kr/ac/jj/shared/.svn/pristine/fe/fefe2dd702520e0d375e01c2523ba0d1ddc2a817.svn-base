package kr.ac.jj.shared.infrastructure.framework.web.support.download;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import kr.ac.jj.shared.infrastructure.framework.core.foundation.exception.BaseException;
import kr.ac.jj.shared.infrastructure.framework.core.support.context.ApplicationContextUtil;
import kr.ac.jj.shared.infrastructure.framework.core.support.io.file.FileUtil;
import kr.ac.jj.shared.infrastructure.framework.core.support.io.util.IOUtil;
import kr.ac.jj.shared.infrastructure.framework.core.support.lang.StringUtil;
import kr.ac.jj.shared.infrastructure.framework.web.context.request.RequestContextUtil;
import kr.ac.jj.shared.infrastructure.framework.web.support.download.config.FileDownloadConfig;
import kr.ac.jj.shared.infrastructure.framework.web.support.download.exception.FileDownloadNotExistsException;
import kr.ac.jj.shared.infrastructure.framework.web.support.download.exception.FileDownloadUndefinedNameException;
import kr.ac.jj.shared.infrastructure.framework.web.support.download.exception.FileDownloadUndefinedPathException;

public class FileDownload {

    private final HttpServletRequest request;
    private final HttpServletResponse response;

    public final String policy;

    public FileDownload() {
        this(RequestContextUtil.getRequest(), RequestContextUtil.getResponse(), null);
    }

    public FileDownload(String policy) {
        this(RequestContextUtil.getRequest(), RequestContextUtil.getResponse(), policy);
    }

    public FileDownload(HttpServletRequest request) {
        this(request, RequestContextUtil.getResponse(), null);
    }

    public FileDownload(HttpServletRequest request, String policy) {
        this(request, RequestContextUtil.getResponse(), policy);
    }

    public FileDownload(HttpServletResponse response) {
        this(RequestContextUtil.getRequest(), response, null);
    }

    public FileDownload(HttpServletResponse response, String policy) {
        this(RequestContextUtil.getRequest(), response, policy);
    }

    public FileDownload(HttpServletRequest request, HttpServletResponse response) {
        this(request, response, null);
    }

    public FileDownload(HttpServletRequest request, HttpServletResponse response, String policy) {
        this.request = request;
        this.response = response;
        this.policy = (policy == null ? "default" : policy);
    }

    public void setHeader(String downloadFileName) {
        this.setHeader(downloadFileName, -1, false);
    }

    public void setHeader(String downloadFileName, boolean previewOnly) {
        this.setHeader(downloadFileName, -1, previewOnly);
    }

    public void setHeader(String downloadFileName, long contentLength) {
        this.setHeader(downloadFileName, contentLength, false);
    }

    private void setHeader(String downloadFileName, long contentLength, boolean previewOnly) {
        if (downloadFileName == null) {
            throw new FileDownloadUndefinedNameException();
        }

        FileDownloadConfig config = ApplicationContextUtil.getBean(FileDownloadConfig.class);

        try {

            String userAgent = StringUtil.nvl(this.request == null ? null : this.request.getHeader("user-agent"), "");
            boolean edge = (userAgent.indexOf("Edge/") != -1);
            boolean trident = (userAgent.indexOf("Trident/") != -1);
            boolean msie = (userAgent.indexOf("MSIE") != -1 || userAgent.indexOf("chromeframe") != -1);
            boolean msie55 = (userAgent.indexOf("MSIE 5.5") != -1);
            boolean filenameURLEncode = BooleanUtils.isTrue(config.getDownloadFilenameUrlEncode());
            boolean filenameEncoding = (!filenameURLEncode
                    && ObjectUtils.defaultIfNull(config.getDownloadFilenameEncoding(), !filenameURLEncode));

            if (!previewOnly) {
                this.response.reset();

                this.response.setHeader("Content-Transfer-Encoding",
                        StringUtils.defaultString(config.getContentTransferEncoding(), "binary;"));

                if (msie55) {
                    this.response.setHeader("Content-Type", "doesn/matter;");
                } else {
                    this.response.setHeader("Content-Type", "application/octet-stream;");
                }
            }

            if (contentLength != -1) {
                this.response.setHeader("Content-Length", String.valueOf(contentLength));
            }

            String contentDisposition;

            if (filenameEncoding && msie
                    && !downloadFileName.equals(new String(downloadFileName.getBytes("MS949"), "MS949"))) {
                String filename = URLEncoder.encode(downloadFileName, "UTF-8");
                if (msie55 || previewOnly) {
                    contentDisposition = "=?UTF-8?Q?filename=\"" + filename + "\";?=";
                } else {
                    contentDisposition = "=?UTF-8?Q?attachment; filename=\"" + filename + "\";?=";
                }
            } else {
                String filename;
                if (filenameEncoding) {
                    if (edge) {
                        filename = URLEncoder.encode(downloadFileName, "UTF-8").replaceAll("\\+", "%20");
                    } else if (trident || msie) {
                        filename = new String(downloadFileName.getBytes("MS949"), "ISO-8859-1").replaceAll(" ", "%20");
                    } else {
                        filename = new String(downloadFileName.getBytes("UTF-8"), "ISO-8859-1");
                    }
                } else if (filenameURLEncode && (trident || msie)) {
                    filename = URLEncoder.encode(downloadFileName, "UTF-8");
                } else {
                    filename = downloadFileName;
                }
                if (msie55 || previewOnly) {
                    contentDisposition = "filename=\"" + filename + "\";";
                } else {
                    contentDisposition = "attachment; filename=\"" + filename + "\";";
                }
            }

            this.response.setHeader("Content-Disposition", contentDisposition);

        } catch (UnsupportedEncodingException e) {
            throw new BaseException(e);
        }
    }

    public void download(String pathname) {
        this.download(pathname, null);
    }

    public void download(String pathname, String downloadFileName) {
        if (StringUtils.isEmpty(pathname)) {
            throw new FileDownloadUndefinedPathException();
        }

        this.download(new File(pathname), downloadFileName);
    }

    public void download(File file) {
        this.download(file, null);
    }

    public void download(File file, String downloadFileName) {
        if (downloadFileName == null) {
            downloadFileName = file.getName();
        }

        if (!(file.exists() && file.isFile())) {
            throw new FileDownloadNotExistsException(file);
        }

        FileInputStream fis = null;

        try {
            fis = new FileInputStream(file);
            this.download(fis, downloadFileName, file.length());
        } catch (FileNotFoundException e) {
            throw new BaseException(e);
        } finally {
            IOUtil.closeQuietly(fis);
        }
    }

    public void download(InputStream is, String downloadFileName) {
        this.download(is, downloadFileName, -1);
    }

    public void download(InputStream is, String downloadFileName, long contentLength) {
        this.setHeader(downloadFileName, contentLength);

        try {
            FileUtil.writeAll(is, this.response.getOutputStream());
        } catch (UnsupportedEncodingException e) {
            throw new BaseException(e);
        } catch (FileNotFoundException e) {
            throw new BaseException(e);
        } catch (IOException e) {
            throw new BaseException(e);
        }
    }

    public void preview(String pathname, String previewFileName) {
        this.preview(new File(pathname), previewFileName);
    }

    public void preview(File file, String previewFileName) {
        if (previewFileName == null) {
            previewFileName = file.getName();
        }

        if (!(file.exists() && file.isFile())) {
            throw new FileDownloadNotExistsException(file);
        }

        FileInputStream fis = null;

        try {
            fis = new FileInputStream(file);
            this.preview(fis, previewFileName);
        } catch (FileNotFoundException e) {
            throw new BaseException(e);
        } finally {
            IOUtil.closeQuietly(fis);
        }
    }

    public void preview(InputStream is, String previewFileName) {
        this.setHeader(previewFileName, true);

        try {
            FileUtil.writeAll(is, this.response.getOutputStream());
        } catch (UnsupportedEncodingException e) {
            throw new BaseException(e);
        } catch (FileNotFoundException e) {
            throw new BaseException(e);
        } catch (IOException e) {
            throw new BaseException(e);
        }
    }

}
