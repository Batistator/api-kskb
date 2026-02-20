package com.batistes.kskb.api.service;

import java.io.InputStream;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jcifs.CIFSContext;
import jcifs.config.PropertyConfiguration;
import jcifs.context.BaseContext;
import jcifs.smb.NtlmPasswordAuthenticator;
import jcifs.smb.SmbFile;

@Service
public class SambaService {

    private final String domain = null;
    @Value("${samba.username}")
    private String user;
    @Value("${samba.password}")
    private String password;
    @Value("${samba.share.url}")
    private String shareUrl;

    public InputStream getSambaFileStream(String fileName) throws Exception {
        // 1. Configurar la autenticación
        Properties props = new Properties();
        PropertyConfiguration config = new PropertyConfiguration(props);
        CIFSContext context = new BaseContext(config);
        
        NtlmPasswordAuthenticator auth = new NtlmPasswordAuthenticator(domain, user, password);
        CIFSContext authenticatedContext = context.withCredentials(auth);

        // 2. Apuntar al archivo
        String fullPath = shareUrl + fileName;
        try (SmbFile smbFile = new SmbFile(fullPath, authenticatedContext)) {
            return smbFile.getInputStream();
        }
    }
}