package backend.pretest.service;


import backend.pretest.entity.Content;
import backend.pretest.exception.BaseInternalServerErrorException;
import backend.pretest.exception.BaseNotFoundException;
import backend.pretest.repository.ContentRepository;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContentService {

    private final ContentRepository repository;

    public ContentService(ContentRepository repository) {
        this.repository = repository;
    }

    @SneakyThrows
    public void createContent(String title, String description) {
        Content content = new Content();
        content.setTitle(title.trim());
        content.setDescription(description.trim());

        save(content);
    }

    public void updateContent(Integer contentId, String title, String description) throws BaseNotFoundException, BaseInternalServerErrorException {
        Content content = findById(contentId);
        content.setTitle(title.trim());
        content.setDescription(description.trim());
        save(content);
    }

    public void deleteContent(Integer contentId) throws BaseNotFoundException {
        Content content = findById(contentId);
        repository.delete(content);
    }

    public List<Content> findAll() {
        return repository.findAll();
    }

    public Content findById(Integer id) throws BaseNotFoundException {
        Optional<Content> opt = repository.findById(id);
        if (opt.isEmpty()) throw BaseNotFoundException.NotFoundContent();
        return opt.get();
    }

    public void save(Content content) throws BaseInternalServerErrorException {
        try {
            repository.save(content);
        } catch (Exception e) {
            throw BaseInternalServerErrorException.SaveError();
        }
    }
}
