package com.djdownloader.domain.port.out;

import org.springframework.core.io.Resource;

import java.util.Optional;

public interface Downloader {
   String extractFileName(String line);
   String executeCommand(String command, String format, String audioQuality);
   Optional<Resource> downloadFile(String fileName);
}
