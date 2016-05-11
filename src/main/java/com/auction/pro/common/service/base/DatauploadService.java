package com.auction.pro.common.service.base;

import java.io.File;

public interface DatauploadService {
	<E> E uploadData(File tempfile, byte[] stream, String uploadoption);

}
	