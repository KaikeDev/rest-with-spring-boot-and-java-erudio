package br.com.erudio.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.erudio.config.FileStorageConfig;
import br.com.erudio.exceptions.FileStorageException;
import br.com.erudio.exceptions.MyFileNotFoundException;
import io.micrometer.common.util.StringUtils;

@Service
public class FileStorageService {

	private final Path fileStorageLocation; // caminho completo até a pasta onde os arquivos serão salvos

	@Autowired
	public FileStorageService(FileStorageConfig fileStorageConfig) {

		// Aqui iniciamos a variavel
		Path path = Paths.get(fileStorageConfig.getUploadDir()).toAbsolutePath().normalize(); // transforma a variavel
																								// em um path do java

		this.fileStorageLocation = path;

		try {

			Files.createDirectories(this.fileStorageLocation);

		} catch (Exception e) {
			throw new FileStorageException("Could not create the directory where the uploaded files will be stored!!",
					e);
		}
	}

	// Gravar arquivos em disco
	public String storeFile(MultipartFile file) {
		String fileName = org.springframework.util.StringUtils.cleanPath(file.getOriginalFilename()); // pega o nome do
																										// arquivo
		try {

			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}

			Path targetPath = this.fileStorageLocation.resolve(fileName);
			Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING); // se ja existir um
																								// arquivo com o mesmo
																								// nome, ele substitui o
																								// arquivo

			return fileName;

		} catch (Exception e) {
			throw new FileStorageException("Could not store file " + fileName + " . Please try again", e);
		}
	}

	public Resource loadFileAsResource(String filename) {
			try {
				Path filePath = this.fileStorageLocation.resolve(filename).normalize();
				Resource resource = new UrlResource(filePath.toUri());
				
				if(resource.exists()) {
						return resource;
				}else {
					throw new MyFileNotFoundException("File not found ");
				}
			} catch (Exception e) {
				throw new MyFileNotFoundException("File not found "+ filename, e);
			}
	}
}
