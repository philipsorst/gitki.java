package net.dontdrinkandroot.gitki.service.wiki;

import net.dontdrinkandroot.gitki.model.DirectoryPath;
import net.dontdrinkandroot.gitki.model.FilePath;
import net.dontdrinkandroot.gitki.service.git.GitService;
import net.dontdrinkandroot.gitki.service.lock.LockService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
@Service
public class DefaultWikiService implements WikiService
{
    private GitService gitService;

    private LockService lockService;

    private List<String> indexFiles = new ArrayList<>();

    protected DefaultWikiService()
    {
        /* Reflection Instantiation */
    }

    @Inject
    public DefaultWikiService(GitService gitService, LockService lockService)
    {
        this.gitService = gitService;
        this.lockService = lockService;
    }

    @Value("#{gitkiConfigurationProperties.indexFiles}")
    public void setIndexFiles(List<String> indexFiles)
    {
        this.indexFiles = indexFiles;
    }

    @Override
    public FilePath resolveIndexFile(DirectoryPath directoryPath)
    {
        for (String indexFile : this.indexFiles) {
            FilePath indexFilePath = directoryPath.appendFileName(indexFile);
            if (this.gitService.exists(indexFilePath)) {
                return indexFilePath;
            }
        }

        return null;
    }
}
