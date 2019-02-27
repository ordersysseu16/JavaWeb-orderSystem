package com.seu.koo.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * @author zyn-w530 支持单文件上传的 SingleFileUpload 类：
 */
public class SingleFileUpload extends FileUploadBase {
	private FileItem fileItem;
	private String fileType; //允许上传的文件类型

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	/**
	 * 
	 * @param request
	 * @throws UnsupportedEncodingException
	 */
	/**
	 * @param request
	 * @throws UnsupportedEncodingException
	 */
	public void parseRequest(HttpServletRequest request) throws UnsupportedEncodingException {

		DiskFileItemFactory factory = new DiskFileItemFactory();
		// DiskFileItemFactory f = new DiskFileItemFactory(sizeThreshold,
		// repository);

		factory.setSizeThreshold(sizeThreshold);

		if (repository != null)
			factory.setRepository(repository);

		ServletFileUpload upload = new ServletFileUpload(factory);

		upload.setHeaderEncoding(encoding);

		try {
			List<FileItem> items = upload.parseRequest(request);

			for (FileItem item : items) {
				if (item.isFormField()) {
					String fieldName = item.getFieldName();
					String value = item.getString(encoding);
					parameters.put(fieldName, value);
				} else {
					if (!isValidFile(item)) {
						continue;
					}
					fileItem = item;
				}
			}

		} catch (FileUploadException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 上传文件, 调用该方法之前必须先调用 parseRequest(HttpServletRequest request)
	 * 
	 * @param path
	 *            文件全路径
	 * @param old_Name
	 *            原文件名
	 * @return 上传后的新文件名
	 * @throws Exception
	 * 
	 */
	public String upload(String path, String old_name) throws Exception {
		String new_name = null;
		if (path == null || old_name == null) {
			return new_name;
		}
		new_name = this.makeFileName(old_name);
		File file = new File(path + new_name);
		uploadFile(file);
		return new_name;
	}

	private void uploadFile(File file) throws Exception {
		if (fileItem == null)
			return;

		long fileSize = fileItem.getSize();
		if (sizeMax > -1 && fileSize > super.sizeMax) {
			String message = String.format(
					"the request was rejected because its size (%1$s) exceeds the configured maximum (%2$s)", fileSize,
					super.sizeMax);

			throw new org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException(message, fileSize,
					super.sizeMax);
		}

		fileItem.write(file);
	}

	/**
	 * 获取文件信息 必须先调用 parseRequest(HttpServletRequest request)
	 * 
	 * @return
	 */
	public FileItem getFileItem() {
		return fileItem;
	}

	/**
	 * 为防止文件覆盖的现象发生，要为上传文件产生一个唯一的文件名
	 * 
	 * @param fileName
	 * @return
	 */
	public static String makeFileName(String fileName) {
		return UUID.randomUUID().toString().replaceAll("-", "") + "_" + fileName;
	}

	/**
	 * 获取文件扩展名
	 * @param fileName
	 * @return
	 */
	public static String getFileExtension(String fileName) {
		return fileName == null || fileName.lastIndexOf(".") == -1 ? null
				: fileName.substring(fileName.lastIndexOf(".") + 1);
	}
}
