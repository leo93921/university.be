package it.unisalento.se.services;

import it.unisalento.se.converters.daoToDto.DocumentDaoToDto;
import it.unisalento.se.converters.dtoToDao.DocumentDtoToDao;
import it.unisalento.se.dao.Document;
import it.unisalento.se.exceptions.DocumentNotFoundException;
import it.unisalento.se.iservices.IDocumentService;
import it.unisalento.se.models.DocumentModel;
import it.unisalento.se.repositories.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class DocumentService implements IDocumentService {

    @Autowired
    private DocumentRepository repository;

    @Override
    @Transactional(readOnly = true)
    public DocumentModel getDocumentByID(Integer ID) throws DocumentNotFoundException {
        try {
            Document dao = repository.getOne(ID);
            return DocumentDaoToDto.convert(dao);
        } catch (EntityNotFoundException e) {
            throw new DocumentNotFoundException();
        }

    }

    @Override
    @Transactional
    public DocumentModel saveDocument(DocumentModel document) {
        Document dao = DocumentDtoToDao.convert(document);
        Document saved = repository.save(dao);
        return DocumentDaoToDto.convert(saved);
    }

}
