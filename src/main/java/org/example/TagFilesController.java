package org.example;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.multipart.FileUpload;

import java.io.IOException;
import java.nio.file.Files;

@ApplicationScoped
@Path("/api/tag")
public class TagFilesController {
    @ConfigProperty(name = "tpu.storagedirectory")
    String storageDirectory;

    @Path("upload")
    @PUT
    public void uploadTagFile(@RestForm("tagFile") FileUpload tagFile) {
        try {
            Files.copy(tagFile.uploadedFile(), java.nio.file.Path.of(storageDirectory + "/sessions/" + tagFile.fileName()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(tagFile);
    }

}
