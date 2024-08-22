package com.santanu.ppc.service;

import java.io.IOException;
import java.io.InputStream;

public interface PPCService {

	void processFiles(InputStream inputStream) throws IOException;

}
